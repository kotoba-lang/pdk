# kotoba-lang/pdk

Zero-dep portable `.cljc` — restored from the legacy `kami-engine/kami-pdk`
Rust crate (deleted in kotoba-lang/kami-engine PR #82 "Remove Rust workspace
from kami-engine") as part of the **clj-wgsl migration** (ADR-2607010930,
`com-junkawasaki/root`).

Process Design Kit management: technology node definitions, Liberty timing
characterisation, LEF physical layout abstractions, standard cell
libraries, and a statistical memory compiler.

| Namespace | Restored from | Purpose |
|---|---|---|
| `pdk.technology` | `technology` | Technology node (N180..N2) + design-rule tech file (min-width/spacing, metal pitch) |
| `pdk.liberty` | `liberty` | Liberty (.lib) timing library model + simplified parser |
| `pdk.lef` | `lef` | LEF physical-abstract library model + simplified parser |
| `pdk.stdcell` | `stdcell` | Generic ~20-cell standard cell library generator |
| `pdk.memory` | `memory` | Statistical memory compiler (SRAM/ROM/RegFile area/timing/leakage estimation) |

Depends on `kotoba-lang/engineer` for shared contracts (constraint/DRC/etc).

## Status

Restored — all 5 modules ported from the original 895-line Rust `lib.rs`,
including the Liberty and LEF line/token-based parsers, with all 6 original
Rust unit tests mirrored 1:1 in `test/pdk_test.cljc` (+1 smoke test). Pure
data + pure functions/parsers throughout; no IO/GPU.

## Develop

```bash
clojure -M:test
```
