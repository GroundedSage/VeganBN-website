(set-env!
 :source-paths    #{"src/cljs" "src/clj" "src/cljc"}
 :resource-paths  #{"resources"}
 :dependencies '[[adzerk/boot-cljs          "1.7.48-5"  :scope "test"]
                 [adzerk/boot-cljs-repl     "0.3.0"      :scope "test"]
                 [adzerk/boot-reload        "0.5.1"      :scope "test"]
                 [pandeiro/boot-http        "0.7.2"      :scope "test"]
                 [com.cemerick/piggieback   "0.2.1"      :scope "test"]
                 [org.clojure/tools.nrepl   "0.2.12"     :scope "test"]
                 [samestep/boot-refresh "0.1.0" :scope "test"]
                 [weasel                    "0.7.0"      :scope "test"]
                 [org.clojure/clojurescript "1.9.494"]
                 [org.clojure/clojure "1.8.0"]
                 [rum "0.10.8"]
                 [org.martinklepsch/boot-garden "1.3.2-0" :scope "test"]
                 [devcards "0.2.2"]
                 [bidi "2.0.14"]
                 [garden "1.3.2"]])



(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[pandeiro.boot-http    :refer [serve]]
 '[samestep.boot-refresh :refer [refresh]]
 '[org.martinklepsch.boot-garden :refer [garden]]

 '[vbn.static :as static]
 '[vbn.removehtmlextension :refer [remove-html-task]]
 '[vbn.atomicstyles :refer [add-atomic-styles]]
 '[vbn.app :as app]
 '[bidi.bidi :refer [path-for route-seq]]
 '[vbn.components :refer [my-routes]])


;(goog.style/installStyles (styler/get-css-str true))

#_(deftask cards []
    (set-env! :resource-paths #(conj % "cards")
              :source-paths #(conj % "cards"))
    identity)

(deftask add-css-path []
    (set-env! :resource-paths #(conj % "target/css")
              :source-paths #(conj % "target/css"))
    identity)

(deftask build []
  (comp (speak)
        ;;;;;;
        ;(remove-html-task)
        (cljs)
        ;; Alternate comment below to toggle between Single Page Application and full site.
        (static/make-pages)
        ;;(static/make-page :route :index)

        (garden :styles-var 'vbn.styles/screen :output-to "css/garden.css")
        (add-atomic-styles)))


(deftask run []
  (comp (serve)
        (watch)
        (cljs-repl)
        (reload)
        ;(cards)
        (build)))



(deftask production []
  (task-options! cljs {:optimizations :advanced}
                 garden {:pretty-print false})
  identity)



(deftask development []
  (task-options! cljs {:optimizations :none
                       :source-map true
                       :compiler-options {:devcards true}}
                 reload {:on-jsload 'vbn.app/init})
  identity)



(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp
   (development)
   (run)))
