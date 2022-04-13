# User Guide

## Features 

### `list`

Print a list of all of your tasks.

### `done`

Mark a task as done.

### `delete <index>`

Delete a task.

### `todo <description>`

Add a Todo task.

### `deadline <description> /by <date>`

Add a Deadline task.

### `event <description> /at <date>`

Add an Event task.

### `find <query>`

Finds a task in the list, given a search query.

## Usage

### `list` - Print list of all tasks

This command prints a list of all of your tasks.

**Example of usage**:

`list`

**Expected outcome**:

A list of all tasks, with task type and status icons in the following format:
```
Here are the tasks in your list:
1. [T][ ] foobar
2. [D][X] foo (by: Oct 8 2021)
3. [E][ ] derp (at: Feb 2 2021)
```

The task type is represented by the following: `[T]` indicates a Todo task, `[D]` indicates a Deadline task
and `[E]` indicates an Event task.

### `done <index>` - Mark a task as done

This command marks a task as done.

**Example of usage**:

`done 1` - marks the first task in the list as done

**Expected outcome**:

The status of the task is modified to done. (If this task was already marked as done, there is no change to
the status.)

### `delete <index>` - Delete a task

This command deletes a task from the list.

**Example of usage**:

`delete 2` - deletes the second task in the list

**Expected outcome**:

The second task in the list is deleted.

### `todo <description>` - Add a Todo task

This command adds a Todo task.

**Example of usage**:

`todo Eat a banana` - creates a new Todo task with description "Eat a banana"

**Expected outcome**:

A new Todo task is created and added to the bottom of the list.

### `deadline <description> /by <date>` - Add a Deadline task

This command adds a Deadline task.

**Example of usage**:

`deadline Clean room /by 2022-04-20` - creates a new Deadline task with description "Clean room" and a
deadline of 2022-04-20.

**Expected outcome**:

A new Deadline task is created and added to the bottom of the list.

### `event <description> /at <date>` - Add an Event task

This command adds an Event task.

**Example of usage**:

`event Project meeting /at 2022-04-20` - creates a new Event task with description "Project meeting" and a
date of 2022-04-20.

**Expected outcome**:

A new Event task is created and added to the bottom of the list.

### `find <query>` - Find a task

This command finds a task in the list, given a search query.

**Example of usage**:

`find meeting` - returns all tasks in the list with description containing the text "meeting"

**Expected outcome**:

A list of relevant tasks is printed.
