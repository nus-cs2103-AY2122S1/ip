# WorkDone

# User Guide

## Features 

### Feature1: Manage tasks and events

WorkDone allows you to add, view, find, delete a task or event. You can also mark it as done.

### Feature2: Store task list locally

WorkDone will store your tasks automatically after each action, and you will be able to access and modify 
them later easily through WorkDone.

### Feature3: Undo an action

WorkDone enables you to undo an action if it is one of the following:
* add a task
* done/undone a task
* delete a task

Note that if you exit the program, you will not be able to undo the actions in this session anymore.

## Usage

### `todo` - Add a task to be completed

`todo {task description}`
Add a `todo` task with description to the end of the task list.

Example of usage: 

`todo study for midterms`

Expected outcome:

The task 'study for midterms' will be added to the end of the task list.

```
Got it. I've added this task:
 [T][] study for midterms
Now you have xx tasks in the list.
```
### `deadline` - Add a task with a deadline

`deadline {task description} /by yyyy-MM-dd HH:mm`
Add a `deadline` task with description and deadline to the end of the task list.

Example of usage:

`deadline CSxxxx assignment 1 /by 2021-10-20 23:59`

Expected outcome:

The task 'CSxxxx assignment 1' will be added to the end of the task list.

```
Got it. I've added this task:
 [D][] CSxxxx assignment 1 (by: Oct. 20 2021, 23:59)
Now you have xx tasks in the list.
```
### `event` - Add an event

`event {event description} /at yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm`
Add a `event` task with description, starting time and ending time to the end of the task list.

Example of usage:

`event dinner with friends /at 2021-09-18 18:00 to 2021-09-18 20:00`

Expected outcome:

The task 'dinner with friends' will be added to the end of the task list.

```
Got it. I've added this task:
 [E][] dinner with friends (at: Sep. 18 2021, 18:00 to Sep. 18 2021, 20:00)
Now you have xx tasks in the list.
```
### `done` - Mark a task as done

`done {task no.}`
Mark the task with 'task no.' as done.

Example of usage:

`done 1`

Expected outcome:

The first task in the list will be marked as done.

```
Nice! I've marked this task as done:
 [T][X] study for midterms
```
### `undone` - Mark a task as undone

`undone {task no.}`
Mark the task with 'task no.' as undone.

Example of usage:

`undone 1`

Expected outcome:

The first task in the list will be marked as undone.

```
Got it! I've marked this task as undone:
 [T][] study for midterms
```
### `delete` - Delete a task

`delete {task no.}`
Delete the task with 'task no.' from the task list.

Example of usage:

`delete 1`

Expected outcome:

The first task in the list will be deleted.

```
Noted. I've removed this task:
 [T][] study for midterms
Now you have xx tasks in the list.
```
### `clear` - Delete all tasks

`clear`
Delete all tasks from the task list.

Example of usage:

`clear`

Expected outcome:

All the tasks in the list will be deleted.

```
Noted. I've removed all the tasks.
```
### `list` - List all tasks

`list`
List all tasks from the task list.

Example of usage:

`list`

Expected outcome:

All the tasks in the list will be shown.

```
Here are the tasks in your list:
1.[T][] study for midterms
2.[D][] CSxxxx assignment 1 (by: Oct. 20 2021, 23:59)
3.[E][] dinner with friends (at: Sep. 18 2021, 18:00 to Sep. 18 2021, 20:00)
```
### `find` - Find tasks

`find {keyword}`
Find and show tasks containing 'keyword'.

Example of usage:

`find assignment`

Expected outcome:

All the tasks with keyword 'assignment' will be shown.

```
Here are the tasks I found:
2.[D][] CSxxxx assignment 1 (by: Oct. 20 2021, 23:59)
```
### `undo` - Undo a command

`undo`
Undo the last command in the stack if it is one of the following:
* todo
* deadline
* event
* done
* undone
* delete

Example of usage:

`undo`

Expected outcome:

Undo the previous command.

Assume the last command is `todo study for midterms`, then the outcome will be:
```
Noted. I've removed this task:
 [T][] study for midterms
Now you have xx tasks in the list.
```
### `bye` - Exit

`bye`
Exit the program.

Example of usage:

`bye`

Expected outcome:

A window will pop up with the following message:

```
Bye. Hope to see you again soon!
```