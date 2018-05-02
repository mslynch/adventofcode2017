(ns adventofcode2017.day07
  (:require [clojure.string :as str]))

(defn split-line
  "Splits each line and removes unnecessary characters."
  [line]
  (str/split (apply str (remove #(contains? #{\( \) \- \> \,} %) line))  #"\s+"))

(defn find-parent
  "Finds the parent of program from program-infos."
  [program-infos program]
  (->> program-infos
       (filter (fn [[k v]] (some #(= % program) (v :children))))
       keys
       first))

(defn add-to-programs
  "Adds a program and its info to a map."
  [programs [program weight & children]]
  (assoc programs
         program
         {:weight (Integer/parseInt weight) :children children}))

(defn populate-map
  "Populates a map with programs, (key is program, value is weight and children)."
  [coll]
  (reduce add-to-programs {} (map split-line coll)))

(defn assoc-with-parent
  "Adds a program in program-infos with its parent."
  [program-infos program]
  (assoc-in program-infos [program :parent] (find-parent program-infos program)))

(defn get-parent-map
  "Returns program infos with an added :parent."
  [program-infos]
  (reduce assoc-with-parent program-infos (keys program-infos)))

(defn get-root
  "Gets the root program from the map of program info."
  [program-infos]
  (first (keys (filter (fn [[k v]] (nil? (v :parent))) program-infos))))

(defn get-bottom-program
  "Gets the root program from a list of text lines."
  [lines]
  (get-root (get-parent-map (populate-map lines))))

(defn sum-total-weights
  "The sum of the root program's weight and programs' total weights."
  [program-infos root programs]
  (reduce +
          ((program-infos root) :weight)
          (map #((program-infos %) :total-weight) programs)))

(defn get-weighted-map
  "The map of program infos with weights."
  [program-infos root]
  (let [info (program-infos root)
        children (info :children)]
    (if children
      (let [recursed-map (reduce get-weighted-map program-infos children)]
        (assoc recursed-map
               root
               (merge info
                      {:total-weight (sum-total-weights recursed-map root children)})))
      (assoc program-infos root (merge info {:total-weight (info :weight)})))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;; part two below ;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-weird-program-data
  "If one of the children has an unbalanced weight, returns the child and and weights
   needed to calculate its correct weight. If balanced, returns nil."
  [program-infos children]
  (let [by-single-program-count
        (group-by #(= 1 (count %))
                  (vals (group-by #((program-infos %) :total-weight) children)))
        [[weird-program]] (by-single-program-count true)
        [[normal-program & _]] (by-single-program-count false)]
    (if weird-program
      {:program weird-program
       :right-total ((program-infos normal-program) :total-weight)
       :wrong-total ((program-infos weird-program) :total-weight)
       :wrong-weight ((program-infos weird-program) :weight)}
      nil)))

(defn get-wrong-weight-program
  "Recurses through the tree to find the unbalanced program. Returns data from
   get-weird-program-data."
  [program-infos root]
  (loop [program-data {:program root}]
    (if-let [children ((program-infos (program-data :program)) :children)]
      (if-let [weird-program (get-weird-program-data program-infos children)]
        (recur weird-program)
        program-data)
      program-data)))

(defn calculate-correct-weight
  "Calculates the correct weight given the wrong weight, wrong total, and right
   total."
  [weights]
  (+ (weights :wrong-weight) (- (weights :right-total)
                                (weights :wrong-total))))

(defn get-correct-weight
  "Gets the corrected weight from the input."
  [lines]
  (let [parented (get-parent-map (populate-map lines))
        root (get-root parented)
        weighted (get-weighted-map parented root)]
    (calculate-correct-weight (get-wrong-weight-program weighted root))))
