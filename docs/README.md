# Duke User Guide

Duke is a desktop app for task management, optimized for use via a Command Line Interface (CLI). If you can type fast, Duke can help you keep track of your work and study tasks handily!

## Features 

Note about the command format: 
- Words in UPPER_CASE are the parameters to be supplied by the user. E.g. in `todo DESCRIPTION`, `DESCRIPTION` is the parameter e.g. borrow a book.
- If a command line requires task `INDEX` as input, the index refers to the index number displayed after the `list` command. The index must be a positive integer within the range of indexes listed.

### Add a task

Adds a task to the address book. The task must be one of the three categories:
1. todo: A task not associated with a date or time.
2. deadline: A task that must be done before a deadline.
3. event: A task that takes place over a time window.

Format:
1. `todo DESCRIPTION`
2. `deadline DESCRIPTION /by DATE TIME`
3. `event DESCRIPTION /at DATE START_TIME END_TIME`

### List all tasks

List all tasks currently in the list.

Format: `list`

Tasks will be displayed in this format: `\[type\] \[status\] description (date time)`
- Type: T for todo, D for deadline, E for event
- Status: X for done. Blank for not done
- Date is in the format: Month Date Year
- Time is in 24-hour format

### Mark a task as done

Mark the specified task as done
 
Format: `done INDEX`

### Delete a task

Delete the specified task from the task list.
 
Format: `delete INDEX`

### Update a task

Update the specified task.

Format: `update INDEX CATEGORY CONTENT`

CATEGORY must be one of the following:
- `description`
- `date`
- `time`
- `start_time`
- `end_time`
The format of the `CONTENT` must match the `CATEGORY`. E.g., if `CATEGORY` is the date, the `CONTENT` must be a valid date like 2021-09-08.

### Find a task

Finds tasks whose description contains a string of keywords

Format: `find KEYWORDS`

Only the description is searched.<br/>
Only full words will be matched e.g. "project" will not match "projects".

## Usage

### Add a Task

Input1: `event project meeting /at 2021-09-15 14:00-16:00`<br/>
Outcome1: <br/>
"Got it. I've added this task: <br/>
\[E\] \[ \] project meeting (at Sep 15 2021 14:00-16:00) <br/>
Now you have 1 tasks in the list."

Input2: `todo borrow book`<br/>
Outcome2: <br/>
"Got it. I've added this task: <br/>
\[T\] \[ \] borrow book <br/>
Now you have 2 tasks in the list."

### List all tasks

Input: `list`<br/>
Outcome:<br/>
"Here are the tasks in your list: <br/>
1. \[E\] \[ \] project meeting (at Sep 15 2021 14:00-16:00)<br/>
2. \[T\] \[ \] borrow book"

### Mark a task as done

Input: `done 2`<br/>
Outcome:<br/>
"Nice! I've marked this task as done: <br/>
\[T\] \[X\] borrow book"

### Delete a task

Input: `delete 2`<br/>
Outcome: <br/>
"Noted. I've removed this task: <br/>
\[T\] \[X\] borrow book <br/>
Now you have 1 tasks in the list."

### Update a task
Input: `update 1 date 2021-09-16`<br/>
Outcome:<br/>
"Nice! The task is updated as follows: <br/>
\[E\] \[ \] project meeting (at Sep 16 2021 14:00-16:00)"

### Find a task
Input: `find project`<br/>
Outcome:<br/>
"Here are the matching tasks in your list: <br/>
1. \[E\] \[ \] project meeting (at Sep 16 2021 14:00-16:00)"

<br/>
### Enjoy the app & your life! :wink:
