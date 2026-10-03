# Copilot CLI Session

> [!NOTE]
> - **Session ID:** `bbb384ec-12a9-4000-bde3-ed0f7ae720a3`  
> - **Started:** 10/3/2026, 12:37:40 AM  
> - **Duration:** 2m 29s  
> - **Exported:** 10/3/2026, 12:40:10 AM  

---

<sub>4s</sub>

### User

Invoke skill `shepherd-task-20-create-issues-from-plan` with these inputs:

- CAMPAIGN_ID: ce607171-1d05-40bd-b1ca-65fd9ffb27df
- LESSON_PROPAGATION: off
- REPO: edburns/dd-3072973-cargotracker-control-01
- BASE_BRANCH: experiment/shepherd-control
- PARENT_ISSUE: 1
- PLAN_DIRECTORY: 1-arrival-deadline-control-remove-before-merge
- PLAN_FILE_NAME: add-change-arrival-deadline-feature-ignorance-reduction-plan.md
- QUESTIONS_SECTION: ## Phase 3 — Ignorance reduction: questions to answer before writing code
- IMPLEMENTATION_SECTION: ## Phase 4 — Implementation (five serial issues)
- EXPECTED_TASK_COUNT: 5
- BASE_REMOTE: origin
- LOG_DIRECTORY: /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037
- DRAFT_VALIDATOR: /home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh
- ISSUE_BODY_VERIFIER: /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-github-issue-body.sh
- CHILD_LINK_VERIFIER: /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh

Fixture pagination response contract (mandatory):

- `gh api ... --paginate --slurp` returns a JSON array of page payloads, so a
  one-page response has the shape `[[{...}]]`, not `[{...}]`.
- Before indexing child issue fields such as `.id`, normalize the response to
  one flat issue array exactly once.
- In Bash, use:
  `jq 'if length == 0 then [] elif all(.[]; type == "array") then add else . end'`.
- In PowerShell, capture the `gh` output and `$LASTEXITCODE` first, then pass
  the complete JSON through the same `jq` normalization before
  `ConvertFrom-Json`.
- Use the normalized flat array for the pre-creation baseline, final child
  count/order checks, and failure reconciliation. Do not apply `add` a second
  time to an already-flat array.

---

<sub>5s</sub>

### `skill`

**shepherd-task-20-create-issues-from-plan**

<details>
<summary>Arguments</summary>

```json
{
  "skill": "shepherd-task-20-create-issues-from-plan"
}
```

</details>

```
Skill "shepherd-task-20-create-issues-from-plan" loaded successfully. Follow the instructions in the skill context.
```


---

<sub>9s</sub>

### Copilot

I’m validating the campaign inputs, plan structure, repository state, and required tooling before any GitHub mutation.

---

<sub>11s</sub>

### `sql`

**Track stage twenty work**

<details>
<summary>Arguments</summary>

```json
{
  "description": "Track stage twenty work",
  "query": "INSERT INTO todos (id, title, description, status) VALUES ('validate-invocation', 'Validating campaign invocation', 'Verify repository, branch, parent issue, scripts, campaign manifest, plan headings, resolutions, task count, and issue type support.', 'in_progress'), ('build-drafts', 'Drafting child issue specifications', 'Study examples and plan resources, build traceability, and persist five complete issue bodies.', 'pending'), ('create-link-issues', 'Creating and linking child issues', 'Validate all drafts, initialize durable ledger/result files, create and verify each issue, then link in order.', 'pending'), ('verify-postconditions', 'Verifying stage postconditions', 'Persist final children, run authoritative child-link verifier, reverify issue bodies/open/unassigned/type, and complete result document.', 'pending'); INSERT INTO todo_deps (todo_id, depends_on) VALUES ('build-drafts','validate-invocation'), ('create-link-issues','build-drafts'), ('verify-postconditions','create-link-issues')"
}
```

</details>

<details>
<summary>5 lines</summary>

```
Executed 2 statement(s):

1. INSERT
   4 row(s) inserted. Last inserted row ID: 4.

2. INSERT
   3 row(s) inserted. Last inserted row ID: 3.
```

</details>


---

<sub>19s</sub>

### `bash`

**Validate repository and GitHub inputs**

$ set -o pipefail
printf '%s\n' '--- git status ---'
git status --short --branch
printf '%s\n' '--- remotes ---'
git remote -v
printf '%s\n' '--- branches ---'
git branch --list 'experiment/shepherd-control'
git ls-remote --heads origin experiment/shepherd-control
printf '%s\n' '--- default branch ---'
gh api repos/edburns/dd-3072973-cargotracker-control-01 --jq '{default_branch,owner}'
printf '%s\n' '--- parent ---'
gh api repos/edburns/dd-3072973-cargotracker-control-01/issues/1 --jq '{number,state,repository_url,title}'
printf '%s\n' '--- issue types ---'
owner_type=$(gh api repos/edburns/dd-3072973-cargotracker-control-01 --jq '.owner.type')
if [ "$owner_type" = Organization ]; then gh api orgs/edburns/issue-types; else printf '{"owner_type":"%s","issue_types_unavailable":true}\n' "$owner_type"; fi

<details>
<summary>18 lines</summary>

```
--- git status ---
## experiment/shepherd-control...origin/experiment/shepherd-control
?? 1-arrival-deadline-control-remove-before-merge/.stage20-after.4173647
?? 1-arrival-deadline-control-remove-before-merge/.stage20-before.4173647
?? 1-arrival-deadline-control-remove-before-merge/prompts/
--- remotes ---
origin	git@github.com:edburns/dd-3072973-cargotracker-control-01.git (fetch)
origin	git@github.com:edburns/dd-3072973-cargotracker-control-01.git (push)
--- branches ---
* experiment/shepherd-control
325b7e1443402ae351e113d0ff8ff4b2ef4f455b	refs/heads/experiment/shepherd-control
--- default branch ---
{"default_branch":"edburns/dd-3016202-cargotracker-devoxx-be-2026-control","owner":{"avatar_url":"https://avatars.githubusercontent.com/u/75821?v=4","events_url":"https://api.github.com/users/edburns/events{/privacy}","followers_url":"https://api.github.com/users/edburns/followers","following_url":"https://api.github.com/users/edburns/following{/other_user}","gists_url":"https://api.github.com/users/edburns/gists{/gist_id}","gravatar_id":"","html_url":"https://github.com/edburns","id":75821,"login":"edburns","node_id":"MDQ6VXNlcjc1ODIx","organizations_url":"https://api.github.com/users/edburns/orgs","received_events_url":"https://api.github.com/users/edburns/received_events","repos_url":"https://api.github.com/users/edburns/repos","site_admin":true,"starred_url":"https://api.github.com/users/edburns/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/edburns/subscriptions","type":"User","url":"https://api.github.com/users/edburns","user_view_type":"public"}}
--- parent ---
{"number":1,"repository_url":"https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01","state":"open","title":"[Campaign][off] Cargo Tracker arrival-deadline control"}
--- issue types ---
{"owner_type":"User","issue_types_unavailable":true}
<shellId: 0 completed with exit code 0>
```

</details>


---

<sub>19s</sub>

### `bash`

**Validate log directory and scripts**

$ set -o pipefail
log='/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037'
printf '%s\n' '--- log dir ---'
[ -d "$log" ] && [ -w "$log" ] && printf 'writable\n' || { printf 'missing-or-not-writable\n'; exit 1; }
mkdir -p "$log/issue-bodies"
printf '%s\n' '--- scripts ---'
for f in /home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-github-issue-body.sh /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh; do if [ -x "$f" ]; then printf 'executable %s\n' "$f"; else printf 'invalid %s\n' "$f"; exit 1; fi; done

<details>
<summary>7 lines</summary>

```
--- log dir ---
writable
--- scripts ---
executable /home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh
executable /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-github-issue-body.sh
executable /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh
<shellId: 2 completed with exit code 0>
```

</details>


---

<sub>19s</sub>

### `bash`

**Read plan and campaign resources**

$ set -o pipefail
git show 'experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md'
printf '\n--- CAMPAIGN MANIFEST ---\n'
git show 'experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/shepherd-campaign.json'
printf '\n--- LESSON FILE EXISTS ---\n'
git cat-file -e 'experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/campaign-lessons.md' && printf 'yes\n'
printf '\n--- DIRECTORY TREE ---\n'
git ls-tree -r --name-only experiment/shepherd-control -- '1-arrival-deadline-control-remove-before-merge'

<details>
<summary>12 lines</summary>

```
Output too large to read at once (35.5 KB). Saved to: /tmp/1790987878650-copilot-tool-output-4173777-0950daee-2f07-4ff0-aff9-bf1d1185e407.txt
Consider using tools like rg (for searching), head/tail (for viewing start/end), view with view_range (for specific sections), or jq (for JSON) to examine portions of the output.

Preview (first 500 chars):
# Implementation plan: Change Arrival Deadline Date (`eclipse-ee4j/cargotracker#64`)

Human DRI: Ed Burns
Starting commit: `634f3ca4787c84fd652cdf5c883c37f1098e0c61` (`Remove prompts from control brancH`)
Working directory: repository root of the current campaign worktree
Runtime baseline: Java 17, Java EE 7 (`javax.*`), Open Liberty 26.0.0.8, PrimeFaces 8.0
Baseline run instructions: `demo/README.md`
Baseline preparation: fixed source branch and immutable SHA validated by the campaign fixture
H
<shellId: 1 completed with exit code 0>
```

</details>


---

<sub>19s</sub>

### `view`

**view the file at /home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/01-1682-java-tool-ergonomics.md.**

/home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/01-1682-java-tool-ergonomics.md (lines 1-400)

<details>
<summary>15 lines</summary>

```
# Example 01: 1682-java-tool-ergonomics-prompts-remove-before-merge

Read `copilot-sdk/1682-java-tool-ergonomics-prompts-remove-before-merge/dd-3018003-ignorance-reduction-for-implementation-plan.md` (the plan) to get the context.

✅✅Pay very close attention to each **Resolution:** to the questions in `Phase 3 — Ignorance reduction: questions to answer before writing code`.

In this session, we are creating issues of type **Task** as children of Epic https://github.com/github/copilot-sdk/issues/1682 . These issues correspond to the sub-sections in `## Phase 4 — Implementation (the build order)`.

I want you to use `gh` to create issues as sub-issues of 1682.

- I will dispatch these issues by assigning them to an agent. Leave them unassigned for now.

- The description you put in the issues you create is the specification for the work the agent assigned to the issue must do. Therefore, you need to be very specific and targeted when writing the description.

- Set the type to **Task**.

- Each issue will be worked on and resolved in serial. You can assume:
  - All issues will operate in this topic branch of `upstream`: `edburns/1682-java-tool-ergonomics`.

  - The assignee agent has access to the topic branch and, most importantly, to the prompts in the directory `1682-java-tool-ergonomics-prompts-remove-before-merge`, which includes the plan. Therefore, you can instruct the agent to access the plan by reference, you don't need to reference it by value directly in the issue.

- The sub-sections of `## Phase 4 — Implementation (the build order)` have essential details for each corresponding **Task**.

- Instruct the agent to read the entire plan before working.

- Instruct the agent to carefully re-read the relevant sections of the plan. ❌❌You can't just say, "read the relevant sections of the plan". ✅✅In the description of the issue, list exactly what sections of the plan apply to this particular issue.
- Though the sub-sections of `## Phase 4 — Implementation (the build order)` have essential details for each corresponding **Task**, I want you to carefully consider if there are additional gating tests you can add so that as the agent proceeds from one task to the next, the likelihood of rework is reduced.
```

</details>


---

<sub>19s</sub>

### `view`

**view the file at /home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/02-1810-java-tool-as-lambda.md.**

/home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/02-1810-java-tool-as-lambda.md (lines 1-400)

<details>
<summary>12 lines</summary>

```
# Example 02: 1810-java-tool-ergonomics-tool-as-lambda-remove-before-merge

Carefully read the plan `copilot-sdk/1810-java-tool-ergonomics-tool-as-lambda-remove-before-merge/1810-ignorance-reduction-for-implementation-plan.md`.

We have completed Phases 1 - 3. We are now on Phase 4.

For each sub item in Phase 4, file a child issue of Feature #1810.

Use `gh` to look at Epic #1682 and its child issues for the standard of verbosity and concreteness for issue descriptions.

The issue should be assignable to a coding agent and there must be sufficient detail in the description for the agent to proceed to completion.

Pull out the specific relevant content from Phase 3 as it applies to this current issue in Phase 4.

Tell the agent to take `edburns/1810-java-tool-ergonomics-tool-as-lambda` as the base branch. When I assign the issue to the coding agent, I will set this as the base branch.

Tell the agent to read `1810-java-tool-ergonomics-tool-as-lambda-remove-before-merge/1810-ignorance-reduction-for-implementation-plan.md`, which is present on the branch, for context.

Tell the agent which sub-item within Phase 4 they are solving.

Tell the agent that each issue will be assigned, resolved and merged to the base branch in the order in which they appear in the plan.

No work is to start until I assign each issue to the coding agent, in turn.
```

</details>


---

<sub>19s</sub>

### `view`

**view the file at /home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/03-dd-3017826-java-real-estate-demo.md.**

/home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/03-dd-3017826-java-real-estate-demo.md (lines 1-400)

<details>
<summary>14 lines</summary>

```
# Example 03: dd-3017826-java-real-estate-demo-remove-before-merge

Read `dd-3017826-java-real-estate-demo-remove-before-merge\dd-3017826-java-real-estate-demo-ignorance-reduction-plan.md` (the plan) to get the context.

✅✅Pay very close attention to each **Resolution:** to the questions in `Phase 2 — Ignorance reduction: questions to answer before writing code`.

In this session, we are creating sub-issues as children of https://github.com/edburns/Build26-BRK206-your-agent-anywhere-multiclient-multidevice-with-github-copilot-sdk/issues/2 . These issues correspond to the sub-sections in `## Phase 3 — Implementation (build order)`.

I want you to use `gh` to create issues as sub-issues of #2.

- I will dispatch these issues by assigning them to an agent. Leave them unassigned for now.

