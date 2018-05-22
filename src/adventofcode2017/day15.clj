(ns adventofcode2017.day15
  (:require [clojure.string :as str]))

(def factor-a 16807)
(def factor-b 48271)
(def divisor 2147483647)

(defn generate
  "Generates a new value."
  [old-value factor multiple-of]
  (loop
   [old-value old-value]
    (let [new-value (mod (* factor old-value) divisor)]
      (if (zero? (mod new-value multiple-of))
        new-value
        (recur new-value)))))

(defn generator
  "A seq of the numbers from a generator."
  [previous factor multiple-of]
  (let [value (generate previous factor multiple-of)]
    (lazy-seq (cons value (generator value factor multiple-of)))))

(defn bit-mask
  "Zeroes bits above the 16th."
  [number]
  (bit-and 0xffff number))

(defn generated-bits
  "A seq of the last 16 bits of each generated value."
  [iterations start factor multiple-of]
  (map bit-mask (take iterations (generator start factor multiple-of))))

(defn judge-count-1
  "The number of outputs with matching final bits."
  [iterations start-a start-b]
  (count
   (filter identity
           (map =
                (generated-bits iterations start-a factor-a 1)
                (generated-bits iterations start-b factor-b 1)))))

(defn judge-count-2
  "The number of outputs with matching final bits."
  [iterations start-a start-b]
  (count
   (filter identity
           (map =
                (generated-bits iterations start-a factor-a 4)
                (generated-bits iterations start-b factor-b 8)))))
