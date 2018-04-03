(ns adventofcode2017.day1)

(defn match-val
  "Returns 1 if the inputs match, 0 if they do not."
  [a b]
  (if (= a b)
    (Character/getNumericValue a)
    0))

(defn shift-sum
  "The total number of digits matching after a circular shift is applied to the input."
  [input shift]
  (reduce + (map match-val
                 input
                 (drop shift (cycle input)))))

(defn inverse-captcha
  "Calculates shift-sum with a shift of 1."
  [input]
  (shift-sum input 1))

(defn inverse-captcha-2
  "Calculates shift-sum, shifting the input for half its size."
  [input]
  (shift-sum input
             (/ (count input) 2)))
