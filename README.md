# Pib Task Tracker

## Quick start

Download the executable jar file `pib.jar` and double-click to start the app!

---

## What is Pib?

Pib is a simple and lightweight task-tracker for you!

You will be able to use it both from the CLI and the GUI.

_Pib auto-saves everytime you make a change and when you quit!_

---

## Commands


### General
- `list`
  - **Display the list of all your tasks**
- `bye`
   - **Exits the application**
---
### Create tasks
- `todo`
  - **Adds a new _todo_ task to your list**
  - Format: `todo <task description>`
    - _eg._ `todo wash the dishes`
- `deadline`
  - **Adds a new _deadline_ task to your list**
  - Format: `deadline <task description> /by <YYYY-MM-DD> <HHMM>`
    - _eg._ `deadline CS2103 Quiz /by 2021-09-19 2359`
- `event`
  - **Adds a new _event_ task to your list**
  - Format: `event <task description> /at <YYYY-MM-DD> <HHMM>`
    - _eg._ `event swab test /at 2021-09-25 1230`
---
### Handle tasks
- `done`
  - **Marks the indicated task as done**
  - Format: `done <task number>`
    - _eg._ `done 1`
- `delete`
  - **Deletes the indicated task from the list**
  - Format: `delete <task number>`
    - _eg._ `delete 2`
- `edit`
  - **Edits the indicated task and the indicated part**
  - Format: `edit <task number> <part indicator> <new value to replace with>`
    - For `<part indicator>`
      - `/i` replaces the task _Information_ with the new value
      - `/d` replaces the task _Date_ with the new value _(formatted YYYY-MM-DD) (NA for todo)_
      - `/t` replaces the task _Time_ with the new value _(formatted HHMM) (NA for todo)_
    - _eg._ `edit 3 /i study`
- `find`
   - **Displays all tasks which contain the indicated word (case-sensitive)**
   - Format: `find <query>`
      - _eg._ `find CS21`
---