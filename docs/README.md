# User Guide
As David Allen said ([source](https://dansilvestre.com/productivity-quotes/)):
> "Your mind is for having ideas, not holding them."

Meow is a highly automated chat-bot for managing tasks, 
optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User 
Interface (GUI). Meow is a cute cat who loves to sleep a lot!
Meow~~

Meow frees your mind of having to remember things you need to do. It's,
* text-based
* easy to learn
* ~~FAST~~ SUPER FAST to use

## Quick start
1. Ensure you have Java ```11``` or above installed in your Computer.
2. Download the latest ```meow.jar``` from [here](https://github.com/Linxcathyyy/ip/).
3. Copy the file to the folder you want to use as the home folder for your Meow.
4. Double-click the file to start the app.

## Features 

### Adding a todo: todo

Adds a todo to your task list.

Format: ```todo TODO```

Examples: ```todo do homework```

### Adding a deadline: deadline

Adds a deadline to your task list.

Format: ```deadline DEADLINE /by DATE TIME```

Examples: ```deadline return book /by 2/12/2019 1800```

### Adding an event: event

Adds an event to your task list.

Format: ```event EVENT /at TIME```

Examples: ```event team meeting /at Friday 8-10pm```

### Listing all tasks : list

Shows a list of all tasks in the task list.

Format: ```list```

### Marking a task as done: done

Marks the specified task as done from the task list.

Format: ```done INDEX```

* Marks the task as done at the specified ```INDEX```.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* ```list``` followed by ```done 2``` marks the 2nd task as done in the task list.
* ```find homework``` followed by ```done 1``` marks the 1st task as done in the results of the find command.


### Finding a task: find

Finds tasks that contain any of the given keywords.

Format: ```find KEYWORD [MORE_KEYWORDS]```

* The search is case-insensitive. e.g. ```homework``` will match ```Homework```
* The order of the keywords does not matter. e.g. ```do homework``` will match ```homework do```
* Partial search is supported e.g. ```home``` will match ```homework```
* Supports search by task type e.g. ```D```, ```E```, and ```T```, search by done status e.g. ```1``` and ```0```, 
  and search by date e.g. ```Monday 9am```
* Tasks matching at least one keyword will be returned (i.e. ```OR``` search). 
  e.g. ```do``` will return ```do homework```, ```do laundry```

### Deleting a task : delete

Deletes the specified task from the task list.

Format: ```delete INDEX```

* Deletes the task at the specified ```INDEX```.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* ```list``` followed by ```delete 2``` deletes the 2nd task in the task list.
* ```find homework``` followed by ```delete 1``` deletes the 1st task in the results of the find command.

### Saving the data

Meow data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file

Meow data are saved as a txt file ```[JAR file location]/data/meow.txt```.
Advanced users are welcome to update data directly by editing that data file.




