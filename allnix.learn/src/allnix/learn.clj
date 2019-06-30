; https://objectcomputing.com/resources/publications/sett/march-2009-clojure-functional-programming-for-the-jvm
(ns allnix.learn
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Introduction
(println "Hello")

;; Clojure Syntax
(set "aeiou") ; set can be used as a function
(def vowel? (set "aeiou"))
(vowel? \b)

((set [1 2 3]) 5)


; Region
(print "there")
