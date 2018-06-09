 (ns adventofcode2017.day19-test
   (:require [clojure.test :refer :all]
             [clojure.string :as str]
             [adventofcode2017.day19 :refer :all]))

(def input (line-seq (clojure.java.io/reader "resources/day19-test.txt")))

(def turn-input (mapv vec [" | "
                           " +-"
                           "   "]))

(deftest turn-test
  (testing "Correct turning."
    (is (= (turn turn-input
                 :down
                 [1 1])
           {:position [1 2]
            :direction :right}))))

(deftest path-letters-test
  (testing "Correct path traversal."
    (is (= (path-letters input)
           "ABCDEF"))))

(deftest path-steps-test
  (testing "Correct number of steps taken on path."
    (is (= (path-steps input)
           38))))
