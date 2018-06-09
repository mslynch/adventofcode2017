(ns adventofcode2017.core
  (:gen-class)
  (:require [adventofcode2017.day01 :refer [inverse-captcha inverse-captcha-2]]
            [adventofcode2017.day02 :refer [checksum difference even-division]]
            [adventofcode2017.day03 :refer [spiral first-larger-than]]
            [adventofcode2017.day04 :refer [num-valid no-dups? no-anagrams?]]
            [adventofcode2017.day05 :refer [maze-str fancy-offset]]
            [adventofcode2017.day06 :refer [steps-to-cycle cycle-size]]
            [adventofcode2017.day07 :refer [get-bottom-program get-correct-weight]]
            [adventofcode2017.day08 :refer [largest-register-value largest-register-value-ever]]
            [adventofcode2017.day09 :refer [process-stream]]
            [adventofcode2017.day10 :refer [first-two-product full-knot-hash]]
            [adventofcode2017.day11 :refer [get-steps-away max-steps-away]]
            [adventofcode2017.day12 :refer [connected-to-zero-count program-group-count]]
            [adventofcode2017.day13 :refer [total-severity-str shortest-delay-str]]
            [adventofcode2017.day14 :refer [hash-to-coords region-count]]
            [adventofcode2017.day15 :refer [judge-count-1 judge-count-2]]
            [adventofcode2017.day16 :refer [dance dance-n]]
            [adventofcode2017.day17 :refer [spinlock value-after value-after-0]]
            [adventofcode2017.day18 :refer [duet duet-async]]
            [adventofcode2017.day19 :refer [path-letters path-steps]]
            [clojure.string :as str]))

(defn day-01 []
  (let [input (butlast (slurp "resources/day01.txt"))]
    (println "Day 1: Inverse Captcha")
    (println (str "part 1: " (inverse-captcha input)))
    (println (str "part 2: " (inverse-captcha-2 input)))))

(defn day-02 []
  (let [input (slurp "resources/day02.txt")]
    (println "Day 2: Corruption Checksum")
    (println (str "part 1: " (checksum input difference)))
    (println (str "part 2: " (checksum input even-division)))))

(defn day-03 []
  (do
    (println "Day 3: Spiral Memory")
    (println (str "part 1: " (spiral 361527)))
    (println (str "part 2: " (first-larger-than 361527)))))

(defn day-04 []
  (let [input (slurp "resources/day04.txt")]
    (println "Day 4: High-Entropy Passphrases")
    (println (str "part 1: " (num-valid no-dups? input)))
    (println (str "part 2: " (num-valid no-anagrams? input)))))

(defn day-05 []
  (let [input (slurp "resources/day05.txt")]
    (println "Day 5: A Maze of Twisty Trampolines, All Alike")
    (println (str "part 1: " (maze-str input inc)))
    (println (str "part 2: " (maze-str input fancy-offset)))))

(defn day-06 []
  (do
    (println "Day 6: Memory Reallocation")
    (println (str "part 1: " (steps-to-cycle [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))
    (println (str "part 2: " (cycle-size [10 3 15 10 5 15 5 15 9 2 5 8 5 2 3 6])))))

(defn day-07 []
  (let [input (line-seq (clojure.java.io/reader "resources/day07.txt"))]
    (println "Day 7: Recursive Circus")
    (println (str "part 1: " (get-bottom-program input)))
    (println (str "part 2: " (get-correct-weight input)))))

(defn day-08 []
  (let [input (line-seq (clojure.java.io/reader "resources/day08.txt"))]
    (println "Day 8: I Heard You Like Registers")
    (println (str "part 1: " (largest-register-value input)))
    (println (str "part 2: " (largest-register-value-ever input)))))

(defn day-09 []
  (let [input (slurp "resources/day09.txt")
        processed (process-stream input)]
    (println "Day 9: Stream Processing")
    (println (str "part 1: " (first processed)))
    (println (str "part 2: " (second processed)))))

(defn day-10 []
  (let [input-str "34,88,2,222,254,93,150,0,199,255,39,32,137,136,1,167"
        input-list (map #(Integer/parseInt %) (str/split input-str #","))]
    (println "Day 10: Knot Hash")
    (println (str "part 1: " (first-two-product input-list 256)))
    (println (str "part 2: " (full-knot-hash input-str)))))

(defn day-11 []
  (let [input (first (line-seq (clojure.java.io/reader "resources/day11.txt")))]
    (println "Day 11: Hex Ed")
    (println (str "part 1: " (get-steps-away input)))
    (println (str "part 2: " (max-steps-away input)))))

(defn day-12 []
  (let [input (line-seq (clojure.java.io/reader "resources/day12.txt"))]
    (println "Day 12: Digital Plumber")
    (println (str "part 1: " (connected-to-zero-count input)))
    (println (str "part 2: " (program-group-count input)))))

(defn day-13 []
  (let [input (line-seq (clojure.java.io/reader "resources/day13.txt"))]
    (println "Day 13: Packet Scanners")
    (println (str "part 1: " (total-severity-str input)))
    (println (str "part 2: " (shortest-delay-str input)))))

(defn day-14 []
  (let [coords (hash-to-coords "wenycdww")]
    (println "Day 14: Disk Defragmentation")
    (println (str "part 1: " (count coords)))
    (println (str "part 2: " (region-count coords)))))

(defn day-15 []
  (let [start-a 618
        start-b 814]
    (println "Day 15: Dueling Generators")
    (println (str "part 1: " (judge-count-1 40000000 start-a start-b)))
    (println (str "part 1: " (judge-count-2 5000000 start-a start-b)))))

(defn day-16 []
  (let [programs [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p]
        input (first (line-seq (clojure.java.io/reader "resources/day16.txt")))]
    (println "Day 16: Permutation Promenade")
    (println (str "part 1: " (str/join (dance programs input))))
    (println (str "part 2: " (str/join (dance-n programs input 1000000000))))))

(defn day-17 []
  (let [input 356]
    (println "Day 17: Spinlock")
    (println (str "part 1: " (value-after (spinlock 2017 input) 2017)))
    (time (println (str "part 2: " (value-after-0 50000000 input))))))

(defn day-18 []
  (let [input (line-seq (clojure.java.io/reader "resources/day18.txt"))]
    (println "Day 18: Duet")
    (println (str "part 1: " (duet input)))
    (println (str "part 2: " (duet-async input)))))

(defn day-19 []
  (let [input (line-seq (clojure.java.io/reader "resources/day19.txt"))]
    (println "Day 19: A Series of Tubes")
    (println (str "part 1: " (path-letters input)))
    (println (str "part 2: " (path-steps input)))))

(defn -main
  "Run the solutions!"
  [& args]
  (do
    (println "Advent of Code 2017 solutions")
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
    (day-13)
    (day-14)
    (day-15)
    (day-16)
    (day-17)
    (day-18)
    (day-19)))
