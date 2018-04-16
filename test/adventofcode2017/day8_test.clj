(ns adventofcode2017.day8-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day8 :refer :all]))

(def lines (line-seq (clojure.java.io/reader "resources/day8-test.txt")))
(def instructions (map line-to-instruction lines))

(deftest part-1
  (testing "The largest value in any register after completing instructions is 1."
    (is (= (largest-register-value lines) 1))))

(deftest part-1
  (testing "The largest value in any register while completing instructions is 10."
    (is (= (largest-register-value-ever lines) 10))))

(deftest part-a
  (testing "The map is empty after the first instruction."
    (is (= (reduce apply-instruction {} (take 1 instructions)) {}))))

(deftest part-b
  (testing "The map after the second instruction."
    (is (= (reduce apply-instruction {} (take 2 instructions)) {"a" 1}))))

(deftest part-c
  (testing "The map after the third instruction."
    (is (= (reduce apply-instruction {} (take 3 instructions)) {"a" 1 "c" 10}))))

(deftest part-d
  (testing "The map after the fourth instruction."
    (is (= (reduce apply-instruction {} (take 4 instructions)) {"a" 1 "c" -10}))))
