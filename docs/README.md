# User Guide
Duke is a desktop app for managing your tasks that 
incorporates the benefits of a Graphical User Interface (GUI). 
It is simple and optimised to keep track of your tasks 
efficiently.

## Features 

>Notes about the command format:
> * Words in UPPER_CASE are the parameters to be supplied by the user.
> * yyyy-mm-dd indicates the format of the date parameter for deadline-type tasks.\
> E.g. `deadline assignment /by 2021-09-17 2359` indicates that the \
    deadline-type task 'assignment' is due by 17 September 2021, 2359.
> * There is no specified format for time parameters.\
> E.g. 10pm, 10:00pm, 2200 are all valid time parameters.


### Viewing help: `help`

Shows a message explaining how to use the application, by showing the 
different commands and a brief description.

Format: `help`

### Adding a todo-type task: `todo`

Adds a todo-type task to the task list. 

Format: `todo DESCRIPTION`

Examples:
* `todo run errands` adds todo task to run errands.
* `todo buy lemons` adds todo task to buy lemons.

Example of expected response from Duke:
````
Got it. I've added this task:
[T][] run errands
Now you have 1 task(s) in the list.
````

### Adding a deadline-type task: `deadline`

Adds a deadline-type task to the task list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD TIME`

> Tip: TIME can have any format. E.g. 10pm, 2200, 10:00 but cannot be left empty.

Examples;
* `deadline assignment /by 2021-09-18 2359` adds deadline task 'assignment' 
  that is due by 18 September 2021, 2359.
* `deadline complete spreadsheet /by 2021-09-20 1pm` adds deadline 
  task 'complete spreadsheet' that is due by 20 September 2021, 1pm.

Example of expected response from Duke:
````
Got it. I've added this task:
[D][] assignment (by: Sep 18 2021, 2359)
Now you have 2 task(s) in the list.
````

### Adding an event-type task: `event`

Adds an event-type task to the task list.

Format: `event DESCRIPTION /at TIME`

> Tip: TIME can have any format. E.g. 10pm, 2200, 10:00 but cannot be left empty.

Examples;
* `event meeting /at 6pm` adds event task 'meeting' that is occurring at 6pm.
* `event swim meet /at 0900` adds deadline task 'swim meet' that 
  is occurring at 9am.

Example of expected response from Duke:
````
Got it. I've added this task:
[E][] meeting (at: 6pm)
Now you have 3 task(s) in the list.
````

### Deleting a task: `delete`

Deletes a specified task from the task list by the index of the task.

Format: `delete INDEX`

Examples:
* `delete 3` deletes the third item from the task list.

Example of expected response from Duke:
````
Noted I've removed this task:
[E][] meeting (at: 6pm)
Now you have 2 task(s) in the list.
````

### Marking a task as completed: `done`

Marks the specified task as completed and indicates this with an 'X'.

Format: `done INDEX`

Examples:
* `done 6` marks the sixth task in the task list as done.

Expected Outcome:
````
Nice! I've marked this task as done:
[T][X] run errands
````

### Listing all tasks: `list`

Shows a list of all the tasks in the task list.

Format: `list`

Example of expected response from Duke:
````
Here are the tasks in your list:
1. [T][X] run errands
2. [D][] assignment (by: Sep 18 2021, 2359)
````

### Finding task by keywords: `find`

Find tasks with descriptions that contain the given keywords.

Format: `find KEYWORD`

>Notes: 
> * There can be more than one keyword.
> * The search is case-sensitive
> * The order of the keywords matter.

Examples:
* `find run`

Example of expected response from Duke:
````
Here are the matching tasks in your list:
1. [T][X] run errands
````

### Exiting the program: `bye`

Stops the program.

Format: 'bye'

### Saving the data

Duke data is saved in the hard disk automatically after any command that 
changes the data. there is no need to save manually.

## Command Summary

Action | Format | Examples
-------|---------|--------
Help | `help` | -
Add todo | `todo DESCRIPTION` | `todo run errands`
Add deadline | `deadline DESCRIPTION /by YYYY-MM-DD TIME`| `deadline assignment /by 2021-09-18 2359`
Add event | `event DESCRIPTION /at TIME` | `event meeting /at 6pm`
Delete | `delete` | -
Mark as done | `done INDEX` | `done 2`
List | `list` | -
Find | `find KEYWORD` | `find run errands`
Exit | `bye` | -