(ns adventofcode2017.day13-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day13 :refer :all]))

(def input (line-seq (clojure.java.io/reader "resources/day13-test.txt")))

(deftest severity-1
  (testing "Depth 0's severity is 0."
    (is (= (get-severity [0 3]) 0))))

(deftest severity-2
  (testing "Depth 1's severity is 0."
    (is (= (get-severity [1 2]) 0))))

(deftest severity-3
  (testing "Depth 4's severity is 0."
    (is (= (get-severity [4 4]) 0))))

(deftest severity-4
  (testing "Depth 6's severity is 24."
    (is (= (get-severity [6 4]) 24))))

(deftest input-severity
  (testing "Input's severity is 24."
    (is (= (total-severity-str input) 24))))

(deftest shortest-input-delay
  (testing "The shortest delay required is 10 picoseconds."
    (is (= (shortest-delay-str input) 10))))
