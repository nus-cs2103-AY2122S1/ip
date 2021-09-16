# User Guide

Magnolia is a desktop app for managing your tasks. It is simple and lightweight.
Tasks can be added to the list through simple commands.

## Features 

### Add Task

Adds a task to the list. There are 4 tasks. Todos, Events, Deadlines, and Withins.
* Todos are the most simple task with just a description
* Events also have a date in which it occurs
* Deadlines have the deadline of the task
* Within tasks have 2 dates for the timeframe to complete the task

### Delete Task

Delete task from list.

### Find Task

Find tasks in list using keywords.

### List Tasks

Show a list of all tasks

### Mark Task

Mark task as complete

## Usage

### `Add Task` 

Add task to list.

Format: 
* `todo DESCRIPTION`
* `event DESCRIPTION at DATE`
* `deadline DESCRIPTION by DATE`
* `within DESCRIPTION between DATE and DATE`

Date in YYYY-MM-DD format

Example of usage: 
* `todo Clean Room`
* `within Cancel Subscription between 2021-09-10 and 2021-10-05`
* `event Dinner with Family at 2021-09-17`

### `delete`

Deletes task from list.

Format: `delete NUMBER`


Number is index of task to be deleted

Example of usage: `delete 3`

### `find`

Finds tasks that matches keywords.

Format: `find KEYWORDS`

Example of usage: `find homework`

### `list`

Lists all tasks.

Format: `list`

### `done`

Marks task as done.

Format: `done NUMBER`


Number is index of task to be marked

Example of usage: `done 4`


