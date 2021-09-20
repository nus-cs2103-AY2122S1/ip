# User Guide

UhtredRagnarson is a **desktop app for managing your tasks, optimized for use via only a keyboard** while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, UhtredRagnarson can get your task management done faster than traditional GUI apps.

* [Quick Start](#quick-start)<br>
* [Features](#features)
  - [Viewing help](#viewing-help-help)
  - [Listing all tasks](#listing-all-tasks-list)
  - [Adding a to-do task](#adding-a-to-do-task-todo)
  - [Adding a deadline task](#adding-a-deadline-task-deadline)
  - [Adding an event task](#adding-an-event-task-event)
  - [Marking a task as done](#marking-a-task-as-done-done)
  - [Deleting a task](#deleting-a-task-delete)
  - [Finding a task](#finding-a-task-in-the-list-find)
* [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ip.jar` from [here](https://github.com/tetrerox/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for the app.

1. Double-click the file to start the app. A GUI should appear in a few seconds.

1. Type the command in the command box and press Enter to execute it.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Features

**:information_source: Notes about the command format:**<br>

* You need to follow the **exact** format given

* Words in the `[]` brackets are to be replaced by user inputs. <br>
  For example, for `todo [description]`, the user input will be `todo borrow books`.

### Viewing help: `help`

Shows a message explaining how to access the help page.

Format: `help`

### Listing all tasks: `list`

Shows a list of all tasks.

Format: `list`

### Adding a to-do task: `todo`

Adds a to-do task to your list of tasks.

Format: `todo [description]`

Examples:
* `todo borrow books`
* `todo watch the last season of Lucifer`

### Adding a deadline task: `deadline`

Adds a deadline task to your list of tasks.

Format: `deadline [description] /by [yyyy-mm-dd hh:mm]`

Examples:
* `deadline assignment 1 /by 2021-09-22 14:00`
* `deadline essay /by 2021-11-21 23:59`

### Adding an event task: `event`

Adds an event task to your list of tasks.

Format: `event [description] /at [yyyy-mm-dd hh:mm to hh:mm]`

Examples:
* `event project meeting /at 2021-09-22 20:00 to 22:00`
* `event exam /at 2021-11-23 09:00 to 11:00`

### Marking a task as done: `done`

Marks a task as done with a cross in the list.

Format: `done [index shown by the list command]`

Example:
* `done 2`

### Deleting a task: `delete`

Deletes a task from the list of tasks.

Format: `delete [index shown by the list command]`

Example:
* `delete 1`

### Finding a task in the list: `find`

Finds a task in the task list which contains the **EXACT** keyword. If the keyword is `books`,
it will find tasks with descriptions that at least contain the keyword `books`.

Format: `find [KEYWORD]`

Example:
* `find exam`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Help** | `help`
**List** | `list`
**Add To-Do** | `todo [description]`
**Add Deadline** | `deadline [description] /by [yyyy-mm-dd hh:mm]`
**Add Event** | `event [description] /at [yyyy-mm-dd hh:mm to hh:mm]`
**Done** | `done [index shown by the list command]`
**Delete** | `delete [index shown by the list command]`
**Find** | `find [KEYWORD]`