- The description you put in the issues you create is the specification for the work the agent assigned to the issue must do. Therefore, you need to be very specific and targeted when writing the description.

- Each issue will be worked on and resolved in serial. You can assume:
  - All issues will operate in this topic branch of `origin`: `edburns/2-build-out-demo`.

  - The assignee agent has access to the topic branch and, most importantly, to the prompts in the directory `dd-3017826-java-real-estate-demo-remove-before-merge`, which includes the plan. Therefore, you can instruct the agent to access the plan by reference, you don't need to reference it by value directly in the issue.

- The sub-sections of `## Phase 3 — Implementation (build order)` have essential details for each corresponding sub-issue.

- Instruct the agent to read the entire plan before working.

- Instruct the agent to carefully re-read the relevant sections of the plan. ❌❌You can't just say, "read the relevant sections of the plan". ✅✅In the description of the issue, list exactly what sections of the plan apply to this particular issue.
- Though the sub-sections of `## Phase 3 — Implementation (build order)` have essential details for each corresponding sub-issue, I want you to carefully consider if there are additional gating tests you can add so that as the agent proceeds from one task to the next, the likelihood of rework is reduced.
```

</details>


---

<sub>19s</sub>

### `view`

**view the file at /home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/04-28-python-agent-demo.md.**

/home/edburns/.copilot/skills/shepherd-task-20-create-issues-from-plan/examples/04-28-python-agent-demo.md (lines 1-400)

<details>
<summary>16 lines</summary>

```
# Example 04: 28-python-agent-demo-remove-before-merge

Read `28-python-agent-demo-remove-before-merge/28-python-agent-demo-ignorance-reduction-plan.md` (the plan) to get the context.

✅✅Pay very close attention to each **Resolution:** to the questions in `Phase 2 — Ignorance reduction: questions to answer before writing code`. These pertain to the implementation tasks in Phase 3. ✅✅✅ Include relevant spike findings as self-contained prose in the issue description; do not direct the agent to spike directories or source files.

In this session, we are creating sub-issues as children of https://github.com/edburns/Build26-BRK206-your-agent-anywhere-multiclient-multidevice-with-github-copilot-sdk/issues/28 . These issues correspond to the sub-sections in `## Phase 3 — Implementation (build order)`.

I want you to use `gh` to create issues as sub-issues of #28

- I will dispatch these issues by assigning them to an agent. Leave them unassigned for now.

- The description you put in the issues you create is the specification for the work the agent assigned to the issue must do. Therefore, you need to be very specific and targeted when writing the description.

- Again, ✅✅For each **Resolution:** to the questions in `Phase 2 — Ignorance reduction: questions to answer before writing code`, include the relevant Resolution values in the issue description. These pertain to the implementation tasks in Phase 3. ✅✅✅ State relevant spike findings directly in the issue body with enough detail to implement them without reading spike code.

- For tasks dealing with the UI, make sure to refer to the relevant screengrabs, but only ones within `28-python-agent-demo-remove-before-merge/`.

- Each issue will be worked on and resolved in serial. You can assume:
  - All issues will operate in this topic branch of `origin`: `edburns/28-python-agent-demo`.

  - The assignee agent has access to the topic branch and, most importantly, to the prompts in the directory `28-python-agent-demo-remove-before-merge/`, which includes the plan. Therefore, you can instruct the agent to access the plan by reference, you don't need to reference it by value directly in the issue.

- The sub-sections of `## Phase 3 — Implementation (build order)` have essential details for each corresponding sub-issue.

- Instruct the agent to read the entire plan before working.

- Instruct the agent to carefully re-read the relevant sections of the plan. ❌❌You can't just say, "read the relevant sections of the plan". ✅✅In the description of the issue, list exactly what sections of the plan apply to this particular issue.
- Though the sub-sections of `## Phase 3 — Implementation (build order)` have essential details for each corresponding sub-issue, I want you to carefully consider if there are additional gating tests you can add so that as the agent proceeds from one task to the next, the likelihood of rework is reduced.
```

</details>


---

<sub>22s</sub>

### Copilot

Validation passed so far: the parent is open, the topic branch exists and is not default, the remote matches, required scripts are executable, and this user-owned repository has no organization issue type support, so children will be created **without an issue type**. I’m now extracting every resolution, task constraint, and research finding and freezing the normalized child baseline.

---

<sub>28s</sub>

### `rg`

**^(##|###|####) |\*\*Resolution:\*\*|^Resolution:**

"^(##|###|####) |\*\*Resolution:\*\*|^Resolution:" (1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md)

<details>
<summary>36 matches</summary>

```
[grep content: 34 matches across 1 file(s) under 1-arrival-deadline-control-remove-before-merge]

add-change-arrival-deadline-feature-ignorance-reduction-plan.md (34 match(es)):
  33: ## Goal
  46: ### User-visible acceptance behavior
  73: ### Domain acceptance behavior
  88: ### Hard scope constraints
  107: ## Completed phases
  109: ### Phase 1 ✅ — Establish a runnable feature-absent baseline
  121: ### Phase 2 ✅ — Verify the before and after user experience
  135: ## Phase 3 — Ignorance reduction: questions to answer before writing code
  141: ### 3.1 — Which cargos expose the edit operation?
  167: **Resolution:**
  174: ### 3.2 — What is the exact domain mutation?
  213: **Resolution:**
  223: ### 3.3 — What should happen to an existing itinerary and delivery state?
  249: **Resolution:**
  257: ### 3.4 — What type crosses the facade boundary?
  285: **Resolution:**
  293: ### 3.5 — How is the DTO's formatted deadline converted for editing?
  323: **Resolution:**
  333: ### 3.6 — Which JSF bean scopes and interaction pattern should be used?
  363: **Resolution:**
  372: ### 3.7 — What is the dynamic-dialog contract?
  413: **Resolution:**
  424: ### 3.8 — What date validation is required?
  442: **Resolution:**
  450: ### 3.9 — How will the feature be tested on the prepared historical baseline?
  488: **Resolution:**
  502: ## Phase 4 — Implementation (five serial issues)
  507: ### 4.1 — Issue 1: Add the application-layer deadline change operation
  575: ### 4.2 — Issue 2: Expose deadline changes through the booking facade
  630: ### 4.3 — Issue 3: Implement the deadline editor backing model
  709: ### 4.4 — Issue 4: Implement the PrimeFaces deadline dialog
  806: ### 4.5 — Issue 5: Integrate deadline editing into the Administration dashboard
  890: ## Phase 5 — Documentation and implementation handoff
  909: ## Cross-cutting concerns
```

</details>


---

<sub>28s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md (lines 1-220)

<details>
<summary>173 lines</summary>

````
# Implementation plan: Change Arrival Deadline Date (`eclipse-ee4j/cargotracker#64`)

Human DRI: Ed Burns
Starting commit: `634f3ca4787c84fd652cdf5c883c37f1098e0c61` (`Remove prompts from control brancH`)
Working directory: repository root of the current campaign worktree
Runtime baseline: Java 17, Java EE 7 (`javax.*`), Open Liberty 26.0.0.8, PrimeFaces 8.0
Baseline run instructions: `demo/README.md`
Baseline preparation: fixed source branch and immutable SHA validated by the campaign fixture
Historical issue: `eclipse-ee4j/cargotracker#64`

Control-fixture provenance: the five-task plan and acceptance requirements are
adapted from `cargotracker-add-change-arrival-deadline-feature` at tag
`shepherd-task-v1.0.4`. The source branch is
`edburns/dd-3016202-cargotracker-devoxx-be-2026-control`.
Paths below are repository-relative; run each `cd demo && ./mvnw ...` command
from the campaign worktree root. Campaign metadata stays at the root.
Completed-phase observations below are inherited historical context, not
evidence from a new run of this pinned baseline. Verify the baseline during
the run and report its actual behavior. Section 3.9 records the baseline's
different test configuration without importing the experiment's test/CI gates.

Related directories and files:

- `demo/src/main/java/org/eclipse/cargotracker/application/`
- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/facade/`
- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/`
- `demo/src/main/webapp/admin/dialogs/`
- `demo/src/main/webapp/admin/tables/listNotRouted.xhtml`
- `demo/src/test/java/org/eclipse/cargotracker/application/BookingServiceTest.java`

---

## Goal

Add an Administration dashboard operation that lets a shipping administrator
change the arrival deadline of a cargo listed in the **Not Routed Cargo** table.
The operation must preserve Cargo Tracker's layered architecture:

1. The application service owns the domain mutation.
2. The booking facade shields the web layer from domain types.
3. A JSF backing bean loads and submits the editable date.
4. A PrimeFaces dynamic dialog presents the editor.
5. The existing Not Routed Cargo table opens the dialog and refreshes after a
   successful update.

### User-visible acceptance behavior

Using the stable sample cargo `DEF789`:

1. Start the application with Java 17:

   ```bash
   cd demo && ./mvnw clean package -Popenliberty liberty:run
   ```

2. Open `http://localhost:8080/cargo-tracker/`.
3. Select **Administration**.
4. Find `DEF789` in the **Not Routed Cargo** table.
5. The Deadline cell displays its date together with an edit icon.
6. Hovering over the deadline displays:
   `Click to change cargo arrival deadline date.`
7. Selecting the deadline opens a modal dialog titled **Change Deadline**.
8. The dialog displays the cargo's origin and destination as read-only
   context.
9. The date editor is initialized to the cargo's current arrival deadline.
10. Selecting a different date and pressing **Update** closes the dialog and
    refreshes the Administration view.
11. The new date is shown in the Not Routed Cargo table.
12. Reloading the page continues to show the new date for the lifetime of the
    running in-memory sample application.
13. Pressing **Cancel** closes the dialog without changing the deadline.

### Domain acceptance behavior

Changing the deadline must:

- locate the cargo by `TrackingId`;
- preserve its existing origin;
- preserve its existing destination;
- replace only the arrival deadline in its `RouteSpecification`;
- apply the specification through `Cargo.specifyNewRoute(...)`;
- preserve the currently assigned itinerary rather than silently discarding
  it;
- allow the domain model to recalculate routing status and delivery-derived
  values against the new route specification;
- persist the changed cargo through `CargoRepository.store(...)`.

### Hard scope constraints

- Begin from commit `634f3ca4787c84fd652cdf5c883c37f1098e0c61`.
- Preserve Java EE 7 and the `javax.*` namespace.
- Preserve the pinned POM's `maven.compiler.release=17`; do not restore the
  tagged fixture's historical Java 7 source/target configuration.
- Run the application on JDK 17 using the existing Open Liberty profile.
- Do not migrate the application to Jakarta EE 8+, Jakarta EE 9+, Spring, or a
  different UI framework.
- Do not replace the in-memory Derby configuration or the Open Liberty runtime.
- Do not redesign unrelated cargo booking, routing, destination editing,
  messaging, batch, REST, or persistence behavior.
- Do not copy commits or files from feature-bearing branches. This plan is the
  implementation specification.
- Implement the five build issues below in order. Each issue must be complete
  and gated before the next issue begins.

---

## Completed phases

### Phase 1 ✅ — Establish a runnable feature-absent baseline

- Commit `634f3ca4787c84fd652cdf5c883c37f1098e0c61` is based on the historical
  feature-absent commit and contains only the compatibility work needed to run
  the sample on JDK 17 and Open Liberty.
- `cd demo && ./mvnw clean package -Popenliberty liberty:run` starts the application.
- The home page and Administration flows return HTTP 200.
- JSF view metadata is placed at `UIViewRoot` scope for MyFaces compatibility.
- The internal routing REST client works without a Jersey/MOXy classloading
  conflict.
- The scheduled batch job has the local authorization it needs.

### Phase 2 ✅ — Verify the before and after user experience

- Before implementation, `DEF789` appears in the Not Routed Cargo table with a
  plain-text deadline and no edit operation.
- The neighboring Destination column demonstrates the existing PrimeFaces
  dynamic-dialog interaction pattern.
- The desired after behavior has been manually exercised: open the deadline
  editor, choose a new date, update, refresh the table, and observe the
  persisted value.
- The historical architectural boundaries and affected files have been
  identified.

---

## Phase 3 — Ignorance reduction: questions to answer before writing code

Resolve these questions before production implementation begins. The
recommendations intentionally define the desired design closely enough that an
implementing agent should not need to invent a different architecture.

### 3.1 — Which cargos expose the edit operation?

**Question:** Should deadline editing be exposed for all cargos or only for
cargos displayed in the Not Routed Cargo table?

The requested feature originates in the Administration dashboard's Not Routed
Cargo table. Other tables represent routed, misrouted, claimed, or otherwise
progressed cargo. Adding the affordance to every table would expand the feature
and require additional business rules about changing deadlines after handling
has begun.

| Option | UI scope | Trade-off |
|--------|----------|-----------|
| A | Not Routed Cargo table only | Matches the requested feature and the established destination-edit affordance. |
| B | Every Administration cargo table | Broader capability, but introduces lifecycle and authorization questions outside the request. |
| C | Cargo details page only | Avoids table complexity but does not meet the requested dashboard interaction. |

The application-service operation itself does not need to encode a UI-table
restriction. It should accept a tracking ID and apply the domain mutation to
the located cargo. The presentation layer determines where the operation is
offered.

**Recommendation:** Option A. Add the edit affordance only to
`demo/src/main/webapp/admin/tables/listNotRouted.xhtml`. Keep the application
operation generally usable for a valid cargo.

**Resolution:**

Select Option A. Expose the edit affordance only in
`demo/src/main/webapp/admin/tables/listNotRouted.xhtml`. The application and facade
operations remain generally callable for any cargo that can be found by
tracking ID; they do not encode knowledge of dashboard table membership.

### 3.2 — What is the exact domain mutation?

**Question:** Should the feature mutate the existing `RouteSpecification`, add
a setter to `Cargo`, or replace the specification using the existing domain
operation?

`RouteSpecification` is a value object describing origin, destination, and
arrival deadline. The existing `changeDestination(...)` implementation already
establishes the correct pattern: create a replacement specification, call
`Cargo.specifyNewRoute(...)`, and store the aggregate.

Proposed application-service shape:

```java
void changeDeadline(TrackingId trackingId, Date deadline);
```

