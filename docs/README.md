# User Guide
Welcome to DukePro! 

## Features 

### TaskList
DukePro is a Task Manager that can help you in keeping track three types of tasks:
1. Todos - Helps you in remembering tasks you need to do!
2. Deadlines - Helps you in completing your tasks on time!
3. Events - Helps you in taking down tasks' timings!


## Usage

### `todo` - Adds a todp task to the task list.

Example of usage:

`todo homework`


### `deadline` - Adds a task with deadline to the task list.

Example of usage:

`deadline submission /by 2021-12-23 1800`


### `event` - Adds a task with a specific time frame.

Describe the action and its outcome.

Example of usage:

`event camp /at 2021-12-23 1800 2021-12-24 1500`


### `list` - Lists all the saved tasks in the task list.

Lists all the saved task in the task list in the order of addition.

Example of usage:

`list`


### `done` - Marks a specific task as done.

Example of usage:

`done INDEX`, where INDEX is the index of the task when `list' is called.


### `delete` - Deletes a specific task from the task list.

Example of usage:

`delete INDEX`, where `INDEX` is the index of the task when `list` is called.


### `find` - Finds tasks from the task list.

Finds all tasks from the task list that match the keyword given.

Example of usage:

`find KEYWORD`, where `KEYWORD` is the keyword specified by the user.


### `sort` - Sorts the task list based on their timings.

Sorts the tasks based on their timings in ascending order. 
It only returns the sorted list to the user, the order of the original task list is unchanged.

Example of usage:

`sort`


### `help` - Shows the summary command.


Example of usage:

`help`


### `bye` - Ends session with DukePro.

Example of usage:

`bye`
Expected outcome:

Description of the outcome.

```
Bye. Hope to see you again soon!
```
