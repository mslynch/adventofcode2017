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

(defn path-char?
  "Whether a character is a | or -."
  [c]
  (or (= \| c)
      (= \- c)))

; (defn )

(defn turn
  "Returns the position and direction after a turn."
  [diagram direction [row col]]
  (let [[new-row new-col]
        (->> [[row (dec col)]
              [row (inc col)]
              [(dec row) col]
              [(inc row) col]]
             (remove (fn [[r c]]
                       = \space (get-in diagram [r c])))
             (filter (fn [[r c]]
                       (path-char? (get-in diagram [r c]))))
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
  (do
    (println (state :position))
    (println (type (state :position)))
    (let [[row col]    (state :position)
          direction    (state :direction)
          chars        (state :chars)
          diagram-char (get-in diagram [row col])]
     (cond
       (path-char? diagram-char)
       (assoc state :position (move-direction direction [row col]))
       (re-matches #"[A-Z]" (str diagram-char))
       (-> state
           (assoc :position (move-direction direction [row col]))
           (assoc :chars (conj chars diagram-char)))
       (= \+ diagram-char)
       (merge state (turn diagram direction [row col]))
       (= \space diagram-char)
       (assoc state :direction :stopped)))))

(defn path-letters
  "The letters found by following the path."
  [input]
  (let [diagram (mapv #(vec %) input)]
    (loop [state {:position  [0 (.indexOf (first diagram) \|)]
                  :direction :down
                  :chars     []}]
      (if (= :stopped (state :direction))
        (str/join (state :chars))
        (do
          (println (state :chars))
          (recur (next-state diagram state)))))))
