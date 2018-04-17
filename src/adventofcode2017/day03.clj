(ns adventofcode2017.day03)

(defn next-direction
  "The direction of the next square, given the current coordinate and direction."
  [[x y] direction]
  (cond
    (and (= 1 x) (zero? y)) :up
    (and (= x y)) (if (> x 0) :left :right)
    (and (< x 0) (= x (- 0 y))) :down
    (and (> x 0) (< y 0) (= (+ x y) 1)) :up
    :else direction))

(defn next-position
  "The next square, given a coordinate and direction."
  [[x y] direction]
  (cond
    (= direction :right) [(inc x) y]
    (= direction :up) [x (inc y)]
    (= direction :left) [(dec x) y]
    :else [x (dec y)]))

(defn spiral-seq
  "A lazy seq of coordinates in the spiral."
  [direction position]
  (let [new-position (next-position position direction)
        new-direction (next-direction new-position direction)]
    (lazy-seq (cons position
                    (spiral-seq new-direction new-position)))))

(defn distance
  "The distance from the given coordinate to the origin."
  [[x y]]
  (+ (Math/abs x)
     (Math/abs y)))

(defn spiral
  "The distance from the nth square to the origin."
  [n]
  (distance (nth (spiral-seq :right [0 0])
                 (dec n))))

(defn neighbors
  "A list of neighboring coordinates of the given coordinate."
  [[x y]]
  [[(dec x) (dec y)]
   [(dec x) y]
   [(dec x) (inc y)]
   [x (dec y)]
   [x (inc y)]
   [(inc x) (dec y)]
   [(inc x) y]
   [(inc x) (inc y)]])

(defn neighbor-sum
  "The sum of a square's neighbors."
  [position spiral-values]
  (->> (neighbors position)
       (map #(spiral-values % 0))
       (reduce +)))

(defn square-value
  "The value of a square."
  [position spiral-values]
  (let [sum (neighbor-sum position spiral-values)]
    (if (zero? sum) 1 sum)))

(defn spiral-values
  "A lazy seq of the values in the spiral (for part 2)."
  [position-values spiral-seq-remaining]
  (let [position (first spiral-seq-remaining)
        value (square-value position position-values)]
    (lazy-seq (cons value
                    (spiral-values
                     (assoc position-values position value)
                     (rest spiral-seq-remaining))))))

(defn first-larger-than
  "The first number in the spiral larger than the given number."
  [n]
  (first (filter #(> % n)
                 (spiral-values {} (spiral-seq :right [0 0])))))
