# Duke
---
Do you procrastinate non-stop and need something to help you be on track?
Then Duke is the solution you need!
![UI Screenshot](Ui.png)
## Features
---
### Task Management

The main feature of Duke is to help you with your task management.
The three types of task are listed below:

- Todo: A todo task with a description.
- Deadline: A task with a description and a specified deadline.
- Event: A task with a description and the time at which the event is occuring.

Duke allows users to add, delete and find such tasks with relative ease.

## Usage
---
### `todo` - Add a todo Task

Adds a todo task with its description to the to-do list.
##### Format:
`todo TASK`  
** `TASK` should not contain the character `|`.
##### Example:
`todo Jogging` adds `Jogging` as a todo task to the list.

### `deadline` - Add a deadline Task

Adds a deadline task with its description and deadline to the to-do list.
##### Format:
`deadline TASK /by yyyy-MM-dd`
`deadline TASK /by d/M/yyyy`
`deadline TASK /by DESCRIPTION`  
** `TASK` and `DESCRIPTION` should not contain the character `|`.
##### Example:
`deadline Jogging /by 2020-02-01` adds `Jogging` as a deadline task with the deadline on `1/2/2020`.
`deadline Jogging /by sometime soon` adds `Jogging` as a deadline task with the deadline on `sometime soon` (as a string).

### `event` - Add an event Task

Adds an event task with its description and time to the to-do list.
##### Format:
`event TASK /at yyyy-MM-dd`
`event TASK /at d/M/yyyy`
`event TASK /at DESCRIPTION`  
** `TASK` and `DESCRIPTION` should not contain the character `|`.
##### Example:
`event Jogging /at 2020-02-01` adds `Jogging` as an event task with happening on `1/2/2020`.
`event Jogging /by sometime soon` adds `Jogging` as an event task happening `sometime soon` (as a string).

### `list` - List all tasks
List the tasks in the to-do list.
##### Format:
`list`

### `done` - Mark task as done
Marks the task with the given index in the list as done.
##### Format:
`done INDEX`  
** `INDEX` has to be a non-negative integer.
##### Example:
`done 1` marks the first task in the to-do list as done.

### `delete` - Deletes task
Deletes the task with the given index from the list.
##### Format:
`delete INDEX`  
** `INDEX` has to be a non-negative integer.
##### Example:
`delete 1` deletes the first task in the to-do list.

### `find` - Find matching tasks
Find the tasks that matches the given string and displays it as a list.
##### Format:
`find STRING`

### `bye` - Exit duke.
Exits Duke and closes the window.
##### Format:
`bye`