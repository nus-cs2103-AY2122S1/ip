# User Guide


Duke is a chatbot that is also a task manager that helps you to keep track your tasklist, ranging from todos, events, deadlines and fixed duration tasks. 

- [User Guide](#user-guide)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [`todo` - Creates a ToDo Task](#todo---creates-a-todo-task)
    - [`event` - Creates an Event Task](#event---creates-an-event-task)
    - [`deadline` - Creates an Deadline Task](#deadline---creates-an-deadline-task)
    - [`fixedduration` - Creates an Fixed Duration Task](#fixedduration---creates-an-fixed-duration-task)
    - [`list` - Lists all Tasks in Tasklist](#list---lists-all-tasks-in-tasklist)
    - [`find` - Searches for Tasks that matches keyword](#find---searches-for-tasks-that-matches-keyword)
    - [`done` - Marks Task in Tasklist as done](#done---marks-task-in-tasklist-as-done)
    - [`bye` - Exits the Application](#bye---exits-the-application)
    - [`list` - Lists all Tasks in Tasklist](#list---lists-all-tasks-in-tasklist-1)
  - [Command Summary](#command-summary)
  
## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar from [here](https://github.com/RachelCheah/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Duke Program.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![UI](https://i.imgur.com/Q0F4Zc1.png)

5. Type the command in the command box and press Enter to execute it.
Some example commands you can try:

   * `todo Read Duke's User Guide` - Adds a todo Task named Read Duke's User Guide
   * `list` - Lists all Tasks in Tasklist
   * `delete 1` - Deletes the 1st contact shown in the current list.
   * `bye` - Exits the app.
6. Refer to the [Features](#features) below for details of each command.



## Features


### `todo` - Creates a ToDo Task

Adds a todo task to the tasklist.

Format: `todo DESCRIPTION`

Example: 
 ```
todo Read book
```



### `event` - Creates an Event Task

Adds a event task to the tasklist.

Format: `event DESCRIPTION /at yyyy-mm-dd`

Example: 
```
event job interview /at 2021-08-08 1200
```


### `deadline` - Creates an Deadline Task

Adds a deadline task to the tasklist.

Format: `deadline DESCRIPTION /by yyyy-mm-dd`

Example: 
```
deadline return book /by 2021-08-08 1200
```


### `fixedduration` - Creates an Fixed Duration Task

Adds a fixed duration task that does not have a fixed start/end time to the tasklist.

Format: `fixedduration DESCRIPTION /duration DURATION`

Example: 
```
fixedduration eat /duration 2 hours
```


### `list` - Lists all Tasks in Tasklist

Shows the user a list of all the current Tasks in the task list

Format: `list`



### `find` - Searches for Tasks that matches keyword

Adds a deadline task to the tasklist.

Format: `find KEYWORD`

Example: 
```
find read
```


### `done` - Marks Task in Tasklist as done

Marks the specified task as done.

Format: `done TASK_INDEX`

Example: 
```
done 2
```


### `bye` - Exits the Application

Exits the Application

Format: `bye`



### `list` - Lists all Tasks in Tasklist

Shows the user a list of all the current Tasks in the task list

Format: `list`


## Command Summary
* Create a ToDo Task: `todo DESCRIPTION`
* Create an Event Task: `event DESCRIPTION /at yyyy-mm-dd`
* Create an Deadline Task: `deadline DESCRIPTION /by yyyy-mm-dd`
* Create an Fixed Duration Task: `fixedduration DESCRIPTION /duration DURATION`
* Lists all Tasks in Tasklist: `list`
* Search for Tasks that matches keyword: `find KEYWORD`
* Marks Task in Tasklist as done: `done TASK_INDEX`
* Delete Task from Tasklist: `delete TASK_INDEX`
* Exits the Application: `Bye`
