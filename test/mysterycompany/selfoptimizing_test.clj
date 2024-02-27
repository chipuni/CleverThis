(ns mysterycompany.selfoptimizing-test
  (:require [clojure.test :refer :all]
            [mysterycompany.selfoptimizing :refer :all]))

(deftest complex-function-test
  (testing "Check that the complex function hasn't changed."
    (is (complex-function true true true))
    (is (not (complex-function true true false)))
    (is (not (complex-function true false true)))))

(deftest gen-sexp-test
  (testing "Verifying some simple checks for gen-sexp"
    (is (= (gen-sexp 0) '(x 0)))
    (is (= (gen-sexp 1) '(y 0)))
    (is (= (gen-sexp 2) '(z 0)))
    (is (= (gen-sexp 3) '((not x) 0)))
    (is (= (gen-sexp 4) '((and x x) 0)))
    (is (= (gen-sexp 5) '((or x x) 0)))))

(deftest int-to-clojure-test
  (testing "A fast check of the output of int-to-clojure"
    (is (= (int-to-clojure 0) '((defn simple-function [x y z] x) true)))))

(defn best-function [x y z]
 	(or 
         (not 
          (or z 
              (and y x))) 
         (and 
          (and z y)
          x)))

(defn simplest-function [x y z]
  true)

(deftest verify-function-test
  (testing "Ensure that complex-function and best-function are the same."
    (is (verify-function complex-function best-function))
    (is (not (verify-function complex-function simplest-function)))))



