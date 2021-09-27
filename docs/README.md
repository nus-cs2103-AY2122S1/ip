# User Guide

Duke is a personal chatbot that helps you to manage your tasks. Its text-based with a simple and neat Graphical User Interface, easy to learn and super fast to use!

## Quick Start

1. Download the latest `duke.jar` from [here.](https://github.com/YoYoCiti/ip/releases/tag/v0.2)
2. Copy the file to the folder you want to use as the *home folder* for Duke to store its data in.
3. Double click the file to start the app.  

## Command Summary

Action | Format, Examples
------  | ----------------
Help | `help`
Todo | `todo <description>`
Deadline | `deadline <description> /by <deadline>` eg. `deadline homework /by 23/09/2021 2359`
Event | `event <description> /at <time>` eg. `event meetup /at 23/09/2021 1500`
List | `list`
Find | `find <keywords>`
Delete | `delete <index>` eg. `delete 2`
Done | `done <index>` eg. `done 2`
Goodbye | `bye`

## Features 

### Getting Help: `help`

Duke provides a list of all available commands and brief explanation on how to use them.

### Adding a Todo Task: `todo`

Duke adds a todo task into your task list.

Format: `todo <task description>`

* Task description **cannot be empty**

Example of usage:

`todo Buy pen`

### Adding a Deadline Task: `deadline`

Duke adds a task with a deadline into your task list.

Format: `deadline <task description> /by <deadline>`

* Task description **cannot be empty**
* Given deadline should be in the following format **D/MM/YYYY HH:mm**

Example of usage:

`deadline CS2100 Assignment /by 15/09/2021 1300`

### Adding a Event Task: `event`

Duke adds an event into your task list.

Format: `event <description> /at <date and time>`

* Description **cannot be empty**
* Given date and time should be in the following format **D/MM/YYYY HH:mm**

Example of usage:

`event Frisbee training /at 12/09/2021 1930`

### Viewing the Task List: `list`

Duke shows you your task list.

Format: `list` 

### Locating Tasks by Description: `find`

Duke lists tasks that matches the given keywords.

Format: `find <keywords>`

* Keyword field **cannot be empty**
* Multiple keywords can be given and tasks matching all the given keywords will be returned
* Order of the keywords matter
* The search is case-insensitive. e.g `frisbee` will match `Frisbee`
* Only the description of tasks is searched

Example of usage:

`find frisbee`

### Deleting Tasks: `delete`

Duke deletes the specified task from the task list.

Format: `delete <index>`

* The index refers to the index number shown in the displayed task list from command `list`
* The index **must be a positive integer** 1, 2, 3, ...

Example of usage:

`delete 2` deletes task 2 from the task list

### Marking Tasks as Done: `done`

Duke marks the specified task as done. 

Format: `done <index>`

* The index refers to the index number shown in the displayed task list from command `list`
* The index **must be a positive integer** 1, 2, 3, ...

Example of usage:

`done 2` marks task 2 as done

### Saying Goodbye to Duke: `bye`

Duke bids you farewell. 

### Saving and Loading Data

Duke saves data in the hard disk automatically after any command that changes the data. Data is saved as TXT file `<JAR file location>/data/duke.txt`. 

**Caution:** If changes made to this data file makes its format invalid, Duke will not be able to load the data accurately.  
