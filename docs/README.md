# User Guide
Welcome to the Duke user guide.

## Features
Duke helps to keep track of your tasks, events and deadlines.

The following features are currently implemented:
1.  Add an Event
2.  Add a Todo
3.  Add a Deadline
4.  List out all tasks
5.  Delete a task
6.  Complete a task

### Feature - Add a task 

There are three types of tasks that can be added to the task list.
All the tasks carry a description field that can be added to the task.

### Feature - Deletion

Tasks that were added can be deleted from the list

### Feature - List

Tasks that were added can be listed out based off the order they were added.

### Feature - Completion

The tasks that were added to the list can be set to be completed.

## Usage

### `todo` - Adds a todo task

Adds a Todo task to the list

Example of usage: 

`todo do something`

Expected outcome:

Shows the task that was added and lists the number of tasks in the list.

```text
> I have added the following task
>  [T][] do something
```

### `deadline` - Adds a deadline task

Adds a Deadline task to the list

Example of usage:

`deadline do something /by 28/09/2021`

Expected outcome:

Shows the deadline task that was added and lists the other tasks in the list

```text
> I have added the following task
>  [D][] do something (deadlineDate: 28/09/2021)
```



### `event` - Adds an event task 

Adds a Event task to the list

Example of usage:

`event do something /at some place`

Expected outcome:

Shows the event task that was added and lists the other tasks in the list

```text
> I have added the following task
>  [D][] do something (at: some place)
```

### `delete` - Removes a task

Deletes a task of a specified index.

Example of usage:

`delete 1`

Expected output:

Shows the todo that was deleted from the list.

```text
> I have removed the following task:
>  [T][] do something
```

### `list` - Lists out all the tasks

List out all of the tasks currently stored in duke

Example of usage:

Shows all the todos that are currently in the task list.

`list`

Expected output:
```text
> Here are the tasks in your list:
> 1. [T][] do something
> 2. [D][] do something (deadlineDate: 28/09/2021)
```