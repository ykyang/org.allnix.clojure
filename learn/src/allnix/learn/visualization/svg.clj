(ns allnix.learn.visualization.svg)
(print *ns*)

(defn latlng->point
  "Convert lat/lng map to comma-separated string"
  [{:keys [lat lng]}]
  (str lat "," lng)
  )
(defn points
  "Convert a sequence of {:lat :lng} to string"
  [locations]
  (clojure.string/join " " (map latlng->point locations))
  )
(latlng->point {:lat 12 :lng 95})
(points [{:lat 12 :lng 95} {:lat 13 :lng 94}])
