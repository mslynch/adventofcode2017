 (ns adventofcode2017.day19-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day19 :refer :all]))

(def input (line-seq (clojure.java.io/reader "resources/day19-test.txt")))

(deftest path-letters-test
  (testing "Correct path traversal."
    (is (path-letters input)
        "ABCDEF")))
