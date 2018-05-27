(ns adventofcode2017.day16-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day16 :refer :all]
            [clojure.string :as str]))

(deftest spin-test
  (testing "s1, a spin of size 1: eabcd."
    (is (= (apply-move [\a \b \c \d \e] "s1")
           [\e \a \b \c \d]))))

(deftest exchange-test
  (testing "x3/4, swapping the last two programs: eabdc."
    (is (= (apply-move [\e \a \b \c \d] "x3/4")
           [\e \a \b \d \c]))))

(deftest partner-test
  (testing "pe/b, swapping programs e and b: baedc."
    (is (= (apply-move [\e \a \b \d \c] "pe/b")
           [\b \a \e \d \c]))))

(deftest dance-test
  (testing "The entire dance."
    (is (= (dance [\a \b \c \d \e] "s1,x3/4,pe/b")
           [\b \a \e \d \c]))))

(deftest dance-n-test
  (testing "The entire dance, twice."
    (is (= (dance-n [\a \b \c \d \e] "s1,x3/4,pe/b" 2)
           [\c \e \a \d \b]))))
