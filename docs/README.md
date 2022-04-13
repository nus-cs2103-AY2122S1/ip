# Duck User Guide

Duck is a desktop app for managing tasks. It is optimized for use via a Command Line Interface (CLI) while still 
having the benefits of a Graphical User Interface (GUI).

- [Quick start](#quick-start)
- [Features](#features)
    - [Adding a todo: `todo`](#adding-a-todo-todo)
    - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding a event: `event`](#adding-a-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking task as done: `done`](#marking-task-as-done-done)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Clearing all tasks: `clear`](#clearing-all-tasks-clear)
    - [Finding a task: `find`](#finding-a-task-find)
    - [Reminders for tasks: `remind`](#reminders-for-tasks-remind)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duck.jar` from [here](https://github.com/ryanpeh/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

1. Refer to the [Features](#features) below for details on how to use the app.

--------------------------------------------------------------------------------------------------------------------

## Features

### Adding a todo: `todo`

Adds a todo to the task list.

Format: `todo DESCRIPTION`

### Adding a deadline: `deadline`

Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by dd mmm yyyy`

### Adding a event: `event`

Adds an event to the task list.

Format: `event DESCRIPTION /at dd mmm yyyy hh:mm`

### Listing all tasks: `list`

Lists all tasks in the task list.

Format: `list`

### Marking task as done: `done`

Marks the specified task in the task list as done.

Format: `done INDEX`

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

### Clearing all tasks: `clear`

Clears all tasks in the task list.

Format: `clear`

### Finding a task: `find`

Finds a task by searching for a keyword.

Format: `find KEYWORD`

### Reminders for tasks: `remind`

Reminds the user on upcoming tasks.

Format: `remind`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

Task list data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/tasks.txt`. Advanced users are welcome to update data directly by editing that data file.

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo DESCRIPTION` <br> e.g., `todo CS2103T Project`
**Deadline** | `deadline DESCRIPTION /by dd-mm-yyyy` <br> e.g., `deadline CS2103T Project /by 17 09 2021`
**Event** | `event DESCRIPTION /at dd-mm-yyyy hh:mm` <br> e.g., `event CS2103T Lecture /at 17 09 2021 16:00`
**List** | `list`
**Done** | `done INDEX` <br> e.g., `done 3`
**Delete** | `delete INDEX` <br> e.g., `delete 4`
**Find** | `find KEYWORD` <br> e.g., `find CS2103T`
**Remind** | `remind`
**Exit** | `exit`