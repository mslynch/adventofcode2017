(ns adventofcode2017.day25
  (:require [clojure.string :as str]))

(defn associate-by
  "From https://www.reddit.com/r/Clojure/comments/8iw3b8/plain_groupby_for_unique_values/dyv1wqg/"
  [f coll]
  (zipmap (map f coll) coll))

(defn text-to-condition
  "Converts condition to instruction condition data."
  [condition]
  (let [[value-line write-line direction-line next-state-line] condition]
    {:value (Integer/parseInt (last value-line))
     :write (Integer/parseInt (last write-line))
     :direction (last direction-line)
     :next-state (last next-state-line)}))

(defn text-to-instruction
  "Converts text to instructions."
  [instruction-text]
  (let [[[_in _state state] & conditions] instruction-text]
    {:state state
     :conditions (associate-by :value
                               (map text-to-condition (partition 4 conditions)))}))

(defn preprocess
  "Splits input into words and removes extraneous characters."
  [input]
  (->> input
       (map #(str/join (remove (fn [c] (contains? #{\- \: \.} c)) (str/trim %))))
       (map #(str/split % #" "))))

(defn parse-input
  "Parses the input into instructions."
  [input]
  (let [[[_begin _in _state initial-state]
         [_perform _a _diagnostic _checksum _after steps _steps]
         _blank
         & remaining-input]
        (preprocess input)]
    {:state        initial-state
     :steps        (Integer/parseInt steps)
     :instructions (associate-by :state
                                 (map text-to-instruction
                                      (partition 9 (remove #(= [""] %) remaining-input))))}))

(defn calculate-new-position
  "Calculates the new position, given direction."
  [position direction]
  (if (= direction "left")
    (dec position)
    (inc position)))

(defn calculate-tape-vals
  "Determines the new values for the tape."
  [[tape-lt-zero tape-ge-zero] position write-val]
  (if (neg? position)
    [(assoc tape-lt-zero (dec (unchecked-negate-int position)) write-val)
     tape-ge-zero]
    [tape-lt-zero
     (assoc tape-ge-zero position write-val)]))

(defn current-value
  "Gets the current value from the tape."
  [[tape-lt-zero tape-ge-zero] position]
  (if (neg? position)
    (get tape-lt-zero (dec (unchecked-negate-int position)) 0)
    (get tape-ge-zero position 0)))

(defn process-instruction
  "Processes an instruction. Data contains :position, :tape, :state, :instructions."
  [data]
  (let [position     (data :position)
        tape         (data :tape)
        instruction  (get-in data [:instructions
                                   (data :state)
                                   :conditions
                                   (current-value tape position)])]
    {:position     (calculate-new-position position (instruction :direction))
     :tape         (calculate-tape-vals tape position (instruction :write))
     :state        (instruction :next-state)
     :instructions (data :instructions)}))

(defn run-turing
  "Runs the Turing machine."
  [input]
  (let [parsed-instructions (parse-input input)]
    (nth (iterate process-instruction
                  {:position     0
                   :tape         [[] []]
                   :state        (parsed-instructions :state)
                   :instructions (parsed-instructions :instructions)})
         (parsed-instructions :steps))))

(defn checksum
  "Calculates the checksum of the tape."
  [tape]
  (reduce + (apply concat tape)))

(defn turing-checksum
  "Runs the Turing machine and calculates the checksum."
  [input]
  (checksum ((run-turing input) :tape)))

(def input (line-seq (clojure.java.io/reader "resources/day25-test.txt")))