Proposed implementation:

```java
Cargo cargo = cargoRepository.find(trackingId);
RouteSpecification specification = new RouteSpecification(
        cargo.getOrigin(),
        cargo.getRouteSpecification().getDestination(),
        deadline);

cargo.specifyNewRoute(specification);
cargoRepository.store(cargo);
```

Calling `specifyNewRoute(...)` is significant. It lets the aggregate recalculate
delivery and routing status relative to the new specification. Direct field
mutation or a persistence-only update would bypass that behavior.

**Recommendation:** Replace the `RouteSpecification` through
`Cargo.specifyNewRoute(...)`. Preserve origin, destination, and itinerary.
Persist using the existing repository. Do not add a deadline setter to the
domain model.

**Resolution:**

Use the same aggregate-update pattern as `changeDestination(...)`. Add
`BookingService.changeDeadline(TrackingId, Date)` and implement it by loading
the cargo, constructing a new `RouteSpecification` from the existing origin,
existing destination, and supplied deadline, calling
`cargo.specifyNewRoute(...)`, and storing the cargo through
`cargoRepository.store(...)`. Do not add mutable deadline setters to the domain
````

</details>


---

<sub>28s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md (lines 221-440)

<details>
<summary>163 lines</summary>

````
objects.

### 3.3 — What should happen to an existing itinerary and delivery state?

**Question:** When a routed cargo's deadline changes, should its itinerary be
cleared, retained, or recomputed?

Although the UI initially exposes the feature only for unrouted cargo, the
application operation should have deterministic domain behavior if invoked for
a routed cargo. The existing `changeDestination(...)` behavior preserves the
assigned itinerary and lets `Cargo.specifyNewRoute(...)` recalculate whether
that itinerary still satisfies the new specification.

The core application test should deliberately invoke the operation after:

1. booking a cargo;
2. requesting route candidates;
3. assigning an itinerary;
4. changing its destination;
5. changing its deadline.

This sequence verifies that the feature uses the aggregate correctly rather
than assuming the cargo always has an empty itinerary.

**Recommendation:** Preserve the itinerary. Let the domain model recompute
routing and delivery-derived state. Assert all unaffected fields explicitly in
`BookingServiceTest`.

**Resolution:**

Retain the existing itinerary. Do not clear, replace, or reroute it as part of
the deadline change. `Cargo.specifyNewRoute(...)` recalculates the delivery
snapshot and routing status against the replacement specification. In the
established sequential application test, the assigned itinerary remains
unchanged and the cargo remains `MISROUTED` after the deadline changes.

### 3.4 — What type crosses the facade boundary?

**Question:** Should the booking facade accept a `Date`, a formatted string, or
a newly introduced request DTO?

The existing facade already uses `java.util.Date` for
`bookNewCargo(...)`. Introducing another representation for this one operation
would create unnecessary conversion code and depart from the historical
application style.

Proposed facade shape:

```java
void changeDeadline(String trackingId, Date arrivalDeadline);
```

The implementation converts only the identifier:

```java
bookingService.changeDeadline(
        new TrackingId(trackingId),
        arrivalDeadline);
```

**Recommendation:** Use `String` for the tracking ID and `java.util.Date` for
the deadline. Do not expose `TrackingId`, `Cargo`, or `RouteSpecification` to
the JSF layer and do not introduce a new DTO solely for this command.

**Resolution:**

Add `void changeDeadline(String trackingId, Date arrivalDeadline)` to
`BookingServiceFacade`. `DefaultBookingServiceFacade` converts the string to
`new TrackingId(trackingId)` and passes the same `Date` to
`BookingService.changeDeadline(...)`. No new command DTO or formatted-string
service parameter is introduced.

### 3.5 — How is the DTO's formatted deadline converted for editing?

**Question:** `CargoRoute` exposes its deadline as formatted strings, while
`p:datePicker` binds naturally to `java.util.Date`. How should the backing bean
initialize the editor?

At the starting commit:

- `CargoRoute.getArrivalDeadline()` returns
  `MM/dd/yyyy hh:mm a z`.
- `CargoRoute.getArrivalDeadlineDate()` returns only the date component.
- The table displays `getArrivalDeadlineDate()`.

Options:

| Option | Approach | Trade-off |
|--------|----------|-----------|
| A | Parse `cargo.getArrivalDeadlineDate()` with `MM/dd/yyyy` | Small, localized change; preserves the existing DTO contract. |
| B | Add a `Date` property to `CargoRoute` | Cleaner typing, but broadens a DTO used throughout the application. |
| C | Reload the domain object in the backing bean | Violates the facade boundary. |

The formatter/parser must be created per operation or per view bean; do not add
a shared mutable `SimpleDateFormat`.

**Recommendation:** Option A. Load `CargoRoute` through
`BookingServiceFacade.loadCargoForRouting(trackingId)` and parse
`cargo.getArrivalDeadlineDate()` using `new SimpleDateFormat("MM/dd/yyyy")`.
Surface an explicit failure if the existing DTO value cannot be parsed; do not
silently submit a null date.

**Resolution:**

Use Option A and keep date conversion inside the view-scoped editor bean. The
existing implementation loads the `CargoRoute`, creates
`new SimpleDateFormat("MM/dd/yyyy")`, and parses the leading date portion of
`cargo.getArrivalDeadline()`. Because that value begins with `MM/dd/yyyy`,
`SimpleDateFormat.parse(...)` obtains the same date that
`getArrivalDeadlineDate()` displays. A per-load formatter is used, so no shared
mutable formatter is added.

### 3.6 — Which JSF bean scopes and interaction pattern should be used?

**Question:** Should deadline editing introduce a new navigation page, use an
inline editor, or mirror the existing Change Destination dynamic-dialog
pattern?

The baseline already contains:

- `ChangeDestination`, a CDI `@Named` and JSF `@ViewScoped` editor bean;
- `ChangeDestinationDialog`, a session-scoped JSF managed bean that opens and
  closes a PrimeFaces dynamic dialog;
- `changeDestination.xhtml`, a dialog view;
- a `dialogReturn` Ajax listener that refreshes `tableNotRouted`.

Using the same pattern minimizes changes and provides a consistent user
experience.

Proposed bean names:

```text
changeArrivalDeadlineDate
changeArrivalDeadlineDateDialog
```

**Recommendation:** Add a serializable CDI `@Named @ViewScoped`
`ChangeArrivalDeadlineDate` editor and a serializable
`@ManagedBean(name = "changeArrivalDeadlineDateDialog") @SessionScoped`
launcher. Mirror the existing destination-dialog lifecycle rather than
introducing a new navigation or inline-edit framework.

**Resolution:**

Mirror the existing Change Destination interaction. Implement
`ChangeArrivalDeadlineDate` as a serializable CDI `@Named @ViewScoped` bean and
`ChangeArrivalDeadlineDateDialog` as a serializable
`@ManagedBean(name = "changeArrivalDeadlineDateDialog") @SessionScoped` bean.
Use a PrimeFaces dynamic dialog rather than navigation to a full page or inline
cell editing.

### 3.7 — What is the dynamic-dialog contract?

**Question:** What path, request parameters, dimensions, and close result should
the PrimeFaces dialog use?

The launcher needs one parameter, `trackingId`, supplied as a
`Map<String, List<String>>`. The dialog metadata binds the parameter and invokes
the editor bean's `load()` action.

Proposed launcher contract:

```java
PrimeFaces.current().dialog().openDynamic(
        "/admin/dialogs/changeArrivalDeadlineDate.xhtml",
        options,
        params);
```

Required options:

| Option | Value |
|--------|-------|
| `modal` | `true` |
| `draggable` | `true` |
| `resizable` | `false` |
| `contentWidth` | `410` |
| `contentHeight` | `280` |

Required completion behavior:

- successful update: `closeDynamic("DONE")`;
- cancel: `closeDynamic("")`;
- caller listens for `dialogReturn` and updates `tableNotRouted`.

Because Open Liberty uses MyFaces, `<f:metadata>` must be a direct child of the
view root, before `<h:head>` and `<h:body>`. It must not be nested inside
`<h:body>`.

**Recommendation:** Use the contract above and preserve the metadata placement
required by the prepared baseline.

**Resolution:**

Open `/admin/dialogs/changeArrivalDeadlineDate.xhtml` with a single
`trackingId` request parameter and these options: modal and draggable are
`true`, resizable is `false`, content width is `410`, and content height is
`280`. Successful submission closes with `"DONE"`; cancellation closes with
the empty string. The caller handles `dialogReturn` and updates
`tableNotRouted`. Place the dialog's `<f:metadata>` directly under the root
`<html>` element, before `<h:head>` and `<h:body>`, so the known MyFaces
`UIViewRoot` requirement is satisfied.

### 3.8 — What date validation is required?

**Question:** Must the new deadline be non-null, in the future, after the
current date, or after itinerary completion?

The requested feature is an administrative correction to an existing arrival
deadline. No new domain policy about future dates is part of the request.
Inventing such a rule could reject dates accepted by existing cargo booking or
`RouteSpecification` behavior.

The UI must nevertheless prevent a null submission because the operation
requires a concrete replacement deadline.

**Recommendation:** Require a date value in the JSF form and display a normal
Faces validation message when it is absent. Do not add a new minimum-date,
future-date, or itinerary-date business rule. Continue to rely on the existing
domain model for its established invariants.
````

</details>


---

<sub>28s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md (lines 441-700)

<details>
<summary>190 lines</summary>

````

**Resolution:**

Require a non-null date selection, but add no new chronological business rule.
In particular, do not require the replacement deadline to be after today,
after the old deadline, or after every itinerary leg. Pass the selected
`java.util.Date` to the existing domain construction path and let the current
`RouteSpecification` invariants apply.

### 3.9 — How will the feature be tested on the prepared historical baseline?

**Question:** Which automated and runtime tests are mandatory on this pinned
control baseline?

The tagged fixture described an older POM with `skipTests=true` and a remote
Payara-only Arquillian suite. This baseline's `demo/pom.xml` instead configures
the Open Liberty managed Arquillian adapter and selects `openliberty` for
Surefire; it does not declare `skipTests=true`. These are configuration facts,
not a claim that this baseline's tests have passed. Modernizing the integration
test runtime remains outside this feature's scope.

The feature still needs layered evidence:

1. Extend `BookingServiceTest` with the domain/application assertions that
   specify the deadline mutation.
2. Ensure all test sources compile as part of
   `cd demo && ./mvnw clean package -Popenliberty`.
3. Add focused JUnit tests for facade and backing-bean delegation where they
   can run without a container, using hand-written fakes rather than adding a
   mocking framework.
4. Perform mandatory end-to-end verification against the running Open Liberty
   application.
5. Preserve the baseline's existing test configuration, including the Payara
   profile and Open Liberty adapter; do not delete, disable, or rewrite tests
   to manufacture a passing result.

**Spike needed:** Before Issue 1 implementation, run the starting commit's
standard Open Liberty package command and record which tests actually run,
their results, and any skips or failures. Confirm the new `BookingServiceTest` method can be added without
expanding the runtime modernization scope.

**Recommendation:** Treat the JDK 17/Open Liberty build plus HTTP/UI acceptance
as the mandatory executable gate. Keep the Arquillian test as a precise
application-layer specification and let the pinned profile execute its
configured tests. Do not assume a fixed test count or add test/CI machinery
from the experiment arm.

**Resolution:**

Extend the existing sequential Arquillian `BookingServiceTest` with
`testChangeDeadline()` after `testChangeDestination()`. The test changes the
deadline by one month, reloads the cargo through JPA, and asserts the complete
set of preserved and recalculated domain state described above. Record actual
test execution rather than assuming the tagged fixture's skipped-test behavior.
The mandatory executable gates remain the
JDK 17 Open Liberty package/start command, direct HTTP checks, and the complete
`DEF789` browser acceptance flow. No Arquillian-runtime modernization or new
mocking dependency is part of this feature.

---

## Phase 4 — Implementation (five serial issues)

Implement these issues in order. Each issue should be a separate commit. Do not
start an issue until the previous issue's gating criteria are satisfied.

### 4.1 — Issue 1: Add the application-layer deadline change operation

**What to build**

Add the core use case to the application layer. This issue must contain no JSF
or PrimeFaces changes.

**Files to modify**

- `demo/src/main/java/org/eclipse/cargotracker/application/BookingService.java`
- `demo/src/main/java/org/eclipse/cargotracker/application/internal/DefaultBookingService.java`
- `demo/src/test/java/org/eclipse/cargotracker/application/BookingServiceTest.java`

**Required API**

```java
void changeDeadline(TrackingId trackingId, Date deadline);
```

**Required implementation behavior**

1. Load the cargo using `cargoRepository.find(trackingId)`.
2. Obtain the current destination from
   `cargo.getRouteSpecification().getDestination()`.
3. Construct a replacement `RouteSpecification` from:
   - `cargo.getOrigin()`;
   - the current destination;
   - the new deadline.
4. Apply it using `cargo.specifyNewRoute(routeSpecification)`.
5. Persist using `cargoRepository.store(cargo)`.
6. Log the tracking ID and new deadline at `Level.INFO`, following the style of
   `changeDestination(...)`.

Do not:

- add a setter to `Cargo` or `RouteSpecification`;
- modify the origin or destination;
- clear or replace the itinerary directly;
- update persistence entities behind the aggregate's back.

**Tests to write first**

Append a sequential `testChangeDeadline()` case to `BookingServiceTest` after
`testChangeDestination()`. Build a new deadline one month after the test's
original `deadline`, invoke the service, reload the cargo with
`Cargo.findByTrackingId`, and assert:

- origin remains Chicago;
- destination remains Helsinki;
- stored deadline is the same calendar day as the requested new deadline;
- assigned itinerary remains unchanged;
- transport status remains `NOT_RECEIVED`;
- last known location remains `Location.UNKNOWN`;
- current voyage remains `Voyage.NONE`;
- cargo is not marked misdirected;
- estimated time of arrival is `Delivery.ETA_UNKOWN`;
- next expected activity is `Delivery.NO_ACTIVITY`;
- cargo is not unloaded at destination;
- routing status reflects the domain model's recalculation and remains
  `MISROUTED` for the established test sequence.

