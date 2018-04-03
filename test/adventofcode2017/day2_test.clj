(ns adventofcode2017.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day2 :refer :all]))

(deftest part-1
  (testing "The example produces a sum of 18."
    (is (= (checksum (slurp "resources/day2-test1.txt") difference)
           18))))

(deftest part-2
  (testing "The example produces a sum of 9."
    (is (= (checksum (slurp "resources/day2-test2.txt") even-division)
           9))))
