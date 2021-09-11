# User Guide

Duke is a chatbot for managing tasks through a CLI.

## Getting Started
1. Download the JAR file from [here](https://github.com/jovyntls/ip/releases/tag/A-Release)
2. Use the `java -jar` command to run the JAR file
3. A duke_storage.txt file will be created in the same directory as the JAR file; this is used to store data locally.

## Features

### Task Management

Duke will help keep track of three types of tasks: Todos, Events and Deadlines.
You can mark each task as done or delete tasks.

### Search

Filter tasks containing a specific keyword to keep track of tasks more efficiently!

### Local Storage

Duke will save your tasks even after you close the app, so you can continue right where you left off.

## Usage

### `todo <title>` - Create a TODO

Create a new "todo" task that will be added to your task list.

Example of usage: `todo read book` 

Expected outcome: Add the todo "read book" to your task list.

### `event <title> /at <date>` - Create a scheduled Event

Create a new event that is scheduled on the specified date, given in YYYY-MM-DD format.

Example of usage: `event project meeting /at 2021-09-01`

Expected outcome: Adds an event "project meeting" that should occur on 1 September 2021.

### `deadline <title> /by <date>` - Create a deadline

Create a new deadline that is due on the specified date, given in YYYY-MM-DD format.

Example of usage: `deadline return book /by 2021-09-02`

Expected outcome: Adds an deadline "return book" that is due 2 September 2021.

### `list` - Lists all tasks

Lists all the tasks so far. 

### `find <keyword>` - Finds tasks containing a keyword

Returns the list of tasks containing the given keyword.

Example of usage: `find book`

Expected outcome: The lists of tasks containing the word "book"

### `done <index>` - Mark a task as done

Marks the task at the given index as done.

Example of usage: `mark 2`

Expected outcome: The second task in the list is marked as completed.

### `delete <index>` - Delete a task

Deletes the task at the given index

Example of usage: `delete 2`

Expected outcome: The second task in the list is deleted.

### `bye` - Saves all tasks

Saves all tasks into a local file so that the program can be safely exited.