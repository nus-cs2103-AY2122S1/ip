# User Guide

## Features 

### Save tasks

Tell Dude about tasks that you have to complete and he will remember it. 

### To-dos, deadlines, events

Choose from 3 task types depending on the nature of your task:
* To-do for simple tasks.
* Deadlines for tasks you need to complete by some time.
* Events for tasks that go on for a while.

## Usage

### `list` - Display all tasks

Dude responds with your list of tasks

Expected outcome:

A list of tasks and their completion statuses

```
1. [D][ ] English homework (by: 12 Sep 2021)
2. [E][ ] Group project meeting (at:  25 Oct 2021)
3. [T][X] Get pencils
```

### `delete` - Display all tasks

Dude deletes a task.

Example of usage:

`delete INDEX`

Expected outcome:

Dude deletes the task at INDEX.

```
I have removed this task!
   [E][ ] Group project meeting (at:  25 Oct 2021)
You have 2 tasks left.
```
