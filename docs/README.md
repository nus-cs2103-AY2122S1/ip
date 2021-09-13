# User Guide for Duke Task Bot
Duke Task Bot is a desktop app for managing your tasks.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from [here](https://github.com/hungkhoaitay/ip/releases/tag/A-Release).

3. Double-click the file to start the app.

4. Type the command in the command box and press Enter to execute it.  Refer to the Features below for details of each command.

## Features
### Adding a todo task: `todo`
Adds a todo task to the task bot. A todo task only has a description of the task and optionally, notes for the task.

Format: `todo [task to be done]`

Example of usage: *todo play games*


### Adding a deadline task: `deadline`
Adds a deadline task to the task bot. A deadline task has a description of the task, deadline date and time and optionally, notes for the task.

Format: `deadline [task to be done] /by YYYY-MM-DD`

Example of usage: *deadline return book /by 2022-02-18*


### Adding an event task: `event`
Adds an event task to the task bot. An event task has a description of the task, event date, start and end time and optionally, notes for the task.

Format: `event [task to be done] /at YYYY-MM-DD`

Example of usage: *event book conference /at 2022-02-18*


### Listing all tasks: `list`
Shows a list of tasks stored in the task bot.

Format: `list`


### Completing a task: `done`
Marks a task, specified by the task number in the updated task list, as done.

Format: `done [task number]` (Task number refers to the task number shown in the displayed task list)

Example of usage: *done 2*


### Deleting a task: `delete`
Deletes a task that is specified by the task number in the updated task list.

Format: `delete [task number]`  (Task number refers to the task number shown in the displayed task list)

Example of usage: *delete 3*


### Finding a task by description: `find`
Find tasks with description that matches the keyword.

Format: `find [keyword]`

Example of usage: *find play*

### Exiting the bot: `bye`
Exits the program.

Format: `bye`

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo Task** | `todo [task to be done]` <br> e.g. `todo borrow book`
**Add Deadline Task** | `deadline [task to be done] /by YYYY-MM-DD` <br> e.g. `deadline return book /by 2022-02-18`
**Add Event Task** | `event [task to be done] /at YYYY-MM-DD`<br> e.g. `event book conference /at 2022-02-18`
**List Tasks** | `list`
**Done Task** | `done [task number]` <br> e.g.`done 4`
**Delete Task** | `delete [task number]`<br> e.g. `delete 3`
**Find Task By Description** | `find [keyword]` <br> e.g.`find book`
**Exit** | `bye`