# User Guide

Iris is a chatbot that helps you keep track of your todos, deadlines and events.

## Features

### Manage tasks

Iris provides a simple, easy to use and intuitive interface for you to manage your tasks.

### Data persists

Tell Iris all your tasks that you're supposed to be doing. Iris never forgets.

### Aesthetically pleasing GUI

Iris uses a calming blue interface to soothe your soul while you look at your list of tasks and get ready to tackle them 💪.

## Usage

### Adding tasks: `todo`, `deadline`, `event`

There are three types of tasks in Iris: todos, deadlines and events.

Format
* `todo[PRIORITY] TASK_NAME`
* `deadline[PRIORITY] DEADLINE_NAME /by EVENT_DATE`
* `event[PRIORITY] EVENT_NAME /at EVENT_DATE`

There are 3 different levels of priority: high, medium and low.
* High priority: `!!`
* Medium priority: `!`
* Low priority (default)

Dates are expected to be in `YYYY-MM-DD` format.

Example(s)
* `todo! write user guide` (medium priority todo)
* `deadline!! submit individual project /by 2021-09-17` (high priority deadline)
* `event birthday celebration /at 2021-09-18` (low priority event)

### Viewing tasks: `list`

View the current state of your todo list with `list`.

Format: `list`

### Search for tasks: `find`

Search for tasks in your todo list that contain a given search term.

Format: `find SEARCH_TERM`

Example: `find CS2103`

### Marking tasks as done: `done`

Mark tasks as done using `done`.

Format: `done INDEX`

Index is expected to be a positive number representing the ID of an existing task.

### Deleting tasks: `delete`

Delete tasks from the todo list using `delete`.

Format: `delete INDEX`

Index is expected to be a positive number representing the ID of an existing task.
