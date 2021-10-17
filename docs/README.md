# Joker
![UI Screenshot](Ui.png)

Feeling disorganised and messy? Never fear, Joker is here! Why so serious anyway?
This bot allows you to add todo tasks, deadline and event tasks with deadline functionality!

## Features

### Task Management

Joker's core feature is to help you with your task management.
There are 3 types of tasks:

- Todo: Todo task with a description.
- Deadline: Deadline task with a description and a deadline.
- Event: A task with a description and the event timing.

Joker allows users to add and delete tasks to and from a list, mark them as done, and list all tasks.

## Usage

### `todo` - Add a todo Task

Adds a todo task with a description to the list.
##### Format:
`todo TASK`
##### Example:
`todo Homework` adds `Homework` as a todo task to the list.

### `deadline` - Add a deadline Task

Adds a deadline task with a description and deadline to the list.
##### Format:
`deadline TASK /by yyyy-MM-dd`
##### Example:
`deadline Send emails /by 2020-07-05` adds `Send emails` as a deadline task with the deadline on `5/7/2020`.

### `event` - Add an event Task

Adds an event task with its description and event timing to the list.
##### Format:
`event TASK /at yyyy-MM-dd`
##### Example:
`event Dinner and Dance /at 2020-07-05` adds `Dinner and Dance` as an event task with happening on `5/7/2020`.

### `list` - List all tasks
Lists down all the tasks in the to-do list, including a column  indicating which ones were marked done and undone.
##### Format:
`list`

### `done` - Mark task as done
Marks tasks with a specified index in the list as done.
##### Format:
`done INDEX`

** `INDEX` has to be a positive integer.
##### Example:
`done 4` marks the fourth task in the to-do list as done.

### `delete` - Deletes task
Deletes the task with a specified index from the list.
##### Format:
`delete INDEX`

** `INDEX` has to be a non-negative integer.
##### Example:
`delete 5` deletes the fifth task in the to-do list.


### `bye` - Saves tasks in duke.
Prompts a goodbye message from duke and saves your data to a txtfile.
##### Format:
`bye`