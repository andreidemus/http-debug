(ns http-debug.core
  (:require
    [io.pedestal.interceptor :refer [defhandler]]
    [io.pedestal.http.route.definition :refer [defroutes]]
    [io.pedestal.http.route :as route :refer [router]]
    [io.pedestal.http :as http]
    [io.pedestal.http.body-params :refer [body-params]]
    [ring.util.response :refer [response redirect]]
    [ns-tracker.core :refer [ns-tracker]]
    [ring.handler.dump :refer [handle-dump]]
    [io.pedestal.http.ring-middlewares :as middleware]
    [http-debug.view :as v]))

(def mocks (atom [{:uri            "/test1"
                   :query-params   {:a "1"}
                   :request-method :get
                   :response       {:body "{\"a\": 5}" :status 201}}
                  {:uri            "/test2"
                   :request-method :post
                   :response       {:body "matched test2"}}]))

(defn matched? [r m]
  (->> m
       keys
       (filter #(not= % :response))
       (every? #(= (get r %) (get m %)))))

(defn find-mock [req]
  (some #(if (matched? req %) %) @mocks))

(defn mock [req]
  (if-let [m (find-mock req)]
    {:status  (or (get-in m [:response :status]) 200)
     :body    (get-in m [:response :body])
     :headers {}}
    {:status  404
     :body    (v/request (str req))
     :headers {}}))

(defhandler index
            [req]
            (response (v/index)))

(defroutes routes
           [[["/" ^:interceptors [http/html-body
                                  (body-params)]
              ["/request" {:any handle-dump}]
              ["/" {:get index}]
              ["/:any" ^:constraints {:any #".+"} {:any mock}]]]])

(def modified-namespaces (ns-tracker "src"))

(def service
  {::http/interceptors [http/log-request
                        http/not-found
                        route/query-params
                        (middleware/file-info)
                        (middleware/resource "public")
                        (router (fn []
                                  (doseq [ns-sym (modified-namespaces)]
                                    (require ns-sym :reload))
                                  routes))]
   ::http/join?        false
   ::http/port         (or (some-> (System/getenv "PORT")
                                   Integer/parseInt)
                           8080)})

(defn run []
  (-> service
      http/create-server
      http/start))

(defn run-with-js-repl []
  (let [repl-env (reset! cemerick.austin.repls/browser-repl-env
                         (cemerick.austin/repl-env))]
    (run)
    (cemerick.austin.repls/cljs-repl repl-env)))

(defn -main
  [& args]
  (run))