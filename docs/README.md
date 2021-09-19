# User Guide

_**Adapted from AddresBook3 [SE-Edu User Guide](https://se-education.org/addressbook-level3/)**_

**Dude Chatbot** is a GUI desktop chatbot app that has many Command Line Interface (CLI) commands. Dude can give you
reminder about your daily task at **00:00 AM** everyday if Dude is alive.

* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure you have `Java 11` or above installed in your Computer
2. Download the latest `Dude` file (which is `Dude-1.1.jar` version from [here](https://github.com/simonjulianl/ip))
3. Copy the file to the folder you want to use as the _home_ folder for your Dude chatbot
4. Double-click the file to start the app. The GUI that is similar to the picture below should appear in a few seconds.
   ![Dude gui](Ui.png)
5. The folder `data` would be created in the _home_ folder creating the serialized object of each `Task`
6. Type the command in the command box and press Enter / Send to chat with Dude. Check
   the [command summary](#command-summary) to play with Dude chatbot!
7. Refer to the [Features](#features) below for details of each command.

## Features

### Viewing help

Shows a message explaining list of available commands for Dude

#### `man` - list all available commands

Example of usage:

`man`

Expected outcome:

Dude will list all available outcomes.

```
1. [T][X] task 1
2. [T][ ] task 2 
```

### Exiting the Programme

Exits Dude chatbot

#### `bye` - exit the chatbot

Example of usage:

`bye`

Expected outcome:

Dude will exit immediately.

### Add a Todo Task

Add a todo task containing description

#### `todo <desc>` - add a todo task to the storage

Example of usage:

`todo do homework`

Expected outcome:

Dude will output a message that the task is added successfully.

```
Got it. I've added the task: 
   [T][] do homework
Now you have 7 tasks in the list.
```

### Add a Deadline Task

Add a deadline task containing description and deadline in dd/MM/yyyy format

#### `deadline <desc> /by <deadline>` - add a deadline task to the storage

Example of usage:

`deadline do homework /by 15/09/2021`

Expected outcome:

Dude will output a message that the deadline is added successfully.

```
Got it. I've added the task: 
   [T][] do homework
Now you have 7 tasks in the list.
```

### Add an Event Task

Add an event task containing description and the time of the event in dd/MM/yyyy format

#### `event <desc> /at <deadline>` - add an event task to the storage

Example of usage:

`event do homework /at 15/09/2021`

Expected outcome:

Dude will output a message that the event is added successfully.

```
Got it. I've added the task: 
   [T][] do homework
Now you have 7 tasks in the list.
```

### Mark a task as Done

Tasks are to be marked done manually (even though the deadline is over / the timing has passed)

#### `done <index>` - mark the task as done by index as shown by `list` command

Example of usage:

`done 1`

Expected outcome:

Dude will successfully mark the task as done

```
Nice! I've marked this task as done: 
   [X] do homework
```

### List all the tasks available

List all the tasks including events, todos, and deadlines whether they are done or not done

#### `list (date)` - list all the tasks available, possibly filter by date in dd/MM/yyyy format

Example of usage:

`list`, `list 11/11/2020`

Expected outcome:

Dude will list all the tasks available

```
1. [E][X] test (at: 10-10-2020)
2. [T][ ] do homework
```

### Delete a task

Delete a task from the list

#### `delete <index>` - delete a task based on the index found using `list` command

Example of usage:

`delete 1`

Expected outcome:

Dude will delete the task if the index if valid, else ignored

```
Noted. I've removed this task:
   [E][X] task (date)
Now you have 1 tasks in the list. 
```

### Find a list of tasks

Find a list of tasks from the list using description as the keyword

#### `find <description>` - find a list of tasks filtered by desc

Example of usage:

`find something`

Expected outcome:

Dude will list all the tasks whose descriptions contain those keywords

```
1. [E][ ] something (date) 
```

### Reminder

Dude has a daily reminder job that will reminder the user of the daily task to be performed on that day if any (this
task will be auto run at **00:00 AM** everyday

## Command Summary

Name | Value &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
-------|-------------------
`man` | Display the list of command and manuals of all function <br> e.g. `man`
`bye` | Exit the programme <br> e.g. `bye`
`list` | List all the tasks and deadline (Optionally by date in dd/MM/yyyy format) <br> e.g. `list`, `list <dd/MM/yyyy>`
`done` | Mark the task as done by supplying the index as shown by `list` <br> e.g. `done <index>`
`todo` | Create a todo task (with description) <br> e.g. `todo <desc>`
`event` | Create a new event task (with description and timing in dd/MM/yyyy format) <br> e.g. `event <desc> /at <dd/MM/yyyy>`
`deadline` | Create a new deadline task (with description and deadline in dd/MM/yyyy format) <br> e.g. `deadline <desc> /by <dd/MM/yyyy>`
`delete` | Delete a task by the index as shown by list <br> e.g. `delete <index>`
`find` | Find a list of tasks by description <br> e.g. `find <keyword>`

