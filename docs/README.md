# User Guide
Duke is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Quick Start

---

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from [here](https://github.com/limdanqi/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Duke.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

[comment]: <> (![Ui Screenshot] &#40;&#41;)

&nbsp;

## Features 

---

### *Record your tasks*

Record tasks of types todo, deadline or event.

### *List out your tasks*

Shows all your tasks.

### *Find a task*

Find tasks using keywords.

### *Check off your tasks*

Mark completed tasks as done.

### *Update your tasks*

Edit the fields of your tasks.

### *Delete a task*

Delete unwanted tasks.

### *Exit Duke*

Exit the Duke app.

&nbsp;

## Usage

---

### `todo` - Add a Todo task

Adds a Todo task to the task list.

Format: `todo n/[task name]`

Example of usage: 

`todo n/read a book`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
 [T][ ] read a book
Now you have 1 task in the list.
```

&nbsp;

### `deadline` - Add a Deadline task

Adds a Deadline task to the task list.

Format: `deadline n/[task name] d/[date and/or time] `

Example of usage:

`deadline n/return book d/12/10/2021`

Expected outcome:

```
Got it. I've added this task:
 [D][ ] return book (by: Oct 12 2021)
Now you have 1 task in the list.
```

&nbsp;

### `event` - Add an Event task

Adds an Event task to the task list.

Format: `event n/[task name] d/[date and/or time] `

Example of usage:

`event n/project meeting d/11/10/2021 15:00`

Expected outcome:

```
Got it. I've added this task:
 [E][ ] project meeting (at: Oct 11 2021, 15:00)
Now you have 1 task in the list.
```

&nbsp;

### `list` - Lists all tasks

Shows a list of all tasks in the task list.

Format: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] return book (by: Oct 12 2021)
3. [E][ ] project meeting (at: Oct 11 2021, 15:00)
```

&nbsp;

### `edit` - Edit a task

Edits an existing task in the task list.

Format: `edit [task number] n/[new task name] d/[new date and/or time] `
- Edits the task at the specified task number. The task number refers to the one present in the current task list.
- Either the new task name or new task date needs to be provided.

Example of usage:

- `edit 1 n/buy eggs`
- `edit 3 d/12/12/2021 10:00`

Expected outcome:

```
Got it. I've updated this task:
 [T][ ] buy eggs

Got it. I've updated this task:
 [E][ ] project meeting (at: Dec 12 2021, 10:00)
```

&nbsp;


### `done` - Check off a task

Mark a task as completed.

Format: `done [task number]`

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][X] read a book
```

&nbsp;

### `find` - Find a task

Find tasks which names contain the given keywords.

Format: `find [keyword] `

Example of usage:

`find eggs`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] buy eggs
```

&nbsp;

### `delete` - Delete a task

Deletes the specified task from the task list.

Format: `delete [task number] `

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
  [T][ ] buy eggs
Now you have 2 tasks in the list.
```

&nbsp;

### `exit` - Exits the program

Format: `exit`
