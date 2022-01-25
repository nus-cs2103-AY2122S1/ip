# User Guide

Welcome to Soy Bot, a bot that keeps track of your current tasks!
## Features 

### Feature - Add Tasks

Create tasks to be added to a list.

### Feature- View List

View your current todo list.

### Feature- Unique Tasks

Create unique tasks such as deadlines and todos that track unique fields such as dates.

### Feature- Find

Search for a specific task using keywords.

### Feature- Save Function

Automatically saves your current list that can be loaded up the next time you open the app again.

## Usage

### `todo` - Adds a Todo Task

Adds a 'todo' labelled task to your list.

Example of usage: 

`todo read book`

Expected outcome:

The list will be updated with the new task.

```
added [T][ ] read book
Now you have 1 task in the list
```

### `deadline` - Adds a Deadline Task

Adds a 'deadline' labelled task to your list.

Example of usage:

`deadline read book /by 2021-12-03`

Expected outcome:

The list will be updated with the new task.

```
added [D][ ]  read book  (by: Dec 03 2021)
Now you have 1 task in the list
```
### `event` - Adds an Event Task

Adds an 'event' labelled task to your list.

Example of usage:

`event read book /at 2021-12-03`

Expected outcome:

The list will be updated with the new task.

```
added [E][ ]  read book  (at: 2021-12-03)
Now you have 1 task in the list
```

### `delete` - Delete a Task

Deletes a task on your list.

Example of usage:

`delete 2`

Expected outcome:

Deletes the 2nd entry in your list.

```
Noted, I have removed this task:
[E][ ]  read book  (at: 2021-12-03)
Now you have 1 task in your list
```


### `find` - Find Tasks

Find a list of tasks that matches the search term.

Example of usage:

`find book`

Expected outcome:

Returns a list of tasks that contain the word 'book'.

```
Here are the matching tasks in your list:
[E][ ]  read book  (at: 2021-12-03)
```

### `done` - Marking Tasks as Done

Mark a task as done using a 'X'.

Example of usage:

`done 1`

Expected outcome:

Marks the 1st entry in your list as done.

```
Nice! I have marked this task as done:
[E][X]  read book  (at: 2021-12-03)
```

### `help` - Help

Display a brief guide on how to use Soy Bot.

Example of usage:

`help`

Expected outcome:

Show relevant help commands.

```
Help Guide:
/general: general description of Soy Bot
/task: tasks available to add
/commands1-2: list of commands
```

### `save` - Help

Saves the current list down.

Example of usage:

`save`

Expected outcome:

Save the current list as a .txt file.

```
List saved!
```

### `bye` - End Program

Saves the current list and close the program.

Example of usage:

`bye`

Expected outcome:

Save the current list as a .txt file and closes the program.

```
List saved!
Bye, hope to see you again soon!
```