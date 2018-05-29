(ns adventofcode2017.day17
  (:require [clojure.string :as str]))

(defn insert-at
  "Inserts a value at a position in the buffer."
  [buffer position value]
  (let [[beginning end] (split-at position buffer)]
    (concat beginning [value] end)))

(defn next-position
  "The next position, given the current position, step, and buffer size."
  [position step size]
  (inc (mod (+ position step) size)))

(defn spinlock-insert
  "Inserts a value into the data's buffer."
  [data value]
  (let [position (next-position (data :position)
                                (data :step)
                                (count (data :buffer)))]
    (-> data
        (assoc :buffer
               (insert-at (data :buffer) position value))
        (assoc :position position))))

(defn spinlock-insert-cheap
  "Calculates a step of spinlock data."
  [data value]
  (let [position (next-position (data :position)
                                (data :step)
                                (data :size))
        new-data (-> data
                     (update :size inc)
                     (assoc :position position))]
    (if (= 1 position)
      (assoc new-data :after-0 value)
      new-data)))

(defn spinlock
  "Generates spinlock output."
  [size step]
  ((reduce spinlock-insert
           {:buffer '(0)
            :position 0
            :step step}
           (range 1 (inc size)))
   :buffer))

(defn value-after-0
  "Uses cheap spinlock output to get the value after 0."
  [size step]
  ((reduce spinlock-insert-cheap
           {:size 1
            :position 0
            :step step}
           (range 1 (inc size)))
   :after-0))

(defn value-after
  "Finds the value after a number from a spinlock buffer."
  [buffer x]
  (loop [buffer (cycle buffer)
         position 0
         found-value false]
    (if found-value
      (first buffer)
      (recur (rest buffer) (inc position) (= (first buffer) x)))))
