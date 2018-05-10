(ns adventofcode2017.core
  (:gen-class)
  (:require [adventofcode2017.day01 :refer :all]
            [adventofcode2017.day02 :refer :all]
            [adventofcode2017.day03 :refer :all]
            [adventofcode2017.day04 :refer :all]
            [adventofcode2017.day05 :refer :all]
            [adventofcode2017.day06 :refer :all]
            [adventofcode2017.day07 :refer :all]
            [adventofcode2017.day08 :refer :all]
            [adventofcode2017.day09 :refer :all]
            [adventofcode2017.day10 :refer :all]
            [adventofcode2017.day11 :refer :all]
            [adventofcode2017.day12 :refer [connected-to-zero-count]]
            [clojure.string :as str]))

(defn day-01 []
  (let [input (butlast (slurp "resources/day01.txt"))]
    (do
      (println "Day 1: Inverse Captcha")
      (println (str "part 1: " (inverse-captcha input)))
      (println (str "part 2: " (inverse-captcha-2 input))))))

(defn day-02 []
  (let [input (slurp "resources/day02.txt")]
    (do
      (println "Day 2: Corruption Checksum")
      (println (str "part 1: " (checksum input difference)))
      (println (str "part 2: " (checksum input even-division))))))

(defn day-03 []
  (do
    (println "Day 3: Spiral Memory")
    (println (str "part 1: " (spiral 361527)))
    (println (str "part 2: " (first-larger-than 361527)))))

(defn day-04 []
  (let [input (slurp "resources/day04.txt")]
    (do
      (println "Day 4: High-Entropy Passphrases")
      (println (str "part 1: " (num-valid no-dups? input)))
      (println (str "part 2: " (num-valid no-anagrams? input))))))

(defn day-05 []
  (let [input (slurp "resources/day05.txt")]
    (do
      (println "Day 5: A Maze of Twisty Trampolines, All Alike")
      (println (str "part 1: " (maze-str input inc)))
      (println (str "part 2: " (maze-str input fancy-offset))))))

(defn day-06 []
  (do
    (println "Day 6: Memory Reallocation")
    (println (str "part 1: " (steps-to-cycle [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))
    (println (str "part 2: " (cycle-size [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))))

(defn day-07 []
  (let [input (line-seq (clojure.java.io/reader "resources/day07.txt"))]
    (do
      (println "Day 7: Recursive Circus")
      (println (str "part 1: " (get-bottom-program input)))
      (println (str "part 2: " (get-correct-weight input))))))


(defn day-08 []
  (let [input (line-seq (clojure.java.io/reader "resources/day08.txt"))]
    (do
      (println "Day 8: I Heard You Like Registers")
      (println (str "part 1: " (largest-register-value input)))
      (println (str "part 2: " (largest-register-value-ever input))))))

(defn day-09 []
  (let [input (slurp "resources/day09.txt")
        processed (process-stream input)]
    (do
      (println "Day 9: Stream Processing")
      (println (str "part 1: " (first processed)))
      (println (str "part 2: " (second processed))))))

(defn day-10 []
  (let [input-str "34,88,2,222,254,93,150,0,199,255,39,32,137,136,1,167"
        input-list (map #(Integer/parseInt %) (str/split input-str #","))]
    (do
      (println "Day 10: Knot Hash")
      (println (str "part 1: " (first-two-product input-list 256)))
      (println (str "part 2: " (full-knot-hash input-str 256 64 16))))))

(defn day-11 []
  (let [input (first (line-seq (clojure.java.io/reader "resources/day11.txt")))]
    (do
      (println "Day 11: Hex Ed")
      (println (str "part 1: " (get-steps-away input)))
      (println (str "part 2: " (max-steps-away input))))))

(defn day-12 []
  (let [input (line-seq (clojure.java.io/reader "resources/day12.txt"))]
    (do
      (println "Day 12: Digital Plumber")
      (println (str "part 1: " (connected-to-zero-count input)))
      (println (str "part 2: " (connected-to-zero-count input))))))

(defn -main
  "Runs all days."
  [& args]
  (do (println "Advent of Code 2017 solutions")
    (day-01)
    (day-02)
    (day-03)
    (day-04)
    (day-05)
    (day-06)
    (day-07)
    (day-08)
    (day-09)
    (day-10)
    (day-11)
    (day-12)
))
