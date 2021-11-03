# User Guide

Duke (Pepper Jack) is a **Personal Assistant Chatbot** that helps a person keep track of their tasks. 
The name and personality of Pepper Jack was chosen in honor of *[Pepper Jack the pimp](https://itsalwayssunny.fandom.com/wiki/Pimp)*, 
the best side character on the best show on television, *It's Always Sunny in Philadelphia*.

* [Quick start](#quickstart)
* [Features](#features)
    * [Viewing help: `help`](#viewing-help-help)
    * [Listing all tasks: `list`](#listing-all-tasks-list)
    * [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
    * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
    * [Adding a Event task: `event`](#adding-a-event-task-event)
    * [Marking a task as done: `done`](#marking-a-task-as-done-done)    
    * [Deleting a task: `delete`](#deleting-a-task-delete)
    * [Finding a task: `find`](#finding-a-task-find)
    * [Exiting the program: `bye`](#exiting-the-program-bye)
    * [Saving the data](#saving-the-data)
    * [Editing the data file](#editing-the-data-file)
* [Command summary](#command-summary)

---
## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
   

2. Download the latest duke.jar from [here](https://github.com/chaiwanlin/ip/releases).


3. Copy the file to the folder you want to use as the home folder for Duke.


4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
![Image of Ui](https://chaiwanlin.github.io/ip/Ui.png)


5. Type the command in the command box and press Enter to execute it. 
   e.g. typing help and pressing Enter will open the help window.
Some example commands you can try:

    * `list` : Lists all tasks.
    * `todo buy groceries` : Adds a Todo task with description `buy groceries`.
    * `delete 1` : Deletes the 1st task in the current list
    * `bye` : Exits the app.


6. Refer to the [Features](#features) below for details of each command.

---
## Features

> Notes about the command format:
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter that can be used as `todo buy groceries`.
> * Items in square brackets are optional.
    e.g `find [KEYWORD]...` can be used as `find crack` or `find`.
> * Items with … after them can be used multiple times including zero times.
    e.g. `find [KEYWORD]...` can be used as `find` (i.e. 0 times), `find crack`, `find buy crack`.
> * Extraneous parameters for commands that do not take in parameters (such as help, list and bye) will be ignored.
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help: `help`
Shows a message with the full list of commands and their usages.

Format: `help`


### Listing all tasks: `list`
Shows a list of all the tasks saved.

Format: `list`


### Adding a Todo task: `todo`
Adds a Todo task.

Format: `todo DESCRIPTION`


### Adding a Deadline task: `deadline`
Adds a Deadline task.

Format: `dealine DESCRIPTION \by YYYY-MM-DD`


### Adding a Event task: `event`
Adds a Event task.

Format: `event DESCRIPTION \at YYYY-MM-DD`


### Marking a task as done: `done`
Marks a task as done.

Format: `done INDEX`


### Deleting a task: `delete`
Deletes a task from your list.

Format: `delete INDEX`


### Finding a task: `find`
Finds an existing task.

Format: `find [KEYWORD]...`


### Exiting the program: `bye`
Exits the program.

Format: `bye`


### Saving the data
Duke data are saved in the hard disk after the program exits. 
There is no need to save manually.


### Editing the data file
Duke data are saved as a JSON file [JAR file location]/data/tasks.txt.
Advanced users are welcome to update data directly by editing that data file.

---
## Command summary
| Action | Format | Examples     |
| :--- | :---- | :--- |
| Add Todo task | `todo DESCRIPTION`       | `todo buy crack` |
| Add Deadline task | `deadline DESCRIPTION /by YYYY-MM-DD` | `deadline smoke crack /by 2020-01-01` |
| Add Event task | `event DESCRIPTION /at YYYY-MM-DD` | `event crack convention /at 2020-01-01` |
| Mark task as done | `done INDEX` | `done 1` |
| Delete task | `delete INDEX` | `delete 1` |
| Find task | `find [KEYWORD]...` | `find crack` |
| List tasks | `list` | `list` |
| Help | `help` | `help` |
| Exit | `bye` | `bye` |
