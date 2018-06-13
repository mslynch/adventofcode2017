 (ns adventofcode2017.day20-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day20 :refer :all]))

(def input1 (line-seq (clojure.java.io/reader "resources/day20-test1.txt")))
(def input2 (line-seq (clojure.java.io/reader "resources/day20-test2.txt")))

(deftest closest-to-origin-test
  (testing "The correct particle is chosen for closest to origin."
    (is (= (closest-to-origin input1) 0))))

(deftest left-after-collisions-test
  (testing "The correct number of particles are left after collisions."
    (is (= (left-after-collisions input2) 1))))
