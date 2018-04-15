(ns adventofcode2017.day7-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day7 :refer :all]))

(def lines (line-seq (clojure.java.io/reader "resources/day7-test1.txt")))
(def populated (populate-map lines))
(def weighted (weighted-map populated (root populated)))

(deftest part-1
  (testing "The bottom program is tknk."
    (is (= (bottom-program lines) "tknk"))))

(deftest part-2
  (testing "The one program's adjusted weight is 60."
    (is (= (correct-weight lines) 60))))

(deftest parent-test
  (testing "The parent of ugml is tknk."
    (is (= (parent populated "ugml") "tknk"))))

(deftest other-weight-test
  (testing "The other weight for ugml is 243."
    (is (= (other-weight weighted "ugml") 243))))

(deftest odd-program-test
  (testing "tknk's odd program is ugml."
    (is (= (odd-program weighted (second (weighted "tknk"))) "ugml"))))
