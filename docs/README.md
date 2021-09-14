# User Guide

## Features

### Adding a task

Adds a task to the task list.

### Deleting a task

Deletes the specified task from the task list.

### Marking task as done

Marks the specified task as done.

### Listing all tasks

Shows a list of tasks in the task list.

### Finding task by task name

Finds task whose names contain any of the given keywords.

### Bye

Exits the application.

## Usage

### Adds a Todo task - `todo (description)`

Adds a Todo task with the task name `(description)`.

Example of usage: 

`todo read books`

Expected outcome:

`Got it. I've added this task:
[T][] read book
Now you have 1 tasks in the list.`


### Adds a Deadline task - `deadline (description) /by (date)`

Adds a Deadline task with the task name `(description)` and `(date)`.

Example of usage: 

`deadline return book /by 2021-09-30`

Expected outcome:

`Got it. I've added this task:
[D][] return book(by:Sep 30 2021)
Now you have 2 tasks in the list.`

### `event (description) /at (period)` - Adds a Event task

Adds a Event task with the task name `(description)` and `(period)`.

Example of usage: 

`event team meeting /at Aug 6th 2-4pm`

Expected outcome:

`Got it. I've added this task:
[E][] team meeting(at:Aug 6th 2-4pm)
Now you have 3 tasks in the list.`

### `doafter (description) /after (afterWhen)` - Adds a DoAfter task

Adds a DoAftertask with the task name `(description)` and `(afterWhen)`.

Example of usage: 

`doafter borrow book /after return book`

Expected outcome:

`Got it. I've added this task:
[DA][] return book(DoAfter:return book)
Now you have 4 tasks in the list.`

### `list` - Lists all tasks

All the tasks in the list will be shown.

Example of usage: 

`list`

Expected outcome:

`1.[T][] read book
2.[D][] return book(by:Sep 30 2021)
3.[E][] team meeting(at:Aug 6th 2-4pm)
4.[DA][] return book(DoAfter:return book)`


### `done (task number)` - Marks task as done

Task with the specified `(task number)` will be marked as done.

Example of usage: 

`done 1`

Expected outcome:

`Nice! I've marked this task as done:
[T][X] read book`

### `delete (task number)` - Deletes a task

Task with the specified `(task number)` will be deleted.

Example of usage: 

`delete 1`

Expected outcome:

`Noted. I've removed this task:
[T][X] read book
Now you have 3 tasks in the list.`

### `find (keyword)` - Finds a task

Finds task whose names contain the given `(keywords)`.

Example of usage: 

`find book`

Expected outcome:

`Here are the matching tasks in your list:
[D][] return book(by:Sep 30 2021)
[DA][] return book(DoAfter:return book)`


