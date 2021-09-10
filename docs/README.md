# LOOK User Guide

## Content

- [Quick Start](#quick-start)
- [Features](#features)
  - [todo](#todo---add-a-todo-task)
  - [deadline](#deadline---add-a-deadline-task)
  - [event](#event---add-an-event-task)
  - [list](#list---show-all-the-list-of-all-tasks)
  - [done](#done---mark-a-task-as-done)
  - [delete](#delete---delete-a-task-from-the-list)
  - [undo](#undo---undo-the-previous-command)
  - [redo](#redo---redo-the-previous-command)
  - [bye](#bye---close-the-program)

- [Command Summary](#command-summary)
- [Reference](#reference)

---
## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.


2. Download the latest `look.jar` from [here](https://github.com/wyrchris/ip/releases/tag/A-Jar)


3. Copy the file to the folder you want to use as the _home folder_ for your LOOK.


4. Double-click the file to start the app.


5. Type the command into the text box at the bottom to start using. Some example commands are below:
   - `list`: List out all the task that have been inputted.
   - `todo`: Add a new todo task.
   - `done 2`: Mark the task at index 2 as completed.
   - `undo`: Undo the previous action

---
## Features 

1. ### ToDo/Deadline/Event

   - Add a new todo/deadline/event task to your list of task.

2. ### List all task

   - List all the task that was inputted into LOOK.

3. ### Delete task

   - Delete a task from your list of task.

4. ### Mark task

   - Mark a task from your list of task as completed.

5. ### Undo/Redo action

   - Undo/Redo the latest action that was done.
---
## Usage

---
### `todo` - Add a todo task

Add a todo task with the given description to our list.

Format: `todo DESCRIPTION`

Example of usage: 

`todo task1`

Expected outcome:

The todo task will be added into our list and shows the current count of task.

```
    Got it. I've added this task:
        [T][ ] task1
    Now you have 2 tasks in the list.
```
---

### `deadline` - Add a deadline task

Add a deadline task with the given description and deadline date to our list.

Format: `deadline DESCRIPTION /by DATE`

Example of usage:

`deadline task1 /by Next Monday`

Expected outcome:

The deadline task will be added into our list and shows the current count of task.

```
    Got it. I've added this task:
        [D][ ] task1 (by: Next Monday)
    Now you have 2 tasks in the list.
```
---
### `event` - Add an event task

Add an event task with the given description and event date to our list.

Format: `event DESCRIPTION /at DATE`

Example of usage:

`event task1 /at Next Monday`

Expected outcome:

The event task will be added into our list and shows the current count of task.

```
    Got it. I've added this task:
        [E][ ] task1 (at: Next Monday)
    Now you have 2 tasks in the list.
```
---
### `list` - Show all the list of all tasks

Show all the tasks that have been added into the lists

Format: `list`

Expected outcome:

```
    Here are the tasks in your list:
        1. [E][ ] task1 (at: Next Monday)
        2. [D][X] task2 (by: Next Tuesday)
        3. [T][ ] task3
```
---
### `done` - Mark a task as done

Mark the tasks at the index provided as done.

Format: `done INDEX`

Example of usage:

`done 2`

Expected outcome:

```
    Nice! I've marked this task as done:
        [D][X] task2 (by: Next Tuesday)
```
---
### `delete` - Delete a task from the list

Delete the tasks at the index provided from the list.

Format: `delete INDEX`

Example of usage:

`delete 2`

Expected outcome:

```
    Noted. I've removed this task:
        [D][X] task2 (by: Next Tuesday)
    Now you have 4 tasks in the list.
```
---
### `undo` - Undo the previous command

Undo the previous command and show the list of tasks in the previous state.

Format: `undo`

Expected outcome:

```
    Last command has been undone. The tasks is now this:
        1. [E][ ] task1 (at: Next Monday)
        2. [D][X] task2 (by: Next Tuesday)
        3. [T][ ] task3
```
---
### `redo` - Redo the previous command

Redo the previous command and show the list of tasks in the previous state.

Format: `redo`

Expected outcome:

```
    Last command has been redone. The tasks is now this:
        1. [E][ ] task1 (at: Next Monday)
        2. [D][X] task2 (by: Next Tuesday)
        3. [T][ ] task3
```
---
### `bye` - Close the program

Close the program.

Format: `bye`

---
## Command Summary
Command|Format, Examples
---|---
ToDo|`todo DESCRIPTION` <br> e.g. todo task1
Deadline|`deadline DESCRIPTION` <br> e.g. deadline task2 /by Next Monday
Event|`event DESCRIPTION` <br> e.g. deadline task2 /at Next Tuesday
List|`list`
Done|`done INDEX` <br> e.g. done 2
Delete|`delete INDEX` <br> e.g. delete 2
Undo|`undo`
Redo|`redo`
Close|`bye`
---
##Reference
AB3 User Guide: https://se-education.org/addressbook-level3/UserGuide.html#features