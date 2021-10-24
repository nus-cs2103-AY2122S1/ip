# User Guide

Duke is a GUI application to help you **easily** manage your tasks!

Sample Screenshot of Duke:

<img src="https://raw.github.com/JunWei3112/ip/master/docs/Ui.png" width="300">

## Quick Start

1. Download `duke.jar` from [here](https://github.com/JunWei3112/ip/releases/tag/v0.2)!
2. Move `duke.jar` to the folder you want to use as the home folder for Duke.
3. Open a command window in that folder.
4. Run the command `java -jar duke.jar` to launch the app.
5. Go ahead and start adding your tasks! :smile:

## Features 

### Add and Delete Tasks

Users can add and delete tasks from the task list.

### Support for Different Task Types

Duke can handle these different task types:
- `Deadline` - A task that needs to be done before a specific date
- `Event` - A task that is held on a specific date
- `Todo` - A task that does not have a specified time frame

### Mark a Task as Done

After completing a task, users can mark that completed task.

### List Tasks

Users are able to view the current task list.

### Find Tasks

Users are able to search for tasks in the task list using a keyword.

### Tag a Task

Users are able to tag a specific task to allow for categorization of tasks in the task list.

### Saving the Data

The data are automatically saved in the hard disk after any command that changes the data has been executed.
There is no need for users to save manually. The data are saved as a TXT file `[JAR file location]/data/dukeFile.txt`.

## Usage

### `list` - Listing all current tasks

Displays the current list of tasks.

Format: `list`

### `deadline` - Adding a Deadline task

Adds a Deadline task to the current task list.

Format: `deadline TASK_DESCRIPTION /by DATE`
- The date must be specified in this format: `YYYY-MM-DD`

Examples of usage:
- `deadline return book /by 2021-09-18`
- `deadline write argumentative essay /by 2021-09-14`

### `event` - Adding an Event task

Adds an Event task to the current task list.

Format: `event TASK_DESCRIPTION /at DATE`
- The date must be specified in this format: `YYYY-MM-DD`

Examples of usage:
- `event lunch with friends /at 2021-09-20`
- `event CS2101 team meeting /at 2021-09-17`

### `todo` - Adding a Todo task

Adds a Todo task to the current task list.

Format: `todo TASK_DESCRIPTION`

Examples of usage:
- `todo buy bread`
- `todo prepare notes for upcoming meeting`

### `delete` - Deleting a task

Deletes the specified task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- The index refers to the index number shown in the displayed task list.
- The index must be a **positive integer** 1, 2, 3...

Example of usage:
- `delete 2` deletes the 2nd task in the task list.

### `done` - Marking a task as done

Marks the specified task as done.

Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The index refers to the index number shown in the displayed task list.
- The index must be a **positive integer** 1, 2, 3...

Example of usage:
- `done 3` marks the 3rd task in the task list as completed.

### `find` - Searching for certain tasks

Displays the tasks that contain the search keyword.

Format: `find KEYWORD`
- Tasks that only matches the keyword partially will still be shown

Example of usage:

<img src="https://raw.github.com/JunWei3112/ip/master/docs/FindResult.png" width="250">

### `tag` - Tagging a task

Tags the specified task.

Format: `tag INDEX TAG_NAME`
- Tags the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a **positive integer** 1, 2, 3...
- The tag name should only consist of one word, and cannot contain spaces.

Example of usage:
- `tag 1 study` tags the 1st task in the task list as `#study`

### `bye` - Exiting the program

Exits the program.

Format: `bye`