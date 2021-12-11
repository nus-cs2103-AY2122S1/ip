# User Guide

## Features 
There are three types of tasks that you can add into this chatbot-task manager.
* ToDos
* Deadlines
* Events

There are commands/features that you can perform in order to improve your productivity. These commands are described below.

### Adding tasks

Add a new task to the task list.

### Marking tasks as done

Mark a certain task as done.

### Deleting tasks

Delete a task from the task list.

### Finding tasks using keyword search

Retrieve all tasks that contain a particular keyword.

### Displaying all existing tasks

View all the tasks in the task list at once.

### View all Deadlines in chronological order

View all the Deadlines you have, in chronological order.

## Usage

### `bye` - Exit the application

Naruto will display a farewell message and the program will terminate. All existing tasks will be saved.

*Example of usage:*

`bye`

*Expected outcome:*

The program will terminate. When Naruto is launched again, the tasks will still exist.

### `list` - Display all existing tasks

Naruto will display all the existing tasks you have.

*Example of usage:*

`list`

*Expected outcome:*

You will see all the tasks you have, nicely listed out.

### `done` - Marks a task as done

Given a task number, marks the corresponding task as done. 

Format: `done <task number>`

*Example of usage:*

`done 3`

*Expected outcome:*

Task number 3 in your task list will be marked as done. Naruto will give you a response message.

### `todo` - Add a ToDo into the task list

Given a task description, a new todo task with that description will be added.

Format: `todo <description>`

*Example of usage:*

`todo return book`

*Expected outcome:*

A new ToDo with description `return book` will be added. Naruto will give you a response message.

### `deadline` - Add a Deadline into the task list

Given a task description and a date string that is correctly formatted, a new deadline task with that information will be added.

Format: `deadline <description> /by <date in YYYY-MM-DD format>`

*Example of usage:*

`deadline CS2103 iP /by 2021-09-17`

*Expected outcome:*

A new Deadline with the specified description and date will be added. Naruto will give you a response message.

### `event` - Add a Event into the task list

Given a task description and a date-time string, a new event with those information will be added.

Format: `event <description> /at <date-time string>`

*Example of usage:*

`event CS2103 project meeting /at 2021-09-13 8pm`

*Expected outcome:*

A new Event with the specified description and date-time will be added. Naruto will give you a response message.

### `delete` - Delete a task from the task list

Given a task number, the corresponding task will be deleted from the task list.

Format: `delete <task number>`

*Example of usage:*

`delete 2`

*Expected outcome:*

The task with task number 2 will be deleted. Naruto will give you a response message.

### `find` - Retrieve tasks using a keyword search

Given a string as keyword(s), Naruto will return you all the tasks that contain that string sequence.

Format: `find <keyword string>`

*Example of usage:*

`find return book`

*Expected outcome:*

All tasks that contain the string `return book` will be returned.

### `chrono deadlines` - View all Deadlines in chronological order

Naruto will return you all the Deadlines in chronological order.

Format: `chrono deadlines`

*Example of usage:*

`chrono deadlines`

*Expected outcome:*

All deadlines will be returned in chronological order in Naruto's response message.
