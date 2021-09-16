# User Guide

Duke is a personal bot assistant to help you keep track of all the things you need to do! 

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
* Having any other parameter will cause the command to not be executed

Example usage:

![Bye Message](/docs/picturesforug/bye.png)


### `List` - List of task

Shows a list of all task in the `tasklist`

Format: `list`
* Having any other parameter will cause the command to not be executed

Example of usage: 

![list](/docs/picturesforug/list.png)

### `ToDo` - Adds new ToDo

Adds a ToDo type task unto the current task list

Format: `todo <name>`

Example of usage: 

![ToDo reply](/docs/picturesforug/todo.png)

### `Event` - Adds new event 

Adds an Event type task unto the current task list

Format: `event <name> /at <time>`
* <name> cannot contant `/at`
* <time> must be in a format of `yyyy-MM-dd HH:mm'
  
Example of usage: 

![event reply](/docs/picturesforug/event.png)

### `Deadline` - Adds new deadline 

Adds an Deadline type task unto the current task list

Format: `deadline <name> /by <time>`
* <name> cannot contant `/by`
* <time> must be in a format of `yyyy-MM-dd HH:mm'
  
Example of usage: 

![deadline reply](/docs/picturesforug/deadline.png)

### `Delete` - Delete a command

Deletes the specified command from the list

Format: `delete <index>`
* <index> refers to the index number of the task in the `list`
* <index> must be a positive integer such as 1, 2, 3
  
Example of usage: 

![delete reply](/docs/picturesforug/delete.png)

### `Done` - Mark command as done

Mark the specified task as done

Format: `done <index>`
* <index> refers to the index number of the task in the `list`
* <index> must be a positive integer such as 1, 2, 3
* If the task in the index is already marked done, the bot will inform you so
  
Example of usage: 

![done reply](/docs/picturesforug/done.png)

### `Find` - Find a command

Shows all task which contains the keyword in the name

Format: `find <keyword>`
  
Example of usage: 

![find reply](/docs/picturesforug/find.png)

### `Update` - update a task

Change task details into the specified details

Format: `update <index> <format of task>`
  * The format of task must be the same as the format of creating a new task of the same type
  * The status of the task will remain the same
     
Example of usage: 

![update reply](/docs/picturesforug/update.PNG)
