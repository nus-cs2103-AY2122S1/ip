# User Guide

## Description
Duke is a chat bot to organize your tasks in a tasklist.

## Features 

### Viewing help: `help`

Shows the list of commands and their usage.

Format: `help`

### Adding a task

Adds a task corresponding to each command to the task list.

Format:

`todo <description>`

`deadline <description> /by <dd/MM/yyyy>`

`event <description> /at <dd/MM/yyyy>`

### Listing all tasks: `list`

Shows the list of tasks currently in the task list.

Format: `list`

### Mark a task as completed

Mark a task in the task list as completed. 

`<index>` is the task identifier in the list.

Format: `done <index>`

### Delete a task

Delete a task from the task list. `<index>` is the task identifier in the list.

Format: `delete <index>`

### Get tasks happening on a certain date

Get all the tasks happening on a certain date.

Format: `get <dd/MM/yyyy>`

### Search a task by keyword

Find a task containing the given keyword.

Format: `find <keyword>`
* The search is case-sensitive
* If more than one keyword is given, the search only finds the task by the first keyword.
* Incomplete keywords will still be matched.
* All tasks in the task list that contains the keyword will be listed.

### Exit the program
Quit Duke program.

Format: `bye`

## Command summary
Action | Format | Examples
------ | ------ | --------
Add ToDo | `todo <description>` | `todo picnic`
Add Deadline | `deadline <description> /by <dd/MM/yyyy>` | `deadline submission /by 17/09/2021`
Add Event | `event <description> /at <dd/MM/yyyy>` | `event cca /at 18/09/2021`
Mark as completed | `done <index>` | `done 1`
Delete | `delete <index>` | `delete 1`
List | `list` | NIL
Get | `get <dd/MM/yyyy>` | `get 17/09/2021`
Find | `find <keyword>` | `find submission`
Exit | `bye` | NIL
Help | `help` | NIL
