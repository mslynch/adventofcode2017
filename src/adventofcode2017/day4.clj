(ns adventofcode2017.day4
  (:require [clojure.string :as str]))

(defn no-dups?
  "Returns true if there are no duplicate words."
  [passphrase]
    (apply distinct? passphrase))

(defn no-anagrams?
  "Returns true if there are no anagrams."
  [passphrase]
  (apply distinct? (map frequencies passphrase)))


(defn num-valid
  "Determines the number of valid passphrases in a line-separated list."
  [validator input]
  (->> (str/split input #"\n")
       (map #(str/split % #"\s"))
       (filter validator)
       (count)))
