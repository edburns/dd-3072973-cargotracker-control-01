#!/usr/bin/env bash

set -Eeuo pipefail

REPO='edburns/dd-3072973-cargotracker-control-01'
PARENT_ISSUE=1
LOG_DIRECTORY='/home/edburns/workareas/dd-3072973-cargotracker-control-01-shepherd-control/1-arrival-deadline-control-remove-before-merge/prompts/shepherd-task-20-20261003-0037'
BODY_DIRECTORY="$LOG_DIRECTORY/issue-bodies"
LEDGER="$LOG_DIRECTORY/creation-ledger.json"
RESULT="$LOG_DIRECTORY/stage-20-result.json"
FINAL_CHILDREN="$LOG_DIRECTORY/final-children.json"
ISSUE_BODY_VERIFIER='/home/edburns/.copilot/plugins/shepherd-task/scripts/verify-github-issue-body.sh'
CHILD_LINK_VERIFIER='/home/edburns/.copilot/plugins/shepherd-task/scripts/verify-stage20-child-links.sh'

subsections=(
  '4.1 — Issue 1: Add the application-layer deadline change operation'
  '4.2 — Issue 2: Expose deadline changes through the booking facade'
  '4.3 — Issue 3: Implement the deadline editor backing model'
  '4.4 — Issue 4: Implement the PrimeFaces deadline dialog'
  '4.5 — Issue 5: Integrate deadline editing into the Administration dashboard'
)
titles=(
  '4.1 — Add the application-layer arrival deadline change'
  '4.2 — Expose arrival deadline changes through the booking facade'
  '4.3 — Add the arrival deadline editor backing model'
  '4.4 — Add the PrimeFaces arrival deadline dialog'
  '4.5 — Integrate arrival deadline editing into Administration'
)
body_files=(
  "$BODY_DIRECTORY/01-4-1-body.md"
  "$BODY_DIRECTORY/02-4-2-body.md"
  "$BODY_DIRECTORY/03-4-3-body.md"
  "$BODY_DIRECTORY/04-4-4-body.md"
  "$BODY_DIRECTORY/05-4-5-body.md"
)

operation='initialization'
last_created=''
last_subsection=''
last_body_relative=''

atomic_write() {
  local path="$1" content="$2" temp
  temp="$(mktemp "${path}.tmp.XXXXXX")"
  printf '%s\n' "$content" > "$temp"
  mv "$temp" "$path"
}

update_ledger_flag() {
  local number="$1" field="$2" value="$3" updated
  updated="$(
    jq \
      --argjson number "$number" \
      --arg field "$field" \
      --argjson value "$value" \
      'map(if .number == $number then .[$field] = $value else . end)' \
      "$LEDGER"
  )" || return 1
  atomic_write "$LEDGER" "$updated"
}

append_last_created_if_missing() {
  [[ -n "$last_created" ]] || return 0
  local id number title url present updated
  id="$(jq -r '.id' <<<"$last_created")"
  number="$(jq -r '.number' <<<"$last_created")"
  title="$(jq -r '.title' <<<"$last_created")"
  url="$(jq -r '.html_url' <<<"$last_created")"
  present="$(jq --argjson number "$number" 'any(.[]; .number == $number)' "$LEDGER" 2>/dev/null || printf 'false')"
  [[ "$present" == 'true' ]] && return 0
  updated="$(
    jq \
      --arg subsection "$last_subsection" \
      --arg bodyFile "$last_body_relative" \
      --argjson id "$id" \
      --argjson number "$number" \
      --arg title "$title" \
      --arg url "$url" \
      '. + [{
        implementationSubsection: $subsection,
        bodyFile: $bodyFile,
        id: $id,
        number: $number,
        title: $title,
        url: $url,
        body_verified: false,
        linked: false
      }]' \
      "$LEDGER"
  )" || return 1
  atomic_write "$LEDGER" "$updated"
}

reconcile_and_fail() {
  local error="$1" children_output normalized id number linked updated result_json
  trap - ERR
  set +e

  append_last_created_if_missing

  children_output="$(gh api "repos/$REPO/issues/$PARENT_ISSUE/sub_issues" --paginate --slurp 2>&1)"
  if [[ $? -eq 0 ]]; then
    normalized="$(printf '%s' "$children_output" | jq 'if length == 0 then [] elif all(.[]; type == "array") then add else . end')"
    if [[ $? -eq 0 ]]; then
      while IFS= read -r number; do
        id="$(jq --argjson number "$number" -r '.[] | select(.number == $number) | .id' "$LEDGER")"
        linked="$(jq --argjson id "$id" 'any(.[]; .id == $id)' <<<"$normalized")"
        update_ledger_flag "$number" linked "$linked"
      done < <(jq -r '.[].number' "$LEDGER")
    else
      error="$error; reconciliation normalization failed"
    fi
  else
    error="$error; reconciliation query failed: $children_output"
  fi

  result_json="$(
    jq -n \
      --arg error "$operation: $error" \
      '{
        schemaVersion: 1,
        status: "failed",
        ledgerFile: "creation-ledger.json",
        operationError: $error
      }'
  )"
  atomic_write "$RESULT" "$result_json"

  printf 'Stage 20 failed during %s: %s\n' "$operation" "$error" >&2
  if [[ "$(jq 'length' "$LEDGER" 2>/dev/null)" == '0' ]]; then
    printf 'No issues were created; no cleanup is required.\n' >&2
  else
    jq -r '.[] | "issue #\(.number) | \(.title) | \(.url) | \(.bodyFile) | body_verified=\(.body_verified) | linked=\(.linked)"' "$LEDGER" >&2
    jq -r --arg repo "$REPO" '.[] | "gh issue delete \(.number) --repo \"\($repo)\" --yes"' "$LEDGER" >&2
    printf 'The operation did not complete. No automatic rollback was performed. Delete every issue in the ledger before invoking stage 20 again.\n' >&2
  fi
  exit 1
}

