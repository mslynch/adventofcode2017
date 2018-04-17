(ns adventofcode2017.day05
  (:require [clojure.string :as str]))

(defn next-state
  "The next state of the maze."
  [state position offset-changer]
  (assoc state position (offset-changer (state position))))

(defn fancy-offset
  "Increment if the value is three or more; else decrement."
  [value]
  (if (>= value 3) (dec value) (inc value)))

(defn maze-states
  "Returns true if there are no duplicate words."
  [state position offset-changer]
  (when (< position (count state))
    (lazy-seq (cons state (maze-states
                           (next-state state position offset-changer)
                           (+ (state position) position)
                           offset-changer)))))

(defn steps-to-exit
  "The number of steps required to exit the maze, with a given offset changing strategy."
  [initial-state offset-changer]
  (count (maze-states initial-state 0 offset-changer)))

(defn maze-str
  "Determines steps to exit on an input string separated by \n."
  [input offset-changer]
  (steps-to-exit (mapv #(Integer/parseInt %) (str/split input #"\n"))
                 offset-changer))
