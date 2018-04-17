(ns adventofcode2017.day04-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [adventofcode2017.day04 :refer :all]))

(deftest part-1-a
  (testing "aa bb cc dd ee is valid."
    (is (no-dups? (str/split "aa bb cc dd ee" #"\s")))))

(deftest part-1-b
  (testing "aa bb cc dd aa is not valid."
    (is (not (no-dups? (str/split "aa bb cc dd aa" #"\s"))))))

(deftest part-1-c
  (testing "aa bb cc dd aaa is valid."
    (is (no-dups? (str/split "aa bb cc dd aaa" #"\s")))))

(deftest part-2-a
  (testing "abcde fghij is valid."
    (is (no-anagrams? (str/split "abcde fghij" #"\s")))))

(deftest part-2-b
  (testing "abcde xyz ecdab is not valid."
    (is (not (no-anagrams? (str/split "abcde xyz ecdab" #"\s"))))))

(deftest part-2-c
  (testing "a ab abc abd abf abj is valid."
    (is (no-anagrams? (str/split "a ab abc abd abf abj" #"\s")))))

(deftest part-2-d
  (testing "iiii oiii ooii oooi oooo is valid."
    (is (no-anagrams? (str/split "iiii oiii ooii oooi oooo" #"\s")))))

(deftest part-2-e
  (testing "oiii ioii iioi iiio is not valid."
    (is (not (no-anagrams? (str/split "oiii ioii iioi iiio" #"\s"))))))
