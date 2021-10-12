# User Guide for _DukeyJukey_

## User Guide

_DukeyJukey_ is a chat-bot style task organizer that allows users to keep 
track of tasks that needs to be completed. It is optimized for use
via a Command Line Interface (CLI) while also having the benefits
of a Graphical User Interface (GUI).

## Features 

### Adding Tasks to the list

_DukeyJukey_ supports 3 different types of tasks, namely:
* **To-do Tasks**: Tasks without any deadlines or dates.
* **Deadlines**: Tasks with deadlines.
* **Events**: Tasks with a date for when it will occur.

#### Adding a To-Do task `t`

Adds a to-do task to the list.

Format: `t NAME`

Example: `t homework`

#### Adding a Deadline task `d`

Adds a deadline task to the list.

Format: `d NAME /by DATE`

* Date should be entered in YYYY-MM-DD format.

Example: `d assignment /by 2020-12-12`

#### Adding an Event task `e`

Adds an event task to the list.

Format: `e NAME /at DATE`

* Date should be entered in YYYY-MM-DD format.

Example: `e exam /at 2021-11-11`

### Viewing the task list `l`

Displays the list of tasks.

Sample response:
```
1. [T][] homework
2. [D][] assignment (by: 12 December 2020)
3. [E][] exam (at: 11 November 2021)
```

### Deleting a task `del` or `rem`

Deletes the task with the specified index from the list.

Format: `del INDEX` or `rem INDEX`

* You can use `l` to refer to the list to find the index of the task you want to delete.

Example: 

In the following list, inputting `rem 1` will delete the task `1. [T][] homework`.
```
1. [T][] homework
2. [D][] assignment (by: 12 December 2020)
3. [E][] exam (at: 11 November 2021)
```

### Marking a task as done `do`

Marks the task with the specified index as done. In the list, `[X]` denotes that the
task is **done**. `[]` denotes that the task is **not done**.

Format: `do INDEX`

* You can use `l` to refer to the list to find the index of the task you want to delete.

Example:

For the following list,
```
1. [T][] homework
2. [D][] assignment (by: 12 December 2020)
3. [E][] exam (at: 11 November 2021)
```
Inputting `do 1` will mark `1. [T][] homework` as done, changing the list to:
```
1. [T][X] homework
2. [D][] assignment (by: 12 December 2020)
3. [E][] exam (at: 11 November 2021)
```

### Searching the list `f` or `s`

Searches the list for tasks containing the search query and lists the results.

Format: `f SEARCH_QUERY` or `s SEARCH_QUERY`

* The search results will be listed with the same indexes they have in the list.

Example:

For the following list,
```
1. [T][] homework
2. [D][] assignment (by: 12 December 2020)
3. [E][] exam (at: 11 November 2021)
```
Inputting `f exam` will display the following:
```
Here are the search results for "exam":
3. [E][] exam (at: 11 November 2021)
```

### Wiping the task list `WIPE`

Deletes all tasks inside the list.

Expected response:
```
BAM! The list has been wiped.
```

### Saying Goodbye `bye`

Exits the program.