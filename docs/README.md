# User Guide

**EightBit** is a desktop app for tracking tasks, optimized for use via a Command Line Interface (CLI).

* [Quick start](#quick-start)
* [Features](#features)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Adding a todo: `todo`](#adding-a-todo-todo)
  * [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  * [Adding an event: `event`](#adding-an-event-event)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Searching a task: `find`](#searching-a-task-find)
  * [Tagging a task: `tag`](#tagging-a-task-tag)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command summary](#command-summary)

## Quick start

1. Ensure you have Java 11 or above installed in your computer.
2. Download the latest `eightbit.jar` from [here](https://github.com/pualixue/ip/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for EightBit.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it.<br/>
Refer to the [Features](#features) below for details of each command.

## Features 

### Listing all tasks: `list`

Shows a list of all tasks.

Format: `list`

### Adding a todo: `todo`

Adds a todo to the list.

Format: `todo TASK_NAME`

Example: `todo Read book`

### Adding a deadline: `deadline`

Adds a task with a deadline to the list.

Format: `deadline TASK_NAME /by yyyy-mm-dd hh:mm`

Example: `deadline Homework /by 2021-12-31 23:59`

### Adding an event: `event`

Adds an event to the list.

Format: `event TASK_NAME /at yyyy-mm-dd hh:mm`

Example: `event Christmas party /at 2021-12-25 19:00`

### Marking a task as done: `done`

Marks a task as completed.

Format: `done INDEX`

Example:<br/>
Suppose the list contains these tasks:
```
1. [T][ ] Read book
2. [D][ ] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
```
Executing `done 2` will result in the following list:
```
1. [T][ ] Read book
2. [D][X] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
```

### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete INDEX`

Example:<br/>
Suppose the list contains these tasks:
```
1. [T][ ] Read book
2. [D][ ] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
```
Executing `delete 3` will remove the `Christmas party` event from the list.

### Searching a task: `find`

Finds a task containing the keyword. The search is case-insensitive.

Format: `find KEYWORD`

Example:<br/>
Suppose the list contains these tasks:
```
1. [T][ ] Read book
2. [D][ ] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
4. [T][ ] Book tickets
```
Executing `find book` or `find BOOK` both returns the following result:
```
1. [T][ ] Read book
2. [T][ ] Book tickets
```

### Tagging a task: `tag`

Add tags to a task.

Format: `tag INDEX TAG [MORE_TAGS]`
* Multiple tags can be added in a single command.

Example:<br/>
Suppose the list contains these tasks:
```
1. [T][ ] Read book
2. [D][ ] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
```
Executing `tag 1 productivity knowledge fun` adds three tags to the first task and returns the following result:
```
1. [T][ ] Read book #productivity #knowledge #fun
2. [D][ ] Homework (by: 31 Dec 2021 23:59)
3. [E][ ] Christmas party (at: 25 Dec 2021 19:00)
```

### Exiting the program: `bye`

Terminates the program.

Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer?<br/>
**A**: Install the app in the other computer and overwrite 
the empty data file it creates with the file that contains the data of your previous EightBit home folder.

## Command summary

Action | Format, Example
------ | ---------------
Add deadline | `deadline TASK_NAME /by yyyy-mm-dd hh:mm`<br/>Eg. `deadline Homework /by 2021-12-31 23:59`
Add event | `event TASK_NAME /at yyyy-mm-dd hh:mm`<br/>Eg. `event Christmas party /at 2021-12-25 19:00`
Add todo | `todo TASK_NAME`<br/>Eg. `todo Read book`
Delete | `delete INDEX`<br/>Eg. `delete 2`
Done | `done INDEX`<br/>Eg. `done 2`
Exit | `bye`
Find | `find KEYWORD`<br/>Eg. `find book`
List | `list`
Tag | `tag INDEX TAG [MORE_TAGS]`<br/>Eg. `tag 1 productivity knowledge fun`
