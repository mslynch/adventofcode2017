(ns adventofcode2017.day05-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day05 :refer :all]))

(def initial-state [0 3 0 1 -3])
(def states (maze-states initial-state 0 inc))

(deftest part-1-a
  (testing "The initial state."
    (is (= (nth states 0) [0 3 0 1 -3]))))

(deftest part-1-b
  (testing "The first state."
    (is (= (nth states 1) [1 3 0 1 -3]))))

(deftest part-1-c
  (testing "The second state."
    (is (= (nth states 2) [2 3 0 1 -3]))))

(deftest part-1-d
  (testing "The third state."
    (is (= (nth states 3) [2 4 0 1 -3]))))

(deftest part-1-e
  (testing "The fourth state."
    (is (= (nth states 4) [2 4 0 1 -2]))))

(deftest part-1-f
  (testing "It takes 5 steps to exit the maze."
    (is (= (steps-to-exit initial-state inc) 5))))

(deftest part-2
  (testing "The example in part 2 takes 10 steps."
    (is (= (steps-to-exit initial-state fancy-offset) 10))))
