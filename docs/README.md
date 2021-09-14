# User Guide

QuestPro is a desktop app for managing your daily quests,
optimized for use via a Command Line Interface (CLI).

- [Quick Start](#quick-start)
- [Features](#features)
    * [Adds a todo task](#adds-a-todo-task-todo)
    * [Adds a deadline task](#deletes-a-task-delete)
    * [Adds an event task](#adds-an-event-task-event)
    * [List all tasks](#lists-all-tasks-list)
    * [Delete task](#deletes-a-task-delete)
    * [Complete task](#indicates-a-task-as-completed-done)
    * [Find task with keyword](#find-tasks-with-the-keyword-find)
    * [Help](#help-help)
    * [Exit Duke](#exit-bye)
- [Command Summary](#command-summary)

<hr>

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest QuestPro.jar from [here](https://github.com/jiayi1129/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your quests.
4. Double-click the file to start the app.

<hr>

## Features

Tasks are categorized into 3 different types: `todo`, `deadline`, `event`.

### Adds a todo task: `todo` 

Format: `todo DESCRIPTION`

Example of Usage:

- `todo read book`

Expected outcome:
```
Got it. I've added this task:
    [T][ ] read book 
Now you have 1 tasks in your list.    
```

### Adds a deadline task: `deadline` 

Format: `deadline DESCRIPTION /by DATE TIME`<br>
`DATE is of format YYYY-MM-DD`<br>
`TIME is of format hh:mm`<br>
`TIME is optional.`

Example of usage:

`deadline return book /by 2021-09-15 18:00`

Expected outcome:
```
Got it. I've added this task:
[D][ ] return book (by: 15 September 2021 06:00 pm)
Now you have 2 tasks in your list.
```


### Adds an event task: `event` 

Format: `event DESCRIPTION /at DATE TIME`<br>
`DATE is of format YYYY-MM-DD`<br>
`TIME is of format hh:mm`<br>
`TIME is optional.`

Example of usage:

`event CS2103T Lecture /at 2021-09-17 10:00`

Expected outcome:
```
Got it. I've added this task:
[E][ ] CS2103T Lecture (at: 17 September 2021 10:00 am)
Now you have 3 tasks in your list.    
```

### Lists all tasks: `list` 

Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] read book 
2. [D][ ] return book (by: 15 September 2021 06:00 pm)
3. [E][ ] CS2103T Lecture (at: 17 September 2021 10:00 am)
```

### Deletes a task: `delete`

Format: `delete INDEX`

Example of usage:
`delete 3`

Expected outcome:
```
Noted. I've removed this task:
[E][ ] CS2103T Lecture (at: 17 September 2021 10:00 am)
Now you have 2 tasks in your list.
```

### Indicates a task as completed: `done`

Format: `done INDEX`

Example of usage:
`done 2`

Expected outcome:
```
Nice! I've marked this task as done: 
[D][X] return book (by: 15 September 2021 06:00 pm)
```

### Find tasks with the keyword: `find` 

Format: `find KEYWORD`

Example of usage:
`find read book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] read book 
```

### Help: `help`
Format: `help`

Example of usage: `help`

Expected outcome:
```
Try out these commands to get started!
todo
deadline
event
done
delete
find
```

### Exit: `bye`

Format: `bye`

Example of usage: `bye`

Expected outcome: Duke terminates.

<hr>

## Command Summary

Feature | Command
------------ | -------------
Create a todo task | `todo DESCRIPTION`
Create a deadline task | `deadline DESCRIPTION /by DATE TIME`
Create an event task | `event DESCRIPTION /at DATE TIME`
Delete a task | `delete INDEX`
Complete a task | `done INDEX`
Find tasks using keyword | `find KEYWORD`
Help | `help`
Exit | `bye`