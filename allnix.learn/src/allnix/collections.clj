[1 2 3]

; Indexed access
(def my-array ["abc" false 99])
(get my-array 0) ; -> "abc"
(get my-array 3) ; nil when index out of bound
; count
(count my-array) ; 3
; Constructing
(vector 1 2 3)
; Adding elements -> Conjoin
(conj [1 2] 3 4 5 6)
; Lists
(def cards '(10 :ace :jack 9))
(first cards) ; 10
(rest cards) ; :ace :jack 9
; Adding elements
(conj cards :queen) ; :queen added to the front since cards is a list
; Stack access
(def stack '(:a :b))
(peek stack) ; :a
(pop stack) ; (:b)

;; Sets
(def players #{"Alice", "Bob", "Kelly"})
(conj players "Fred") ; add to a set
(disj players "Bob" "Sal") ; remove from a set
; Checking content
(contains? players "Kelly")
; Sorted sets
(sorted-set "Sigma" "Charlie" "Bravo" )
; sorted-set-by to use custom comparator

; into
(def players (sorted-set "Kelly" "Bob" "Alice"))
(def new-players ["Tim" "Sue" "Greg"])
(into players new-players)

;; Maps
; Creating a literal map
(def scores {"Fred" 1400 "Bob" 1240 "Angela" 1024})
; Adding new key-value pairs
(assoc scores "Sally" 0)
(assoc scores "Bob" 0)
; Removing
(dissoc scores "Bob")
; Looking up by key
(get scores "Angela")
; use map as a function
(def directions {:north 0 :east 1 :south 2 :west 3})
(directions :north)
; Looking up with a default
(get scores "Sam" 0)
(directions :northwest -1)
; Checking contains
(contains? scores "Fred")
(find scores "Fred")
; Keys and values
(keys scores)
(vals scores)

; Building a map
(def players (set ["Alice" "Kelly" "Bob"]))
(zipmap players (repeat 0))

(into {} (map (fn [player] [player 0]) players ))

(reduce (fn [Map player] (assoc Map player 0)) 
        {} ; initial value
        players; collection
        )


; Combining maps
(def new-scores {"Angela" 300 "Jeff" 900})
(merge scores new-scores)
(def new-scores {"Fred" 550 "Angela" 900 "Sam" 1000})
(merge-with + scores new-scores)


; Sorted maps
(def sm (sorted-map "Bravo" 204 "Alfa" 35 "Sigma" 99 "Charlie" 100))
(keys sm)
(vals sm)

;; Representing application domain information
; map with keywords
(def person {:first-name "Kelly"
              :last-name "Keen"
              :age 32
              :occupation "Programmer"
              })
; Field accessors
(get person :occupation)
(person :occupation)
(:occupation person)
(:favorite-color person "beige")

; Updating fields
(assoc person :occupation "Baker")
; Removing a field
(dissoc person :age)
