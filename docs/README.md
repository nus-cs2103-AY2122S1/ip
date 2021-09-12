# User Guide

## Features

- Add tasks
- List all tasks
- Delete tasks
- Set tasks as done
- Sort tasks

### Add task

Add either a Todo, Deadline or Event task to the list of tasks.

### Delete task

Remove a specified task from the list of tasks.

### List all tasks

List all added tasks.

### Set task as done

Set a specified task as done.

### Sort tasks

Sorts the list of tasks by type, then by name.

## Usage

### `todo` - add Todo task

Adds a todo task. Specify the name of that Todo task after the `todo` keyword.

Example of usage: 

`todo todoname`

Expected outcome:

A Todo task with `todoname` is created and added to the list of tasks.

```
Added [T] [ ] todoname to the list of tasks(1).
```

### `deadline` - add Deadline task

Adds a deadline task. Specify the name of that Deadline task after the `deadline` keyword, then a `/', then the deadline in the following date format: YYYY/MM/DD.

Example of usage:

`deadline deadlineName /2011/09/13`

Expected outcome:

A Deadline task with `deadlineName` is created and added to the list of tasks.

```
Added [D] [ ] deadlineName (13 Sep 2011) to the list of tasks(2).
```

### `event` - add Event task

Adds an event task. Specify the name of that Event task after the `event` keyword, then a `/', then a description of when the event takes place.

Example of usage:

`event eventName /whenever I'm free`

Expected outcome:

A Event task with `eventName` is created and added to the list of tasks.

```
Added [E] [ ] eventName (whenever I'm free) to the list of tasks(3).
```

### `list` - list all tasks

List all added tasks.

Example of usage:

`list`

Expected outcome:

All added tasks are listed.

```
1. [T] [ ] todoname
2. [D] [ ] deadlineName (13 Sep 2011)
3. [E] [ ] eventName (whenever I'm free)
```

### `delete` - delete task

Deletes a specified task. Specify the task number to delete after the `delete` keyword.

Example of usage:

`delete 3`

Expected outcome:

Task 3 is deleted.

```
Task 3 has been deleted.
```

### `done` - set task as done

Sets a specified task as done. Specify the task number to set as done after the `done` keyword.

Example of usage:

`done 2`

Expected outcome:

Task 2 is set as done.

```
[D] [X] deadlineName (13 Sep 2011) set as done.
```

### `sort` - sort tasks

Sorts the list of tasks by type, then by name.

Example of usage:

`sort`

Expected outcome:
Tasks sorted by type, then by name (in alphabetical order)

```
List of tasks is sorted.
```