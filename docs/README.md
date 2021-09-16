# When the Duke is Sus - A User Guide

## Features 

### Keep track of different tasks

1. Todos - Simple tasks you plan to do.
2. Deadlines - Tasks that have to be completed `by` a certain date.
3. Events - Tasks that occur `at` a certain time.

### List out or filter your tasks.
See what tasks you currently have

### Toggle completion or delete tasks
Mark your tasks as complete

### Load and save your task list
Saving and loading are done automatically onto your local hard drive

## Usage

### `todo` - Creates a todo task

Adds a todo into your task list.

**Example usage:**

`todo taskname`

* `taskname` - Name of todo

**Expected outcome:**

A confirmation response would be sent by Duke:

```
Got it. I've added this task:
        [T][ ] Example task
There are ___ tasks in your list
```

<br/><br/>

### `deadline` - Creates a deadline task

Adds a deadline into your task list.

**Example usage:**

`deadline taskname /by DD/MM/YYYY xxxx`

* `taskname` - Name of deadline
* `DD/MM/YYYY` - Date and month can be 1-2 digits long
* `xxxx` - Time in 24-hour format *[Optional]*

**Expected outcome:**

A confirmation response would be sent by Duke:

```
Got it. I've added this task:
        [D][ ] Example task
There are ___ tasks in your list
```
<br/><br/>

### `event` - Creates an event task

Adds an event into your task list.

**Example usage:**

`event taskname /at DD/MM/YYYY xxxx`

* `taskname` - Name of deadline
* `DD/MM/YYYY` - Date and month can be 1-2 digits long
* `xxxx` - Time in 24-hour format *[Optional]*

A confirmation response would be sent by Duke:

```
Got it. I've added this task:
        [E][ ] Example task
There are ___ tasks in your list
```

<br/><br/>

### `list` - Lists or filters your tasks

Lists out or filters your tasks based on the optional arguments provided (if any).
Using the command with no arguments displays the index numbers of all tasks, to be used for `done` and `delete`.

**Example usage (with and without optional arguments):**

`list` - Standard list command (no arguments)

`list /date DD/MM/YYYY` - Search for tasks at date

`list /name taskname` - Search for tasks with name

Arguments can be stacked, order of arguments does not matter.

`list /name taskname /date DD/MM/YYYY` - Search for tasks with specified name AND date


* `DD/MM/YYYY` - Date and month can be 1-2 digits long
* `taskname` - Name to search for

**Expected outcome:**

A list of all your tasks (if any) would be sent by Duke.

<br/><br/>

### `done` - Mark as complete

Toggles completion of task. If task is already done, it will mark it as not done.

**Example usage:**

`done index`

* `index` - Index number as shown in `list`

**Expected outcome:**

A confirmation response would be sent by Duke.

```
Sugoi! Duke-san marked this task as done!
        [D][X] Example task
```

```
Duke-san marked this task as not done!
        [D][ ] Example task
```

<br/><br/>

### `delete` - Delete task

Deletes a task in the list **permanently**.

**Example usage:**

`delete index`

* `index` - Index number as shown in `list`

**Expected outcome:**

A confirmation response would be sent by Duke.

```
Noted. Duke-san removed this task!
        [D][X] Example task
There are ___ tasks in your list
```

<br/><br/>

### `Sort` - Sorts your tasks

Sorts your tasks based on the arguments provided.

**Example usage (with and without optional arguments):**

`sort` - Standard sort by name (no arguments)

`list /date` - Sort by date and time

`list /done` - Sort by completion status

`list /name` - Sort by name (Same as no arguments)

`list /added` - Sort by date in which task was added

Arguments can be stacked, list will be sorted by the first one first, followed by the second, etc.

`list /done /date` - Sort by completion status first. For those with the completion status, sort by date and time.

`list /done /date /name` - Same as above, but for those with the same date and time, sorted in alphabetical order.

**Expected outcome:**

This command will overwrite your current list with the new sorted task list.

A new list of all your tasks (if any) would be sent by Duke.

<br/><br/>

### `help` - Display list of commands
In-built command to display the list of possible commands and arguments within the app.

**Example usage:**

`help`

**Expected outcome:**

An in-built list of possible commands with their arguments would be sent by Duke.

<br/><br/>

### `gubbai` - Goodbye
Exits the app

**Example usage:**

`gubbai`

**Expected outcome:**

The following goodbye message will be displayed. The app would close automatically 2 seconds later.

`kimi no unmei no hito wa boku jyanai`