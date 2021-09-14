# User Guide

## Features 

### Getting help

* Lists all commands.

### Adding a Todo task

* Adds a Todo task to the list of tasks.

### Adding a Deadline task

* Adds a Deadline task to the list of tasks.

### Adding an Event task

* Adds an Event task to the list of tasks.

### Listing all tasks 

* Lists all tasks.

### Marking a task as Done

* Marks a task as done

### Deleting a task

* Deletes a task.

### Getting all tasks on a date

* Lists all tasks falling on the specified date.

### Finding all tasks containing a word/phrase

* Lists all tasks containing the specified word or phrase.

## Usage

### `help` - Lists all commands

Lists all commands.

Expected outcome:

All commands listed.

```
Commands:
1. ...
```

### `todo TASK_DESCRIPTION` - Adds a todo task

Adds a todo task.

Example of usage:

`todo Read CS2103 Textbook`

Expected outcome:

Todo task added to list of tasks.

```
I've added this task:
[T] [] TASK_DESCRIPTION
```

### `deadline TASK_DESCRIPTION /by DATE` - Adds a deadline task

Adds a deadline task on `DATE`.

`DATE` must be in the form yyyy-mm-dd

Example of usage:

`deadline CS2103 Project /by 2021-09-17`

Expected outcome:

Deadline task added to list of tasks.

```
I've added this task:
[D] [] TASK_DESCRIPTION (by: DATE)
```

### `event TASK_DESCRIPTION /on DATE` - Adds an event task

Adds an event task on `DATE`.

`DATE` must be in the form yyyy-mm-dd

Example of usage:

`event CS2103 Class /at 2021-09-10`

Expected outcome:

Event task added to list of tasks.

```
I've added this task:
[E] [] TASK_DESCRIPTION (at: DATE)
```

### `list` - Lists all tasks

Lists all tasks.

Expected outcome:

List of all tasks.

```
Here are the tasks in your list:
1. ...
```

### `done TASK_INDEX` - Marks a task as done

Marks task at `TASK_INDEX` as done.

`TASK_INDEX` must be a positive integer 1, 2, 3, ...

Example of usage:

`done 2`

Expected outcome:

Task at `TASK_INDEX` is marked as done.

```
Nice! I've marked this task as done:
[TASK_TYPE] [X] TASK_DESCRIPTION
```

### `delete TASK_INDEX` - Deletes a task

Deletes task at `TASK_INDEX`.

`TASK_INDEX` must be a positive integer 1, 2, 3, ...

Example of usage:

`delete 3`

Expected outcome:

Task at `TASK_INDEX` is deleted.
```
Alright! I've removed this task:
...
```

### `get DATE` - Finds all tasks on a specified date

Finds all tasks on `DATE` and returns them.

`DATE` must be in the form yyyy-mm-dd

Example of usage:

`get 2021-10-07`

Expected outcome:

All tasks with `DATE` as their date.

```
Here are the matching tasks in your list:
...
```

### `find PHRASE` - Finds all tasks containing specified words

Finds all tasks containing `PHRASE` and returns them.

Example of usage:

`find CS2103`

Expected outcome:

All tasks containing `PHRASE`.

```
Here are the matching tasks in your list:
...
```