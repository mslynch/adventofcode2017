(ns adventofcode2017.core
  (:gen-class)
  (:require [adventofcode2017.day1 :refer :all]
            [adventofcode2017.day2 :refer :all]
            [adventofcode2017.day3 :refer :all]
            [adventofcode2017.day4 :refer :all]
            [adventofcode2017.day5 :refer :all]
            [adventofcode2017.day6 :refer :all]
            [adventofcode2017.day7 :refer :all]
            [adventofcode2017.day8 :refer :all]))

(defn day-1 []
  (let [input (butlast (slurp "resources/day1.txt"))]
    (do
      (println "Day 1: Inverse Captcha")
      (println (str "part 1: " (inverse-captcha input)))
      (println (str "part 2: " (inverse-captcha-2 input))))))

(defn day-2 []
  (let [input (slurp "resources/day2.txt")]
    (do
      (println "Day 2: Corruption Checksum")
      (println (str "part 1: " (checksum input difference)))
      (println (str "part 2: " (checksum input even-division))))))

(defn day-3 []
  (do
    (println "Day 3: Spiral Memory")
    (println (str "part 1: " (spiral 361527)))
    (println (str "part 2: " (first-larger-than 361527)))))

(defn day-4 []
  (let [input (slurp "resources/day4.txt")]
    (do
      (println "Day 4: High-Entropy Passphrases")
      (println (str "part 1: " (num-valid no-dups? input)))
      (println (str "part 2: " (num-valid no-anagrams? input))))))

(defn day-5 []
  (let [input (slurp "resources/day5.txt")]
    (do
      (println "Day 5: A Maze of Twisty Trampolines, All Alike")
      (println (str "part 1: " (maze-str input inc)))
      (println (str "part 2: " (maze-str input fancy-offset))))))

(defn day-6 []
  (do
    (println "Day 6: Memory Reallocation")
    (println (str "part 1: " (steps-to-cycle [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))
    (println (str "part 2: " (cycle-size [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))))

(defn day-7 []
  (let [input (line-seq (clojure.java.io/reader "resources/day7.txt"))]
    (do
      (println "Day 7: Recursive Circus")
      (println (str "part 1: " (bottom-program input)))
      (println (str "part 2: " (correct-weight input))))))


(defn day-8 []
  (let [input (line-seq (clojure.java.io/reader "resources/day8.txt"))]
    (do
      (println "Day 8: I Heard You Like Registers")
      (println (str "part 1: " (largest-register-value input)))
      (println (str "part 2: " (largest-register-value-ever input))))))

(defn -main
  "Runs all days."
  [& args]
  (do (println "Advent of Code 2017 solutions")
    (day-1)
    (day-2)
    (day-3)
    (day-4)
    (day-5)
    (day-6)
    (day-7)
    (day-8)
))
