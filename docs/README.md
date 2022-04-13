# User Guide

## Features 

### Add task

Add a task which can be a simple to-do, a deadline or an event to the storage.

### Delete task

Delete any task from the storage.

### List tasks

Display all the tasks in the storage.

### Mark as done

Mark any completed task as done. Done tasks will be checked("X") in the list.

### Find task by keyword

Enter a keyword to search for a task by its description.

### Help

Enter "help" to get a list of commands that can be used.

## Usage

### `todo` - Adds a Todo Task

Adds a Todo Task to storage.

Example of usage: 

`todo read book`

Expected outcome:
Task added to storage.

### `deadline` - Adds a Deadline Task

Adds a Deadline Task to storage.

Example of usage:

`deadline quiz /by 2021-09-21 2359`

Expected outcome:

Task added to storage.

### `event` - Adds an Event Task

Adds an Event Task to storage.

Example of usage:

`event party /at 2021-09-21 2359`

Expected outcome:

Task added to storage.

### `done` - Mark a Task as done

Marks a Task as done.

Example of usage:

`done 2`

Expected outcome:

Task marked as done should be checked("X").

### `delete` - Delete a Task 

Delete a Task.

Example of usage:

`delete 2`

Expected outcome:

Task should be removed from storage.

### `find` - Finds Tasks by keyword

Find Tasks for which the description matches the keyword.

Example of usage:

`find read`

Expected outcome:

Tasks with description matching the keyword returned.

### `help` - Returns list of commands

Returns a list of commands.

Example of usage:

`help`

Expected outcome:

List of commands returned.

### `bye` - Exits the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

Exits the program.