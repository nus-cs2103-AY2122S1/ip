# User Guide

## Features 

### Add Task

Add a new task (todo, deadline or event) to your task list.

### Delete Task

Delete a task from your task list.

### Complete Task

Mark a task in your task list as completed.

### Display List

Display your task list.

### Find Task

Search for the task(s) according to an input keyword.

### Tag Task

Tag a task with a label.

## Usage

### `todo` - Add a todo

Add a 'todo' task into your task list. The task should be stored in your task list.

Example of usage: 

`todo homework (# math)`

Expected outcome:

Your task should be added and the total number of tasks in your task list should appear.

```
one more thing: [T][ ] homework
Now you got 1 thing(s). sian
```

### `deadline` - Add a deadline

Add a 'deadline' task into your task list. The task should be stored in your task list.

Example of usage: 

`deadline homework /by 15/09/21 23:59 (# math)`

Expected outcome:

Your task should be added and the total number of tasks in your task list should appear.

```
one more thing: [D][ ] homework 15/09/21 23:59
Now you got 1 thing(s). sian
```

### `event` - Add an event

Add an 'event' task into your task list. The task should be stored in your task list.

Example of usage: 

`event birthday /at 15/09/21 18:00 (# party)`

Expected outcome:

Your task should be added and the total number of tasks in your task list should appear.

```
one more thing: [E][ ] birthday 15/09/21 18:00
Now you got 1 thing(s). sian
```

### `delete` - Delete a task

Deletes a task from your task list. The task should be removed from your task list.

Example of usage: 

`delete 1`

Expected outcome:

The first task should be deleted and the new total number of tasks in your task list should appear.

```
this one no more liao ah:
[T][ ] homework
Now you got 0 thing(s). sian
```

### `done` - Mark a task as completed

Mark a task as completed. The task should have a `X` beside its type.

Example of usage: 

`done 1`

Expected outcome:

The first task should be marked as completed.

```
noice this thing done:
[T][X] homework
```

### `list` - Display your task list

The tasks in your task list should be displayed.

Example of usage: 

`list`

Expected outcome:

Your task list should be displayed.

```
1. [T][ ] homework
2. [D][ ] assignment (by: 15/09/21 23:59)
```

### `find` - Find task(s)

Find task(s) according to an input keyword or tag.

Example of usage: 

`find math`

Expected outcome:

A list of tasks containing the keyword or tag should be displayed.

```
all these similar one:
1. [T][ ] math homework
2. [D][ ] assignment (by: 15/09/21 23:59) # math
```
