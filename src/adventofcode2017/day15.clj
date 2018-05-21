(ns adventofcode2017.day15
  (:require [clojure.string :as str]))

(def factor-a 16807)
(def factor-b 48271)
(def divisor 2147483647)

(defn generate
  "Generates a new value."
  [old-value factor]
  (mod (* factor old-value) divisor))

(defn generator
  "A seq of the numbers from a generator."
  [previous factor]
  (let [value (generate previous factor)]
    (lazy-seq (cons value (generator value factor)))))

(defn bit-mask
  "Zeroes bits above the 16th."
  [number]
  (bit-and 0xffff number))

(defn generated-bits
  "A seq of the last 16 bits of each generated value."
  [iterations start factor]
  (map bit-mask (take iterations (generator start factor))))

(defn judge-count
  "The number of outputs with matching final bits."
  [iterations start-a start-b]
  (count
   (filter identity
           (map =
                (generated-bits iterations start-a factor-a)
                (generated-bits iterations start-b factor-b)))))
