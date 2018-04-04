(ns adventofcode2017.day5
  (:require [clojure.string :as str]))

; (defn next-state)
(defn next-state
  "The next state of the maze."
  [state position]
  (assoc state position (inc (state position))))

(defn maze-states
  "Returns true if there are no duplicate words."
  [state position]
    (let [next-position (+ (nth state position) position)]
      (when (>= next-position (count state))
        (cons state (lazy-seq (maze-states
                               (next-state state position)
                               next-position))))))


(defn steps-to-exit
  [initial-state]
  (dec (count (maze-states initial-state 0))))
