# Duke User Guide

## Features Overview
1. Add To-Do Tasks
2. Add Deadline Tasks
3. Add Event Tasks

### Add To-Do tasks

This feature allows you to add simple to-do tasks to your list.

### Add Deadline Tasks

This feature allows you to add tasks with deadlines
and get notified when the deadline is due.

### Add Event Tasks

This feature allows you to add tasks that are events
happening on a certain day and get notified when 
the event is going to happen.

## Usage

1. ### `t <task>` - Add to-do task

Example of usage: 

`t task one`

Expected outcome: Add to-do task called "task one"

2. ### `d <task> /by <yyyy-mm-dd>` - Add deadline task

Example of usage:

`d iP /by 2021-09-20`

Expected outcome:
Adds a deadline task called iP which is 
due on 20th September, 2021

3. ### `e <task> /at <yyyy-mm-dd>` - Add event task

Example of usage:

`e iP Presentation /at 2021-11-17`

Expected outcome:
Adds a event task called iP Presentation which is
on 17th November, 2021

4. ### `list` - List all added tasks
   
5. ### `done <task_number>` - Mark a task as done

Example of usage:

`done 1`

Expected Outcome:
Marks the first task as done.

6. ### `delete <task_number>` - Delete a task from the list

Example of usage:

`delete 1`

Expected Outcome:
Deletes the first task from the list

7. ### `bye` - End the Program

Note: This will not close the GUI application. 
It allows the user to resume the program
without having to restart the application.

### Acknowledgements
Jovyn Tan Li Shyan (@jovyntls)