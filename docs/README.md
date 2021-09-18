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

### 1. `todo <task>` - Add to-do task
  Example: `todo work` --> Adds a task called "work"

### 2. `deadline <task> /by <yyyy-mm-dd>` - Add deadline task
  Example: `deadline essay /by 2021-09-17` --> Adds a deadline task called "essay" to be completed by 17 Sep 2021

### 3. `event <task> /at <date/time>` - Add event task
  Example: `event tp meeting /at Sunday 3.30pm` --> Adds an event called "tp meeting" at Sunday 3.30pm
  
### 4. `list` - List all added tasks

### 5. `done <task_number>` - Mark task as done
  Example: `done 3` --> Marks task number 3 as done

### 6. `delete <task_number> (task_number_1 ... task_number_n)` - Delete task(s) from list
  To delete a single task, specify the task number. To delete multiple tasks, specify the task numbers separated by spaces.
  
  Examples of usage: 

  Delete single task: `delete 3` --> deletes task 3

  Delete multiple tasks: `delete 1 2` --> deletes tasks 1 and 2

### 7. `bye` - Say bye to Duke!
  ❗ Note: Saying 'bye' to Duke will not close the GUI application. This is by design so as to allow the user to restart a conversation with Duke easily without havnig to restart the application.



<!-- 
Example of usage: 

`list (optional arguments)`

Expected outcome:

Returns a simple message if there are no tasks.

```
You have no tasks!
``` -->
