# User Guide

**Duke** is a desktop task manager app for you to keep track of your tasks and is optimized for use via a Command Line Interface (CLI).
## Features
### Support for multiple type of tasks
**Duke** allows you to track three type of tasks `todo`, `deadline`, `event`.
* `todo` task allows user to specify the description of the task that you need to do.
* `deadline` task allows user to specify the description and when is the deadline of the task.
* `event` task allows user to specify the description and when is the upcoming event.

All the tasks created will be saved locally, allowing for easy backups and transfer.

### CLI Commands
**CLI commands** allows fast typers to input, edit and delete tasks at a fast rate.

## Usage
**Notes about the command format:**

Items in angle brackets(<>) are compulsory.
e.g `/by <10/10/2021>` cannot be used as `/by`

Items in square brackets are optional.
e.g `/by 10/10/2021 [2300]` can be used as `/by 10/10/2021 [2300]` or as `/by 10/10/2021`

The format of the date is `d/M/YYYY`<br>
The format of the time is `HHmm` (24 hrs format)

### Listing all the tasks : `list`

Shows you all the tasks.<br>
**Format:** `list`<br>
**Example:** `list`<br>
**Expected output:**<br>
![List Ouput](images/list.PNG)

### Create a todo: `todo <todo description>`
Creates a todo.<br>
**Format:** `todo <task description>`<br>
**Example:** `todo homework`<br>
**Expected output:**<br>
![Todo Ouput](images/todo.PNG)

### Create a deadline: `deadline <deadline description> <date of deadline> [time]`
Creates a deadline.<br>
**Format:** `deadline <deadline description> <date of deadline> [time]`<br>
**Example:** `deadline finish assigment /by 15/9/2021 2359`<br>
**Expected output:**<br>
![Deadline Ouput](images/deadline.PNG)

### Create an event: `event <event description> <date of event> [time]`
Creates a event.<br>
**Format:** `event <event description> <date of event> [time]`<br>
**Example:** `event CS2103 Lecture /at 15/9/2021 1300`<br>
**Expected output:**<br>
![Event Ouput](images/event.PNG)

### Find task: `find <task name>`
Find the task with the text specified .<br>
**Format:** `find <task name>`<br>
**Example:** `find Lecture`<br>
**Expected output:**<br>
![Find Ouput](images/find.PNG)

### Delete a task: `delete <task number>`
Deletes the task that corresponds to that task number.<br>
**Format:** `delete <task number>`<br>
**Example:** `delete 1`<br>
**Expected output:**<br>
![Delete Ouput](images/delete.PNG)

### Mark task as done: `done <task number>`
Marks the task that corresponds to that task number as done.<br>
**Format:** `done <task number>`<br>
**Example:** `done 1`<br>
**Expected output:**<br>
![Done Ouput](images/done.PNG)

### Update task: `update <task number> <new task (same format as creating a new task)>`
Updates the task with the new task inputted. **(Must be same type of task)**<br>
**Format:** `update <task number> <new task (same format as creating a new task)>`<br>
**Example:** `update 2 event CS2103 Lecture /at 17/9/2021 1300`<br>
**Expected output:**<br>
![Update Ouput](images/update.PNG)

### Exit: `bye`
Updates the task with the new task inputted.(Must be same type of task)<br>
**Format:** `bye`<br>
**Example:** `bye`<br>
**Expected output:**<br>
![Bye Ouput](images/bye.PNG)