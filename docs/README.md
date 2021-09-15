# User Guide

*Please note: Do create a "data" folder, with a "duketest.txt" inside, within the same folder as the jar file.

## Features 

### Adding tasks
Add either a todo, event or deadline task to the list.

### List out all tasks
Shows all the tasks stored in the list.

### Mark tasks as done
Mark a task at a specified index in the list as done.

### Delete tasks from the list
Delete a task at a specified index in the list.

### Find specific tasks from the list
Shows tasks that match the given keyword.

### View tasks on specified dates
Shows tasks that fall on a certain date.

## Usage

### `todo` - Add a todo task

Add a todo with a task description to the list.

Example of usage: 

`todo <task description>`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [T][] <task description>
Now you have x tasks in the list.
```

### `deadline` - Add a deadline task

Add a deadline with a task description and the date and time of the deadline to the list.

Example of usage: 

* `deadline <task description> /by MMM dd yyyy HH:mm`
* `deadline <task description> /by yyyy-MM-dd HH:mm`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [D][] <task description> (by: MMM dd yyyy HH:mm)
Now you have x tasks in the list.
```

### `event` - Add an event task

Add an event with a task description and the date and time of the event to the list.

Example of usage: 

* `event <task description> /at MMM dd yyyy HH:mm`
* `event <task description> /at yyyy-MM-dd HH:mm`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [E][] <task description> (at: MMM dd yyyy HH:mm)
Now you have x tasks in the list.
```

### `list` - Show all tasks stored.

Displays all tasks with their relevant details in a list format.

Example of usage: 

`list`

Expected outcome:

A list of tasks stored, with their relevant details.

```
Here are the tasks in your list:
  1. <task 1>
  ...
```

### `done` - Mark a specific task as done.

Marks the task at the given index in the list as done.

Example of usage: 

`done <idx>`

Expected outcome:

Confirmation message from Vision that the task has been marked as done.

```
Nice! I've marked this task as done
  <task description>
```

### `delete` - Removes a specific task from the list

Removes the task at the given index from the list.

Example of usage: 

`delete <idx>`

Expected outcome:

Confirmation message from Vision that the task has been removed from the list.

```
Got it. I've deleted this task:
  <task description>
Now you have x tasks in the list.
```

### `find` - Finds tasks that match the given keyword

Finds tasks that match the given keyword and displays them in a list format.

Example of usage: 

`find <keyword>`

Expected outcome:

A list of all matching tasks.

```
Here are the matching tasks in your list:
  1. <task description>
  ...
```

### `view` - Shows tasks that fall under the given date

Shows tasks that fall under the given date and displays them in a list format.

Example of usage: 

`view <MMM dd yyyy>`

Expected outcome:

A list of all matching tasks.

```
Here is your schedule for this day:
  1. <task description>
  ...
```

