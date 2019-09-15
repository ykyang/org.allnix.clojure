; https://objectcomputing.com/resources/publications/sett/march-2009-clojure-functional-programming-for-the-jvm
;
; Vim
; :Eval evaluates a range

;
; C-x b: Select buffer

; M-x cider-jack-in: Cider Mode
; C-c-k: Load the current buffer
; C-c-c: Eval current form under cursor
; C-c-v r: Eval region
; C-o u: Undo

; C-c M-n n: set namespace


; Additional package
; M-x package-install RET company RET
; global-company
; sublimity

; M-x global-company-mode
;; M-x smooth-scrolling-mode

(ns allnix.learn
  (:gen-class))

;; Introduction
(println "Hello")

;; Clojure Syntax
(set "aeiou") ; set can be used as a function
(def vowel? (set "aeiou"))
(vowel? \b) ; \b
(defn pig-latin [word] ; define a function
  ; word: string
  (let [first-letter (first word)] ; local binding
;    (println first-letter) ; debug
    (if (vowel? first-letter)
      (str word "ay") ; then
      (str (subs word 1) first-letter "ay") ; else
      )
    )
  )

(println (pig-latin "red"))
(println (pig-latin "orange"))


; REPL
;(require 'clojure.string)
;(doc clojure.string/join)


; VARS
(println "VARS")
(def ^:dynamic v 1) ; dynamic

(defn f1 []
  (println "f1: v: " v))
(f1)

(defn f2 []
  (println "f2: before let, v: " v)
  ; creates local binding that shadows global one
  (let [v 2]
    (println "f2: in let, v: " v)
    (f1))
  ; outside of let, v refers to the global binding
  (println "f2: outside of let, v: " v)
  )
(f2)
; binding
(defn f3 []
  (println "f3: before binding, v: " v)
  (binding [v 3]
    (println "f3: within binding, v: " v)
    (f1))
  (println "f3: outside binding, v: " v)
)
(f3)

(defn f4 []
  (def v 4) ; not a good practice
  (println "f4: v: " v))
(f4)


; COLLECTIONS
; immutable, heterogeneous and persistent

(count [19 "yellow" true])
(conj [19 "yellow"] true)
(reverse [19 "yellow"])
; map
(map #(+ % 3) [2 4 7])
(map + [2 4] [1] [1 2 3])
; apply
(apply + [2 4 6])

(def stooges ["Moe" "Larry" "Curly" "Shemp"])
(first stooges)
(second stooges)
(last stooges)
(nth stooges 2)

(next stooges)
(butlast stooges)
(drop-last 2 stooges)
(filter #(> (count %) 3) stooges)
(nthnext stooges 2)

(every? #(instance? String %) stooges)
(not-every? #(instance? String %) stooges)
(some #(instance? Number %) stooges)
(not-any? #(instance? Number %) stooges)

; LISTS
; ways to create lists
(def stooges (list "Moe" "Larry" "Curly"))
(def stooges (quote ("Moe" "Larry" "Curly")))
(def stooges '("Moe" "Larry" "Curly"))

; not efficient
(some #(= % "Moe") stooges)
; use set to check
(contains? (set stooges) "Moe")

(def more-stooges (conj stooges "Shemp"))
;(println more-stooges)
(def less-stooges (remove #(= % "Curly") more-stooges))
(println less-stooges)

(println "Brady Bunch")
(def kids-of-mike '("Greg" "Peter" "Bobby"))
(def kids-of-carol '("Marcia" "Jan" "Cindy"))
(def brady-bunch (into kids-of-mike kids-of-carol))
(println brady-bunch)
(peek brady-bunch)
(def less-brady-bunch (pop brady-bunch))
(println less-brady-bunch)
(peek less-brady-bunch)


; VECTORS
; conj better than cons
; efficient: nth, assoc
; efficient: add to the end
(def stooges (vector "Moe" "Larry" "Curly"))
(def stooges ["Moe" "Larry" "Curly"])

(get stooges 1 "unknown")
(get stooges 3 "unknown")
(assoc stooges 2 "Shemp")


; SETS
; sorted and unsorted
(def stooges (hash-set "Moe" "Larry" "Curly"))
(def stooges #{"Moe" "Larry" "Curly"})
(println stooges)
(def stooges (sorted-set "Moe" "Larry" "Curly"))
(println stooges)

; contains?
(contains? stooges "Moe")
(contains? stooges "Mark")

; set as a function
(stooges "Moe")
(stooges "Mark")

; conj, into, disj
(def more-stooges (conj stooges "Shemp"))
(def less-stooges (disj more-stooges "Curly"))
(println more-stooges)
(println less-stooges)
;(clojure.set/difference more-stooges less-stooges)


; MAPS
(def popscile-map
  {:red :cherry, :green :apple, :puple :grape})
(println popscile-map)

; get value
(get popscile-map :green)
(popscile-map :green)
(:green popscile-map)
(contains? popscile-map :green)
(keys popscile-map)
(vals popscile-map)
; assoc
(assoc popscile-map :green :lime, :blue :blueberry)
(dissoc popscile-map :green :blue)

; doseq
(doseq [[color flavor] popscile-map]
  (println 
   (str "The flavor of " (name color) " popsicles is " (name flavor))))

;> select-keys
(select-keys popscile-map [:red :green])

;> get-in
(def person 
  {
   :name "Mark"
   :address {
             :street "644 Glen Summit"
             :city "St. Charles"
             :state "Missouri"
             :zip "63304"}
   :employer {
              :name "Object Computing, Inc."
              :address {
                        :street "12140 Woodcrest Executive Drive, Suite 250"
                        :city "Creve Coeur"
                        :state "Missouri"
                        :zip "63141"}}})
(get-in person [:employer :address :city])

;> "->" Thread Macro
(-> person :employer :address :city)
;> reduce
(reduce get person [])

;> assoc-in
; Replace the value
(assoc-in person [:employer :address :city] "Clayton")

;> update-in
; Use a function to "assoc-in"
(println (update-in person [:employer :address :zip] str "-1234"))


;>> STRUCTMAPS
; Deprecated, use defrecord

;>> defrecord
; https://clojuredocs.org/clojure.core/defrecord
(defrecord Person [fname lname address])
(defrecord Address [street city state zip])
(def stu (Person. "Stu" "Halloway"
                  (Address. "200 N Mangum"
                            "Durham"
                            "NC"
                            "27701")))
(:lname stu)
(-> stu :address :city)
(assoc stu :fname "Stuart")
(update-in stu [:address :zip] str "-1234")



;>> DEFINING FUNCTIONS
; defn-: Defines private functions
(defn parting
  "returns a String parting"
  [name]
  (str "Goodby, " name))
(println (parting "Mark"))

; optional arguments
(defn power [base & exponents]
  (reduce #(Math/pow %1 %2) base exponents))
(power 2 3 4)

; overloading
(defn parting
  "Return a parting string in a given language"
  ([] (parting "World")) ; default name: "World"
  ([name] (parting name "en")); default language:English
  ([name language]
   (condp = language
     "en" (str "Goodbye, " name)
     "es" (str "Adios, " name)
     (throw (IllegalArgumentException. (str "Unsupported language " language)))
     )
   )
)
(parting "Ironman" "es")

;> anonymnous functions
(def years [1940 1944 1961 1985 1987])
; short hand
(filter #(even? %) years)
; long version
(filter (fn [year] (even? year)) years)

;> defmulit & defmethod
;TODO


;> range
;TODO

;>> JAVA INTEROPERABILITY
(import '(java.util Calendar GregorianCalendar))
(import '(javax.swing JFrame JLabel))
(. Calendar APRIL)
(Calendar/APRIL)

; new instance
(def calendar (GregorianCalendar. 2008 Calendar/APRIL 07))
(println calendar)
; (. calendar add Calendar/MONTH 2)
(.add calendar Calendar/MONTH 2)
(println calendar)
(.. calendar getTimeZone getDisplayName)

; invoke multiple methods
(doto calendar
  (.set Calendar/YEAR 1981)
  (.set Calendar/MONTH Calendar/AUGUST)
  (.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(.format formatter (.getTime calendar) )

(map #(.substring %1 %2) ; String.substring
     ["Moe" "Larry" "Curly"] [1 2 3])

; TODO: memfn

;>> PROXIES
; Mainly used for callback

;>> THREADS
(defn delayed-print [ms text]  
  (Thread/sleep ms) (print text))

(.start (Thread. #(delayed-print 1000 ", World!")))
(print "Hello")


;>> EXCEPTION HANDLING
(defn collection? [obj]
  (println "obj is a" (class obj))
  (or (coll? obj) ; Clojure collection
      (instance? java.util.Collection obj)
      )
)

(defn average [coll]
  (when-not (collection? coll) 
    (throw (IllegalArgumentException. "Expect a collection")))
  (when (empty? coll) 
    (throw (IllegalArgumentException. "Collection is empty")))
  (let [sum (apply + coll)] 
    (/ sum (count coll)))
)
(average '(2. 3))
(collection? (java.util.ArrayList.))
(let [a 5
      b 6]
  (+ a b)
)

;>> CONDITIONAL PROCESSING
(import '(java.util Calendar GregorianCalendar))

; if
(let 
    [gc (GregorianCalendar.)
     day-of-week (.get gc Calendar/DAY_OF_WEEK)
     is-weekend (or (= day-of-week Calendar/SATURDAY) 
                    (= day-of-week Calendar/SUNDAY))
     ]
  (if is-weekend
    (println "play") ; then
    (do 
      (println "work")
      (println "sleep")  
      )
    )
)


; when, when-not
#_(when is-weekend
  (println "play"))
#_(when-not is-weekend
  (println "work")
  (println "sleep")
)

; if-let
(defn process-next [waiting-line]
  (if-let [name (first waiting-line)]
    (println name "is next")
    (println "no waiting")
))
(process-next '("Jeremy" "Amanda"))
(process-next '())

; when-let
(defn summarize
  "Print the first item in a collection followed by a period for each
  remaining tiem."
  [collection]
  (when-let [head (first collection)]
    (print head)
    (dotimes [_ (dec (count collection))] 
      (print "."))
    (println)
    )
)
(summarize ["Moe" "Larry"])
(summarize [])
(do (print "Enter a number: ") (flush))
; Read from stdin
(let [reader (java.io.BufferedReader. *in*) ; *in* is stdin
      line (.readLine reader)
      value (try
              (Integer/parseInt line)
              (catch NumberFormatException e
                line))
      ]
  (println 
   (condp = value
     1 "one"
     2 "two"
     (str "Unexpected value: " value)
     ))
  (println 
   (condp instance? value
     Number (* value 2)
     String (* (count value) 2)
     ))
)

; cond


; ITERATION

; dotimes
(dotimes [card-number 3]
  (println "Deal card number" (inc card-number)))

; while
(defn my-fn [ms]
  (println "Enter my-fn")
  (Thread/sleep ms)
  (println "Exit my-fn")
)

(let [thread (Thread. #(my-fn 1))]
  (.start  thread)
  (println "Thread started")
  (while (.isAlive thread)
    (print ".") (flush)
    )
  (println "Thread stopped")
)

; Region

(print "there")
