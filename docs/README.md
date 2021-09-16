# User Guide
**WoofBot** is your very own task managing bot. Simply key in the tasks that you want to keep track off, and WoofBot will keep note of it for you. Fast, clean, and easy-to-use - what more could you ask for!
![WoofBot Ui](/docs/Ui.png.png)

## Features
Listed below are features available in WoofBot:
1. Add a task to the task list. The following 3 types of tasks are supported:
    * To-Do
    * Deadline
    * Event
2. Mark a task as done
3. Delete a task from the task list
4. View all tasks in task list
5. Find all tasks related to keyword(s)
6. Archive a task
7. View all tasks in archive
8. Restore a task from the archive back to the task list

All inputs are text-based and can be input via a Graphical User Interface (GUI).
All tasks that have been added or archived are saved and loaded automatically from a locally stored file!

## Usage

### `todo` - Add a To-Do task.

Adds a task with no deadline and date to the tasklist.

Format: `todo <name>`

Example of usage:

`todo study for exam`

Expected outcome:

Description of the outcome.

![Todo](/docs/Todo.png)

### `deadline` - Add a Deadline task.

Adds a task that has to be completed by a specified deadline to the tasklist.

Format: `deadline <name> /by <YYYY-MM-DD>`

Example of usage:

`deadline submit assignment /by 2021-09-15`

Expected outcome:

![Deadline](/docs/Deadline.png)

### `event` - Add an Event task.

Adds a task that is occuring on a specific date to the tasklist.

Format: `event <name> /at <YYYY-MM-DD>`

Example of usage:

`event release of exam results /at 2021-10-06`

Expected outcome:

![Event](/docs/Event.png)

### `list` - Lists all saved tasks.

Generates a list of all saved complete/incomplete tasks in the task list. ToDo, Deadline and Event tasks are prefixed with `[T]`, `[D]` and `[E]` respectively. Completed and incomplete tasks are marked with `[X]` and `[]` respectively.

Format: `list`

Example of usage:

`list`

Expected outcome:

![List Tasks](/docs/List.png)

### `done` - Marks the specified task as completed.

Marks the task at the specified task number as done.

Format: `done <task_number>`

Example of usage:

`done 1`

Expected outcome:

![Done](/docs/Done.png)

### `delete` - Deletes the specified task.

Permenantly delete the task at the specified task number from the task list.

Format: `delet <task_number>`

Example of usage:

`delete 1`

Expected outcome:

![Delete Task](/docs/Delete.png)

### `find` - Find all tasks with names that match the given keyword query.

All tasks that have names which contain the specified query of keyword(s) are returned.

Format: `find <keywords>`

Example of usage:

`find exa`

Expected outcome:

![Find Tasks](/docs/Find.png)

### `archive` - Archives all specified tasks.

Archives the task at the specified task number, by moving it from the task list to the archive.
Multiple task numbers can be input in one command, by separating each task number with a whitespace.

Format: `archive <task_numbers>`

Example of usage:

`archive 1`

Expected outcome:

![Archive Task](/docs/Archive.png)

### `listarchive` - Lists all tasks in the archive.

Generates a list of all saved complete/incomplete tasks in the archive. ToDo, Deadline and Event tasks are prefixed with `[T]`, `[D]` and `[E]` respectively. Completed and incomplete tasks are marked with `[X]` and `[]` respectively.

Format: `listarchive`

Example of usage:

`listarchive`

Expected outcome:

![List Archive](/docs/ArchiveList.png)

### `restore` - Restores all specified tasks.

Restores the task at the specified task number in the archive, by moving it from the archive, back to the task list.
Multiple task numbers can be input in one command, by separating each task number with a whitespace.

Format: `restore <task_numbers>`

Example of usage:

`restore 1`

Expected outcome:

![Restore Task](/docs/Restore.png)

### `bye` - Exit the application.

Closes the application.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

![Exit Application](/docs/Bye.png)
