# User Guide

Duke is a desktop task manager app, optimized for use via a 
Command Line Interface (CLI) while having the benefits of a 
Graphical User Interface (GUI). If you can type fast, Duke can 
get your task list arranged faster than traditional GUI apps.

## Features 

### Create

Populate your task list with todos, events and deadline.  

### Read

View your tasks in total, search for keywords, or get reminders for 
upcoming deadlines.

## Usage

### `help` - Viewing help

Shows a message explaining how to use Duke.

Example of usage: 

`help`

Expected outcome:

List of commands.

```
expected output
```

### `todo` - Adding a todo task

Adds a new todo task to the task list.

Example of usage:

`todo borrow books`

Expected outcome:

New todo task is added to the task list. The description of the todo task is 
'borrow books'.

```
Task successfully added: [T][ ] borrow books
```

### `deadline` - Adding a deadline task

Adds a new deadline task to the task list.

Example of usage:

`deadline return books /by 2021-09-23`

Expected outcome:

New deadline task is added to the task list.
The description of the deadline task is 'return books'.
The time to complete the deadline is 2021-09-23 (YYYY-MM-DD).

```
Task successfully added: [D][ ] return books (by: Sep 23 2021)
```

### `event` - Adding an event task

Adds a new event task to the task list.

Example of usage:

`event cs2103T meeting /at 2021-09-24`

Expected outcome:

New event task is added to the task list.
The description of the event task is 'cs2103T meeting'.
The time to complete the event is 2021-09-24 (YYYY-MM-DD).

```
Task successfully added: [E][ ] cs2103T meeting (at: Sep 24 2021)
```

### `done` - Marking a task as done

Marks the task at the specified index as done.

Example of usage:

`done 3`

Expected outcome:

The task in the task list at index 3 is marked as done.

```
Noted. I've marked this as done: [E][X] cs2103T meeting (at Sep 24 2021)
```

### `list` - Listing all tasks

Lists all the tasks in the task list.

Example of usage:

`list`

Expected outcome:

Numbered list of all tasks in the task list.

```
1. [T][ ] borrow books
2. [D][ ] return books (by: Sep 23 2021)
3. [E][ ] cs2103T meeting (at: Sep 24 2021) 
```

### `Find` - Searching for tasks by keywords

Lists the tasks in the task list containing the specified keyword.

Example of usage:

`find books`

Expected outcome:

Numbered list of all tasks containing 'books' in the description.

```
1. [T][ ] borrow books
2. [D][ ] return books (by: Sep 23 2021)
```

### `delete` - Deleting a task

Deletes a task from the task list at the specified index.

Example of usage:

`delete 3`

Expected outcome:

The task in the task list at index 3 is deleted.

```
Noted. I've deleted this task:
[E][X] cs2103T meeting (at: Sep 24 2021)
```

### `reminder` - Searching for upcoming deadlines

Lists the deadlines in the task list that are occurring in the next 2 days.

Example of usage:

`reminder`

Expected outcome:

Numbered list of all tasks occurring the next 2 days.

```
// Assuming current date is 16 Sep 2021
Upcoming deadlines(s):
1. [D][ ] example1 (by: Sep 16 2021)
2. [D][ ] example2 (by: Sep 17 2021)
3. [D][ ] example3 (by: Sep 18 2021)
```

### `bye` - Exiting the program

Exits the program and closes window.

Example of usage:

`bye` 

Expected outcome:

Exit message is printed and window closes after 5 seconds.

```
Exiting Duke. See you again!
```
