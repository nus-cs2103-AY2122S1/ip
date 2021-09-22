# User Guide

## Features

1. Add a todo task with a specified description in the list.
2. Add a deadline task with a specified description and due date in the list.
3. Add an event task with a specified description and event date in the list.
4. Mark the task at a specified index in the list as done.
5. List out all the tasks in the list in the same order as they were entered.
6. Delete the task at a specified index in the list.
7. Find tasks with name that contains a specified keyword.
8. Undo the last operation.
9. Redo the last operation.
10. Exit the program.

## Usage

### Add Todo Task: `todo`
Adds a new todo task to the list.

Format: `todo <description>`

⚠️Ensure `description` is not empty.

Example of usage:

* `todo read book` adds the todo task with description `read book`.

### Add Deadline Task: `deadline`
Adds a new deadline task to the list.

Format: `deadline <description> /by <due_date>`

⚠️Ensure `description` is not empty.

⚠️Ensure  `due_date` is `YYYY-MM-DD`.

Example of usage:

* `deadline return book /by 2021-09-09` adds the deadline task with description `return book` and due date `2021-09-09`.

### Add Event Task: `event`
Adds a new event task to the list.

Format: `event <description> /at <event_date>`

⚠️Ensure `description` is not empty.

⚠️Ensure `event_date` is `YYYY-MM-DD`.

Example of usage:

* `event party after school /at 2021-09-08` adds the event task with description `party after school` and event date `2021-09-08`.

### Mark Task as Done: `done`
Marks the task as done.

Format: `done <index>`

⚠️Ensure  `index` is `1, 2, ..., list_size`.

Example of usage:

* `done 3` marks the third task in the list as done.

### List Tasks: `list`
Lists out all the tasks in the list.

Format: `list`

Example of usage:

* `list` shows all the tasks in the list.

### Delete Task: `delete`
Deletes the task from the list.

Format: `delete <index>`

⚠️Ensure `index` is `1, 2, ..., list_size`.

Example of usage:

* `delete 3` deletes the third task in the list.

### Find Tasks: `find`
Lists out all the tasks that match the keyword.

Format: `find <description>`

Example of usage:

* `find boo` lists tasks that has description containing `boo`.

### Undo operation: `undo`
Undoes the last operation.

Format: `undo`

⚠️Ensure there is a previous operation to undo.

Example of usage:

* `undo` undoes the last operation.

### Redo operation: `redo`
Redoes the last operation.

Format: `redo`

⚠️Ensure there is an undone operation to redo.

Example of usage:

* `redo` redoes the last operation.

### Exit Program: `bye`
Exits the program.

Format: `bye`

Example of usage:

* `bye` exits the program.
