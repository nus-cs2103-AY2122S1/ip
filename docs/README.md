# User Guide

Duke helps you keep track of and manage tasks.

![UI](./Ui.png)

## Features

### Keep track of tasks

View all your tasks easily at a glance and mark them as done when needed.

### Schedule tasks

Assign schedules and deadlines to tasks. Snooze them if they have to be delayed.

### Data saved across sessions

Data is saved locally; tasks can be persisted across all sessions.

## Usage


### List all tasks: `list`

List all tasks.

Example of usage:  `list`

### Add a todo: `todo`

Add a todo. A todo is a task without any specific time attributed to it.

Format: `todo TODO_NAME`

Example of usage:  `todo Go for a run`

### Add a deadline: `deadline`

Adds a deadline. A deadline is a task which has a target date of completion.

Dates are expected to be in `YYYY-MM-DD` format.
Time of days are optional and are expected to be in `hh:mm` format.

Format: `deadline DEADLINE_NAME /by DATE TIME_OF_DAY`

Example of usage:  `deadline Assignment /by 2021-09-15 13:00`

### Add an event: `event`

Adds an event. An event is a task which is scheduled to happen at a specific time.

Dates are expected to be in `YYYY-MM-DD` format.
Time of days are optional and are expected to be in `hh:mm` format.

Format: `event EVENT_NAME /at DATE TIME_OF_DAY`

Example of usage:  `event Dinner /at 2021-09-14 18:00`

### Mark a task as done: `done`

Mark a task as done, ticking it off in the list.

`INDEX` is the index of the task as shown when displaying all tasks using `list`.

Format: `done INDEX`

Example of usage: `done 1`

### Delete a task: `delete`

Removes a task from the list. Cannot be reversed!

`INDEX` is the index of the task as shown when displaying all tasks using `list`.

Format: `delete INDEX`

Example of usage: `delete 1`

### Search for tasks using keywords: `find`

Shows a list of tasks that match the keywords.
The keywords have to be exact words or phrases.

Format: `find KEYWORD_1 KEYWORD_2...`

Example of usage: `find cs2103t tp`

### Snooze a task: `snooze`

Delay the date of a deadline/event by a number of days.

`INDEX` is the index of the task as shown when displaying all tasks using `list`.

Format: `snooze INDEX NUMBER_OF_DAYS`

Example of usage: `snooze 1 3`