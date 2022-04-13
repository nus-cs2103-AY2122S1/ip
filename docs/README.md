# User Guide


Duke is a chatbot that is also a task manager that helps you to keep track your tasklist, ranging from todos, events, deadlines and fixed duration tasks. 

- [User Guide](#user-guide)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Creates a ToDo Task - `todo`](#creates-a-todo-task---todo)
    - [Creates an Event Task - `event`](#creates-an-event-task---event)
    - [Creates an Deadline Task - `deadline`](#creates-an-deadline-task---deadline)
    - [Creates an Fixed Duration Task - `fixed`](#creates-an-fixed-duration-task---fixed)
    - [Lists all Tasks in Tasklist - `list`](#lists-all-tasks-in-tasklist---list)
    - [Searches for Tasks that matches keyword - `find`](#searches-for-tasks-that-matches-keyword---find)
    - [Marks Task in Tasklist as done - `done`](#marks-task-in-tasklist-as-done---done)
    - [Exits the Application `bye`](#exits-the-application-bye)
  - [Command Summary](#command-summary)
  
## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar from [here](https://github.com/RachelCheah/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Duke Program.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Ui](https://imgur.com/AT6DuS9)

5. Type the command in the command box and press Enter to execute it.
Some example commands you can try:

   * `todo Read Duke's User Guide` - Adds a todo Task named Read Duke's User Guide
   * `list` - Lists all Tasks in Tasklist
   * `delete 1` - Deletes the 1st contact shown in the current list.
   * `bye` - Exits the app.
6. Refer to the [Features](#features) below for details of each command.



## Features


### Creates a ToDo Task - `todo` 

Adds a todo task to the tasklist.

Format: `todo DESCRIPTION`

Example: 
 ```
todo Read book
```  
&nbsp;



### Creates an Event Task - `event` 

Adds a event task to the tasklist.

Format: `event DESCRIPTION /at yyyy-MM-dd (HH:mm)`

Example: 
```
event ruby's birthday /at 2021-08-13 0000
```  
&nbsp;

### Creates an Deadline Task - `deadline` 

Adds a deadline task to the tasklist.

Format: `deadline DESCRIPTION /by yyyy-MM-dd (HH:mm)`

Example: 
```
deadline finish assignment /by 2021-08-08 1200
```  
&nbsp;

### Creates an Fixed Duration Task - `fixed` 

Adds a fixed duration task that does not have a fixed start/end time to the tasklist.

Format: `fixed DESCRIPTION /duration DURATION`

Example: 
```
fixed eat /duration 20 minutes
```
&nbsp;

### Lists all Tasks in Tasklist - `list` 

Shows the user a list of all the current Tasks in the task list

Format: `list`
&nbsp;


### Searches for Tasks that matches keyword - `find` 

Adds a deadline task to the tasklist.

Format: `find KEYWORD`

Example: 
```
find read
```
&nbsp;

### Marks Task in Tasklist as done - `done` 

Marks the specified task as done.

Format: `done TASK_INDEX`

Example: 
```
done 2
```
&nbsp;

### Exits the Application `bye`

Exits the Application

Format: `bye`
&nbsp;



## Command Summary
* Create a ToDo Task: `todo DESCRIPTION`
* Create an Event Task: `event DESCRIPTION /at yyyy-MM-dd (HH:mm)`
* Create an Deadline Task: `deadline DESCRIPTION /by yyyy-MM-dd (HH:mm)`
* Create an Fixed Duration Task: `fixed DESCRIPTION /duration DURATION`
* Lists all Tasks in Tasklist: `list`
* Search for Tasks that matches keyword: `find KEYWORD`
* Marks Task in Tasklist as done: `done TASK_INDEX`
* Delete Task from Tasklist: `delete TASK_INDEX`
* Exits the Application: `Bye`
