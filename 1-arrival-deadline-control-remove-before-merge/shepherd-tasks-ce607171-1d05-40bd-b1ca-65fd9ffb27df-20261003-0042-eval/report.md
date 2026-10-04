# Shepherd Campaign Evaluation

- **Arm:** `control`
- **Campaign:** `ce607171-1d05-40bd-b1ca-65fd9ffb27df`
- **Evaluator:** `0.4.4` at `c2051a95cd589e9c594a2648558ae938f04c1038`
- **Evaluator worktree dirty:** false
- **Generated:** 2026-10-04T14:39:42.124787Z

## Headline findings

| Task | PR | Attempts/outcomes | First product-defect detection | Product defects | Nonzero exits | CCRA rounds | CCRA comments | Flaky tests |
|---:|---:|---|---|---:|---:|---:|---:|---:|
| 2 | 7 | 1:completed | formatting | 2 | 4 | 0 | 0 | 0 |
| 3 | 8 | 1:completed | formatting | 1 | 2 | 1 | 0 | 0 |
| 4 | 9 | 1:completed | ccra_review | 1 | 6 | 0 | 1 | 0 |
| 5 | 10 | 1:convergence_failure | stage_30_gate | 2 | 4 | 1 | 0 | 0 |
| 6 | 11 | 1:completed | none detected | 0 | 6 | 1 | 0 | 0 |

## Cost and timing

- Campaign active time: 13h 57m 55s
- Inter-directory gap: 0h 00m 00s
- First-start to last-end span: 13h 57m 55s (not campaign active time)
- Recorded session time: 2h 16m 29s
- JSONL exact session time: 8194462 ms (5462 ms above second-truncated Markdown headers)
- Orchestration overhead: 11h 41m 26s
- CCA wait proxy: 2 polls / 0h 04m 03s elapsed; 0h 06m 00s configured ceiling
- AIU: 880.55454
- Premium requests: 10

## Evidence and run invariants

- CI tests run: unavailable (`not_captured`)
- Partial output: 5014982 Unicode code points / 5015016 UTF-16 code units
- Skill content verification: `unverified`; telemetry hashes identify skill names, not content

## Acceptance checks

| Check | Status | Observed |
|---|---|---|
| maven_project_root_recorded | **pass** | `{"325b7e1443402ae351e113d0ff8ff4b2ef4f455b":"demo","3fd8e9de222d1913c2df11817cf126961658c454":"demo","b33f513b4a26c8de59e366ce5a48323f0a859235":"demo","79a76225caa48ea082eaed4002f2cdb204587b0e":"demo","b97cb071b3393b3a5a15bb9a85b8aa1c73f1863b":"demo","7f180401b8850a0dea0c236ef43a57bcfdbf37df":"demo","6075f5cfcc9d979446cc554c08ae8d6ab040a41c":"demo"}` |
| cross_repo_start_equivalence | **pass** | `` |
| ci_and_build_gates_classified | **pass** | `{"formatting":{"status":"present","entryCount":1},"build_contract":{"status":"not_present","entryCount":0},"security":{"status":"not_present","entryCount":0},"static_analysis":{"status":"not_present","entryCount":0},"compiler":{"status":"not_present","entryCount":0},"unit_tests":{"status":"not_present","entryCount":0},"container_tests":{"status":"not_present","entryCount":0},"ci_other":{"status":"present","entryCount":10}}` |
| product_defect_gate_and_class_non_null | **pass** | `true` |
| defect_class_subtype_consistent | **pass** | `true` |
| guardrail_failures_not_unclassified | **pass** | `[]` |
| ci_test_log_availability_semantics | **pass** | `{"availability":"not_captured","testsExecuted":null,"testsRun":null,"reason":"Only gh run view --log-failed output was captured; passing-run Surefire/Failsafe summaries were not captured."}` |
| combined_attempts_preserved | **pass** | `["/Users/edburns/workareas/dd-3072797-tricked-out-cargotracker-run-04/1-arrival-deadline-control-remove-before-merge/dd-3072973-cargotracker-control-01/1-arrival-deadline-control-remove-before-merge/shepherd-tasks-ce607171-1d05-40bd-b1ca-65fd9ffb27df-20261003-0042"]` |
| combined_active_time_separates_gap | **pass** | `{"arm":"control","campaignId":"ce607171-1d05-40bd-b1ca-65fd9ffb27df","campaignDirectory":"/Users/edburns/workareas/dd-3072797-tricked-out-cargotracker-run-04/1-arrival-deadline-control-remove-before-merge/dd-3072973-cargotracker-control-01/1-arrival-deadline-control-remove-before-merge/shepherd-tasks-ce607171-1d05-40bd-b1ca-65fd9ffb27df-20261003-0042","campaignDirectories":["/Users/edburns/workareas/dd-3072797-tricked-out-cargotracker-run-04/1-arrival-deadline-control-remove-before-merge/dd-3072
…` |

## CI gate inventory

| Gate | Status | Entries |
|---|---|---:|
| formatting | present | 1 |
| build_contract | not_present | 0 |
| security | not_present | 0 |
| static_analysis | not_present | 0 |
| compiler | not_present | 0 |
| unit_tests | not_present | 0 |
| container_tests | not_present | 0 |
| ci_other | present | 10 |

Named steps requiring `ci_other` review:

- `Build Cargo Tracker with Open Liberty`
- `Build with Maven`
- `Cache Maven packages`
- `Set up Java`

## Start equivalence and confounds

Cross-repository start comparison was not requested.

## Defect interpretation

See `defects.csv` for one row per deduplicated defect, including defect class, item count, local/CI location, timestamp source, ancestry-verified fix, and anomalies.

Style and static-analysis findings are detectable only where the corresponding gate exists; they are excluded from arm-comparison conclusions when either arm reports that gate as `not_present`.

## Post-mortem agent cost

- AIU: 83.11
- Premium requests: 1
- Tokens: unavailable

## Reference reconciliation

No built-in acceptance profile applies to this campaign.

## Trust assessment

- **Trustworthy:** manifest timing, session counts, transcript durations, exact JSONL durations, nonzero exit counts, JSONL AIU/premium/model/tool counts, JSONL partial-output cross-checks, run invariants, and cumulative OTEL tokens.
- **Approximate:** command-to-head correlation when a transcript does not emit a full SHA, agent-action labels, rule-based defect deduplication, and CCA wait as a latency proxy.
- **Manual review:** all entries in `unclassified.md`, confirmed evidence gaps, and remote CCA/CCRA internal cost because those internals are absent.

## Experiment interpretation

- Detection stage should be presented per defect and descriptively; a small number of product defects per run does not support significance claims.
- Local AIU and tokens include Shepherd waiting/polling activity; CCA wait is reported separately because remote CCA internals are unavailable.
- Enabling tests in the treatment is a disclosed intervention, not evaluator-detected control-arm tampering.
- If the treatment raises the Java release level, disclose that it also removes the JDK-25/source-7 operational failure mode.
- Test-tampering metrics are comparable only within an arm where tests run by default; the control arm is `not_meaningful`, so this is not a between-arm tampering comparison.

## Remaining unclassified

- `event-0031`: Scope-like free text was present, but the stage-30 request had no recognized scope heading.
- `event-0032`: The failing CI job was identified, but no failing step was captured.
- `event-0001`: The failing CI job was identified, but no failing step was captured.
- `event-0003`: The failing CI job was identified, but no failing step was captured.
- `event-0013`: The failing CI job was identified, but no failing step was captured.
- `event-0015`: No deterministic product, operational, or infrastructure rule matched.
- `event-0009`: No deterministic product, operational, or infrastructure rule matched.
