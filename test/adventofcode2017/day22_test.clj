 (ns adventofcode2017.day22-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day22 :refer :all]))

(def initial-grid ["..#"
                   "#.."
                   "..."])

(deftest initial-grid-infections-test
  (testing "Initial grid infections are determined correctly."
    (is (= (infection-grid-to-set initial-grid)
           #{[1 1] [-1 0]}))))

(deftest infection-count-test-1
  (testing "The number of infections is correct after 70 bursts."
    (is (= (count-infections initial-grid 70 burst)
           41))))

(deftest infection-count-test-2
  (testing "The number of infections is correct after 10000 bursts."
    (is (= (count-infections initial-grid 10000 burst)
           5587))))

(deftest infection-count-evolved-test-1
  (testing "The number of infections is correct after 100 bursts (evolved)."
    (is (= (count-infections initial-grid 100 burst-evolved)
           26))))

(deftest infection-count-evolved-test-2
  (testing "The number of infections is correct after 10000000 bursts (evolved)."
    (is (= (count-infections initial-grid 10000000 burst-evolved)
           2511944))))