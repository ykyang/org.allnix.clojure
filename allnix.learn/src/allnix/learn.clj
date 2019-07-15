; https://objectcomputing.com/resources/publications/sett/march-2009-clojure-functional-programming-for-the-jvm

; C-x b: Select buffer

; M-x cider-jack-in: Cider Mode
; C-c-k: Load the current buffer
; C-c-c: Eval current form under cursor
; C-c-v r: Eval region
; C-o u: Undo

; C-c M-n n: set namespace


; Additional package
; M-x package-install RET company RET
; M-x global-company-mode

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
(clojure.set/difference more-stooges less-stooges)


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
  (println (str (name color) " " (name flavor))))

; Region
(print "there")
