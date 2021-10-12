# User Guide

## Features 

### Add Task

Add your task to the list with the inputs you give

### Complete Task

Mark task that have been completed

### Find Task

Filter your list for specific tasks

### Update Task

Made an error? no worries simply update the task through the update tab

### Delete Task

Easily delete any task that have expired/done

## Usage

### `Gui Based` - No long commands needed to add items

This bot allows for simple entries in the correct text field

### Example of add Task: 

type: `T`

description: `Cook Lunch`

date:

Expected outcome:

Todo task of description cook lunch added to the list

```
Here are the tasks in your list:
1. [T][ ] Cook Lunch
```
### Example of Complete Task: 

index: `1`

Expected outcome:

Task marked with 1 marked as completed

```
Here are the tasks in your list:
1. [T][X] Cook Lunch
```
### Example of find Task: 

filter word: `cook`

Expected outcome:

list will now only show task with "cook" in it (Case-insensitive

```
Here are the matching tasks in your list:
1. [T][X] Cook Lunch
Press load to go back!
```
### Example of Update Task: 

index: `1`

new description: `Eat Lunch`

new date: 

Expected outcome:

Task at index 1 has been updated, and marked as undone.

```
Here are the tasks in your list:
1. [T][ ] Eat Lunch
```
### Example of Delete Task: 

index: `1`

Expected outcome:

Task 1 in the list will be deleted

```
There are no task in your list
```
## FAQ

What if I want to change the type of task?

`As changing the type of task is a large change I believe that user should delete the task completely and add a new one!`

How will I know if I entered some fields wrongly?

`The GUI will automatically show error fields if there are errors in the input!`