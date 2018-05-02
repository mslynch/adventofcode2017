(ns adventofcode2017.day07-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day07 :refer :all]))

(def lines (line-seq (clojure.java.io/reader "resources/day07-test1.txt")))
(def populated (populate-map lines))
(def parented (get-parent-map populated))
(def weighted (get-weighted-map parented (get-root parented)))

(deftest get-bottom-program-test
  (testing "The bottom program is tknk."
    (is (= (get-bottom-program lines) "tknk"))))

(deftest get-wrong-weight-program-test
  (testing "The correct info is returned from get-wrong-weight-program."
    (is (= (get-wrong-weight-program weighted (get-root weighted))
           {:program "ugml"
            :right-total 243
            :wrong-total 251
            :wrong-weight 68}))))

(deftest calculate-correct-weight-test
  (testing "Testing weight correction calculation."
    (is (= 60
           (calculate-correct-weight {:wrong-weight 68
                                      :wrong-total 251
                                      :right-total 243})))))
