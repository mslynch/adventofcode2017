(ns adventofcode2017.day14
  (:require [clojure.string :as str]
            [adventofcode2017.day10 :refer [full-knot-hash]]))

(defn prehash-rows
  "Returns a list containing the rows to apply knot hash on."
  [k num-rows]
  (map #(str k \- %) (range num-rows)))

(def num-rows 128)

(defn hex-to-binary
  "Converts a hexadecimal string to a binary string."
  [hex]
  (format "%128s" (.toString (BigInteger. hex 16) 2)))

(defn binary-to-coords
  "Converts a list of binary digits to the set of '1' value coorinates."
  [binary]
  (let [binary-vec (mapv vec binary)]
    (set (for [row (range num-rows)
               col (range num-rows)
               :when (= \1 (get-in binary-vec [row col]))]
           [row col]))))

(defn hash-to-coords
  "How many squares are 1 after converting hashed rows to binary."
  [input]
  (->> (prehash-rows input num-rows)
       (map full-knot-hash)
       (map hex-to-binary)
       binary-to-coords))

(defn neighbors
  "Gets the squares surrounding the square at [row col]."
  [row col]
  [[(dec row) col]
   [(inc row) col]
   [row (dec col)]
   [row (inc col)]])

(defn region-count-helper
  "A depth-first search to eliminate a region from coords."
  [coords [row col]]
  (if (contains? coords [row col])
    (reduce region-count-helper
            (disj coords [row col])
            (neighbors row col))
    coords))

(defn region-count
  "If [row col] is in a region, zeros the region and increments :region-count."
  [coords]
  (loop
   [coords coords
    [row col] (first coords)
    count 0]
    (let [new-coords (region-count-helper coords [row col])]
      (if (empty? new-coords)
        (inc count)
        (recur new-coords (first new-coords) (inc count))))))
