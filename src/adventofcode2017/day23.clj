(ns adventofcode2017.day23
  (:require [clojure.string :as str]))

(defn get-value
  "If arg is a register, returns the register value; otherwise returns arg."
  [registers arg]
  (if (char? arg)
    (registers arg)
    arg))

(defn set-instr
  "Sets register x to the value of y."
  [data [x y]]
  (-> data
      (update :registers #(assoc % x (get-value (data :registers) y)))
      (update :register inc)))

(defn sub
  "Decreases register x by the value of y."
  [data [x y]]
  (-> data
      (update :registers (fn [registers]
                           (update registers
                                   x
                                   #(- % (get-value (data :registers) y)))))
      (update :register inc)))

(defn mul
  "Sets register x to the result of multiplying the value contained in register
   x by the value of y."
  [data [x y]]
  (-> data
      (update :registers (fn [registers]
                           (update registers
                                   x
                                   (partial * (get-value (data :registers) y)))))
      (update :register inc)))

(defn jnz
  "Jumps with an offset of the value of y, but only if the value of x is not 0."
  [data [x y]]
  (if (zero? (get-value (data :registers) x))
    (update data :register inc)
    (update data :register (partial + (get-value (data :registers) y)))))

(defn parse-args
  "Parse argument into char or bigint."
  [arg]
  (if (re-matches #"[a-z]" arg)
    (first arg)
    (bigint arg)))

(defn parse-input
  "Parses instructions into corresponding functions and strings into integers."
  [input]
  (mapv #(let [[instruction & args] (str/split % #" ")]
           [({"set" set-instr
              "sub" sub
              "mul" mul
              "jnz" jnz} instruction)
            (map parse-args args)])
        input))

(defn run-program
  "Runs the instructions of the program and returns the number of times mul was called."
  [input]
  (let [size (count input)
        instructions (parse-input input)]
    (loop [data {:registers (reduce #(assoc %1 %2 0)
                                    {}
                                    (map char (range (int \a) (inc (int \h)))))
                 :register 0}
           mul-call-count 0]
      (if (>= (data :register) size)
        mul-call-count
        (let [[instruction args] (instructions (data :register))]
          (recur
           (instruction data args)
           (if (= instruction mul)
             (inc mul-call-count)
             mul-call-count)))))))

; set b 79
; set c b
; jnz a 2               # goto A
; jnz 1 5               # skipped!
; mul b 100             #A
; sub b -100000
; set c b
; sub c -17000
;     set f 1
;     set d 2           # for d in 2..b
;         set e 2       # for e in 2..b
;             set g d
;             mul g e
;             sub g b   # g = d * e
;             set f 0
;             jnz g 2
;             sub e -1  # inc e
;             set g e
;             sub g b
;             jnz g -8  # end e loop
;         sub d -1      # inc d
;         set g d
;         sub g b
;         jnz g -13    # end d loop
;     jnz f 2          # if ?? then ??
;     sub h -1
;     set g b
;     sub g c
;     jnz g 2
;     jnz 1 3          # halt
;     sub b -17
;     jnz 1 -23
;
; PSEUDOCODE
; b = 79 * 100 + 100000
; for b_i in b..(b + 17000)
;     f = 1
;     for d in 2..b_i
;         for e in 2..b_i
;             f = 0 if d*e == b
;     h += 1 if f == 0
;
; If d*e == b, b is not prime, so we're counting the number of nonprimes in the range.

(defn get-b
  "Gets the value of register b after completing line 6."
  [input]
  (+ (* 100 (bigint (nth (str/split (first input) #" ") 2)))
     100000))

(defn primes-in-range
  [b]
  "Counts the number of primes in the range based on b."
  (count
   (remove #(.isProbablePrime % 1000)
           (map #(.toBigInteger %)
                (range b (+ b 17000 17) 17)))))

(defn calculate-h
  "Calculates the final value of register h."
  [input]
  (primes-in-range (get-b input)))