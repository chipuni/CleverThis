(ns cleverthis.core
  (:gen-class))
(require 'cleverthis.selfoptimizing)
(refer 'cleverthis.selfoptimizing)

(defn -main
  "Start the generator."
  [& args]
  (find-optimal-function 0))
