(ns adventofcode2017.day5-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day5 :refer :all]))

(def initial-state [0 3 0 1 -3])
(def states (maze-states initial-state 0))

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
  (testing "The fifth state."
    (is (= (nth states 5) [2 5 0 1 -2]))))

(deftest part-1-g
  (testing "Last state is :end."
    (is (= (nth states 6) :end))))

(deftest part-1-h
  (testing "It takes 5 steps to exit the maze."
    (is (= (steps-to-exit initial-state) 5))))
