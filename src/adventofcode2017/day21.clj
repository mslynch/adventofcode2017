(ns adventofcode2017.day21
  (:require [clojure.string :as str]))

(defn parse-line
  "Splits a line into ((px py pz) (vx vy vz) (ax ay az))."
  [line]
  (map
   #(map vec (str/split % #"/"))
   (str/split (str/join (remove #(contains? #{\space \>} %) line)) #"=")))

(defn rotate-2
  "Rotates a 2x2 matrix clockwise."
  [[[a b]
    [c d]]]
  [[c a]
   [d b]])

(defn rotate-3
  "Rotates a 3x3 matrix clockwise."
  [[[a b c]
    [d e f]
    [g h i]]]
  [[g d a]
   [h e b]
   [i f c]])

(defn reverse-horizontal
  "Flips a matrix horizontally."
  [matrix]
  (mapv (comp vec reverse) matrix))

(defn reverse-vertical
  "Flips a matrix vertically."
  [matrix]
  (vec (reverse matrix)))

(defn all-transformations
  "A list of all transformations on the matrix (rotations and reflections)."
  [matrix]
  (let  [rotator (if (= 2 (count matrix))
                   rotate-2
                   rotate-3)]
    (mapcat #(take 4 (iterate rotator %))
            [matrix
             (reverse-vertical matrix)
             (reverse-horizontal matrix)])))

(defn add-to-map
  "Adds all transformed inputs to the map with their output patterns."
  [m [transformed-input-patterns output-pattern]]
  (reduce #(assoc %1 %2 output-pattern) m transformed-input-patterns))

(defn split-matrix
  "Splits a matrix into submatrices."
  [m]
  (let [small-size (if (even? (count m)) 2 3)]
    ((reduce (fn [data row]
               {:new-matrix
                (if (zero? (mod (data :row) small-size))
                  (conj (data :new-matrix)
                        (map vector
                             (partition small-size (nth m (data :row)))))
                  (update (data :new-matrix)
                          (quot (data :row) small-size)
                          (fn [row-to-update]
                            (map #(conj %1 %2) row-to-update (partition small-size row)))))
                :row (inc (data :row))})
             {:new-matrix []
              :row 0}
             m)
     :new-matrix)))

(defn reassemble-matrix
  "Reassemble a split matrix."
  [m]
  (let [small-size (count (nth (iterate first m) 3))
        small-range (range small-size)]
    (mapcat (fn [squares]
              (map (partial apply concat)
                   (map (fn [small-size-i]
                          (map (fn [square]
                                 (nth square small-size-i))
                               squares))
                        small-range)))
            m)))

(defn generate-rules
  "Generates the complete list of rules from the input."
  [input]
  (reduce add-to-map
          {}
          (map (fn [[input-pattern output-pattern]]
                 [(all-transformations input-pattern) output-pattern])
               (map parse-line input))))

(defn enhance
  "Let's magnify it, see if we can get a reflection off her eye."
  [rules pixels]
  (reassemble-matrix
   (map (fn [squares]
          (map #(rules %) squares))
        (split-matrix pixels))))

(defn on-after-n-iterations
  "The number of pixels left on after n iterations."
  [input n]
  (generate-rules input)
  (count (filter #(= \# %)
                 (flatten
                  (nth (iterate (partial enhance (generate-rules input))
                                [[\. \# \.]
                                 [\. \. \#]
                                 [\# \# \#]])
                       n)))))

