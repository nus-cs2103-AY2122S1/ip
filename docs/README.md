# User Guide

DUKE is a **desktop app for managing tasks**, optimised for use via a **Command Line Interface** (CLI)


## Features 
- Adding a Todo task: `todo`
- Adding a Deadline task: `deadline`
- Adding a Event task: `event`
- Adding a DoWithin task: `dowithin`
- Deleting a task: `delete`
- Listing all tasks: `list`
- Search via keyword: `find`
- Marking a task as complete: `done`
- Exiting the program: `bye`
- Saving the data
- Editing the data file

&nbsp;

### Adding a Todo: `todo`

Adds a representation of a task to be done to the list

Format: `todo [TASK]`

Examples
- `todo read book` 
- `todo watch Harry Potter` 

&nbsp;

### Adding a Deadline: `deadline`

Adds a representation of a task to be done with a specified deadline to the list

Format: `deadline [TASK] [/by DATE]`

- Dates specified must be in `YYYY-MM-DD` format

Examples:
- `deadline return library book /by 2021-04-16`   
- `deadline pay rent /by 2021-08-10`

&nbsp;

### Adding an Event: `event`

Adds a representation of a event with a specified start and end time to the list

Format: `event [TASK] [/at DATE] [STARTTIME-ENDTIME]`

- Dates specified must be in `YYYY-MM-DD` format.
- Time range is specified in 24hr format.

Examples:
- `event career fair /at 2021-02-18 1000-1200hrs`
- `event prize ceremony /at 2021-06-12 1100-1300hrs`

&nbsp;

### Adding a DoWithin: `dowithin`

Adds a representation of a task which needs to be done within a certain period

Format: `dowithin [TASK] [/between STARTDATE and ENDDATE]`

- Dates specified must be in `YYYY-MM-DD` format.

Examples:
- `dowithin collect certificate /between 2021-04-25 and 2021-04-27`

&nbsp;

### Deleting a task: `delete`

Deletes a specified task from the list.

Format: `delete INDEX`

- Index refers to the index number displayed on the list.
- Index numbers **must be positive**

Examples:
- `delete 1` deletes the 1st task in the list (if present)

&nbsp;

### Listing all tasks: `list`

Shows a list of all tasks tracked by DUKE

Format: `list`

&nbsp;

### Search via keyword: `find`

Finds any task which contains the given keyword

Format: `find [KEYWORD]`

- Search is case-sensitive i.e `book` will NOT match `Book`

Examples:
- In a list containing `todo read book` & `deadline return book /by 2021-04-04`, `find book` will return both tasks  

&nbsp;

### Marking a task as complete: `done`

Tells DUKE to mark the task at the specified index as complete

Format: `done INDEX`

- Index refers to the index number displayed on the list.
- Index numbers **must be positive**

Examples:
- `delete 2` marks the 2nd task in the list as complete(if present)

&nbsp;

### Exiting the program: `bye`

Exits the program.

Format: `bye` 

&nbsp;

### Saving the data

- DUKE has an autosave feature which is invoked upon the typing the command `bye`.
- If the program is forcibly closed before normal termination, the session's data will not be saved.

&nbsp;

### Editing the data

- Data is stored in a TXT file in `[DESKTOP LOCATION]/data/tasklist.txt`
- Advanced users are welcome to update or edit the data directly via the file.

