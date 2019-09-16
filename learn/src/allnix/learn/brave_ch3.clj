(ns allnix.learn.brave-ch3)

(+ 1 2 3)


(require '[infix.macros :refer :all]) ; TODO: learn what the heck is this
(refer 'infix.macros :only '[infix from-string])
(infix 5 + 6)

; when
(when true
  (println "Success!")
  "abra cadabra"
  )

; nil, true, false ...
(nil? 1)
(nil? nil)
(if "str"
  "str is truthy"
  )
(if nil
  "nil is falsey"
  )

; equality, and, or
(= 1 1)
(= nil nil)
(or false nil :abc :def) ; return first true expression
(or false nil) ; return last false expression
(or false) 
(and :abc :def) ; return last true expression
(and :abc nil false) ; return first false expression

;; Data Structure
; Map
(hash-map :first "Charlie" :last "McFishwich")
(def operators  {"plus" + "minus" -})
((operators "plus") 1 3) ; use map as operator
((get operators "plus") 1 3) ; use "get"


; Nested Map with keyword (word starts with :)
(def person {:name {:first "John" :last "Conner"}})
(get person :name)
(:name person)
(person :name)
; get-in
(get-in person [:name :first])

;; Vectors
(def v [3 2 1])
(get v 1)
(conj v 4 5 6) ; Add to the end

;; Lists
(def l '(1 2 3 4))
(nth l 2)
(def l (list 1 "two" {3 4}))
(conj '(1 2 3) 4) ; Add to the beginning

;; Sets
(def s #{"kurt" 20 :ice})
(def s (hash-set 1 2 3 1 2 3))
(def s (conj #{:a :b} :b :c))
(contains? s :a)


;; Functions
; Calling Functions
(* 1 2 3)
(first [1 2 3])

(inc 1.1) ; -> 2.1 
(map inc [1 2 3]) ; -> (2 3 4) map function

; Defining Functions
(defn no-params
  "Function without parameters"
  []
  "no params"
  )
(defn one-params
  "Function with one parameter"
  [x]
  "one param"
  )
(defn multi-arity
  "Function with multiple arity"
  ([x y z]
   (println x y z)
   )
  ([x y]
   (println x y "c")
   )
  )

; variable arity
(defn add 
  "Add variable number of numbers"
  [& args]
  (apply + args)
  )

; Destructuring
(defn my-first
  "Accept a vector as the first argument and ask Clojure to assign the
  first item to 'first-item'"
  [[first-item & ignore]]
  (println "first: " first-item)
  (println "ignore: " ignore)
  )

(defn gps 
  "Takes a map that contains :lat and :lng"
  [{lat :lat lng :lng}]
  (println "Lat & Long: " lat " " lng)
  )
(defn gps-2
  "Same as above but more concise"
  [{:keys [lat lng] :as loc-map}] ; loc-map is the full map
  (println "Lat & Long: " lat " " lng)
  (println "full map: " loc-map)
  )
(def location-map {:lat 13.123 :lng 96.133 :other "some other info"})
(gps location-map)
(gps-2 location-map)

; Anonymous Functions
(map (fn [name] (str "Hi, " name)) ["a" "b"])

; #()
(map #(str "Hi, " %1 ) ["a" "b"])

; Returning Functions



(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
"Replace 'left-' with 'right-' of the value of :name

 part {}: A map with key :name"
  [{:keys [name] :as part}]
  (let [new-name (clojure.string/replace name #"^left-" "right-")]
;   {:name name :size (:size part) }
     (assoc part :name new-name) ; this is better than right?
     )
  )

(matching-part {:name "left-foot" :size 2} ) ; {:name "right-foot", :size 2}
(matching-part {:name "head" :size 1}) ; no change

(defn symmetrize-body-part
  "Accept a sequence of maps that have :name and :size.  For each :name that starts with 'left-' creates a map with :name starts 'right-'"
  [asym-parts]
  (loop [remaining-asym-parts asym-parts
         final-parts []]
    (if (empty? remaining-asym-parts)
      ; empty
      final-parts
      ; not empty
      (let [[part & remaining] remaining-asym-parts] ; get the first item
        (recur 
          ; first loop binding 
          remaining
          ; second loop binding
          (let [part2 (matching-part part)]
            ; part could be the same as part 2 so we need to use
            ; set [part part2]
            (into final-parts (set [part part2]))
            )
          )
        )
      )
    )
  )
(println asym-hobbit-body-parts)
(symmetrize-body-part asym-hobbit-body-parts )


; let
(def dalmatian-list ["Pongo" "Perdita" "Puppy-1" "Puppy-2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians
  )
; rest in let
(let [[pongo & others] dalmatian-list]
  (println pongo)
  (println others)
  )

; into
(into [1 2] [3 4])
; loop and recur
(loop [ind 0]
  (println "ind: " ind)
  (if (< ind 10) 
    (recur (inc ind))
  )
  )
;; Regular Expressions
(re-find #"^left-" "left-eye")
(re-find #"^left-" "right-eye")
(clojure.string/replace "left-eye" #"^left-" "right-") ; -> right-eye 
(clojure.string/replace "eye" #"^left-" "right-") ; -> eye

;; reduce
(reduce + [ 1 2 3])

(defn add-matching-part
  "Add matching part to total body part

total-body-part: list of body parts
part {}: A body part {:name :size}"
  [total-body-part part]
  (let [part2 (matching-part part)]
    (into total-body-part (set [part part2]))
    )
  )
(add-matching-part [] {:name "left-eye" :size 1})
(add-matching-part [] {:name "head" :size 2})
(defn symmetrize-body-part-2
  "Accept a sequence of {:name :size}"
  [asym-body-parts]
  (reduce add-matching-part [] asym-body-parts)

  )
(symmetrize-body-part-2 asym-hobbit-body-parts)
