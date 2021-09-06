# User Guide

## Features 

### Feature- Management of various task

Application allows users to add, delete, mark as done 3 different type of tasks(ToDos, Events and Deadline).


## Usage

### `list` - Lists out all tasks

Lists out all task that is previously stored.

Example of usage: 

`list`

Expected outcome:

The task are displayed with its type as well as its doneness.

```
Here are the tasks in your list:
1.[T][X] check1
2.[E][ ] check2  (at: time1)
3.[D][ ] check3  (by: time2)
```

### `done` - Marks a task as done

It marks the task at the specified index to be done.

Example of usage:

`done 1`

Expected outcome: 

The task from the list corresponding to the number will be marked done.

```
Nice! I've marked this task as done:
[T][X] check1
```
### `todo` - Creates a new todo task

Creates a new todo task adding to the back of the list.

Example of usage:

`todo check1`

Expected outcome:

A new todo task is added to the back of the list.

```
Got it. I've added this task:
  [T][ ] check1
Now you have 1 tasks in the list.
```

### `event` - Creates a new event task

Creates a new event task adding to the back of the list with timing specified by /at.
Timing is to be specified in a yyyy-mm-dd format

Example of usage:

`event check2 /at time1`

Expected outcome:

A new event task is added to the back of the list with timing specified by /at.

```
Got it. I've added this task:
  [E][ ] check2  (at: time1)
Now you have 2 tasks in the list.
```

### `deadline` - Creates a new deadline task

Creates a new deadline task adding to the back of the list with timing specified by /by.
Timing is to be specified in a yyyy-mm-dd format

Example of usage:

`deadline check3 /by time2`

Expected outcome:

A new deadline task is added to the back of the list with timing specified by /by.

```
Got it. I've added this task:
  [D][ ] check3  (by: time2)
Now you have 3 tasks in the list.
```

### `activity` - Creates a new activity task

Creates a new deadline task adding to the back of the list with timing specified by /for. 
Timing is to be specified in a yyyy-mm-dd format

Example of usage:

`activity check2 /for 2`

Expected outcome:

A new activity task is added to the back of the list with timing specified by /by.

```
Got it. I've added this task:
  [A][ ] check2  (needs 2 hours)
Now you have 3 tasks in the list.
```

```
Bye. Hope to see you again soon!
```
### `bye` - Exits the program

Exits the Duke program and save the current task list.

Example of usage:

`bye`

Expected outcome:

Program exits.

```
Bye. Hope to see you again soon!
```