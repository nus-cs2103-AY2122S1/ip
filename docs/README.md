# User Guide
*Duke-Mark* is a desktop application for **managing tasks**, 
optimized for use via a Command Line Interface (CLI). 
* [Quick start](#quick-start)
* [Features](#features)
    * [Adding a todo task `todo`](#adding-a-todo-task-todo)
    * [Adding a deadline `deadline`](#adding-a-deadline-deadline)
    * [Adding an event `event`](#adding-an-event-event)
    * [Marking a task as done `done`](#marking-a-task-as-done-done)
    * [Finding the specific tasks `find`](#finding-the-specific-tasks-find)
    * [Listing all the tasks `list`](#listing-all-the-tasks-list)
    * [Deleting a specific task `delete`](#deleting-a-specific-task-delete)
    * [Adding a task with a specific period `period`](#adding-period-of-specific-task)
    * [Exiting the system `bye`](#exiting-the-system-bye)
## Features 
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `Duke-Mark.jar` from here.
3. Copy the file to the folder you want to use as the 
home folder for your own Duke-Mark.
4. Create your own text file named as <tasks.txt>
5. Double-click the file to start the app. The GUI similar to 
the below should appear in a few seconds. 
### Feature-Duke-Mark
![Enter Page](./Ui.png)

### Features
* Words in `UPPER_CASE` are the parameters to be supplied by the user.  
e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
* The valid input of time is `YYYY-MM-DD HH:SS`

### Adding a todo task `todo` 
Adds a todo task to the task list. You can also use command: `TODO`
Format: `todo DESCRIPTION`

Examples:
* `todo read book`

### Adding a deadline `deadline`
Adds a deadline to the task list. 
Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:SS`
Examples:
* `deadline return book /by 2021-08-20 12:00
`

### Adding an event `event`
Adds an event to the task list. 
Format: `event DESCRIPTION /at YYYY-MM-DD HH:SS`
Examples:
* `event meeting /at 2021-08-23 17:00`

### Marking a task as done `done`
Marks the task in the list with index `i` as done. 
Format: `done i`
Examples:
* `done 1`

### Finding the specific tasks `find`
Finds the tasks contains `KEYWORD` in the task list. 
Format: `find KEYWORD`
Examples:
* `find book`

### Listing all the tasks `list`
Lists all the tasks in the task list. 
Format: `list`

### Deleting a specific task `delete`
Deletes the task in the list with index `i`. 
Format: `delete i`
Examples:
* `delete 1`

### Exiting the system `bye`
Exits Duke-Mark.
Format: `bye`