(ns adventofcode2017.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day10 :refer :all]))

(def example-input [3 4 1 5])
(def example-size 5)

(deftest part-1
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (first-two-product example-input example-size)
           12))))

(deftest part-1-a
  (testing "The list after 1 number is fed in."
    (is (= (knot-hash example-size (take 1 example-input))
           [2 1 0 3 4]))))

(deftest part-1-b
  (testing "The list after 2 numbers are fed in."
    (is (= (knot-hash example-size (take 2 example-input))
           [4 3 0 1 2]))))

(deftest part-1-c
  (testing "The list after 3 numbers are fed in."
    (is (= (knot-hash example-size (take 3 example-input))
           [4 3 0 1 2]))))

(deftest part-1-d
  (testing "The list after 4 numbers are fed in."
    (is (= (knot-hash example-size (take 4 example-input))
           [3 4 2 1 0]))))

(deftest part-2-a
  (testing "The empty string becomes a2582a3a0e66e6e86e3812dcb672a272."
    (is (= (full-knot-hash "")
           "a2582a3a0e66e6e86e3812dcb672a272"))))

(deftest part-2-b
  (testing "AoC 2017 becomes 33efeb34ea91902bb2f59c9920caa6cd."
    (is (= (full-knot-hash "AoC 2017")
           "33efeb34ea91902bb2f59c9920caa6cd"))))

(deftest part-2-c
  (testing "1,2,3 becomes 3efbe78a8d82f29979031a4aa0b16a9d."
    (is (= (full-knot-hash "1,2,3")
           "3efbe78a8d82f29979031a4aa0b16a9d"))))

(deftest part-2-d
  (testing "1,2,4 becomes 63960835bcdc130f0b66d7ff4f6a5a8e."
    (is (= (full-knot-hash "1,2,4")
           "63960835bcdc130f0b66d7ff4f6a5a8e"))))
