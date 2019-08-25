;; https://clojure.org/guides/learn/functions
(ns allnix.learn (:gen-class))
;;    name   params         body
;;    -----  ------  -------------------
(defn greet  [name]  (str "Hello, " name) )

;; Multi-arity functions
(defn messenger
  ([msg] (println msg))
  ([] (messenger "Hello World!"))
  )

;; Variadic functions
(defn hello [greeting & who ]
  (println greeting who)
  )
(hello "Hello" "world" "class") ; Hello (world class)

;; Anyonymous Functions
(fn [message] (println message))
(def greet (fn [name] (str "Hello " name)))

; #()
; % is used for a single parameter
; %1, %2, %3, etc are used for multiple parameters
; %& is used for any remaining (variadic) parameters

; anyonymous function accept 1 argument
#(+ 6 %)
; anyonymous function accepts 2 arguments
#(+ %1 %2)
; wrap arg into vector
#(vector %)
; equal to
(fn [x] [x])



;; Applying Functions
(apply +  '(1 2 3))



;; Locals and Closures

;;      bindings     name is defined here
;;    ------------  ----------------------
;;(let  [name value]  (code that uses name))










