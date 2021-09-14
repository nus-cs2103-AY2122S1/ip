# User Guide

Bruh is a **chatbot assistant for task management, optimized for use via a Command Line Interface (CLI)**. Bruh makes keeping track of different tasks a breeze, with easy-to-read commands and a user-friendly Graphical User Interface (GUI).

## Features 

- Creating & deleting of tasks
- Marking of tasks as complete
- Support for various task types (deadlines, events etc.)
- Searching of tasks by name & date
- User-friendly GUI format

## Usage

**Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Do Homework`.

- Items in square brackets are optional.<br>
  e.g `list [/date DATETIME]` can be used as `list` or as `list /date tomorrow`.

- If a date & time is to be specified (i.e. whenever `DATETIME` is specified as a parameter), it must be specified according to one the following formats:

  - `DD-MM-YYYY HHMM`
  - `DD/MM/YYYY HHMM`

  Otherwise, it is treated as a plaintext string.



### Adding a to-do task: `todo`

Adds a to-do item to your task list.

Format: `todo DESCRIPTION`

Example:

- `todo Get milk`

### Adding a deadline: `deadline`

Adds a task with a specified deadline to your task list.

Format: `deadline DESCRIPTION /by DATETIME`

Examples:

- `deadline Finish homework /by 25/12/2021 1200`
- `deadline Feed fish /by tomorrow`

### Adding an event: `event`

Adds a event occurring at a specified date & time to your task list.

Format: `event DESCRIPTION /at DATETIME`

Example:

- `event Black Eyed Peas concert /at Tuesday`

### Adding a "do-after" task: `doafter`

Adds a task to be done after a specified date & time.

Format: `doafter DESCRIPTION /after DATETIME`

Example:

- `doafter Procrastinate /after Tomorrow`

### Delete a task: `delete`

Deletes a task in your task list.

Format: `delete INDEX`

- Deletes the task at the specified `INDEX` . The index refers to the index number shown in the task list. The index **must be a positive integer**.

Example:

- `delete 2` Deletes the 2nd task in the list.

### Mark a task as complete: `done`

Marks a task in your task list as complete.

Format: `done INDEX`

- Marks the task at the specified `INDEX` as complete. The index refers to the index number shown in the task list. The index **must be a positive integer**.
- When viewing the task list, the corresponding task is now marked with a `X` to indicate its completed status.

Example:

- `done 3`  Marks the 3rd task in the list as complete.

### Viewing your tasks: `list`

Shows a list of all your tasks & their respective statuses.

Format: `list​ [/date DATETIME]`

Example:

- `list /date today` Lists all your tasks that have their date & time specified as "today".

### Finding tasks: `find`

Searches for tasks with descriptions matching the specified search string.

Format: `find SEARCHSTRING`

Example:

- `find homework` Lists all your tasks with descriptions containing "homework".

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Bruh saves your task list to the hard disk automatically after every command. There is no need to save manually.

