# User Guide

## Features 

### list

View the current list of tasks

### todo

Add a new Todo task

### deadline

Add a new Deadline task

### event

Add a new Event task

### done

Mark a task as done

### find

Find all tasks matching a given search string

### edit

Edit task name

### delete

Delete a task

### bye

Exit the app

## Usage

### `list` - View the current list of tasks

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][X] join sports club
2.[D][] return book (by: Mar 3 2012)
3.[T][] run
```

### `todo` - Add a new Todo task

Example of usage:

`todo sleep`

Expected outcome:

```
Got it. I've added this task:
 [T][] sleep
Now you have 4 tasks in the list.
```

### `deadline` - Add a new Deadline task

Example of usage:

`deadline submit essay /by 2012-03-03`

* YYYY-MM-DD format

Expected outcome:

```
Got it. I've added this task:
 [D][] submit essay (by: Mar 3 2012)
Now you have 5 tasks in the list.
```

### `event` - Add a new Event task

Example of usage:

`event party /at 2012-03-02`

* YYYY-MM-DD format

Expected outcome:

```
Got it. I've added this task:
 [E][] party (at: Mar 2 2012)
Now you have 6 tasks in the list.
```

### `done` - Mark a task as done

Example of usage:

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] run
```

### `find` - Find all tasks matching a given search string

Example of usage:

`find sports`

Expected outcome:

```
Here are the matching tasks in your list:
 1.[T][X] join sports club
```

### `edit` - Edit task name

Example of usage:

`edit 3 cycle`

Expected outcome:

```
Noted. I've updated the task:
[T][X] cycle
```

### `delete` - Delete a task

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
[T][X] cycle
Now you have 5 tasks in the list.
```

### `bye` - Exit the app

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```