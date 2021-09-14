# DukeSupreme User Guide

## Features 

### Track all of your Tasks

Choose from four different tasks to create and keep track of.

* A Todo task is simply a Task that can be checked off.

* An Event is a Task to be completed on a certain date.

* A Deadline is a Task to be completed before a given date.

* A DoWithinPeriod is a Task that should be completed within a given time period.

### Automatic saving of your Task list to drive

All tasks with their dates and completion status are automatically recorded and updated onto a save file on the disk!

## Usage

### `bye` - Exits the program

Immediately exits the program.

### `list` - Lists all logged tasks

Displays a list of all of the tasks that have been logged, their completion status and due dates/deadlines (if applicable).

### `done` - Sets a task to DONE status

Sets a task that is specified by index to be completed.

Example of usage: 

`done 2`

### `delete` - Deletes a task from the task list

Deletes a task that is specified by index from the list of tasks.

Example of usage: 

`delete 2`

### `todo` - Creates a new Todo Task

Creates a Task that is to be completed. Has no due date/deadline, only completion status.

Example of usage: 

`todo Read a book`

### `deadline` - Creates a new Task with a deadline

Creates a Task that is to be completed by a specified deadline.

Example of usage: 

`deadline Read a book /by 2021-09-14`

### `event` - Creates a new Task with a specific date

Creates a Task that will happen on the specified date.

Example of usage: 

`event Attend book event /at 2021-09-14`

### `dowithinperiod` - Creates a new Task that should be completed within a time period

Creates a Task that as a start and end date, specifying a time period in which the task should be completed.

Example of usage: 

`dowithinperiod Complete CS2103T IP /between 2021-09-10 /and 2021-09-15`

### `date` - Filters and returns tasks that are due on the given date

Displays a list of only tasks that are due on the given date.

Example of usage: 

`date 2021-09-15`

### `find` - Finds and returns all tasks that have the given string in its description

Displays a list of only tasks whose descriptions cotnain the string given by the user.

Example of usage: 

`find book`
