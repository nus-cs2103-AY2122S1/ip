# User Guide

## Features 

### Add Task

This feature adds a task (be it a Todo, a Deadline or an Event) to the list.

### Delete Task

This feature deletes a task from the list.

### Show All Tasks

This feature displays all tasks in the list.

### Mark Task As Done

This feature marks a task as done, indicated by `[x]`.

### Find Task

This feature shows task(s) which contains the keyword given.

### Undo

This feature lets you undo the latest command (works for add, delete and mark as done!)

## Usage

### `ls` - Show Task

This displays all tasks in the list.

Example of usage: 

`ls`

Expected outcome:

```
1. [T][] read
2. [T][x] write
3. [E][] movie night (at: 18 Sep 2021)
```

### `todo/deadline/event [description] [date and/or time]` - Add Task

This creates a Task in the list.

Examples of usage: 

`todo [description]`
`deadline [description] /by [yyyy-MM-dd] [HH:mm]`
`event [description] /at [yyyy-MM-dd] [HH:mm]`

Expected outcome:

```
added: [T][] task
Now you have 4 tasks in the list.
```

### `delete/done [index]` - Delete Task/Mark Task As Done

This deletes a task or marks a task as done.

Examples of usage: 

`delete 3`
`done 2`

Expected outcome:

```
Noted. I've removed this task:
[D][] iP (by: 23:59)
Now you have 2 tasks in the list.
```

### `find [keyword(s)]` - Find Task(s)

This shows task(s) that contain the keyword(s).

Examples of usage: 

`find read`
`find event`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][] read
```

### `undo` - Undo Command

This lets you undo the previous command. (works only for add, delete and mark as done)

Example of usage: 

`undo`

Expected outcome:

```
You've gone back in time!
1. [T][] read
2. [T][x] write
3. [E][] movie night (at: 18 Sep 2021)

```
