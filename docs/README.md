# User Guide
WingerIO is a task manager with a GUI.
Start getting organized using simple text-based commands!

***

* [Quick Start](#quick-start)
* [Features](#features)
  * [Add todo task](#adding-a-todo-task-todo)
  * [Add deadline task](#adding-a-deadline-task-deadline)
  * [Add event task](#adding-a-event-task-event)
  * [Mark task as done](#marking-a-task-as-done-done)
  * [Delete task](#deleting-a-task-delete)
  * [List tasks](#listing-all-tasks-list)
  * [Find tasks](#finding-a-task-find)
  * [Sort tasks](#sorting-tasks-sort)
  * [Exit](#exiting-bye)
* [Command Summary](#command-summary)

***

##Quick Start
1. Ensure you have Java 11 or later installed
2. Download the latest WingerIO.jar from [here](https://github.com/hpkoh/ip/releases/latest)
3. Copy the file into the folder you want to use as the home folder
4. Double-click the file to start the WingerIO!
***
## Features 

### Adding a todo task: `todo`

Add a todo task with only a description to the list of tasks.

Format: `todo {description}`

Example: `todo ip`

### Adding a deadline task: `deadline`

Add a task with a deadline to the list of tasks.

Format: `deadline {description} /by {YYYY-MM-DD}`

Example: `deadline ip /by 2021-09-17`

### Adding a event task: `event`

Add an event task with a start and end time to the list of tasks

Format: `event {description} /from {YYYY-MM-DD} /to {YYYY-MM-DD}`

Example: `event ip /from 2021-08-09 /to 2021-09-17`

### Marking a task as done: `done`

Mark a specific task as done

Format: `done {index}`
* mark task at the specific `{index}` as done 
* index must be a positive integer
* index must be less than or equal to the size of the task list

Example: `done 3`

### Deleting a task: `delete`

Delete a specific task

Format: `delete {index}`
* delete task at the specific `{index}`
* index must be a positive integer
* index must be less than or equal to the size of the task list

Example: `delete 3`

### Listing all tasks: `list`

Shows a list of all the tasks.

Format: `list`

### Finding a task: `find`

Shows a list of all the tasks with given keyword in their description

Format: `find {keyword}`
* Keywords can be partially match e.g `2103` will match `cs2103`

Example: `find ip`

### Sorting tasks: `sort`

Sort the tasks according to their due date.

Format: `sort`

### Exiting: `bye`

Exit WingerIO.

Format: `bye`

***
##Command Summary

Command | Format
------------ | -------------
Add todo task | `todo {description}`
Add deadline task | `deadline {description} /by {YYYY-MM-DD}`
Add event task | `event {description} /from {YYYY-MM-DD} /to {YYYY-MM-DD}`
Mark task as done | `done {index}`
Delete task | `delete {index}`
List tasks | `list`
Find tasks | `find {keyword}`
Sort tasks | `sort`
Exit | `bye`
