(ns adventofcode2017.day11
  (:require [clojure.string :as str]))

(defn cube-distance
  "Calculates Manhattan distance for cube grid coordinates."
  [[x y z]]
  (/ (+ (Math/abs x)
        (Math/abs y)
        (Math/abs z)) 2))

(defn new-coord
  "Calculates the new coordinate given the current coordinate and position."
  [[x y z] direction]
  (cond
    (= direction "n")  [x (inc y) (dec z)]
    (= direction "ne") [(inc x) y (dec z)]
    (= direction "se") [(inc x) (dec y) z]
    (= direction "s")  [x (dec y) (inc z)]
    (= direction "sw") [(dec x) y (inc z)]
    (= direction "nw") [(dec x) (inc y) z]))

(defn get-steps-away
  "Gets the steps needed to travel directly to the end point of the path from input."
  [input]
  (cube-distance (reduce new-coord [0 0 0] (str/split input #","))))

(defn max-steps-away
  "Gets the maximum number of steps away the input path is from the origin."
  [input]
  (->> (str/split input #",")
       (reductions new-coord [0 0 0])
       (map cube-distance)
       (apply max)))
