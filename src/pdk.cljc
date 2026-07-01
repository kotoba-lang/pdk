(ns pdk
  "KAMI PDK — Process Design Kit management. Restored from the legacy
  kami-engine/kami-pdk Rust crate (deleted in kotoba-lang/kami-engine
  PR #82 'Remove Rust workspace from kami-engine') as part of the clj-wgsl
  migration (ADR-2607010930, com-junkawasaki/root).

  Technology node definitions, Liberty timing characterisation, LEF
  physical layout abstractions, standard cell libraries, and a statistical
  memory compiler for SRAM/ROM/RegFile estimation — one namespace per
  original Rust module:
    pdk.technology — technology node + design-rule tech file
    pdk.liberty    — Liberty (.lib) timing library model + simplified parser
    pdk.lef        — LEF physical-abstract library model + simplified parser
    pdk.stdcell    — generic ~20-cell standard cell library generator
    pdk.memory     — statistical memory compiler (area/timing/leakage estimation)

  Zero-dep portable CLJC — pure data + pure functions/parsers, no IO/GPU.
  Depends on kotoba-lang/engineer for shared contracts.")
