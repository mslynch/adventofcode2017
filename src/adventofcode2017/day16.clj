(ns adventofcode2017.day16
  (:require [clojure.string :as str]))

(defn spin
  "Moves n programs from the back to the front, maintaining order."
  [programs n]
  (vec (take (count programs) (concat (take-last n programs) programs))))

(defn exchange
  "Swaps programs at positions a and b."
  [programs a b]
  (-> programs
      (assoc a (programs b))
      (assoc b (programs a))))

(defn partner
  "Swaps the programs a and b."
  [programs a b]
  (-> programs
      (assoc (.indexOf programs a) b)
      (assoc (.indexOf programs b) a)))

(defn apply-move
  "Applies a move to the programs."
  [programs instruction]
  (let [move (first instruction)]
    (cond
      (= move \s) (spin programs
                        (Integer/parseInt (str/join (rest instruction))))
      (= move \x) (let [[a b] (str/split (str/join (rest instruction)) #"\/")]
                    (exchange programs
                              (Integer/parseInt a)
                              (Integer/parseInt b)))
      (= move \p) (let [[a b] (str/split (str/join (rest instruction)) #"\/")]
                    (partner programs
                             (first a)
                             (first b))))))

(defn dance
  "Dances using the given programs and input instructions."
  [programs input]
  (reduce apply-move programs (str/split input #",")))

(defn cycle-after
  "Detects the number of dances needed to cycle."
  [programs input]
  (loop
   [count 0
    programs programs
    seen #{}]
    (if (contains? seen programs)
      count
      (recur
       (inc count)
       (dance programs input)
       (conj seen programs)))))

(defn dance-n
  "Does the input's dance n times."
  [programs input n]
  (nth (iterate #(dance % input)
                programs)
       (mod n (cycle-after programs input))))
