(ns adventofcode2017.day7
  (:require [clojure.string :as str]))

; (defn nested-keys-helper
;   [m names name]
;   (do
;     ; (println "-----------")
;     ; (clojure.pprint/pprint m)
;     ; (println (str "names: " names))
;     ; (println (str "name: " name))
;     (if (contains? m name)
;      names
;      (let [map-map (filter (fn [[k v]] (map? v)) m)]
;        (do
;          ; (print "map-map: ")
;          ; (clojure.pprint/pprint map-map)
;          (if (empty? map-map)
;           nil
;           (->> map-map
;                (map (fn [[k v]] (nested-keys-helper v (conj names k) k)))
;                (filter #(not (nil? %)))
;                (first))))))))

; (defn nested-keys
;   [programs program]
;   (do
;     ; (println "@@@@@@@@@@")
;     (conj (nested-keys-helper programs [] program) program)))

; (defn associated-map
;   [programs program]
;   (or (get-in programs (nested-keys programs program))
;       {}))



; (defn associated-map
;   [m k]
;   (some k (tree-seq (constantly true) vals m)))

; (defn fancy-assoc-in
;   "Associates the program in programs with a list of associated programs."
;   [programs program associated]
;   (do
;     ; (println program)
;     ; (println associated)
;     ; (clojure.pprint/pprint programs)
;     (let [keys (nested-keys programs program)]
;      (assoc-in programs keys (zipmap associated
;                                      (map #(associated-map programs %) associated))))))

; https://stackoverflow.com/questions/28965549/clojure-search-replace-values-in-a-dynamic-nested-map-seq
(defn assoc-walk
  "Associates program in programs with associated."
  [programs program associated]
  (let [new-programs
        (clojure.walk/prewalk
         (fn [m]
           (if (and (map? m) (contains? m program))
             (assoc m program associated)
             m))
         programs)]
    (when (not= programs new-programs) new-programs)))

(defn dissoc-walk
  "Dissociates program by walking recursively."
  [programs program]
  (let [new-programs
        (clojure.walk/prewalk
         (fn [m]
           (if (and (map? m) (contains? m program))
             (dissoc m program)
             m))
         programs)]
    (when (not= programs new-programs) new-programs)))


(defn recursive-dissoc
  "Associates program in programs with associated."
  [programs program]
  (if (map? programs)
    (reduce-kv #(if (= program %2)
                 %1
                 (assoc %1 %2 (recursive-dissoc %3 program)))
              {}
              programs)
    programs))


;https://stackoverflow.com/questions/28091305/find-value-of-specific-key-in-nested-map
(defn find-nested
  "Finds nested key k in map m."
  [m k]
  (->> (tree-seq (constantly true) vals m)
       (filter map?)
       (some #(get % k))))

(defn associated-map
  [programs associated]
  (do
    ; (println (zipmap associated
    ;        (map #(find-nested programs %) associated)))
    (zipmap associated
           (map #(find-nested programs %) associated))))

; gotta dissociate everything in associated
(defn add-to-maps
  [[programs weights] [program weight & associated]]


    [(if (not (nil? associated))
       (do
         ; (println "**************")
         ; (println associated)
         ; (clojure.pprint/pprint programs)
        (let [associated-to-map (associated-map programs associated)]
          (if-let [walked (assoc-walk (dissoc-walk programs program)
                                      program
                                      associated-to-map)]
            walked
            (assoc programs
                   program
                   associated-to-map))))
      programs)
    (assoc weights program weight)])


(defn split-line
  [line]
  (str/split (apply str (remove #(contains? #{\( \) \- \> \,} %) line))  #"\s+"))

(defn to-map
  [coll]
  (reduce add-to-maps [{} {}] (map split-line coll)))

(defn bottom-program
  "Generates a map from a list of programs and returns the root program."
  [coll]
  (first (keys (first (to-map coll)))))


(def lines (line-seq (clojure.java.io/reader "resources/day7-test1.txt")))
; (def mappy (to-map lines))
; (clojure.pprint/pprint (first mappy))
