(ns adventofcode2017.day18
  (:require [clojure.string :as str]
            [clojure.core.async :as async]))

(defn get-value
  "If y is a register, returns the register value; otherwise parses with bigint."
  [registers y]
  (if (re-matches #"[a-z]" y)
    (registers y)
    (bigint y)))

(defn run-instructions
  "Chooses the correct instruction to execute and applies it to data."
  [instructions registers send-chan receive-chan program-id]
  (loop [data {:register 0
               :registers registers
               :receive-count 0
               :send-chan send-chan
               :receive-chan receive-chan
               :program-id program-id}]
    (let [register (data :register)]
      (if (or (>= register (count instructions))
              (neg? register)
              (data :output))
        data
        (let [[instruction & [args]] (instructions register)]
          (recur (instruction data args)))))))

(defn play-sound
  "Plays a sound with a frequency equal to the value of x."
  [data [x]]
  (-> data
      (assoc :sound (get-value (data :registers) x))
      (update :register inc)))

(defn recover
  "Recovers the frequency of the last sound played, but only when the
   value of x is not zero."
  [data [x]]
  (update (if (not= 0 ((data :registers) x))
            (assoc data :output (data :sound))
            data)
          :register
          inc))

(defn set-register
  "Sets register x to the value of y."
  [data [x y]]
  (-> data
      (update :registers #(assoc % x (get-value (data :registers) y)))
      (update :register inc)))

(defn arithmetic
  "Updates a register using an arithmetic operation."
  [registers x y operation]
  (update registers x #(operation (or % 0) (get-value registers y))))

(defn add
  "Increases register x by the value of y."
  [data [x y]]
  (-> data
      (update :registers #(arithmetic % x y +))
      (update :register inc)))

(defn multiply
  "Sets register x to the result of multiplying the value contained in
   register x by the value of y."
  [data [x y]]
  (-> data
      (update :registers #(arithmetic % x y *))
      (update :register inc)))

(defn modulo
  "Sets x to the result of x modulo y."
  [data [x y]]
  (-> data
      (update :registers #(arithmetic % x y mod))
      (update :register inc)))

(defn jgz
  "Jumps with an offset of the value of y, but only if the value of x
   is greater than zero."
  [data [x y]]
  (if (pos? (get-value (data :registers) x))
    (update data :register #(+ % (get-value (data :registers) y)))
    (update data :register inc)))

(defn send-to-chan
  "Send a value to the chan."
  [data [x]]
  (do
    (async/>!! (data :send-chan) (get-value (data :registers) x))
    (update data :register inc)))

(defn receive-from-chan
  "Receive a value from the channel and stores it in register x."
  [data [x]]
  (let [received (deref (future (async/<!! (data :receive-chan))) 100 :deadlock)]
    (if (= received :deadlock)
      (do
        (async/>!! (data :send-chan) :deadlock)
        (assoc data :output :deadlock))
      (-> data
          (update :registers #(assoc % x received))
          (update :receive-count inc)
          (update :register inc)))))

(def common-instructions
  {"snd" play-sound
   "set" set-register
   "add" add
   "mul" multiply
   "mod" modulo
   "rcv" recover
   "jgz" jgz})

(def sound-map
  {"snd" play-sound
   "rcv" recover})

(def async-map
  {"snd" send-to-chan
   "rcv" receive-from-chan})

(defn parse-input
  "Parses instructions into corresponding functions and strings into integers."
  [input unique-instructions]
  (mapv #(let [[instruction & args] (str/split % #" ")]
           [((merge common-instructions
                    unique-instructions) instruction) args])
        input))

(defn duet
  "Runs the duet and finds the first recovered value."
  [input]
  ((run-instructions (parse-input input sound-map) {} nil nil 0)
   :output))

(defn duet-async-runner
  "Outputs the number of sends run."
  [instructions program-id send-chan receive-chan]
  ((run-instructions instructions
                     {"p" program-id}
                     send-chan
                     receive-chan
                     program-id)
   :receive-count))

(defn duet-async
  "Runs the duet in two threads and finds the receive count of program 1."
  [input]
  (async/<!!
   (let [chan0 (async/chan 10000)
         chan1 (async/chan 10000)
         parsed-input (parse-input input async-map)]
     (async/thread (duet-async-runner parsed-input
                                      1
                                      chan0
                                      chan1))
     (async/thread (duet-async-runner parsed-input
                                      0
                                      chan1
                                      chan0)))))
