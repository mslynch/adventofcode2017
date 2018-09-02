(ns adventofcode2017.day25-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day25 :refer :all]))
; (def input (line-seq (clojure.java.io/reader "resources/day25-test.txt")))
(def parsed-instructions (parse-input (line-seq (clojure.java.io/reader "resources/day25-test.txt"))))

(deftest correct-tape-states
  "Test that tape states are correct."
  (is (= (map :tape
              (take 7 (iterate process-instruction
                               {:position     0
                                :tape         [[] []]
                                :state        (parsed-instructions :state)
                                :instructions (parsed-instructions :instructions)})))
         [[[] []]
          [[] [1]]
          [[] [1 1]]
          [[] [0 1]]
          [[1] [0 1]]
          [[1 1] [0 1]]
          [[1 1] [0 1]]])))

(deftest current-value-test-1
  "Testing getting the current value from position 0."
  (is (= (current-value [[2 3] [5 6 7]] 0)
         5)))

(deftest current-value-test-2
  "Testing getting the current value from position 2."
  (is (= (current-value [[2 3] [5 6 7]] 2)
         7)))

(deftest current-value-test-3
  "Testing getting the current value from position -1."
  (is (= (current-value [[2 3] [5 6 7]] -1)
         2)))

(deftest current-value-test-4
  "Testing getting the current value from position -2."
  (is (= (current-value [[2 3] [5 6 7]] -2)
         3)))

(deftest checksum-test
  "Testing that the checksum is calculated properly."
  (is (= (checksum [[1 1] [0 1]])
         3)))