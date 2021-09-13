# User Guide
ArchDuke is a task-list application that allows users to organise and keep track
of their tasks through a graphical user Interface. Archduke is adapted from Java's Project Duke.


## Features 

### Manage your tasks
Archduke enables you to plan and organise you upcoming tasks. Three types of tasks are supported - ToDos, 
impending deadlines and upcoming events. With Archduke, you can add, view, delete or complete tasks.

### Find a task
Search for a task using a keyword to find all tasks that contain the search phrase.

### See what's happening on a particular date
Find tasks that are scheduled for a specific date. If you're planning a date this friday night, better make 
sure you don't have anything due then!

## Usage

### `todo` - Add a ToDo task to the list

Adds a task that does not contain a date or time.

Example of usage: 

`todo A whole lotta stuff`

Expected outcome:

Archduke recognises your ToDo task and adds it to the task list.

Arguments:
* Description of task (required)

```
Got it. I've added this task:
[T][ ] A whole lotta stuff
Now you have 1 tasks in the list.
```

### `deadline` - Add a Deadline to the list

Adds a task that needs to be completed by a certain date (and time, if any). If
date of a Deadline is before current date, it will be automatically marked as completed.

Arguments
* Description of deadline (required)
* Seperator `by` to distinguish between description and date (required)
* Date of deadline in D/MM/YYYY fromat (required)
* Time of deadline in HHmm format (optional)

Example of usage:

`deadline Brexit /by 31/01/2021 (time)`

Expected outcome:

Archduke recognises your deadline task and adds it to the task list.

```
Got it. I've added this task:
[D][X] Brexit (by: Jan 31 2021)
Now you have 2 tasks in the list.
```

### `event` - Add an Event to the list

Adds a task that begins at a certain date (and time, if any). If
date of an Event is before current date, it will be automatically marked as completed.

Arguments
* Description of event (required)
* Seperator `at` to distinguish between description and date (required)
* Date of event in D/MM/YYYY fromat (required)
* Time of event in HHmm format (optional)

Example of usage:

`event CS2103T finals /at 23/11/2021 (time)`

Expected outcome:

Archduke recognises your event task and adds it to the task list.

```
Got it. I've added this task:
[E][ ] CS2103T finals (at: Nov 23 2021)
Now you have 3 tasks in the list.
```

### `list` - Displays all tasks in the list

Renders and displays all stored tasks with their key details.

Example of usage:

`list`

Expected outcome:

Archduke displays all tasks contained in the list.

```
Here are the tasks on your list:
1. [T][ ] A whole lotta stuff
2. [D][X] Brexit (by: Jan 31 2021)
3. [E][ ] CS2103T finals (at: Nov 23 2021)
```

### `done` - Marks a task as complete

Marks task at given index as complete

Arguments:
* Position of task in the list. Can be obtained by using the `list` command. Index starts from 1 instead
of 0. (required)


Example of usage:

`done 1`

Expected outcome:

Archduke marks task as completed and displays it with an 'X'.

```
Nice! I've marked this task as done:
[T][X] A whole lotta stuff
```

### `delete` - Deletes a task.

Removes tasks from task list and deletes them from Archduke.

Arguments:
* Position of task in the list can be obtained by using the `list` command. Index starts from 1 instead
  of 0. (required)
* If multiple tasks are to be delted, separate IDs with a space (optional)

Example of usage:

`delete 1 3`

Expected outcome:

Tasks in given ID's would be deleted from Archduke.

```
Noted. I've removed these tasks:
[E][ ] CS2103T finals (at: Nov 23 2021)
[T][X] A whole lotta stuff
Now you have 1 tasks in the list.
```

### `find` - Finds tasks by keyword

Searches for tasks that contain the key word or phrase given and displays them.

Arguments:
* Keyword (required)

Example of usage: 

`find exit`

Expected outcome:

Archduke displays tasks that contain keyword.

```
Here are the matching results:
[D][X] Brexit (by: Jan 31 2021)
```

### `date` - Finds tasks on given date

Searches for tasks that are scheduled for the given date.

Arguments:
* date (required)

Example of usage:

`date 31/01/2021`

Expected outcome:

Archduke displays tasks scheduled for given date

```
Here are the tasks on that date:
[D][X] Brexit (by: Jan 31 2021)
```

### `bye` - Exits Archduke

Displays the goodbye message before exiting the application.

Example of usage:

`bye`

Expected outcome:

Archduke displays goodbye message before closing the session.

```
Bye. Hope to see you again soon!
```