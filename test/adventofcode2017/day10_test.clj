(ns adventofcode2017.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day10 :refer :all]))

(def input [3 4 1 5])
(def size 5)

(deftest part-1
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (first-two-product input size) 12))))

(deftest part-1-a
  (testing "The list after 1 number is fed in."
    (is (= (knot-hash (take 1 input) size) [2 1 0 3 4]))))

(deftest part-1-b
  (testing "The list after 1 number is fed in."
    (is (= (knot-hash (take 2 input) size) [4 3 0 1 2]))))

(deftest part-1-c
  (testing "The list after 1 number is fed in."
    (is (= (knot-hash (take 3 input) size) [4 3 0 1 2]))))

(deftest part-1-d
  (testing "The list after 1 number is fed in."
    (is (= (knot-hash (take 4 input) size) [4 3 2 1 0]))))
