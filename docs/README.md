# Cygnus User Guide

Cygnus is a __chatbot__ for __task management__. It supports creation of three types of 
tasks: _deadlines_, _events_, and _todos_. These tasks can be marked as done or 
deleted. The list of tasks is maintained locally and is __saved between uses of
Cygnus__. This list can be filtered by keyword or to show upcoming deadlines.

## Quick Start

1. Ensure that you have Java `11` installed on your computer.
1. Download the latest `Cygnus.jar` from [here](https://github.com/jyrw/ip).
1. Copy the file to the folder you want to use as the home folder.
1. Double-click the file to start the chatbot. The GUI should appear within a few seconds.

## Commands

### Summary

Action | Format
-------|-------
__Create deadline__ | `deadline DEADLINE_INFO /by DDMMYY`
__Create event__ | `event EVENT_INFO /at DDMMYY HHmm`
__Create todo__ | `todo TODO_INFO`
__List all tasks__ | `list`
__Set task done__ | `done TASK_NUMBER`
__Delete task__ | `delete TASK_NUMBER`
__List matching tasks__ | `find KEYWORD`
__List upcoming deadlines__ | `upcoming`
__Refer user to user guide__ | `help`

### Creating a deadline: `deadline`

Creates a deadline task with the specified description and due date.

Format: `deadline DEADLINE_INFO /by DDMMYY`

Usage example: `deadline iP /by 150921`

Expected outcome - an incomplete deadline is created:

```
Got it. I've added this task:
[D][ ] iP | by: Wed 15 Sep 2021
```

### Creating an event: `event`

Creates an event with the specified description and event date.

Format: `event EVENT_INFO /at DDMMYY HHmm`

Usage example: `event Dinner /at 260921 1830`

Expected outcome - an incomplete event is created:

```
Got it. I've added this task:
[E][ ] Dinner | at: Sun 26 Sep 2021 06:30PM
```

### Creating a todo: `todo`

Creates a todo with the specified description.

Format: `todo TODO_INFO`

Usage example: `todo Revise OOP`

Expected outcome - an incomplete todo is created: 
```
Got it. I've added this task:
[T][ ] Revise OOP
```

### Listing all tasks: `list`

Lists all tasks.

Format: `list`

Usage example: `list`

Expected outcome - all tasks are displayed: 

```
Here are the tasks in your list:
1. [D][ ] iP | by: Wed 15 Sep 2021
2. [D][ ] CS2100 Tutorial | by: Thu 16 Sep 2021
3. [D][ ] CS2100 Lab | by: Thu 16 Sep 2021
4. [E][ ] Dinner | at: Sun 26 Sep 2021 06:30PM
5. [T][ ] Revise OOP
```

### Setting a task as done: `done`

Sets a task as done.

Format: `done TASK_NUMBER`

Usage example: `done 1`

Expected outcome - the specified task is set as done: 

```
Nice! I've marked this task as done:
 [D][X] iP | by: Wed 15 Sep 2021
```

### Deleting a task: `delete`

Deletes a task.

Format: `delete TASK_NUMBER`

Usage example: `delete 2`

Expected outcome - the specified task is deleted: 

```
Noted. I've deleted this task: 
 [D][ ] CS2100 Tutorial | by: Thu 16 Sep 2021
```

### List all tasks matching a keyword: `find`

Lists all tasks containing the given keyword.

Format: `find KEYWORD`

Usage example: `find CS2100`

Expected outcome - the matching tasks are displayed: 

```
Here are the tasks in your list:
1. [D][ ] CS2100 Lab | by: Thu 16 Sep 2021
```

### List all upcoming deadlines: `upcoming`

Lists all incomplete deadlines due within 1 week.

Format: `upcoming`

Usage example: `upcoming`

Expected outcome - all upcoming incomplete deadlines are displayed:
```
Here are the tasks in your list:
1. [D][ ] CS2100 Lab | by: Thu 16 Sep 2021
```

### Refer user to user guide: `help`

Refers user to this page.

Format: `help`

Usage example: `help`

Expected outcome - a link to this page is displayed:
```
Refer to https://jyrw.github.io/ip/ for a full list of commands.
```