(ns allnix.learn.brave-ch6-live
  (:require [allnix.learn.brave-ch6 :as ch6]
            [allnix.learn.visualization.svg :as svg]
            )
  )
;(clojure.core/refer-clojure) ; this is buit-in "ns"
; *ns* Current name space
(println *ns*) ; object of current namespace
(println (ns-name *ns*)) ; name of current namespace


;; Evalute function definition
;;(require '[allnix.learn.brave-ch6 :as ch6])
;;(require '[allnix.learn.visualization.svg :as svg])

;; like "import" in Java
;(refer 'allnix.learn.brave-ch6)
;(refer 'allnix.learn.visualization.svg)

;; Check some values
(println ch6/great-book) ; access w/o name space

(println ch6/ns-interns-ch6)
(println allnix.learn.brave-ch6/great-book) ; access w/ full name space
;; de-reference
(println (deref #'allnix.learn.brave-ch6/great-book))

(svg/latlng->point {:lat 12 :lng 95})
(svg/points heist-list)



;; ns call can incorporate require, use, in-ns, alias, and refer




;; Not sure how useful is this
;; Switch between namespace
; in-ns
(in-ns 'cheese.taxonomy)
(clojure.core/print "Hello")
(def cheddars ["mild" "medium"])
(def bries ["Wisconsin" "Somerset"])

(in-ns 'allnix.learn.brave-ch6)
(refer 'cheese.taxonomy)
(println cheddars)

(println cheddars) 
