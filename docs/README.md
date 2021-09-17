# User Guide

## Features 

### Add Todo

Add a **Todo** Task to your Task List which represents a Task to be done.

### Add Deadline

Add a **Deadline** Task to your Task List which represents a Task to be done, by the given deadline.

### Add Event

Add a **Event** Task to your Task List which represents a Task to be done, at the given timing.

### Delete Task

Delete any Task in the Task List.

### List Tasks

View all Tasks in the Task List.

### Mark as Done

Marks the specified Task as done.

### Find Task

Shows all Task matching the keyword given.

### Change Priority

Change the Priority level of a specified Task.

### Save Data

Saves all Task in the Task List to the hard drive.

## Usage

### `todo` - Adds a todo to the Task List

Creates a Todo with the specified description and adds it to the Task List.

Example of usage: 

`todo homework`

Expected outcome:

Message informing user that the todo has been added, along with
how many items are currently in the list.

```
I have added to the list:
        [T][] homework Low lvl. 
        There are 2 items in the list 
```

### `deadline` - Adds a deadline to the Task List

Creates a Deadline with the specified description and deadline and
adds it to the Task List.

Example of usage: 

`deadline homework /by 2021-09-21`

Expected outcome:

Message informing user that the deadline has been added, along with
how many items are currently in the list.

```
I have added to the list:
        [D][] homework (by Sep 21 2021) Low lvl. 
        There are 2 items in the list 
```

### `event` - Adds an event to the Task List

Creates an event with the specified description and time and adds it to the Task List.

Example of usage: 

`event submit homework /at 2021-09-22`

Expected outcome:

Message informing user that the task has been added, along with
how many items are currently in the list.

```
I have added to the list:
        [E][] submit homework (at Sep 22 2021) Low lvl.
        There are 3 items in the list 
```

### `delete` - Deletes a Task from the Task List

Deletes a Task at the specified index number.

Example of usage: 

`delete 2`

Expected outcome:

Message informing user that the task has been deleted.

```
    Noted. The task has been removed! 
```

### `list` - Lists all the Task in the Task List

Displays a List of all the Task in the Task List.

Example of usage: 

`list`

Expected outcome:

List of all the Task in the Task List.

```
    1.[T][x] homework Low lvl
    2.[D][] assignment (by Sep 21 2021) Medium lvl!
    3.[E][] exam (at Sep 22 2021) Low lvl
```

### `done` - Marks a Task as done

Marks the Task at the specified index number as done.

Example of usage: 

`done 1`

Expected outcome:

Message informing user that the task has been marked as done.

```
    Good job! This task has been completed:
        [T][X] homework Low lvl
```

### `find` - Finds Tasks from the Task List

Find Tasks that match the given keyword, and displays them.

Example of usage: 

`find homework`

Expected outcome:

List of all the Task that match the keyword.

```
    1.[T][X] homework Low lvl
    2.[D][] submit homework (by Sep 25 2021) Low lvl
```

### `priority` - Changes the Priority of a Task

Changes the priority of the Task at the specified index number, to the specified priority level (low, medium, high).

Example of usage: 

`priority 2 high`

Expected outcome:

Message informing user that the task's priority has been 
changed.

```
    The priority for this task has been changed:
        [D][] assignment (by Sep 21 2021) HIGH LVL!!
```

### `save` - Saves the Tasks in the Task List

Saves the Tasks in the Task List to the hard drive.

Example of usage: 

`save`

Expected outcome:

Message displaying successful save.

```
    List successfully saved
```