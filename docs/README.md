# DukePro User Guide
---
Quick Start:
- 
1. download it from [here](https://github.com/noobmaster19/ip/releases/download/v1/ip.jar)
2. double-click it.
3. Refer to the features section on how to use it
---

Features:
- 
- Adding a Todo: `todo` 
  - Adds a todo task to duke.
  - Format: `todo {todoName}`
  - Examples:
    `todo study`


- Adding a Deadline: `deadline`
    - Adds a task with deadline to duke.
    - Format: `deadline {deadLineName} /by {yyyy-mm-dd}`
    - Examples:
      `deadline test /by 2021-01-16`


- Adding a Event: `event`
    - Adds a task with date to duke.
    - Format: `event {eventName} /at {yyyy-mm-dd}`
    - Examples:
      `event test /at 2021-01-16`


- Deleting a task: `delete`
    - deletes a task (includes todos,events and deadlines) from duke.
    - Format: `delete {task index}`
    - Index starts from 1
    - Examples:
      `delete 1`


- Completing a task: `Done`
    - Marks  a task (includes todos,events and deadlines) as done.
    - Format: `done {task index}`
    - Index starts from 1
    - Examples:
      `done 1`


- Find all tasks based on substring: `find`
    - Finds all tasks (includes todos,events and deadlines) that has a specific substring.
    - Format: `find {subString}`
    - Examples:
      `find test`


- Find all tasks based on specific date: `getat`
  - Finds all tasks (includes todos,events and deadlines) that has a specific date.
  - Format: `getat {yyyy-mm-dd}`
  - Examples:
    `getat 1999-01-01`


- List all tasks: `list`
    - Lists out all tasks in Duke's memory.
    - Format: `list`
    - Examples:
      `list`


- Exiting: `bye`
    - Exits duke
    - Format: `bye`
    - Examples:
      `bye`


---