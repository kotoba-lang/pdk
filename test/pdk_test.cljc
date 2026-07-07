(ns pdk-test
  "Restoration-fidelity tests — one per original kami-pdk Rust test
  (kami-engine/kami-pdk/src/lib.rs `mod tests`, deleted PR #82)."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as str]
            [pdk]
            [pdk.technology :as tech]
            [pdk.stdcell :as stdcell]
            [pdk.memory :as memory]))

(deftest namespace-loads
  (testing "the restored CLJC namespace loads"
    (is (some? (the-ns 'pdk)))))

;; mirrors `tech_file_min_width`
(deftest tech-file-min-width
  (let [tf (tech/for-node :n7)
        w (tech/get-min-width tf "poly")]
    (is (some? w))
    (is (and (> w 0.0) (< w 1.0)))
    (is (some? (tech/metal-pitch tf 1)))))

;; mirrors `stdcell_library_generic`
(deftest stdcell-library-generic
  (let [lib (stdcell/create-generic-lib :n7)]
    (is (>= (count (:cells lib)) 20))
    (let [invs (stdcell/find-by-function lib :inv)]
      (is (>= (count invs) 3)))
    (let [sorted (stdcell/cells-sorted-by-area lib)]
      (doseq [[a b] (partition 2 1 sorted)]
        (is (<= (:area a) (:area b)))))))

;; mirrors `memory_compiler_sram`
(deftest memory-compiler-sram
  (let [spec {:mem-type :sram :words 1024 :bits 32 :mux 4 :banks 1}
        result (memory/compile-memory spec :n7)]
    (is (> (:area-um2 result) 0.0))
    (is (> (:read-time-ns result) 0.0))
    (is (> (:write-time-ns result) (* (:read-time-ns result) 0.9)))
    (is (str/starts-with? (:name result) "SRAM_1024x32"))
    (is (= 10 (count (filter #(str/starts-with? % "A[") (:pins result)))))))

;; mirrors `memory_compiler_regfile`
(deftest memory-compiler-regfile
  (let [spec {:mem-type :reg-file :words 32 :bits 64 :mux 1 :banks 1}
        result (memory/compile-memory spec :n5)]
    (is (> (:area-um2 result) 0.0))
    (is (str/starts-with? (:name result) "RF_32x64"))))
