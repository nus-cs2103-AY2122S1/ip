# User Guide

## Features 

### Help Page

Shows all possible commands and their usage.

### Task List

Shows all tasks entered with their current completed status.

### Add Todo, Event and Deadline Tasks

Adds 1 of 3 types of tasks into the task list.
* Todo tasks requires only a task description
* Events require both a task description, and a start date & time
* Deadlines require both a task description, and a due date & time

### Find tasks

Finds all tasks whose description contains the keyword searched for.

### Mark tasks as done

Mark tasks that are completed as done.

### Delete tasks

Remove tasks from the task list.

### Filter tasks

Filters event and deadline tasks by their inputted dates. Options are available for filtering of only events, deadlines, or both.

### Automatically saves tasks

Automatically saves task list to local memory after every command.

***

## Usage

### `help` - Displays the help page

Displays a list of all possible commands and their usage.

#### Example of usage: 

`help`

#### Expected outcome:

A list of all possible commands and their usage is displayed on the screen.

```
bye : Closes the programme

list : Returns all tasks added

todo <description> : Adds a todo task

find <description> : Returns all tasks with <description> in their description

event <description> /at <time: YYYY-MM-DDThh:mm> : Adds an event at time <time>

deadline <description> /by <time: YYYY-MM-DDThh:mm> : Adds a task with deadline at time <time>

done <index> : Marks the task at <index> as done

delete <index> : deletes the task at <index>

at <time: YYYY-MM-DDThh:mm> : Returns all events up till <time>

by <time: YYYY-MM-DDThh:mm> : Returns all tasks with deadline due before or at <time>

all <time: YYYY-MM-DDThh:mm> : Returns all timed tasks with times up till <time>
```
<br>

### `list` - Displays the current task list

Displays a list of all tasks inputted.

#### Example of usage:

`list`

#### Expected outcome:

A list of all tasks their status is displayed on the screen.

```
Here are the tasks in your list:
1.[T][X] eat
2.[D][ ] sleep (by: Sep 9 2021 04:00 AM)
3.[E][ ] lesson (at: Sep 11 2021 11:00 AM
```
<br>

### `todo <description>` - Adds a todo task

Adds a todo task to the task list.

#### Example of usage:

`todo eat`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
Noted. I've added this task:
  [T][ ] eat
Now you have 3 tasks in the list.
```

<br>

### `event <description> /at <time: YYYY-MM-DDThh:mm>` - Adds an event task

Adds an event task to the task list.

#### Example of usage:

`event lesson /at 2021-01-12T13:00`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
Noted. I've added this task:
  [E][ ] lesson (at: Jan 12 2021 01:00 PM)
Now you have 4 tasks in the list.
```
<br>

### `deadline <description> /by <time: YYYY-MM-DDThh:mm>` - Adds a deadline task

Adds a deadline task to the task list.

#### Example of usage:

`deadline submission /by 2021-02-11T11:00`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
Noted. I've added this task:
  [D][ ] submission (by: Feb 11 2021 11:00 AM)
Now you have 5 tasks in the list.
```
<br>

### `find <description>` - Finds tasks based on a keyword

Finds all tasks whose description contains the keyword.

#### Example of usage:

`find lesson`

#### Expected outcome:

Tasks that match the keyword are displayed on the screen.

```
Here are the matching tasks in your list:
1.[E][ ] lesson (at: Jan 12 2021 01:00 PM)
```
<br>

### `done <index>` - Marks a task as done

Marks a task at a given index as done.
    
#### Example of usage:

`done 1`

#### Expected outcome:

The task that is marked as done is displayed on the screen with its status updated.

```
Noted. I've marked this task as done:
[T][X] eat
```
<br>

### `delete <index>` - Deletes a task

Deletes a task at a given index.

#### Example of usage:

`delete 1`

#### Expected outcome:

The task that is deleted is displayed on the screen together with an updated count of the number of tasks left in the list.

```
Noted. I've removed this task:
[T][X] eat
Now you have 3 tasks in the list.
```
<br>

### `at <time: YYYY-MM-DDThh:mm>` - Finds all event tasks by a certain date and time 

Finds all event tasks that occur at or before a specified date and time.

#### Example of usage:

`at 2022-01-01T00:00`

#### Expected outcome:

Events that occur at or before the given date and time are displayed on the screen.

```
Here are the Events happening before Jan 1 2022 12:00 AM:
[E][ ] lesson (at: Jan 12 2021 01:00 PM) 
```
<br>

### `by <time: YYYY-MM-DDThh:mm>` - Finds all deadline tasks by a certain date and time

Finds all deadline tasks that are due at or before a specified date and time.

#### Example of usage:

`by 2022-01-01T00:00`

#### Expected outcome:

Deadline tasks that are due at or before the given date and time are displayed on the screen.

```
Here are the Deadlines to be completed by Jan 1 2022 12:00 AM:
[D][ ] submission (by: Feb 11 2021 11:00 AM)
```
<br>

### `all <time: YYYY-MM-DDThh:mm>` - Finds all tasks by a certain date and time

Finds all tasks that have a time field at or before a specified date and time.

#### Example of usage:

`all 2022-01-01T00:00`

#### Expected outcome:

Tasks that have a time field at or before the given date and time are displayed on the screen.

```
Here are the timed tasks occurring before Jan 1 2022 12:00 AM:
[E][ ] lesson (at: Jan 12 2021 01:00 PM)
[D][ ] submission (by: Feb 11 2021 11:00 AM)
```
