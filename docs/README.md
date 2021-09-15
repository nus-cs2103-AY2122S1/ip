# User Guide

## Features 

### Interactive Graphic User Interface (GUI)

Displays the Virushade application with a graphic user interface. 

This display makes it easy to see user input history, and the interactions with Virushade.

The interface is set to be interactive, every input by the user is being met with a reply.

```
eg. Virushade greets the user with a special message everytime the application is being launched,
and replies with a sinister closing message when the user chooses to close the application.
 ```


### Task manager

Virushade works as an interactive task list. 
There are 3 types of tasks that the user can enter:

1. Todo

    `These represent tasks that are not that urgent, or do not have any stipulated deadline.`
2. Deadline

    `These represent tasks that have a due date.`
3. Event

    `These represent tasks that are to be done or conducted in a certain specified time-frame.`

Users can conveniently perform functions such as find and sort on top of other must-have 
functions typical of a task list. 

### Loading of files

This ensures that all tasks that the user have inputted are saved. Upon future opening, 
Virushade will load the data from these files and hence preserve all tasks that were 
entered on the last use.

## Usage

### `list` - Lists down all input tasks.

Lists down all input tasks currently in the task list.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] Manifest in a Virology Lab
```

### `todo` - Adds a todo task.

Adds a task of type 'todo' to Virushade.

Example of usage:

`todo Infiltrate Computer Systems.`

Expected outcome:

A message acknowledging the adding of the task into Virushade.

```
Added: Infiltrate Computer Systems
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline task.

Adds a task of type 'deadline' to Virushade.

Examples of usage: 

`deadline Infect Before Defense Update /by Sunday`

Expected outcome: 

A message acknowledging the adding of task into Virushade.

```
Added: deadline Infect Before Defense Update
Now you have 3 tasks in the list.
```

### `event` - Adds an event task.

Adds a task of type 'event' to Virushade.

Examples of usage:

`event Target and Destroy Systems /at 0000`

Expected outcome:

A message acknowledging the adding of task into Virushade.

```
Added: Target and Destroy Systems
Now you have 4 tasks in the list.
```

### `sort`

Sorts the task list in Virushade according to the sorting specifications.

Examples of usage:
`sort type`
`sort name`

Expected outcome:

A message acknowledging the sorting.

```
Tasks sorted by type successfully.
Tasks sorted by name successfully.
```

### `find` - Finds the task within Virushade.

Finds the task in Virushade according to the words after 'find'.

Examples of usage:
`find Com`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] Infiltrate Computer Systems
```

### `done` - Marks a selected task as done.

Marks the task as done according to the input index.

Examples of usage:
`done 3`

Expected outcome:
```
Nice! I have marked this task as done:
[E][x] Target and Destroy Systems /at 12:00AM
```

### `delete` - Deletes a selected task.

Removes the task from the list according to the input index.

Examples of usage:
`delete 2`

Expected outcome:
```
Noted. I've removed this task:
[T][ ] Infiltrate Computer Systems
You have 2 tasks in the list.
```

### `bye` - Closes Virushade and orders a shutdown.

Examples of usage:

`bye`

Expected outcome:

```
You may run, you may hide, but you will never escape my infections!
```

## Command Summary

| action | Format | Examples|
| -------| -------|---------|
| list | list | --- |
| todo | todo {description} | todo Say hello|
| deadline | deadline {description} /by {day} | deadline homework /by Sunday |
| event | event {description} /at {time} | event eat /at 1700 |
| sort | sort {mode of sorting} | sort type |
| find | find {string} | find you |
| done | done {index} | done 1 |
| delete | delete {index} | delete 1 |
| bye | bye | --- |
