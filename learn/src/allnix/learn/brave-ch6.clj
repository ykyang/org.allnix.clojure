(ns allnix.learn (:gen-class))
(clojure.core/refer-clojure)

; *ns* Current name space
(print *ns*) ; object of current namespace
(print (ns-name *ns*)) ; name of current namespace

(def great-book ["East of Eden" "The Glass Bead Game"])

(ns-interns *ns*)
(get (ns-interns *ns*) 'great-book) ; #' ref to a variable

; de-reference
(deref #'allnix.learn/great-book)

; Creating and Switching to Namespaces


; create-ns

; ns-name

; in-ns
(in-ns 'cheese.taxonomy)
(def cheddars ["mild" "medium"])
(def bries ["Wisconsin" "Somerset"])

(in-ns 'allnix.learn)

(refer 'cheese.taxonomy)
(println cheddars)

(refer 'cheese.taxonomy :only [])
(println cheddars)
