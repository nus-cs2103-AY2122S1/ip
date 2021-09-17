# User Guide

SaitamaChat is a **desktop app for managing tasks, optimized for use
via a Command Line Interface** (CLI) while still having the benefits
of a Graphical User Interface (GUI). You can keep track of all the
tasks you have using this app!

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest SaitamaChat.jar from [here](https://github.com/xiangjunn/ip/releases).

3. Copy the file to the folder you want to use as the home folder
for your Duke app.

4. Double-click the file to start the app. The GUI similar to the below 
should appear in a few seconds.

![screenshot of the app](Ui.png "screenshot of the app")
   
5.Type the command in the command box and press Enter to execute it.

Some example commands you can try:

- `todo CS2103T iP` : Add a task called "CS2103T iP".

- `list` : Lists all tasks.

- `delete 3` : Deletes the 3rd task shown in the task list.

- `done 1` : Mark the 1st task shown in the task list as done.

## Features 

### Feature-Add

Add a new task to the list. There are three types of task,
namely ToDo, Deadline and Event. The commands to add each
of the task are `todo`, `deadline` and `event` respectively.

### Feature-List

List all the tasks the user currently have.

### Feature-Done

Mark a task as done.

### Feature-Delete

Delete a task from the list.

### Feature-Find

Find a task by searching for a keyword.

### Feature-Bye

Exits the app.

### Feature-Friendlier syntax

Provide shorter aliases for keywords.

Refer to the Usage below for details of each command.

## Usage

**Notes about the command format:**

- Words in `UPPER_CASE` are the parameters to be supplied by
the user. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a
parameter which can be used as `todo CS2103T quiz`.

- Extraneous parameters for commands that do not take in
  parameters (such as `list` and `bye`) will be
  ignored. e.g. if the command specifies `list 123`, it will
  be interpreted as `list`.
  
### `todo` - Add a ToDo task

Add a ToDo task to the task list.

format : `todo DESCRIPTION`

Example of usage: 

`todo wash clothes`

Expected outcome:

```
Got it. I've added this task:
        [T][] wash clothes
    Now you have 3 tasks in the list.
```

### `deadline` - Add a Deadline task

Add a Deadline task to the task list.

format : `deadline DESCRIPTION /by DATE TIME`

**Note:**
- DATE is in `yyyy-MM-dd` format. e.g. 2021-11-11
- TIME is in `hh:mm` format. e.g. 23:11

Example of usage:

`deadline iP submission /by 2021-09-16 23:59`

Expected outcome:

```
Got it. I've added this task:
        [D][] iP submission (by: Sep 16 2021
23:59)        
    Now you have 3 tasks in the list.
```

### `event` - Add an Event task

Add an Event task to the task list.

format : `event DESCRIPTION /at DATE DETAILS`

**Note:**
- DATE is in `yyyy-MM-dd` format. e.g. 2021-12-12

Example of usage:

`event Apple event /at 2021-09-15 Marina Bay Sands from 2pm to 5pm`

Expected outcome:

```
Got it. I've added this task:
        [E][] Apple event (at: Sep 15 2021
Marina Bay Sands from 2pm to 5pm)        
    Now you have 4 tasks in the list.
```

### `list` - List all the current tasks
List all the current tasks to the user in the order they were added.

format : `list`

Example of usage:

`todo workout` to add a ToDo task

`list` show the latest tasks to the user

Expected outcome:

```
Here are the matching tasks in your list:
    1.[T][X] CS2103T iP
    2.[D][] CS2100 Assignment (by Sep 15 2021
13:00)
    3.[D][] iP submission (by: Sep 16 2021 23:59)
    4.[E][] Apple event (at: Sep 15 2021 Marina
Bay Sands from 2pm to 5pm)
    5.[T][] workout 
```

### `done` - Marks a task as done

Marks a task from the task list as done.

format : `done INDEX`

Example of usage:

`done 2` Mark the 2nd task shown in the task list as done.

Expected outcome:

```
Nice! I've marked this task as done:
        [D][X] CS2100 Assignment (by: Sep 15
2021 13:00)
```

### `delete` - Deletes a task
Deletes a task from the task list.

format : `delete INDEX`

Example of usage:

`delete 5` Delete the 5th task shown in the task list. 

Expected outcome:

```
Noted. I've removed this task:
        [T][] workout
    Now you have 4 tasks in the list.
```

### `find` - Find a task
Find a task by searching for a keyword.

format : `find KEYWORD`

Example of usage:

`find Sep 15` Show a list of tasks matching the keyword "Sep 15"

Expected outcome:

```
Here are the matching tasks in your list:
    1.[D][] CS2100 Assignment (by Sep 15 2021
13:00)
    2.[E][] Apple event (at: Sep 15 2021 Marina
Bay Sands from 2pm to 5pm)
```

### `bye` - Exit the app

Show a display message when user enters the command and exit the app after some delay.

format : `bye`

Example of usage:

`bye` Exit the app after a goodbye message

Expected outcome:

```
Hope to see you again!! ^_^
```

### `Friendlier syntax` - Shorter aliases

Provide shorter aliases for keywords.

Shorter aliases for respective keywords:

- `todo` : `t`
- `deadline` : `d`
- `event` : `e`
- `delete` : `del`
- `find` : `f`
- `list` : `l`

## Saving the data

Tasks data are saved in the hard disk automatically after any command
that changes the data. There is no need to save manually.

## Command summary
|Action|Format|
|------|------|
|todo|`todo DESCRIPTION`<br>`t DESCRIPTION`|
|deadline|`deadline DESCRIPTION /by DATE TIME`<br>`d DESCRIPTION /by DATE TIME`|
|event|`event DESCRIPTION /at DATE DETAILS`<br>`e DESCRIPTION /at DATE DETAILS`|
|list|`list`<br>`l`|
|done|`done INDEX`|
|delete|`delete INDEX`<br>`del INDEX`|
|find|`find KEYWORD`<br>`f KEYWORD`|
|bye|`bye`|

