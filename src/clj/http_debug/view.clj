(ns http-debug.view
  (:require [io.pedestal.http.route :refer [url-for]]
            [hiccup.page :refer [html5]]
            [hiccup.core :refer [html h]]
            ;[hiccup.form :as f]
            [cemerick.austin.repls :refer (browser-connected-repl-js)]
            ))

(defn request [req]
  (html5 {:lang "en"}
         [:head
          [:title "Request"]
          [:meta {:name    :viewport
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
                  :rel  "stylesheet"}]
          [:div.container
           [:h3 "Mock not found"]
           [:div.col-md-8 [:pre req]]]
          [:script {:src "http://code.jquery.com/jquery-2.1.1.min.js"}]
          [:script {:src "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"}]]))

(defn index []
  (html5 {:lang "en"}
         [:head
          [:title "HTTP Debug"]
          [:meta {:name    :viewport
                  :content "width=device-width, initial-scale=1"}]
          [:link {:href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
                  :rel  "stylesheet"}]
          ;[:link {:href "/css/main.css"
          ;        :rel  "stylesheet"}]]
          ;[:body
          ; [:nav.navbar.navbar-default.navbar-fixed-top {:role "navigation"}
          ;  [:div.container
          ;   [:div.navbar-header
          ;    [:a.navbar-brand {:href "#"} ""]
          ;    [:div#palette-placeholder]]
          ;   ]]
          [:div.container
           "Try-hard flannel pug, Truffaut Vice yr Helvetica cliche Brooklyn cray Echo Park wayfarers slow-carb mixtape sustainable."
           ]
          [:script {:src "http://fb.me/react-0.11.2.js"}]
          [:script {:src "http://code.jquery.com/jquery-2.1.1.min.js"}]
          [:script {:src "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"}]
          [:script {:src "/js/main.js"}]
          [:script (browser-connected-repl-js)]]))
