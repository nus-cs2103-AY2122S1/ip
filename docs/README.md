# User Guide

Duck is a desktop app for managing tasks using  a Command Line Interface (CLI) while still having the benefits of a 
Graphical User Interface (GUI). If you can type fast, Duck will help you organise your tasks quicker than traditional 
GUI apps.

## Quick Start

1. Ensure you have Java 11 installed on your computer for your operating system.
   If you do not have Java 11 installed, you may visit 
   [this page](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) to do so.
2. Download duck.jar from [here](https://github.com/ntwbruce/ip/releases/tag/v0.3.1) or
   [click](https://github.com/ntwbruce/ip/releases/download/v0.3.1/duck.jar) to download.
3. Copy duck.jar to a folder of your choice.
4. Double-click on duck.jar to start Duck, or use the CLI command `java -jar duck.jar`.
5. To interact with Duck, type commands into the text bar and hit Enter or click Send.
6. The Features section below explains the commands Duck has to offer.

## Features 

### Add a Todo Task: `todo`

Adds a task to be done later to the task list.

Format: `todo (DESCRIPTION)`  

Example: `todo Buy 47 bricks and 1 can of plums`

### Add a Deadline Task: `deadline`

Adds a Deadline to the task list. Deadline can be a date, or a date and time. Specify the date (and time) of the 
deadline in the following format: _YYYY-MM-DD HH:mm_.

Formats:\
`deadline (DESCRIPTION) /by (DATE)`\
`deadline (DESCRIPTION) /by (DATE) (TIME)`

Examples:\
`deadline Make User Guide /by 2021-09-15`\
`deadline Submit CS2105 Assignment /by 2021-09-24 23:59`

### Add an Event Task: `event`

Adds an Event to the task list. Event can have the following periods:
* Over multiple days (start and end date)
* On one day, over a period of time (date, start and end time)
* Over multiple days with times (start date and time, end date and time) 

Specify the dates (and times) of the deadline in the following format: _YYYY-MM-DD HH:mm_.

Formats:\
`event (DESCRIPTION) /at (START_DATE) (END_DATE)`\
`event (DESCRIPTION) /at (DATE) (START_TIME) (END_TIME)`\
`event (DESCRIPTION) /at (START_DATE) (START_TIME) (END_DATE) (END_TIME)`

Examples:\
`event Brain Cell Building Workshop /at 2021-09-15 2021-09-21`\
`event CS2103T Finals /at 2021-11-23 17:00 19:00`\
`event Floating World Under the Moonlight Guide Contest /at 2021-09-01 12:00 2021-10-09 23:59`

### Set a task to done: `done`

Marks a task in the task list as done. User must provide the list index of the task, i.e. the position of the task in 
the list, to be set to done.

Format: `done (TASK_INDEX)`  

Example: `done 1` (sets the first task in the task list to done)

### Remove a task: `delete`

Removes a task from the task list. User must provide the list index of the task, i.e. the position of the task in
the list, to be deleted.

Format: `delete (TASK_INDEX)`

Example: `delete 2` (removes the second task from the task list)

### Show the current list of tasks: `list`

Returns a list of all tasks currently in the task list.

Format: `list`

Example output:
````
Here are the tasks in your list:
1.[T][ ] Buy 47 bricks and 1 can of plums
2.[D][X] Make User Guide (by 2021-09-15)
3.[E][ ] CS2103T Finals (on 2021-11-23 from 5:00pm to 7:00pm)
````

### Find tasks containing a keyword: `find`

Finds all tasks in the task list that contain a given keyword. If multiple words are provided, it is treated as one 
keyword: only tasks with that exact phrase (words together in that order) will be returned.

Format: `find (KEYWORD)`

Examples:\
`find Submit` finds all tasks that contain the keyword "Submit"\
`find Assignment 1` finds all tasks that contain the exact phrase "Assignment 1"

### Show schedule for the day: `schedule`

Returns a schedule of tasks to be completed or that take place on the given date. Separates the tasks into those with 
specific timings and those without. Specify the date in the following format: _YYYY-MM-DD_.

Format: `schedule (DATE)`

Example: `schedule 2021-09-15`

Expected output:
````
Here is the schedule for 2021-09-15:
[D][ ] Go shopping (by 2021-09-15 at 3:00pm)
[E][ ] Prepare for dinner (on 2021-09-15 from 5:00pm to 7:00pm)

Here are tasks without specific timing:
[D][X] Make User Guide (by 2021-09-15)
````

### Close the program: `bye`

Closes the program.

Format: `bye`

### Saving of data between uses
Duck saves your existing task list to a hard disk when the application is closed, and will reload it when the 
application is loaded again. On the first usage of Duck, a directory `data` will be created in the same folder as the 
`duck.jar` file, and the data will be saved in a file called `duck.txt` within `data`.
