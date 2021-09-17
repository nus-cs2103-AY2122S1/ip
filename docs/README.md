# User Guide

Duke is a desktop app for tracking tasks such as deadlines, events and to-do lists.

## Features 

### Viewing Help

Shows a message explaining how to get started with Duke.

### Adding a task

Adds a task to the task list.

### Listing all tasks

Shows a list of all tasks in the task list. 

### Marking a task as done

Marks the specified task as done.

### Finding tasks by keyword

Find tasks which contain the given keyword.

### Deleting a task

Deletes the specified task from the task list.


### Exiting the program

Exits Duke.

## Usage

### `help` - Viewing help

Shows a message explaining how to get started with Duke.

Example of usage:

`help`

Expected outcome:

A message explaining valid Duke commmands.

```
Hi, I'm Duke! To get started, enter your commands.
1. To view your task list enter 'list'
2. To add a task use the following format
    'todo <description>'
    'deadline <description> /by <YYYY-MM-DD>'
    'event <description> /at <date or time>'
3. To mark a task as done enter 'done <task no>'
4. To delete a task enter 'delete <task no>'
5. To search the task list enter 'find <keyword>'
6. To close the program enter 'bye'
```


### `todo` - Adding a new todo task

Adds a new task to the task list.

Format : `todo DESCRIPTION`

Example of usage:

`todo sleep`

Expected outcome:

Adds a new todo task with the title sleep.

```
added: [T][ ] sleep
Now you have 1 tasks in your list.
```

### `deadline` - Adding a task with a deadline

Adds a new task with a deadline to the task list.

Format : `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage:

`deadline return book /by 2021-10-01`

Expected outcome:

Adds a new task with the title return book.

```
added: [D][ ] return book (by: Oct 01 2021)
Now you have 2 tasks in your list.
```

### `event` - Adding an event 

Adds a new task or event that occurs at a specific time.

Format : `event DESCRIPTION /at DATETIME`

Example of usage:

`event formal dinner /at 14 Sep 7PM`

Expected outcome:

Adds a new event with the title formal dinner.

```
added: [E][ ] formal dinner (at: 14 Sep 7PM)
Now you have 3 tasks in your list.
```

### `list` - Viewing list of all tasks

Shows a list of all tasks in the task list,

Example of usage:

`list`

Expected outcome:

Shows an indexed list of all tasks in the task list. Todo Tasks, Deadlines and Events will be displayed
in the following formats respectively. The X indicates that a task is completed.

```
1. [T][X] DESCRIPTION
2. [D][ ] DESCRIPTION (by: MMM DD YYYY)
3. [E][ ] DESCRIPTION (at: DATETIME)
```

### `done` - Marking a task as done

Marks the specified task as completed.

Format: `done TASKNO`

Example of usage:

`done 1`

Expected outcome:

Marks the first task in the list as completed.

```
Good job! I've marked this task as done:
[T][X] sleep
```
### `find` - Searching tasks

Searches the list for tasks containing the given keyword. The keyword is not case-sensitive.

Format: `find KEYWORD`

Example of usage:

`find read`

Expected outcome:

Shows a list of matches found or informs the user if there are no matching tasks in the list.

```
Here are the matching tasks in your list:
1.[T][ ] read notes
2.[T][ ] read book
```

### `delete` - Deleting a task

Removes the specified task from the list.

Format: `delete TASKNO`

Example of usage:

`delete 2`

Expected outcome:

Deletes the second task from the list.

```
Got it! I've removed this task:
[T][X] sleep
Now you have 2 tasks in your list.
```

### `bye` - Exiting the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

Causes Duke to display a farewell message and closes the application.

```
See you! Have a nice day!
```

