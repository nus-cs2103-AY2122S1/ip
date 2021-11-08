# User Guide

## Features 

### Add task

add a task based on its task type (ToDo, Deadline, Event) to the Duke bot

### Show task list

returns a list of all the tasks in the bot


### Delete task

delete a task from the Task List

### Mark task as done

mark a task as done from the Task List

### Find Task

find a task from the Task List

### Add notes

add a short note snippet for a task 

### Show notes

show the notes of the respective task

## Usage

### `todo` - add a task of type 'ToDo'

Example of usage: 

`todo borrow book`

<br />

### `deadline` - add a task of type 'Deadline'

Examples of usage:

`deadline project submission /by 2021-09-19 1800`

`deadline return book / by 21/10/2021 1300`

<br />

### `event ` - add a task of type 'Event'

Examples of usage:

`event project meeting /at 2021/09/20 1300`

`event family gathering /at 22/10/2021 1900`

<br />

### `list` - returns a list of all the added tasks
<br />

### `done` - marks respective task as done

Example of usage:

`done 2 // marks task number 2 on the task list as done`

<br />

### `delete` - deletes respective task in the task list

Example of usage:

`delete 4 // deletes task number 4 from the task list`

<br />

### `find` - finds task from task list based on keyword

Example of usage:

`find borrow book`

<br />

### `addNote` - adds notes to the respective task 

Example of usage:

`addNote read book / textbook on algorithms`

<br />

### `showNote` - show notes of the respective task

Example of usage:

`showNote project meeting`

<img src="docs/images/Ui.png">
