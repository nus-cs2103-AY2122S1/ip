# User Guide

## Features 

### Create tasks

In Duke, there are namely 3 different types of tasks.
- Todo: represents tasks that do not have a timestamp
- Deadline: represents tasks that have to be completed by a certain deadline
- Event: represents events that occur at a specific date and time

### Mark a task as done

Once you have completed a task, you can mark it as completed.

### Delete a task

You can delete a task that you no longer want to track from the list.

### Show all tasks

You can see all your tasks at a glance.

### Search for a specific task by its name or date

You can search for tasks using their names or dates (if they have dates) as search filters.

### Load tasks from file

If you have tasks previously stored in your drive, you can load them into the application.

## Usage

### `load` - Loads tasks from a local file

Loads tasks from local file to the list.

Format: `load <file-path>`

### `list` - Displays all tasks

Displays all tasks you are currently tracking. If the date argument is added, only tasks on that date are displayed.

Format: `list [<date>]`

### `find` - Displays tasks with matching names

Displays tasks that matches the search string. If multiple search strings are provided, then tasks that match at least one search string are shown.

Format: `find <search-string> <search-string>...`

### `todo` - Creates a new Todo

Creates a new Todo and adds it to the current list.

Format: `todo <name>`

### `deadline` - Creates a new Deadline

Creates a new Deadline and adds it to the current list. The deadline must be in `YYYY-MM-dd HHMM` format.

Format: `deadline <name> /by <due-date>`

Example: `deadline return book /by 2021-09-31 2359`

### `event` - Creates a new Event

Creates a new Event and adds it to the current list. The deadline must be in `YYYY-MM-dd HHMM` format.

Format: `event <name> /at <event-timestamp>`

Example: `event book convention /at 2021-09-25 1300`

### `done` - Marks a task as completed

Marks a task as completed.

Format: `done <task-number>`

### `delete` - Deletes a task

Removes a task from the list.

Format: `delete <task-number>`

### `bye` - Closes the application

Exits the application, saving the task list to the storage file while doing so.

Format: `bye`
