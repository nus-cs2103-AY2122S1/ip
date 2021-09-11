# User Guide

## Features 

### Types of Task

- Todo
- Deadline
- Event

### Adding tasks
Allows user to add different tasks.
### Completing tasks
Allows user to mark task as completed.
### Deleting tasks
Allows user to delete task.
### Finding tasks
Allows user to find task.
### Displaying tasks
Allows user to see all recorded tasks.

## Usage

### `Add` - Adds tasks

There are three different types of tasks and each has its own format when adding these tasks. To add the respective tasks,
please follow these format:

#### Todo:
`todo <task_description>`

Expected outcome:

A `Todo` task named `<task_description>` will be added.


#### Deadline:
`deadline <task_description> /by <date_and_time>`

Expected outcome:

A `Deadline` task named `<task_description>` with deadline `date_and_time` will be added.


#### Event:
`event <task_description> /at <date_and_time>`

Expected outcome:

A `Event` task named `<task_description>` at `date_and_time` will be added.


### `Done` - Marks task as done

To mark the specific task as done, please follow this format:

#### Done
`done <task_number>`

Expected outcome:

The task with the specific `<task_number>` will be marked as done, as indicated by a [x] beside the task description.


### `Delete` - Deletes a task from the list

To delete a specific task from the list, please follow this format:

#### Delete
`delete <task_number>`

Expected outcome:

The task with the specific `<task_number>` will be deleted.


### `Find` - Finds a task

To find tasks using a specific keyword, please follow this format:

#### Find
`Find <keyword>`

Expected outcome:

Task(s) with `<keyword>` will be found and displayed.


### `List` - Displays all the tasks

To view all the tasks, please follow this format:

#### List
`list`

Expected outcome:

All the tasks will be displayed.


### `Bye` - Saves the current tasks and exit

To save all the current tasks and exit, please follow this format:

#### Bye
`bye`

Expected outcome:
Current tasks will be saved and exits. Saved tasks will be displayed when `Duke` is run.