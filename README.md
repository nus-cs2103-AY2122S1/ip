# User Guide

IntelliBot is a brilliant personal task manager who will help to make your life
just a little easier. You can think of him as your very own butler!

## Features

### Task Management

IntelliBot is able to perform many operations that allow you to manage tasks. Some
examples are `todo`, `delete`, `find` and many more! 

### Continuous Memory

IntelliBot stores information about tasks in your local memory and accesses that 
memory whenever you use IntelliBot. That's right, you don't have to worry about 
losing track of your tasks because it is tracked continuously! 
(Unless you delete it of course, which would be pretty silly)

## Usage

### `help` - Gives a list of instructions

Shows the user a list of instructions that IntelliBot understands

Format: `help`

Expected outcome:
```
Fear not! The list of instructions is here:
'todo [task]' --> adds a Todo task to the list
'deadline [task] /by [timing]' --> adds a Deadline task to the list
'event [task] /by [timing]' --> adds an Event task to the list
'list -->' shows the tasks in the current list
'done [index]' --> marks a task in the list as done by its index
'delete [index]' --> deletes a task from the list by its index
'find [keyword]' --> finds all the tasks in the list that have a certain keyword
'bye' --> closes IntelliBot
```

### `todo` - Add a Todo task

Adds a Todo task to the task list and shows you the number of tasks in the current list.

Format: `todo [task]`

Examples (assume no tasks initially for both):
* `todo read book`
* `todo S/U`

Expected outcomes:

```
Got it. I've added this task:
    [T][] sleep
Now you have 1 task(s) in the list.
```

```
Got it. I've added this task:
    [T][] S/U
Now you have 1 task(s) in the list.
```

### `todo` - Add a Todo task

Adds a Todo task to the task list and shows you the number of tasks in the current list.

Format: `todo [task]`

Examples (assume no tasks initially for both):
* `todo read book`
* `todo S/U`

Expected outcomes:

```
Got it. I've added this task:
    [T][] sleep
Now you have 1 task(s) in the list.
```

```
Got it. I've added this task:
    [T][] S/U
Now you have 1 task(s) in the list.
```

### `deadline` - Add a Deadline task

Adds a Deadline task to the task list and shows you the number of tasks in the current list.

Format: `deadline [task] /by [timing]`
* If only a date is provided, and it is in either `yy/mm/dddd` format or `dddd/mm/yy` format, IntelliBot can format it into a more readable date
* Otherwise, the timing will remain as provided

Example (assume no tasks initially):
* `deadline push user guide /by 15/9/2021`

Expected outcome:

```
Got it. I've added this task:
    [D][] push user guide (by: 15 September 2021)
Now you have 1 task(s) in the list.
```

### `event` - Add an Event task

Adds an Event task to the task list and shows you the number of tasks in the current list.

Format: `event [task] /by [timing]`
* If only a date is provided, and it is in either `yy/mm/dddd` format or `dddd/mm/yy` format, IntelliBot can format it into a more readable date
* Otherwise, the timing will remain as provided

Example (assume no tasks initially):
* `event project meeting /at Monday 10-11am`

Expected outcome:

```
Got it. I've added this task:
    [E][] project meeting (at: Monday 10-11am)
Now you have 1 task(s) in the list.
```

### `list` - Show task list

Shows you the current task list and the details of each task

Format: `list`

Example:
```
Sample list:
    1. [T][] sleep
    2. [D][] push user guide (by: 15 September 2021) 
```

Expected outcome:

```
Here are the tasks in your list:;
    1. [T][] sleep
    2. [D][] push user guide (by: 15 September 2021)
```

### `done` - Mark a Task as done

Marks a task by the index in the list (list is 1-indexed)

Format: `done [index]`

Example:

```
Sample list:
    1. [T][] sleep
    2. [D][] push user guide (by: 15 September 2021) 
```

`done 2`

Expected outcome:
```
Nice! I've marked this task as done:
    [D][X] push user guide (by: 15 September 2021) 
```

### `delete` - Deletes a task

Deletes a task by the index in the list (list is 1-indexed) and shows you the size of the 
remaining list

Format: `delete [index]`

Example:

```
Sample list:
    1. [T][] sleep
    2. [D][] push user guide (by: 15 September 2021) 
```

`delete 2`

Expected outcome:
```
Noted. I've removed this task:
    [D][X] push user guide (by: 15 September 2021)
Now you have 1 task(s) in the list.
```

### `find` - Finds a task by a keyword

Finds all tasks that contain the specific keyword in its description

Format: `find [keyword]`

Example:

```
Sample list:
    1. [T][] sleep
    2. [D][] push user guide (by: 15 September 2021) 
```

`find sleep`

Expected outcome:
```
Here are the matching tasks in your list:
    1. [T][] sleep
```

### `bye` - Closes IntelliBot

Ends interaction with IntelliBot and saves all changes to memory

Format: `bye`

Expected outcome:
```
Peace out!
```

## Command Summary

| Command           | Formats                       |
| :---------------- | :----------:                  |
| todo              | `todo` [task]                   |
| deadline          | `deadline` [task] `/by` [timing]|
| event             | `event` [task] `/at` [timing]   |
| list              | `list`                          |
| done              | `todo` [task]                   |
| delete            | `delete` [index]                |
| find              | `find` [index]                  |
| bye               | `bye`                  |