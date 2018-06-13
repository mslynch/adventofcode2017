(ns adventofcode2017.day20
  (:require [clojure.string :as str]))

(defn parse-line
  "Splits a line into ((px py pz) (vx vy vz) (ax ay az))."
  [line]
  (partition 3
             (map #(Integer/parseInt %)
                  (str/split (str/join (remove #(contains? #{\p \v \a \= \< \> \space} %)
                                               line))
                             #","))))

(defn manhattan
  "The Manhattan distance of a point to another point."
  [p0 p1]
  (reduce + (map #(Math/abs (- %1 %2)) p0 p1)))

(defn min-position-helper
  "Updates data with the given particle if it is closer to the origin."
  [data [position velocity acceleration]]
  (let [test-distance (manhattan position [0 0 0])]
    (if (< test-distance (data :distance))
      {:particle [position velocity acceleration]
       :distance test-distance}
      data)))

(defn min-position-particle
  "Finds the particle with minimum position distance."
  [particles]
  (let [first-particle (first particles)]
    ((reduce min-position-helper
             {:particle first-particle
              :distance (manhattan (first first-particle) [0 0 0])}
             (rest particles))
     :particle)))

(defn next-pos
  "Calculates the next position."
  [[[px py pz] [vx vy vz] [ax ay az]]]
  [[(+ px vx ax) (+ py vy ay) (+ pz vz az)]
   [(+ vx ax) (+ vy ay) (+ vz az)]
   [ax ay az]])

(defn iterated-particles
  "Iterates particle movement n times."
  [particles n]
  (nth (iterate #(map next-pos %) particles) n))

(defn iterated-particles-collisions
  "Iterates particle movement n times."
  [particles n]
  (nth (iterate #(let [moved-particles (map next-pos %)]
                   (mapcat (fn [[_ v]]
                             v)
                           (filter (fn [[k v]]
                                     (= 1 (count v)))
                                   (group-by first moved-particles))))
                particles) n))

(defn closest-to-origin
  "The index of the closest particle to the origin."
  [input]
  (let [iterated (iterated-particles (map parse-line input) 1000)]
    (.indexOf iterated (min-position-particle iterated))))

(defn left-after-collisions
  "The number of particles lef after collisions."
  [input]
  (count (iterated-particles-collisions (map parse-line input) 1000)))
