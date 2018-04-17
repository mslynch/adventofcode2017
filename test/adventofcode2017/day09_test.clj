(ns adventofcode2017.day09-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day09 :refer :all]))

(deftest part-1-a
  (testing "Score, example 1."
    (is (= (first (process-stream "{}")) 1))))

(deftest part-1-b
  (testing "Score, example 2."
    (is (= (first (process-stream "{{{}}}")) 6))))

(deftest part-1-c
  (testing "Score, example 3."
    (is (= (first (process-stream "{{},{}}")) 5))))

(deftest part-1-d
  (testing "Score, example 4."
    (is (= (first (process-stream "{{{},{},{{}}}}")) 16))))

(deftest part-1-e
  (testing "Score, example 5."
    (is (= (first (process-stream "{<a>,<a>,<a>,<a>}")) 1))))

(deftest part-1-f
  (testing "Score, example 6."
    (is (= (first (process-stream "{{<ab>},{<ab>},{<ab>},{<ab>}}")) 9))))

(deftest part-1-g
  (testing "Score, example 7."
    (is (= (first (process-stream "{{<!!>},{<!!>},{<!!>},{<!!>}}")) 9))))

(deftest part-1-h
  (testing "Score, example 8."
    (is (= (first (process-stream "{{<a!>},{<a!>},{<a!>},{<ab>}}")) 3))))

(deftest part-2-a
  (testing "Non-canceled, example 1."
    (is (= (second (process-stream "<>")) 0))))

(deftest part-2-b
  (testing "Non-canceled, example 2."
    (is (= (second (process-stream "<random characters>")) 17))))

(deftest part-2-c
  (testing "Non-canceled, example 3."
    (is (= (second (process-stream "<<<<>")) 3))))

(deftest part-2-d
  (testing "Non-canceled, example 4."
    (is (= (second (process-stream "<{!>}>")) 2))))

(deftest part-2-e
  (testing "Non-canceled, example 5."
    (is (= (second (process-stream "<!!>")) 0))))

(deftest part-2-f
  (testing "Non-canceled, example 6."
    (is (= (second (process-stream "<!!!>>")) 0))))

(deftest part-2-g
  (testing "Non-canceled, example 7."
    (is (= (second (process-stream "<{o\"i!a,<{i<a>")) 10))))
