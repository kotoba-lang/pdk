(ns pdk-test
  (:require [clojure.test :refer [deftest is testing]]
            [pdk]))
(deftest namespace-loads
  (testing "the restored CLJC namespace loads"
    (is (some? pdk))))
