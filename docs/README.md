# User Guide

## Features 

### Create tasks

You can create 3 types of tasks using Duke:
1. [Todo](#adding-a-new-todo-task-todo): tasks without any date attached to it.
2. [Event](#adding-a-new-deadline-task-deadline): tasks with a date to be done at that date.
3. [Deadline](#adding-a-new-event-task-event): tasks with a date to be completed by that date

### [View list of tasks](#view-all-tasks-list)

You can view all tasks in a glance.

### [Mark a task as completed](#mark-tasks-as-completed-done)

When you have completed a task, Duke can mark that task as completed.

### [Delete a task](#delete-a-task-deadline)

If you want to remove a specific task from the list, Duke can delete it.


### [Search for task by name](#search-for-task-by-name-find)

You can search for a specific task by its name.

### [Undo a command](#undo-a-command-undo)

You can undo the previous commands you gave to Duke to carry out.

## Usage

### Adding a new Todo task: `todo`

Creates a new Todo task and adds it to the list.

Format: `todo <task_name>`

Example of usage: 
* `todo read book`

### Adding a new Event task: `event`

Creates a new Todo task and adds it to the list.

Format: `event <task_name> /at <YYYY-MM-DD>`
* Date must be given in the <YYYY-MM-DD> format.
* /at is compulsory.

Example of usage:
* `event birthday /at 2021-01-01`
* `event carnival /at 2020-12-25`

### Adding a new Deadline task: `deadline`

Creates a new Todo task and adds it to the list.

Format: `deadline <task_name> /by <YYYY-MM-DD>`
* Date must be given in the <YYYY-MM-DD> format.
* /by is compulsory.

Example of usage:
* `deadline assignment /by 2021-01-01`
* `deadline homework /by 2020-12-25`

### View all tasks: `list`

Shows the full list of tasks.

Format: `list`

### Mark tasks as completed: `done`

Marks a specific task as completed.

Format: `done <index>`
* `index` refers to the number the task takes in the list.
* an `index` of 3 refers to the task that is third on the list.
* `done 3` will mark the third task on the list as completed. 

Example of usage:
* `done 1`

### Delete a task: `deadline`

Removes a specific task from the list of tasks.

Format: `delete <index>`
* `index` refers to the number the task takes in the list.
* an `index` of 3 refers to the task that is third on the list.
* `delete 3` will remove the third task and tasks numbered 4 and above are re-ordered.

Example of usage:
* `delete 1`

### Search for task by name: `find`

Shows the tasks that contain the search terms in their names.

Format: `find <search_term>`
* tasks with names that contain `search_term` will be shown

Example of usage:
* `search CS2103T` return tasks with names `CS2103T`, and `CS`

### Undo a command: `undo`

Undo the command that last made changes to the list.

Format: `undo`
* if there are multiple commands that made changes to the list, using undo will undo commands from most recently
executed to earliest executed.
* if there are no commands that made changes to the list, Duke will display the message "Invalid command" as there are 
no commands to be undone.
  
### Exit Duke: `bye`

Exits Duke and closes the pop-up window. 

Format: `bye`
* Note that changes made to the task list will be saved upon execution, so closing the pop-up using the cross in the
top-right corner will achieve the same effect as the `bye` command.
