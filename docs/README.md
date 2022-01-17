# User Guide

## Features 

### Managing Tasks

Helps you manage tasks, deadlines and events.

## Usage

### Exit the program - `bye` 

Terminates the program.

Example of usage: `bye`

Expected outcome: The program is terminated.

### List all tasks - `list`

Prints out all current tasks in the list.

Example of usage: `list`

Expected outcome: All current tasks in the list are printed out.

```
Current list of tasks:
	1.[T][X] IPPT
	2.[D][ ] iP (by: 17/09/2021)
	3.[E][ ] Meet up with friends (at: 22 September 2021)
Now you have 3 tasks in the list.
```

### Complete a task - `done`

Mark the specified task as completed.

Format: `done TASK_INDEX`

Example of usage: `done 1`

Expected outcome: The first task in the list is marked as completed.

```
Marking task as completed:
	[T][X] IPPT
```

### Add a ToDo task - `todo`

Adds a ToDo task to the list.

Format: `todo TASK_NAME`

Example of usage: `todo iP Project`

Expected outcome: The ToDo task 'iP Project' is added to the list.

```
Added:
	[T][ ] iP Project
Now you have 4 tasks in the list.
```

### Add a Deadline task - `deadline`

Adds a Deadline task to the list.

Format: `deadline TASK_NAME /by YYYY-MM-DD`

Example of usage: `deadline iP project /by 2021-09-17`

Expected outcome: The deadline 'iP project' due by 7th Sept 2021 is added to the list.

```
Added:
	[D][ ] iP project (by: 17/09/2021)
Now you have 5 tasks in the list.
```

### Add an Event task - `event`

Adds an Event task to the list.

Format: `event TASK_NAME /at YYYY-MM-DD`

Example of usage: `event Tipsy's Birthday /at 2021-09-25`

Expected outcome: The event 'Tipsy's Birthday' happening at 25th Sept 2021 is added to the list.

```
Added:
	[E][ ] Tipsy's Birthday (at: 25 September 2021)
Now you have 6 tasks in the list.
```

### Delete a task - `delete`

Removes the specified task from the list.

Format: `delete TASK_INDEX`

Example of usage: `delete 2`

Expected outcome: The second task in the list is removed from the list.

```
Removing task:
	[D][ ] iP (by: 17/09/2021)
Now you have 5 tasks in the list.
```

### Find tasks - `find`

Searches for tasks with the specified expression in their task names.

Format: `find SEARCH_EXPRESSION`

Example of usage: `find proj`

Expected outcome: The list of tasks in the task list that has 'proj' in their task names will be printed out.

```
Tasks with "proj":
	4.[D][ ] iP project (by: 17/09/2021)
Total of 1 result found.
```

### Show current save file path - `showpath`

Prints out the current file path of the task list's save file.

Example of usage: `showpath`

Expected outcome: The save file path is printed out.

```
Current path is:
/Users/SLong/Documents/GitHub/ip/data/tipsy.txt
```

### Set new save file path - `setpath`

Sets a new file path for the task list's save file.

Format: `setpath FILE_PATH`

Example of usage: `setpath /Saves/tasklist1.txt`

Expected outcome: The task list is now set to be saved at '/Saves/tasklist1.txt'.

```
New path set:
/Users/SLong/Documents/GitHub/ip/Saves/tasklist1.txt

I have loaded your past tasks list!
Current list of tasks:
Now you have 0 tasks in the list.
```
