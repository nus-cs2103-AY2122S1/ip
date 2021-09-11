# User Guide - Duke
Duke is a task manager designed with the command line in mind. It is suitable for people who type fast and has a stylish user interface

## Quick start
1. Ensure you have Java 11 or above installed in your Computer.
1. Download the latest release from [here](https://github.com/yongxiangng/ip/releases/tag/A-Release).
1. Copy the file to the folder you want to use as the home folder for your Duke.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds after executing some commands.<br> ![Screenshot](Ui.png)
1. Type the command in the command box and press Enter to execute it. <br>
Some example commands you can try:
    - `list`: Lists all tasks.
    - `todo CS2103`: Adds a todo task with description CS2103.
    - `delete 1`: Deletes the first task.
    - `bye`: Closes Duke and exits the app.
1. Refer to the [Features](#features) below for details of each command.

## Features 
Notes on the command format:
    - Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo CS2103`.
    

### Listing all tasks: `list`

Shows a list of all tasks in Duke.

Format: `list`

### Adding a task: `todo` `deadline` `event` `period`

Adds a task to the list of tasks managed by Duke. Todo task contains a description of the task. Deadline task contains description of the task and the deadline of the task. Event task contains description of the task and the time of the event. Period task contains description of the task and the start, end time of the task. To see their usage, look under the `Usage` section.

Format: `todo DESCRIPTION`<br>
Format: `deadline DESCRIPTION /by TIME`<br>
Format: `event DESCRIPTION /at TIME`<br>
Format: `period DESCRIPTION /start STARTTIME /end ENDTIME`<br>

### Listing all tasks: `place`

Adds the name of a place associated to a task. Look under `Usage` section for how it is used.

Format: `place INDEX /at LOCATION`

### Sorting the tasks by description: `sort`

Sorts the tasks by their descriptions.

Format: `sort`

### Deleting a task: `delete`

Deletes a task from the list of tasks managed by Duke.

Format: `delete INDEX`

### Marking a task as done: `done`

Marks a task as done.

Format: `done INDEX`

### Finding a task by description: `find`

Finds a task by its description by filtering the list of tasks with the keyword.

Format: `find KEYWORD`

### Exiting the program: `bye`

Exits the program.

Format: `bye`

## Usage

### `list` - List tasks

Shows a list of all tasks inside Duke.

Example of usage: 

`list`

Expected outcome:

A list of tasks stored inside Duke.

```
1.[T][ ] CS2103
2.[E][ ] HelloEvent (at: Dec 13 2019)
```

### `todo` - Create todo task

Adds a todo task to be managed by Duke.

Example of usage: 

`todo CS2103`

Expected outcome:

Success message showing the todo task added and the number of tasks in the task list.

```
Got it. I've added this task:
  [T][ ] CS2103
Now you have 1 task in the list.
```

### `deadline` - Create deadline task

Adds a deadline task to be managed by Duke.

Example of usage: 

`deadline CS2103 /by 2021-08-09`

Expected outcome:

Success message showing the deadline task added and the number of tasks in the task list.

```
Got it. I've added this task:
  [D][ ] CS2103 (by: Aug 09 2021)
Now you have 1 task in the list.
```

### `event` - Create event task

Adds an event task to be managed by Duke.

Example of usage: 

`event CS2103 /at 2021-08-09`

Expected outcome:

Success message showing the event task added and the number of tasks in the task list.

```
Got it. I've added this task:
  [E][ ] CS2103 (at: Aug 09 2021)
Now you have 1 task in the list.
```

### `period` - Create period task

Adds a period task to be managed by Duke.

Example of usage: 

`period CS2103 /start 2021-08-09 /end 2021-08-10`

Expected outcome:

Success message showing the period task added and the number of tasks in the task list.

```
Got it. I've added this task:
  [P][ ] CS2103 (starting: Aug 09 2021, ending: Aug 10 2021)
Now you have 1 task in the list.
```

### `place` - Associate a task with a place

Adds a place to the task.

Example of usage: 

`place 1 /at NUS COM 2`

Expected outcome:

Success message showing the place added to the task.

```
Noted. I've added the place to the task:
  [T][ ] CS2103, Places: COM 2
Now you have 1 task in the list.
```

### `sort` - Sort the list

Sorts the list of tasks by their descriptions.

Example of usage: 

`sort`

Expected outcome:

A list sorted in order by description.

```
1.[T][ ] 1
2.[T][ ] 2
3.[T][ ] a
4.[T][ ] b
```

### `delete` - Delete task

Deletes a task from the list.

Example of usage: 

`delete 1`

Expected outcome:

Success message to let user know the task is deleted.

```
Noted. I've removed this task:
  [T][ ] CS2103
Now you have 1 task in the list.
```

### `done` - Set task to be done

Sets a task to be done from the task list.

Example of usage: 

`done 1`

Expected outcome:

Success message to let user know the task is marked as done.

```
Nice! I've marked this taskas done:
  [T][X] CS2103
```

### `find` - Filter list by keyword

Finds tasks with descriptions containing the keyword.

Example of usage: 

`find 2103`

Expected outcome:

Returns a list of tasks with descriptions containing the keyword.

```
Here are the matching tasks in your list.
1.[T][ ] CS2103
```
### `bye` - Exit the program

Exits the program.

Example of usage: 

`bye`

Expected outcome:

Bye message is displayed and program closes.

```
Bye. Hope to see you again soon!
```
