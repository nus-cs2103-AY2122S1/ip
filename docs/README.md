# User Guide
Nukem is a simple chat-bot that can help you take note of tasks, deadlines or upcoming events. It utilises a Command Line Interface (CLI) for quick and easy operations.

## Features 

### Task tracking

Take note of tasks to do as well as upcoming events and deadlines. Mark them as done once you're finished.

### CLI input

All commands are typed in a single text box, allowing for quick and easy operations.

## Usage

### `list` - List all tasks

Lists all the tasks in your roster, with distinction between task types and whether they are done.

Example of usage: 

`list`

Expected outcome:

A list of tasks in your roster.

```
1.[T][ ] Do the laundry
2.[D][X] Submit assignment 1 (by: 21 Dec 2021 - 12:21)
3.[E][ ] Movie night (at: 21 Dec 2021 - 20:00)
```

### `done` - Mark task as done

Marks a task at specified index as *done*. The task will be showed as *done* in your list.

Example of usage: 

`done 2`

Expected outcome:

A message confirming the task has been marked as *done*.

```
Nice! I've marked this task as done:
  [D][X] Submit assignment 1 (by: 21 Dec 2021 - 12:21)
```

### `todo` - Add a new todo task

Adds a new *todo* task to your list.

Example of usage: 

`todo Do the laundry`

Expected outcome:

A message confirming the new *todo* task has been added, and the number of tasks in your list.

```
Got it. I've added this task:
  [T][ ] Do the laundry
Now you have 3 tasks in the list.
```

### `deadline` - Add a new deadline

Adds a new *deadline* to your list.

Example of usage: 

`deadline Submit assignment 1 /by 2021-12-21 12:21`

Expected outcome:

A message confirming the new *deadline* has been added, and the number of tasks in your list.

```
Got it. I've added this task:
  [D][ ] Submit assignment 1 (by: 21 Dec 2021 - 12:21)
Now you have 3 tasks in the list.
```

### `event` - Add a new event

Adds a new *event* to your list.

Example of usage: 

`event Movie night /at 2021-12-21 20:00`

Expected outcome:

A message confirming the new *event* has been added, and the number of tasks in your list.

```
Got it. I've added this task:
  [E][ ] Movie night (at: 21 Dec 2021 - 20:00)
Now you have 3 tasks in the list.
```

### `delete` - Delete a task

Deletes a task at the specified index from your list.

Example of usage: 

`delete 2`

Expected outcome:

A message confirming the task has been deleted, and the number of tasks in your list.

```
Noted. I've removed this task:
  [D][X] Submit assignment 1 (by: 21 Dec 2021 - 12:21)
Now you have 2 tasks in the list.
```

### `find` - Find tasks

Finds all tasks that match your keyword.

Example of usage: 

`find movie`

Expected outcome:

A message showing all tasks that match your specified keywords.

```
Here are the matching tasks in your list:
1.[E][ ] Movie night (at: 21 Dec 2021 - 20:00)
2.[D][X] Design movie poster (by: 13 July 2022 - 13:06)
```
