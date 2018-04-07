(ns adventofcode2017.day6)

(defn new-max-indices
  "Returns the new max and the new index."
  [value max-value index max-indices]
  (cond
    (> value max-value) [value [index]]
    (= value max-value) [value (cons index max-indices)]
    :else [max-value max-indices]))

(defn max-reduce
  "Given the previous max, the indices it appears at, and the current index and value, return a new maximum and the indices it appears at."
  [[max-value indices] [index value]]
  (new-max-indices value max-value index indices))

(defn max-index
  "The maximum of the collection and the first index it appears at."
  [coll]
  (let [[max-val indices] (reduce max-reduce [(second (first coll)) [0]] (rest coll))]
    [max-val (last indices)]))

(defn reallocate-distributor
  "While remaining > 0, increment the bank at index."
  [banks remaining index]
  (if (zero? remaining)
    banks
    (let [mod-index (mod index (count banks))]
      (recur
       (assoc banks mod-index (inc (banks mod-index)))
       (dec remaining)
       (inc index)))))

(defn reallocate
  "A reallocated list of banks."
  [banks]
  (let [indices-banks (map-indexed (fn [ind item] [ind item]) banks)
        [max-val index-of-max] (max-index indices-banks)]
    (reallocate-distributor (assoc banks index-of-max 0) max-val (inc index-of-max))))

(defn cycle-info
  "The number of steps required to reach a loop and the "
  [banks previous-banks step]
  (if (.contains previous-banks banks)
    [step (- (count previous-banks) (.indexOf previous-banks banks))]
    (recur (reallocate banks)
      (conj previous-banks banks)
      (inc step))))

(defn steps-to-cycle
  "The number of reallocations needed to reach a cycle."
  [banks]
  (first (cycle-info banks [] 0)))

(defn cycle-size
  "The size of the reallocation cycle."
  [banks]
  (second (cycle-info banks [] 0)))
