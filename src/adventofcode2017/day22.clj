(ns adventofcode2017.day22
  (:require [clojure.string :as str]))

(defn turn
  "The new direction after turning left or right."
  [current-direction turn-direction]
  (let [directions [:north :east :south :west]]
    (if (= :left turn-direction)
      (cond
        (= :north current-direction) :west
        (= :west current-direction) :south
        (= :south current-direction) :east
        :else :north)
      (cond
        (= :north current-direction) :east
        (= :west current-direction) :north
        (= :south current-direction) :west
        :else :south))))

(defn move
  "Moves one node forward in the facing direction."
  [[x y] facing]
  (cond
    (= :north facing) [x (inc y)]
    (= :west facing) [(dec x) y]
    (= :south facing) [x (dec y)]
    :else [(inc x) y]))

(defn burst
  "Runs a burst given the current state."
  [state]
  (let [position (state :position)
        infected (state :infected)
        new-direction (if (contains? infected position)
                        (turn (state :direction) :right)
                        (turn (state :direction) :left))
        will-clean? (contains? infected position)
        infection-count (state :infection-count)
        new-position (move (state :position) new-direction)]
    {:direction new-direction
     :position new-position
     :infected (if will-clean?
                 (disj infected position)
                 (conj infected position))
     :infection-count (if will-clean?
                        infection-count
                        (inc infection-count))}))

(defn burst-evolved
  "Runs a burst given the current state using the evolved behavior (part 2)."
  [state]
  (let [position (state :position)
        infected (state :infected)
        weakened (state :weakened)
        flagged (state :flagged)
        node-status (or (first (filter #(contains? (state %) position)
                                       [:infected :weakened :flagged]))
                        :cleaned)
        direction (state :direction)
        new-direction (cond
                        (= node-status :cleaned) (turn direction :left)
                        (= node-status :weakened) direction
                        (= node-status :infected) (turn direction :right)
                        :else (turn (turn direction :left) :left))
        will-infect? (contains? weakened position)
        will-flag? (contains? infected position)
        will-clean? (contains? flagged position)
        infection-count (state :infection-count)
        new-position (move (state :position) new-direction)]
    (merge {:direction new-direction
            :position new-position
            :infection-count (if will-infect?
                               (inc infection-count)
                               infection-count)}
           (cond
             will-infect? {:weakened (disj weakened position)
                           :infected (conj infected position)
                           :flagged flagged}
             will-flag? {:weakened weakened
                         :infected (disj infected position)
                         :flagged (conj flagged position)}
             will-clean? {:weakened weakened
                          :infected infected
                          :flagged (disj flagged position)}
             :else {:weakened (conj weakened position)
                    :infected infected
                    :flagged flagged}))))

(defn infected-row-to-set
  "Adds infected positions from a row."
  [position row infected]
  (loop [[x y] position
         row row
         infected infected]
    (if (empty? row)
      infected
      (recur
       [(inc x) y]
       (rest row)
       (if (= (first row) \#)
         (conj infected [x y])
         infected)))))

(defn infection-grid-to-set
  "Converts an infection grid to a set of infected coordinates."
  [grid]
  (let [size (quot (count grid) 2)]
    (loop [[x y] [(unchecked-negate-int size) size]
           grid grid
           infected #{}]
      (if (empty? grid)
        infected
        (recur
         [x (dec y)]
         (rest grid)
         (infected-row-to-set [x y] (first grid) infected))))))

(defn count-infections
  "The number of infections after n iterations, using a burst function."
  [input n burst-function]
  ((nth (iterate burst-function {:direction :north
                                 :position [0 0]
                                 :infected (infection-grid-to-set input)
                                 :weakened #{}
                                 :flagged #{}
                                 :infection-count 0})
        n) :infection-count))
