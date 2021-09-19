# User Guide

**Duke** is a personal assistant chatbot that helps user manage tasks.

## Features 

### Add Tasks

Duke allows you to add three types of tasks:

- ToDo: Tasks without time attached to it;
- Deadline: Tasks that need to be done by a specific time/date;
- Event: Tasks that will happen during a specific time/date.

For all the three tasks, you can enter the description of the tasks, and for 
Deadlines/Events, you can enter the attached date. 

### List Tasks

Duke allows you to list all the current tasks.

### Mark Tasks as Done/Undone

Besides adding tasks, you can also mark them as done/undone.

### Delete Tasks

If you no longer need some tasks, you can delete them.

### Find/Query Tasks by Date or Keyword

Duke allows you to find specific tasks by entering date or any word in the task description.

### Update Task

If you typed a wrong task description or date, you can modify it.

## Usage

The UPPERCASE words can be changed to meet your need. For instance, you can change
`todo DESCRIPTION` to `todo homework 1`, and the description of the new task will be
"homework 1".

The parts enclosed by a parentheses "()" are optional.

### `todo DESCRIPTION` - Add a new ToDo task

This command adds a new ToDo task with the given DESCRIPTION.

#### Example of usage

`todo CS2103T post-lecture quiz`

#### Expected outcome

A Todo task with description "CS2103T post-lecture quiz" is created and stored in 
a task list.

#### Expected output (the task number may be different)

```
Got it. I've added this task:
    [T][ ] CS2103T post-lecture quiz
Task(s) remaining in the list: 1
```

### `deadline DESCRIPTION /by YYYY-MM-DD` - Add a new Deadline task

This command adds a new Deadline task with the given DESCRIPTION and date. The date
must be in `YYYY-MM-DD` format.

#### Example of usage

`deadline CS2101 presentation slides /by 2020-12-23`

#### Expected outcome

A Deadline task with description "CS2101 presentation slides" and due date "2020-12-23"
is created and stored in the task list.

#### Expected output (the task number may be different)

```
Got it. I've added this task:
    [D][ ] CS2101 presentation slides (by: Dec 23 2020)
Task(s) remaining in the list: 2
```

### `event DESCRIPTION /at YYYY-MM-DD` - Add a new Event task

This command adds a new Event task with the given DESCRIPTION and date. The date
must be in `YYYY-MM-DD` format.

#### Example of usage

`event CS2101 presentation /at 2020-12-25`

#### Expected outcome

A Deadline task with description "CS2101 presentation" and date "2020-12-25"
is created and stored in the task list.

#### Expected output (the task number may be different)

```
Got it. I've added this task:
    [E][ ] CS2101 presentation (at: Dec 25 2020)
Task(s) remaining in the list: 3
```

### `list` - List all the tasks in the task list

#### Example of usage

`list`

#### Expected output (the content may be different)

```
Here are the tasks in your list:
    1. [T][ ] CS2103T post-lecture quiz
    2. [D][ ] CS2101 presentation slides  (by: Dec 23 2020)
    3. [E][ ] CS2101 presentation  (at: Dec 25 2020)
```

### `done TASK_NUMBER` - Mark a task as done

This command marks the task with index TASK_NUMBER as done.

#### Example of usage

`done 1`

#### Expected outcome

The first item is marked done.

#### Expected output (the description may be different)

```
Nice! I've marked this task as done:
    [T][X] CS2103T post-lecture quiz
```

### `undone TASK_NUMBER` - Undo a task

This command undoes the task with index TASK_NUMBER.

#### Example of usage

`undone 1`

#### Expected outcome

The first item is undone.

#### Expected output (the description may be different)

```
Alright. I've undone this task:
    [T][ ] CS2103T post-lecture quiz
```

### `delete TASK_NUMBER` - Delete a task

This command delete the task with index TASK_NUMBER.

#### Example of usage

`delete 1`

#### Expected outcome

The first item is deleted.

#### Expected output (the description may be different)

```
Noted. I've removed this task:
    [T][ ] CS2103T post-lecture quiz
Task(s) remaining in the list: 2
```

### `find KEYWORD` - Search tasks by keyword

This command finds all the tasks that includes the keyword.

#### Example of usage

`find presentation`

#### Expected outcome

All the tasks with word "presentation" is listed.

#### Expected output (the description may be different)

```
Here is the result:
    1. [D][ ] CS2101 presentation slides  (by: Dec 23 2020)
    2. [E][ ] CS2101 presentation  (at: Dec 25 2020)
```

### `query YYYY-MM-DD` - Search tasks by date

This command finds all the tasks that is associated with the date.

#### Example of usage

`query 2020-12-25`

#### Expected outcome

All the tasks associated with the date "2020-12-25" is listed.

#### Expected output (the description may be different)

```
Here is the result:
    1. [E][ ] CS2101 presentation  (at: Dec 25 2020)
```

### `update TASK_NUMBER DESCRIPTION (/time YYYY-MM-DD)` - Update tasks

This command updates the command with the index TASK_NUMBER. If the description or
the date remain unchanged, you can replace the respective field by `*`.

#### Example of usage

`update 2 * /time 2021-06-06`

#### Expected outcome

The second task has a new date 2021-06-06

#### Expected output (the description may be different)

```
Got it. I've update the task as:
    2. [E][ ] CS2101 presentation  (at: Jun 6 2021)
```

### `bye` - Exit

This command close the program.