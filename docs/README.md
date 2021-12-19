# User Guide

## Features


### View tasks

Shows all entered tasks with their description and status.

### Add Tasks: Todo / Event / Deadline

Each task respectively has these features:
* Todo tasks have description and done status
* Event tasks have description, done status, and the time
* Deadline tasks have description, done status, and the due date.

### Search tasks

Can find the tasks that contain a specific keyword

### Mark tasks as done

Mark tasks that are completed as done.

### Task deletion

Added tasks could be removed from the list.

### Extension: Undo

The latest change made in the task list could be undone.

### Auto save / load

Entered tasks will be automatically saved and read from the hard drive.
***

## Usage

### `list` - Displays the current task list

Displays a list of all tasks inputted.

#### Example of usage:

`list`

#### Expected outcome:

A list of all tasks their status is displayed on the screen.

```
Here are the tasks in your list:
1.[T][X] study
2.[D][ ] sleep (by: May 4 1999 04:13 AM)
3.[E][ ] lesson (at: 3pm)
```
<br>

### `todo <description>` - Adds a todo task

Adds a todo task to the task list.

#### Example of usage:

`todo study`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
OK bro, I just added: [T][ ] study
```

<br>

### `event <description> /at <time>` - Adds an event task

Adds an event task to the task list.

#### Example of usage:

`event party /at 3pm`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
OK bro, I just added: [E][ ] party (at: 3pm)

```
<br>

### `deadline <description> /by <time: YYYY-MM-DD>` - Adds a deadline task

Adds a deadline task to the task list.

#### Example of usage:

`deadline submission /by 2012-12-24`

#### Expected outcome:

The task to be inputted is displayed on the screen together with the total number of tasks in the list.

```
OK bro, I just added: [D][ ] submission (by: Dec 24, 2012)

```
<br>

### `find <keyword>` - Finds tasks based on a keyword

Finds all tasks whose description contains the keyword.

#### Example of usage:

`find study`

#### Expected outcome:

Tasks that match the keyword are displayed on the screen.

```
Here are the matching tasks in your list:
1.[T][X] study
```
<br>

### `done <index>` - Marks a task as done

Marks a task at a given index as done.

#### Example of usage:

`done 1`

#### Expected outcome:

The task that is marked as done is displayed on the screen with its status updated.

```
Noted. I've noted you've done this task:
[T][X] study
```
<br>

### `delete <index>` - Deletes a task at the index

Deletes a task at a given index number.

#### Example of usage:

`delete 1`

#### Expected outcome:

The task that is deleted is displayed on the screen together with an updated count of the number of tasks left in the list.

```
OK Bro, I've noted you've deleted this task:
[T][X] study
So bro, now you have 3 tasks stored in the list!
```

<br>

### `undo` - Undo the latest change made in the task list

The latest change made by the user is cancelled.

#### Example of usage:

`undo`

#### Expected outcome:

The bot lets the user know the change is undone successfully.

```
OK Bro, the latest change you made in the task list is undone!
```
