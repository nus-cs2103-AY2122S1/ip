# TiTi Catbot
# User Guide

## Features 

TiTi Catbot is a Personal Assistant Chatbot that helps you keep track of your upcoming tasks!
TiTi Catbot frees your mind of having to remember things you need to do.

TiTi can manage three types of tasks: 
* `Todo`: simple tasks to be completed
* `Deadline`: tasks that need to be completed before a specified time
* `Event`: scheduled tasks for at specified time

TiTi can help you keep track of which tasks have been completed. 

<hr> 

## Usage

### Adding a todo task: `todo`

Adds a new `todo` task to your task list.

Format: `todo <task_description>`

Example: `todo buy groceries`

<br>

### Adding a deadline task: `deadline`

Adds a new `deadline` task to your task list.

Format: `deadline <task_description> /by <time>`
* Time can be entered in any format convenient for you. 
* Time can also be entered in the format `YYY-MM-DD`. 

Examples: 
* `deadline math homework /by today 9pm`
* `deadline ip project /by 2020-09-17`
  
<br>

### Adding an event task: `event` 

Adds a new `event` task to your task list.

Format: `event <task_description> /at <time>`
* Time can be entered in any format convenient for you.
* Time can also be entered in the format `YYY-MM-DD`.

Examples:
* `event team meeting /at Saturday 9pm`
* `event concert /at 2020-09-13`

<br>

### Listing all tasks: `list` 

Shows a list of all tasks in your task list.

Format: `list`

<br>

### Marking a task as complete: `done`

Marks specified tasks as done. 

Format: `done <task_numbers>`
* Multiple task numbers can be entered, each separated by a space. 

Example: `done 2 3`

<br>

### Deleting a task: `delete`

Deletes specified tasks from task list.

Format: `delete <task_numbers>`
* Multiple task numbers can be entered, each separated by a space.

Example: `delete 2 3`

<br>

### Finding a task: `find`

Finds specified tasks from task list.

Format: `find <task_description>`

Example: `find homework`

<br>

### Exiting the program: `exit`

Exits the program. 

Format: `exit`


