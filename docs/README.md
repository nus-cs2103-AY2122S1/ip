# User Guide

## Features 

### Add Todos, Events and Deadlines

Keep track of your upcoming tasks with Duke's Todos, Events and Deadlines.

### Keep track of completed tasks

Mark your tasks as done to keep track which tasks you have completed so far.

### Easily find your tasks
Search for tasks using the `find` command to find your tasks easily!

## Usage

### `list` - Lists all tasks

Lists out all the tasks.

Example of usage:

`list`

The current list of tasks will be displayed


```
1.[T][ ] todo1
2.[E][X] event2 (at: mon 2-4pm)
3.[D][ ] deadline3 (by: 01 January 2021)
```

### `todo` - Adds a todo to your list of tasks

Adds a todo with a given description to the list of tasks

Example of usage: 

`find DESCRIPTION`

The todo will be added and the message below will be shown.

```
Got it, I've added this task
[T][ ] DESCRIPTION
```
### `deadline` - Adds a deadline to your list of tasks

Adds a deadline with a given description and deadline to the list of tasks

Example of usage:

`deadline /by YYYY-MM-DD`

The event will be added and the message below will be shown.

```
Got it, I've added this task
[D][ ] DESCRIPTION (by: DATE MONTH YEAR)
```
### `event` - Adds an event to your list of tasks

Adds an event with a given description and timeframe to the list of tasks

Example of usage:

`event /at TIMEFRAME`

The event will be added and the message below will be shown.

```
Got it, I've added this task
[E][ ] DESCRIPTION (at: TIMEFRAME)
```
### `delete` - Deletes a task from the task list

Deletes a task at the given index from the task list

Example of usage:

`delete INDEX`

Task at index will be deleted.

### `done` - Marks a task as completed

Marks a task at the given index as completed

Example of usage:

`done INDEX`

Task at index will be marked as done.

### `find` - Finds all tasks that match a query

Finds all tasks with description that matches the query.

Example of usage:

`find QUERY`

All tasks with description that matches the query will be displayed.

### `update` - Updates a task

Updates a task at the given index with the provided information.

Example of usage:

If task at INDEX is a todo,

`update INDEX DESCRIPTION`

If task at INDEX is a deadline,

`update INDEX DESCRIPTION/YYYY-MM-DD`

If task at INDEX is an event,

`update INDEX DESCRIPTION/TIMEFRAME`

Task will be updated with the given information

