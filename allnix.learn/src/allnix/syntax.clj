(ns allnix.learn (:gen-class))
;; https://clojure.org/guides/learn/syntax
42 ; integer
123456789012345678901234567890N ; arbitrary precision
-1.5 ; float
1234567890.1234567890M ; arbitrary precsion
##Inf
##-Inf
##NaN
22/7 ; ratio
#"[0-9+]" ; regular expression

'(1 2 3) ; list
[1 2 3 ] ; vector
#{1 2 3} ; set
{:a 1, :b 2} ; map

;; REPL
(require '[clojure.repl :refer :all])
(doc +)
(println (apropos "+")) ; search by regular expression
;; (find-doc "trim") ; search in doc string
(dir clojure.repl) ; list all function in a name space
(source dir) ; see source code

;; def
(def x 7)
(+ x x)
;; Test your knowledge
(println (+ 7654 1234 ))
(println  (/ (+ (+ (* 3 4) 7) 5) 10.))
(println (/ (+ 7 (* 3 4) 5) 10.)) ; 2.4
(rem 24 10) ; 4
(mod 24 10)
(require '[infix.macros :refer :all]) ; TODO: learn what the heck is this
(refer 'infix.macros :only '[infix from-string])
(println (infix (7 + 3 * 4 + 5) / 10.)) ; space around operator is necessary
