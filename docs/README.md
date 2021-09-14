# DukeSupreme User Guide

## Features 

### Track all of your Tasks

Choose from four different tasks to create and keep track of.

* A Todo task is simply a Task that can be checked off.

* An Event is a Task to be completed on a certain date.

* A Deadline is a Task to be completed before a given date.

* A DoWithinPeriod is a Task that should be completed within a given time period.

### Automatic saving of your Task list to drive

All tasks with their dates and completion status are automatically recorded and updated onto a save file on the disk!

## Usage

### `bye` - Exits the program

Immediately exits the program.

Example: `bye`

### `list` - Lists all logged tasks

Displays a list of all of the tasks that have been logged, their completion status and due dates/deadlines (if applicable).

Example of usage: `list`

Example outcome:

```
Here are the tasks in your list:
1. [T][] Read book
2. [D][] Complete IP (by: Sep 15 2021)
```

### `done` - Sets a task to DONE status

Sets a task that is specified by index to be completed.

Format: done INDEX

Example of usage: `done 2`

Example outcome:

```
Nice! I've marked this task as done:
  [D][X] Complete IP (by: Sep 15 2021)
```

### `delete` - Deletes a task from the task list

Deletes a task that is specified by index from the list of tasks.

Format: delete INDEX

Example of usage: `delete 2`

Example outcome:

```
Noted. I've removed this task:
  [D][X] Complete IP (by: Sep 15 2021)
Now you have 1 tasks in the list.
```

### `todo` - Creates a new Todo Task

Creates a Task that is to be completed. Has no due date/deadline, only completion status.

Format: todo DESCRIPTION

Example of usage: `todo Read a book`

Example outcome:

```
Got it. I've added this task:
  [T][] Read a book
Now you have 2 tasks in the list.
```

### `deadline` - Creates a new Task with a deadline

Creates a Task that is to be completed by a specified deadline.

Format: deadline DESCRIPTION /by YEAR-MONTH-DAY

Example of usage: `deadline Read a book /by 2021-09-14`

Example outcome:

```
Got it. I've added this task:
  [D][] Read a book (by: Sep 14 2021)
Now you have 3 tasks in the list.
```

### `event` - Creates a new Task with a specific date

Creates a Task that will happen on the specified date.

Format: event DESCRIPTION /at YEAR-MONTH-DAY

Example of usage: `event Attend book event /at 2021-09-14`

Example outcome:

```
Got it. I've added this task:
  [E][] Attend book event (at: Sep 14 2021)
Now you have 4 tasks in the list.
```

### `dowithinperiod` - Creates a new Task that should be completed within a time period

Creates a Task that as a start and end date, specifying a time period in which the task should be completed.

Format: dowithinperiod DESCRIPTION /between YEAR-MONTH-DAY /and YEAR-MONTH-DAY

Example of usage: `dowithinperiod Complete CS2103T IP /between 2021-09-10 /and 2021-09-15`

Example outcome:

```
Got it. I've added this task:
  [P][] Complete CS2103T IP (between: Sep 10 2021 and Sep 15 2021)
Now you have 5 tasks in the list.
```

### `date` - Filters and returns tasks that are due on the given date

Displays a list of only tasks that are due on the given date.

Format: date YEAR-MONTH-DAY

Example of usage: `date 2021-09-14`

Example outcome:

```
Here are the tasks in your list that are due on Sep 14 2021:
1. [D][] Read a book (by: Sep 14 2021)
2. [E][] Attend book event (at: Sep 14 2021)
```

### `find` - Finds and returns all tasks that have the given string in its description

Displays a list of only tasks whose descriptions cotnain the string given by the user.

Format: find KEYWORD

Example of usage: `find book`

Example outcome:

```
Here are the tasks in your list:
1. [T][] Read a book
2. [D][] Read a book (by: Sep 14 2021)
3. [E][] Attend a book event (at: Sep 14 2021)
```
