(ns adventofcode2017.day6-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day6 :refer :all]))

(def initial [0 2 7 0])

(deftest part-1-a
  (testing "The first reallocation."
    (is (= (nth (iterate reallocate initial) 1) [2 4 1 2]))))

(deftest part-1-b
  (testing "The second reallocation."
    (is (= (nth (iterate reallocate initial) 2) [3 1 2 3]))))

(deftest part-1-c
  (testing "The third reallocation."
    (is (= (nth (iterate reallocate initial) 3) [0 2 3 4]))))

(deftest part-1-d
  (testing "The fourth reallocation."
    (is (= (nth (iterate reallocate initial) 4) [1 3 4 1]))))

(deftest part-1-e
  (testing "The fifth reallocation."
    (is (= (nth (iterate reallocate initial) 5) [2 4 1 2]))))

(deftest part-1-f
  (testing "There are 5 steps until a loop occurs."
    (is (= (steps-to-cycle initial) 5))))

(deftest part-1-g
  (testing "The cycle size is 4."
    (is (= (cycle-size initial) 4))))
