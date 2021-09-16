# User Guide for Project Wednesday Dude

Wednesday Duke is an application made to help users keep track of various tasks they want to do. The application takes all input through typing out text and is optimized for use through Command Line Interface (CLI) 

* [1. Features](#Features)
    - [1.1. Help](#Help)
    - [1.2. Add Todo Task](#Todo)
    - [1.3. Add Deadline Task](#Deadline)
    - [1.4. Add Event Task](#Event)
    - [1.5. Set Task as Done](#Done)
    - [1.6. Delete Task](#Delete)
    - [1.7. Find Task](#Find)
    - [1.8. List Tasks](#List)
    - [1.9. Close Application](#Bye)
* [2. Code Summary](#Code)

## 1. Features <a name="Features"></a>

### 1.1 Help <a name="Help"></a>

This feature allows users to learn how to use the application and view its syntax

_Format_: `help`

_Example_: `help`

### 1.2. Add Todo Task  <a name="Todo"></a>

This feature adds a todo task to the user's task list

_Format_: `todo <TASK_DESCRIPTION>`

_Example_: `todo read book` 

### 1.3. Add Deadline Task <a name="Deadline"></a>

This feature adds a deadline task to the user's task list

_Format_: `deadline <TASK_DESCRIPTION> /by <dd-MM-yyyy> <HH:mm>`

_Example_: `deadline return book /by 12-09-2021 12:00`

### 1.4. Add Event Task <a name="Event"></a>

This feature adds an event task to the user's event list

_Format_: `event <TASK_DESCRIPTION> /at <dd-MM-yyyy> <HH:mm>`

_Example_: `event attend team meeting /at 12-09-2021 12:00`

### 1.5. Set Task as Done <a name="Done"></a>

Set a task from the user's task list as done using it's index number

_Format_: `done <TASK_NUMBER>`

_Example_: `done 2`

### 1.6. Delete Task <a name="Delete"></a>

Delete a task from the user's task list using it's index number

_Format_: `delete <TASK_NUMBER>`

_Example_: `delete 2`

### 1.7. Find task <a name="Find"></a>

Find a task/tasks by searching for a specific keyword

_Format_: `find <KEYWORD>`

_Example_: `find book`

### 1.8. List Tasks <a name="List"></a>

List out all the tasks in the user's task list

_Format_: `list`

_Example_: `list`

### 1.9. Bye <a name="Bye"></a>

Close the application and save all your tasks onto a text file

_Format_: `bye`

_Example_: `bye`

## 2. Code Summary <a name="Code"></a>

Index | Command | Format | Example
----- | ------- | ------ | -------
1 | Help | `help` | `help`
2 | Add Todo Task | `todo <TASK_DESCRIPTION>` | `todo read book`
3 | Add Deadline Task | `deadline <TASK_DESCRIPTION> /by <dd-MM-yyyy> <HH:mm>` | `deadline return book /by 12-09-2021 12:00`
4 | Add Event Task | `event <TASK_DESCRIPTION> /at <dd-MM-yyyy> <HH:mm>` | `event attend team meeting /at 12-09-2021 12:00`
5 | Set Task as Done | `done <TASK_NUMBER>` | `done 2`
6 | Delete Task | `delete <TASK_NUMBER>` | `done 2`
7 | Find Task | `find <KEYWORD>` | `find book`
8 | List Tasks | `list` | `list`
9 | Close Application | `bye` | `bye`
