# Unclassified Campaign Evidence

- **Arm:** `control`
- **Evaluator:** `0.4.4` at `c2051a95cd589e9c594a2648558ae938f04c1038`

## event-0031 — task #2, stage 30

- Kind: `stage_30_change_request`
- Detection gate: `null`
- Reason: Scope-like free text was present, but the stage-30 request had no recognized scope heading.
- Artifact: `phase1-task-20261003-004207-2.md:852`

```text
gh pr review 7 -R edburns/dd-3072973-cargotracker-control-01 --request-changes --body $'@copilot Please fix the following issue-requirement failures:\n\n## Scope violation: four disallowed Maven wrapper files\n\nThe issue requires a focused diff containing only these three paths:\n- demo/src/main/java/org/eclipse/cargotracker/application/BookingService.java\n- demo/src/main/java/org/eclipse/cargotracker/application/internal/DefaultBookingService.java\n- demo/src/test/java/org/eclipse/cargotracke
…
```

## event-0032 — task #2, stage 30

- Kind: `failed_ci_check`
- Detection gate: `null`
- Reason: The failing CI job was identified, but no failing step was captured.
- Artifact: `phase1-task-20261003-004207-2.md:1565`

```text
<details>
<summary>31 lines</summary>

```
APPROVED_RUNS=37084592117
37084592041
37084587690
37084215761
37084215765
37084212905
37084140774
37084140830
37084137905
37083191580
37083191644
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084592041/job/111092361320	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084587690/job/111092364067	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084592117/job/111092359072	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084592041/job/111092361320	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084587690/job/111092364067	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37084592117/job/111092359072	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

formatting	fail	21s	htt
…
```

## event-0001 — task #3, stage 30

- Kind: `failed_ci_check`
- Detection gate: `null`
- Reason: The failing CI job was identified, but no failing step was captured.
- Artifact: `phase1-task-20261003-012056-3.md:1468`

```text
<details>
<summary>18 lines</summary>

```
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955336/job/111096970207	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085952211/job/111096974675	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955354/job/111096971955	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955336/job/111096970207	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085952211/job/111096974675	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955354/job/111096971955	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

formatting	fail	18s	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955354/job/111096971955	
build	skipping	0	https://github.com/edburns/
…
```

## event-0003 — task #3, stage 30

- Kind: `failed_ci_check`
- Detection gate: `null`
- Reason: The failing CI job was identified, but no failing step was captured.
- Artifact: `phase1-task-20261003-012056-3.md:1899`

```text
<details>
<summary>6 lines</summary>

```
{"conclusion":"skipped","details_url":"https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085952211/job/111097059331","name":"build","status":"completed"}
{"conclusion":"skipped","details_url":"https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955354/job/111097035040","name":"build","status":"completed"}
{"conclusion":"failure","details_url":"https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085952211/job/111096974675","name":"formatting","status":"completed"}
{"conclusion":"failure","details_url":"https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955354/job/111096971955","name":"formatting","status":"completed"}
{"conclusion":null,"details_url":"https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37085955336/job/111096970207","name":"Shepherd task Cargo Tracker","status":"in_progress"}
<shellId: 11 completed with exit code 0>
```

</details>
```

## event-0013 — task #5, stage 30

- Kind: `failed_ci_check`
- Detection gate: `null`
- Reason: The failing CI job was identified, but no failing step was captured.
- Artifact: `phase1-task-20261003-130748-5.md:1194`

```text
<details>
<summary>22 lines</summary>

```
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126098036/job/111211794362	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126094326/job/111211799106	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126098042/job/111211795811	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

Shepherd task Cargo Tracker	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126098036/job/111211794362	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126094326/job/111211799106	
formatting	pending	0	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126098042/job/111211795811	
Refreshing checks status every 10 seconds. Press Ctrl+C to quit.

formatting	fail	22s	https://github.com/edburns/dd-3072973-cargotracker-control-01/actions/runs/37126094326/job/111211799106	
formatting	fail	21s	https://github.com/edbur
…
```

## event-0015 — task #5, stage 30

- Kind: `nonzero_tool_exit`
- Detection gate: `null`
- Reason: No deterministic product, operational, or infrastructure rule matched.
- Artifact: `phase1-task-20261003-130748-5.md:1625`

```text
```
curl: (7) Failed to connect to localhost port 9080 after 1 ms: Couldn't connect to server
<shellId: 13 completed with exit code 7>
```
```

## event-0009 — task #6, stage 30

- Kind: `nonzero_tool_exit`
- Detection gate: `null`
- Reason: No deterministic product, operational, or infrastructure rule matched.
- Artifact: `phase1-task-20261003-135748-6.md:1879`

```text
<details>
<summary>19 lines</summary>

```
browserType.launch: Target page, context or browser has been closed
Browser logs:

<launching> /home/edburns/.cache/ms-playwright/chromium_headless_shell-1193/chrome-linux/headless_shell --disable-field-trial-config --disable-background-networking --disable-background-timer-throttling --disable-backgrounding-occluded-windows --disable-back-forward-cache --disable-breakpad --disable-client-side-phishing-detection --disable-component-extensions-with-background-pages --disable-component-update --no-default-browser-check --disable-default-apps --disable-dev-shm-usage --disable-extensions --disable-features=AcceptCHFrame,AvoidUnnecessaryBeforeUnloadCheckSync,DestroyProfileOnBrowserClose,DialMediaRouteProvider,GlobalMediaControls,HttpsUpgrades,LensOverlay,MediaRouter,PaintHolding,ThirdPartyStoragePartitioning,Translate,AutoDeElevate --allow-pre-commit-input --disable-hang-monitor --disable-ipc-flooding-protection --disable-popup-blocking --disable-prompt-on-repost --disable-renderer-backgrounding --force-color-profile=srgb --metrics-recording-only --no-first-run --password-store=basic --use-mock-keychain --no-service-autorun --export-tagged-pdf 
…
```

