# User Guide

*Please note: Within the same folder as the jar file, create a "data" folder, with a "duketest.txt" inside.

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

Add a todo with a task description to the list. Format of command: `todo <task description>`.

Example of usage: 

* `todo buy groceries`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [T][] <task description>
Now you have x tasks in the list.
```

### `deadline` - Add a deadline task

Add a deadline with a task description and the date and time of the deadline to the list. Format of command: `deadline <task description> /by MMM dd yyyy HH:mm` or 
`deadline <task description> /by yyyy-MM-dd HH:mm`.
  
Example of usage: 

* `deadline report /by Aug 20 2021 18:00`
* `deadline project submission /by 2021-08-20 17:00`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [D][] <task description> (by: MMM dd yyyy HH:mm)
Now you have x tasks in the list.
```

### `event` - Add an event task

Add an event with a task description and the date and time of the event to the list. Format of command: `event <task description> /at MMM dd yyyy HH:mm` or 
`event <task description> /at yyyy-MM-dd HH:mm`.

Example of usage: 

* `event birthday party /at Sep 20 2021 18:00`
* `event prom /at 2021-10-28 19:00`

Expected outcome:

Confirmation message from Vision that the task has been added.

```
Got it. I've added this task:
  [E][] <task description> (at: MMM dd yyyy HH:mm)
Now you have x tasks in the list.
```

### `list` - Show all tasks stored

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

### `done` - Mark a specific task as done

Marks the task at the given index in the list as done. Format of command: `done <idx>`.

Example of usage: 

* `done 2`

Expected outcome:

Confirmation message from Vision that the task has been marked as done.

```
Nice! I've marked this task as done
  <task description>
```

### `delete` - Removes a specific task from the list

Removes the task at the given index from the list. Format of command: `delete <idx>`.

Example of usage: 

* `delete 2`

Expected outcome:

Confirmation message from Vision that the task has been removed from the list.

```
Got it. I've deleted this task:
  <task description>
Now you have x tasks in the list.
```

### `find` - Finds tasks that match the given keyword

Finds tasks that match the given keyword and displays them in a list format. Format of command: `find <keyword>`.

Example of usage: 

* `find party`
* `find project`

Expected outcome:

A list of all matching tasks.

```
Here are the matching tasks in your list:
  1. <task description>
  ...
```

### `view` - Shows tasks that fall under the given date

Shows tasks that fall under the given date and displays them in a list format. Format of command: `view <MMM dd yyyy>`.

Example of usage: 

* `view Aug 20 2021`
* `view Jan 14 2020`

Expected outcome:

A list of all matching tasks.

```
Here is your schedule for this day:
  1. <task description>
  ...
```

### `bye` - Exits from the app

Exits from the app and closes the window.

Example of usage: 

`bye`

Expected outcome:

The app window closes, and the app is stopped.

