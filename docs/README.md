# BMODuke User Guide

Welcome to BMODuke, your one-stop task, event and deadline logger!

![Screenshot of UI](Ui.png)

## Features Overview and Usage

### Add Task

Add a task to the current list of tasks.

Usage:\
Todo: `todo [description]` \
Event: `event [description] /at [dd-MM-yyyy h:mma]` \
Deadline: `deadline [description] /by [dd-MM-yyyy h:mma]`

Example of usage: `event b /at 11-11-1111 1111`

Expected output:
```
Got it. I've added this task:
[E][ ] b (at: Nov 11 1111 1111)
```

Example of usage: `todo ip`

Expected output:
```
Got it. I've added this task:
[T][ ] ip
```

### Update Task

Updates an existing task to the new user input. The task type does not have to be specified.

Usage:
`update [index of task] [description]`

Example of usage: `update 2 tp`

Expected output:
```
Task 2 has been updated to:
2. [T][X] tp
```

### Complete Task

Marks the task, specified by index, as completed. 

Usage: `done [index of task]`

Example of usage: `done 2`

Expected output:
```
Nice! I've marked this task as done:
2. [T][X] tp
```

### Find Task

Finds all tasks that contain the keyword used.

Usage: `find [description]`

Example of usage: `find tp`

Expected output:
```
Here are the matching tasks in your list:
2. [T][X] tp
```

### Show List of Tasks

Lists all the tasks that have been added up to that point  in time.

Usage: `list`

Expected output:
```
Here are the tasks in your list:
1. [E][X] b (at: Nov 11 1111 1111)
2. [T][X] tp
```

### Delete Task

Deletes a task, specified by index, from the list of tasks.

Usage: `delete [index of task]`

Example of usage: `delete 1`

Expected output:
```
Noted. I've removed this task:
[E][X] b (at: Nov 11 1111 1111)
Now you have 1 tasks in the list.
```
