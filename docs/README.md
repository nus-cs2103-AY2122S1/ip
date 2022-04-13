# User Guide to DUKE

## Features 

### Adding a task: `todo`, `event`, `deadline`
Adds a task to the task list.

There are 3 types of tasks: todo, event, and deadline, each with a
different format.

Format:
*  Todo:
    * `todo TASK_DESCRIPTION`
*  Event:
    * `event TASK_DESCRIPTION /at TIME`
*  Deadline:
    * `deadline TASK_DESCRIPTION /by TIME`
    
TASK_DESCRIPTION refers to the description of the task.

TIME refers to the time in which an event occurs or a deadline is 
due. TIME has a format of 'YYY-MM-DD hh-mm'

Examples:
- deadline finalise IP /by 2021-09-15 2359
- todo eat breakfast

### Display tasks: `list`

Displays all tasks in the list.

Format: list

## Delete task: `delete`

Deletes a specified task.

Format: `delete INDEX`

- Deletes a task of specified INDEX
- INDEX refers to the ordering of the tasks in the list
- INDEX has to be an integer greater than 0

Examples:
- `delete 1` Deletes the first task on the list

## Complete task: `done`

Marks a task as completed.

Format: `done INDEX`
- Marks a task of the specified INDEX as completed
- INDEX refers to the ordering of the tasks in the list
- INDEX has to be an integer greater than 0

Examples:
- `done 3` Marks the 3rd task on the list as completed

## Find task: `find`

Finds tasks which contain a specified keyword.

Format: `find KEYWORD`
- displays a list of tasks which contain KEYWORD

Example:
- `find eat` displays all tasks which contain the word 'eat'

## Sort task: `sort`

Sorts the tasks in specified order

Format:
* order by date
   * `sort by date`
* order by date in reverse 
   * `sort by date reverse`
* order by type of task
   * `sort by type`
   
## Save task: `bye`

Saves the list of task to /data/Duke.txt

Format: `bye`


