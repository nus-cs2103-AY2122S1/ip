# User Guide

Duke is a command line interface oriented task manager. It comes with a GUI and support for multiple
types of tasks.

- [Quick Start](#Quick Start)
- [Features](#Features)
- [Usage](#Usage)
- [Command Summary](#Command Summary)

---

## Quick Start

1. Ensure you have Java 11 or above installed.
2. Download the latest release from [here](https://github.com/caipng/ip).
3. Move the downloaded `jar` file into a folder of your choice. This folder will be used to store your tasks.
4. Run the app.
5. You should see something like the screenshot below. Type your command in the textbox and hit enter. You may find a list of commands in this user guide.

![Screenshot](Ui.png)

---

## Features

### Tasks

Duke comes with support for various types of tasks:

| Type | Description |
| ---- | ----------- |
| Todo | A task to be completed |
| Event | A task associated with a specific date |
| Deadline | A task to be completed by a date |

### Delete or Mark as Done

You are able to either delete a task or mark it as completed.

### Find task

You can search for a task in the list using its description.

### List tasks

You can view all of your tasks in a numbered list.

---

## Usage

### Adding a Todo: `todo <description>`

Adds a todo to your task list.

Example Usage: `todo finalize IP`

Expected Outcome:
```
I've added this task:
	[T] [ ] finalize IP
Now you have 1 tasks in the list.
```

Aliases: `todo`, `t`

### Adding a Deadline: `deadline <description> /by YYYY-MM-DD`

Adds a deadline with the specified date to your task list.

Example Usage: `deadline submit IP /by 2021-09-19`

Expected Outcome:
```
I've added this task:
	[D] [ ] submit IP (by: Sep 19 2021)
Now you have 1 tasks in the list.
```

Aliases: `deadline`, `d`

### Adding an Event: `event <description> /at YYYY-MM-DD`

Adds an event to your task list.

Example Usage: `event celebrate IP over /at 2021-09-20`

Expected Outcome:
```
I've added this task:
	[E] [ ] celebrate IP over (at: Sep 20 2021)
Now you have 1 tasks in the list.
```

Aliases: `event`, `e`

### List all tasks: `list` 

Lists all your tasks.

Example Usage: `list`

Expected Outcome:
```
Here are the tasks in your list:
1. [T] [ ] finalize IP
2. [D] [ ] submit IP (by: Sep 19 2021)
3. [E] [ ] celebrate IP over (at: Sep 20 2021)
```

Aliases: `list`, `ls`

### Delete task: `delete <index>`

Deletes the task at the given index. This index corresponds to the number besides the task in `list`.

Example Usage: `delete 1`

Expected Outcome:
```
Ok, I've removed this task:
	[T] [X] finalize IP
Now you have 1 tasks in the list.
```

Aliases: `delete`, `remove`, `rm`, `del`

### Mark task as done: `done <index>`

Marks the task at the given index as done. This index corresponds to the number besides the task in `list`.

Example Usage: `done 1`

Expected Outcome:
```
Good job! I've marked this task as done:
	[D] [X] submit ip (by: Sep 17 2021)
```

Aliases: `done`, `do`

### Find task: `find <description>`

Find tasks containing the given description. 

Example Usage: `find IP`

Expected Outcome:
```
Here are the matching tasks in your list:
1. [T] [ ] finalize IP
2. [D] [ ] submit IP (by: Sep 19 2021)
3. [E] [ ] celebrate IP over (at: Sep 20 2021)
```

Aliases: `find`, `search`, `/`

### Exit: `exit`

Exits the application.

Example Usage: `exit`

Expected Outcome:
```
Bye bye! Hope you have a productive day :)
```

Aliases: `exit`, `bye`, `quit`, `q`

---

### Command Summary

| Action | Format |
| ------ | ------ |
| Add Task | `todo <description>`<br>`deadline <description> /by YYYY-MM-DD`<br>`event <description> /at YYYY-MM-DD` |
| List | `list` |
| Delete | `delete <index>` |
| Mark as Done | `done <index>` |
| Find | `find <description>` |
| Exit | `exit`