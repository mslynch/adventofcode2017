(ns adventofcode2017.day19
  (:require [clojure.string :as str]
            [clojure.core.async :as async]))

(defn move-direction
  "The next position after moving in a given direction."
  [direction [row col]]
  (condp = direction
    :up    [(dec row) col]
    :down  [(inc row) col]
    :left  [row (dec col)]
    :right [row (inc col)]))

(defn opposite-direction
  "The opposite of the past direction."
  [direction]
  (condp = direction
    :up :down
    :down :up
    :left :right
    :right :left))

(defn bar-char?
  "Whether a character is a | or - (for the path)."
  [c]
  (or (= \| c)
      (= \- c)))

(defn path-char?
  "Whether a character is a capital letter or a bar (for the path)."
  [c]
  (or (bar-char? c)
      (re-matches #"[A-Z]" (str c))))

(defn turn
  "Returns the position and direction after a turn."
  [diagram direction [row col]]
  (let [[new-row new-col]
        (->> [[row (dec col)]
              [row (inc col)]
              [(dec row) col]
              [(inc row) col]]
             (filter (fn [[r c]]
                       (path-char? (get-in diagram [r c]))))
             (remove (fn [[r c]]
                       (= (move-direction (opposite-direction direction) [row col])
                          [r c])))
             first)]
    {:position [new-row new-col]
     :direction (cond
                  (< new-row row) :up
                  (> new-row row) :down
                  (< new-col col) :left
                  (> new-col col) :right)}))

(defn next-state
  "The next state (position, direction, and output chars.)"
  [diagram state]
  (update
   (let [[row col]    (state :position)
         direction    (state :direction)
         chars        (state :chars)
         diagram-char (get-in diagram [row col])]
     (cond
       (bar-char? diagram-char)
       (assoc state :position (move-direction direction [row col]))
       (= \+ diagram-char)
       (merge state (turn diagram direction [row col]))
       (re-matches #"[A-Z]" (str diagram-char))
       (-> state
           (assoc :position (move-direction direction [row col]))
           (assoc :chars (conj chars diagram-char)))
       (= \space diagram-char)
       (assoc state :direction :stopped)))
   :steps
   inc))

(defn walk-path
  "Walks the input path and returns data."
  [input]
  (let [diagram (mapv vec input)]
    (loop [state {:position  [0 (.indexOf (first diagram) \|)]
                  :direction :down
                  :chars     []
                  :steps     -1}]
      (if (= :stopped (state :direction))
        state
        (recur (next-state diagram state))))))

(defn path-letters
  "The letters found by following the path."
  [input]
  (str/join ((walk-path input) :chars)))

(defn path-steps
  "The number of steps taken by walking the path."
  [input]
  ((walk-path input) :steps))