**Gating criteria**

- The test source compiles.
- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17.
- No web, facade, REST, Liberty, or persistence configuration files change in
  this issue.

### 4.2 — Issue 2: Expose deadline changes through the booking facade

**What to build**

Expose the use case to presentation clients without leaking domain identifier
types into the web layer.

**Files to modify**

- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/facade/BookingServiceFacade.java`
- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/facade/internal/DefaultBookingServiceFacade.java`

**Optional focused test file**

- `demo/src/test/java/org/eclipse/cargotracker/interfaces/booking/facade/internal/DefaultBookingServiceFacadeTest.java`

**Required API**

```java
void changeDeadline(String trackingId, Date arrivalDeadline);
```

**Required implementation**

```java
bookingService.changeDeadline(
        new TrackingId(trackingId),
        arrivalDeadline);
```

The facade must not:

- load and mutate `Cargo` itself;
- call `CargoRepository.store(...)`;
- parse a formatted date;
- introduce JSF or PrimeFaces types.

**Tests**

Where a container-free test is added, use a hand-written `BookingService` fake
or spy and prove that:

- the same `Date` object/value reaches the application service;
- the tracking-ID string is converted to an equivalent `TrackingId`;
- the facade delegates exactly once;
- no repository work is duplicated in the facade.

Do not add Mockito or another dependency solely for this test.

**Gating criteria**

- Existing facade consumers still compile.
- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17.
- The application-layer test added in Issue 1 remains unchanged and compiling.

### 4.3 — Issue 3: Implement the deadline editor backing model

**What to build**

Add the view-scoped backing bean that loads a cargo's current deadline and
submits a replacement deadline through the booking facade. Do not add the
dialog launcher or XHTML in this issue.

**File to create**

- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/ChangeArrivalDeadlineDate.java`

**Required bean shape**

```java
@Named
@ViewScoped
public class ChangeArrivalDeadlineDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String trackingId;
    private CargoRoute cargo;
    private Date arrivalDeadlineDate;

    @Inject
    private BookingServiceFacade bookingServiceFacade;
}
```

Required properties and methods:

- `getTrackingId()` / `setTrackingId(String)`
- `getCargo()`
- `getArrivalDeadlineDate()` / `setArrivalDeadlineDate(Date)`
- `load()`
- `changeArrivalDeadline()`

**Load behavior**

1. Call `bookingServiceFacade.loadCargoForRouting(trackingId)`.
2. Store the returned `CargoRoute`.
3. Parse `cargo.getArrivalDeadlineDate()` using `MM/dd/yyyy`.
4. Store the resulting `Date` in `arrivalDeadlineDate`.
5. Do not query a repository or domain object directly.
6. Do not ignore a parsing failure or merely print its stack trace. Surface a
   clear application/view error consistent with existing JSF behavior.

**Submit behavior**

1. Refuse a null date through JSF validation or explicit bean validation.
2. Call
   `bookingServiceFacade.changeDeadline(trackingId, arrivalDeadlineDate)`.
3. Close the dynamic dialog with:

   ```java
   PrimeFaces.current().dialog().closeDynamic("DONE");
   ```

4. Do not close the dialog if the facade call fails.

**Tests to write**

Add a container-free JUnit test if practical, using a hand-written fake facade,
that proves:

- `load()` requests the correct tracking ID;
- `load()` converts an `MM/dd/yyyy` DTO date into the editable `Date`;
- `changeArrivalDeadline()` delegates the selected date and tracking ID;
- a malformed DTO deadline is surfaced rather than converted to null;
- a null selected date is rejected.
````

</details>


---

<sub>28s</sub>

### `glob`

**1-arrival-deadline-control-remove-before-merge/**/***

"1-arrival-deadline-control-remove-before-merge/**/*"

<details>
<summary>10 matches</summary>

```
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/.stage20-after.4173647
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/shepherd-campaign.json
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/shepherd-test-experiment.json
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/.stage20-before.4173647
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/campaign-lessons.md
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/create-issues-otel-20261003-0037.jsonl
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/20261003-0037-invoke-shepherd-task-20-create-issues-from-plan-skill.sh
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/create-issues-session-20261003-0037.jsonl
/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/20261003-0037-invoke-shepherd-task-20-create-issues-from-plan-skill.md
```

</details>


---

<sub>28s</sub>

### `bash`

**Persist normalized child baseline**

$ set -euo pipefail
repo='edburns/dd-3072973-cargotracker-control-01'
parent='1'
log='/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037'
raw=$(gh api "repos/$repo/issues/$parent/sub_issues" --paginate --slurp)
normalized=$(printf '%s' "$raw" | jq 'if length == 0 then [] elif all(.[]; type == "array") then add else . end')
tmp="$log/.pre-creation-children.json.tmp"
printf '%s\n' "$normalized" > "$tmp"
mv "$tmp" "$log/pre-creation-children.json"
printf '%s\n' "$normalized" | jq '[.[] | {id,number,title}]'

```
[]
<shellId: 3 completed with exit code 0>
```


---

<sub>28s</sub>

### `bash`

**Verify remote branch and manifest**

$ set -euo pipefail
remote_url=$(git remote get-url origin)
case "$remote_url" in git@github.com:edburns/dd-3072973-cargotracker-control-01.git|https://github.com/edburns/dd-3072973-cargotracker-control-01.git|https://github.com/edburns/dd-3072973-cargotracker-control-01) printf 'remote-match\n';; *) printf 'remote-mismatch: %s\n' "$remote_url"; exit 1;; esac
printf 'local=%s\nremote=%s\n' "$(git rev-parse experiment/shepherd-control)" "$(git ls-remote origin refs/heads/experiment/shepherd-control | cut -f1)"
printf 'plan-headings: questions=%s implementation=%s\n' "$(git show experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md | grep -Fxc '## Phase 3 — Ignorance reduction: questions to answer before writing code')" "$(git show experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md | grep -Fxc '## Phase 4 — Implementation (five serial issues)')"
git show experiment/shepherd-control:1-arrival-deadline-control-remove-before-merge/shepherd-campaign.json | jq -e '.campaignId == "ce607171-1d05-40bd-b1ca-65fd9ffb27df" and .lessonPropagation == "off"' >/dev/null
printf 'campaign-match\n'

<details>
<summary>6 lines</summary>

```
remote-match
local=325b7e1443402ae351e113d0ff8ff4b2ef4f455b
remote=325b7e1443402ae351e113d0ff8ff4b2ef4f455b
plan-headings: questions=1 implementation=1
campaign-match
<shellId: 4 completed with exit code 0>
```

</details>


---

<sub>34s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/add-change-arrival-deadline-feature-ignorance-reduction-plan.md (lines 630-950)

<details>
<summary>217 lines</summary>

````
### 4.3 — Issue 3: Implement the deadline editor backing model

**What to build**

Add the view-scoped backing bean that loads a cargo's current deadline and
submits a replacement deadline through the booking facade. Do not add the
dialog launcher or XHTML in this issue.

**File to create**

- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/ChangeArrivalDeadlineDate.java`

**Required bean shape**

```java
@Named
@ViewScoped
public class ChangeArrivalDeadlineDate implements Serializable {
    private static final long serialVersionUID = 1L;

    private String trackingId;
    private CargoRoute cargo;
    private Date arrivalDeadlineDate;

    @Inject
    private BookingServiceFacade bookingServiceFacade;
}
```

Required properties and methods:

- `getTrackingId()` / `setTrackingId(String)`
- `getCargo()`
- `getArrivalDeadlineDate()` / `setArrivalDeadlineDate(Date)`
- `load()`
- `changeArrivalDeadline()`

**Load behavior**

1. Call `bookingServiceFacade.loadCargoForRouting(trackingId)`.
2. Store the returned `CargoRoute`.
3. Parse `cargo.getArrivalDeadlineDate()` using `MM/dd/yyyy`.
4. Store the resulting `Date` in `arrivalDeadlineDate`.
5. Do not query a repository or domain object directly.
6. Do not ignore a parsing failure or merely print its stack trace. Surface a
   clear application/view error consistent with existing JSF behavior.

**Submit behavior**

1. Refuse a null date through JSF validation or explicit bean validation.
2. Call
   `bookingServiceFacade.changeDeadline(trackingId, arrivalDeadlineDate)`.
3. Close the dynamic dialog with:

   ```java
   PrimeFaces.current().dialog().closeDynamic("DONE");
   ```

4. Do not close the dialog if the facade call fails.

**Tests to write**

Add a container-free JUnit test if practical, using a hand-written fake facade,
that proves:

- `load()` requests the correct tracking ID;
- `load()` converts an `MM/dd/yyyy` DTO date into the editable `Date`;
- `changeArrivalDeadline()` delegates the selected date and tracking ID;
- a malformed DTO deadline is surfaced rather than converted to null;
- a null selected date is rejected.

Do not add a mocking framework solely for these tests.

**Gating criteria**

- The bean is serializable and uses the established CDI/JSF annotations.
- The bean references only facade DTOs, not domain model classes.
- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17.

### 4.4 — Issue 4: Implement the PrimeFaces deadline dialog

**What to build**

Add the session-scoped dialog launcher and the dynamic dialog view. The dialog
must work when addressed directly with a `trackingId` query parameter, but it
is not yet linked from the dashboard in this issue.

**Files to create**

- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/ChangeArrivalDeadlineDateDialog.java`
- `demo/src/main/webapp/admin/dialogs/changeArrivalDeadlineDate.xhtml`

**Launcher requirements**

Use:

```java
@ManagedBean(name = "changeArrivalDeadlineDateDialog")
@SessionScoped
```

Implement:

- `showDialog(String trackingId)`
- `handleReturn(SelectEvent event)`
- `cancel()`

`showDialog(...)` must:

- set the options documented in Question 3.7;
- pass `trackingId` as a dynamic-dialog request parameter;
- open `/admin/dialogs/changeArrivalDeadlineDate.xhtml`.

`cancel()` must close the dialog without invoking the facade.

**XHTML requirements**

The page title must be:

```xhtml
<title>Change Deadline</title>
```

Place metadata directly beneath the root `<html>` element and before
`<h:head>`:

```xhtml
<f:metadata>
    <f:viewParam name="trackingId"
                 value="#{changeArrivalDeadlineDate.trackingId}"/>
    <f:viewAction action="#{changeArrivalDeadlineDate.load}"/>
</f:metadata>
```

The form must display:

- `Origin:` and `changeArrivalDeadlineDate.cargo.originName`;
- `Destination:` and
  `changeArrivalDeadlineDate.cargo.finalDestinationName`;
- `Deadline:` and a `p:datePicker` bound to
  `changeArrivalDeadlineDate.arrivalDeadlineDate`;
- **Cancel**, invoking
  `changeArrivalDeadlineDateDialog.cancel()`;
- **Update**, invoking
  `changeArrivalDeadlineDate.changeArrivalDeadline()`.

The date picker must require a value. The Update action must reload or refresh
the calling Administration view after a successful dialog close, following the
existing destination-dialog behavior.

**Runtime tests**

With the application running, request:

```text
http://localhost:8080/cargo-tracker/admin/dialogs/changeArrivalDeadlineDate.xhtml?trackingId=DEF789
```

Verify:

- HTTP 200;
- title is **Change Deadline**;
- origin and destination render;
- the existing deadline is selected;
- no `TagException`, `Parent UIComponent`, `FacesException`, or server error is
  present;
- Cancel does not change the persisted deadline;
- Update changes the deadline.

**Gating criteria**

- `cd demo && ./mvnw clean package -Popenliberty liberty:run` succeeds on JDK 17.
- Direct dialog loading and both actions work.
- Destination editing continues to work.
- Stop Liberty cleanly before completing the issue.

### 4.5 — Issue 5: Integrate deadline editing into the Administration dashboard

**What to build**

Replace the plain deadline text in the Not Routed Cargo table with the
PrimeFaces command-link affordance that opens the completed dialog and refreshes
the table after return.

**File to modify**

- `demo/src/main/webapp/admin/tables/listNotRouted.xhtml`

**Required UI shape**

Within the existing Deadline column, add a `p:commandLink` that:

- calls
  `changeArrivalDeadlineDateDialog.showDialog(cargoNotRouted.trackingId)`;
- retains the displayed
  `cargoNotRouted.arrivalDeadlineDate`;
- adds the existing Font Awesome edit icon style;
- uses a stable component ID such as `arrivalDeadlineToUpdate`;
- listens for `dialogReturn`;
- invokes
  `changeArrivalDeadlineDateDialog.handleReturn`;
- updates `tableNotRouted`;
- provides the tooltip:
  `Click to change cargo arrival deadline date.`

Follow the adjacent Destination column's established structure and styling. Do
not alter tracking-ID routing or destination editing.

**End-to-end acceptance test**

1. Start from a clean build on JDK 17:

   ```bash
   cd demo && ./mvnw clean package -Popenliberty liberty:run
   ```

2. Confirm the home page returns HTTP 200.
3. Open Administration and locate `DEF789`.
4. Record the original deadline.
5. Confirm the deadline now has an edit icon and tooltip.
6. Open the deadline dialog.
7. Confirm origin and destination identify the same cargo.
8. Choose a visibly different date.
9. Press **Update**.
10. Confirm the dialog closes and the Not Routed Cargo table refreshes.
11. Confirm the table shows the selected date.
12. Reload the browser and confirm the selected date remains.
13. Reopen the dialog and confirm the editor initializes to the changed date.
14. Press **Cancel** and confirm no additional change occurs.
15. Verify the Destination edit dialog still opens.
16. Verify selecting `DEF789` for routing still loads without an error page.

**Log acceptance**

The final run must contain none of:

- `<f:metadata> Parent UIComponent`;
- `TagException`;
- `VerifyError`;
- `FacesException`;
- `CWWKZ0002E` or `CWWKZ0003E`;
- recurring batch authorization failures;
- new FFDC files attributable to this feature.

Transient JMS activation-order warnings are acceptable only if all message
endpoints subsequently activate, as established by the prepared baseline.

**Final regression and scope checks**

- `cd demo && ./mvnw clean package -Popenliberty` succeeds.
- The existing test sources and the new deadline test compile.
- No Java EE namespace migration occurred.
- No Open Liberty, Derby, Jackson, JSF metadata, batch authorization, or REST
  compatibility fix from the starting commit was reverted.
- The feature affects only the intended application, facade, web, dialog,
  table, and test surfaces.
- Stop Liberty cleanly.

---

## Phase 5 — Documentation and implementation handoff

- Update `demo/README.md` only if user-facing Administration capabilities are
  enumerated there; add one concise sentence that administrators can change an
  unrouted cargo's arrival deadline.
- Record the exact JDK 17 run command in the final issue or pull-request
  description:

  ```bash
  cd demo && ./mvnw clean package -Popenliberty liberty:run
  ```

- Include `DEF789` and the before/after deadline values in the acceptance
  evidence.
- State explicitly that data is in-memory and resets when the application is
  rebuilt/restarted.

---

## Cross-cutting concerns

| Concern | Required handling |
|---------|--------------------|
| Domain-driven design | Mutate the aggregate through `Cargo.specifyNewRoute(...)`; do not bypass it with persistence-level field updates. |
| Layering | Web bean → booking facade → booking service → cargo repository. |
| Date handling | Use `java.util.Date` at service/facade boundaries and `MM/dd/yyyy` for the date-only editor representation. |
| Time zones | Acceptance compares the calendar date displayed by the application; do not introduce a new timezone policy in this feature. |
| Error handling | Do not silently swallow parse, lookup, validation, or persistence failures. A failed update must not look successful. |
| JSF compatibility | Keep `<f:metadata>` at view-root scope for MyFaces. |
| Bean lifecycle | Editor is CDI `@ViewScoped`; dynamic-dialog launcher mirrors the existing session-scoped JSF managed bean. |
| Accessibility | Preserve visible labels; the date editor must have an associated label and validation feedback. |
| Backward compatibility | Existing destination editing, routing, tracking, REST, messaging, batch, and startup behavior must remain intact. |
| Test discipline | Add tests before production code where practical; every issue must preserve all prior gates. |
| Experiment integrity | Implement from this specification starting at `634f3ca4787c84fd652cdf5c883c37f1098e0c61`; do not cherry-pick or inspect feature-bearing commits. |
````

</details>


---

<sub>34s</sub>

### `view`

**view the file at /home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh.**

/home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh (lines 1-300)

<details>
<summary>65 lines</summary>

```
#!/usr/bin/env bash
# shepherd-task-version: 1.0.4

