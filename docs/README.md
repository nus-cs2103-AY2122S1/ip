# User Guide

## Features 

### Feature - Task Tracking

Keep track of tasks that can be divided into 3 categories
1) To-do Tasks
2) Deadline Tasks (Comes with a date)
3) Event Tasks (Comes with a date and time)

### Feature - Task Completion

Keep track of productivity by being able to check off Tasks

### Feature - Task Organization

Sort Tasks based on chronological order

### Feature - Adding Task

Add Tasks based on provided keywords to be included below

### Feature - Deleting Task

Deletes a certain Tasks based on the position in the list

### Feature - Listing Tasks

Shows all tasks currently being tracked

### Feature - Persistent Memory

Tasks are saved into a storage file for easy retrieval to preserve changes across sessions

## Usage

### `todo` - add a new To-do Task

Adds a new To-do Tasks to tracking

Example of usage: 

`todo ({Task Description})`

Expected outcome:

Shows that the task has been added 

```
Got it. I've added the following To-do:

X. [T][] {Task Description}

Now you have N Tasks in your list.
```

### `deadline` - add a new Deadline Task

Adds a new Deadline Tasks to tracking.

Syntax of Command must be followed exactly.

Task Description and Date must be included.

Date provided must be in `yyyy-mm-dd` format.

Date output will be changed to calendar standard.

Example of usage:

`deadline {Task Description} /by {Date}`

Expected outcome:

Shows that the Deadline has been added

```
Got it. I've added the following Deadline:

X. [D][] {Task Description} (by: {Date})

Now you have X Tasks in your list.
```

### `event` - add a new Event Task

Adds a new Event Task to tracking.

Syntax of Command must be followed exactly.

Task Description,Date and Time must be included.

Date provided must be in `yyyy-mm-dd` format.

Time provided must be in `HH:MM`, 24-Hour Clock Format.

Date output will be changed to calendar standard.

Time output will remain as 24-Hour format.

Example of usage:

`event {Task Description} /at {Date} {Time}`

Expected outcome:

Shows that the Event has been added

```
Got it. I've added the following Event:

X. [E][] {Task Description} (at: {Date} {Time})

Now you have X Tasks in your list.
```

### `list` - lists all tasks 

lists all the added tasks

Example of usage: 

`list`

Expected outcome:

Shows all tasks 

```
Here are the tasks in your list:

1. [T][] {Task Description}
2. [D][] {Task Description} (by: {Date})
3. [E][] {Task Description} (at: {Date} {Time})

```

### `done` - marks a task as completed

Marks a task as completed. 

The task number should correspond to the provided index on the list

Example of usage: 

`done {Task number}`

Expected outcome (for Task Number proved as 1):

marks the selected task number as done 

```
Here are the tasks in your list:

1. [T][X] {Task Description}
2. [D][] {Task Description} (by: {Date})
3. [E][] {Task Description} (at: {Date} {Time})

```

### `sort` - shows a chronologically sorted list of tasks

Sorts tasks chronologically and shows the newly sorted lists of tasks. 

Example of usage: 

`sort`

Expected outcome (Assuming Date are the same):

shows a list of sorted tasks

```
Here are the tasks in your list:

1. [T][] {Task Description}
2. [D][] {Task Description} (by: {Date})
3. [E][] {Task Description} (at: {Date} {Time})

```
