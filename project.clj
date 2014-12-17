(defproject http-debug "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :source-paths ["src/clj"]
            :url "http://example.com/FIXME"
            :plugins [[lein-cljsbuild "1.0.3"]
                      [com.cemerick/austin "0.1.5"]]
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [io.pedestal/pedestal.service "0.3.1"]
                           [io.pedestal/pedestal.service-tools "0.3.1"]
                           [io.pedestal/pedestal.jetty "0.3.1"]
                           [ns-tracker "0.2.2"]
                           [ring/ring-devel "1.2.2"]
                           [hiccup "1.0.5"]
                           [org.clojure/clojurescript "0.0-2371"]
                           [reagent "0.4.3"]]
            :repl-options {:init-ns http-debug.core}
            :main http-debug.core
            :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                  :compiler     {:output-to     "resources/public/js/main.js"
                                                 :optimizations :whitespace
                                                 :pretty-print  true}}]})