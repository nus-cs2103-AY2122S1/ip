# User Guide
Duke is your personal chatbot that helps you to keep track of your tasks.

![Ui](docs/images/Ui.png)
## Features 

### Adding a todo task :`todo`

Add a simple todo task to the task list.
* Usage: `todo <DESCRIPTION>`
* Example: 
```
todo Read a book
```

### Adding a deadline task :`deadline`

Add a task with a deadline.
* Usage: `deadline <DESCRIPTION> /by <DATELINE>`
* Example:
```
deadline Math Quiz /by 17/09/2021 1200
```

### Adding a event task :`event`

Add a task that is an event.
* Usage: `event <DESCRIPTION> /at <EVENT_TIME>`
* Example: 
```
event Project Meeting /at 17/09/2021 1600
```
### Deleting a task :`delete`

Delete a task from the task list.
* Usage: `delete <TASK_NO>`
* Example: 
```
event Project Meeting /at 17/09/2021 1600
```

### List all tasks :`list`

List all the task in the task list.
* Usage: `list`

### Mark a task as done :`done`

Mark a task as completed.
* Usage: `done <TASK_NO>`
* Example:
```
done 1
```

### Tagging a task :`tag`

Tag a task with a comment.
* Usage: `tag <TASK_NO> <TAG>`
* Example: 
```
tag 1 Important
```

### Search for a task :`find`

Search for a task using keywords.
* Usage: `find <KEYWORDS>`
* Example:
```
find quiz
```

### Exiting duke :`bye`

Terminate the duke session.
* Usage: `bye`


