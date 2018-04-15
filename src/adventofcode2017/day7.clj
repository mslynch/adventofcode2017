(ns adventofcode2017.day7
  (:require [clojure.string :as str]))

(defn split-line
  "Splits each line and removes unnecessary characters."
  [line]
  (str/split (apply str (remove #(contains? #{\( \) \- \> \,} %) line))  #"\s+"))

(defn add-to-programs
  "Adds a program and its info to a map."
  [programs [program weight & children]]
  (assoc programs
         program
         [(Integer/parseInt weight) children]))

(defn populate-map
  "Populates a map with programs, (key is program, value is weight and children)."
  [coll]
  (reduce add-to-programs {} (map split-line coll)))

(defn has-parent?
  "Returns whether the program has a parent in the list of children."
  [program children]
  (some #(= program %) children))

(defn all-children
  "The set of all children in the programs info vals."
  [program-info-vals]
  ; first is weight, second is children
  (reduce into #{} (map second program-info-vals)))

(defn root
  "Gets the root program from the map of program info."
  [program-infos]
  (let [children (all-children (vals program-infos))]
   (first (remove #(children %) (keys program-infos)))))

(defn bottom-program
  "Gets the root program from a list of text lines."
  [lines]
  (root (populate-map lines)))

(defn parent
  "The parent of a program."
  [program-infos program]
  (first (first (filter (fn [[_ [_ children _]]]
                          (some #(= program %) children))
                        program-infos))))

(defn weighted-map
  "The map of program infos with key being [weight children total-weight]."
  [program-infos program]
  (let [[weight children] (program-infos program)]
    (if children
      (let [children-map (reduce #(weighted-map %1 %2) program-infos children)]
        (assoc children-map
               program
               [weight
                children
                (reduce + weight (map #(nth (children-map %) 2) children))]))
      (assoc program-infos program [weight children weight]))))

(defn siblings
  "A list containing the siblings of program."
  [program-infos program]
  (remove #(= program %) (second (program-infos (parent program-infos program)))))

(defn other-weight
  "Gets the normal weight of a program from its siblings."
  [program-infos program]
  (nth (program-infos (first (siblings program-infos program))) 2))

(defn odd-program
  "Gets the odd one out of a program's children."
  [program-infos children]
  (first (first (vals (filter (fn [[k v]] (= (count v) 1))
                              (group-by #(nth (program-infos %) 2) children))))))


(defn correct-weight-recurse
  "Determines the correct number for an unbalanced tree."
  [program-infos]
  (loop [program (root program-infos)]
    (let [[weight children total] (program-infos program)]
      (if (and children (apply not= (map #(nth (program-infos %) 2) children)))
        (recur (odd-program program-infos children))
        (+ weight (- (other-weight program-infos program) total))))))

(defn correct-weight
  "Determines the correct weight of the program that has an incorrect weight."
  [lines]
  (let [populated (populate-map lines)]
    (correct-weight-recurse (weighted-map populated (root populated)))))
