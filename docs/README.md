# User Guide

Duke is a personal bot assistant to help you keep track of all the things you need to do! 

* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)

## Quick Start

1. Install java 11
2. Download the latest version [here](https://github.com/LeopardMerkava/ip/releases)
3. Launch by double clicking it!

## Features 

### The task list

Duke supports 3 types of task:
* `todo` are general tasks with no associated time
* `event` are tasks with a timeframe for you to do the task
* `deadline`  are tasks which needs to be done before the a certain time

You can add, delete and modify task. You can also mark them as done.

### Save and Load

Stop losing your data! Duke saves and load your list unto a data file automatically to ensure you're always on top of things! It will automatically check for a data file every time you start it up


## Usage
```
Notes the format
* Items in angle brackets are parameters which the user input
```
### `Bye` - Closes application

A goodbye message (shown below) and the application closing in 1 second.

Format: `bye`
* Having any other parameter will cause the command to not be execute

### `List` - List of task

Shows a list of all task in the `tasklist`

Format: `list`
* Having any other parameter will cause the command to not be executed

### `ToDo` - Adds new ToDo

Adds a ToDo type task unto the current task list

Format: `todo <name>`

Example: `todo play games`

### `Event` - Adds new event 

Adds an Event type task unto the current task list

Format: `event <name> /at <time>`
* <name> cannot contant `/at`
* <time> must be in a format of `yyyy-MM-dd HH:mm'
  
Example: `event exam /at 2021-11-04 12:00`
  
### `Deadline` - Adds new deadline 

Adds an Deadline type task unto the current task list

Format: `deadline <name> /by <time>`
* <name> cannot contant `/by`
* <time> must be in a format of `yyyy-MM-dd HH:mm'

Example: `deadline User Guide /by 2021-11-04 11:00`
  
### `Delete` - Delete a command

Deletes the specified command from the list

Format: `delete <index>`
* <index> refers to the index number of the task in the `list`
* <index> must be a positive integer such as 1, 2, 3

Example: `delete 1`
  
### `Done` - Mark command as done

Mark the specified task as done

Format: `done <index>`
* <index> refers to the index number of the task in the `list`
* <index> must be a positive integer such as 1, 2, 3
* If the task in the index is already marked done, the bot will inform you so
 
Example: `done 1`
  
### `Find` - Find a command

Shows all task which contains the keyword in the name

Format: `find <keyword>`

Example: `find CS`
  
### `Update` - update a task

Change task details into the specified details

Format: `update <index> <format of task>`
  * The format of task must be the same as the format of creating a new task of the same type
  * The status of the task will remain the same

Example `update 1 play games /at 2021-12-12 12:00` (assuming task with index 1 was an event)

## Command Summary

| Commands | Command Format |
| ------------ | ------------- |
| Bye | `bye` |
| List | `list` |
| ToDo | `todo <name>` </br> Example: `todo sleep` |
| Event | `event <name> /at <time>` </br> Example: `event exam /at 2021-11-04 12:00` |
| Deadline | `deadline <name> /by <time>` </br> Example: `deadline User Guide /by 2021-11-04 11:00` |
| Delete | `delete <index>` </br> Example `delete 1` |
| Done | `done <index>` </br>  `done 1` |
| Find | `find <keyword>` </br> ' `find cs` |
| Update | `update <index> <format of task>` </br> Example: `update 1 play games /at 2021-12-12 12:00` (assuming task with index 1 was an event) |
  