unexpected_error() {
  local code="$1" line="$2"
  reconcile_and_fail "unexpected exit $code at script line $line"
}

trap 'unexpected_error "$?" "$LINENO"' ERR

atomic_write "$LEDGER" '[]'
atomic_write "$RESULT" '{
  "schemaVersion": 1,
  "status": "in_progress",
  "ledgerFile": "creation-ledger.json",
  "operationError": null
}'

for index in "${!body_files[@]}"; do
  subsection="${subsections[$index]}"
  title="${titles[$index]}"
  body_file="${body_files[$index]}"
  body_relative="issue-bodies/$(basename "$body_file")"

  operation="creating $subsection"
  if ! created="$(
    gh api "repos/$REPO/issues" \
      -X POST \
      -f title="$title" \
      -F "body=@$body_file" \
      --jq '{id,number,node_id,html_url,title}'
  )"; then
    reconcile_and_fail 'GitHub issue creation failed'
  fi

  last_created="$created"
  last_subsection="$subsection"
  last_body_relative="$body_relative"
  id="$(jq -r '.id' <<<"$created")"
  number="$(jq -r '.number' <<<"$created")"
  created_title="$(jq -r '.title' <<<"$created")"
  url="$(jq -r '.html_url' <<<"$created")"

  operation="persisting ledger entry for issue #$number"
  append_last_created_if_missing || reconcile_and_fail 'unable to append the created issue to the ledger'

  operation="verifying body for issue #$number"
  if ! issue_json="$(
    "$ISSUE_BODY_VERIFIER" \
      "$REPO" \
      "$number" \
      "$body_file" \
      6 \
      5 \
      "$LOG_DIRECTORY/issue-$number-body-verification-failure.json"
  )"; then
    reconcile_and_fail 'issue body did not match the persisted specification'
  fi
  update_ledger_flag "$number" body_verified true ||
    reconcile_and_fail 'unable to persist body verification'

  operation="linking issue #$number to parent #$PARENT_ISSUE"
  linked=false
  link_error=''
  for attempt in 1 2 3; do
    if link_output="$(
      printf '{"sub_issue_id": %s}' "$id" |
        gh api "repos/$REPO/issues/$PARENT_ISSUE/sub_issues" -X POST --input - 2>&1
    )"; then
      linked=true
      break
    fi
    link_error="$link_output"
    [[ "$attempt" -eq 3 ]] || sleep 2
  done
  [[ "$linked" == true ]] ||
    reconcile_and_fail "link failed after 3 attempts: $link_error"
  update_ledger_flag "$number" linked true ||
    reconcile_and_fail 'unable to persist linked status'

  last_created=''
  last_subsection=''
  last_body_relative=''
  printf 'Created and linked #%s: %s\n' "$number" "$created_title"
done

operation='capturing final child snapshot'
if ! final_output="$(gh api "repos/$REPO/issues/$PARENT_ISSUE/sub_issues" --paginate --slurp 2>&1)"; then
  reconcile_and_fail "final child query failed: $final_output"
fi
if ! final_normalized="$(printf '%s' "$final_output" | jq 'if length == 0 then [] elif all(.[]; type == "array") then add else . end')"; then
  reconcile_and_fail 'final child normalization failed'
fi
atomic_write "$FINAL_CHILDREN" "$final_normalized"

operation='verifying child links and order'
if ! child_verification="$(
  "$CHILD_LINK_VERIFIER" \
    "$LOG_DIRECTORY/pre-creation-children.json" \
    "$FINAL_CHILDREN" \
    "$LEDGER" 2>&1
)"; then
  reconcile_and_fail "child-link postcondition failed: $child_verification"
fi

while IFS= read -r number; do
  body_relative="$(jq --argjson number "$number" -r '.[] | select(.number == $number) | .bodyFile' "$LEDGER")"
  body_file="$LOG_DIRECTORY/$body_relative"
  operation="final body verification for issue #$number"
  if ! issue_json="$(
    "$ISSUE_BODY_VERIFIER" \
      "$REPO" \
      "$number" \
      "$body_file" \
      6 \
      5 \
      "$LOG_DIRECTORY/issue-$number-final-body-verification-failure.json"
  )"; then
    reconcile_and_fail 'final issue body did not match the persisted specification'
  fi

  operation="verifying open and unassigned state for issue #$number"
  if ! issue_state="$(gh api "repos/$REPO/issues/$number" 2>&1)"; then
    reconcile_and_fail "unable to read final issue state: $issue_state"
  fi
  if ! jq -e '.state == "open" and ((.assignees // []) | length == 0)' <<<"$issue_state" >/dev/null; then
    reconcile_and_fail 'issue is not open and unassigned'
  fi
done < <(jq -r '.[].number' "$LEDGER")

operation='writing complete stage result'
atomic_write "$RESULT" '{
  "schemaVersion": 1,
  "status": "complete",
  "ledgerFile": "creation-ledger.json",
  "operationError": null
}'

trap - ERR
jq -r '.[] | "#\(.number) | \(.title) | \(.url)"' "$LEDGER"
