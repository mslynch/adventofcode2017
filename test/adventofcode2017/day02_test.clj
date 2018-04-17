(ns adventofcode2017.day02-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day02 :refer :all]))

(deftest part-1
  (testing "The example produces a sum of 18."
    (is (= (checksum (slurp "resources/day02-test1.txt") difference)
           18))))

(deftest part-2
  (testing "The example produces a sum of 9."
    (is (= (checksum (slurp "resources/day02-test2.txt") even-division)
           9))))
