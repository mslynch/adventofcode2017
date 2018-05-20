(ns adventofcode2017.day10
  (:require [clojure.string :as str]))

(defn get-new-list
  "The new list, given the current position and input length."
  [old-list position input]
  (let [list-size (count old-list)
        modded-position (mod position list-size)
        dropped-cycled (drop modded-position (cycle old-list))
        non-reversed-size (- list-size input)]
    (take list-size
          (drop (- list-size (mod position list-size))
                (cycle (concat (reverse (take input dropped-cycled))
                               (take non-reversed-size (drop input dropped-cycled))))))))

(defn process-input
  "Determines the new list, position, and skip size given a new length."
  [data input-length]
  (-> data
      (assoc :list
             (get-new-list (data :list) (data :position) input-length))
      (assoc :position
             (+ (data :position) (data :skip-size) input-length))
      (assoc :skip-size
             (inc (data :skip-size)))))

(defn knot-hash
  "Gets the final list from processing all the input lengths."
  [size input-lengths]
  ((reduce process-input {:list (range size)
                          :position 0
                          :skip-size 0} input-lengths) :list))

(defn first-two-product
  "Calculates the product of the first two elements of the calculated knot hash."
  [input-lengths size]
  (apply * (take 2 (knot-hash size input-lengths))))

(def size 256)
(def repetitions 64)
(def partitions 16)

(defn full-knot-hash
  "Calculates the full knot hash using hexadecimal and XOR."
  [input-lengths]
  (->> (concat (map int input-lengths) '(17 31 73 47 23))
       (repeat repetitions)
       (apply concat)
       (knot-hash size)
       (partition partitions)
       (map (partial apply bit-xor))
       (map (partial format "%02x"))
       (apply str)))
