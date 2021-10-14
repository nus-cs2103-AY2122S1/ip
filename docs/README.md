# User Guide

# Pib Task Tracker

## Quick start

Download the executable jar file `pib.jar` and double-click to start the app!

## What is Pib?

Pib is a simple and lightweight task-tracker for you!

You will be able to use it both from the CLI and the GUI.

_Pib auto-saves everytime you make a change and when you quit!_

## Commands


### General
- `list`
    - **Display the list of all your tasks**
      - _eg. output:_ `1. [T][ ] Wash the dishes`
- `bye`
    - **Exits the application**
      - _eg. output:_ `Bye! See you next time!`

### Create tasks
- `todo`
    - **Adds a new _todo_ task to your list**
    - Format: `todo <task description>`
        - _eg. input:_ `todo Wash the dishes`
        - _eg. output:_ `Added: Wash the dishes. There are 1 task(s) in the list`

- `deadline`
    - **Adds a new _deadline_ task to your list**
    - Format: `deadline <task description> /by <YYYY-MM-DD> <HHMM>`
        - _eg. input:_ `deadline CS2103 Quiz /by 2021-09-19 2359`
        - _eg. output:_ `Added: CS2103 Quiz. There are 2 task(s) in the list`
- `event`
    - **Adds a new _event_ task to your list**
    - Format: `event <task description> /at <YYYY-MM-DD> <HHMM>`
        - _eg. input:_ `event Swab test /at 2021-09-25 1230`
        - _eg. output:_ `Added: Swab test. There are 3 task(s) in the list`

### Handle tasks
- `done`
    - **Marks the indicated task as done**
    - Format: `done <task number>`
        - _eg. input:_ `done 1`
        - _eg. output:_ `Nice! I've marked this task as done: [X] wash the dishes`
- `delete`
    - **Deletes the indicated task from the list**
    - Format: `delete <task number>`
        - _eg. input:_ `delete 2`
        - _eg. output:_ `Successfully deleted task: CS2103 Quiz`
- `edit`
    - **Edits the indicated task and the indicated part**
    - Format: `edit <task number> <part indicator> <new value to replace with>`
        - For `<part indicator>`
            - `/i` replaces the task _Information_ with the new value
            - `/d` replaces the task _Date_ with the new value _(formatted YYYY-MM-DD) (NA for todo)_
            - `/t` replaces the task _Time_ with the new value _(formatted HHMM) (NA for todo)_
        - _eg. input:_ `edit 3 /i study`
        - _eg. output:_ `Task successfully updated!`
- `find`
    - **Displays all tasks which contain the indicated word (case-sensitive)**
    - Format: `find <query>`
        - _eg. input:_ `find Swab`
        - _eg. output:_ `These tasks contain the word: Swab. 1. [E][ ] Swab test (at: 25 Sep 2021, 12:30 PM)`

---
