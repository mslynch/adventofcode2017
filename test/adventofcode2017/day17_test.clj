 (ns adventofcode2017.day17-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day17 :refer :all]
             [clojure.string :as str]))

(deftest insert-at-test
  (testing "Testing insertion into the middle of a list."
    (is (= (insert-at [:a :b :c :d] 2 :z)
           [:a :b :z :c :d]))))

(deftest spinlock-test
  (testing "Testing spinlock."
    (is (= (spinlock 1 3) [0 1]))
    (is (= (spinlock 2 3) [0 2 1]))
    (is (= (spinlock 3 3) [0 2 3 1]))
    (is (= (spinlock 4 3) [0 2 4 3 1]))
    (is (= (spinlock 5 3) [0 5 2 4 3 1]))
    (is (= (spinlock 6 3) [0 5 2 4 3 6 1]))
    (is (= (spinlock 7 3) [0 5 7 2 4 3 6 1]))
    (is (= (spinlock 8 3) [0 5 7 2 4 3 8 6 1]))
    (is (= (spinlock 9 3) [0 9 5 7 2 4 3 8 6 1]))))

(deftest value-after-test
  (testing "The value 638 appears after 2017."
    (is (= (value-after (spinlock 2017 3) 2017)
           638))))

(deftest spinlock-cheap-test
  (testing "Testing getting the value after 0."
    (is (= (value-after-0 8 3)
           5))))
