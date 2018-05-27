(defproject adventofcode2017 "1.0.0"
  :description "Solutions to Advent of Code 2017"
  :url "https://github.com/mslynch/adventofcode2017"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.7"]
                 [proto-repl "0.3.1"]]
  :main ^:skip-aot adventofcode2017.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-cljfmt "0.5.7"]
            [lein-kibit "0.1.6"]
            [lein-bikeshed "0.5.1"]
            [lein-ancient "0.6.15"]
            [jonase/eastwood "0.2.6"]
            [venantius/yagni "0.1.4"]])
