# User Guide

```
     ____________________________  ______
    /  _______/  _____   / ____  \/ _   |
   /  /______   /    /  / /____/ / /_|  |
  /______   /  /    /  /  _   __/  __   |
 _______/  /  /____/  /  / \  \/  /  |  |
/_________/__________/__/   \__\_/   |__|
```

Sora is a desktop application for managing Tasks. The main mode of input is via Command Line Interface (CLI) with a
Graphical User Interface (GUI) to show the output.

* [Features](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#features)
    * [Viewing Help: `help`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#viewing-help-help)
    * [Exiting the program : `bye`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#exiting-the-program--bye)
    * [Listing all tasks : `list`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#listing-all-tasks--list)
    * [Adding a task](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#adding-a-task)
        * [Todo: `todo`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#todo-todo)
        * [Deadline: `deadline`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#deadline-deadline)
        * [Event: `event`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#event-event)
    * [Marking a task as done: `done`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#marking-a-task-as-done-done)
    * [Deleting a task: `delete`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#deleting-a-task-delete)
    * [Finding a task: `find`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#finding-a-task-find)
    * [Sorting the list of tasks: `sort`](https://github.com/SkyBlaise99/ip/blob/master/docs/README.md#sorting-the-list-of-tasks-sort)

## Features

### Viewing Help: `help`

Show the help message, which contains some information about the app.

Format: `help`

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Listing all tasks : `list`

Shows a list of all tasks, and details regarding the tasks.

Format: `list`

### Adding a task

Adds any task of the following types into the list of tasks.

#### Todo: `todo`

Adds a todo to the list of tasks.

Format: `todo [description]`

#### Deadline: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline [description] /by [dd/MM/yy] [HHmm]`

#### Event: `event`

Adds an event to the list of tasks.

Format: `event [description] /at [dd/MM/yy] /from [HHmm] /to [HHmm]`

### Marking a task as done: `done`

Marks a task as done.

Format: `done [task number]`

### Deleting a task: `delete`

Deletes a task.

Format: `delete [task number]`

### Finding a task: `find`

Finds a list of tasks that match the keyword.

Format: `find [keyword]`

### Sorting the list of tasks: `sort`

Sorts the list by date and time.

Format: `sort [-r]`
