# Duchess User Guide
Duchess is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duchess can manage your tasks faster than traditional GUI apps. 

* Table of Contents
{:toc}

## Features 

### Feature - Task Managing

Create 3 different types of tasks to add, mark as done, or delete:
1. Todos
2. Deadlines
3. Events

### Feature - Task Retrieval

Filter through your tasks in numerous ways:
- Tasks which contain a specific keyword
- Tasks that are due before or after a specific date and time

## Usage
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Task creation commands like 'todo' and 'deadline' cannot be empty.<br>
* Duplicate tasks are not allowed.<br>
  
</div>

### `todo` - Adding a Todo

Adds a todo to your task list.

Format: `todo TASK`
* Adds a todo with the description 'TASK' to your task list.

### `deadline` - Adding a Deadline

Adds a deadline to your task list.

Format: `deadline TASK /by DEADLINE`
* Adds a deadline with the description 'TASK' and time 'DEADLINE' to your task list.

Example:
* `deadline assignment /by 15/9/2021 5pm`

### `event` - Adding an Event

Adds an Event to your task list.

Format: `event TASK /at TIME_PERIOD`
* Adds an event with the description 'TASK' and period 'TIME_PERIOD' to your task list.

Example:
* `event exam /at 15/9/2021 5pm-7pm`

### `list` - Listing all tasks

Lists all tasks in your task list.

Format: `list`

### `done` - Marking a task as completed

Marks a specified task as completed in your task list.

Format: `done INDEX`

* Marks the task at the specified `INDEX` as done with a [X].
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

### `delete` - Removing a task from the task list

Removes a specified task from your task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

### `find` - Locating specific tasks by a keyword

Finds tasks whose descriptions contain of the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g `homework` will not match `HOMEWORK`
* Partial words will be matched e.g. `h` will match `homework`

Example:
* `find cs2103` may return `cs2103 todo` and `cs2103 deadline`, but never `cs2105 homework`


### `tasks` - Filtering specific tasks due before or after a certain time

Finds tasks due before or after the given time.

Format: `tasks /BEFORE_OR_AFTER TIME`

* The search is case-sensitive. e.g `homework` will not match `HOMEWORK`
* Partial words will be matched e.g. `h` will match `homework`

Example:
* `tasks /before 15/9/2021 8pm` returns tasks due before 15 September 2021 20:00
* `tasks /after 20/9/2021 1am` returns tasks due after 20 September 2021 01:00

<div markdown="span" class="alert alert-primary"> 💡 Tip:
  
You can substitute TIME with `today`. e.g `tasks /after today` will return tasks due after the current date and time.
</div>

### `bye` - Exiting the program

Exits the program.

Format: `bye`

### Saving the data

Duchess data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Duchess data is saved as a txt file `[JAR file location]/data/duchess.txt`. Advanced users are welcome to update data directly by editing that data file.

## Command summary

Action | Format, Examples
--------|------------------
**todo** | `todo TASK` <br> e.g., `todo assignment 1`
**deadline** | `deadline TASK /by DEADLINE` <br> e.g.,`deadline assignment /by 15/9/2021 5pm`
**event** |  `event TASK /at TIME_PERIOD` <br> e.g.,`event exam /at 15/9/2021 5pm-7pm`
**list** | `list`
**done** | `done INDEX`<br> e.g., `done 1`
**delete** | `delete INDEX`<br> e.g., `delete 2`
**find** | `find KEYWORD`<br> e.g., `find cs2103`
**tasks** | `tasks /BEFORE_OR_AFTER TIME`<br> e.g., `tasks /after 15/9/2021 8pm`

