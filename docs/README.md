# User Guide
Duke helps you to manage your tasks via commands with a simple GUI.

## Requirements
* Java 11 version installed
* `Duke.jar` downloaded from the releases page

## Features 

### Add tasks to record down
Duke contains support for four types of tasks, namely `Todo`, `Deadline`, `Event` and `Recurring` tasks.

#### Todo
A task to be done.

#### Deadline
A task to be done by a given date.

#### Event
An event or activity that will occur at a given date.

#### Recurring
A task to be done by a given date, and will need to be done again after a set interval of days.

**Take not that the recurring task will only be added when the current one is done.**

### Listing tasks and finding them
You can use the `list` and `find` commands to lookup tasks easily.

### Checking off tasks
You can mark tasks as done to keep track of tasks that are not completed yet.

### Delete tasks
You can delete tasks to reduce clutter.

### Local storage
Tasks are saved between sessions, even after PC reboot.

## Command Reference

#### Add Todo
`todo task_name`

where task_name is the name of the task.

Example: `todo Read TextBook`

#### Add Deadline
`deadline task_name /by due_date`

where `task_name` is the name of the task,

where `due_date` is the deadline given in yyyy-mm-dd format.

Example: `deadline Homework /by 2021-09-11`

#### Add Event
`event event_name /at event_date`

where `event_name` is the name of the event,

where `event_date` is the date of the event given in yyyy-mm-dd format.

Example: `event Christmas /at 2021-12-25`

#### Add Recurring
`recurring task_name /by due_date /every interval`

where `task_name` is the name of the task,

where `due_date` is the deadline given in yyyy-mm-dd format,

where `interval` is the the number of days for said task to recur.

Example: `recurring Sweep The Floor /by 2021-09-13 / every 7`

#### List all added tasks
`list` command to view all tasks

#### Find tasks by keywords
`find keyword` command to filter tasks containing `keyword`

#### Mark tasks as done
`done task_index` to mark a particular task as done, where `task_index` is the index of the task when using the `list` function

#### Delete Tasks
`delete task_index` to delete a particular task from the list, where `task_index` is the index of the task when using the `list` function

