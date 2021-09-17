# User Guide

## Features 

### Manage Tasks
Keep track of your various tasks that need to be completed.

* Add deadlines/event/todos
* Mark completed tasks as done
* Delete unwanted tasks from list

### Find Tasks
Find all tasks that match a keyword for easier tracking of tasks.

### Save Tasks
All your tasks will be saved to your computer and will be reloaded
the next time you open the app.

### Archive Tasks
Start with a clean slate if you no longer need the previous list.


## Usage

### `todo` - Add a todo task

Adds a todo task to the list.

Format: todo [description]

Example of usage: 
`todo walk my dog`

Expected outcome:

Shows message of todo task added to the list

```
added: [T][] walk my dog
Now you have 1 tasks in your list
```
___
### `event` - Add an event task

Adds an event task with the date (dd MMM yyyy) to list.

Format: event [description] /at [date]

Example of usage: `event funfair /at 22 Jun 2021`

Expected outcome:

Shows message of event task added to the list with
the date stored in the form yyyy-MM-dd

```
added: [E][] funfair (at: 2021-06-22)
Now you have 2 tasks in your list
```
___
### `deadline` - Add a deadline task

Adds a deadline task with the date (dd MMM yyyy) to list.

Format: deadline [description] /by [date]

Example of usage: `deadline quiz /by 22 Jun 2021`

Expected outcome:

Shows message of event task added to the list with
the date stored in the form yyyy-MM-dd

```
added: [D][] quiz (at: 2021-06-22)
Now you have 3 tasks in your list
```
___

### `Done` - Set task as done

Sets a task at a particular index in list as done.

Format: done [index]

Example of usage: `done 1`

Expected outcome:

Shows message of task at index 1 in list set as done.

```
Nice! I've marked this task as done:
[T][X] walk my dog
```
___

### `delete` - Deletes a task

Deletes a task at a particular index in list.

Format: delete [index]

Example of usage: `delete 2`

Expected outcome:

Shows message of task at index 2 deleted.

```
Noted. I've removed this task:
[E][] funfair (at: 2021-06-22)
Now you have 2 tasks in your list
```
___

### `list` - Shows all tasks in list

Displays all the tasks in list.

Example of usage: `list`

Expected outcome:

Shows list.

```
Here are the tasks in your list:
    1. [T][X] walk my dog
    2. [D][] quiz (at: 2021-06-22)
```
___

### `find` - Find tasks in list

Finds tasks in list that matches keyword.

Format: find [keyword]

Example of usage: `find quiz`

Expected outcome:

Shows list of all tasks that matches the word quiz.

```
Here are the matching tasks in your list:
    1. [D][] quiz (at: 2021-06-22)
```
___
### `archive` - Archives all tasks in list

Saves all tasks to a file and removes them from current
list shown.

Example of usage: `archive`

Expected outcome:

Shows an empty list when list command is entered.

```
Here are the tasks in your list:
no tasks in list yet...
```
___
### `bye` - Exits program

Example of usage: `bye`

Expected outcome:

Shows bye message and closes app.

```
Bye! Hope to see you again soon!
```