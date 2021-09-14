# User Guide

## Features

### Add Tasks

The user's input will be added
as a task into the program.

### Add ToDos

ToDo types of tasks can also be added
to the program.

### Add Deadlines and Events

Deadline and Event types of tasks can be added
to the program as well, and they could include
dates and/or timings.

### Delete Tasks

Any task in the list can be deleted by the user.

### Find tasks

Users can search for at least one task in the list.

### Mark tasks as done

Users can indicate that a task has been completed.

### List tasks

All existing tasks, whether complete or incomplete,
can be displayed to the user.

### Undo Changes

The most recent change made to the task list
(such as deleting a task, adding a new task) can be
undone.

## Usage

### `todo` - Adds a Todo task

Example of usage:

todo clean room

Expected outcome:

Adds the ToDo to the list, and prints
the correct statement:

```
Got it. I've added this task:  
    [T][] clean room
Now you have 2 tasks in the list.
```
### `deadline` - Adds a Deadline task

Example of usage:

deadline finish hw /by 1800

Expected outcome:

Adds the Deadline to the list, and prints
the correct statement:

```
Got it. I've added this task:
    [D][] finish hw (by: 6pm)
Now you have 3 tasks in the list.
```

### `find` - Adds an Event task

Example of usage:

event School Fair /at 2/12/2019 1300

Expected outcome:

Adds the Event to the list, and prints
the correct statement:

```
Got it. I've added this task:
    [E][] School Fair (at: Dec 2 2019 1pm)
Now you have 5 tasks in the list.
```

### `delete` - Deletes a task

Example of usage:

delete 2

Expected outcome:

Deletes the 2nd task in the list, and
prints the correct statement:

```
Noted. I've removed this task.
   [T][] clean room
Now you have 4 tasks in the list.
```

### `find` - Finds all corresponding tasks

Example of usage:

find room

Expected outcome:

Finds all tasks containing the word "room",
and prints the correct statement:

```
Here are the matching tasks in your list:
  1. [T][] buy room decor
```

### `done` - Mark a task as done

Example of usage:

done 1

Expected outcome:

Marks the first task in the list as done,
and prints the correct statement:

```
Nice! I've marked this task as done:
   [D][X] finish hw (by: 6pm)
```

### `list` - Lists all the tasks

Expected outcome:

Prints the correct statement (shows
all the tasks and whether they have been
completed or not):

```
Here are the tasks in your list:
  1. [T][] call friend
  2. [D][X] finish hw (by: 6pm)
  3. [E][] School Fair (at: Dec 2 2019 1pm)
  4. [T][] buy room decor
```

### `undo` - Undoes the most recent task

Expected outcome:

Reverts the change made and prints the
correct statement:

(For example, if delete 3 is performed,
followed by undo, the output is shown below:)

```
Undid delete on task 3
```
