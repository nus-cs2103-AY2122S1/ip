# User Guide for Duke™
<p align="center">
  <img src="https://github.com/kleonang/ip/blob/master/docs/Ui.png" width="512">
</p>

## Features 

### A Myriad of Task Types

In Duke™, you get to manage 3 distinct types of tasks, they are:
- Todos
  - Tasks to check off your to-do list  
- Deadlines
  - Tasks with an associated deadline
- Events
  - Tasks that occur at a specified time and date

### Load and Store Tasks
Tasks managed by Duke™ are stored locally in a data file, so you can be sure that Duke™ remembers your tasks, no matter how many there are.

### Interact with Tasks
Duke™ enables you to list your tasks, mark tasks as done, find tasks, and even set a priority level to differentiate more important tasks from the rest.

## Usage

### `todo DESCRIPTION` - adds a Todo task

Adds a Todo task to your tasks.

Example of usage: 

`todo watch bee movie`

Expected output:

```
Got it. I've added this task:
  [T][MEDIUM][ ] watch bee movie
Now you have 1 tasks in the list.
```

### `deadline DESCRIPTION /by YYYY-MM-DD hh:mm` - adds a Deadline task

Adds a Deadline task to your tasks. Provide time in `hh:mm`, 24h-clock format (e.g. `23:59`).

Example of usage: 

`deadline finish assignment /by 2021-09-06 16:20`

Expected output:

```
Got it. I've added this task:
  [D][MEDIUM][ ] finish assignment
  (by: Sep 6 2021, 4:20 pm)
Now you have 2 tasks in the list.
```

### `event DESCRIPTION /at YYYY-MM-DD hh:mm` - adds an Event task

Adds an Event task to your tasks. Provide time in `hh:mm`, 24h-clock format (e.g. `23:59`).

Example of usage: 

`event National Day Celebration /at 2021-08-09 17:00`

Expected output:

```
Got it. I've added this task:
  [E][MEDIUM][ ] National Day Celebration
  (at: Aug 9 2021, 5:00 pm)
Now you have 3 tasks in the list.
```

### `list` - lists all tasks

Lists out all current tasks.

Example of usage: 

`list`

Expected output:

```
Here are the tasks in your list:
1.[T][MEDIUM][ ] watch bee movie
2.[D][MEDIUM][ ] finish assignment
  (by: Sep 6 2021, 4:20 pm)
3.[E][MEDIUM][ ] National Day Celebration
  (at: Aug 9 2021, 5:00 pm)
```

### `done INDEX` - marks a task as done

Marks the task of the given index as done. `INDEX` is referenced from the `list` command.

Example of usage: 

`done 2`

Expected output:

```
Nice! I've marked this task as done:
  [D][MEDIUM][X] finish assignment
  (by: Sep 6 2021, 4:20 pm)
```

### `find KEYWORD` - finds a matching task

Finds a task based on the given keyword.

Example of usage: 

`find bee`

Expected output:

```
Here are the matching tasks in your list:
1.[T][MEDIUM][ ] watch bee movie
```

### `priority INDEX LEVEL` - sets priority level for a task

Sets a priority level for the task of the given index. `INDEX` is referenced from the `list` command. Indicate priority `LEVEL` as `high`, `medium` or `low`. Tasks are marked as `MEDIUM` priority by default.

Example of usage: 

`priority 1 high`

Expected output:

```
Nice! I've marked this task as HIGH priority:
  [T][HIGH][ ] watch bee movie
```

### `bye` - sends a goodbye message

Says goodbye to the user.

Example of usage: 

`bye`

Expected output:

```
Bye. Hope to see you again soon!
```
