# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your task management tasks done faster 
than traditional GUI apps.

- [Quick start](#quick-start)
- [Features](#features) 
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
  - [Adding an event task: `event`](#adding-an-event-task-event)
  - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Marking a task as done: `done`](#marking-a-task-as-done-done)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding task(s) by keyword: `find`](#finding-tasks-by-keyword-find)
  - [Sorting all deadline tasks: `sort`](#sorting-all-deadline-tasks-sort)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
- [Command summary](#command-summary)

<hr />

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer. 
2. Download the latest duke.jar from [here](https://github.com/xyliew25/ip). 
3. Copy the file to the folder you want to use as the home folder for your Duke program. 
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Image of Duke](Ui.png)

5. Type the command in the command box and press Enter to execute it.
Some example commands you can try:

- `list` : Lists all tasks.
- `todo read book` : Adds a Todo task with a description of read book to the task list.
- `delete 3` : Deletes the 3rd task in the task list.
- `bye` : Exits the program.

Refer to the [Features](#features) below for details of each command.

<hr />

## Features
:information_source: Notes about the command format:

- Words in `UPPER_CASE` are the parameters to be supplied by the user e.g. in `todo TASK_DESCRIPTION`, 
`TASK_DESCRIPTION` is a parameter which is to be used as `todo read book`.

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Adding a todo task: `todo`
Adds a Todo task, with the given task description, to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo read book`

### Adding an event task: `event`
Adds an Event task, with the given task description and date, to the task list.

Format: `event TASK_DESCRIPTION /at DATE`

The `DATE` can be in **ANY** format.

Examples:
- `event go party /at midnight`
- `event attend wedding /at 2021-08-31`

### Adding a deadline task: `deadline`
Adds a Deadline task, with the given task description and date, to the task list.

The `DATE` must be in **yyyy-mm-dd** format.

Format: `deadline TASK_DESCRIPTION /by DATE`

Example:
- `deadline submit assignment /by 2021-09-16`

### Marking a task as done: `done`
Marks the task with the given index in the task list as done.

Format: `done INDEX`

- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3,...

Examples:
- `list` followed by `done 2` marks the 2nd task in the task list as done.
- `find read` followed by `done 1` marks the 1st task in the task list as done, **NOT** in the results of the find command.

### Deleting a task: `delete`
Deletes the task with the given index from the task list.

Format: `delete INDEX`

- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3,... 

Examples:
- `list` followed by `delete 2` deletes the 2nd task from the task list.
- `find read` followed by `delete 1` deletes the 1st task from the task list, **NOT** from the results of the find command.

### Finding task(s) by keyword: `find`
Finds task(s) which contain(s) the given keyword.

Format: `find KEYWORD`

- The search is case-sensitive e.g. read will NOT match Read.
- Only the first given keyword is searched e.g. `find read book` will only find task(s) which contain(s) the keyword
`read`.
- Everything is searched e.g. task description, date.
- Partial words will be matched e.g. re will match read and lecture.

### Sorting all deadline tasks: `sort`
Shows the sorted list of all Deadline tasks in the task list.

Format: `sort`

### Exiting the program: `bye`
Exits the program.

Format: `exit`

<hr />

## Command summary

Action | Format, Examples
--- | ---
**List** | `list`
**Todo** | `todo TASK_DESCRIPTION` e.g. `todo read book`
**Event** | `event TASK_DESCRIPTION /at DATE` e.g. `event go party /at midnight`
**Deadline** | `deadline TASK_DESCRIPTION /by DATE` e.g. `deadline submit assignment /by 2020-09-16`
**Done** | `done INDEX` e.g. `done 1`
**Delete** | `delete INDEX` e.g. `delete 2`
**Find** | `find KEYWORD` e.g. `find read`
**Sort** | `sort`
**Exit** | `bye`
