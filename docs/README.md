# User Guide

## Features 

### Easy task management

Easily create todos, deadlines and events using simple keyboard commands. Easily view the list of tasks as well as mark them as done. When you are done with a task easily toggle it for easy reference. You can even delete a task.

### Date and time

For the Deadline and Events, store a date and time so that you can reference when it should be accomplished by for easy reference. Furthermore, use the `sort` command to easily sort the deadlines and events by chronological order.

## Usage

### `list` - Lists out tasks

Lists out the lists of tasks.

Example of usage:

`list`

Expected outcome:

the lists of tasks so far.

```
Here are the tasks in your list:
1. [E][X] feed spike (at: Jan 1 2021 1800)
2. [D][ ] do homework (by: Sep 13 2021 2359)
```

### `todo` - Creates a todo task

Creates a todo task with the given description

Example of usage:

`todo (description of task)`

Expected outcome:

Creates a todo with the given description.

### `deadline` - Creates a deadline task

Creates a deadline with the given description and date and time as the deadline

Example of usage:

`deadline (description) /by (dd/mm/yyyy hhmm)`

Expected outcome:

Creates a deadline with the given description and date and time as the deadline.

### `event` - Creates a event task

Creates an event with the given description and date time as the timing of the event.

Example of usage:

`event (description) /at (dd/mm/yyyy hhmm)`

Expected outcome:

Creates an event with the given description and date time as the timing of the event.

### `delete` - Deletes a task from the list

Deletes a task indicated by its position in the list.

Example of usage:

`delete (position of task in list)`

Expected outcome:

Deletes the task from the list

### `done` - Toggles a task

Toggles the state of the task between done and not done.

Example of usage:

`done (position of task in list)`

Expected outcome:

The task specified has its state toggled

### `sort` - Sorts the list

Sorts the list chronologically based on the timing of the event and deadline.

Example of usage:

`sort`

Expected outcome:

the list gets sorted chronologically based on the timing of the event and the deadline.

### `find` - finds tasks that fit a given description

Finds through all the tasks that fit the given description. Is case-insensitive.

Example of usage:

`find (description)`

Expected outcome:

A list of tasks that fit the given description.

### `bye` - shuts down the application

Saves all the data, and displays a goodbye message before shutting down the application.

Expected outcome:

The application replies with "Bye. Hope to see you soon!" before shutting down.

