(ns adventofcode2017.day11-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day11 :refer :all]))

(deftest part-1-a
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (get-steps-away "ne,ne,ne")
           3))))

(deftest part-1-b
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (get-steps-away "ne,ne,sw,sw")
           0))))

(deftest part-1-c
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (get-steps-away "ne,ne,s,s")
           2))))

(deftest part-1-d
  (testing "The result of multiplying the first two numbers in the list is 12."
    (is (= (get-steps-away "se,sw,se,sw,sw")
           3))))
