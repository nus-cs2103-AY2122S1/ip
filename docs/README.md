# User Guide

## Features 

#### Interactive Task List

Why keep track of your tasks in your head when a bot can do it for you?
Duke separates your tasks into three categories: todo, deadline and event. The Duke task list allows you to add, delete, find, view and update tasks. Then, when you're done with a task, simply mark it as done.

## Usage

###  Viewing Help: `help`

Displays a message which contains all the commands of the Duke bot and their usage.

Example of usage: 

`help`

Expected outcome:

A message that lists all the commands of the bot.


### Viewing tasks: `list`

Lists all the tasks in the task list.

Example of usage: 

`list`

Expected outcome:

A message that lists all the tasks in the user's task list.

```
Here are the tasks in your list:
    1. [T][] borrow book
    2. [E][] family dinner (at: Sep 12 2021)
    3. [D][] assignment 1 (by: Sep 14 2021)
```

### Adding a todo: `todo`

Adds a todo task to the task list.

Format:

`todo <description>`

Example of usage: 

`todo borrow book`

Expected outcome:

A todo is added to the task list and a message is returned to confirm the addition of the todo task.

```
Got it. I've added this task:
    [T][] borrow book
Now you have 1 task in the list.
```

### Adding a deadline: `deadline`

Adds a deadline task to the task list.

Format:

`deadline <description> /by <any input or date with format yyyy-mm-dd>`

Example of usage: 

`deadline assignment 1 /by 2021-09-14`

Expected outcome:

A deadline is added to the task list and a message is returned to confirm the addition of the deadline task.

```
Got it. I've added this task:
    [D][] assignment 1 (by: Sep 14 2021)
Now you have 1 task in the list.
```

### Adding an event: `event`

Adds an event task to the task list.

Format:

`event <description> /at <any input or date with format yyyy-mm-dd>`

Example of usage: 

`event family dinner /by 2021-09-12`

Expected outcome:

An event is added to the task list and a message is returned to confirm the addition of the event task.

```
Got it. I've added this task:
    [E][] family dinner (by: Sep 12 2021)
Now you have 1 task in the list.
```

### Finding tasks: `find`

Finds all the tasks that contain the user's search query.

Format:

`find <search query>`

Example of usage: 

`find book`

Expected outcome:

A message that lists all the tasks that fit the user's search query.

```
Here are the matching tasks in your list:
    [T][] borrow book
```

### Deleting a task: `delete`

Deletes the specified task from the task list and returns a message to confirm the deletion.

Format:

`delete <task number>`

Example of usage: 

`delete 1`

Expected outcome:

The task is deleted from the task list and a message is returned to confirm the deletion.

```
Noted. I've removed the this task:
    [T][] borrow book
Now you have 0 tasks in the list.
```

### Updating a task: `delete`

Updates the specified task and returns a message to confirm the update.

Format:

`update <task number>`

Example of usage: 

`udpate 1 assignment 1 and 2 /by 2021-09-20`

Expected outcome:

The task is updated and a message is returned to confirm the update.

```
Your task has been updated!
    [D][] assignment 1 and 2 (by: Sep 20 2021)
```

### Exiting: `exit` or `bye`

Closes the application


Example of usage: 

`exit`

Expected outcome:

The application closes
