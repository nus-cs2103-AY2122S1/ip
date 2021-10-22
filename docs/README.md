# Duke Pro MAX (TM) User Guide

## Table of Contents

- [Duke Pro MAX (TM) User Guide](#duke-pro-max-tm-user-guide)
	- [Table of Contents](#table-of-contents)
	- [About The Project](#about-the-project)
	- [Features](#features)
		- [3 Types of Tasks](#3-types-of-tasks)
		- [Pretty User Interface](#pretty-user-interface)
		- [Command Line Interface Backend](#command-line-interface-backend)
	- [Usage](#usage)
		- [`todo` - Creates a todo task](#todo---creates-a-todo-task)
		- [`event` - Creates an event task](#event---creates-an-event-task)
		- [`deadline` - Creates a deadline task](#deadline---creates-a-deadline-task)
		- [`list` - Lists all ongoing tasks](#list---lists-all-ongoing-tasks)
		- [`done` - Marks a task as complete](#done---marks-a-task-as-complete)
		- [`delete` - Deletes a task](#delete---deletes-a-task)
		- [`addtag` - Adds a tag to a task](#addtag---adds-a-tag-to-a-task)
		- [`removetag` - Removes a tag from a task](#removetag---removes-a-tag-from-a-task)
		- [`bye` - Quits the application](#bye---quits-the-application)
	- [Contact](#contact)

## About The Project

There are many To-do applications out there, but there are few that has the ability to keep track of different types of events. Even more rare, are To-do applications that have a CLI for Pro user to make use of. That's where our Pro MAX (TM) moniker comes from.

## Features

### 3 Types of Tasks

We have Todos, Events and Deadlines that all have different information captured for the user.

### Pretty User Interface

The user interface is designed for beginners and professionals alike. Duke Pro MAX (TM) has a cute and accessible interface which prompts the user should they make mistake, but also being able to handle advanced commands.

### Command Line Interface Backend

Are you a power user that loves to automate tasks with scripts? Or are you a 1337 hacker that hates using Graphical User Interfaces? Or perhaps you are a UI designer and hate our UI? Well, Duke Pro MAX is the app for you. The CLI backend allows you to automate to your heart's content, get things done at the speed of your typing, or even create a frontend wrapper.

## Usage

### `todo` - Creates a todo task

Creates a todo task with the name specified by the user.

Example of usage:

`todo some work`

Expected outcome:

A task with the content "some work" will be created and added to the list of tasks:

```
Got it. I've added this task:
    [T][ ] some work []
You now have 5 tasks in the list.
```

### `event` - Creates an event task

Creates an event task with the name and time specified by the user.

Example of usage:

`event lecture /at tomorrow morning`

Expected outcome:

A task with the content of "lecture" and time of "tomorrow morning" will be created and added to the list of tasks:

```
Got it. I've added this task:
    [E][ ] lecture [] (at: tomorrow morning)
You now have 6 tasks in the list.
```

### `deadline` - Creates a deadline task

Creates a deadline task with the name and date specified by the user.

Example of usage:

`deadline homework /by 2021-06-13`

Expected outcome:

A task with the content of "homework" and date of "13th June 2021" will be created and added to the list of tasks:

```
Got it. I've added this task:
    [D][ ] homework [] (by: 13 Jun 2021)
You now have 7 tasks in the list.
```

### `list` - Lists all ongoing tasks

Displays a list of all tasks.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] some work []
2.[E][ ] lecture [] (at: tomorrow morning)
3.[D][ ] homework [] (by: 13 Jun 2021)
```

### `done` - Marks a task as complete

Marks the task that the user specified as completed.

Example of usage:

`done 2`

Expected outcome:

The task at position 2 of the list will be marked as completed:

```
Nice! I've marked this task as done:
[E][X] lecture [] (at: tomorrow morning)
```

### `delete` - Deletes a task

Deletes the task that the user specified.

Example of usage:
`delete 1`

Expected outcome:

The task as position 1 of the list will be marked as completed:

```
Noted. I've removed this task:
    [T][ ] some work []
Now you have 2 tasks in the list.
```

### `addtag` - Adds a tag to a task

The given tag will be added to the task specified by the user.

Example of usage:
`addtag 2 CS2103`

Expected outcome:

A tag of CS2103 will be added to the task at position 2 of the list:

```
Added #CS2103:
    [D][ ] homework [CS2103] (by: 13 Jun 2021)
```

### `removetag` - Removes a tag from a task

The given tag will be removed from the task specified by the user.

Example of usage:
`removetag 2 CS2103`

Expected outcome:

The tag of CS2103 will be removed from the taks at position 2 of the list:

```
Removed #CS2103:
    [D][ ] homework [] (by: 13 Jun 2021)
```

### `bye` - Quits the application

Ends Duke Pro MAX (TM)

Example of usage:
`bye`

Expected of outcome:

Duke Pro MAX (TM) will be terminated.

## Contact

Lee Yat Bun - [Github](https://github.com/yatbun/)

Project [link](https://github.com/yatbun/ip)
