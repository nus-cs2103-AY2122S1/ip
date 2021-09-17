# Side User Guide

## Features

### Add tasks to be done with ```todo```
Users can add a task to be done.

### Add tasks with deadlines with ```deadline```
Users can add a task that comes with a deadline.

### Add tasks that have start and end datetimes with ```event```
Users can add a task that comes with start and end date and times.

### See all tasks with ```list```
Users can see all the tasks they have given Side.

### Mark tasks as Complete with ```done```
Users can mark tasks off when they are completed.

### Remove tasks with ```delete```
Users can delete tasks that are done or irrelevant.

### Find tasks matching input with ```find```
Users can find tasks whose names match the user input.

### Exit and save tasks with ```bye```
Lets user tell Side goodbye and save tasks for future use.

## Usage

### `todo` - Add task to be done

Task that is not tagged with date or time is added to the list.

Format:

`todo [taskname]`

Example of usage:

`todo exercise`

Expected outcome:

`Fine, I'll add: [T][] exercise`

`You now have 1 task...`

### `deadline` - Add task to be done by a deadline

Task that is due by a certain date and time is added to the list.

Format:

`deadline [taskname] /by YYYY-MM-DD`

`deadline [taskname] /by YYYY-MM-DD, HHmm`

Example of usage (Datetime):

`deadline exercise /by 2020-11-11, 1800`

Expected outcome:

`Fine, I'll add: [D][] exercise (by: 11 Nov 2020, 6:00:00PM)`

`You now have 1 task...`

Example of usage (Date only):

`deadline exercise /by 2020-11-11`

Expected outcome:

`Fine, I'll add: [D][] exercise (by: 11 Nov 2020)`

`You now have 1 task...`

### `event` - Add task tagged with start and end datetime

Task that occurs over a period of time is added to the list.

Format:

`event [taskname] /at YYYY-MM-DD /to YYYY-MM-DD`  
`event [taskname] /at YYYY-MM-DD, HHmm /to YYYY-MM-DD, HHmm`

Example of usage (Datetime):

`event tutorial /at 2020-11-11, 1800 /to 2020-11-12, 1800`

Expected outcome:

`Fine, I'll add: [E][] tutorial (at: 11 Nov 2020, 6:00:00PM to 12 Nov 2020, 6:00:00PM)`

`You now have 1 task...`

Example of usage (Date only):

`event tutorial /at 2020-11-11 /to 2020-11-12`

Expected outcome:

`Fine, I'll add: [E][] tutorial (at: 11 Nov 2020 to 12 Nov 2020)`

`You now have 1 task...`

### `list` - See all current tasks

Shows all tasks currently in list.

Example of usage:

`list`

Expected outcome:

`Fine, here are your tasks:`

`1. [T][] exercise`

### `done` - Mark task as complete

Tags task as done by list index.

Example of usage:

`done 1`

Expected outcome:

`Fine, I'll mark it for you: [T][x] exercise`

### `delete` - Delete a task

Removes task specified by list index.

Example of usage:

`delete 1`

Expected outcome:

`Fine, I'll delete: [T][] exercise`

`You now have 0 tasks...`

### `find` - Find tasks matching input

Tasks that match the string given will be shown.

Example of usage:

`find exer`

Expected outcome:

`I found...`

`1. [T][] exercise`

### `bye` - Say goodbye to Side

Closes Side, and saves the tasks to show next session.

Example of usage:

`bye`

Expected outcome:

`Oh, you have to go? What a pity...`