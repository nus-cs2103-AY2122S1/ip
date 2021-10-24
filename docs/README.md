# User Guide

Duke, aka Dwight Shrute, is your personal task manager.
Let Dwight know of your existing tasks and Dwight will help you to keep track of it. 

## Features 

### Add Personal Tasks

Dwight handles 3 different types of task:<br>
* Todo
* Deadline
* Event


### Edit Task State

Dwight can also help you to edit existing tasks in the following way:<br>

* *Check off* completed tasks
* *Delete* existing tasks


### Search for Tasks

Dwight is also capable of searching for existing tasks with given keywords.

## Usage

### `todo` - Add a Todo-type task

Example of usage: 

`todo Homework`

Expected outcome:

```
T | | Homework
```

### `deadline` - Add a Deadline-type task

Example of usage:

`deadline CS2103T iP /by 2021-09-01`

Expected outcome:

```
D | | CS2103T iP | Jan 9 2021
```

### `event` - Add an Event-type task

Example of usage:

`event CS2101 OP1 /at 2021-09-02`

Expected outcome:

```
E | | CS2101 OP1 | Feb 9 2021
```

### `list` - Lists all existing tasks

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. T | | Homework
2. D | | CS2103T iP | Jan 9 2021
3. E | | CS2101 OP1 | Feb 9 2021
```

### `done` - Mark an existing task as completed
Select the task to check off based on the index in your task list.

Example of usage:

`done 1`

Expected outcome:

```
Noted. I've marked this task as done:
T |X| Homework
```
### `delete` - Delete an existing task
Select the task to delete based on the index in your task list.

Example of usage:

`done 1`

Expected outcome:

```
Noted. I've removed this task:
T |X| Homework
You now have 2 tasks in the list.
```
### `find` - Search for an existing task
Search for an existing task based on a keyword
Example of usage:

`find cs2103`

Expected outcome:

```
Here are the tasks in your list that match the keyword:
D | | CS2103T iP | Jan 9 2021
```

### `statistics` - View the current statistics 

Example of usage:

`statistics`

Expected outcome:

```
Total Tasks Done: 1
Total Tasks Added: 3
Total Tasks Deleted: 1 
```

### `bye` - Exits the application

Example of usage:

`bye`



### `help` - Learn how to use the application

Example of usage:

`help`

Expected outcome:

```
Here are my commands:
Todo: todo [task]
Deadline: deadline [task] /by YYYY-MM-DD
Event: event [task] /at YYYY-MM-DD
Done: done [task number]
List: list
Delete: delete [task number]
Find: find [keyword]
Delete: delete [task number]
Save: save
Exit: bye
```



