(ns adventofcode2017.day9
  (:require [clojure.string :as str]))

;
; (defn score-recurse
;   "Recurses on the remaining characters in a string to build up a score."
;   [score group-count in-garbage]
;   loop )

(defn process-stream
  "Calculates the score of a string."
  [input]
  (loop [input input
         score 0
         non-canceled 0
         group-count 0
         in-garbage false]
    (if (empty? input)
      [score non-canceled]
      (let [char (first input)]
        (if (= (first input) \!)
          (recur (drop 2 input) score non-canceled group-count in-garbage)
          (if in-garbage
            (let [garbage-escape (not= char \>)]
              (recur
                (rest input)
                score
                (if garbage-escape (inc non-canceled) non-canceled)
                group-count
                garbage-escape))
            (cond
              (= char \{) (recur
                            (rest input)
                            score
                            non-canceled
                            (inc group-count)
                            false)
              (= char \}) (recur
                            (rest input)
                            (+ score group-count)
                            non-canceled
                            (dec group-count)
                            false)
              (= char \<) (recur
                            (rest input)
                            score
                            non-canceled
                            group-count
                            true)
              :else (recur
                      (rest input)
                      score
                      non-canceled
                      group-count
                      false))))))))
