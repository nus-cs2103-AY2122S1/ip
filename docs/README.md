# User Guide
## Features
1. ### [Add tasks](#add-tasks)
   - [`todo`](#`todo` - add a todo task that is not time-sensitive to your list of tasks)
   - [`deadline`](#`deadline` - add a deadline task with a specified date and time your list of tasks)
   - [`event`](#`event` - add an event with a specified date and time to your list of tasks)
2. ### [View tasks](#View Tasks)
   - [`list`](#`list` - shows all your current tasks with the completion status and task type)
3. ### [Update tasks](#Update Tasks)
   - [`done`](#`done` - complete a task in your list of tasks)
   - [`delete`](#`delete` - remove a task from your list of tasks)
4. ### [Search tasks](#Search Tasks)
   - [`find`](#`find` - find relevant tasks with keyword)
5. ### [Additional Features](#Additional Features)
   - [`help`](#`help` - list all  supported commands with their respective formats)
   - [`bye`](#`bye` - exits the application)
   - [save](#save)
### Download here

---
## Add Tasks
Adds a task of type `todo`/ `deadline`/ `event ` to your list of tasks.


### `todo` - add a todo task that is not time-sensitive to your list of tasks

**Format**: `todo [detail]`

Argument | Remarks
------------ | -------------
[detail] | description of the task 
Example: `todo read book`

Expected outcome: 
<img height="80" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 5.15.17 PM.png" width="600"/>

---
### `deadline` - add a deadline task with a specified date and time your list of tasks

**Format**: `deadline [detail] /by [dd/MM/yyyy HH:mm]`

Argument | Remarks
------------ | -------------
[detail] | description of the deadline
[dd/MM/yyyy HH:mm] | date and time of the deadline

**Example of Usage**: `deadline math hw /by 19/02/2021 19:00`

**Expected outcome**:
<img height="80" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 5.37.26 PM.png" width="600"/>

---
### `event` - add an event with a specified date and time to your list of tasks

**Format**: `event meeting /at [dd/MM/yyyy HH:mm]`

Argument | Remarks
------------ | -------------
[detail] | description of the event
[dd/MM/yyyy HH:mm] | date and time of the event
**Example of Usage**: `event meeting /at 19/08/2021 09:00`

**Expected outcome**:
<img height="80" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 5.42.44 PM.png" width="600"/>
---

## View Tasks

### `list` - shows all your current tasks with the completion status and task type

**Format**: `list`

**Expected Outcome**:
<img height="110" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 5.55.34 PM.png" width="600"/>

---
## Update Tasks

### `done` - complete a task in your list of tasks
**Format**: `done [index]`

Argument | Remarks
------------ | -------------
[index] | index of task starting from 1

**Example of Usage**: `done 1`

**Expected Outcome**:
<img height="220" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 6.05.20 PM.png" width="600"/>

---
### `delete` - remove a task from your list of tasks
**Format**: `delete [index]`

Argument | Remarks
------------ | -------------
[index] | index of task starting from 1
**Example of Usage**: `delete 2`

**Expected Outcome**:
<img height="220" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 6.07.02 PM.png" width="600"/>
---
## Search Tasks
### `find` - find relevant tasks with keyword
**Format**: `find [keyword]`

Argument | Remarks
------------ | -------------
[keyword] | string that appears in the detail of a task

**Example of Usage**: `find meet`

**Expected Outcome**:
<img height="220" src="/Users/zumana/Desktop/Screenshot 2021-09-16 at 6.11.45 PM.png" width="600"/>
---
## Additional Features
### `help` - list all  supported commands with their respective formats
**Format**: `help`

### `bye` - exits the application
**Format**: `bye`

### save
Saves and loads your current tasks from your local file`data/list.txt` whenever you use the application.

<br></br>