set -euo pipefail

[[ $# -eq 3 ]] || {
    echo "Usage: $0 <body-directory> <expected-count> <off|campaign>" >&2
    exit 1
}

body_directory="$1"
expected_count="$2"
lesson_propagation="$3"

[[ -d "$body_directory" ]] || {
    echo "Stage-20 body directory does not exist: $body_directory" >&2
    exit 1
}
[[ "$expected_count" =~ ^[1-9][0-9]*$ ]] || {
    echo "Expected count must be a positive integer." >&2
    exit 1
}
[[ "$lesson_propagation" == "off" || "$lesson_propagation" == "campaign" ]] || {
    echo "Lesson propagation must be off or campaign." >&2
    exit 1
}

body_files=()
for body_file in "$body_directory"/*-body.md; do
    [[ -f "$body_file" ]] || continue
    [[ "$body_file" != *-observed-body.md ]] || continue
    body_files+=("$body_file")
done
[[ ${#body_files[@]} -eq $expected_count ]] || {
    echo "Expected $expected_count persisted stage-20 body files; found ${#body_files[@]}." >&2
    exit 1
}

required_headings=(
    '## Campaign context and required reading'
    '## Branch and execution order'
    '## Implement'
    '## Completion gates'
    '## Out of scope'
)

for body_file in "${body_files[@]}"; do
    [[ -s "$body_file" && "$(awk 'END { print NR }' "$body_file")" -gt 1 ]] || {
        echo "Persisted issue body must contain physical Markdown lines: $body_file" >&2
        exit 1
    }
    first_nonblank="$(awk 'NF { print; exit }' "$body_file")"
    [[ "$first_nonblank" =~ ^##[[:space:]]+[^[:space:]] ]] || {
        echo "Persisted issue body must begin with a level-two heading: $body_file" >&2
        exit 1
    }
    for heading in "${required_headings[@]}"; do
        grep -Fqx -- "$heading" "$body_file" || {
            echo "Persisted issue body is missing the physical heading '$heading': $body_file" >&2
            exit 1
        }
    done
    if [[ "$lesson_propagation" == "campaign" ]]; then
        grep -Fqx -- '## Campaign lessons (REQUIRED)' "$body_file" || {
            echo "Treatment issue body is missing its campaign-lessons heading: $body_file" >&2
            exit 1
        }
    elif grep -Fqx -- '## Campaign lessons (REQUIRED)' "$body_file"; then
        echo "Control issue body unexpectedly contains a campaign-lessons heading: $body_file" >&2
        exit 1
    fi
done
```

</details>


---

<sub>34s</sub>

### `view`

**view the file at /home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh.**

/home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh (lines 1-300)

<details>
<summary>30 lines</summary>

```
#!/usr/bin/env bash
# shepherd-task-version: 1.0.4

set -euo pipefail

if [[ $# -ne 3 ]]; then
    echo "Usage: $0 <PRE_CREATION_CHILDREN_JSON> <FINAL_CHILDREN_JSON> <CREATION_LEDGER_JSON>" >&2
    exit 2
fi

for command_name in jq; do
    command -v "$command_name" >/dev/null 2>&1 || {
        echo "Error: Required command '$command_name' was not found." >&2
        exit 1
    }
done

script_dir="$(cd "$(dirname "$0")" && pwd)"
filter_path="$script_dir/verify-stage20-child-links.jq"
[[ -f "$filter_path" ]] || {
    echo "Error: Stage-20 child-link verification filter was not found: $filter_path" >&2
    exit 1
}

for input_path in "$@"; do
    [[ -f "$input_path" ]] || {
        echo "Error: Stage-20 child-link verification input was not found: $input_path" >&2
        exit 1
    }
done

jq -n \
    --slurpfile baseline "$1" \
    --slurpfile final "$2" \
    --slurpfile ledger "$3" \
    -f "$filter_path"
```

</details>


---

<sub>34s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/shepherd-test-experiment.json.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/shepherd-test-experiment.json (lines 1-250)

<details>
<summary>6 lines</summary>

```
{
  "schemaVersion": 1,
  "baselineSha": "634f3ca4787c84fd652cdf5c883c37f1098e0c61",
  "lessonPropagation": "off",
  "expectedTaskCount": 5
}
```

</details>


---

<sub>40s</sub>

### Copilot

All nine implementation questions have substantive resolution blocks; none remains unresolved. The plan contains exactly five direct Phase 4 tasks, and the normalized pre-creation child baseline is empty. I’m persisting the complete five-issue specification set now, with no campaign-lesson instructions because propagation is off.

---

<sub>41s</sub>

### `sql`

**Advance drafting phase**

<details>
<summary>Arguments</summary>

```json
{
  "description": "Advance drafting phase",
  "query": "UPDATE todos SET status='done', updated_at=CURRENT_TIMESTAMP WHERE id='validate-invocation'; UPDATE todos SET status='in_progress', updated_at=CURRENT_TIMESTAMP WHERE id='build-drafts'"
}
```

</details>

<details>
<summary>5 lines</summary>

```
Executed 2 statement(s):

1. UPDATE
   1 row(s) updated.

2. UPDATE
   1 row(s) updated.
```

</details>


---

<sub>1m 15s</sub>

### `apply_patch`

<details>
<summary>Arguments</summary>

````json
"*** Begin Patch\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/01-4-1-body.md\n+## Campaign context and required reading\n+\n+**On the `experiment/shepherd-control` branch, the directory `1-arrival-deadline-control-remove-before-merge` contains the plan (`add-change-arrival-deadline-feature-ignorance-reduction-plan.md`) and supporting resources (diagrams, decision records). Spike subdirectories are research artifacts — read the plan's Resolution sections for findings, not the spike source code.**\n+\n+Read the entire plan before working. Then re-read these exact sections:\n+\n+- `## Goal`, including `### Domain acceptance behavior` and `### Hard scope constraints`\n+- `### 3.2 — What is the exact domain mutation?`\n+- `### 3.3 — What should happen to an existing itinerary and delivery state?`\n+- `### 3.8 — What date validation is required?`\n+- `### 3.9 — How will the feature be tested on the prepared historical baseline?`\n+- `### 4.1 — Issue 1: Add the application-layer deadline change operation`\n+- `## Cross-cutting concerns`\n+\n+The resolved decisions are binding: replace the route specification through `Cargo.specifyNewRoute(...)`; preserve the current origin, destination, and itinerary; let the aggregate recalculate delivery and routing state; store through `CargoRepository`; accept a non-null `java.util.Date` without inventing a new chronological policy; and add `testChangeDeadline()` after `testChangeDestination()` in the existing sequential Arquillian test.\n+\n+Research established that the correct aggregate update is the existing `changeDestination(...)` pattern. Direct mutation, a new domain setter, clearing the itinerary, or updating persistence state behind the aggregate would bypass required recalculation and is rejected. The established sequential test leaves the retained itinerary unchanged and the cargo `MISROUTED` after the deadline changes. Implement these findings from the production architecture; do not copy or adapt research code.\n+\n+## Branch and execution order\n+\n+Target `experiment/shepherd-control` from remote `origin`. This is task **4.1**, first of five serial tasks. Tasks are assigned, completed, and merged in the listed order. Do not begin until this issue is assigned. Do not include facade, JSF, PrimeFaces, XHTML, REST, Liberty configuration, or persistence configuration changes.\n+\n+Begin from the campaign baseline and, before implementation, run the existing JDK 17 Open Liberty package gate and record which tests actually execute, pass, skip, or fail. Do not assume the historical fixture's test count or skipped-test behavior, and do not broaden this task into Arquillian modernization.\n+\n+## Implement\n+\n+Modify only:\n+\n+- `demo/src/main/java/org/eclipse/cargotracker/application/BookingService.java`\n+- `demo/src/main/java/org/eclipse/cargotracker/application/internal/DefaultBookingService.java`\n+- `demo/src/test/java/org/eclipse/cargotracker/application/BookingServiceTest.java`\n+\n+Add this application API:\n+\n+```java\n+void changeDeadline(TrackingId trackingId, Date deadline);\n+```\n+\n+Implement it by:\n+\n+1. Loading the cargo with `cargoRepository.find(trackingId)`.\n+2. Reading the current destination from `cargo.getRouteSpecification().getDestination()`.\n+3. Constructing a replacement `RouteSpecification` with `cargo.getOrigin()`, that current destination, and the supplied deadline.\n+4. Applying it with `cargo.specifyNewRoute(routeSpecification)`.\n+5. Persisting it with `cargoRepository.store(cargo)`.\n+6. Logging the tracking ID and new deadline at `Level.INFO` in the style of `changeDestination(...)`.\n+\n+Write the test first. Append sequential `testChangeDeadline()` after `testChangeDestination()`. Advance the test's original `deadline` by one month, invoke the new service operation, reload with `Cargo.findByTrackingId`, and assert:\n+\n+- origin is still Chicago;\n+- destination is still Helsinki;\n+- stored deadline is the same calendar day as requested;\n+- assigned itinerary is unchanged;\n+- transport status is `NOT_RECEIVED`;\n+- last known location is `Location.UNKNOWN`;\n+- current voyage is `Voyage.NONE`;\n+- cargo is not misdirected;\n+- ETA is `Delivery.ETA_UNKOWN`;\n+- next expected activity is `Delivery.NO_ACTIVITY`;\n+- cargo is not unloaded at destination;\n+- routing status remains `MISROUTED`.\n+\n+Surface lookup, validation, construction, or persistence failures normally; do not turn a failed mutation into apparent success.\n+\n+## Completion gates\n+\n+- `testChangeDeadline()` is ordered after `testChangeDestination()` and asserts every preserved and recalculated field listed above.\n+- Test sources compile and the configured tests' actual execution/results are recorded.\n+- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17.\n+- A focused diff confirms only the three allowed application/test files changed.\n+- No mutable deadline setter exists, no persistence-level field update bypasses the aggregate, and no itinerary is cleared or replaced.\n+\n+## Out of scope\n+\n+- Facade, DTO, web bean, dialog, table, REST, messaging, batch, runtime, dependency, and configuration changes.\n+- A future-date, after-old-deadline, or after-itinerary chronological rule.\n+- Arquillian/runtime modernization, a new mocking framework, or imported test/CI machinery.\n+- Cherry-picking or inspecting feature-bearing commits.\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/02-4-2-body.md\n+## Campaign context and required reading\n+\n+**On the `experiment/shepherd-control` branch, the directory `1-arrival-deadline-control-remove-before-merge` contains the plan (`add-change-arrival-deadline-feature-ignorance-reduction-plan.md`) and supporting resources (diagrams, decision records). Spike subdirectories are research artifacts — read the plan's Resolution sections for findings, not the spike source code.**\n+\n+Read the entire plan before working. Then re-read these exact sections:\n+\n+- `## Goal`, including `### Domain acceptance behavior` and `### Hard scope constraints`\n+- `### 3.2 — What is the exact domain mutation?`\n+- `### 3.4 — What type crosses the facade boundary?`\n+- `### 3.8 — What date validation is required?`\n+- `### 4.2 — Issue 2: Expose deadline changes through the booking facade`\n+- `## Cross-cutting concerns`\n+\n+The resolved facade contract is `void changeDeadline(String trackingId, Date arrivalDeadline)`. `DefaultBookingServiceFacade` must convert only the identifier with `new TrackingId(trackingId)` and pass the same `Date` to the application service. Do not introduce a command DTO, formatted-string date parameter, domain type in the web-facing API, or repository work in the facade.\n+\n+Research established that `java.util.Date` already crosses this facade for booking and is the consistent boundary type. A second date representation would add conversion without value. The domain mutation remains owned by `BookingService`; the facade is a single delegation boundary. Implement these findings from scratch in production code, not from research artifacts.\n+\n+## Branch and execution order\n+\n+Target `experiment/shepherd-control` from remote `origin`. This is task **4.2**, second of five serial tasks. Tasks are assigned, completed, and merged in the listed order. Do not begin until this issue is assigned and task 4.1 has merged into the base branch. Preserve task 4.1 and its tests unchanged.\n+\n+## Implement\n+\n+Modify:\n+\n+- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/facade/BookingServiceFacade.java`\n+- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/facade/internal/DefaultBookingServiceFacade.java`\n+\n+Optionally add:\n+\n+- `demo/src/test/java/org/eclipse/cargotracker/interfaces/booking/facade/internal/DefaultBookingServiceFacadeTest.java`\n+\n+Add:\n+\n+```java\n+void changeDeadline(String trackingId, Date arrivalDeadline);\n+```\n+\n+Implement exactly one delegation:\n+\n+```java\n+bookingService.changeDeadline(\n+        new TrackingId(trackingId),\n+        arrivalDeadline);\n+```\n+\n+The facade must not load or mutate `Cargo`, use `CargoRepository`, parse or format dates, or depend on JSF/PrimeFaces. Preserve normal error propagation; do not catch service failures and return success.\n+\n+If a focused container-free test is practical with the existing construction/injection seams, use a hand-written fake or spy `BookingService` and prove:\n+\n+- the tracking string becomes an equivalent `TrackingId`;\n+- the same date object/value reaches the service;\n+- delegation happens exactly once;\n+- the facade performs no duplicate repository work.\n+\n+Do not add a mocking dependency solely to enable the test.\n+\n+## Completion gates\n+\n+- Existing facade consumers compile with the new method.\n+- The implementation performs one application-service call and no domain/repository mutation.\n+- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17.\n+- The application-layer test introduced by task 4.1 remains unchanged and compiling.\n+- A focused diff contains only the two facade files and, if justified, the optional focused test.\n+\n+## Out of scope\n+\n+- Changes to the task 4.1 application operation or its test.\n+- JSF backing beans, PrimeFaces dialogs, XHTML, dashboard integration, DTO redesign, or date formatting.\n+- New command DTOs, domain types in presentation APIs, mocking libraries, or dependency changes.\n+- Runtime/configuration modernization or feature-bearing commit reuse.\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/03-4-3-body.md\n+## Campaign context and required reading\n+\n+**On the `experiment/shepherd-control` branch, the directory `1-arrival-deadline-control-remove-before-merge` contains the plan (`add-change-arrival-deadline-feature-ignorance-reduction-plan.md`) and supporting resources (diagrams, decision records). Spike subdirectories are research artifacts — read the plan's Resolution sections for findings, not the spike source code.**\n+\n+Read the entire plan before working. Then re-read these exact sections:\n+\n+- `## Goal`, including `### Hard scope constraints`\n+- `### 3.4 — What type crosses the facade boundary?`\n+- `### 3.5 — How is the DTO's formatted deadline converted for editing?`\n+- `### 3.6 — Which JSF bean scopes and interaction pattern should be used?`\n+- `### 3.7 — What is the dynamic-dialog contract?`\n+- `### 3.8 — What date validation is required?`\n+- `### 4.3 — Issue 3: Implement the deadline editor backing model`\n+- `## Cross-cutting concerns`\n+\n+The resolutions require a serializable CDI `@Named @ViewScoped` editor bean named by convention `changeArrivalDeadlineDate`. It loads a `CargoRoute` through the facade, parses the DTO's date-only value with a per-load `new SimpleDateFormat(\"MM/dd/yyyy\")`, holds a `java.util.Date`, submits it through `BookingServiceFacade.changeDeadline(...)`, and closes successfully with `\"DONE\"`. A missing selection is invalid, but no new chronological rule is allowed.\n+\n+Research established that adding a `Date` field to the broadly used DTO is unnecessary and loading a domain object in the view would violate layering. The DTO's displayed date uses `MM/dd/yyyy`; use a per-operation parser rather than shared mutable `SimpleDateFormat`. Parsing, validation, or facade failures must remain visible, and a failed call must not close the dialog. Implement these findings without reading or adapting spike code.\n+\n+## Branch and execution order\n+\n+Target `experiment/shepherd-control` from remote `origin`. This is task **4.3**, third of five serial tasks. Tasks are assigned, completed, and merged in the listed order. Do not begin until this issue is assigned and task 4.2 has merged into the base branch. Do not create the dialog launcher or XHTML yet.\n+\n+## Implement\n+\n+Create:\n+\n+- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/ChangeArrivalDeadlineDate.java`\n+\n+Use this shape:\n+\n+```java\n+@Named\n+@ViewScoped\n+public class ChangeArrivalDeadlineDate implements Serializable {\n+    private static final long serialVersionUID = 1L;\n+\n+    private String trackingId;\n+    private CargoRoute cargo;\n+    private Date arrivalDeadlineDate;\n+\n+    @Inject\n+    private BookingServiceFacade bookingServiceFacade;\n+}\n+```\n+\n+Provide:\n+\n+- `getTrackingId()` and `setTrackingId(String)`;\n+- `getCargo()`;\n+- `getArrivalDeadlineDate()` and `setArrivalDeadlineDate(Date)`;\n+- `load()`;\n+- `changeArrivalDeadline()`.\n+\n+`load()` must call `bookingServiceFacade.loadCargoForRouting(trackingId)`, retain the returned `CargoRoute`, parse its date-only deadline with `MM/dd/yyyy`, and populate the editable date. Follow established JSF error behavior for malformed data; do not swallow the parse failure, print only a stack trace, or silently set null. Keep domain classes and repositories out of the bean.\n+\n+`changeArrivalDeadline()` must reject null, call `bookingServiceFacade.changeDeadline(trackingId, arrivalDeadlineDate)`, and only after successful return invoke:\n+\n+```java\n+PrimeFaces.current().dialog().closeDynamic(\"DONE\");\n+```\n+\n+Add a container-free JUnit test if practical with existing seams and a hand-written fake facade. Cover the correct load ID, date conversion, submit arguments, malformed deadline, null selection, and the rule that a facade failure does not produce a successful close. Do not add a mocking framework.\n+\n+## Completion gates\n+\n+- The bean is serializable and uses the repository's established CDI/JSF annotations and error conventions.\n+- Loading requests the correct ID and produces the displayed `MM/dd/yyyy` calendar date.\n+- Malformed and null values fail explicitly; service failure does not appear successful.\n+- Submission delegates the selected ID/date exactly once.\n+- The bean references facade types only, never domain model or repository types.\n+- `cd demo && ./mvnw clean package -Popenliberty` succeeds on JDK 17, preserving all prior tests.\n+- No launcher or XHTML file is added in this task.\n+\n+## Out of scope\n+\n+- Dynamic-dialog launcher, XHTML, dashboard/table integration, or destination-dialog changes.\n+- DTO expansion, repository/domain access from the web layer, shared mutable date formatter, or timezone-policy changes.\n+- New future/ordering date rules, mocking dependencies, runtime modernization, or feature-bearing commit reuse.\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/04-4-4-body.md\n+## Campaign context and required reading\n+\n+**On the `experiment/shepherd-control` branch, the directory `1-arrival-deadline-control-remove-before-merge` contains the plan (`add-change-arrival-deadline-feature-ignorance-reduction-plan.md`) and supporting resources (diagrams, decision records). Spike subdirectories are research artifacts — read the plan's Resolution sections for findings, not the spike source code.**\n+\n+Read the entire plan before working. Then re-read these exact sections:\n+\n+- `## Goal`, especially `### User-visible acceptance behavior` and `### Hard scope constraints`\n+- `### 3.5 — How is the DTO's formatted deadline converted for editing?`\n+- `### 3.6 — Which JSF bean scopes and interaction pattern should be used?`\n+- `### 3.7 — What is the dynamic-dialog contract?`\n+- `### 3.8 — What date validation is required?`\n+- `### 3.9 — How will the feature be tested on the prepared historical baseline?`\n+- `### 4.4 — Issue 4: Implement the PrimeFaces deadline dialog`\n+- `## Cross-cutting concerns`\n+\n+The resolved interaction mirrors Change Destination: a serializable JSF managed `@SessionScoped` launcher opens a PrimeFaces dynamic dialog, while task 4.3's CDI `@ViewScoped` editor owns data. The request contains only `trackingId`. Options are modal `true`, draggable `true`, resizable `false`, content width `410`, and content height `280`. Success returns `\"DONE\"`; cancel returns `\"\"`. The caller handles `dialogReturn`.\n+\n+Research established a MyFaces compatibility constraint: `<f:metadata>` must be directly under root `<html>`, before `<h:head>` and `<h:body>`. Nesting it in the body can produce `UIViewRoot`/parent-component failures. The date is required, but no future/minimum-date rule is permitted. Implement these findings directly; do not copy research source or test helpers.\n+\n+## Branch and execution order\n+\n+Target `experiment/shepherd-control` from remote `origin`. This is task **4.4**, fourth of five serial tasks. Tasks are assigned, completed, and merged in the listed order. Do not begin until this issue is assigned and task 4.3 has merged into the base branch. This task makes the dialog directly addressable but does not yet modify the dashboard table.\n+\n+## Implement\n+\n+Create:\n+\n+- `demo/src/main/java/org/eclipse/cargotracker/interfaces/booking/web/ChangeArrivalDeadlineDateDialog.java`\n+- `demo/src/main/webapp/admin/dialogs/changeArrivalDeadlineDate.xhtml`\n+\n+The launcher must be serializable and use:\n+\n+```java\n+@ManagedBean(name = \"changeArrivalDeadlineDateDialog\")\n+@SessionScoped\n+```\n+\n+Implement `showDialog(String trackingId)`, `handleReturn(SelectEvent event)`, and `cancel()`. `showDialog(...)` must pass `trackingId` as a `Map<String, List<String>>`, set all five resolved options, and open `/admin/dialogs/changeArrivalDeadlineDate.xhtml`. `cancel()` closes with the empty string and must not call the facade. Preserve the established return-handling behavior needed by task 4.5.\n+\n+The XHTML title is `Change Deadline`. Put this metadata directly beneath root `<html>` and before `<h:head>`:\n+\n+```xhtml\n+<f:metadata>\n+    <f:viewParam name=\"trackingId\"\n+                 value=\"#{changeArrivalDeadlineDate.trackingId}\"/>\n+    <f:viewAction action=\"#{changeArrivalDeadlineDate.load}\"/>\n+</f:metadata>\n+```\n+\n+Build an accessible form with visible/associated labels and validation feedback:\n+\n+- `Origin:` displaying `changeArrivalDeadlineDate.cargo.originName`;\n+- `Destination:` displaying `changeArrivalDeadlineDate.cargo.finalDestinationName`;\n+- `Deadline:` with a required `p:datePicker` bound to `changeArrivalDeadlineDate.arrivalDeadlineDate`;\n+- **Cancel** invoking `changeArrivalDeadlineDateDialog.cancel()`;\n+- **Update** invoking `changeArrivalDeadlineDate.changeArrivalDeadline()`.\n+\n+Do not close after a failed update. Follow the existing destination dialog's PrimeFaces structure and styling without changing it.\n+\n+## Completion gates\n+\n+- `cd demo && ./mvnw clean package -Popenliberty liberty:run` starts successfully on JDK 17.\n+- A direct request to `/cargo-tracker/admin/dialogs/changeArrivalDeadlineDate.xhtml?trackingId=DEF789` returns HTTP 200.\n+- The page title, origin, destination, existing selected deadline, labels, required validation, Cancel, and Update all behave as specified.\n+- Cancel leaves the stored deadline unchanged; Update changes it and returns `\"DONE\"`.\n+- Server output contains no new `TagException`, `<f:metadata> Parent UIComponent`, `FacesException`, or related server error.\n+- Existing destination editing still works.\n+- Liberty is stopped cleanly before completion.\n+\n+## Out of scope\n+\n+- Editing `listNotRouted.xhtml` or exposing the command link on any dashboard table.\n+- Inline editing, full-page navigation, other cargo tables, destination-dialog redesign, or domain/facade changes.\n+- New date policy, framework/dependency migration, test-runtime modernization, or feature-bearing commit reuse.\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/05-4-5-body.md\n+## Campaign context and required reading\n+\n+**On the `experiment/shepherd-control` branch, the directory `1-arrival-deadline-control-remove-before-merge` contains the plan (`add-change-arrival-deadline-feature-ignorance-reduction-plan.md`) and supporting resources (diagrams, decision records). Spike subdirectories are research artifacts — read the plan's Resolution sections for findings, not the spike source code.**\n+\n+Read the entire plan before working. Then re-read these exact sections:\n+\n+- `## Goal`, including `### User-visible acceptance behavior`, `### Domain acceptance behavior`, and `### Hard scope constraints`\n+- `### 3.1 — Which cargos expose the edit operation?`\n+- `### 3.6 — Which JSF bean scopes and interaction pattern should be used?`\n+- `### 3.7 — What is the dynamic-dialog contract?`\n+- `### 3.8 — What date validation is required?`\n+- `### 3.9 — How will the feature be tested on the prepared historical baseline?`\n+- `### 4.5 — Issue 5: Integrate deadline editing into the Administration dashboard`\n+- `## Phase 5 — Documentation and implementation handoff`\n+- `## Cross-cutting concerns`\n+\n+The resolved UI scope is only the Not Routed Cargo table. The service/facade remain generally callable, but do not add this affordance to routed, misrouted, claimed, details, or other tables. Follow the adjacent Destination command-link pattern. The dialog takes `trackingId`, returns `\"DONE\"` on update or `\"\"` on cancel, and the `dialogReturn` listener refreshes `tableNotRouted`.\n+\n+Research established that the prepared JDK 17/Open Liberty baseline and existing destination editor are the compatibility references. Preserve Java EE 7 `javax.*`, PrimeFaces 8, the MyFaces metadata fix, Derby, REST, messaging, batch, and runtime configuration. Acceptance compares the displayed calendar date and introduces no timezone or chronological policy. Implement these findings from production patterns, not research source code.\n+\n+## Branch and execution order\n+\n+Target `experiment/shepherd-control` from remote `origin`. This is task **4.5**, fifth and final serial task. Tasks are assigned, completed, and merged in the listed order. Do not begin until this issue is assigned and task 4.4 has merged into the base branch. Preserve all earlier task gates.\n+\n+## Implement\n+\n+Modify:\n+\n+- `demo/src/main/webapp/admin/tables/listNotRouted.xhtml`\n+\n+Within the existing Deadline column, replace plain deadline text with a `p:commandLink` that:\n+\n+- invokes `changeArrivalDeadlineDateDialog.showDialog(cargoNotRouted.trackingId)`;\n+- continues displaying `cargoNotRouted.arrivalDeadlineDate`;\n+- uses the existing Font Awesome edit-icon style;\n+- has a stable ID such as `arrivalDeadlineToUpdate`;\n+- contains a `dialogReturn` listener invoking `changeArrivalDeadlineDateDialog.handleReturn`;\n+- updates `tableNotRouted`;\n+- exposes the exact tooltip `Click to change cargo arrival deadline date.`\n+\n+Match the adjacent Destination column's interaction and styling. Do not alter tracking-ID routing or destination editing. If `demo/README.md` enumerates user-facing Administration capabilities, add only one concise sentence noting that administrators can change an unrouted cargo's arrival deadline; otherwise leave it unchanged.\n+\n+Capture final acceptance evidence with `DEF789`, including before/after displayed deadlines, the exact JDK 17 command, and the fact that in-memory data resets after rebuild/restart.\n+\n+## Completion gates\n+\n+- `cd demo && ./mvnw clean package -Popenliberty liberty:run` starts successfully on JDK 17; the home page returns HTTP 200.\n+- In Administration, `DEF789` shows the deadline, edit icon, and exact tooltip.\n+- Opening the dialog shows the same cargo's origin/destination and current deadline.\n+- Updating to a visibly different date closes the dialog, refreshes `tableNotRouted`, displays the new date, persists it across browser reload, and initializes the next dialog open to that date.\n+- Cancel causes no additional change.\n+- Existing Destination editing still opens, and selecting `DEF789` for routing does not produce an error page.\n+- Logs contain none of `<f:metadata> Parent UIComponent`, `TagException`, `VerifyError`, `FacesException`, `CWWKZ0002E`, `CWWKZ0003E`, recurring batch authorization failures, or new feature-attributable FFDC files. Transient JMS activation-order warnings are acceptable only when all endpoints subsequently activate.\n+- `cd demo && ./mvnw clean package -Popenliberty` succeeds; existing and new test sources compile.\n+- A focused diff confirms no Java EE namespace migration and no regression to Liberty, Derby, Jackson, JSF metadata, batch authorization, REST compatibility, destination editing, routing, tracking, messaging, or startup behavior.\n+- Liberty is stopped cleanly.\n+\n+## Out of scope\n+\n+- Exposing deadline editing outside `listNotRouted.xhtml`, changing domain/facade contracts, or redesigning existing interactions.\n+- New timezone, future-date, lifecycle, or authorization policy.\n+- Jakarta/Spring/UI-framework migration, runtime/persistence replacement, unrelated cleanup, or feature-bearing commit reuse.\n*** End Patch\n"
````

</details>

```
Added 5 file(s): /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/01-4-1-body.md, /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/02-4-2-body.md, /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/03-4-3-body.md, /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/04-4-4-body.md, /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies/05-4-5-body.md
```


---

<sub>1m 27s</sub>

### Copilot

The five persisted issue bodies are complete and tailored to their serial tasks. I’m running the mandatory all-drafts validator; only after it passes will the durable ledger be initialized and GitHub creation begin.

---

<sub>1m 28s</sub>

### `bash`

**Validate all issue drafts**

$ set -euo pipefail
validator='/home/edburns/.copilot/plugins/shepherd-task/scripts/validate-stage20-drafts.sh'
bodies='/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/issue-bodies'
"$validator" "$bodies" 5 off
for f in "$bodies"/*-body.md; do printf '%s\t%s lines\n' "$(basename "$f")" "$(awk 'END {print NR}' "$f")"; done

<details>
<summary>6 lines</summary>

```
01-4-1-body.md	78 lines
02-4-2-body.md	71 lines
03-4-3-body.md	79 lines
04-4-4-body.md	74 lines
05-4-5-body.md	62 lines
<shellId: 5 completed with exit code 0>
```

</details>


---

<sub>1m 38s</sub>

### Copilot

The draft validator passed all five multiline specifications. I’m now installing the one-shot execution script that atomically maintains the required ledger/result files and performs failure reconciliation without rollback.

---

<sub>1m 52s</sub>

### `apply_patch`

<details>
<summary>Arguments</summary>

```json
"*** Begin Patch\n*** Add File: 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/run-stage-20.sh\n+#!/usr/bin/env bash\n+\n+set -Eeuo pipefail\n+\n+REPO='edburns/dd-3072973-cargotracker-control-01'\n+PARENT_ISSUE=1\n+LOG_DIRECTORY='/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037'\n+BODY_DIRECTORY=\"$LOG_DIRECTORY/issue-bodies\"\n+LEDGER=\"$LOG_DIRECTORY/creation-ledger.json\"\n+RESULT=\"$LOG_DIRECTORY/stage-20-result.json\"\n+FINAL_CHILDREN=\"$LOG_DIRECTORY/final-children.json\"\n+ISSUE_BODY_VERIFIER='/home/edburns/.copilot/plugins/shepherd-task/scripts/verify-github-issue-body.sh'\n+CHILD_LINK_VERIFIER='/home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh'\n+\n+subsections=(\n+  '4.1 — Issue 1: Add the application-layer deadline change operation'\n+  '4.2 — Issue 2: Expose deadline changes through the booking facade'\n+  '4.3 — Issue 3: Implement the deadline editor backing model'\n+  '4.4 — Issue 4: Implement the PrimeFaces deadline dialog'\n+  '4.5 — Issue 5: Integrate deadline editing into the Administration dashboard'\n+)\n+titles=(\n+  '4.1 — Add the application-layer arrival deadline change'\n+  '4.2 — Expose arrival deadline changes through the booking facade'\n+  '4.3 — Add the arrival deadline editor backing model'\n+  '4.4 — Add the PrimeFaces arrival deadline dialog'\n+  '4.5 — Integrate arrival deadline editing into Administration'\n+)\n+body_files=(\n+  \"$BODY_DIRECTORY/01-4-1-body.md\"\n+  \"$BODY_DIRECTORY/02-4-2-body.md\"\n+  \"$BODY_DIRECTORY/03-4-3-body.md\"\n+  \"$BODY_DIRECTORY/04-4-4-body.md\"\n+  \"$BODY_DIRECTORY/05-4-5-body.md\"\n+)\n+\n+operation='initialization'\n+last_created=''\n+last_subsection=''\n+last_body_relative=''\n+\n+atomic_write() {\n+  local path=\"$1\" content=\"$2\" temp\n+  temp=\"$(mktemp \"${path}.tmp.XXXXXX\")\"\n+  printf '%s\\n' \"$content\" > \"$temp\"\n+  mv \"$temp\" \"$path\"\n+}\n+\n+update_ledger_flag() {\n+  local number=\"$1\" field=\"$2\" value=\"$3\" updated\n+  updated=\"$(\n+    jq \\\n+      --argjson number \"$number\" \\\n+      --arg field \"$field\" \\\n+      --argjson value \"$value\" \\\n+      'map(if .number == $number then .[$field] = $value else . end)' \\\n+      \"$LEDGER\"\n+  )\" || return 1\n+  atomic_write \"$LEDGER\" \"$updated\"\n+}\n+\n+append_last_created_if_missing() {\n+  [[ -n \"$last_created\" ]] || return 0\n+  local id number title url present updated\n+  id=\"$(jq -r '.id' <<<\"$last_created\")\"\n+  number=\"$(jq -r '.number' <<<\"$last_created\")\"\n+  title=\"$(jq -r '.title' <<<\"$last_created\")\"\n+  url=\"$(jq -r '.html_url' <<<\"$last_created\")\"\n+  present=\"$(jq --argjson number \"$number\" 'any(.[]; .number == $number)' \"$LEDGER\" 2>/dev/null || printf 'false')\"\n+  [[ \"$present\" == 'true' ]] && return 0\n+  updated=\"$(\n+    jq \\\n+      --arg subsection \"$last_subsection\" \\\n+      --arg bodyFile \"$last_body_relative\" \\\n+      --argjson id \"$id\" \\\n+      --argjson number \"$number\" \\\n+      --arg title \"$title\" \\\n+      --arg url \"$url\" \\\n+      '. + [{\n+        implementationSubsection: $subsection,\n+        bodyFile: $bodyFile,\n+        id: $id,\n+        number: $number,\n+        title: $title,\n+        url: $url,\n+        body_verified: false,\n+        linked: false\n+      }]' \\\n+      \"$LEDGER\"\n+  )\" || return 1\n+  atomic_write \"$LEDGER\" \"$updated\"\n+}\n+\n+reconcile_and_fail() {\n+  local error=\"$1\" children_output normalized id number linked updated result_json\n+  trap - ERR\n+  set +e\n+\n+  append_last_created_if_missing\n+\n+  children_output=\"$(gh api \"repos/$REPO/issues/$PARENT_ISSUE/sub_issues\" --paginate --slurp 2>&1)\"\n+  if [[ $? -eq 0 ]]; then\n+    normalized=\"$(printf '%s' \"$children_output\" | jq 'if length == 0 then [] elif all(.[]; type == \"array\") then add else . end')\"\n+    if [[ $? -eq 0 ]]; then\n+      while IFS= read -r number; do\n+        id=\"$(jq --argjson number \"$number\" -r '.[] | select(.number == $number) | .id' \"$LEDGER\")\"\n+        linked=\"$(jq --argjson id \"$id\" 'any(.[]; .id == $id)' <<<\"$normalized\")\"\n+        update_ledger_flag \"$number\" linked \"$linked\"\n+      done < <(jq -r '.[].number' \"$LEDGER\")\n+    else\n+      error=\"$error; reconciliation normalization failed\"\n+    fi\n+  else\n+    error=\"$error; reconciliation query failed: $children_output\"\n+  fi\n+\n+  result_json=\"$(\n+    jq -n \\\n+      --arg error \"$operation: $error\" \\\n+      '{\n+        schemaVersion: 1,\n+        status: \"failed\",\n+        ledgerFile: \"creation-ledger.json\",\n+        operationError: $error\n+      }'\n+  )\"\n+  atomic_write \"$RESULT\" \"$result_json\"\n+\n+  printf 'Stage 20 failed during %s: %s\\n' \"$operation\" \"$error\" >&2\n+  if [[ \"$(jq 'length' \"$LEDGER\" 2>/dev/null)\" == '0' ]]; then\n+    printf 'No issues were created; no cleanup is required.\\n' >&2\n+  else\n+    jq -r '.[] | \"issue #\\(.number) | \\(.title) | \\(.url) | \\(.bodyFile) | body_verified=\\(.body_verified) | linked=\\(.linked)\"' \"$LEDGER\" >&2\n+    jq -r --arg repo \"$REPO\" '.[] | \"gh issue delete \\(.number) --repo \\\"\\($repo)\\\" --yes\"' \"$LEDGER\" >&2\n+    printf 'The operation did not complete. No automatic rollback was performed. Delete every issue in the ledger before invoking stage 20 again.\\n' >&2\n+  fi\n+  exit 1\n+}\n+\n+unexpected_error() {\n+  local code=\"$1\" line=\"$2\"\n+  reconcile_and_fail \"unexpected exit $code at script line $line\"\n+}\n+\n+trap 'unexpected_error \"$?\" \"$LINENO\"' ERR\n+\n+atomic_write \"$LEDGER\" '[]'\n+atomic_write \"$RESULT\" '{\n+  \"schemaVersion\": 1,\n+  \"status\": \"in_progress\",\n+  \"ledgerFile\": \"creation-ledger.json\",\n+  \"operationError\": null\n+}'\n+\n+for index in \"${!body_files[@]}\"; do\n+  subsection=\"${subsections[$index]}\"\n+  title=\"${titles[$index]}\"\n+  body_file=\"${body_files[$index]}\"\n+  body_relative=\"issue-bodies/$(basename \"$body_file\")\"\n+\n+  operation=\"creating $subsection\"\n+  if ! created=\"$(\n+    gh api \"repos/$REPO/issues\" \\\n+      -X POST \\\n+      -f title=\"$title\" \\\n+      -F \"body=@$body_file\" \\\n+      --jq '{id,number,node_id,html_url,title}'\n+  )\"; then\n+    reconcile_and_fail 'GitHub issue creation failed'\n+  fi\n+\n+  last_created=\"$created\"\n+  last_subsection=\"$subsection\"\n+  last_body_relative=\"$body_relative\"\n+  id=\"$(jq -r '.id' <<<\"$created\")\"\n+  number=\"$(jq -r '.number' <<<\"$created\")\"\n+  created_title=\"$(jq -r '.title' <<<\"$created\")\"\n+  url=\"$(jq -r '.html_url' <<<\"$created\")\"\n+\n+  operation=\"persisting ledger entry for issue #$number\"\n+  append_last_created_if_missing || reconcile_and_fail 'unable to append the created issue to the ledger'\n+\n+  operation=\"verifying body for issue #$number\"\n+  if ! issue_json=\"$(\n+    \"$ISSUE_BODY_VERIFIER\" \\\n+      \"$REPO\" \\\n+      \"$number\" \\\n+      \"$body_file\" \\\n+      6 \\\n+      5 \\\n+      \"$LOG_DIRECTORY/issue-$number-body-verification-failure.json\"\n+  )\"; then\n+    reconcile_and_fail 'issue body did not match the persisted specification'\n+  fi\n+  update_ledger_flag \"$number\" body_verified true ||\n+    reconcile_and_fail 'unable to persist body verification'\n+\n+  operation=\"linking issue #$number to parent #$PARENT_ISSUE\"\n+  linked=false\n+  link_error=''\n+  for attempt in 1 2 3; do\n+    if link_output=\"$(\n+      printf '{\"sub_issue_id\": %s}' \"$id\" |\n+        gh api \"repos/$REPO/issues/$PARENT_ISSUE/sub_issues\" -X POST --input - 2>&1\n+    )\"; then\n+      linked=true\n+      break\n+    fi\n+    link_error=\"$link_output\"\n+    [[ \"$attempt\" -eq 3 ]] || sleep 2\n+  done\n+  [[ \"$linked\" == true ]] ||\n+    reconcile_and_fail \"link failed after 3 attempts: $link_error\"\n+  update_ledger_flag \"$number\" linked true ||\n+    reconcile_and_fail 'unable to persist linked status'\n+\n+  last_created=''\n+  last_subsection=''\n+  last_body_relative=''\n+  printf 'Created and linked #%s: %s\\n' \"$number\" \"$created_title\"\n+done\n+\n+operation='capturing final child snapshot'\n+if ! final_output=\"$(gh api \"repos/$REPO/issues/$PARENT_ISSUE/sub_issues\" --paginate --slurp 2>&1)\"; then\n+  reconcile_and_fail \"final child query failed: $final_output\"\n+fi\n+if ! final_normalized=\"$(printf '%s' \"$final_output\" | jq 'if length == 0 then [] elif all(.[]; type == \"array\") then add else . end')\"; then\n+  reconcile_and_fail 'final child normalization failed'\n+fi\n+atomic_write \"$FINAL_CHILDREN\" \"$final_normalized\"\n+\n+operation='verifying child links and order'\n+if ! child_verification=\"$(\n+  \"$CHILD_LINK_VERIFIER\" \\\n+    \"$LOG_DIRECTORY/pre-creation-children.json\" \\\n+    \"$FINAL_CHILDREN\" \\\n+    \"$LEDGER\" 2>&1\n+)\"; then\n+  reconcile_and_fail \"child-link postcondition failed: $child_verification\"\n+fi\n+\n+while IFS= read -r number; do\n+  body_relative=\"$(jq --argjson number \"$number\" -r '.[] | select(.number == $number) | .bodyFile' \"$LEDGER\")\"\n+  body_file=\"$LOG_DIRECTORY/$body_relative\"\n+  operation=\"final body verification for issue #$number\"\n+  if ! issue_json=\"$(\n+    \"$ISSUE_BODY_VERIFIER\" \\\n+      \"$REPO\" \\\n+      \"$number\" \\\n+      \"$body_file\" \\\n+      6 \\\n+      5 \\\n+      \"$LOG_DIRECTORY/issue-$number-final-body-verification-failure.json\"\n+  )\"; then\n+    reconcile_and_fail 'final issue body did not match the persisted specification'\n+  fi\n+\n+  operation=\"verifying open and unassigned state for issue #$number\"\n+  if ! issue_state=\"$(gh api \"repos/$REPO/issues/$number\" 2>&1)\"; then\n+    reconcile_and_fail \"unable to read final issue state: $issue_state\"\n+  fi\n+  if ! jq -e '.state == \"open\" and ((.assignees // []) | length == 0)' <<<\"$issue_state\" >/dev/null; then\n+    reconcile_and_fail 'issue is not open and unassigned'\n+  fi\n+done < <(jq -r '.[].number' \"$LEDGER\")\n+\n+operation='writing complete stage result'\n+atomic_write \"$RESULT\" '{\n+  \"schemaVersion\": 1,\n+  \"status\": \"complete\",\n+  \"ledgerFile\": \"creation-ledger.json\",\n+  \"operationError\": null\n+}'\n+\n+trap - ERR\n+jq -r '.[] | \"#\\(.number) | \\(.title) | \\(.url)\"' \"$LEDGER\"\n*** End Patch\n"
```

</details>

```
Added 1 file(s): /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/run-stage-20.sh
```


---

<sub>1m 59s</sub>

### Copilot

All pre-mutation gates have passed. Child issues will now be created untyped, body-verified, and linked one at a time in plan order; any failure will stop mutations, reconcile the durable ledger, and leave explicit cleanup commands.

---

<sub>2m 0s</sub>

### `sql`

**Advance creation phase**

<details>
<summary>Arguments</summary>

```json
{
  "description": "Advance creation phase",
  "query": "UPDATE todos SET status='done', updated_at=CURRENT_TIMESTAMP WHERE id='build-drafts'; UPDATE todos SET status='in_progress', updated_at=CURRENT_TIMESTAMP WHERE id='create-link-issues'"
}
```

</details>

<details>
<summary>5 lines</summary>

```
Executed 2 statement(s):

1. UPDATE
   1 row(s) updated.

2. UPDATE
   1 row(s) updated.
```

</details>


---

<sub>2m 15s</sub>

### `bash`

**Create verify and link issues**

$ bash 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/run-stage-20.sh

<details>
<summary>11 lines</summary>

```
Created and linked #2: 4.1 — Add the application-layer arrival deadline change
Created and linked #3: 4.2 — Expose arrival deadline changes through the booking facade
Created and linked #4: 4.3 — Add the arrival deadline editor backing model
Created and linked #5: 4.4 — Add the PrimeFaces arrival deadline dialog
Created and linked #6: 4.5 — Integrate arrival deadline editing into Administration
#2 | 4.1 — Add the application-layer arrival deadline change | https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/2
#3 | 4.2 — Expose arrival deadline changes through the booking facade | https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/3
#4 | 4.3 — Add the arrival deadline editor backing model | https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/4
#5 | 4.4 — Add the PrimeFaces arrival deadline dialog | https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/5
#6 | 4.5 — Integrate arrival deadline editing into Administration | https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/6
<shellId: 6 completed with exit code 0>
```

</details>


---

<sub>2m 17s</sub>

### Copilot

All five issues were created, body-verified, and linked in order. I’m checking the persisted authoritative result and ledger before handing off the ordered list.

---

<sub>2m 19s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/stage-20-result.json.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/stage-20-result.json

<details>
<summary>6 lines</summary>

```
{
  "schemaVersion": 1,
  "status": "complete",
  "ledgerFile": "creation-ledger.json",
  "operationError": null
}
```

</details>


---

<sub>2m 19s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/creation-ledger.json.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/creation-ledger.json

<details>
<summary>52 lines</summary>

```
[
  {
    "implementationSubsection": "4.1 — Issue 1: Add the application-layer deadline change operation",
    "bodyFile": "issue-bodies/01-4-1-body.md",
    "id": 5685171378,
    "number": 2,
    "title": "4.1 — Add the application-layer arrival deadline change",
    "url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/2",
    "body_verified": true,
    "linked": true
  },
  {
    "implementationSubsection": "4.2 — Issue 2: Expose deadline changes through the booking facade",
    "bodyFile": "issue-bodies/02-4-2-body.md",
    "id": 5685171658,
    "number": 3,
    "title": "4.2 — Expose arrival deadline changes through the booking facade",
    "url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/3",
    "body_verified": true,
    "linked": true
  },
  {
    "implementationSubsection": "4.3 — Issue 3: Implement the deadline editor backing model",
    "bodyFile": "issue-bodies/03-4-3-body.md",
    "id": 5685171843,
    "number": 4,
    "title": "4.3 — Add the arrival deadline editor backing model",
    "url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/4",
    "body_verified": true,
    "linked": true
  },
  {
    "implementationSubsection": "4.4 — Issue 4: Implement the PrimeFaces deadline dialog",
    "bodyFile": "issue-bodies/04-4-4-body.md",
    "id": 5685172032,
    "number": 5,
    "title": "4.4 — Add the PrimeFaces arrival deadline dialog",
    "url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/5",
    "body_verified": true,
    "linked": true
  },
  {
    "implementationSubsection": "4.5 — Issue 5: Integrate deadline editing into the Administration dashboard",
    "bodyFile": "issue-bodies/05-4-5-body.md",
    "id": 5685172249,
    "number": 6,
    "title": "4.5 — Integrate arrival deadline editing into Administration",
    "url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/6",
    "body_verified": true,
    "linked": true
  }
]
```

</details>


---

<sub>2m 19s</sub>

### `view`

**view the file at /home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/final-children.json.**

/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/final-children.json (lines 1-120)

<details>
<summary>120 lines</summary>

```
[
  {
    "url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/2",
    "repository_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01",
    "labels_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/2/labels{/name}",
    "comments_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/2/comments",
    "events_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/2/events",
    "html_url": "https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/2",
    "id": 5685171378,
    "node_id": "I_kwDOU5gt6c8AAAABUtzUsg",
    "number": 2,
    "title": "4.1 — Add the application-layer arrival deadline change",
    "user": {
      "login": "edburns",
      "id": 75821,
      "node_id": "MDQ6VXNlcjc1ODIx",
      "avatar_url": "https://avatars.githubusercontent.com/u/75821?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/edburns",
      "html_url": "https://github.com/edburns",
      "followers_url": "https://api.github.com/users/edburns/followers",
      "following_url": "https://api.github.com/users/edburns/following{/other_user}",
      "gists_url": "https://api.github.com/users/edburns/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/edburns/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/edburns/subscriptions",
      "organizations_url": "https://api.github.com/users/edburns/orgs",
      "repos_url": "https://api.github.com/users/edburns/repos",
      "events_url": "https://api.github.com/users/edburns/events{/privacy}",
      "received_events_url": "https://api.github.com/users/edburns/received_events",
      "type": "User",
      "user_view_type": "public",
      "site_admin": true
    },
    "labels": [],
    "state": "open",
    "locked": false,
    "assignees": [],
    "milestone": null,
    "comments": 0,
    "created_at": "2026-10-03T00:39:42Z",
    "updated_at": "2026-10-03T00:39:42Z",
    "closed_at": null,
    "assignee": null,
    "author_association": "OWNER",
    "issue_field_values": [],
    "type": null,
    "active_lock_reason": null,
    "sub_issues_summary": {
      "total": 0,
      "completed": 0,
      "percent_completed": 0
    },
    "parent_issue_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/1",
    "issue_dependencies_summary": {
      "blocked_by": 0,
      "total_blocked_by": 0,
      "blocking": 0,
      "total_blocking": 0
    },
    "repository": {
      "id": 1402482153,
      "node_id": "R_kgDOU5gt6Q",
      "name": "dd-3072973-cargotracker-control-01",
      "full_name": "edburns/dd-3072973-cargotracker-control-01",
      "private": false,
      "owner": {
        "login": "edburns",
        "id": 75821,
        "node_id": "MDQ6VXNlcjc1ODIx",
        "avatar_url": "https://avatars.githubusercontent.com/u/75821?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/edburns",
        "html_url": "https://github.com/edburns",
        "followers_url": "https://api.github.com/users/edburns/followers",
        "following_url": "https://api.github.com/users/edburns/following{/other_user}",
        "gists_url": "https://api.github.com/users/edburns/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/edburns/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/edburns/subscriptions",
        "organizations_url": "https://api.github.com/users/edburns/orgs",
        "repos_url": "https://api.github.com/users/edburns/repos",
        "events_url": "https://api.github.com/users/edburns/events{/privacy}",
        "received_events_url": "https://api.github.com/users/edburns/received_events",
        "type": "User",
        "user_view_type": "public",
        "site_admin": true
      },
      "html_url": "https://github.com/edburns/dd-3072973-cargotracker-control-01",
      "description": "The project demonstrates how you can develop applications with Jakarta EE using widely adopted architectural best practices like Domain-Driven Design (DDD).",
      "fork": false,
      "url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01",
      "forks_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/forks",
      "keys_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/teams",
      "hooks_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/hooks",
      "issue_events_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/events{/number}",
      "events_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/events",
      "assignees_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/assignees{/user}",
      "branches_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/branches{/branch}",
      "tags_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/tags",
      "blobs_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/languages",
      "stargazers_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/stargazers",
      "contributors_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/contributors",
      "subscribers_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/subscribers",
      "subscription_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/subscription",
      "commits_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/contents/{+path}",
      "compare_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/merges",
      "archive_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/downloads",
      "issues_url": "https://api.github.com/repos/edburns/dd-3072973-cargotracker-control-01/issues{/number}",
```

</details>


---

<sub>2m 19s</sub>

### `bash`

**Inspect persisted stage artifacts**

$ git status --short -- 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037

```
?? 1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037/
<shellId: 7 completed with exit code 0>
```


---

<sub>2m 25s</sub>

### `sql`

**Complete stage twenty work**

<details>
<summary>Arguments</summary>

```json
{
  "description": "Complete stage twenty work",
  "query": "UPDATE todos SET status='done', updated_at=CURRENT_TIMESTAMP WHERE id IN ('create-link-issues','verify-postconditions')"
}
```

</details>

```
2 row(s) updated.
```


---

<sub>2m 28s</sub>

### Copilot

Created and linked five open, unassigned child issues under #1. All bodies match their persisted specifications, linkage/order verification passed, and `stage-20-result.json` records `complete`.

| Implementation subsection | Issue | Title |
|---|---:|---|
| 4.1 | #2 | [Add the application-layer arrival deadline change](https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/2) |
| 4.2 | #3 | [Expose arrival deadline changes through the booking facade](https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/3) |
| 4.3 | #4 | [Add the arrival deadline editor backing model](https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/4) |
| 4.4 | #5 | [Add the PrimeFaces arrival deadline dialog](https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/5) |
| 4.5 | #6 | [Integrate arrival deadline editing into Administration](https://github.com/edburns/dd-3072973-cargotracker-control-01/issues/6) |

**Ordered child list:** `2,3,4,5,6`

**Suggested stage 25 invocation:**
```text
/shepherd-task-25-given-list CAMPAIGN_ID=ce607171-1d05-40bd-b1ca-65fd9ffb27df REPO=edburns/dd-3072973-cargotracker-control-01 BASE_BRANCH=experiment/shepherd-control BASE_REMOTE=origin PARENT_ISSUE=1 PLAN_DIRECTORY=1-arrival-deadline-control-remove-before-merge ISSUE_NUMBERS=2,3,4,5,6
```

The repository owner is a user account, so organization issue types are unavailable; the children were created **without an issue type**.

---

<sub>Generated by [GitHub Copilot CLI](https://github.com/features/copilot/cli)</sub>
