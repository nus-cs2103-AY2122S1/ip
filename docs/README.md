# User Guide

## Features

### Adding a Todo task

Adds a Todo task to the database.

### Adding a Deadline task

Adds a task with a deadline to the database.

### Adding an Event task

Adds a task with a time to the database.

### Marking a task as done

Marks a task as done.

### Deleting a task

Deletes a task from the database.

### Listing all tasks

Shows a list of all tasks stored in the database.

### Finding tasks by a keyword

Finds tasks which match the given keyword.

### Finding tasks by date

Finds Deadline and Event tasks which match the given date.

## Usage

### `td` - Adds a Todo task

Adds the given Todo task to the database. The new task will be shown on the list of tasks.

Example of usage:

`td finish up cs2103t user guide`

Expected outcome:

A new task labelled "T" along with the task's description is added to the list of tasks.

```
Lollipop: [T][] finish up cs2103t user guide has been added.
```

### `dl` - Adds a Deadline task

Adds the given Deadline task to the database. The new task will be shown on the list of tasks.

Example of usage:

`dl submit assignment 3 /by 2020-11-16`

Expected outcome:

A new task labelled "D" along with the task's description and deadline is added to the list of tasks.

```
Lollipop: [D][] submit assignment 3 (by: 16 November 2020) has been added.
```

### `ev` - Adds a Event task

Adds the given Event task to the database. The new task will be shown on the list of tasks.

Example of usage:

`ev attend meeting /at 2021-08-02`

Expected outcome:

A new task labelled "E" along with the task's description and time is added to the list of tasks.

```
Lollipop: [E][] attend meeting (at: 02 August 2021) has been added.
```

### `d` - Marks a task as done

Marks the task associated with the given task index as done. The marked task will be shown on the list of tasks with an "X".

Example of usage:

`d 1`

Expected outcome:

The task associated with the given task index is labelled with an "X".

```
Lollipop: [D][X] team meeting (at: 01 October 2021) has been marked as done.
```

### `rm` - Deletes a task

Deletes the task associated with the given task index as done. The deleted task will be removed from the list of tasks.

Example of usage:

`rm 1`

Expected outcome:

The task associated with the given task index is removed from the list of tasks.

```
Lollipop: [D][X] buy book (by: 30 December 2020) has been deleted.
```

### `ls` - Lists all tasks

Lists all the task in the database.

Example of usage:

`ls`

Expected outcome:

Prints a list of all tasks. Tasks are either labelled "T", "D" or "E", for Todos, Deadlines and Events respectively. Marked tasks are labelled with an "X". Following the task descriptions is either a deadline or an event time.

```
Lollipop: Here are your tasks
1. [E][X] team meeting (at: 01 October 2021)
2. [T][] finish up script for presentation
3. [D][] finish up cs2103t user guide (by: 16 November 2021)
```

### `ff` - Finds all tasks with a keyword

Finds all tasks that matches the given keyword and prints them as a list.

Example of usage:

`ff finish`

Expected outcome:

Prints a list of tasks that contains the given keyword.

```
Lollipop: Here are the tasks that match your keyword:
1. [T][] finish up script for presentation
2. [D][] finish up cs2103t user guide (by: 16 November 2021)
```

### `dt` - Finds all tasks with a date

Finds all tasks that contains the given date and prints them as a list.

Example of usage:

`dt 2021-11-16`

Expected outcome:

Prints a list of tasks where the given date is matches the task's deadline or event time.

```
Lollipop: Here are the tasks that occurs on the specified date:
1. [D][] finish up cs2103t user guide (by: 16 November 2021)
```

### `q` - Quits the program

Quits the program.

Example of usage:

`q`
