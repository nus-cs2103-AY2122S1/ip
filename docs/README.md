# User Guide
Duke is a **desktop app for managing tasks, optimized for use via a Command Line 
Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). For fast typists, Duke
gets your task management done faster than traditional GUI apps.

## Features
*Words in `UPPER_CASE` are the parameters to be supplied by the user.*

### Listing all tasks: `list`

Shows a list of all tasks currently stored.

Format: `list`

### Adding a To-Do task: `todo`

Adds a To-Do task to be stored.

Format: `todo DESCRIPTION`

Examples:
* `todo clean the garden`
* `todo wash dishes`

### Adding a Deadline task: `deadline`

Adds a Deadline task to be stored. Deadlines are tasks that need to be done before a 
specific date.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Examples:
* `deadline mow the lawn /by 2019-10-15`
* `deadline math homework /by 2021-02-23`

### Adding an Event task: `event`

Adds an Event task to be stored. Events are tasks that take place on a specific date.

Format: `event DESCRIPTION /at YYYY-MM-DD`

Examples:
* `event family gathering /at 2021-09-01`
* `event wedding /at 2023-12-09`

### Marking a task as done: `done`

Marks a task as done.

Format: `done INDEX`
* Marks the task at the specified INDEX as done. 
* The index refers to the index number shown
in the displayed task list. 
* The index must be a **positive integer** 1, 2, 3, ...

Example:
* `list` followed by `done 3` marks the 3rd task in the task list as done.
  
### Locating tasks by description: `find`

Finds all tasks which has descriptions containing the given keyword.

Format: `find KEYWORD`
* Only the description of the task is searched.
* Partial words will also be matched e.g. `find spor` will match `sports`

### Deleting a task: `delete`

Deletes the specified task.

Format: `delete INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number shown
  in the displayed task list.
* The index must be a **positive integer** 1, 2, 3, ...

Example:
* `list` followed by `delete 2` deletes the 2nd task in the task list.

### Undoing a command: `undo`

Undo the most recent command.

Format: `undo`
* If the most recent command is `list`, `find`, `bye`, or `undo`, this command will have
**no effect**.
  
### Exiting the program: `bye`

Exits the Duke program.

Format: `bye`

### Saving the data

Duke data are saved in the hard disk automatically after any command that modifies the 
data. There is no need to save manually.

### Editing the data file

Duke data are saved as a TXT file `[JAR file location]/data/duke.txt`. Advanced users are
welcome to update directly by editing that data file.


## Command summary

**Action** | **Format, Examples**
---------- | --------------------
**Add Deadline** | `deadline DESCRIPTION /by YYYY-MM-DD`  e.g., `deadline mow the lawn /by 2019-10-15`
**Add Event** | `event DESCRIPTION /at YYYY-MM-DD` e.g., `event wedding /at 2023-12-09`
**Add To-Do** | `todo DESCRIPTION` e.g., `todo homework`
**Delete** | `delete INDEX` e.g., `delete 2`
**Done** | `done INDEX` e.g., `done 3`
**Exit** | `bye`
**Find** | `find` e.g., `find book`
**List** | `list`
**Undo** | `undo`

