# User Guide
Blitz is an organization tool that frees your mind of having to remember things you need to do. It's a text-based, easy to learn GUI app for managing tasks like todo's, events and deadlines.

### Features
* Adding a task todo : `todo`
* Adding a deadline : `deadline`
* Adding an event : `event`
* Deleting a task : `delete`
* Finding a task : `find`
* Marking a task as done : `done`
* Listing existing tasks : `list`
* Viewing command help : `help`
* Saving the data 

## Features 
<div markdown="block" class="alert alert-info">
  
**Notes about the command format:**<br>
  
* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo go running`.
  
</div>

### Adding a task todo : `todo`

Adds a task to do to the list.

Format : `todo NAME`

Example:
* `todo buy groceries`

Expected outcome:
The task `buy groceries` has been added to the list.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/todo.png?raw=true" width = "500" height = "300">



### Adding a deadline : `deadline`

Adds a deadline to be completed by a certain date and time to the list.

Format : `deadline NAME /by DATE TIME`

**Note:**<br>
* DATE should be in dd/mm/yyyy format
* TIME should be in 24 hour format

Examples:
* `deadline finish iP /by 17/09/2021 2359`

Expected outcome:
The deadline `finish iP` to be completed by the given date and time has been added to the list.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/deadline.png?raw=true" width = "500" height = "300">



### Adding an event : `event`
Adds an event schedules for a certain date and time to the list.

Format : `event NAME /on DATE TIME`

**Note:**<br>
* DATE should be in dd/mm/yyyy format
* TIME should be in 24 hour format

Examples:
* `event project meeting /by 21/09/2021 1600`

Expected outcome:
The `project meeting` event scheduled for the given date and time has been added to the list.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/event.png?raw=true" width = "500" height = "300">



### Deleting a task : `delete`
Deletes the specified task from the list.

Format : `delete NUMBER`

Examples:
* `delete 4`

Expected outcome:
The `4th` item in the task list is deleted.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/delete.png?raw=true" width = "500" height = "300">



### Finding a task : `find`
Displays all tasks containing the specified keyword.

Format : `find KEYWORD`

Examples:
* `find quiz`

Expected outcome:
List of tasks that contains the word `quiz`

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/find.png?raw=true" width = "500" height = "300">



### Marking a task as done : `done`
Marks specified task in the list as done.

Format : `done NUMBER`

Examples:
* `done 3`

Expected outcome:
The `3rd` item in the task list is marked as done.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/done.png?raw=true" width = "500" height = "300">



### Listing existing tasks : `list`
Displays all the tasks currently present in the list.

Format : `list`

Examples:
* `list`

Expected outcome:
The current list of tasks is displayed.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/list.png?raw=true" width = "500" height = "300">



### Viewing command help : `help`
Shows syntax and purpose of the specified command.

Format : `help COMMAND`

Examples:
* `help find`

Expected outcome:
The syntax and purpose of the find command is displayed.

<img src ="https://github.com/aishh12/ip/blob/master/src/main/resources/images/help.png?raw=true" width = "500" height = "300">



### Saving the data 
The tasks in the hard disk are saved automatically whenever the task list changes and the data from the hard disk when Blitz starts up. There is no need to save manually.

