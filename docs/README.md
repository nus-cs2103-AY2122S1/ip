# User Guide

_**Heavily inspired from AddresBook3 [SE-Edu User Guide](https://se-education.org/addressbook-level3/)**_

**Bern Chatbot** is a GUI desktop application for managing your tasks, optimised for use via a **Command Line Interface** (CLI). If you can type fast, Bern Chatbot can get your tasks planning done faster than traditional GUI apps.

* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure you have `Java 11` or above installed in your Computer
2. Download the latest `Bern` file [here](https://github.com/bernarduskrishna/ip)
3. Copy the file to the folder you want to use as the _home_ folder for your Bern chatbot
4. Double-click the file to start the app. The GUI that is similar to the picture below should appear in a few seconds.
   ![GUI](Ui.png)
5. A file called "savedList" would be created. This file serves as the place to save all the added tasks.
6. Type out the command in the reply box and press Enter / click Send to initiate a chat with Bern. Do check the [command summary](#command-summary) to have a thorough understanding of what Bern is capable of doing!
7. Refer to the [Features](#features) below for details of each command.

## Features 

### Exiting the Programme

Exits the program.

#### Format - `bye`

### Add a Todo Task

Adds a Todo task with a description

#### Format - `todo <description>`

Example of usage:

`todo do 2103 project`

Expected outcome:

Bern will indicate that the Todo task is added successfully.

```
Got it. I've added this task:
[T][ ] Do 2103 project
Now you have 1 task in the list
```

### Add an Event Task

Adds an Event task with a description and time in yyyy-MM-dd

#### Format - `event <description> /at <time>`

Example of usage:

`event project meeting /at 2020-01-15`

Expected outcome:

Bern will indicate that the Event task is added successfully.

```
Got it. I've added this task:
[E][ ] project meeting (at: Jan 15 2020)
Now you have 2 tasks in the list
```

### Add a Deadline Task

Adds a Deadline task with a description and deadline in yyyy-MM-dd

#### Format - `deadline <description> /by <deadline>`

Example of usage:

`deadline return book /by 2019-10-15`

Expected outcome:

Bern will indicate that the Deadline task is added successfully.

```
Got it. I've added this task:
[D][ ] return book (by: Oct 15 2019)
Now you have 3 tasks in the list
```

### Mark a Task as done

Marks any Task as done. A task is only considered done when marked manually.

#### Format - `done <index>`

Example of usage:

`done 3`

Expected outcome:

Bern will indicate that the Task has been marked done successfully.

```
Good job! I've marked this task as done:
[D][X] return book (by: Oct 15 2019)
```

### List all the Tasks added

Lists all tasks including their completion state.

#### Format - `list`

Expected outcome:

Bern will list down all the tasks.

```
1. [T][ ] Do 2103 project
2. [E][ ] project meeting (at: Jan 15 2020)
3. [D][X] return book (by: Oct 15 2019)
```

### Delete a Task

Deletes the task from the savedList file. Irreversible.

#### Format - `delete <index>`

Example of usage:

`delete 2`

Expected outcome:

Bern will indicate that the Task has been deleted successfully.

```
Noted! I've removed this task:
[E][ ] project meeting (at: Jan 15 2020)
```
### Find a Task based on their description

Finds all the Tasks which contain the specified word. It will not detect any matching substring, strictly matching words only.

#### Format - `find <word>`

Example of usage:

`find project`

Expected outcome:

Bern will tell you all the Tasks which contain the specified word.

```
Here are the matching tasks in your list:
1. [T][ ] Do 2103 project
```

## Command Summary

Name | Value &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
-------|-------------------

`bye` | Exits the programme <br> e.g. `bye`
`todo` | Adds a Todo task with a description <br> e.g. `todo do 2103 project`
`event` | Adds an Event task with a description and time in yyyy-MM-dd <br> e.g. `event project meeting /at 2020-01-15`
`deadline` | Adds a Deadline task with a description and deadline in yyyy-MM-dd <br> e.g. `event project meeting /at 2020-01-15`
`done` | Marks any Task as done <br> e.g. `done 3`
`list` | Lists all tasks including their completion state <br> e.g. `list`
`delete` | Deletes the task from the savedList file <br> e.g. `delete 2`
`find` | Find all the Tasks which contain the specified word <br> e.g. `find project`
