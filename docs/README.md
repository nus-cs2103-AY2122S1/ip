# Duke

---

## User Guide
Duke is a desktop app for people who have a lot of tasks to do, but have a hard time keeping track of what 
needs to be done. Duke uses quick-text inputs via a Command Line Interface (CLI) while having an excellent 
Graphical User Interface (GUI). What are you waiting for? Use Duke now! 😄

* [Quick Start](#quick-start-guide)
* [Features](#features)
* [Usage](#usage)
* [Command Summary](#command-summary)

## Quick Start

---

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the Duke.jar file from the repository.
3. Double-click the file to start the app.

## Features

---

The following are the features Duke provides:

### Add a Todo task
Adds a Todo task to your list. Todo tasks are tasks without any date/time attached to it.

### Add a Deadline task
Adds a Deadline task to your list. Deadline tasks are tasks that need to be done before a specific
date.

### Add an Event task
Adds an Event task to your list. Even tasks are tasks that start at a specific date and time.

### List tasks
Lists all the tasks you currently have in your list.

### Delete task
Removes a task from your list.

### Mark tasks as done
Indicates that a current task on your list has been completed.

### Find a specific task
Searches for tasks on your list that matches the keyword you input.

## Usage

---

### `todo` - Adds a Todo task

Adds a Todo task to your list with the given description.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it boss! I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

Description of the outcome.

`[T]` indicates that this is a Todo task.

### `deadline` - Adds a Deadline task

Adds a Deadline task to your list with the given description and date.

Example of usage:

`deadline return book /by 2021-09-30`

The format of the date should be YYYY-MM-DD.

Expected outcome:

```
Got it boss! I've added this task:
  [D][ ] return book (by: 09 30 2021)
Now you have 2 tasks in the list.
```

Description of the outcome.

`[D]` indicates that this is a Deadline task.

### `event` - Adds an Event task

Adds an Event task to your list with the given description, date and time.

Example of usage:

`event meeting /at 2021-09-30T13:55`

The format of the date and time should be YYYY-MM-DD, followed by T and finally hh:mm.

Expected outcome:

```
Got it boss! I've added this task:
  [E][ ] meeting (at: 09 30 2021 at 01:55)
Now you have 3 tasks in the list.
```

Description of the outcome.

`[E]` indicates that this is a Deadline task.

### `list` - Lists all tasks

Lists all tasks currently in your list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: 09 30 2021)
3.[E][ ] meeting (at: 09 30 2021 at 01:55)
```

### `delete` - Removes a task

Removes a task from your list with the given index.

Example of usage:

`delete 1`

The index would be the current position of your task on the list.

Expected outcome:

```
Noted boss! I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

### `done` - Marks a task as complete

Indicates that a task on your list with the given index is completed.

Example of usage:

`done 1`

The index would be the current position of your task on the list.

Expected outcome:

```
Nice one boss! I've marked this task as done:
  [D][X] return book (by: 09 30 2021)
```

Description of the outcome.

`[ ]` indicates that the task is not yet complete.

`[X]` indicates that the task is completed.

### `find` - Removes a task

Finds all tasks from your list that matches the given keyword.

Example of usage:

`find meeting`

Expected outcome:

```
Here are the matching tasks in your list:
1.[E][ ] meeting (at: 09 30 2021 at 01:55)
```

### `bye` - Exits Duke

Exits Duke after showing the goodbye message.

Example of usage:

`bye`

Expected outcome:

```
Okay then! I hope to see you again soon boss!
```
Proceeded by the closure of the application.

## Command Summary

---

| Action | Format | Example |
| ------------ | ------------- | ------------- |
|todo | `todo {task's description}` | `todo read book` |
| deadline | `deadline {task's description} /by {Year-Month-Day}` | `deadline return book /by 2021-09-30` |
| event | `event {task's description} /at {Year-Month-DateTHour:Minute}` | `event meeting /at 2021-09-30 13:55` |
| list | `list` | `list`|
| delete | `delete {task's index}` | `delete 1` |
| done | `done {task's index}` | `done 2` |
| find | `find {keyword}` | `find meeting` |