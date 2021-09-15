# User Guide
Duke adds and schedules your tasks for you!

![Image](/docs/Ui.png)

## Features 

1. Viewing help to give you a list of commands: _help_
2. Listing all your current tasks: _list_
3. Find tasks based on description keywords: _find_
4. Adding a todo task: _todo_
5. Adding an event task: _event_
6. Adding a deadline task: _deadline_
7. Marking a task as done: _done_
8. Deleting a task: _delete_
9. Reversing previous command: __undo_
10. Exiting the program: _bye_
11. Saving the data
12. Editing the data files

## Installing Duke

1. Install Duke.jar from [here](https://github.com/michael-lee-sk/ip/releases/tag/v0.2).
2. Move the file to any folder to be used as the home folder for Duke.
3. Double click to run the application.

## Features description

#### `help` - Viewing help to give you a list of commands.
Shows the list of possible commands.

Format: `help`


#### `list` - Listing all your current tasks.
Lists all tasks that you may currently have.

Format: `list`


#### `find` - Find tasks based on description keywords.
Finds related tasks based on descriptions with similar keywords (does not have to be complete).

  Format: `find [KEYWORD]`
  
  Example of usage:
* `find Jan`


#### `todo` - Adding a todo task.
Adds a todo task.
  
  Format: `todo [DESCRIPTION]`
  
  Example of usage:
* `todo Eat lunch`


#### `event` - Adding an event task.
Adds an event task.
  
  Format: `event [DESCRIPTION] /at [YYYY-MM-DD HH-MM]`

  Example of usage:
* `event Dinner with Jan /at 2022-08-30 18:30`


#### `deadline` - Adding a deadline task.
Adds a deadline task

  Format: `event [DESCRIPTION] /at [YYYY-MM-DD HH-MM]`
  
  Example of usage:
* `deadline CS2103T assignment /by 2022-08-30 18:30`


#### `done` - Marking a task as done.
Marks a task as completed with an 'X'

  Format: `done [INDEX]`
  
  Example of usage:
* `done 1`
* `done 3`


#### `delete` - Deleting a task.
Deletes an existing task.

Format: `delete [INDEX]`

Example of usage:
* `delete 1`
* `delete 3`


#### `undo` - Reversing previous command.
Reverses a previous command typed in the current GUI tab.

Format: `undo`


#### `bye` - Exiting the program.
Exits the program.

Format: `bye`
