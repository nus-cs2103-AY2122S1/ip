# User Guide

**Duke** is an app that helps you to manage all your tasks and deadlines through a Graphical User Interface.

## Contents
- [Features](#features)
  - [Add Task](#add-task)
  - [Edit Task](#edit-task)
  - [Delete Task](#delete-task)
  - [List Tasks](#list-tasks)
  - [Mark Task as Done](#mark-task-as-done)
  - [Find Tasks](#find-tasks-containing-keyword)
  - [Save Tasks](#save-tasks)
  - [Exit Program](#exit-program)
- [Command Usage](#command-usage)
  - [`todo`](#todo---add-todo-task)
  - [`event`](#event---add-event-task)
  - [`deadline`](#deadline---add-deadline-task)
  - [`edit`](#edit---edit-task)
  - [`delete`](#delete---delete-task)
  - [`list`](#list---list-all-tasks)
  - [`done`](#done---mark-task-done)
  - [`find`](#find---find-tasks-containing-keyword)
  - [`bye`](#bye---exit-program)

## Features 

### Add Task

Add a task to the task list in Duke.

3 types of tasks can be added:
- Todo: Contains description of task
- Event: Contains description and date of event
- Deadline: Contains description and deadline of task

### Edit Task

Edit an existing task in the task list. Either the description, date, or both can be edited.

### Delete Task

Delete an existing task from the task list.

### List Tasks

List all the tasks in the task list.

### Mark Task as Done

Mark an existing task as done in the task list.

### Find Tasks Containing Keyword

Find all tasks that contain a specified keyword in their descriptions.

### Save Tasks

Duke automatically saves the list of tasks to the hard disc whenever it is updated. 
There is no need to manually save the task list. 

### Exit Program

Exit the Duke program.

## Command Usage

### `todo` - Add Todo Task

Adds a Todo task to the task list. A Todo contains a description.

Format: `todo [DESC]`

Example of usage: 

`todo watch 2100 lecture`

Expected outcome:

Displays the newly added Todo task and the total number of tasks in the list.

```
Got it. I've added this task:
	[T][ ] watch 2100 lecture
Now you have 6 tasks in the list.
```

### `event` - Add Event Task

Adds an Event task to the task list. An Event contains a description and a datetime.

Format: `event [DESC] /at [DATETIME]`
- DATETIME should be entered in this format: `dd-mm-yyyy hh:mm`

Example of usage:

`event attend concert /at 23-01-2021 14:30`

Expected outcome:

Displays the newly added Event task and the total number of tasks in the list.

```
Got it. I've added this task:
	[E][ ] attend concert (at: 23 Jan 2021 02:30 PM)
Now you have 7 tasks in the list.
```

### `deadline` - Add Deadline Task

Adds a Deadline task to the task list. A deadline contains a description and a datetime.

Format: `deadline [DESC] /by [DATETIME]`
- DATETIME should be entered in this format: `dd-mm-yyyy hh:mm`

Example of usage:

`deadline submit report /by 22-01-2021 10:00`

Expected outcome:

Displays the newly added Deadline task and the total number of tasks in the list.

```
Got it. I've added this task:
	[D][ ] submit report (by: 22 Jan 2021 10:00 AM)
Now you have 8 tasks in the list.
```

### `edit` - Edit Task

Edits the task at the specified index. 

Format: `edit INDEX /desc [DESC] /date [DATETIME]`
- The index refers to the number shown in the displayed task list.
- The index should be a positive number not greater than the number of tasks in the task list.
- At least one of the fields (desc or date) must be provided
- Existing values will be updated to the input values
- DATETIME should be entered in this format: `dd-mm-yyyy hh:mm`
- If the task to be edited is a Todo, the /date field will be ignored 
but the description of the Todo will still be updated if the /desc field is present

Example of usage:

`edit 2 /desc Submit 2103 quiz /date 20-01-2020 14:20`

Expected outcome:

Shows updated task.

```
Task 2 has been successfully edited to:
	[D][X] Submit 2103 quiz /date 20-01-2020 14:20 (by: 20 Jan 2020 02:20 PM)
```

### `delete` - Delete Task

Deletes the task at the specified index.

Format: `delete INDEX`
- The index refers to the number shown in the displayed task list.
- The index should be a positive number not greater than the number of tasks in the task list.

Example of usage:

`delete 2`

Expected outcome:

Shows deleted task and total number of tasks after deletion.

```
Noted. I've removed this task:
	[D][X] Submit 2103 quiz /date 20-01-2020 14:20 (by: 20 Jan 2020 02:20 PM)
Now you have 8 tasks in the list.
```

### `list` - List All Tasks

Displays all tasks in the task list.

Example of usage:

`list`

Expected outcome:

Displays all tasks in the task list.

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][X] return book (by: 13 Sep 2020 06:00 PM)
3. [E][ ] project meeting (at: 10 Oct 2019 11:10 PM)
4. [T][X] join sports club
5. [E][ ] Attend CCA (at: 23 Jan 2021 02:30 PM)
```

### `done` - Mark Task Done

Marks the task at the specified index as done.

Format: `done INDEX`
- The index refers to the number shown in the displayed task list.
- The index should be a positive number not greater than the number of tasks in the task list.

Example of usage:

`done 3`

Expected outcome:

Shows task that was marked as done.

```
I've marked this task as done:
	[T][X] read book
```

### `find` - Find Tasks Containing Keyword

Finds all tasks whose descriptions contain the keyword specified by the user.

Format: `find [KEYWORD]`

Example of usage:

`find book`

Expected outcome:

Displays all tasks containing the keyword.

```
Here are the matching tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: 19 Sep 2021 12:00 PM)
```

### `bye` - Exit Program

Exits the Duke program.

Example of usage:

`bye`

Expected outcome:

Duke window is closed.



