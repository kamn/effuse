(ns effuse.core-test
  (:require [clojure.test :refer :all]
            [effuse.core :refer :all]))


(defn simple-inc [n]
  (perform :inc n))

(deftest simple-1 
  (testing "Simple effect case"
    (let [effed-simple-inc (handle simple-inc {:inc inc})]
      (is 
        (=
          (effed-simple-inc 1)
          2)))))

(deftest simple-fail
  (testing "If there is no bounded value throw an error"
    (is 
      (thrown? Exception (simple-inc 1)))))

(defn lazy-stuff [xs]
  (map simple-inc xs))

(comment 
  (deftest simple-lazy-check
    (testing "Check forcing laziness works"
      (let [effed-lazy-stuff (handle lazy-stuff {:inc inc})]
        (is 
          (=
            (effed-lazy-stuff [1 2])
            `(2 3)))))))