# User Guide

Duke is a **task management application** with a simple and easy-to-use GUI. It includes many features to help you track and manage your tasks efficiently.

## Features 

### Add Task

Add 3 types of tasks: a to-do, an event or a deadline

### Save Tasks
Duke saves the task list to your hard disk, so that you can jump right back after you quit the app!

### Mark Tasks as Done

You can mark a task as completed once it's finished

### List Tasks

View all your tasks at a glance, including their completion status

## Usage

### `todo` - Adds a todo
Adds a task to be done.

Example of usage: 

`todo buy groceries`

Expected outcome: The todo will be listed in the task list.

Description of the outcome.

```
List:
---------------
1. ToDo: buy groceries []
```
### `deadline` - Adds a deadline

Adds a task with a deadline. The deadline is a date with the format YYYY-MM-DD.

Example of usage:

`deadline submit homework /by 2021-10-20`

Expected outcome: The deadline will be listed in the task list 

Description of the outcome.

```
List:
---------------
1. Deadline: submit homework [] (by Oct 20 2021)
```
### `event` - Adds an event

Adds an event with a date. The date is specified with the format YYYY-MM-DD. 

Example of usage:

`event dinner /at 2021-09-01`

Expected outcome: The event will be listed in the task list.

Description of the outcome:

```
List:
---------------
1. Event: dinner [] (at Sep 1 2021)
```
### `list` - Show all tasks

Shows the task list

Example of usage:

`list`

Expected outcome: All tasks will be displayed in a list, with the completion status and dates (if applicable).

Description of the outcome.

```
List:
---------------
1. ToDo: buy groceries []
2. Deadline: submit homework [] (by Oct 20 2021)
3. Event: dinner [] (by Sep 1 2021)
```
### `done` - Mark a task as done

Mark a task as done, specify a task by its number in the task list.

Example of usage:

`done 2`

Expected outcome: The task that is done will be marked with a cross.

Description of the outcome.

```
List:
---------------
1. ToDo: buy groceries []
2. Deadline: submit homework [X] (by Oct 20 2021)
3. Event: dinner [] (by Sep 1 2021)
```

### `delete` - Delete a task 

Delete a task, specify a task by its number in the task list.

Example of usage:

`delete 2`

Expected outcome: The deleted task will no longer appear in the list.

Description of the outcome.

Before:
```
List:
---------------
1. ToDo: buy groceries []
2. Deadline: submit homework [] (by Oct 20 2021)
3. Event: dinner [] (by Sep 1 2021)
```
After:
```
List:
---------------
1. ToDo: buy groceries []
2. Event: dinner [] (by Sep 1 2021)
```

### `find` - Find a task with a search query

Find all tasks that match the query.

Example of usage:

`find homework`

Expected outcome: A list of tasks that match the query are displayed.

Description of the outcome.

```
List:
---------------
1. Deadline: submit homework [X] (by Oct 20 2021)
```

### `bye` - Exits the Duke application

Exits the application.

Example of usage:

`bye`

Expected outcome: Duke displays a goodbye message before exiting.

Description of the outcome.

```
Bye from Duke!
```



