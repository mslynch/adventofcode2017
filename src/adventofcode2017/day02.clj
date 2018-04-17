(ns adventofcode2017.day02
  (:require [clojure.string :as str]))

(defn parse-int
  [a]
  (Integer/parseInt a))

(defn to-list
  "Converts a string input into a list of lists."
  [input]
  (->> (str/split input #"\n")
       (map #(str/split % #"\s+"))
       (map #(map parse-int %))))

(defn difference
  "The difference between the maximum and minimum values of a row."
  [row]
  (- (apply max row)
     (apply min row)))

(defn even-division
  "Calculates the quotient of the two evenly divisible values in the row."
  [row]
  (first (for [x row
               y row
               :let [z (/ x y)]
               :when (and (zero? (mod x y))
                          (not= x y))]
           z)))

(defn checksum
  "Calculates the checksum with a given per-row function."
  [input row-fn]
  (reduce + (map row-fn (to-list input))))
