(ns adventofcode2017.day13
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn caught?
  "Whether the firewall traverser would get caught at the given time and range."
  [[time range]]
  (zero? (mod time (- (* range 2) 2))))

(defn get-severity
  "Determines the severity of getting caught at a depth and range."
  [[depth range]]
  (if (zero? (mod depth (- (* range 2) 2)))
    (* depth range)
    0))

(defn total-severity
  "Gets the total severity of inputs."
  [inputs]
  (reduce + (map get-severity inputs)))

(defn to-depth-range
  "Removes unnecessary characters and splits a line by whitespace."
  [line]
  (map #(Integer/parseInt %)
       (str/split (apply str (remove (partial = \:) line))
                  #"\s+")))

(defn next-delay-scanners
  [scanners step]
  (lazy-seq (map (fn [[depth range]]
                   [(inc depth) range])
                 scanners)))

(defn total-severity-str
  "Calculates the total severity for a sequence of input strings."
  [lines]
  (total-severity (map to-depth-range lines)))

(defn next-delay
  "The next delay to try, given the previously tried delays."
  [tried-delays]
  (first (filter #(every? (fn [x] zero? (/ % x)) tried-delays)
                 (iterate inc (inc (max tried-delays))))))

(defn shortest-delay
  "Gets the shortest delay required to pass through the scanners undetected."
  [inputs]
  (first
   (for [wait-time (iterate inc 1)
         :let [scanners (map (fn [[depth range]]
                               [(+ depth wait-time) range])
                             inputs)]
         :when (not-any? caught? scanners)]
     wait-time)))

(defn shortest-delay-str
  "Finds the shortest delay for a sequence of input strings."
  [lines]
  (shortest-delay (map to-depth-range lines)))
