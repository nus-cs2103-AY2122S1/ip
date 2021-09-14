# User Guide

## Features 

### Feature-Add Todo

Adds a todo item to the task list.

### Feature-Add Event

Adds an event to the task list containing task name and the date of the event.

### Feature-Add Deadline

Adds a deadline to the task list containing the task name and the date of the deadline.

### Feature-List items

Lists all the tasks currently in the task list.

### Feature-Mark item as complete

Marks an item on the task list as completed.

### Feature-Find task(s) using a keyword

Returns a list of task names that match the given keyword.

### Feature-Delete an item

Deletes an item from the task list.

### Feature-Save the task list offline

Stores current items in a task list into a text file for offline storage.


## Usage

### `todo` - Add a todo to task list.

The todo command adds a todo task to the task list.

Example of usage: 

`todo (task name)`

Expected outcome:

The task name is added to the task list.

```
[T]|[]|some task
```

### `event` - Add an event to task list.

The event command adds an event task to the task list.

Example of usage:

`todo (event name) \at yyyy-mm-dd`

Expected outcome:

The event name is added to the task list.

```
[E]|[]|some task (at: 2003-03-01)
```

### `deadline` - Add a deadline to task list.

The deadline command adds a deadline task to the task list.

Example of usage:

`deadline (deadline name) \by yyyy-mm-dd`

Expected outcome:

The deadline name is added to the task list.

```
[D]|[]|some task (by: 2001-02-01)
```

### `items` - List all items on the task list.

List all the items in the task list.

Example of usage:

`items`

Expected outcome:

Displays a list of all tasks in the task list.

```
1. [T]|[]|some task
2. [T]|[]|some task
3. [T]|[]|some task
```

### `completed` - Mark an item as completed.

Marks the specified task as completed.

Example of usage:

`completed (index)`

Expected outcome:

Displays the completed task as a confirmation.
```
You have successfully completed task:
[T]|[x]|some task
```

### `delete` - Delete a task from the task list.

Delete a task specified by the index from the task list.

Example of usage:

`delete (index)`

Expected outcome:

Displays the deleted task as a confirmation.
```
[D]|[]|some task (by: 2001-02-01)
```

### `find` - Find a task(s) from the task list

Find all tasks that match a keyword from the task list.

Example of usage:

`find (keyword)`

Expected outcome:

Displays all tasks with matching keyword.
```
[D]|[]|some task (by: 2001-02-01)
[D]|[]|some task (by: 2001-02-01)
[D]|[]|some task (by: 2001-02-01)
```

### `bye` - Save the task list offline

Saves all tasks offline into a text document.

Example of usage:

`bye`

Expected outcome:

Displays a confirmation after saving to a text document.

```
Your data has been successfully saved!
```