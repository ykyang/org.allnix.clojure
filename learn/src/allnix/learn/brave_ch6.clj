(ns allnix.learn.brave-ch6)
(clojure.core/refer-clojure)

(def great-book ["East of Eden" "The Glass Bead Game"])
(def ns-interns-ch6 (ns-interns *ns*)) ; what the ns looks like

(def heist-list [{:location "Cologne, Germany"
                  :cheese-name "Archbishop Hildebold's Cheese Pretzel"
                  :lat 50.95
                  :lng 6.97}
                 {:location "Zurich, Switzerland"
                  :cheese-name "The Standard Emmental"
                  :lat 47.37
                  :lng 8.55}
                 
                 ])
