(ns adventofcode2017.day01-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day01 :refer :all]))

(deftest part-1-a
  (testing "1122 produces a sum of 3."
    (is (= (inverse-captcha "1122") 3))))

(deftest part-1-b
  (testing "1111 produces a sum of 4."
    (is (= (inverse-captcha "1111") 4))))

(deftest part-1-c
  (testing "1234 produces a sum of 0."
    (is (= (inverse-captcha "1234") 0))))

(deftest part-1-d
  (testing "91212129 produces a sum of 9."
    (is (= (inverse-captcha "91212129") 9))))

(deftest part-2-a
  (testing "1212 produces a sum of 6."
    (is (= (inverse-captcha-2 "1212") 6))))

(deftest part-2-b
  (testing "1221 produces a sum of 0."
    (is (= (inverse-captcha-2 "1221") 0))))

(deftest part-2-c
  (testing "123425 produces a sum of 4"
    (is (= (inverse-captcha-2 "123425") 4))))

(deftest part-2-d
  (testing "123123 produces a sum of 12."
    (is (= (inverse-captcha-2 "123123") 12))))

(deftest part-2-e
  (testing "12131415 produces a sum of 4."
    (is (= (inverse-captcha-2 "12131415") 4))))
