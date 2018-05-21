(ns adventofcode2017.day15-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day15 :refer :all]
            [clojure.string :as str]))

(def start-a 65)
(def start-b 8921)

(deftest bit-mask-test
  (testing "Testing 16-bit bit mask."
    (is (= (Integer/toString (bit-and 0xffff 1092455) 2)
            "1010101101100111"))))

(deftest generation-test
  (testing "Testing number generation."
    (is (= (take 5 (generator start-a factor-a))
            [1092455 1181022009 245556042 1744312007 1352636452]))))

(deftest judge-test
  (testing "Testing the judge's count."
    (is (= (judge-count 40000000 start-a start-b)
            588))))
