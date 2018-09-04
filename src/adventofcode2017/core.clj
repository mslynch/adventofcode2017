(ns adventofcode2017.core
  (:gen-class)
  (:require
   [clojure.tools.cli :refer [parse-opts]]
   [adventofcode2017.day01 :refer [inverse-captcha inverse-captcha-2]]
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
   [adventofcode2017.day20 :refer [closest-to-origin left-after-collisions]]
   [adventofcode2017.day21 :refer [on-after-n-iterations]]
   [adventofcode2017.day22 :refer [count-infections burst burst-evolved]]
   [adventofcode2017.day23 :refer [run-program calculate-h]]
   [adventofcode2017.day24 :refer [best-bridge-strength bridge-strength]]
   [adventofcode2017.day25 :refer [turing-checksum]]
   [clojure.string :as str]))

(defn day-1 [filename]
  (let [input (slurp (or filename "resources/day01.txt"))]
    (println "Day 1: Inverse Captcha")
    (println (str "part 1: " (inverse-captcha input)))
    (println (str "part 2: " (inverse-captcha-2 input)))))

(defn day-2 [filename]
  (let [input (slurp (or filename "resources/day02.txt"))]
    (println "Day 2: Corruption Checksum")
    (println (str "part 1: " (checksum input difference)))
    (println (str "part 2: " (checksum input even-division)))))

(defn day-3 [filename]
  (let [input (Integer/parseInt (slurp (or filename "resources/day03.txt")))]
    (println "Day 3: Spiral Memory")
    (println (str "part 1: " (spiral input)))
    (println (str "part 2: " (first-larger-than input)))))

(defn day-4 [filename]
  (let [input (slurp (or filename "resources/day04.txt"))]
    (println "Day 4: High-Entropy Passphrases")
    (println (str "part 1: " (num-valid no-dups? input)))
    (println (str "part 2: " (num-valid no-anagrams? input)))))

(defn day-5 [filename]
  (let [input (slurp (or filename "resources/day05.txt"))]
    (println "Day 5: A Maze of Twisty Trampolines, All Alike")
    (println (str "part 1: " (maze-str input inc)))
    (println (str "part 2: " (maze-str input fancy-offset)))))

(defn day-6 [filename]
  (let [input (mapv #(Integer/parseInt %)
                    (str/split (slurp (or filename "resources/day06.txt")) #"\t"))]
    (println "Day 6: Memory Reallocation")
    (println (str "part 1: " (steps-to-cycle input)))
    (println (str "part 2: " (cycle-size input)))))

(defn day-7 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day07.txt")))]
    (println "Day 7: Recursive Circus")
    (println (str "part 1: " (get-bottom-program input)))
    (println (str "part 2: " (get-correct-weight input)))))

(defn day-8 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day08.txt")))]
    (println "Day 8: I Heard You Like Registers")
    (println (str "part 1: " (largest-register-value input)))
    (println (str "part 2: " (largest-register-value-ever input)))))

(defn day-9 [filename]
  (let [input (slurp (or filename "resources/day09.txt"))
        processed (process-stream input)]
    (println "Day 9: Stream Processing")
    (println (str "part 1: " (first processed)))
    (println (str "part 2: " (second processed)))))

(defn day-10 [filename]
  (let [input (slurp (or filename "resources/day10.txt"))
        input-list (map #(Integer/parseInt %) (str/split input #","))]
    (println "Day 10: Knot Hash")
    (println (str "part 1: " (first-two-product input-list 256)))
    (println (str "part 2: " (full-knot-hash input)))))

(defn day-11 [filename]
  (let [input (first (line-seq (clojure.java.io/reader (or filename "resources/day11.txt"))))]
    (println "Day 11: Hex Ed")
    (println (str "part 1: " (get-steps-away input)))
    (println (str "part 2: " (max-steps-away input)))))

(defn day-12 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day12.txt")))]
    (println "Day 12: Digital Plumber")
    (println (str "part 1: " (connected-to-zero-count input)))
    (println (str "part 2: " (program-group-count input)))))

(defn day-13 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day13.txt")))]
    (println "Day 13: Packet Scanners")
    (println (str "part 1: " (total-severity-str input)))
    (println (str "part 2: " (shortest-delay-str input)))))

(defn day-14 [filename]
  (let [input (slurp (or filename "resources/day14.txt"))
        coords (hash-to-coords input)]
    (println "Day 14: Disk Defragmentation")
    (println (str "part 1: " (count coords)))
    (println (str "part 2: " (region-count coords)))))

(defn day-15 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day15.txt")))
        [start-a start-b] (map #(Integer/parseInt (last (str/split % #" "))) input)]
    (println "Day 15: Dueling Generators")
    (println (str "part 1: " (judge-count-1 40000000 start-a start-b)))
    (println (str "part 1: " (judge-count-2 5000000 start-a start-b)))))

(defn day-16 [filename]
  (let [programs [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p]
        input (first (line-seq (clojure.java.io/reader (or filename "resources/day16.txt"))))]
    (println "Day 16: Permutation Promenade")
    (println (str "part 1: " (str/join (dance programs input))))
    (println (str "part 2: " (str/join (dance-n programs input 1000000000))))))

(defn day-17 [filename]
  (let [input (Integer/parseInt (slurp (or filename "resources/day17.txt")))]
    (println "Day 17: Spinlock")
    (println (str "part 1: " (value-after (spinlock 2017 input) 2017)))
    (time (println (str "part 2: " (value-after-0 50000000 input))))))

(defn day-18 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day18.txt")))]
    (println "Day 18: Duet")
    (println (str "part 1: " (duet input)))
    (println (str "part 2: " (duet-async input)))))

