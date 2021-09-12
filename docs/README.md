# User Guide

DukeNukemBot, or just Duke, is a desktop app for you to manage tasks using
a Command Line Interface (CLI) while still having the benefits of a Graphical
User Interface. (GUI) If you can type fast, Duke will help you organise your
tasks quicker than traditional GUI apps.

## Quick Start

1. Ensure you have Java 11 installed on your computer for your operating system.
   If you do not have Java 11 installed, you may visit the following link to do
   so:
   https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html
2. Download Duke.jar from [here](https://github.com/lowkangn/ip/releases/download/BCD-Extension/duke.jar).
3. Copy Duke.jar to a folder you want.
4. Double-click on Duke.jar to start Duke.
5. Type in commands into the text book and hit Enter or Send to start using Duke.
6. Refer to the Features section below to learn what commands you can use in Duke.

## Features 

### Add a ToDo task: `todo`

Adds a task that you intend to do later to Duke's list.  
Format: `todo (DESCRIPTION)`  
Examples:
* `todo borrow book`
* `todo buy bread from store`

### Add a Deadline task: `deadline`

Adds a task that has a known deadline to Duke's list.</br>
Specify the date and time of the deadline in the following format:
YYYY-MM-DD, HH:mm<br/>
If no time is specified, Duke will automatically set midnight of the date to be
the time.  
Format: `deadline (DESCRIPTION) /by (DATETIME)`  
Examples:
* `deadline finish homework /by 2021-09-11, 13:00`
* `deadline return book /by 2021-08-20`

### Add an Event task: `event`

Adds a task that has a duration to Duke's list.  
Specify the date and time of the event in the following format:
YYYY-MM-DD, HH:mm<br/>
A start and end time must be specified for the event.  
Format: `event (DESCRIPTION) /at (STARTTIME) - (ENDTIME)`  
Examples:
* `event group meeting /at 2021-10-06, 13:00 - 14:00`
* `event class reunion /at 2021-09-25, 14:00 - 17:00`

### Lists all tasks: `list`

Lists all the tasks that you have added to Duke's list.  
Format: `list`  

### Mark a task as done: `done`

Marks a task in Duke's list as done. Please supply the number of the task to be
marked as done. The task number is its position in Duke's list.  
Format: `done (TASKNUMBER)`  
Examples:
* `done 1`
* `done 5`

### Delete a task: `delete`

Deletes a task in Duke's list. Please supply the number of the task to be deleted.
The task number is its position in Duke's list.  
Format: `delete (TASKNUMBER)`  
Examples:
* `delete 1`
* `delete 5`

### Find a task: `find`

Locates a task in Duke's list with a given keyword.  
Format: `find (KEYWORD)`  
Examples:
* `find book`
* `find group meeting`

### Update a task's details: `update`

Updates a task in Duke's list by changing its description or its date and time.
You cannot change tasks between types e.g. changing a ToDo task to a Deadline
task is not allowed etc.   
You may choose to change only the description, only the date and time or both.
Please supply the number of the task to be updated. The task number is its position
in Duke's list.
  
Format: `update (TASKNUMBER) /d (DESCRIPTION) /t (DATETIME)`  
Examples:
* `update 1 /d throw out the trash`
* `update 3 /t 2021-09-15, 12:00`
* `update 10 /d web seminar /t 2021-11-23, 13:00 - 14:00`

### Close Duke: `bye`

Closes Duke.  
Format: `bye`  

### Saving the data

Duke will save your tasks automatically in the hard disk after any changes you
made to the list. There is no need to save manually. The save file will be created
in the same folder as Duke.jar.