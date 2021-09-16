# AnnieZero User guide

## Navigation

 - [How to set-up](#quick-start)
 - Features
	 - [Listing all your tasks: `list`](#listing-all-your-tasks-list)
	 - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
	 - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
	 - [Adding an event task: `event`](#adding-an-event-task-event)
	 - [Deleting a task: `delete`](#deleting-a-task-delete)
	 - [Marking a task as done: `done`](#marking-a-task-as-done-done)
	 - [Finding specified tasks: `find`](#finding-specified-tasks-find)
## Quick-start
1.  Ensure you have  `Java 11`  or above installed in your computer.
2.  Copy the  `anniezero.jar`  file to the folder you want to use as the home folder for AnnieZero.
3.  Open your terminal and navigate to the home folder for Anniezero.
4.  Run  `java -jar anniezero.jar`  to start the program.
## Features
### Listing all your tasks: `list`
Format: `list`

Lists all the tasks currently in your task list. 

Expected outcome:  
A numbered list of your current tasks will be shown.

### Adding a todo task: `todo`
Format: `todo DESCRIPTION`

Adds a todo tasks to your current task list.

- The description of this todo task should be stated in `DESCRIPTION`. It cannot be left empty.

Expected outcome:

AnnieZero will notify you with a message displaying the task details
if the task has been successfully added.

Example usage:

`todo learn baking` will add a todo task with description *learn baking*.

### Adding a deadline task: `deadline`
Format: `deadline DESCRIPTION / BY_DATE BY_TIME`

Adds a deadline tasks to your current task list.

- The description of this deadline task should be stated in
   `DESCRIPTION`. It cannot be left empty.
- The deadline date of this deadline task should be stated in
   `BY_DATE`, in `YYYY-MM-dd` format. It cannot be left empty.
- The deadline time of this deadline task should be stated in
   `BY_TIME`, in `hh:mm` format. It cannot be left empty.
- There must only be one **spacing** between `BY_DATE` and `BY_TIME`.

Expected outcome:

AnnieZero will notify you with a message displaying the task details
if the task has been successfully added.

Expected usage:

`deadline cs999 assignment / 2021-10-19 23:59` will add a tasks named cs999 assignment that has a deadline of 19 October 2021, 11.59pm.
### Adding an event task: event
Format: `event DESCRIPTION / AT_DATE START_TIMEtEND_TIME`

Adds a deadline tasks to your current task list.

- The description of this deadline task should be stated in
   `DESCRIPTION`. It cannot be left empty.
- The event date of this event task should be stated in
   `AT_DATE`. It cannot be left empty.
- The event start time of this event task should be stated in
   `START_TIME`. It cannot be left empty.
- The event end time of this event task should be stated in `END_TIME`. It cannot be left empty.
- There must only be one **spacing** between `BY_DATE` and `START_TIMEtEND_TIME`.
- There must be a letter **t** between `START_TIME` and `END_TIME`.

Expected outcome:

AnnieZero will notify you with a message displaying the task details
if the task has been successfully added.

Example usage:

`event beach day / 2021-11-01 08:30t12:00` will add a task named beach day that happens on 1 November 2021 from 8.30am to 12pm.

### Deleting a task: `delete`
Format: `delete INDEX`

Deletes the task with `INDEX` (shown in  `list`).

Expected outcome:  
AnnieZero will notify you with a message displaying the task details
of the successfully deleted task.

Example of usage:  
`delete 2`  will delete the 2nd task in the list.

### Marking a task as done: `done`
Format: `done INDEX`

Deletes the task with `INDEX` (shown in  `list`).

Expected outcome:  
AnnieZero will notify you with a message displaying the task details
of the successfully marked task.

Example of usage:  
`mark 3`  will delete the 3rd task in the list.

### Finding specified tasks: `find`
Format: `find KEYWORD`

Finds tasks which description matches the `KEYWORD`.

- The `KEYWORD` can be a subsequence of the task's description. e.g. `find sm rhol` will match a task named `summer holiday`.
- The `KEYWORD` is case-insensitive. e.g. `find eat fruits` will match a task named `Eat FruiTs`.
- The spacing in KEYWORD does not matter. e.g `find e      b f   ast` will match a task named `eat breakfast`.

Expected outcome:
A list of tasks with description that matches the `KEYWORD` will be shown.

Example usage:
`find d w sc` will find a task named `Date with Skittle-chan`.
`find learnbaking` will find a task named `learn baking`.
