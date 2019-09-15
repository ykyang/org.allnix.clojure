(ns allnix.learn
  (:gen-class))

;; Define a function
(defn hello 
  "A function that says hello"
  [person]
  (str "hello " person)
  )
(println (hello "abc"))
(defn ^{:t1 1} foo
  "My first foo function"
  {:t2 2}
  ([a b] (+ a b))
  {:t3 3}
  )
(println (meta #'foo))
