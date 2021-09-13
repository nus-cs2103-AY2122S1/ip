# User Guide
Quack Quack! This is Duck!

She will help you manage your daily tasks, without any complaints of course.

## Features

### Tasks

There are a total of 3 types of tasks Duck can help you manage:
* Todos
* Events
* Deadlines

#### Todo

A simple task that isn't time sensitive.

#### Event

A task that will occur at a specified time.

#### Deadline

A task that needs to be completed before a certain time.

### Task Management

The tasks are stored in a list, and you the user can add or remove tasks from the list.
You can view the list of tasks you have, or mark a task as done once you have completed it.

### Tagging

For tasks that require a bit more information, or to classify tasks into certain categories,
you can tag the tasks with user defined tags.

## Usage

### `addTag` - Tags a task

Adds a tag to the specified task

`addTag {TASK_NUMBER} {TAG_NAME}`

* `{TAG_NAME}` cannot contain the characters `|` and `,`
* `{TASK_NUMBER}` must be a positive integer. e.g. 1, 2, 3...

Example of usage:

`addTag 1 exercise`

### `bye` - Exit the application

Exits the application after saying goodbye to Duck.

`bye`

### `deadline` - Add an Event task

Adds a Deadline task into the list.

`deadline {TASK_NAME} /by {DATE}`

* `{TASK_NAME}` cannot contain the character `|`
* `{DATE}` must be in the format of `dd/mm/yyyy`.

Example of usage:

`deadline light jogging /by 10/12/2021`

### `delete` - Removes a task

Deletes a task from the list.

`delete {TASK_NUMBER}`

* `{TASK_NUMBER}` must be a positive integer. e.g. 1, 2, 3...

Example of usage:

`delete 1`

### `deleteTag` - Removes a task

Deletes a tag from the specified task

`deleteTag {TASK_NUMBER} {TAG_NAME}`

* `{TAG_NAME}` cannot contain the characters `|` and `,`
* `{TASK_NUMBER}` must be a positive integer. e.g. 1, 2, 3...

Example of usage:

`deleteTag 1 exercise`

### `done` - Complete a task

Marks a task in the list as complete.

`done {TASK_NUMBER}`

* `{TASK_NUMBER}` must be a positive integer. e.g. 1, 2, 3...

Example of usage:

`done 1`

### `event` - Add an Event task

Adds an Event task into the list.

`event {TASK_NAME} /at {DATE}`

* `{TASK_NAME}` cannot contain the character `|`
* `{DATE}` must be in the format of `dd/mm/yyyy`.

Example of usage:

`event light jogging /at 10/12/2021`

### `find` - Find a task

Finds a task by its name.

`find {KEYWORD}`

* `{KEYWORD}` will be matched as long as it is contained inside the task name.
* `{KEYWORD}` cannot contain the character `|`

Example of usage:

`find jogging`

### `list` - List the tasks

Displays all the tasks user have added.

`list`

### `listTag` - List the tags

Displays all the tags for a specified task.

`listTag {TASK_NUMBER}`

* `{TASK_NUMBER}` must be a positive integer. e.g. 1, 2, 3...

Example of usage:

listTag 1`

### `todo` - Add a Todo task

Adds a Todo task into the list.

`todo {TASK_NAME}`

* `{TASK_NAME}` cannot contain the character `|`

Example of usage: 

`todo light jogging`







