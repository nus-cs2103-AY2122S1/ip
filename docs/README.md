# Duke User Guide

## Features Overview
1. Add todo tasks
2. Add deadline tasks
3. Add events

### 1. Add-ToDo

This feature allows you to add a simple task to your task list.

### 2. Add Deadline tasks

This feature allows you to add tasks to be completed by a certain deadline (in yyyy-mm-dd) format.

### 3. Add Events 

This feature allows you to add events at some date and/or time.


## Usage

### `list` - List all added tasks
### `todo <task>` - Add to-do task
  Example: `todo work`

### `deadline <task> /by <yyyy-mm-dd>` - Add deadline task
  Example: `deadline essay /by 2021-09-17`

### `event <task> /at <date/time>` - Add event task
  Example: `event tp meeting /at Sunday 3.30pm`

### `done <task_number>` - Mark task as done
  Example: `done 3`

### `delete <task_number> (task_number_1 ... task_number_n)` - Delete task from list
  Examples of usage: 

  `delete 3` ➡️  deletes task 3

  `delete 1 2` ➡️  deletes tasks 1 and 2

### `bye` - Say bye to Duke!



<!-- 
Example of usage: 

`list (optional arguments)`

Expected outcome:

Returns a simple message if there are no tasks.

```
You have no tasks!
``` -->
