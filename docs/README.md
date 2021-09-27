# User Guide

## Features 

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `list` - List all tasks

You can list all tasks currently stored in BTDuke with the `list` command.

Example of usage: 

`list`

Expected outcome:

You will see the list of all tasks printed. 

```
[T][X] read book
[D][ ] homework (by: 2021-10-02)
[E][ ] test (at: 2021-10-05)

```

### `todo` - Add a to-do

You can add a to-do to BTDuke using the `todo` command.

Example of usage: 

`todo TASK_NAME`

Expected outcome:

You will see a message that the task has been added if it was successful.

```
Added: [T][ ] My to-do
```

### `deadline` - Add a task with a deadline

You can add a task with a deadline to BTDuke.

Example of usage: 

`deadline TASK_NAME /by DUE_DATE`

You should write DUE_DATE in YYYY-MM-DD format, where YYYY is the year in four digits, MM is the month in two digits, and DD is the date in two digits.

Expected outcome:

You will see a message that the task has been added if it was successful.

```
Added: [D][ ] My task with a deadline
```

### `event` - Add an event

You can add an event to BTDuke. 

Example of usage: 

`event TASK_NAME /at EVENT_DATE`

You should write EVENT_DATE in YYYY-MM-DD format, where YYYY is the year in four digits, MM is the month in two digits, and DD is the date in two digits.

Expected outcome:

You will see a message that the event has been added if it was successful.

```
Added: [E][ ] My event
```
