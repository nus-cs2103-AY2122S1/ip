# Duke User Guide

<p align="center">
    <img src="Ui.png" width="400" alt="Ui demo"/>
</p>

Welcome to Duke! Duke is a simple CLIBot built to assist you in keeping your life in check. Treat it like your personal assistant who can help you to remember all your tasks in your daily life so that you don't need to!

## Features 

### Support for multiple types of Tasks

Duke supports 3 different types of tasks that can be created, depending on your need:

### Todos

Todos are the simplest type of task with just a description and a completion status, similar to what you would see on your own todo list. 

### Events

Events allow you to tag a particular time frame to your tasks. e.g "at COM1-0217, 4-6pm"

### Deadlines

Deadlines are tasks with a hard due date.

### Manage your tasks efficiently

Duke comes with a variety of commands provided to enable efficient management and handling of tasks through a CLI. Check out all available commands below!

## Usage

### `todo <description>` - adds a todo type task

Alias: `t`, `td`

A _todo_ task will be added to your task list.

Example of usage:

`todo Run`
`t Run`

Expected outcome:

```
Got it. I've added this task:
  [T][] Run
Now you have 1 tasks in the list.
```

### `event <description> /at <date>` - adds an event type task

Alias: `e`

An _event_ task will be added to your task list.

Example of usage:

`event CS2103T Lecture /at 2021-09-10 1600`

Expected outcome:

```
Got it. I've added this task:
  [E][] CS2103T Lecture (at: Sep 10 2021 4.00 PM)
Now you have 2 tasks in the list.
```

**Accepted date time format**

Here are the acceptable formats in the date time input:

- yyyy-mm-dd (e.g `2021-09-10`)
- yyyy-mm-dd HHmm (e.g `2021-09-10 1800`)
- any other string literal (e.g `UTown, 7PM`)

### `deadline <desdription> /by <date>` - adds a deadline type task

Alias: `dl`

A _deadline_ will be added to your task list.

Example of usage:

`deadline CS2100 Assignment /by 2021-09-15 1300`

Expected outcome:

```
Got it. I've added this task:
  [D][] CS2100 Assignment (at: Sep 15 2021 1.00 PM)
Now you have 3 tasks in the list.
```

**Accepted date time format**

Similar to _event_ task, here are the acceptable formats in the date time input:

- yyyy-mm-dd (e.g `2021-09-10`)
- yyyy-mm-dd HHmm (e.g `2021-09-10 1800`)
- any other string literal (e.g `UTown, 7PM`)

### `list` - lists out all the task in the task list

Alias: `l`, `ls`

Shows you all your tasks that are currently stored in the task list.

Expected outcome:

```
Here are the tasks in your list:
  1. [T][] Run
  2. [E][] CS2103T Lecture (at: Sep 10 2021 4.00 PM)
  3. [D][] CS2100 Assignment (by: Sep 15 2021 1.00 PM)
```

### `done <index>` - marks a task as completed

Alias: `d`

Marks a task to be completed indicated by the index after the `done` command. The index specified is with reference to its position in the `list` command.

Example of usage:

`done 1`

Expected outcome:

```
Good job on completing this task!
  [T][X] Run
```

### `find <query>` - finds tasks containing the search query

Alias: `f`, `search`

The `find` command is used to search through the tasks and find tasks with descriptions that contains the search query. This command is **case-sensitive**.

Example of usage:

`find CS`

Expected outcome:

```
Here are the matching tasks in your list:
  1. [E][] CS2103T Lecture (at: Sep 10 2021 4.00 PM)
  2. [D][] CS2100 Assignment (by: Sep 15 2021 1.00 PM)
```

### `delete <index>` - removes a task from the task list

Alias: `rm`, `remove`, `del`

The `delete` command uses the same indexing method as `done` command. Deletes the task at the specified index.

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
  [E][] CS2103T Lecture (at: Sep 10 2021 4.00 PM)
Now you have 2 tasks in the list.
```

### `filter <date>` - filters all tasks that match the specified <date>

The `filter` command is similar to the `find` command, but it searches the list for the date specified instead. Note that in the current implementation, Duke can only search through task that have been tagged with **date** only! If you tagged your task with both date and time Duke **will not** be able to find it.

Example of usage:

`filter 2021-09-10`

Expected outcome:

```
Here are your tasks for this day:
[E][] CS2103T Lecture (at: Sep 10 2021)
```

**Accepted date format**
- yyyy-mm-dd (e.g `2021-09-10`)

### `exit` - terminates Duke

Alias: `bye`

Closes the GUI window.

Example of usage:

`exit`

Expected outcome:

Duke GUI closes