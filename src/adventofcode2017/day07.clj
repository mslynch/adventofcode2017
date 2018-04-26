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

(defn children-totals-are
  "Returns the application of f on the children's total weights."
  [program-infos children f]
  (do
    (println children)
    (apply f (map #((program-infos %) :total-weight) children))))

(defn get-odd-child
  "Finds the child with the odd weight from some children."
  [program-infos children]
  (let [[_ [odd-child]]
        (first (filter
                (fn [[k v]] (= 1 (count v)))
                (group-by #((program-infos %) :total-weight)
                          children)))]
    odd-child))

(defn children-pred
  "Returns true if the children aren't equal, but the odd program's children
   are equal."
  [program-infos children]
  (do
    (println "children: " children)
   (and
    (children-totals-are program-infos
                         children
                         not=)
    (children-totals-are program-infos
                         ((program-infos (get-odd-child program-infos children))
                          :children)
                         =))))

(defn get-uneven-parent
  "Returns the program with unequal children."
  [program-infos programs-by-parent]
  (->> programs-by-parent
       (filter (fn [[_ v]] children-pred program-infos v))
       keys
       first))

(defn flash
  [msg val]
    (println (str msg ": " val))
    val)

(defn get-right-and-wrong-totals
  "Gets the right and wrong program."
  [program-infos children-by-total]
  (let [by-single-count (group-by (fn [[_ v]] (= (count v) 1)) children-by-total)
        [[wrong-total [wrong-program]]] (by-single-count true)
        [[right-total _]] (by-single-count false)]
    {:wrong-total wrong-total
     :right-total right-total
     :wrong-weight ((program-infos wrong-program) :weight)}))




(defn leaf-node?
  "Returns whether a program is a leaf node or not."
  [program-infos program]
  (nil? ((program-infos program) :children)))

(defn weird?
  ""
  [programs])

(defn calculate-balanced
  [program-infos program]
  (let [balanced?
        (cond
          (leaf-node? program-infos program) true
          (weird? (map #() ((program-infos program) :children))) false
          :else true)]
    {:program program
     :total-weight ((program-infos :total-weight))
     :balanced? balanced}))


(defn get-relevant-weights
  "Gets the right total, wrong total, and wrong weight from the parent of the bad program."
  [program-infos program]
  (do
    (println (str "program: " program))
    (get-right-and-wrong-totals program-infos
                               (group-by #((program-infos %) :total-weight)
                                         ((program-infos program) :children)))))

(defn calculate-correct-weight
  "Calculates the correct weight given a map of relevant weights."
  [weights]
  (+ (weights :wrong-weight) (- (weights :right-total)
                                (weights :wrong-total))))

(defn get-correct-weight-map
  "Gets the corrected value of the single wrong weight in the map."
  [program-infos]
  (->> program-infos
       keys
       ; (flash "keys")
       (group-by #((program-infos %) :parent))
       ; (flash "grouped")
       (get-uneven-parent program-infos)
       (flash "uneven parent")
       (get-relevant-weights program-infos)
       calculate-correct-weight))


(defn get-correct-weight
  "Gets the corrected weight from the input."
  [lines]
  (let [parented (get-parent-map (populate-map lines))]
    (get-correct-weight-map
     (get-weighted-map parented (get-root parented)))))
