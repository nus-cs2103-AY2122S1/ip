## User Guide
### Table of contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
- [Commands and Usage](#commands)
    * [Create a todo](#todo)
    * [Create an event](#event)
    * [Create a deadline](#deadline)
    * [List all tasks](#list)
    * [Delete task](#delete)
    * [Complete task](#done)
    * [Find task with keyword](#findtask)
    * [Reschedule task](#snoozetask)
    * [Exit Duke](#bye)
- [Command Summary](#summary)

## Introduction<a name="introduction"></a>
Duke is a task management desktop application to track your tasks. It has a variety of features to help you better manage your tasks. 
Duke is optimized for users who prefer to type out their commands rather than 

## Quick Start<a name="quick-start"></a>
Ensure you have Java 11 or above installed in your Computer.
Download the jar file [here](https://github.com/ChuangZheQuan/ip/releases/tag/A-Release)!
Copy the file to the folder you want to use as the home folder for your todo list.
Double-click the file to start the app. The GUI should appear in a few seconds.
Alternatively (or if double-clicking doesn't work), you can open your terminal/console, move to the directory of the file and type `java -jar duke.jar` to run the file.

## Features<a name="features"></a>

### Create Task

Tasks are categorised into 3 different types: `todo`, `deadline`, `event`.
`todo` contains a description of the task to be done. `deadline` contains a description and a deadline of the task.
`event` contains a description, and the time the event is happening.

### Delete Tasks

Delete any task that you no longer want to keep.

### Complete your tasks

You can set a task as `done`.

### Find
You can `find` tasks that contains the input you have typed.

### List all the tasks stored
You can `list` all the tasks that you have recorded.

### Snooze

You can `snooze` a task to reschedule it to a different period. 

## Commands and Usage<a name="commands"></a>


### `todo` - Creates a todo task<a name="todo"></a>

Format: `todo {description of the task}`

Example of usage:

`todo homework`

Expected outcome:
```
Got it. I've added this task:
    [T][ ] homework 
Now you have 1 tasks in your list.    
```

### `event` - Creates an event<a name="event"></a>

Format: `event {description} /at {time with format: dd/mm/yyyy hhmm}`
`hhmm is optional.`

Example of usage:

`event party /by 2/2/2022 2222`

Expected outcome:
```
Got it. I've added this task:
    [E][ ] party (at: 02 Feb 2022 22:22)
Now you have 3 tasks in your list.    
```


### `deadline` - Creates a task with a deadline<a name="deadline"></a>

Format: `deadline {description} /by {time with format: dd/mm/yyyy hhmm}`
`hhmm is optional.`

Example of usage:

`deadline homework /by 2/2/2022 2222`

Expected outcome:
```
Got it. I've added this task:
    [D][ ] homework (by: 02 Feb 2022 22:22)
Now you have 2 tasks in your list.    
```


### `list` - Lists all tasks added<a name="list"></a>

Format: `list`

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
   1. [T][ ] homework 
   2. [E][ ] party (at: 02 Feb 2022 22:22)
   3. [D][ ] homework (by: 02 Feb 2022 22:22)
```

### `delete` - Deletes a task<a name="delete"></a>

Format: `delete {index of task}`

Example of usage:
`delete 3`

Expected outcome:
```
Noted. I've removed this task:
    [E][ ] party (at: 02 Feb 2022 22:22)
Now you have 2 tasks in your list.
```

### `done` - Sets a task as completed<a name="done"></a>

Format: `done {index of task}`

Example of usage:
`done 2`

Expected outcome:
```
Nice! I've marked this task as done: 
    [D][X] homework (by: 2/2/2022 22:22)
```

### `find` - Finds tasks with the keyword <a name="findtask"></a>

Format: `find {keyword}`

Example of usage:
`find homework`

Expected outcome:
```
Here are the matching tasks in your list:
    1. [D][X] homework (by: 2/2/2022 22:22)
```

### `snooze` - Reschedules your task to a different time <a name="snoozetask"></a>

Format: `snooze {index of task} /to {time with format: dd/mm/yyyy hhmm}`
`hhmm is optional.`

Example of usage:
`snooze 1 /to 2/2/2122`

Expected outcome:
```
You have snoozed this task:
    [D][ ] homework (by: 2/2/2022 22:22) to:
    [D][ ] homework (by: 2/2/2122 00:00)
```
### `bye` - Exit Duke<a name="bye"></a>

Format: `bye`

Example of usage: `bye`

Expected outcome: Duke terminates.

Command Summary<a name="summary"></a>

Feature | Command
------------ | -------------
Create a todo task | `todo {description of the task}`
Create a deadline task | `deadline {description} /by {time with format: dd/mm/yyyy hhmm} hhmm is optional.`
Create an event task | `event {description} /at {time with format: dd/mm/yyyy hhmm} hhmm is optional.`
Delete a task | `delete {index of task}`
Complete a task | `done {index of tasks}`
Find tasks using keyword | `find {keyword}`
Reschedule a task | `snooze {index of task} /to {time with format: dd/mm/yyyy hhmm} hhmm is optional.`
Exit Duke | `bye`