# User Guide

**Botto** is a desktop task tracker application that frees your mind of having to remember things you need to do.

* [Quick start](#quick-start)
* [Features](#features)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Adding a task](#add-a-task)
    * [Todo: `todo`](#todo-todo)
    * [Deadline: `deadline`](#deadline-deadline)
    * [Event: `event`](#event-event)
  * [Marking a task as done: `done`](#marking-a-task-as-done-done)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Finding a task: `find`](#finding-a-task-find)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
  * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command summary](#command-summary)
    
    
## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest botto.jar from [here](https://github.com/ngchisern/ip/releases/tag/v1.0).
3. Double-click the file to start the app.
4. Type the command in the chat box and press Enter/Send to execute it.
5. Refer to the [Features](#features) below for details of each command.


## Features 

####
Notes about the command format:

* Words in `[]` are the parameters to be supplied by the user. <br>
  e.g. `todo [task]` is a parameter which can be used as `todo CS2103T iP` 


### Listing all tasks: `list`

Shows a list of all tasks stored in Botto.

Format: `list`

### Add a task

Adds a task to the bot. There are 3 types of task.

#### Todo: `todo`

Adds a todo to the bot.

Format: `todo [task]`

#### Deadline: `deadline`

Adds a deadline to the bot.

Format: `deadline [task] /by [d/M/yyyy H:mm a]`

Examples:
1. `deadline CS2103T iP submission /by 9/9/2021 9:00 am`
2. `deadline internship application /by 7/7/2021 11:59 pm`

#### Event: `event`

Adds an event to the bot.

Format: `event [task] /at [d/M/yyyy H:mm a]`

Examples:
1. `deadline CS2103T mid term /at 10/10/2021 10:00 am`
2. `deadline internship interview /at 8/8/2021 2:00 pm`

### Marking a task as done: `done`

Mark a specified task as done.

Format: `done [index]`

* Mark the task at the specified `index` as done.
* The index refers to the index number shown in the displayed task list.
* the index **must be a positive integer** 1,2,3 ...

### Deleting a task: `delete`

Deleting a specified task from Botto.

Format: `delete [index]`

* Deletes the task at the specified `index`.
* The index refers to the index number shown in the displayed task list.
* the index **must be a positive integer** 1,2,3 ...

### Finding a task: `find`

Finds tasks whose titles contain the keyword

Format: `find [keyword]`

* The search is case-insensitive. e.g. `apple` will match `APPLE`
* The search only targets at the tasks' titles.
* Only full words will be matched. e.g. `appl` will not match `apple`

### Exiting the program: `bye`

Exits the program

Format: `exit`

### Saving the data

Botto data are saved in the local computer automatically after any command that changes the data. 
There is no need to save manually.

## FAQ

Q: How do I transfer my data to another Computer? <br>
A: Install the app in the other computer and overwrite the empty data file
it creates with the file that contains the data of your previous botto data folder.

##Command Summary

Action | Format, Examples
-------- | -------------------
List | `list`
Todo | `todo [task]` <br> e.g. `todo CS2103T iP`
Deadline | `deadline [task] /by [d/M/yyyy H:mm a]` <br> e.g. `deadline CS2103T iP submission /by 9/9/2021 9:00 am`
Event | `event [task] /at [d/M/yyyy H:mm a]` <br> e.g. `deadline CS2103T mid term /at 10/10/2021 10:00 am`
Done | `done [index]` <br> e.g. `done 2`
Delete | `delete [index]` <br> e.g. `delete 1`
Find | `find [keyword]` <br> e.g. `find book`
Exit | `bye`

