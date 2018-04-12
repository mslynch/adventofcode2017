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
         [weight children]))

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



; (def lines (line-seq (clojure.java.io/reader "resources/day7-test1.txt")))
; (def mappy (populate-map lines))
; (def children (all-children (vals mappy)))
; (clojure.pprint/pprint mappy)
