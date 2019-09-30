;; See the link below for ns cheat sheet
;; https://gist.github.com/ghoseb/287710/
(ns allnix.learn.brave-ch3-live
  (:require [infix.macros :refer [infix $=]])
  )
;(require '[infix.macros :refer :all]) ; TODO: Move this to (ns)
;(refer 'infix.macros :only '[infix from-string])

;; Basic arithmetic
(infix 1 + 2 + 3)
($= 1 + 2)
(+ 1 2 3)

;; when
(when true
  (println "It is true")
  "This is what got returned"
  )

;; nil
(nil? 1) ; false
(nil? nil) ; true
(if "str" "It is true") ; true
(if (not nil) "It is true") ; true 

;; equality, and, or
(= 1 1) ; true
(= nil nil); true 
