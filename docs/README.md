# User Guide

## Features 

### Add a task

Adds a task which user has inputted.

### List all tasks

Lists all the tasks the user has.

### Complete a task 

Marks a task is done. 

### Delete a task 

Deletes a task from the list of tasks. 

### Find a task 

Finds a task containing the input keyword.

### View schedule 

Lists the schedule for a particular date. 

## Usage

### `todo` - Adds a todo task

A todo task with the input description is stored into the list of tasks.

Example of usage: 

`todo read book`

Expected outcome: Task to read book is added to the list.

```
Sure! I've added this task:
    [T][] read book 
You have (n) tasks in the list :)
```

### `event` - Adds an event task

An event task with the input description, date and time
is stored into the list of tasks.

Example of usage:

`event celeb /at 2021-01-01 18:00`

Expected outcome: The event is added to the list.

```
Sure! I've added this task:
    [E][] celeb (at: Jan 1 2021, 18:00)
You have (n) tasks in the list :)
```

### `deadline` - Adds a deadline task

A deadline task with the input description is stored into the list of tasks.

Example of usage:

`deadline read book /by 2021-01-01`

Expected outcome: Deadline task to read book is added to the list.

```
Sure! I've added this task:
    [D][] read book (by: Jan 1 2021)
You have (n) tasks in the list :)
```

### `list' - Lists all the tasks

All the tasks in the task list are listed out.

Example of usage:

`list`

Expected outcome: All tasks saved are listed out.

```
Here is your list of tasks :
  1.[T][] read book 
  2.[E][] celeb (at: Jan 1 2021, 18:00)
  3.[D][] read book (by: Jan 1 2021)
```

### `delete' - Deletes a task at the specified index. 

The task at the input index is removed from the task list.

Example of usage:

`delete 2`

Expected outcome: Task at index 2 is deleted.

```
Sure! I've removed this task:
 [E][] celeb (at: Jan 1 2021, 18:00)
You have (n) tasks in the list :)
```

### 'done' - Marks a task as done. 

The task at the input index is marked as done with 
the icon 'X'.

Example of usage:

`done 2`

Expected outcome: Task at index 2 is marked as done.

```
YAY good job for completing the task :)
I've marked it as done:
 [D][X] homework (by: Jan 1 2021)
```

### `find' - Finds a task with the specified keyword.

Tasks containing the specified keyword are listed out.

Example of usage:

`find book`

Expected outcome: Task(s) with description containing book 
are listed out.

```
I have found these matching tasks!!
 1. [E][] celeb (at: Jan 1 2021, 18:00)
```

### `schedule' - Lists out schedule for the specified date.

The schedule for input date is shown to user.

Example of usage:

`schedule 2021-01-01`

Expected outcome: Schedule for the date is listed. 

```
Here is your schedule :
[D][X] homework (by: Jan 1 2021)
[E][] celeb (at: Jan 1 2021, 18:00)
```
