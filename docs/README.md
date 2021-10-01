# User Guide

## Features 

### Add tasks

Add various tasks. (Todo, Events, Deadlines).

### Delete tasks

Delete tasks if added by mistake.

### Tracks status of tasks

Mark the task as completed.

### Display tasks

Load list of existing task.

### Find task

Search tasks that correspond to a keyword

## Usage

### `todo [task description]` - Adds a todo

A new todo with the description behind will be added

Example of usage: 

`todo shave`

Expected outcome:

```
Got it. I've added this task:
    [T][] shave
Now you have 1 tasks in the list
```
---
### `deadline [task description] /by [YYYY-MM-DD] [TIME]` - Adds a deadline

A new deadline with the description, date and time will be added

Example of usage:

`deadline assignment 1 /by 2020-02-02 1700`

Expected outcome:

```
Got it. I've added this task:
    [D][] assignment 1 (by: Feb 3 2020 1700)
Now you have 2 tasks in the list
```
---
### `event [task description] /at [YYYY-MM-DD]` - Adds an event

A new event with the description, date be added

Example of usage:

`event cca lunch /at 2020-02-04`

Expected outcome:

```
Got it. I've added this task:
    [E][] cca lunch (at: Feb 4 2020)
Now you have 3 tasks in the list
```
---

### `list` - List all existing task

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][] shave
2.[D][] assignment 1 (by: Feb 3 2020 1700)
3.[E][] cca lunch (at: Feb 4 2020)
```
---
### `done [index]` - Mark certain task as done

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][X] shave
```
---
### `Find [description]` - Find task with same description

Searches all existing task to see if there is a match.

Example of usage:

`find lunch`

Expected outcome:

```
Here are the matching tasks in your list:
1.[E][] cca lunch (at: Feb 4 2020)
```
---
### `Delete [index]` - Delete task based on index

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
    [D][] assignment 1 (by: Feb 3 2020 1700)
You now have 2 tasks left in the list
```

---

### `bye` - Exit the programme

Example of usage:

`bye`

Expected outcome:

```
Programme closes
```
