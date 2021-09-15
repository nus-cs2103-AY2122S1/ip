# User Guide for MyJournal

## Download

You can download the latest version of MyJournal [here](https://github.com/felissaf/ip/releases/tag/A-Jar).

## Features 
* Add tasks
* Delete tasks
* Edit your tasks
* Record your tasks' deadlines
* Find your tasks
* Show a list of your tasks

### Feature-Add tasks

You can add your tasks in form of todo, event or deadline.

### Feature-Delete tasks

You can delete your tasks if it is not relevant anymore.

### Feature-Mark as Done

You can mark the tasks that have been completed as done.

### Feature-Edit tasks

Accidentally put the wrong date or task name? With MyJournal, 
you can easily edit your task descriptions without having to delete your tasks first! 

### Feature-Record deadlines

You can put the time or deadline for all of your tasks just by classifying them as event/deadline!

### Feature-Fine tasks

You can search tasks according to a keyword to generate a list of your tasks with the keyword.

### Feature-List tasks

You can generate a list of all of your tasks!

## Usage

### Todo - `todo [taskName]`

It adds a todo task to the task list.

Example of usage: 

`todo do laundry`

Expected outcome:

The todo task is added to the task list.

```
Okay!! I've added the following task:
[T][ ] do laundry
Now you have 1 task in the list
```

### Event - `event [taskName] /at [time]`

It adds an event task to the task list. Time can be in form of yyyy-mm-dd or hh:mm or both.

Example of usage:

`event hangout with friends /at 2021-09-16 14:00`

Expected outcome:

The event task is added to the task list.

```
Okay!! I've added the following task:
[E][ ] hangout with friends (at: Thu, Sep 16 2021 02:00 pm)
Now you have 2 tasks in the list
```

### Deadline - `deadline [taskName] /at [time]`

It adds a deadline task to the task list. Time can be in form of yyyy-mm-dd or hh:mm or both.

Example of usage:

`deadline do hw /at 2021-09-16 14:00`

Expected outcome:

The deadline task is added to the task list.

```
Okay!! I've added the following task:
[D][ ] do hw (at: Thu, Sep 16 2021 02:00 pm)
Now you have 3 tasks in the list
```

### Delete - `delete [taskNumber]`

It deletes a task.

Example of usage:

`delete 1`

Expected outcome:

The task is deleted.

```
Okay!! I have removed the following task:
[T][ ] do laundry
```

### Edit - `edit [taskNumber] [time/description] [editedTimeOrDescription]`

It edits the time or description of a task.

Example of usage:

`edit 1 time 2021-09-18 12:00`

Expected outcome:

The task is edited.

```
Okay!! I have edited the following task:
[E][ ] hangout with friends (at: Sat, Sep 18 2021 12:00 pm)
```

### Find - `find [keyword]`

It generates a list of tasks containing the keyword.

Example of usage:

`find friends`

Expected outcome:

The list of tasks containing the keyword.

```
Here are the matching tasks in your list:
1.[E][ ] hangout with friends (at: Thu, Sep 16 2021 02:00 pm)
2.[T][ ] text friends
```

### Done - `done [taskNumber]`

It marks a task as done.

Example of usage:

`done 1`

Expected outcome:

The task is marked as done.

```
Okay!! I have marked this task as done:
[T][X] text friends
```

### List - `list`

It generates the full list of your tasks.

Example of usage:

`list`

Expected outcome:

The list of all tasks.

```
Here are your tasks:
1.[E][ ] hangout with friends (at: Sat, Sep 18 2021 12:00 pm)
2.[T][X] text friends
```

### Bye - `bye`

It closes MyJournal.

Example of usage:

`bye`

Expected outcome:

MyJournal is closed.

```
Bye. Hope to see you again soon!:)
```