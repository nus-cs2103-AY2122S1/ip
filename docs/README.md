# DukeBot User Guide

DukeBot is a CLI (Command Line Interface) program that functions as a to-do list manager,
with GUI in JavaFX.

## Features 



### Listing all tasks: ```list```

Shows a list of all the tasks in the user's task list.

Format: ```list```
- Each task will be in the form ```[task icon] [done status] description datetime```
- ```[T]``` is a task icon that stands for a Todo task, does not have a datetime after the description.
- ```[D]``` is a task icon that stands for a Deadline task.
- ```[E]``` is a task icon that stands for an Event task. 
- ```[X]``` is a done status indicating the task is done. 
- ```[ ]``` is a done status indicating the task has not been done yet.

### Exiting the program: ```bye```

Exits the program.

Format: ```bye```

### Locating tasks by name: ```find```

Find tasks with descriptions that contain the given keyword.

Format: ```find <keyword>```

### Deleting as task: ```delete```

Delete the specified task from the user's task list.

Format: ```delete <index>```
- Deletes the task at the specified index.
- The index refers to the index number shown in the user's task list.

### Marking task as done: ```done```

Mark the specified task in the user's task list as done.

Format: ```done <index>```
- Mark the task at the specified index as done.
- The index refers to the index number shown in the user's task list.

### Adding a todo task: ```todo```

Adds a todo task to the user's task list.

Format: ```todo <description>```

### Adding a deadline task: ```deadline```

Adds a deadline task to the user's task list.

Format: ```deadline <description> /by <DD/MM/YY HHmm>```

### Adding an event task: ```event```

Adds an event task to the user's task list.

Format: ```event <description> /at <DD/MM/YY HHmm>```

### Viewing help: ```help```

Shows a message listing all the possible commands and how to input them.

Format: ```help```

### Saving the data

DukeBot data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.


*User guide adapted from https://se-education.org/addressbook-level3/UserGuide.html*
