# User Guide

## Features 

### GUI

The desktop application comes with a user-friendly User Interface.

### List

View all tasks(ToDos, Deadlines, and Events) and respective task status.

### Add

Add tasks(ToDos, Deadlines, and Events).

### Delete

Delete a specific task.

### Find

Find relevant tasks using matching keywords. 

### Done

Mark a task as done to maintain an updated task list. 

### Local Storage

Save tasks in a local file. 

## Usage

### `todo <name>` - Add a ToDo

Adds a ToDo type task to your task list. 

Example of usage: 

`todo read book`

Expected outcome:

The ToDo is added to your list.

```
    Got it. I've added this task:
[T][] read book
```
### `deadline <name> /by YYYY-MM-DD` - Add a Deadline

Adds a Deadline type task to your task list.

Example of usage:

`deadline hw /by 2019-09-18`

Expected outcome:

The Deadline is added to your list.

```
    Got it. I've added this task:
[D][] hw (by: Sep 18 2019)
```
### `event <name> /at YYYY-MM-DD` - Add an Event

Adds an Event type task to your task list.

Example of usage:

`event concert /at 2019-09-18`

Expected outcome:

The Event is added to your list.

```
    Got it. I've added this task:
[E][] concert (at: Sep 18 2019)
```
### `list` - List all available tasks

Lists all tasks that have been added. ToDo tasks 
start with [T], Deadline tasks with [D], and Event 
tasks with [E]. Tasks that have been already completed
are displayed with [X].

Example of usage:

`list`

Expected outcome:

The task list is displayed.

```
    Here is your list:
[T][] read book
[D][] hw (by: Sep 18 2019)
[E][] concert (at: Sep 18 2019)
```
### `done <number>` - Mark a task as done

Marks the specified numbered task as done.

Example of usage:

`done 1`

Expected outcome:

The first task is marked as done. 

```
    Got it. I've marked this task as done:
[T][X] read book
```
### `delete <number>` - Delete a task

Deletes the specified numbered task.

Example of usage:

`delete 1`

Expected outcome:

The first task is deleted.

```
    Got it. I've deleted this task:
[T][X] read book
Now you have 2 tasks remaining in your list!
```
### `find <query>` - Find the task containing the given query

Finds the relevant task matching the input keyword. 

Example of usage:

`find concert`

Expected outcome:

The task containing the read keyword is returned.

```
Here are the matching tasks in your list:
1. [E][] concert (at: Sep 18 2019)
```
### `bye` - Exit the application

Closes GUI and exits the application. 

Example of usage:

`bye`

Expected outcome:

The exit message is printed and the application window closes.

```
Bye. Hope to see you again soon! Just a little
reminder : YOU ARE AWESOME - THAT'S WHAT SHE SAID :))
```
## Acknowledgements

The entities used in this app to represent the 
bot and the user are both fictional characters
from the show 'The Office'.

The images used are from the following sources:

* The bot: [Michael Scott's character from 'The Office'](https://www.reddit.com/r/DunderMifflin/comments/50ozx4/my_favorite_michael_scott_face/)
* The user: [Jim Halpert's character from 'The Office'](https://in.pinterest.com/pin/448882287849921609/?amp_client_id=CLIENT_ID(_)&mweb_unauth_id={{default.session}}&simplified=true)


