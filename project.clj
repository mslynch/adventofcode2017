(defproject adventofcode2017 "1.0.0"
  :description "Solutions to Advent of Code 2017"
  :url "https://github.com/mslynch/adventofcode2017"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot adventofcode2017.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
