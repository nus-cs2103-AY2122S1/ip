# User Guide

## Riludduma is here! 

###Riladduma helps you with all your tasks including:
* Todos
* Deadlines
* Events

## Usage

### `list` - Show a list of tasks

Show a list of tasks.

Example of usage: 

`list`

Expected outcome:

A list of task entries will be shown.

```
Here are the tasks in your list:
1. [T] | [ ] | add increment: A-BetterGui
2. [D] | [ ] | iP final version submission | Sep 17, 2021
3. [E] | [ ] | tP meeting | Sep 12, 2021
```

### `todo (task description)` - Add a todo task

Add an event task.

Example of usage:

`todo Riladduma User Guide`

Expected outcome:

The specified todo task will be added.

```
Added: [T] | [ ] | Riladduma User Guide
Now you have 5 tasks in the list
```


### `deadline (task description) /by yyyy-mm-dd` - Add a deadline task

Add a deadline task.

Example of usage:

`deadline CS2100 assignment 1 /by 2021-09-15`

Expected outcome:

The specified deadline task will be added.

```
Added: [D] | [ ] | CS2100 assignment 1 | Sep 15, 2021
Now you have 4 tasks in the list
```

### `event (task description) /at yyyy-mm-dd` - Add an event task

Add an event task.

Example of usage:

`event SoC Career Fair /at 2021-09-13`

Expected outcome:

The specified event task will be added.

```
Added: [E] | [ ] | SoC Career Fair | Sep 13, 2021
Now you have 5 tasks in the list
```

### `done (task index)` - Mark a task as done

Mark a task as done.

Example of usage:

`done 1`

Expected outcome:

The specified task will be marked as done.

```
Nice! I've marked this task as done:
1. [T] | [X] | add increment: A-BetterGui
```

### `delete (task index)` - delete a task

Delete a task at the specified index.

Example of usage:

`delete 1`

Expected outcome:

The specified task will be deleted.

```
Noted. I've removed this task:
1. [T] | [X] | add increment: A-BetterGui
```

### `find (keywords)` - Find tasks containing keywords

Find a list of tasks containing the search keywords.

Example of usage:

`find meeting`

Expected outcome:

A list of matched tasks will be shown.

```
Here are the matching tasks in your list:
1. [E] | [ ] | tP meeting | Sep 12, 2021
```

### `help` - Show help page

Show a help page about how to use the app

### `bye` - Exit the program

The program will be closed.
