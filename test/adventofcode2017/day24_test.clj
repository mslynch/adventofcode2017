(ns adventofcode2017.day24-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day24 :refer :all]))

(def components
  (parse-input (line-seq (clojure.java.io/reader "resources/day24-test.txt"))))

; excluding bridges that leave out viable components
; components are flipped to show connections
(deftest all-bridges-test
  (testing "All bridges are computed, excluding sub-bridges."
    (is (= (set (all-bridges components))
           #{[[0 1] [1 10] [10 9]]
             [[0 2] [2 3] [3 4]]
             [[0 2] [2 3] [3 5]]
             [[0 2] [2 2] [2 3] [3 4]]
             [[0 2] [2 2] [2 3] [3 5]]}))))

(deftest strongest-bridge-test
  (testing "The strongest bridge is found."
    (is (= (best-bridge bridge-strength (all-bridges components))
           [[0 1] [1 10] [10 9]]))))

(deftest bridge-strength-test
  (testing "The strength of bridges is computed correctly."
    (is (= (bridge-strength [[0 1] [1 10] [10 9]])
           31))))