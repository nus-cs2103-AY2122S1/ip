# User Guide
Duke is a chatbot help you remember and manage your task. It is:
* text-based
* easy to use
* fast

## Features

### Listing all tasks: `list`

Shows a list of all tasks the user have added

Format: `list`

### Adding a 'todo' task: `todo`

Add a todo task with description to the task list.

Format: `todo {description}`

Example: `todo borrow book`

### Adding an 'event' task: `event`

Add an event task with description and its start time to the task list.

Format: `event {description} /at {startTime}`
* `startTime` should be in the form of yyyy-mm-dd

Example: `event attend lecture /at 2021-09-09`

### Adding a 'deadline' task: `deadline`

Add an event task with description and its end time to the task list.

Format: `event {description} /by {endTime}`
* `endTime` should be in the form of yyyy-mm-dd

Example: `event return book /by 2021-09-09`

### Marking task as done: `done`

Mark a task as already completed.

Format: `done {index}`
* mark the task at the specified `index`
* `index` should be a positive integer smaller than the size of task list.

Example: `done 2`

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete {index}`
* mark the task at the specified `index`
* `index` should be a positive integer smaller than the size of task list.

Example: `delete 2`

### Deleting a task: `find`

Find all tasks containing specified word.

Format: `find {keyWord}`

Example: `find book`

### Editing the time of a task: `update time`

Edit the time of an existing task

Format: `update time {index} {newTime}`
* mark the task at the specified `index`
* `index` should be a positive integer smaller than the size of task list.
* `newTime` should be in the form of yyyy-mm-dd

Example: `update time 2 2021-12-12`

### Editing the description of a task: `update description`

Edit the time of an existing task

Format: `update description {index} {newName}`
* mark the task at the specified `index`
* `index` should be a positive integer smaller than the size of task list. 
  
Example: `update description 2 return book`



