# User Guide

## Features 
Lania is a task management application for users to keep track of various tasks
that can include date and time for deadlines and events. All commands automatically
updates the hard disk. It is easy to use and comes with a user-friendly GUI to 
provide users with a positive and efficient experience while using the application.

![Lania](Ui.png)

### Add Task

3 types of tasks are supported by Lania - *Todo*, *Deadline* and *Event*.
You can add a task of any type specified by a description and additionally a
date/time format for *Deadline* and *Event*.

### Complete Task
You can mark a task as done (`[X]`).

### Delete Task
You can delete a specific task by its numbering on the task list.

### Find Tasks
You can find a list of tasks matching your keyword in your task list.

### List Tasks
You can view your list of tasks.

### Undo
You can undo your previous *add*, *complete* and *delete* task commands.

## Usage

### `todo` - Adding a Todo

Adds a task of type todo to the task list with a description.

Format: `todo DESCRIPTION`

Example of usage: 

`todo Exercise`

Expected outcome:

```
Lania has added: 
[T][ ] Exercise
Great! Now you have 1 tasks in your list.
```

### `deadline` - Adding a Deadline

Adds a task of type deadline to the task list with a description and date/time format.

Format: `deadline DESCRIPTION /by dd-mm-yyyy hh:mm`

Example of usage:

`deadline Homework /by 09-14-2021 23:59`

Expected outcome:

```
Lania has added: 
[D][ ] Homework (by: Sep 14 2021 11:59PM)
Great! Now you have 2 tasks in your list.
```

### `event` - Adding an Event

Adds a task of type event to the task list with a description and date/time format.

Format: `event DESCRIPTION /by dd-mm-yyyy hh:mm`

Example of usage:

`event Midterms /at 09-14-2021 23:59`

Expected outcome:

```
Lania has added: 
[E][ ] Midterms (at: Oct 4 2021 1:00PM)
Great! Now you have 3 tasks in your list.
```

### `list` - Lists all tasks

Lists all tasks in the user's task list.

Format: `list`

Expected outcome:

```
You have the following task(s):
1.[T][ ] Exercise
2.[D][ ] Homework (by: Sep 14 2021 11:59PM)
3.[E][ ] Midterms (at: Oct 4 2021 1:00PM)
```

### `find` - Locating tasks by description or date/time

Retrieves a list of tasks matching the given keyword.

Format: `find DESCRIPTION`

Example of usage:

`find Midterms`

Expected outcome:

```
You have the following task(s):
1.[E][ ] Midterms (at: Oct 4 2021 1:00PM)
```

### `done` - Locating tasks by description or date/time

Marks a task in the task list as complete by the given index.

Format: `done NUMBER`

Example of usage:

`done 1`

Expected outcome:

```
Good job! Lania has marked this task as done:
[T][X] Exercise
```

### `delete` - Removes a task

Removes a task in the task list by the given index.

Format: `delete NUMBER`

Example of usage:

`delete 1`

Expected outcome:

```
Ok, Lania has removed this task:
[T][X] Exercise
```

### `undo` - Undo a previous command

Undo a `todo`, `deadline`, `event`, `done` or `delete` command. Does not work for commands after the application is closed.

Format: `undo`

Expected outcome when `undo` command is executed twice separately:

```
Lania has added: 
[T][X] Exercise
Great! Now you have 3 tasks in your list.

Lania has marked this task as undone:
[T][ ] Exercise
```

### `bye` - Exits the program

Format: `bye`

Expected outcome:

```
Bye. Lania looks forward to seeing you again!
```