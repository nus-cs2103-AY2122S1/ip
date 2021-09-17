# User Guide

## Contents page

[Features](#features)

[Usage](#usage)
- [help](#help---get-command-syntax)
- [list](#list---list-tasks-in-to-do-list)
- [todo](#todo---create-a-todo-task)
- [deadline](#deadline---create-a-deadline-task)
- [event](#event---create-an-event-task)
- [done](#done---mark-task-as-complete)
- [delete](#delete---delete-a-task)
- [find](#find---find-task)
- [bye](#bye---exit-program)

## Features

### Keep track of tasks

Lists all the tasks and if you have completed them.

### Various tasks supported
    
Supports basic to-do list tasks, events and deadlines.

### Local Storage

Your data is saved on your own computer and can be transferred
to other devices to be used.

## Usage

### `help` - Get command syntax

Gets the command syntax for the command, which include the command and
any inputs they require.

Example of usage: 

```
help <command name>
help deadline
```

Expected outcome:

Prints the syntax for the deadline command 
```
deadline <description> /by <dd-mm-yyyy>
```

### `list` - List tasks in to-do list

List all the tasks in the to-do list, with details on their:
- completion status 
- task type
- description
- Date associated with task (Deadline and event task only)

```
    [Task Shortform] [completion status] <description> (date)
```

Example of usage:

`list`

Expected outcome:

List of current items are printed
```
Here are the things in your list:
1. [T] [] Go to school
2. [E] [] Attend webinar (at: Oct 20 2021)
```

### `todo` - Create a todo task

Create a basic task that only has a description

Example of usage:

```
todo <description>
todo Beat covid
```

Expected outcome:

Creates a todo with the description provided.
```
[T][] Beat covid
```

### `event` - Create an event task

Create an event with the description and date provided.
Date is in the form dd-mm-yyyy

Example of usage:

```
event <description> /at <date>
event Apocalpyse /at 21-12-2012
```

Expected outcome:

Creates an event with the description and date provided
```
[E][] Apocalypse (at: Dec 21 2021)
```

### `deadline` - Create a deadline task

Create a deadline with the description and date provided.
Date is in the form dd-mm-yyyy

Example of usage:

```
deadline <description> /by <date>
deadline Renew Passport /by 05-01-2022
```

Expected outcome:

Creates a deadline with the description and date provided.
```
[D][] Apocalypse (by: Jan 5 2022)
```

### `done` - Mark task as complete

Mark a task in list as complete.

Example of usage:

```
done <task number>
done 1
```
Expected outcome:

Marks task 1 as complete
```
1. [T][X] Save Christmas 
```

### `delete` - Delete a task

Delete a task from the todo list.

Example of usage:

```
delete <task number
delete 3
```

Expected outcome:

Task 3 was deleted
```
1. [T][] Get Cookies
2. [T][] Get Jam
```

### `find` - Find task

Find tasks that contains keyword  in their description

Example of usage:

```
find <keyword>
find bob
```

Expected outcome:

Finds all tasks that contain keyword
```
1. [T][] Make bob a coffee
2. [D][] Find Bob (by: Dec 20 2021)
```

### `bye` - Exit program

Exits the program

Example of usage:

`bye`

Expected outcome:

Kermit window closes

