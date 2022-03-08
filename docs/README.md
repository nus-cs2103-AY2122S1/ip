# User Guide

Duke is a desktop app for **keeping track of tasks**. It is **optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). 

<hr>

## Features

### `todo` - Adding a todo

Adds a todo to the task list.

Format: `todo DESCRIPTION`

Example of usage: `todo borrow book`

Expected outcome:

```
Got it. I've added this task:
[T][ ] borrow book
Now you have 1 task in the list.
```

### `deadline` - Adding a deadline

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DATE`
- The date has to be in `dd/MM/yyyy` format.

Example of usage: `deadline return book /by 03/09/2021` 

Expected outcome:

```
Got it. I've added this task:
[D][ ] return book (by: 3 Sep 2021)
Now you have 2 tasks in the list.
```

### `event` - Adding an event

Adds an event to the task list.

Format: `event DESCRIPTION /at DATE`
- The date has to be in `dd/MM/yyyy` format.

Example of usage: `event project meeting /at 06/10/2021`

Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting (at: 6 Oct 2021)
Now you have 3 tasks in the list.
```

### `list` - Listing all tasks

Shows a list of all tasks stored in Duke.

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: 3 Sep 2021)
3.[E][ ] project meeting (at: 6 Oct 2021)
```

### `done` - Marking a task as done

Marks a specified task as done.

Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown when `list` is called.
- The index **must be a positive integer** and be no greater than the largest index number shown when `list` is called.

Example of usage: `done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] borrow book
```

### `delete` - Deleting a task

Deletes the specified task from the task list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown when `list` is called.
- The index **must be a positive integer** and be no greater than the largest index number shown when `list` is called.

Example of usage: `delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][X] borrow book
Now you have 2 tasks in the list.
```

### `find` - Locating tasks by description

Finds tasks with descriptions that contain the keyword.

Format: `find KEYWORD`
- The search is case-sensitive.
- Only the description is searched.

Example of usage: `find meeting`

Expected outcome:

```
Here are the matching tasks in your list:
1.[E][ ] project meeting (at: 6 Oct 2021)
```

### `remind` - Reminding tasks that are due

Reminds user of past and upcoming undone tasks to be done within the specified days.

Format: `remind DAYS_FROM_TODAY`
- Finds tasks that are to be done within the specified `DAYS_FROM_TODAY`.
- Tasks that have no date (i.e. `todo`) or have been done will be ignored.
- The `DAYS_FROM_TODAY` is an integer referring to the number of days from today. e.g `0` means today, whereas `15` means 15 days from today.
- The `DAYS_FROM_TODAY` **must be a non-negative integer**.

Example of usage: `remind 1` (Suppose today is `5 Oct 2021`)

Expected outcome:

- Note that **expired** (i.e. undone tasks that have dates before today) tasks are reminded as well.

```
Here are the tasks to be done before they are due:
1.[D][ ] return book (by: 3 Sep 2021)
2.[E][ ] project meeting (at: 6 Oct 2021)
```

### `bye` - Exiting the program

Exits the program.

Format: `bye`

### Saving the data

Duke data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duke data is saved as a text file `[JAR file location]/data/duke.txt`. Advanced users are welcome to update data directly by editing that data file.

The tasks stored in the data consist of either of the two following formats: 
1. `TASK_TYPE | IS_DONE | DESCRIPTION`
2. `TASK_TYPE | IS_DONE | DESCRIPTION | DATE`
- `TASK_TYPE` is `T` when the task is a todo, `D` when the task is a deadline and `E` when the task is an event.
- `IS_DONE` is `1` when a task is done; `0` otherwise.
- `DESCRIPTION` contains the task description.
- `DATE` is 

Example:
```
T | 0 | Study for Midterms
E | 0 | Concert | 26/09/2021
D | 1 | Finish Assignment #1 | 15/09/2021
```
The above stores a todo, event and deadline respectively to the text file. The todo follows the first format as it does not have a date. The todo and event are not done, which are indicated with a `IS_DONE` value of `0`; the deadline is done, which is indicated with a `1`. 

<hr>

## Command summary

| Action       | Format, Examples |
| ------------ | ---------------- |
| Add todo     | `todo DESCRIPTION` <br> e.g., `todo borrow book` |
| Add deadline | `deadline DESCRIPTION /by DATE` <br> e.g., `deadline return book /by 03/09/2021` |
| Add event    | `event DESCRIPTION /at DATE` <br> e.g., `event project meeting /at 06/10/2021` |
| List         | `list` |
| Done         | `done INDEX` <br> e.g., `done 1` |
| Delete       | `delete INDEX` <br> e.g., `delete 1` |
| Find         | `find KEYWORD` <br> e.g., `find meeting` |
| Remind       | `remind DAYS_FROM_TODAY` <br> e.g., `remind 1` |
