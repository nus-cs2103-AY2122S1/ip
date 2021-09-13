# User Guide

## Features

* Add Tasks
* Remove Tasks
* Complete Tasks
* View List of Tasks
* Snooze Tasks
* Clear List of Tasks

## Type of Tasks

- [X] Events 🤯
- [X] Deadlines 🤯
- [X] Todos 🤯


## Usage

### `todo` - Add Tasks

A Todo with the description you entered will be created. `event` and `deadline`
can be used in place of `todo` to create different types of tasks.

Example of usage: 

`todo Household Chores`

Expected outcome:

Kopitiam uncle will reply you acknowledging the creation of a Todo

### `list` - List Tasks

Your current list of tasks will be displayed

Example of usage:

`list`

Expected outcome:

Kopitiam uncle will show you your entire list.

### `remove` - Remove Tasks

You can remove a task by entering `remove` followed by the index of the task.

Example of usage:

`remove 1`

Expected outcome:

Kopitiam uncle will remove the first task.

### `done` - Complete a Task

You can complete a task by entering `done` followed by the index of the task.

Example of usage:

`done 1`

Expected outcome:

Kopitiam uncle will complete the first task.

### `snooze` - Snooze Tasks for 1 week

Increased the duration of date and time of a task by 1 week

Example of usage:

`snooze 1`

Expected outcome:

Kopitiam uncle will snooze the first task.

### `clear` - Clear List of Tasks

The entire list of tasks will be wiped out.

Example of usage:

`clear`

Expected outcome:

Kopitiam uncle will remove all tasks. If you restart the chatbot, the list will
be empty

