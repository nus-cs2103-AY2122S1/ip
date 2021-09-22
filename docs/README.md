# Alexa User Guide

![Alexa Preview](https://github.com/DonnyS57/ip/blob/master/docs/Ui.png)

Alexa is a free and easy-to-use chat bot that acts as a personal assistant. It can help you keep track of your tasks with up to 3 different types of tasks available!

## Features

### Keyboard-Friendly GUI

Alexa has a user-friendly interface that is easy on the eyes. Simply type up the commands and you're good to go!

### Manage Tasks

Alexa can manage 3 different types of tasks. These tasks include:

- [x] Todo - Tasks with no deadline
- [x] Deadline - Tasks with a deadline
- [x] Event - Tasks with a date attached to it 

These tasks can be added, viewed, marked as complete. Once you're done with it you can delete it as well!

## Usage

### Creating Tasks

Alexa can create 3 different types of tasks. These tasks can be created using the following syntax:
```
todo {description}
deadline {description} /by {dd/mm/yyyy}
event {description} /at {dd/mm/yyyy}
```
**Example of use:**
1. Creating todo task:
```
todo Exercise
```
2. Creating deadline task:
```
deadline iP /by 23/09/2021
```
3. Creating event task:
```
event Formal Dinner /at 30/11/2021
```
**Expected outcome:**
1. Todo task
```
Alright. I'm adding this task:
 [T][ ] Exercise
Now there are 1 tasks in the list
```
2. Deadline task
```
Alright. I'm adding this task:
 [D][ ] iP (by:23 Sep 2021) 
Now there are 2 tasks in the list
```
3. Event task
```
Alright. I'm adding this task:
 [E][ ] Formal Dinner (at:30/11/2021) 
Now there are 3 tasks in the list
```
--------------------------------------------------------------------

### Viewing Tasks

Alexa can easily list all your current tasks using the list command.

These tasks are listed based on the order they were added.

**Example of use:**
```
list
```
**Expected outcome:**
If there are tasks in your list:
```
Here are your current tasks!
1. [T][ ] Exercise
2. [D][ ] iP (by:23 Sep 2021)
3. [E][ ] Formal Dinner (at:30/11/2021)
```
If there are no tasks in your list:
```
You currently have no pending tasks!
```
--------------------------------------------------------------------
### Finding Tasks

Alexa can help you find your tasks if you have difficulty trying to find a task you have entered before!
You need to search using their title.

**Example of use:**
```
find Exercise
```
**Expected outcome:**
If the task matching the description is found:
```
Here are the matching tasks I found:
1.[T][ ] Exercise
```
If there are no matching tasks:
```
Sorry! I can't find any task that matches your keyword
```
--------------------------------------------------------------------
### Completing Tasks

Alexa can mark your tasks to indicate that you have completed them!
This will be shown to you when you use the list command. You call upon the command by using this syntax:
```
done {number of Task}
```
**Example of use:**
```
done 1
```
**Expected outcome:**
```
Congratulations on finishing this task!
[X] Exercise
```
--------------------------------------------------------------------
### Deleting Tasks

Alexa can delete your tasks off the list once you are done with them.
The task will be removed from your list. You call upon the command by using this syntax:
```
delete {number of Task}
```
**Example of use:**
```
delete 1
```
**Expected outcome:**
```
Okay! I have deleted the task for you.
[T][X] Exercise
Now there are 0 tasks in the list
```
--------------------------------------------------------------------
