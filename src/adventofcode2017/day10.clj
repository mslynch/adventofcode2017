(ns adventofcode2017.day10
  (:require [clojure.string :as str]))

(defn new-list
  "The new list, given the current position and length."
  [list position input-length]
  (do
   ; (println [input-length position])
   (let [dropped-cycled (drop position (cycle list))
         list-size (count list)
         non-reversed-size (- list-size input-length)]
     (take list-size
           (drop position (cycle (concat (reverse (take input-length dropped-cycled))
                                         (take non-reversed-size (drop input-length dropped-cycled)))))))))

(defn process-input
  "Determines the new list, position, and skip size given a new length."
  [data input-length]
  (do
    (println (data :list))
    (println (data :position))
    (println (data :skip-size))
    (-> data
       (assoc :list
              (new-list (data :list) (data :position) input-length))
       (assoc :position
              (+ (data :position) (data :skip-size)))
       (assoc :skip-size
              (inc (data :skip-size))))))

(defn knot-hash
  "Gets the final list from processing all the input lengths."
  [input-lengths size]
  ((reduce process-input {:list (range size)
                          :position 0
                          :skip-size 0} input-lengths) :list))


(defn first-two-product
  "Calculates the product of the first two elements of the calculated knot hash."
  [input-lengths size]
  (apply * (take 2 (knot-hash input-lengths size))))



; position 0
; position
