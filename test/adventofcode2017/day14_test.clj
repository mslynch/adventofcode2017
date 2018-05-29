(ns adventofcode2017.day14-test
  (:require [clojure.test :refer :all]
            [adventofcode2017.day14 :refer :all]
            [clojure.string :as str]))

(def coords (hash-to-coords "flqrgnkx"))

(deftest prehash-rows-test
  (testing "Pre-hash rows."
    (is (= (prehash-rows "asdf" 3)
           ["asdf-0" "asdf-1" "asdf-2"]))))

(deftest hex-to-binary-test-1
  (testing "Hexadecimal to binary conversion."
    (is (str/ends-with? (hex-to-binary "4")
                        " 100"))))

(deftest hex-to-binary-test-2
  (testing "Hexadecimal to binary conversion."
    (is (str/ends-with? (hex-to-binary "bb")
                        " 10111011"))))

(deftest hex-to-binary-test-3
  (testing "Hexadecimal to binary conversion."
    (is (= (count (hex-to-binary "bb")) 128))))

(deftest used-squares-count-test
  (testing "The number of 1s in the binary output."
    (is (= (count coords)
           8108))))
;
(deftest region-count-test-1
  (testing "The number of regions in the binary output."
    (is (= (region-count #{[0 0] [0 1] [1 0] [1 1]})
           1))))

(deftest region-count-test-2
  (testing "The number of regions in the binary output."
    (is (= (region-count #{[0 0] [0 1] [2 0] [2 1]})
           2))))

(deftest region-count-test-example
  (testing "The number of regions in the binary output."
    (is (= (region-count coords)
           1242))))
