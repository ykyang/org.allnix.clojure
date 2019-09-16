(ns allnix.learn.brave_ch6)
(clojure.core/refer-clojure)

; *ns* Current name space
(print *ns*) ; object of current namespace
(print (ns-name *ns*)) ; name of current namespace

(def great-book ["East of Eden" "The Glass Bead Game"])

(ns-interns *ns*)
(get (ns-interns *ns*) 'great-book) ; #' ref to a variable

; de-reference
(deref #'allnix.learn.brave_ch6/great-book)

; Creating and Switching to Namespaces


; create-ns

; ns-name

; in-ns
(in-ns 'cheese.taxonomy)
(clojure.core/print "Hello")
(def cheddars ["mild" "medium"])
(def bries ["Wisconsin" "Somerset"])

(in-ns 'allnix.learn.brave_ch6)

(refer 'cheese.taxonomy)
(println cheddars)

(println cheddars)
