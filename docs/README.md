# User Guide

## Features 
* Save tasks into a list
* Includes tasks with time-based deadlines
* Search for tasks based on keywords
* Able to reload tasks saved from previous sessions
* Delete unwanted tasks

### Feature- Search for tasks based on keywords
Type a sentence or phrase with that matches the task description.<br /> 
Our search function will be able to produce a list of tasks<br /> 
that matches (even partially) to what you have input.

### Feature-Reload tasks
Task CATcher saves your task list from the previous station into your<br />
local storage as a text file.<br />
Upon reinitialization, it reloads that file, so you have your previous tasks<br/>
ready for perusal. 

## Usage
Task CATcher works using a GUI with a text box for input.<br />
For normal tasks, simply type the task description and enter.

### `list` - Lists current tasks
Lists the current tasks in your list.

Example of usage: 

`list`

Expected outcome: A list of your tasks are shown on the GUI. 

Description of the outcome.

```
Here are the tasks in your list:
1. [] get groceries
```

### `done` - Mark task as done
Marks a task, based on its index, as done.

Example of usage:

`done (index)`

Expected outcome: Task is marked as done as reflected in GUI.

Description of the outcome.

```
Nice! I've marked this task as done:
1. [X] get groceries
```

### `delete` - Delete task
Deletes a task, based on its index.

Example of usage:

`delete (index)`

Expected outcome: Task is removed from the list as reflected in GUI.

Description of the outcome.
```
Noted. I've removed this task:
 [T][] learn java
```

### `todo` - A todo task
Adds a todo task into your list.

Example of usage:

`todo (task description)`

Expected outcome: A todo task is added into task list and reflected on the GUI.

Description of the outcome.

```
Got it. I've added this task:
 [T][] pay bills
```

### `deadline` - A todo task
Adds a deadline task into your list.
Deadline of task must be in the following format:<br />
`yyyy-MM-dd hh:mm`

Example of usage:

`deadline (task description) /by (deadline-time)`

Expected outcome: A deadline task is added into task list and reflected on the GUI.

Description of the outcome.

```
Got it. I've added this task:
 [D][] submit homework (by: Sep 17 2021 23:59)
```

### `event` - A todo task
Adds an event task into your list.
Event of task must be in the following format:<br />
`yyyy-MM-dd hh:mm`

Example of usage:

`event (task description) /at (event-time)`

Expected outcome: A event task is added into task list and reflected on the GUI.

Description of the outcome.

```
Got it. I've added this task:
 [E][] concert (at: Sep 17 2021 23:59)
```

### `find` - Find tasks
Find tasks that matches (even partially) the input description.

Example of usage:

`find (description)`

Expected outcome: Matched tasks are returned as a list.

Description of the outcome.

```
find pay 
```

```
1. [T][] paybills
2. [] interview for paypal
```