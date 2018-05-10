(ns adventofcode2017.day12
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn get-connected-to
  "Given all programs and their associations, gets the programs connected to program."
  [all-programs program]
  ((fn add-connected
     [connected program]
     (if-let [connected-programs (remove #(contains? connected %) (all-programs program))]
       (reduce add-connected (conj connected program) connected-programs)
       (conj connected program)))
   #{}
   program))

(defn split-line
  "Removes unnecessary characters and splits a line by whitespace."
  [line]
  (str/split (apply str (remove #(contains? #{\< \- \> \,} %) line))  #"\s+"))

(defn programs-to-map
  "Converts a list of lists of programs to a map."
  [programs]
  (reduce (fn [acc-map [program & others]]
            (assoc acc-map program others))
          {}
          programs))

(defn connected-to-zero
  "Gets all programs connected to program 0."
  [lines]
  (get-connected-to (programs-to-map (map split-line lines)) "0"))

(defn connected-to-zero-count
  "Gets the count of programs in program 0's group."
  [lines]
  (count (connected-to-zero lines)))

(defn program-group-count
  "Gets the number of groups from the input."
  [lines]
  (count (connected-to-zero lines)))
