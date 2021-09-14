# User Guide

UhtredRagnarson is a **desktop app for managing your tasks, optimized for use via only a keyboard** while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, UhtredRagnarson can get your task management done faster than traditional GUI apps.

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

## Deleting a task: `delete`

Deletes a task from the list of tasks.

Format: `delete [index shown by the list command]`

Example:
* `delete 1`

## Finding a task in the list: `find`

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
