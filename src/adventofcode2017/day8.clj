(ns adventofcode2017.day8
  (:require [clojure.string :as str]))

(defn line-to-instruction
  "Transforms a line into an instruction."
  [line]
  (let [[register
         operator
         amount
         _
         cond-register
         cond-comparator
         cond-amount] (str/split line #"\s")]
    [register
     (if (= operator "inc") + -)
     (Integer/parseInt amount)
     cond-register
     (cond
       (= cond-comparator "==") =
       (= cond-comparator "!=") not=
       (= cond-comparator ">") >
       (= cond-comparator "<") <
       (= cond-comparator ">=") >=
       :else <=)
     (Integer/parseInt cond-amount)]))

(defn apply-instruction
  "Applies the given instruction to the map."
  [m [register operator amount cond-register cond-comparator cond-amount]]
  (if (cond-comparator (or (m cond-register) 0) cond-amount)
    (assoc m register
           (operator (or (m register) 0) amount))
    m))

(defn largest-register-value
  "Determines the largest register value after a list of instructions."
  [lines]
  (->> lines
       (map line-to-instruction)
       (reduce apply-instruction {})
       (vals)
       (apply max)))

(defn largest-register-value-ever
  "Determines the largest register value ever held from processing a list of instructions."
  [lines]
  (->> lines
       (map line-to-instruction)
       (reductions apply-instruction {})
       (mapcat vals)
       (apply max)))
