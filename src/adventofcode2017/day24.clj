(ns adventofcode2017.day24
  (:require [clojure.string :as str]))

(defn parse-input
  "Parses the input into a list of list of numbers."
  [input]
  (set (map (fn [line]
              (map #(Integer/parseInt %) (str/split line #"/")))
            input)))

(defn check-viability
  "Returns the viability of a component with a port."
  [check-port [port-a port-b]]
  (cond
    (= check-port port-a) :viable
    (= check-port port-b) :viable-reversed
    :else :unviable))

(defn find-viable-components
  "Finds the viable and unviable components; flips components that need flipping to connect."
  [current-bridge components]
  (let [components-by-viability
        (group-by #(check-viability (second (first current-bridge)) %) components)]
    {:viable (set (concat
                   (components-by-viability :viable)
                   (map reverse (components-by-viability :viable-reversed))))
     :unviable (components-by-viability :unviable)}))

(defn all-bridges-helper
  "Recursively calculates all possible bridges."
  [current-bridge components]
  (let [components-by-viability (find-viable-components current-bridge components)]
    (do
      (if (empty? (components-by-viability :viable))
        [current-bridge]
        (apply concat
               (map #(all-bridges-helper
                      (cons % current-bridge)
                      (disj (set (apply clojure.set/union
                                        (vals components-by-viability))) %))
                    (components-by-viability :viable)))))))

(defn all-bridges
  "Calculates all possible bridges."
  [components]
  (map (comp reverse butlast) (all-bridges-helper [[0 0]] components)))

(defn bridge-strength
  "Calculates the strength of a bridge."
  [bridge]
  (reduce + (flatten bridge)))

(defn best-bridge
  "Finds the best bridge possible from the given components using a measuring function."
  [measurer bridges]
  (apply max-key measurer bridges))

(defn best-bridge-strength
  "Calculates the strength of the best bridge."
  [input measurer]
  (->> input
       parse-input
       all-bridges
       (best-bridge measurer)
       bridge-strength))