# kotoba-lang/pdk

Zero-dep portable `.cljc` — restored from the legacy `kami-engine/kami-pdk`
Rust crate (deleted in kotoba-lang/kami-engine PR #82 "Remove Rust workspace
from kami-engine") as part of the **clj-wgsl migration** (ADR-2607010930,
`com-junkawasaki/root`).

Process Design Kit management: technology node definitions, standard cell
libraries, and a statistical memory compiler.

| Namespace | Restored from | Purpose |
|---|---|---|
| `pdk.technology` | `technology` | Technology node (N180..N2) + design-rule tech file (min-width/spacing, metal pitch) |
| `pdk.stdcell` | `stdcell` | Generic ~20-cell standard cell library generator |
| `pdk.memory` | `memory` | Statistical memory compiler (SRAM/ROM/RegFile area/timing/leakage estimation) |

Depends on `kotoba-lang/engineer` for shared contracts (constraint/DRC/etc).

**Liberty and LEF split out 2026-07-07** (ADR-2607072500): these were
independent industry-standard formats bundled into `pdk` by the original
Rust crate, not pdk-specific logic (nothing in `pdk.stdcell`/`pdk.memory`/
`pdk.technology` referenced them). They now live in their own
`org-<standards-body>-<spec>` reverse-domain repos:
- `kotoba-lang/org-synopsys-liberty` — Liberty (.lib) timing library model + parser
- `kotoba-lang/org-si2-lef` — LEF physical-abstract library model + parser

## Status

Restored — 3 modules ported from the original 895-line Rust `lib.rs`
(Liberty/LEF modules extracted to their own repos, see above), with the
corresponding 4 of the original 6 Rust unit tests mirrored 1:1 in
`test/pdk_test.cljc` (+1 smoke test; the other 2 moved with Liberty/LEF).
Pure data + pure functions throughout; no IO/GPU.

## Develop

```bash
clojure -M:test
```