(defn day-19 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day19.txt")))]
    (println "Day 19: A Series of Tubes")
    (println (str "part 1: " (path-letters input)))
    (println (str "part 2: " (path-steps input)))))

(defn day-20 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day20.txt")))]
    (println "Day 20: Particle Swarm")
    (println (str "part 1: " (closest-to-origin input)))
    (println (str "part 2: " (left-after-collisions input)))))

(defn day-21 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day21.txt")))]
    (println "Day 21: Fractal Art")
    (println (str "part 1: " (on-after-n-iterations input 5)))
    (println (str "part 1: " (on-after-n-iterations input 18)))))

(defn day-22 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day22.txt")))]
    (println "Day 22: Sporifica Virus")
    (println (str "part 1: " (count-infections input 10000 burst)))
    (println (str "part 2: " (count-infections input 10000000 burst-evolved)))))

(defn day-23 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day23.txt")))]
    (println "Day 23: Coprocessor Conflagration")
    (println (str "part 1: " (run-program input)))
    (println (str "part 2: " (calculate-h input)))))

(defn day-24 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day24.txt")))]
    (println "Day 24: Electromagnetic Moat")
    (println (str "part 1: " (best-bridge-strength input bridge-strength)))
    (println (str "part 2: " (best-bridge-strength input count)))))

(defn day-25 [filename]
  (let [input (line-seq (clojure.java.io/reader (or filename "resources/day25.txt")))]
    (println "Day 25: The Halting Problem")
    (println (str "solution: " (turing-checksum input)))))

(def cli-options
  [["-d" "--day DAY" "Day number (required)"
    :parse-fn #(Integer/parseInt %)
    :validate [#(<= 1 % 25) "Must be a number between 1 and 25"]]
   ["-f" "--file FILE" "Input file"
    :default nil]
   ["-h" "--help"]])

(defn usage [options-summary]
  "Returns string of usage instructions."
  (str/join
   \newline
   ["Usage: adventofcode2017 -d DAY [options]"
    ""
    "Options:"
    options-summary]))

(defn help [options-summary]
  "Returns help summary."
  (str/join
   \newline
   ["Runs my solutions to the Advent of Code 2017 problems."
    ""
    (usage options-summary)]))

(defn -main
  [& args]
  (let [parsed (parse-opts args cli-options)
        errors (parsed :errors)
        options (parsed :options)]
    (cond
      (options :help) (println (help (parsed :summary)))
      errors (doseq [error errors] (println error))
      (not (options :day)) (println (usage (parsed :summary)))
      :else ((->> (options :day)
                  (str "adventofcode2017.core/day-")
                  symbol
                  resolve)
             (options :file)))))