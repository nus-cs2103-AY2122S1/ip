# User Guide

Jo is a **desktop app** for managing your tasks. A frog wannabe chat bot, 
she is hungry for the tasks that you have in store for her!

## Features 
Notes about the command format: <br/>
- Words in `UPPER_CASE` are the parameters to be supplied by the user. <br/>
e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo work`

### Adding a "ToDo" task: `todo` or `t`
A ToDo is a task without any date/time attached to it _e.g., visit new theme park_

Format: `todo TASK_DESCRIPTION` or `t TASK_DESCRIPTION`

### Adding a "Deadline" task: `deadline` or `d`
A Deadline is a task that needs to be done before a specific date/time _e.g., submit CS2103T iP before 10 Oct 10pm_

Format: `deadline TASK_DESCRIPTION /by DEADLINE` or `d TASK_DESCRIPTION /by DEADLINE`, where `DEADLINE` must follow the syntax `YYYY-MM-DD TIME`

### Adding an "Event" task: `event` or `e`
An Event is a task that start at a specific time and ends at a specific time _e.g., team meeting on 11/09/2021 9-10pm_

Format: `event TASK_DESCRIPTION /at DATE_AND_TIME` or `e TASK_DESCRIPTION /at DATE_AND_TIME`

### Listing all tasks: `list`
Shows a list of tasks in the task list

Format: `list`

### Mark task as done: `done`
Marks the given task as _done_

Format: `done TASK_INDEX`

### Delete a task: `delete`
Deletes the given task from the task list

Format: `delete TASK_INDEX`

### Locating tasks by name: `find`
Find tasks whose description contain the given keyword

Format: `find KEYWORD`

### Exiting the program: `bye`
Exits the program by saying bye to Jo. This saves the task list.

Format: `bye`

### Saving the data
Jo's data are saved in the hard disk automatically after the program has exited. 
There is no need to save manually.

### Editing the data file
Jo's data are saved as a txt file `duke.txt`. Advanced users are welcome to update data directly by editing that data file.

## Command Summary

Action | Format, Examples
-------|------------------
Add ToDo | `todo TASK_DESCRIPTION`<br/> `t TASK_DESCRIPTION`<br/>  e.g.,`todo work`
Add Deadline |  `deadline TASK_DESCRIPTION /by DEADLINE`<br/> `d TASK_DESCRIPTION /by DEADLINE`<br/> e.g.,`deadline submit CS2103T /by 2021-09-12 10pm`
Add Event | `event TASK_DESCRIPTION /at DATE_AND_TIME`<br/> `e TASK_DESCRIPTION /at DATE_AND_TIME` <br/> e.g.,`event team meeting /at 11/09/2021 9-10pm`
List | `list`
Done | `done TASK_INDEX` <br/> e.g., `done 2`
Delete | `delete TASK_INDEX` <br/> e.g., `delete 3`
Find | `find KEYWORD` <br/> e.g., `find book`
Exit | `bye`

