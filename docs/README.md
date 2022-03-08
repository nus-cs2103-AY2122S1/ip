# User Guide

Neko Duke is a task manager that allows you to manage your tasks through quick, easy-to-use commands.

## Getting Started
1. Download it from [here](https://github.com/weiquu/ip/releases/tag/A-Release).
2. Double-click to run the JAR file, or run it using the `java -jar` command.
3. A local storage file will be created in your home directory (to store information on your tasks).
4. Let Neko Duke manage your tasks for you!

## Features 

### Task Management

Let Neko Duke manage your tasks for you! Simply add a task to your list and update it when completed.

### Multiple Types of Tasks

Want to add details to certain kinds of tasks by adding dates and events? We've 3 different kinds of tasks for you!

### Simple Commands
Neko Duke functions on simple, easy-to-remember commands. Need help? Don't worry! We've a command for that too (:

### Saves Data
Your task data is saved to your computer automatically, so you can exit Neko Duke whenever you wish without needing to
worry about saving your data.

## Usage

### `help` - Provides a list of available commands

Lists all available commands, how to use them, and what they do.

Example of usage:

`help`

Expected outcome:

A numbered list of all available commands

### `list` - Lists all tasks

Lists all tasks and their current status.

Example of usage: 

`list`

Expected outcome:

A numbered list of all tasks, e.g.

```
--------------------------------------------
    Here are the tasks in your list:
    1. [T][] task_desc
    2. [D][X] task_desc (by: 2-5-2022)
--------------------------------------------
```

### `todo <task_name>` - Creates a new ToDo task

Adds a new ToDo task to the task list with the specified task name.

Example of usage:

`todo call weiq`

Expected outcome:

A confirmation message of the task added as well as the updated total number of tasks, e.g.

```
--------------------------------------------
    Got it. I've added this task:
      [T][] call weiq
    Now you have 2 tasks in the list.
--------------------------------------------
```

### `event <task_name> /at <date>` - Creates a new Event task

Adds a new Event task to the task list with the specified task name and when the event will occur.

Example of usage:

`event justin bday party /at this sat`

Expected outcome:

A confirmation message of the task added as well as the updated total number of tasks, e.g.

```
--------------------------------------------
    Got it. I've added this task:
      [E][] justin bday party (at: this sat)
    Now you have 3 tasks in the list.
--------------------------------------------
```

### `deadline <task_name> /by <date_time>` - Creates a new Deadline task

Adds a new Deadline task to the task list with the specified task name and the date and time of which the task is due.
Note that specifying a recognisable date format (yyyy-mm-dd) and time format (24-hour) will allow Neko Duke to re-format
it into a fuller format.

Example of usage:

`deadline CS2103 assignment /by 2021-09-17 2359 but finish earlier`

Expected outcome:

A confirmation message of the task added as well as the updated total number of tasks, e.g.

```
--------------------------------------------
    Got it. I've added this task:
      [D][] CS2103 assignment (by: Sep 17 
2021 11:59pm but finish earlier)
    Now you have 4 tasks in the list.
--------------------------------------------
```

### `find <search_term>` - Finds specific tasks

Finds all tasks that contain the specified search term.

Example of usage:

`find justin`

Expected outcome:

A numbered list of all tasks that contain the specified search term in their name, e.g.

```
--------------------------------------------
    Here are the matching tasks in your list:
    1. [E][] justin bday party (at: this sat)
    2. [T][] justin present
--------------------------------------------
```

### `done <task_index>` - Marks a task as done

Marks the task at that index as done.

Example of usage:

`done 2`

Expected outcome:

A confirmation message of the task marked as done, e.g.

```
--------------------------------------------
    Nice! I've marked this task as done:
      [T][X] call weiq
--------------------------------------------
```

### `delete <task_index>` - Deletes a task

Deletes the task at that index.

Example of usage:

`delete 2`

Expected outcome:

A confirmation message of the task deleted, e.g.

```
--------------------------------------------
    Noted. I've removed this task:
      [T][X] call weiq
    Now you have 3 tasks in the list.
--------------------------------------------
```

### `bye` - Exits the program

Exits the program (after a 2-second delay).

Example of usage:

`bye`

Expected outcome:

The program exits
