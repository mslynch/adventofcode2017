(ns adventofcode2017.day7-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day7 :refer :all]))

(deftest part-1
  (testing "The bottom program is tknk."
    (is (= (bottom-program (line-seq (clojure.java.io/reader "resources/day7-test1.txt")))
           "tknk"))))
