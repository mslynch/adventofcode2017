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

(deftest generation-1-test
  (testing "Testing number generation."
    (is (= (take 5 (generator start-a factor-a 1))
            [1092455 1181022009 245556042 1744312007 1352636452]))))

(deftest generation-1-test
  (testing "Testing number generation."
    (is (= (take 5 (generator start-a factor-a 4))
            [1352636452 1992081072 530830436 1980017072 740335192]))))

(deftest generation-2-test
  (testing "Testing number generation."
    (is (= (take 5 (generator start-b factor-b 8))
            [1233683848 862516352 1159784568 1616057672 412269392]))))

(deftest judge-1-test
  (testing "Testing the first judge's count."
    (is (= (judge-count-1 40000000 start-a start-b)
            588))))

(deftest judge-2-test
  (testing "Testing the second judge's count."
    (is (= (judge-count-2 5000000 start-a start-b)
            309))))
