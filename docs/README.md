# User Guide

## Description

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

Duke is a Personal Assistant Chatbot that helps you keep track of your tasks! It is:
* Fast
* Lightweight
* Command Line Based
* Easy to use

## Quick Start
1. Ensure you have ```Java 11``` or above installed in your Computer.
2. Download the latest ```Duke.jar``` from [here](https://github.com/Jiwei99/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your AddressBook
4. Double-click on the file to start the app. Alternatively, you can also start the app by entering 
```java -jar Duke.jar``` into your terminal after navigating to the directory where the app is stored.

## Features 

1. ### Listing all tasks - `list`
2. ### Add a new task
   1. `todo`
   2. `deadline`
   3. `event`
3. ### Marking a task as done - `done`
4. ### Deleting a task - `delete`
5. ### Clearing all tasks - `clear`
6. ### Changing priority level of task - `priority`
7. ### Exiting the app - `bye`
   

## Usage

### `list`

Lists out all the tasks that is stored in Duke.

**Example of usage:** 

`list`

**Expected outcome:**

Duke shows a list of tasks that are tracked.

```
Here are the tasks in your list:
1. <Task 1>
```
---
### `todo`

Adds a todo task into Duke.

**Example of usage:**

`todo <task description>`

`todo <task description> /p <priority>`

**Expected outcome:**

The todo task specified will be added and saved in Duke.

```
Got it! I've added this task:
[T][ ][ ] <Task description>
Now you have <?> tasks in the list.
```
---
### `deadline`

Adds a deadline into Duke.

**Example of usage:**

`deadline <deadline description> /by <dd-MM-yyyy>`

`deadline <deadline description> /by <dd-MM-yyyy> /p <priority>`

**Expected outcome:**

The deadline specified will be added and saved in Duke.

```
Got it! I've added this task:
[D][ ][ ] <Task description> (by: <dd MMM yyyy>)
Now you have <?> tasks in the list.
```
---
### `event`

Adds an event into Duke.

**Example of usage:**

`event <event description> /at <dd-MM-yyyy HHmm>`

`event <event description> /by <dd-MM-yyyy HHmm> /p <priority>`

**Expected outcome:**

The event specified will be added and saved in Duke.

```
Got it! I've added this task:
[E][ ][ ] <Task description> (at: <dd MMM yyyy HH:mm>)
Now you have <?> tasks in the list.
```
---
### `done`

Marks a task as done.

**Example of usage:**

`done <task index>`

**Expected outcome:**

The task specified will be marked as completed.

```
Nice! I've marked this task as done:
[X] <Task description>
```
---
### `delete`

Deletes a task from Duke.

**Example of usage:**

`delete <task index>`

**Expected outcome:**

The task will be removed from Duke.

```
Noted. I've removed this task:
[T][ ][ ] <Task description>
Now you have <?> tasks in the list.
```
---
### `clear`

Clears all tasks from Duke. Resets the Duke chatbot.

**Example of usage:**

`clear`

**Expected outcome:**

All tasks previously entered will be deleted.

```
List is cleared!
```
---
### `priority`

Updates the priority level of a task.

**Example of usage:**

`priority <task index> <priority level>`

**Expected outcome:**

Priority level of the task specified will be updated

```
I've changed the priority level of this task
[T][ ][!] <task description>
```
---
### `bye`

Closes the Duke app.

**Example of usage:**

`bye`

**Expected outcome:**

Duke app closes and exits.
