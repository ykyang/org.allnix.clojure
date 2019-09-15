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

;; local scope or lexical scope
(let [x 1, y 2]
  (+ x y)
  )

(defn messenger [msg]
  (let [a 7, b 5, c (clojure.string/capitalize msg)]
    (println a b c)
    )
  )

;; Closures
(defn messenger-builder [greeting]
  (fn [who] (println greeting who))
  )
; "Hello" is in local scope but got passed into hello-er
(def hello-er (messenger-builder "Hello"))
(hello-er "world!")

;;>
;;> Java Interop
;;>

;; Invoking Java code
(fn [obj] (.length obj))
; same as
#(.length %)

;; Test your knowledge
; 1
(defn greet [] (println "Hello"))
; 2
(def greet (fn [] (println "Hello1")))
(def greet #(println "Hello2"))
; 3
(defn greeting
  ([x y] (str x ", " y "!"))
  ([x] (str "Hello, " x "!"))
  ([] "Hello, World!"))
; test
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))

; 4
; identity function
(defn do-nothing [x] x)
; 5
(defn always-thing [& args] 100)
; 6
; constantly function
(defn make-thingy [x] 
  (fn [& args] x)
  )
; Test
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)
      ]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range))))
  )
; 7

(defn triplicate [f] (f) (f) (f))
; 8
(defn opposite [f] 
  (fn [& args] (not (apply f args)))
  )

( (complement (fn [x] x)) true)
; 9
(defn triplicate2 [f & args]
  (triplicate #(f args))
  )

; 10
(Math/cos Math/PI)
(defn sc2 [x]
  (+ (Math/pow ( Math/cos x) 2) (Math/pow (Math/sin x) 2))
  )
; 11
(defn http-get [url-string]
  (let [url (java.net.URL. url-string)] 
    (slurp (.openStream url))
  )
  )
(assert
(.contains (http-get "https://www.w3.org") "html"))

; Implement http-get with slurp
(defn http-get [url]
  (slurp url)
  )
(assert
(.contains (http-get "https://www.w3.org") "html"))


; 12 partial
(defn one-less-arg [f x]
  (fn [& args] f x args)
  )

; 13
(defn two-fns [f g]
  (fn [x] (f (g x)))
  )
