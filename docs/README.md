# User Guide

## Features 

### Keep track of your tasks

BTDuke can help you keep track of your tasks, and other information with them. You can classify your tasks into either to-dos, deadlines, or events. 

### Save your tasks between sessions

BTDuke will help you save your tasks between each usage of the app, so you won't have to remember them. 

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

Format:

`todo TASK_NAME`

Example of usage: 

`todo My task`

Expected outcome:

You will see a message that the task has been added if it was successful.

```
Added: [T][ ] My to-do
```

### `deadline` - Add a task with a deadline

You can add a task with a deadline to BTDuke.

Format: 

`deadline TASK_NAME /by DUE_DATE`

Example of usage:
`deadline My task with a deadline /by 2021-09-28`

You should write DUE_DATE in YYYY-MM-DD format, where YYYY is the year in four digits, MM is the month in two digits, and DD is the date in two digits.

Expected outcome:

You will see a message that the task has been added if it was successful.

```
Added: [D][ ] My task with a deadline (by: 2021-09-28)
```

### `event` - Add an event

You can add an event to BTDuke. 

Format: 

`event TASK_NAME /at EVENT_DATE`

Example of usage:

`event My event /at 2021-09-29`

You should write EVENT_DATE in YYYY-MM-DD format, where YYYY is the year in four digits, MM is the month in two digits, and DD is the date in two digits.

Expected outcome:

You will see a message that the event has been added if it was successful.

```
Added: [E][ ] My event (at: 2021-09-29)
```

### `done` - Mark task as done

You can mark a task as done in BTDuke.

Format: 

`done TASK_NAME`

Example of usage:

`done My task`

Expected outcome:

You will see a message that the task, if found, has been marked as done.

```
Yay :) This task is done:
[T][X] My task
```


### `delete` - Delete task

You can delete a task from BTDuke

Format: 

`delete TASK_NAME`

Example of usage:

`delete My task`

Expected outcome:

You will see a message that the task, if found, has been deleted.

```
Noted. This task has been deleted
[T][X] My task
```

### `find` - Find task with substring

You can find all tasks containing any substring in BTDuke. 

Format:

`find SUBSTRING`

Example of usage: 

`find homework`

Expected outcome:

You will see a list of all tasks which name contains the substring.

```
Noted. This task has been deleted
[T][ ] Math homework
[T][ ] Science homework
```
