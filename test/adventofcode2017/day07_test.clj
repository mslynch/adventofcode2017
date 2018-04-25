(ns adventofcode2017.day07-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day07 :refer :all]))

(def lines (line-seq (clojure.java.io/reader "resources/day07-test1.txt")))
(def populated (populate-map lines))
(def parented (get-parent-map populated))
(def weighted (get-weighted-map parented (get-root parented)))

(deftest calculate-correct-weight-test
  (testing "Testing weight correction calculation."
    (is (= 60
           (calculate-correct-weight {:wrong-weight 68
                                   :wrong-total 251
                                   :right-total 243})))))

(deftest get-bottom-program-test
  (testing "The bottom program is tknk."
    (is (= (get-bottom-program lines) "tknk"))))

(deftest get-correct-weight-test
  (testing "The one program's adjusted weight is 60."
    (is (= (get-correct-weight lines) 60))))
;
; (deftest parent-test
;   (testing "The parent of ugml is tknk."
;     (is (= (get-parent populated) "tknk"))))

(def input (line-seq (clojure.java.io/reader "resources/day07.txt")))
(def pars (get-parent-map (populate-map input)))
(def w (get-weighted-map pars (get-root pars)))
; vgzejbd is root

; [vuoqao snuewn fiprusz jjgjvki vwkkml kmpfxl]
