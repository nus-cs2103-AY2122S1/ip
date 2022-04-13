# User Guide

## Welcome
Here is a Duke program built by me, ready for your usage
anytime! You can store and manage your task list perfectly
using Duke, feel free to start anytime!

## Features 

### `todo` - Add a 'todo' item to the task list

Have a task that you need to do but there is no specific 
deadline? This command lets you add a 'todo' item. 
Here's how to use it.

Format:
`todo <TASK_DESCRIPTION>`

Example of usage:

`todo iP`

Expected outcome:

`I've added this task.`

`[T] [] iP`

`Now you have 1 tasks.`

The todo task 'iP' would be added to the tasklist.

### `deadline` - Add a 'deadline' item to the task list

Have a task that you need to do by a specific
deadline? This command lets you add a 'deadline' item.
Here's how to use it.

Format:
`deadline TASK_DESCRIPTION /YYYY-MM-DD`

Example of usage:

`deadline do tP /2021-09-17`

Expected outcome:

`I've added this task.`

`[D] [] do tP (by: Sep 17 2021)`

`Now you have 2 tasks.`

The deadline task 'do tP' would be added to the tasklist.

### `event` - Add an 'event' item to the task list

Have a task that you need to attend during a specific
time? This command lets you add an 'event' item.
Here's how to use it.

Format:
`event TASK_DESCRIPTION /YYYY-MM-DD`

Example of usage:

`event go home /2021-09-18`

Expected outcome:

`I've added this task.`

`[E] [] go home (at: Sep 18 2021)`

`Now you have 3 tasks.`

The event task 'go home' would be added to the tasklist.


### `list` - List out all tasks in the task list

Want to see all the tasks that you have added?
This command lets you view all tasks in the task list.
Here's how to use it.

Format:
`list`

Example of usage:

`list`

Expected outcome:

`Here is your to-do list!`

`1. [T][] iP`

`2. [D][] do tP (by: Sep 17 2021)`

`3. [E][] go home (at: Sep 18 2021)`

### `done` - Mark a specific task as done

Completed a task and want to indicate it as so?
This command lets you mark a task as done.
Here's how to use it.

Format:
`done TASK_NUMBER` (starting from 1)

Example of usage:

`done 1`

Expected outcome:

`I've marked this task as done:`

`[X] iP`

The task 'iP' is now marked as done and is indicated
with a 'X'.

### `delete` - Delete a specific task in the task list

Have a task that you want to delete?
This command lets you delete a task in the task list.
Here's how to use it.

Format:
`delete TASK_NUMBER`

Example of usage:

`delete 3`

Expected outcome:

`I've deleted this task:`

`[X][] go home`

The task 'go home' would be removed from the task list.

### `find` - Find all matching tasks in the task list

Want to find tasks based on a keyword?
This command lets you find matching tasks based on a
keyword in the task list.
Here's how to use it.

Format:
`find KEYWORD`

Example of usage:

`find tP`

Expected outcome:

`Here are the matching tasks:`

`1. [D][] do tP (by: Sep 17 2021)`

If there is more than 1 match, it would list out all
possible matches.

### `view` - View all tasks for a specific date in the task list

Want to see all the tasks you have to do for a day?
This command lets you view all tasks based on a
date in the task list.
Here's how to use it.

Format:
`view YYYY-MM-DD`

Example of usage:

`view 2021-09-17`

Expected outcome:

`Here are your tasks for that day:`

`1. [D][] do tP (by: Sep 17 2021)`

If there is more than 1 task, it would list out all
the tasks for that day.

## Saving of Data
Duke automatically saves the task list into /data/duke.txt,
no need to worry about your precious task list going missing!

## FAQ
Q: Is this Duke program good?

A: Yes!
