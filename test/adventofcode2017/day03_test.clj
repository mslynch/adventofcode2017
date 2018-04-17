(ns adventofcode2017.day03-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day03 :refer :all]))

(deftest part-1-a
  (testing "Data from square 1 is carried 0 steps."
    (is (= (spiral 1) 0))))

(deftest part-1-b
  (testing "Data from square 12 is carried 3 steps."
    (is (= (spiral 12) 3))))

(deftest part-1-c
  (testing "Data from square 23 is carried only 2 steps."
    (is (= (spiral 23) 2))))

(deftest part-1-d
  (testing "Data from square 1024 must be carried 31 steps."
    (is (= (spiral 1024) 31))))

(def values (spiral-values {} (spiral-seq :right [0 0])))

(deftest part-2-a
  (testing "Square 1 starts with the value 1."
    (is (= (first values) 1))))

(deftest part-2-b
  (testing "Square 2 has the value 1."
    (is (= (nth values 1) 1))))

(deftest part-2-c
  (testing "Square 3 has the value 2."
    (is (= (nth values 2) 2))))

(deftest part-2-d
  (testing "Square 4 has the value 4."
    (is (= (nth values 3) 4))))

(deftest part-2-e
  (testing "Square 5 has the value 5."
    (is (= (nth values 4) 5))))
