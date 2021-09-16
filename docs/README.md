# User Guide

Ponyo is a desktop app for managing your todos, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

**💡 Notes about the command format:**
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `todo TASK`, `TASK` is a parameter which can be used as `todo english homework`.

## Features 

### Adding a todo: `todo`/`t`

Adds a todo to the task list.

*Format:* `todo TASK` / `t TASK`

*Examples:*
* `todo math homework`
* `t math homework`

### Adding a deadline: `deadline`/`d`

Adds a deadline to the task list.

*Format:* `deadline TASK /by DATE` / `d TASK /by DATE`

*Examples:*
* `deadline apply for scholarship /by 2020-09-20`
* `d apply for scholarship /by 2020-09-20`

### Adding an event: `event`/`e`

Adds an event to the task list.

*Format:* `event EVENT /at DATE` / `e EVENT /at DATE`

*Examples:*
* `event club meeting /at 2020-10-01`
* `e club meeting /at 2020-10-01`

### Listing all tasks: `list`/`l`

Lists all completed and uncompleted tasks.

*Format:* `list`/`l`

### Marking a task as done: `done`

Marks a task as done.

*Format:* `done INDEX`
* Marks the task as done at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

*Examples:*
* `list` followed by `done 1` marks the 1st task as done in the address book.

### Delete a task `delete`/`del`

Deletes the specified task from the task list.

*Format:* `delete INDEX` / `del INDEX`
* Deletes the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

*Examples:*
* `list` followed by `delete 1` deletes the 1st task in the address book.
* `l` followed by `del 1` deletes the 1st task in the address book.

### Locating a task by name: `find`/`f`

Finds persons whose names contain any of the given keywords.

*Format:* `find KEYWORD` / `f KEYWORD`
* Half-words, full words, single letters will all be matched.

*Examples:*
* `find soccer` returns `soccer training`

### Exiting the program: `exit`/`bye`

Exits the program.

*Format:* `exit` / `bye`


### Saving the data

Ponyo's tasks data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
