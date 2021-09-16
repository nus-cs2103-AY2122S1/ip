# User Guide

Tabby is a dsektop app for managing your task list. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

---

## Features 



### Viewing commands: `help`

Shows a list of available commands.

![image](https://user-images.githubusercontent.com/35279431/133550167-0ec47c24-cd50-45d0-9f1d-0ad33e216b40.png)

Format: `help`

<br />

### Viewing user documentation: `:help`

Opens a window with user documentation.

Format: `:help`

<br />

### Adding a todo task: `todo`

Adds a todo to the task list.

Format: `todo <TASK_DESCRIPTION>`

Example: `todo read book`

Output: `[T][ ] read book`

<br />

### Adding an event task: `event`

Adds an event task, which occurs at some time, to the task list.

Format: `event <TASK_DESCRIPTION> /at <TIME>`

Example: `event project meeting /at 2pm-4pm`

Output: `[E][ ] project meeting (at: 2pm-4pm)`

<br />

### Adding a deadline task: `deadline`

Adds a task with a deadline to do by some date and time, to the task list.

Format: `deadline <TASK_DESCRIPTION> /by <DATE_TIME>`

Note: `<DATE_TIME>` has to be in the format `dd-MM-YYYY HH:mm`

Example: `deadline math assignment /by 05-09-2021 23:00`

Output: `[D][ ] math assignment (by: Sep 05 2021, 11:00pm)`

<br />

### Showing task list: `list`

Shows all tasks in the task list.

Example: `list`

Output:
```
Your task list:
1.[T][X] read book
2.[E][ ] project meeting (at: 2pm-4pm)
3.[D][ ] math assignment (by: Sep 05 2021, 11:00pm)
```

<br />


### Marking a task as done: `done`

Marks a task with given task number as done.

Format: `done <TASK_NUMBER>`

Example: `done 2`

Output: `[T][X] read book`. The 2nd task is marked as done.

<br />

### Deleting a task: `delete`

Deletes a task with given task number from the task list.

Format: `delete <TASK_NUMBER>`

Example: `delete 3`. The 3rd task is deleted from the list.

<br />

### Finding a task: `find`

Finds tasks that match a keyword.

Format: `find <KEY_WORD>`

Example: `find book`

Output:
```
I found these matching tasks in your list for 'book':
1.[T][X] read book
2.[D][ ] return book (by: Sep 08 2021, 03:00 pm)
```

<br />

### Exiting the program: `bye`

Exits this program session.

Format: `bye`

<br />

---
## Saving the data
<br />

Tabby saves data in the hard disk automatically after any command that changes the data. There is no need to save manually.

---
## Command Summary

Action | Format, Examples
--------|------------------
**List** | `list`
**Add todo** | `todo <TASK>` <br> e.g., `todo read book`
**Add event** | `event <TASK> /at <TIME>` <br> e.g., `event project meeting /at 2pm-4pm`
**Add deadline** | `deadline <TASK> /by <DATE_TIME>` <br> e.g., `deadline math assignment /by 05-09-2021 23:00 `
**Done** | `done <INDEX>` <br> e.g., `done 2`
**Delete** | `delete <INDEX>`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**View commands** | `help`
**View user doc** | `:help`

