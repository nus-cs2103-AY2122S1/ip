# User Guide

```
     ____________________________  ______
    /  _______/  _____   / ____  \/ _   |
   /  /______   /    /  / /____/ / /_|  |
  /______   /  /    /  /  _   __/  __   |
 _______/  /  /____/  /  / \  \/  /  |  |
/_________/__________/__/   \__\_/   |__|
```

Sora is a desktop application for managing Tasks. The main mode of input is via Command Line Interface (CLI) with a
Graphical User Interface (GUI) to show the output.

* [Features](#features)
    * [Viewing Help: `help`](#viewing-help-help)
    * [Exiting the program : `bye`](#exiting-the-program--bye)
    * [Listing all tasks : `list`](#listing-all-tasks--list)
    * [Adding a task](#adding-a-task)
        * [Todo: `todo`](#todo-todo)
        * [Deadline: `deadline`](#deadline-deadline)
        * [Event: `event`](#event-event)
    * [Marking a task as done: `done`](#marking-a-task-as-done-done)
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding a task: `find`](#finding-a-task-find)
    * [Sorting the list of tasks: `sort`](#sorting-the-list-of-tasks-sort)
* [Command Summary](#command-summary)

## Features

Note:

* Please follow the exact format given
* Items in `[]` bracket are for user inputs
    * e.g. for `deadline [description] /by [dd/MM/yy] [HHmm]`, user can input `deadline test deadline /by 1/5/20 1658`
* Items in `<>` bracket are optional user inputs
    * e.g. for `sort <-r>`, user can either input `sort` or `sort -r`

<br/>

### Viewing Help: `help`

Show the help message, which contains some information about the app.

Format: `help`

<br/>

### Exiting the program : `bye`

Exits the program.

Format: `bye`

<br/>

### Listing all tasks : `list`

Shows a list of all tasks, and details regarding the tasks.

Format: `list`

Sample output:

```
[T][X] read book
[D][ ] return book (by: Jun 6 2021, 5:12 PM)
[E][ ] project meeting (at: Aug 6 2021, 2:00 PM - 6:00 PM)
```

Interpretation

* First `[ ]` bracket
    * `[T]` refers to a `Todo`
    * `[D]` refers to a `Deadline`
    * `[E]` refers to a `Event`
* Second `[ ]` bracket
    * `[X]` refers to a task which is done
    * `[ ]` refers to a task which is not done
* Task description
* (Optional) Any date/time specified by the user

<br/>

### Adding a task

Adds any task of the following types into the list of tasks.

#### Todo: `todo`

Adds a todo to the list of tasks.

Format: `todo [description]`

#### Deadline: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline [description] /by [dd/MM/yy] [HHmm]`

Note:

1. `[dd/MM/yy]` refers to `day/month/year`
    1. Please keep the `/`
    2. You can input both single or double digits (e.g. `1/5/21` is the same as `01/05/21`)
2. `[HHmm]` refers to `hour` followed by `minute`

#### Event: `event`

Adds an event to the list of tasks.

Format: `event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]`

Note:

1. `[dd/MM/yy]` refers to `day/month/year`
    1. Please keep the `/`
    2. You can input both single or double digits (e.g. `1/5/21` is the same as `01/05/21`)
2. `[HHmm]` refers to `hour` followed by `minute`

<br/>

### Marking a task as done: `done`

Marks a task as done.

Format: `done [task number]`

Note:

1. `task number` **must be a positive integer** 1, 2, 3, ...
2. The `task number` refers to the `task number` shown by the [`list`](#listing-all-tasks--list) command
3. This command has no effect on a `Task` that is already marked as `done`

<br/>

### Deleting a task: `delete`

Deletes a task.

Format: `delete [task number]`

Note:

1. `task number` **must be a positive integer** 1, 2, 3, ...
2. The `task number` refers to the `task number` shown by the [`list`](#listing-all-tasks--list) command

<br/>

### Finding a task: `find`

Finds a list of tasks that match the keyword.

Format: `find [keyword]`

Note:

1. Only description and type of task is searched (e.g. `deadline` will match all tasks of
   type [`Deadline`](#deadline-deadline) and tasks containing `deadline` in their description)
2. Partial matching of description is possible (e.g. `t` will match tasks with description of `task` and `test`)
3. `keyword` is _case-insensitive_

<br/>

### Sorting the list of tasks: `sort`

Sorts the list by date and time.

Format: `sort <-r>`

Note:

1. The sort is done by date and time, with the earliest on top
2. Since [`Todo`](#todo-todo) does not have a time, it will be the last
    1. Note that supplying a `-r` flag will cause [`Todo`](#todo-todo) tasks to be at the top
3. To sort by reverse order, input the `-r` flag
4. The `-r` flag is optional

## Command Summary

Command | Format
------- | ------
[List](#listing-all-tasks--list) | `list`
[Add Todo](#todo-todo) | `todo [description]`
[Add Deadline](#deadline-deadline) | `deadline [description] /by [dd/MM/yy] [HHmm]`
[Add Event](#event-event) | `event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]`
[Done](#marking-a-task-as-done-done) | `done [task number]`
[Delete](#deleting-a-task-delete) | `delete [task number]`
[Find](#finding-a-task-find) | `find [keyword]`
[Sort](#sorting-the-list-of-tasks-sort) | `sort <-r>`
[Exit](#exiting-the-program--bye) | `bye`
[Help](#viewing-help-help) | `help`
