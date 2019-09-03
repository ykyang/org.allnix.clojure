(ns allnix.learn (:gen-class))
;; Flow Control Expressions
; if
(str "2 is " (if (even? 2) "even" "Odd"))

; Truth
(if true :truthy :falsey)
(if (Object.) :truthy :falsey) ; objects are true
(if [] :truthy :falsey) ; empty collections are true
(if 0 :truthy :falsey) ; 0 is true
(if false :truthy :falsey) ; false is false
(if nil :truthy :falsey) ; nil is false

; if and do
(if (even? 5)
  (do (println "even") true)
  (do (println "odd") false)
  )
; when
(def x -15)
(when (neg? x) (throw (RuntimeException. (str "x must be positive: " x))))

; cond
(let [x 5] (cond
  (< x 2) "x is less than 2"
  (< x 10) "x is less than 10"
  ))

; cond and else
(let [x 11]
  (cond
    (< x 2) "x is less than 2"
    (< x 10) "x is less than 10"
    
    :else "x is greater than or equal to 10"
    )
  )

; case: constant time switching
(defn foo [x]
  (case x
    5 "x is 5"
    10 "x is 10"
    "x isn't 5 or 10" ;else
    )
  )
(foo 10)
(foo 11)

;; Iteration for Side Effects

; dotimes
(dotimes [ind 3]
  (println ind)
  )

; doseq
(doseq [ind (range 3)]
  (println ind)
  )

; doseq with multiple bindings
(doseq [letter [:a :b]
        number (range 3)]
  (prn [letter number])
  )

; Clojure's for
(for [letter [:a :b]
      number (range 3)
      ]
  [letter number]
  )

;; Recursion
; Recursion and Iteration
; loop and recur
(loop [i 0]
  (if (< i 10) 
    (recur (inc i))
    i
    )
  )

;; Exceptions
; Exception handling
(try
  (/ 2 0)
  (catch ArithmeticException e
    "divide by zero")
  (finally
    (prn "cleanup")
    )
  )
; Throwing exceptions
(try
  (throw (RuntimeException. "Something went wrong"))
  (catch Exception e (.getMessage e))
  )
; ex-info ex-data
(try
  (throw (ex-info "There was a problem" {:detail 42}))
  (catch Exception e
    (prn (:detail (ex-data e)))
    )
  )
; with-open
(let [out (clojure.java.io/writer "/tmp/new.txt")]
  (try 
    (.write out "some text")
    (finally (.close out))
    )
  )

(with-open [out (clojure.java.io/writer "/tmp/new.txt")]
  (.write out "some text text")
  )
