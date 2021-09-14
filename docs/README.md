# User Guide

## Features 

### Adding a Todo: `todo`

Adds a Todo task to the list.

Format: `todo DESCRIPTOR` or `td DESCRIPTOR`

Example:
* `todo read a book`
* `td study math`

### Adding an Event: `event`

Adds an Event task to the list.

Format: `event DESCRIPTOR /at TIME` or `ev DESCRIPTOR /at TIME`
* `TIME` does not require specific format.

Example:
* `event meet Amy /at 12th Sept`
* `ev study group /at 6pm`

### Adding a Deadline: `deadline`

Adds a Deadline task to the list.

Format: `deadline DESCRIPTOR /by DATETIME` or `dl DESCRIPTOR /by DATETIME`
* `DATETIME` requires format of `dd/MM/yyyy HHmm`

Example:
* `deadline bake cake /by 07/10/2020 0700`
* `dl English assignment /by 02/09/2023 2359`

### Listing all tasks: `list`

Shows all tasks currently recorded.

Format: `list` or `ls`

### Marking a task as done: `done`

Marks a task in the list as done.

Format: `done INDEX` or `d INDEX`
* Deletes the person at the specified `INDEX`
* The index refers to the index number displayed when calling `list`
* The index **must be a positive integer** 1, 2, 3, ... 

Example:
* `done 3` or `d 3` marks the 3rd task in the list as done.

### Finding a task by description: `find`

Finds tasks that contain the keyword in their description.

Format: `find KEYWORD` or `f KEYWORD`
* Exactly 1 `KEYWORD` should be provided.
* The search is case-insensitive.
* Only the description is searched.

Example:
* `find read` or `f read` will return tasks containing keyword `read`

### Deleting a task: `delete`

Deletes a task from the list.

Format: `delete INDEX` or `rm INDEX`
* Deletes the person at the specified `INDEX`
* The index refers to the index number displayed when calling `list`
* The index **must be a positive integer** 1, 2, 3, ...

Example:
* `delete 3` or `rm 3` marks the 3rd task in the list as done.

### Delete all tasks: `clear`

Shows all tasks currently recorded.

Format: `clear` or `clr`

### Exiting the program: `bye`

Closes the program.

Format: `bye` or `quit`

### Saving and loading data

Duke saves all data to hard disk automatically after any command.
Data is loaded from hard disk automatically upon program activation.

## Command Summary

Action | Format
------------ | -------------
Add Todo | `todo DESCRIPTION` or `td DESCRIPTION`
Add Deadline | `deadline DESCRIPTOR /by DATETIME` or `dl DESCRIPTOR /by DATETIME`
Add Event | `event DESCRIPTOR /by DATE` or `ev DESCRIPTOR /by DATE`
Mark as done | `done INDEX` or `d INDEX`
Delete | `delete INDEX` or `rm INDEX`
Clear | `clear` or `clr`
Find | `find KEYWORD` or `f KEYWORD`
List | `list` or `ls`
Exit | `bye` or `quit`