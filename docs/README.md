# User Guide

## Features 

### Adding and Removing Tasks

Duke allows you to add and remove tasks. There are three types of task Todos, Deadlines and Events.

Todo - A task that needs to be done but does not have a specific date to do it by.

Deadline - A task that needs to be done by a certain date.

Event - A task that needs to be done on a specific date.

### Marking the completion of tasks

Duke allows you to mark the completion of tasks. This helps you to focus on the tasks that are yet to be completed. 

### Find tasks by name

Duke allows you to find tasks by name. Duke can handle a large number of tasks and the search feature enables you to 
find your tasks in a convenient manner.

### Edit tasks

Duke allows you to edit the description and the date of tasks. So, you do not need to worry about making a mistake when
adding new tasks.

## Usage

All arguments are required unless stated otherwise. The order of
the arguments do not matter.

### `todo` - Adds a todo

Adds a todo the existing list of tasks.

Arguments

`/desc` - Description of the todo

Example of usage: 

`todo /desc brush teeth`

Adds a todo with the description "brush teeth".

### `deadline` - Adds a deadline

Adds a deadline the existing list of tasks.

Arguments

`/desc` - Description of the deadline

`/date` - Date of the deadline to be specified in YYYY-MM-DD format

Example of usage:

`deadline /desc do homework /date 2021-09-14`

Adds a deadline with the description "do homework" with the 
date set to 14 September 2021.

### `event` - Adds an event

Adds a event the existing list of tasks.

Arguments

`/desc` - Description of the event

`/date` - Date of the event to be specified in YYYY-MM-DD format

Example of usage:

`event /desc project meeting /date 2021-09-14`

Adds a event with the description "project meeting" with the
date set to 14 September 2021.

### `done` - Marks a task as done

Marks a task as done.

Arguments

`/sn` - Serial number of the task

Example of usage:

`done /sn 1`

Marks a task with serial number 1 as done.

### `delete` - Deletes a task

Deletes a task.

Arguments

`/sn` - Serial number of the task

Example of usage:

`delete /sn 1`

Deletes a task with serial number 1.

### `update` - Edits a task

Edits a task. The date  argument is not to be used when editing todos.

Arguments

`/sn` - Serial number of the task to edit

`/desc` - New description of the task (Optional)

`/date` - New date of the the task (Optional)

Examples of usage:

`update /sn 1 /desc Project meeting`

Edits the task with serial number 1 by updating only the description to "Project Meeting".

`update /sn 1 /date 2021-10-10`

Edits the task with serial number 1 by updating only the date to 10 October 2021.

`update /sn 1 /desc Project meeting /date 2021-10-10`

Edits the task with serial number 1 by updating the description to "Project Meeting" and the date to 10 October 2021.

### `find` - Finds a task

Finds a task which contains the query in its name. Do note that the serial numbers 
returned by this command are not valid for updating or deleting tasks. Use the 
`list` command to display the full list of tasks and the correct serial numbers
before performing such operations.

Arguments

`/query` - Query to find for

Example of usage:

`find /query project`

Finds all tasks with "project" in its name.

### `list` - List all tasks

List all tasks. Meant to be used after the `find` command
to display the full list of tasks again.

Arguments

No Arguments

Example of usage:

`list`

Displays the full list of tasks.

### `bye` - Closes the application

Closes the application

Arguments

No Arguments

Example of usage:

`bye`

Closes the application.
