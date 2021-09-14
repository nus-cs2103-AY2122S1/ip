# DukePro

Welcome to DukePro. This app houses Duke, a friendly chat bot who can help you manage your tasks effectively.

## Quick start

**Prerequisite: Ensure that you have already installed Java 11.**

1. Download DukePro [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
2. Move the jar file `duke.jar` to the folder that you want to be the home folder for DukePro
3. Open this folder in command window
4. Run this command `java -jar duke.jar`
5. Enjoy the app 

## Features

### Add a to-do task `todo`

Adds a to-do task into your list of tasks.

Format: `todo TASK_DESCRIPTION`

Example: `todo Watching TV`



### Add an event task `event`

Adds an event task into your list of tasks. An event task has a time at which it will happen.

Format: `event TASK_DESCRIPTION /at yyyy-MM-dd HH:mm`

Example: `event Final Exam /at 2021-11-20 09:00`



### Add a deadline task `deadline`

Adds a deadline task into your list of tasks. A deadline task has a time before which the task must be completed.

Format: `deadline TASK_DESCRIPTION /by yyyy-MM-dd HH:mm`

Example: `deadline Assignment /by 2021-11-20 12:00`



### List all your tasks `list`

Lists all the tasks in your list of tasks in the order of when they were added. 

Format: `list`



### Mark a task as done `done`

Marks a task in your list of tasks as done. The `TASK_INDEX` will be the number assigned to the task by the `list` command.

Format: `done TASK_INDEX`

Example: `done 2`



### Delete a task `delete`

Deletes a task in your list of tasks as done. The `TASK_INDEX` will be the number assigned to the task by the `list` command.

Format: `delete TASK_INDEX`

Example: `delete 2`



## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
