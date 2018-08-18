 (ns adventofcode2017.day21-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day21 :refer :all]))

; (def input1 (line-seq (clojure.java.io/reader "resources/day20-test1.txt")))
; (def input2 (line-seq (clojure.java.io/reader "resources/day20-test2.txt")))
;
(def three-by-three [[:a :b :c]
                     [:d :e :f]
                     [:g :h :i]])

(def four-by-four [[:a :b :c :d]
                   [:e :f :g :h]
                   [:i :j :k :l]
                   [:m :n :o :p]])

(def six-by-six [[:a :b :c :d :e :f]
                 [:g :h :i :j :k :l]
                 [:m :n :o :p :q :r]
                 [:s :t :u :v :w :x]
                 [:y :z :1 :2 :3 :4]
                 [:5 :6 :7 :8 :9 :0]])

(deftest horizontal-flip-test
  (testing "The matrix is flipped horizontally (along y axis)."
    (is (= (reverse-horizontal three-by-three)
           [[:c :b :a]
            [:f :e :d]
            [:i :h :g]]))))

(deftest vertical-flip-test
  (testing "The matrix is flipped vertically (along x axis)."
    (is (= (reverse-vertical three-by-three)
           [[:g :h :i]
            [:d :e :f]
            [:a :b :c]]))))

(deftest split-test-1
  (testing "The matrix should be split when size is 4x4."
    (is (= (split-matrix four-by-four)
           [[[[:a :b]
              [:e :f]] [[:c :d]
                        [:g :h]]]
            [[[:i :j]
              [:m :n]] [[:k :l]
                        [:o :p]]]]))))

(deftest split-test-2
  (testing "The matrix should be split when size is 6x6."
    (is (= (split-matrix six-by-six)
           [[[[:a :b]
              [:g :h]] [[:c :d]
                        [:i :j]] [[:e :f]
                                  [:k :l]]]
            [[[:m :n]
              [:s :t]] [[:o :p]
                        [:u :v]] [[:q :r]
                                  [:w :x]]]
            [[[:y :z]
              [:5 :6]] [[:1 :2]
                        [:7 :8]] [[:3 :4]
                                  [:9 :0]]]]))))

(deftest invertibility-1
  (testing "We can split and reassemble the matrix when the size is 4x4."
    (is (= four-by-four
           (reassemble-matrix (split-matrix four-by-four))))))

(deftest invertibility-2
  (testing "We can split and reassemble the matrix when the size is 6x6."
    (is (= six-by-six
           (reassemble-matrix (split-matrix six-by-six))))))