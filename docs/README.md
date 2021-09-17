# User Guide

## Features

This chatbot is able to support saving and loading of your tasks.

By default, all task that are saved are automatically loaded upon launch.

Currently, the available tasks are ToDos, Events, and Deadlines.

## Usage

### `help` - Displays all available commands

### `todo` - Creates a new todo task

Command Format: `todo <description>`

Example of usage: `todo borrow book`

### `deadline` - Creates a new deadline task

Command Format: `deadline <description> /by <date>`

Example of usage: `deadline return book /by 2019-10-15`

### `event` - Creates a new event task

Command Format: `event <description> /at <date>`

Example of usage: `event project meeting /at Mon 2-4pm`

### `list` - Lists all tasks created by the user

### `done` - Marks a task as done

Command Format: `done <index of task>`

The index of the task follows the output from `list`

Example of usage: `done 3`

### `delete` - Deletes a specific task

Command Format: `delete <index of task>`

The index of the task follows the output from `list`

Example of usage: `delete 3`

### `find` - Deletes a specific task

Command Format: `find <description of task>`

The description can only be a one-word description

### `save` - Saves all tasks to a text file

The text file will be automatically loaded upon the next program launch.

All tasks will also be overwrriten.

### `reset` - Deletes all tasks

### `bye` - Shuts down the program

The program will shutdown after a 3 second timer.
