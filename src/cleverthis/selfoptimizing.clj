(ns cleverthis.selfoptimizing)

(defn complex-function
  "This is a complex function that other code will be optimizing."
  [x y z]
  (cond
    (and x y z) true
    (and x y (not z)) false
    (and x (not y) z) false
    (and x (not y) (not z)) true
    (and (not x) y z) false
    (and (not x) y (not z)) true
    (and (not x) (not y) z) false
    (and (not x) (not y) (not z)) true))

(defn simple-function
  "This function will be replaced during coding."
  [x y z]
  true)

                                        ; The next function is controversial.

                                        ; My *goal* with this program is to demonstrate how easy it is for
                                        ; Clojure code to generate code and use the code that it
                                        ; generates.

                                        ; The code below will eventually generate all combinations of
                                        ; and, or, not, x, y, and z. This should be immediately obvious.

                                        ; This goal has tension with efficiency. It is also clear that
                                        ; the below code is NOT efficient:

                                        ; One-half of the generated sexps are one element long (just 'x,
                                        ; 'y, or 'z).

                                        ; It allows for repeats to show (although I removed many repeats.)

                                        ; It allows for mirror images to appear (for example, both '(and x y)
                                        ; and '(and y x) ).

                                        ; It allows for examples that are not in the base form (for example,
                                        ; '(not (not x)) or '(or z z) ).

                                        ; There are some programmers for whom this lack of optimization is
                                        ; intolerable. If you are among them, and if you must see this code
                                        ; optimized, please let me know. 

                                        ; But I don't feel that optimizing is a good use of my time: The underlying
                                        ; algorithm is *exponential* on the number of nodes.

                                        ; It would better serve any time to create a different underlying algorithm
                                        ; that isn't exponential, rather than try to polish this code that's
                                        ; fundamentally flawed.

                                        ; This algorithm is excellent at my goal -- to clearly demonstrate how easy Clojure
                                        ; makes it to generate and execute code. Anything more complex would
                                        ; obscure that goal.

(defn gen-sexp
  "Input: An integer.
   Output: A tuple:
      - A sexp that can use x, y, z, not, and, or.
      - The remaining value in i."
  [i]
  (case (mod i 6)
      0 (list 'x (quot i 6))
      1 (list 'y (quot i 6))
      2 (list 'z (quot i 6))
      3 (let [inner-sexp (gen-sexp (quot i 6))]
          (list (list 'not (first inner-sexp)) (second inner-sexp)))
      4 (let [inner-sexp1 (gen-sexp (quot i 6))
              inner-sexp2 (gen-sexp (second inner-sexp1))]
          (list (list 'and (first inner-sexp1) (first inner-sexp2)) (second inner-sexp2)))
      5 (let [inner-sexp1 (gen-sexp (quot i 6))
              inner-sexp2 (gen-sexp (second inner-sexp1))]
          (list (list 'or (first inner-sexp1) (first inner-sexp2)) (second inner-sexp2)))))

(defn int-to-clojure
  "Input: An integer.
   Output:
     - Clojure code that accepts 3 booleans and outputs 1 boolean.
     - Whether the code is a repeat."
  [i]
  (let [sexp (gen-sexp i)]
    (list (list 'defn 'simple-function '[x y z] (first sexp)) (= (second sexp) 0))))

(defn verify-function 
  "This is a very explicit method to check whether complex-function and simple-function
  give the same results."
  [function1 function2]
  (and
   (= (function1 true true true) (function2 true true true))
   (= (function1 true true false) (function2 true true false))
   (= (function1 true false true) (function2 true false true))
   (= (function1 true false false) (function2 true false false))
   (= (function1 false true true) (function2 false true true))
   (= (function1 false true false) (function2 false true false))
   (= (function1 false false true) (function2 false false true))
   (= (function1 false false false) (function2 false false false))))

(defn find-optimal-function 
  "Generate all possible combinations booleans and compare them to the desired function."
  [x-orig]
  (loop [x x-orig]
    (when (= (mod x 10000) 0) (print ".") (flush))  ; Show that we're doing something.
    (let [sf (int-to-clojure x)]
      (if (second sf)
        (do
          (eval (first sf))
          (if (verify-function complex-function simple-function)
            (do
              (println x)
              (first sf))
            (recur (+ x 1))))
        (recur (+ x 1))))))

  
   
         
         
