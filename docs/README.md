# User Guide

## Features 

### Keep track of your day-to-day tasks, deadlines and events

Add tasks easily using short and simple commands. 

You can view all your tasks to remind you of what you need to do, mark them as done once you've completed them, and delete them whenever you want!

### Optimised for CLI-style usage

Commands are entirely based on typing with the keyboard. No mouse movement required!

You can also press up and down to scroll through your command history, just like in your favourite command-line application!

### Highly customisable commands

Rename the commands as you wish with the `alias` command! There's no limit to how many aliases you can make!

### Mass delete completed tasks

If you had a particularly productive day, you won't need to delete each task you completed individually. This application supports mass deleting tasks you have done, or tasks that have passed.

## Usage

### `todo` - Add a to-do

Format: `todo <name>`

Adds a to-do with the specified name. A to-do represents a general task to be done.

Example of usage: 

`todo Buy groceries`


Add a to-do into the task list.

```
Got it. I've added this task:
  [T] [✗] Buy groceries
Now you have 1 task in the list.
```


### `deadline` - Add a deadline

Format: `deadline <name> /by <date in YYYY-MM-DD format>`

Adds a deadline with the specified name and date. A deadline represents a task that has to be done by that date.


Example of usage:

`deadline CS2103T iP /by 2021-09-17`


Add a deadline into the list.

```
Got it. I've added this task:
  [D] [✗] CS2103T iP (by: 2021-09-17)
Now you have 2 tasks in the list.
```


### `event` - Add an event

Format: `event <name> /at <date in YYYY-MM-DD format>`

Adds an event with the specified name and date. An event represents an event happening on the specified date. 

Example of usage:

`event Job interview /at 2020-09-18`

Add an event into the task list.

```
Got it. I've added this task:
  [E] [✗] Job interview (at: 2020-09-18)
Now you have 3 tasks in the list.
```


### `list` - List all tasks

Format: `list`

Lists all tasks added so far, in the order that they were listed.

Example of usage:

`list`

Show a list of all the tasks added so far.

```
1. [T] [✗] Buy groceries
2. [D] [✗] CS2103T iP (by: 2021-09-17)
3. [E] [✗] Job interview (at: 2020-09-18)
```

### `find` - Finds all tasks with a matching name

Format: `find <query string>`

Finds all tasks whose name includes the query string.

Performs a case-insensitive search.

Example of usage:

`find groceries`

Finds all tasks whose name contains "groceries"

```
Here are the matching tasks in your list:
  1. [T] [✗] Buy groceries
```
---
`find ie`

Finds all tasks whose name contains "ie"

```
Here are the matching tasks in your list:
  1. [T] [✗] Buy groceries
  3. [E] [✗] Job interview (at: 2020-09-18)
```

### `done` - Mark a task as done

Format: `done <task number>`

Marks the task with that task number as done. The task number is the number on the left of the task when `list` or `find` is called.

Example of usage:

`done 1`

The first task is marked as done.

```
Nice! I've marked this task as done:
  [T] [✓] Buy groceries
```

### `delete` - Delete a task

Format: `delete (<task number> | done | expired)`

If used with a number as the second word, deletes the task with the task number. The task number is the number on the left of the task when `list` or `find` is called.

If used with `done` as the second word, deletes all tasks that have been marked as done

If used with `expired` as the second word, deletes all events and deadlines with dates that have already passed.

All tasks with a greater task number than the deleted goal will have their task numbers reduced by 1 to maintain a continuous series of task numbers.

Example of usage:

`delete 2`

Deletes the second task from the list. The third task now becomes the new second task.

```
Noted! I've removed this task:
  [D] [✗] CS2103T iP (by: 2021-09-17)
Now you have 2 tasks in the list.
```
---
`delete done`

Deletes all tasks that have been marked as done.

```
Noted! I've removed all completed tasks.
Now you have 1 task in the list.
```
---
`delete expired`

Deletes all events and deadlines whose date has passed.

```
Noted! I've removed all expired tasks.
Now you have 0 tasks in the list.
```

### `alias` - Add an alias for a command

Format: `alias <existing command> <alias>`

Sets a new alias that can be used instead to execute the command.

`existing command` can be another existing alias.

Example of usage:

`alias todo td`

Adds td as an alias for todo.

```
td added as an alias for todo!
```
---

`alias td t`

Adds t as an alias for td, which is an alias for todo.

```
t added as an alias for todo!
```

### `bye` - Exits the application

Format: `bye`

Exits the application and closes the window.

Example of usage:

`bye`