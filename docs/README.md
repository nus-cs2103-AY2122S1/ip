# User Guide

JaredDeluxe is a desktop app for managing tasks such as todos, deadlines and events.

## Notes about command format
* Words in UPPER_CASE are parameters to be given by the user.

  e.g `done INDEX`, INDEX is a parameter which can be used as `done 2`.
* Items in square brackets are optional.

  e.g `DESCRIPTION /by [DATE] [TIME]` can be used as `Homework /by 2021-09-17` or `Homework /by 16:00`.


## Features

- Adding a
  - [todo](#todo)
  - [deadline](#deadline)
  - [event](#event)
- [List all tasks](#list) 
- [Mark a task as done](#done) 
- [Delete a task](#delete) 
- [Find a task](#find) 
- [Sort tasks](#sort) 
- [Exit](#bye) 

## Usage

### `todo`
Adds a todo task.

Format: `todo DESCRIPTION`

Example: `todo groceries`

Expected outcome:

```
Got it. I've added this task:
[T][] groceries
Now you have 1 tasks in the list.
```

### `deadline` 
Adds a deadline task.

Format: `deadline DESCRIPTION /by [DATE] [TIME]`
* DATE is specified in yyyy-mm-dd format
* TIME is specified in hh-mm format
* Must specify either DATE or TIME or both

Example: `deadline homework /by 2021-09-17 16:00`

Expected outcome:

```
Got it. I've added this task:
[D][] homework (by: 17 Sep 2021 16:00)
Now you have 2 tasks in the list.
```

### `event` 
Adds a event task.

Format: `event DESCRIPTION /at [DATE] [TIME]`
* DATE is specified in yyyy-mm-dd format
* TIME is specified in hh-mm format
* Must specify either DATE or TIME or both

Example: `event wedding dinner /at 2021-11-24 18:00`

Expected outcome:

```
Got it. I've added this task:
[E][] wedding dinner (at: 24 Nov 2021 18:00)
Now you have 3 tasks in the list.
```

### `list`
Lists all the tasks you have currently.

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][] groceries
2. [D][] homework (by: 17 Sep 2021 16:00)
3. [E][] wedding dinner (at: 24 Nov 2021 18:00)
```

### `done`
Marks the task corresponding to that index number as done.

Format: `done INDEX`

Example: `done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] groceries
```

### `delete`
Deletes the task corresponding to that index number as done.

Format: `delete INDEX`

Example: `done 3`

Expected outcome:

```
Noted. I've removed this task:
[E][] wedding dinner (at: 24 Nov 2021 18:00)
```

### `find`
Finds the task with the keyword entered.

Format: `find KEYWORD`

Example: `find wedding`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][] wedding dinner (at: 24 Nov 2021 18:00)
```

### `sort`
Sorts the task list in chronological order.

Format: `sort`

Expected outcome:

```
1. [D][] homework (by: 17 Sep 2021 16:00)
2. [E][] wedding dinner (at: 24 Nov 2021 18:00)
3. [T][] groceries
```

### `bye`
Exits JaredDeluxe.

Format: `bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```




