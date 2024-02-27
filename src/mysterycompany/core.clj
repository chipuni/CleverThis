(ns mysterycompany.core
  (:gen-class))
(require 'mysterycompany.selfoptimizing)
(refer 'mysterycompany.selfoptimizing)

(defn -main
  "Start the generator."
  [& args]
  (find-optimal-function 0))
