(ns adventofcode2017.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day12 :refer :all]))

(def input (line-seq (clojure.java.io/reader "resources/day12-test.txt")))
(def connected (get-connected-to (prepare-input input) "0"))

(deftest part-1-a
  (testing "Program 0 by definition."
    (is (contains? connected "0"))))

(deftest part-1-b
  (testing "Program 2, directly connected to program 0."
    (is (contains? connected "2"))))

(deftest part-1-c
  (testing "Program 3 via program 2."
    (is (contains? connected "3"))))

(deftest part-1-d
  (testing "Program 4 via program 2."
    (is (contains? connected "4"))))

(deftest part-1-f
  (testing "Program 5 via programs 6, then 4, then 2."
    (is (contains? connected "5"))))

(deftest part-1-g
  (testing "Program 6 via programs 4, then 2."
    (is (contains? connected "6"))))

(deftest part-1-total
  (testing "A total of 6 programs are in this group."
    (is (= (connected-to-zero-count input) 6))))

(deftest program-group-count-test
  (testing "There are two groups in the input."
    (is (= (program-group-count input) 2))))
