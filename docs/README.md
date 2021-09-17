# Bhutu Chat Bot User Guide

## Introduction

Manage your upcoming deadlines, events and tasks with the Bhutu bot. This task manager will manage all that you seek to do!

![Ui](https://user-images.githubusercontent.com/62797335/133653478-92067f9c-52fa-4988-b0a9-ee9211e5284d.PNG)

## Installation

### Prerequisites
1. If you have Java 11 installed in your computer you are all set!
2. Else, [download java](https://java.com/en/download/help/download_options.html).

### Setting Up
1. Download the `Bhutu.jar` from [here](https://github.com/Agentum07/ip/releases/tag/A-Release)
2. Once installed, double click on `Bhutu.jar` to run the bot.
3. **(OPTIONAL)** The bot is also accessible via the command line.
  * Open your terminal
  * Navigate to the directory containing `Bhutu.jar`
  * Use this command: 
  `java -jar Bhutu.jar`
  * Bhutu is very helpful. Be sure to treat her well!

## Features
Bhutu has multiple features, which are listed below
* LIST - Display all tasks
* TODO - Add a todo task
* EVENT - Add an event
* DEADLINE - Add a deadline
* DELETE - Delete a task
* DONE - Mark a task as done
* FIND - Search for tasks with a given keyword
* UNDO - Undo last task
* HELP - Show description and syntax of all commands
* BYE - Exit the bot

Description of the feature.

## Usage

### `LIST` all the tasks you have
Syntax: `list`
Function: Lists down all the tasks in your task list.
Expected Outcome:
```
These are your tasks: 
 1. T | 0 | watch netflix 
 2. E | 0 | movie night  | tonight
 3. D | 0 | text bhutu  | Sep 17 2021
```
`T | D | E` stand for the type of tasks - **T**odo **E**vent **D**eadline
`0` denotes that the tasks has not been completed yet.
`1` denotes that the tasks has been marked as completed.

### TODO Adds a todo task
Syntax: `todo <task description>`
Function: Adds a todo task to the list
Expected Outcome of `todo watch netflix`:
```
Got it, I've added this task:
T | 0 | watch netflix 
Now you have 1 task in the list.
```
`T` denotes that the task is of type `TODO`. `0` denotes that the task has not been done yet. `watch netflix` is the Task Description.

### EVENT Adds an event task
Syntax: `event <task description> /at <time>` 
Function: Adds an event task to the list
Expected outcome of `event movie night /at tonight`
```
Got it, I've added this task:
E | 0 | movie night  | tonight
Now you have 2 tasks in the list.
```
`E` denotes that the task is of type `EVENT`. `0` denotes that the task has not been done yet. `movie night` is the Task Description and `tonight` is the time.

### DEADLINE Adds a deadline task
Syntax: `deadline <task description> /by <time>` 
Function: Adds a deadline task to the list
Expected outcome of `deadline text bhutu /by 2021-09-17`
```
Got it, I've added this task:
D | 0 | text bhutu  | Sep 17 2021
Now you have 3 tasks in the list.
```

### DELETE a Task
Syntax: `delete [index]`
Function: deletes a task at given index
Expected Outcome of `delete 3`
```
Noted. I have removed this task:
D | 0 | text bhutu  | Sep 17 2021
Number of tasks remaining: 2
```
The message will show the task deleted and the amount of tasks remaining.

### Mark a task as DONE 
Syntax: `done [index]`
Function: marks the task at given index as done.
Expected Outcome of `done 2`
```
Nice! I've marked this task as done: 
E | 1 | movie night  | tonight
```

### FIND a Task
Syntax: `FIND [keyword]`
Function: Searches for a task with the given keyword
Expected outcome of `find netflix`:
```
Here are the matching tasks in your list: 
1. T | 0 | watch netflix 
```
It will list all the matching tasks with netflix as a keyword.

### Made a mistake? No worries, just UNDO
Syntax: `undo`
Function: Undoes the last task. Can undo until you reach the state of the app which you started with.
Expected outcome of `undo` right after `done 2`
```
Got it. I have undone the task.
These are your tasks: 
1. T | 0 | watch netflix 
2. E | 0 | movie night  | tonight
```
Notice the event has been undone.

### When stuck, just ask for HELP
Syntax: `help`
Function: Prints out the description and syntax of all available commands.
Expected outcome of `help`:
```
Welcome to the help section. 
I will help you find what you desire
	
Use the bye command to end the session.
Command Example: bye
	
Use the deadline command to add a deadline type task.
-> deadline TASK_DESCRIPTION /by DD-MM-YYYY
Command Example: deadline CS2103T iP /by 23-09-2021
	
Use the event command to add an event type task.
-> event TASK_DESCRIPTION /at time
Command Example: event fix hair /at weekend

Use the todo command to add a todo type task.
-> todo TASK_DESCRIPTION
Command Example: todo iP cleanup

Use the delete command to delete a task.
-> delete TASK_NUMBER
Command Example: delete 4
	
Use the done command to mark a task as done.
-> done TASK_NUMBER
Command Example: done 7
	
Use the find command to find tasks.
-> find KEYWORD
Command Example: find notes
	
Use the list command to view the tasks currently present in the task list.
Command Example: list
	
Use the help command to open the help section.
Command Example: help
	
Use the undo command to undo the latest action you performed.
Command Example: undo
```

### Bid farewell - BYE
Syntax: `bye`
Function: exits the program
Expected outcome of `bye`:
```
Going so soon? Hope to see you again!
```
