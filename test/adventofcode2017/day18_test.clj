 (ns adventofcode2017.day18-test
   (:require [clojure.test :refer :all]
             [adventofcode2017.day18 :refer :all]
             [clojure.string :as str]
             [clojure.core.async :as async]))

(def input1 (line-seq (clojure.java.io/reader "resources/day18-test1.txt")))
(def input2 (line-seq (clojure.java.io/reader "resources/day18-test2.txt")))

(deftest get-value-test
  (testing "Getting register values."
    (is (= (get-value {"a" 1} "a")
           1))
    (is (= (get-value {"a" 1} "2")
           2))))

(deftest recovered-freq-test
  (testing "The frequency of the last sound played is 4."
    (is (= (duet input1)
           4))))

(deftest receive-count-test
  (testing "Program 1 sent 3 values."
    (is (= (duet-async input2)
           3))))

(def data {:register 0
           :registers {"a" 5}
           :receive-count 0})

(deftest play-sound-test
  (testing "Sounds play."
    (is (= (play-sound data ["a"])
           {:register 1
            :registers {"a" 5}
            :receive-count 0
            :sound 5}))))

(deftest recover-test
  (testing "Sounds play."
    (is (= (recover (assoc data :sound 5) [0])
           {:register 1
            :registers {"a" 5}
            :receive-count 0
            :sound 5
            :output 5}))))

(deftest set-register-test
  (testing "Registers get set."
    (is (= (set-register data ["a" "6"])
           {:register 1
            :registers {"a" 6}
            :receive-count 0}))))

(deftest arithmetic-test
  (testing "Arithmetic operations work."
    (is (= (add data ["a" "2"])
           {:register 1
            :registers {"a" 7}
            :receive-count 0}))
    (is (= (multiply data ["a" "2"])
           {:register 1
            :registers {"a" 10}
            :receive-count 0}))
    (is (= (modulo data ["a" "2"])
           {:register 1
            :registers {"a" 1}
            :receive-count 0}))))

(deftest jgz-test
  (testing "Jump if greater than 0."
    (is (= (jgz data ["a" "5"])
           {:register 5
            :registers {"a" 5}
            :receive-count 0}))
    (is (= (jgz (update data :registers #(assoc % "b" 0)) ["b" "5"])
           {:register 1
            :registers {"a" 5 "b" 0}
            :receive-count 0}))))

(let [c (async/chan 5)]
  (deftest send-to-chan-test
    (testing "Sending to channels works."
      (is (= (send-to-chan (assoc data :send-chan c) ["a"])
             {:register 1
              :registers {"a" 5}
              :receive-count 0
              :send-chan c}))
      (is (= (async/<!! c) 5)))))
