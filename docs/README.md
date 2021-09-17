# User Guide

Bob is a __desktop app for managing tasks, in the style of a chatbot__ 
## Features 

### Manage Tasks

Allows you to add, remove and edit tasks.

### See and search for Tasks

Bob allows you to see all your tasks at a glance, as well as search for tasks matching a given keyword

### Save to local storage

Bob saves your list of tasks to your local computer every time the list is updated, and will load the saved list on startup.  
There is no need to save manually.

## Usage

Notes about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user.   
E.g. in `done INDEX`, `INDEX` is a parameter which can be used as `done 3`.
* Items in square brackets are optional.  
E.g. `TASK_NUM [n/ NAME] [t/ TIME]` can be used as `3 n/ Read book` or `3 n/ Read book t/ Friday`

### `add` - Adds a Task

Format: `add TASK_TYPE DESCRIPTION`

Bob has 3 types of tasks:
* `ToDo`
* `Deadline`
* `Event`

These three task types have different keywords for the description, and will be explained below.

#### `todo` - Adds a ToDo task

Format: `add todo TASK_NAME`

Examples:
* `add todo Buy lunch`
* `add todo Read book`

#### `deadline` - Adds a Deadline task

Format: `add deadline TASK_NAME /by DEADLINE`

`DEADLINE` can be a string e.g. `today` or `next week`, and it could be a date in the `YYYY-MM-DD` format.

Examples:
* `add deadline Buy lunch /by 12pm`
* `add deadline Read book /by 2021-12-25`

#### `event` - Adds an Event task

Format: `add event TASK_NAME /at EVENT_DATE`

`EVENT_DATE` can be a string e.g. `today` or `next week`, and it could be a date in the `YYYY-MM-DD` format.

Examples:
* `add event Dance performance /at next Tuesday`
* `add event Project meeting /at 2021-09-13`

### `list` - Lists all Tasks

Shows a numbered list of all the Tasks saved in Bob.

Format: `list`

### `done` - Marks a Task as done

Marks the specified Task as done

Format: `done INDEX`

* Marks a Task at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `done 3` Marks the third task as done

### `remove` - Removes a Task

Removes the specified Task from Bob.

Format: `remove INDEX`

* Removes a Task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `remove 3` Removes the third task

### `update` - Updates a Task

Updates the name and/or time of a specified Task

Format: `update INDEX [n/ NAME] [t/ DATE]`

* Updates the task at the specified `INDEX`
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …
* At least one of the optional fields must be provided
* The fields can be in any order
* `ToDo`s do not have a `DATE` field, so only the `NAME` of a `ToDo` can be updated

Examples:
* `update 2 /n Bake cookies` Edits the name of the second task to `Bake cookies`
* `update 1 /t Tomorrow` Edits the time of the first task to `Tomorrow`
* `update 3 /t 2021-09-23 /n Lunch with friends` Edits the name of the third task to `Lunch with friends` and its date to `2021-09-23`

### `find` - Finds Tasks by name

Finds Tasks whose name contain a given keyword

Format: `find KEYWORD`

* The search is case-insensitive, e.g. `read` will match `Read`
* Only the name is searched
* Partial words will be matched, e.g. `shot` will match `screenshot`

Examples:
* `find lunch` returns `Buy lunch`
* `find book` returns `Read book` and `Return book`

### `clear` - Clears all tasks from Bob

Format: `clear`

### `restore` - Restores a cleared list of Tasks

Restores the list of tasks before the most recent `clear` command.  
If no `clear` command was given, it will restore the list of tasks loaded on startup.

Format: `restore`

### `bye` - Exits the program

Format: `bye`