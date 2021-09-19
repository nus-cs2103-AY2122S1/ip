#Duke User Guide

Duke is an application designed to help you keep track and manage your various tasks.

This project was built from Project Duke.

## Features 
1. Add task
    * Todo task
    * Deadline task
    * Event task
2. Delete task
3. List tasks
4. Mark task as done
5. Find task
6. Update task
7. Exit


## Usage

### 1. Add task

Add a todo, deadline, or event task to the list of tasks. Each task type has a different command.

#### `todo` - Adds todo task to the list
Command format: `todo [description]`

Example of usage: `todo return book`

Expected outcome:

Duke will response with
```
Got it. I've added this task:
[T][ ] return book
Now you have 1 tasks in the list

```

---
#### `deadine` - Adds deadline task to the list
Command format: `deadline [description] /by [date]`

__Note:__ The date should be in the format _yyyy-mm-dd_

Example of usage: `deadline submit work /by 2021-05-02`

Expected outcome:

Duke will response with
```
Got it. I've added this task:
[D][ ] submit work (by: May 05 2021)
Now you have 2 tasks in the list
```
---

#### `event` - Adds event task to the list
Command format: `event [description] /at [time]`

Example of usage: `event attend party /at Sunday, 6pm`

Expected outcome:

Duke will response with
```
Got it. I've added this task:
[E][ ] attend party (at: Sunday, 6pm)
Now you have 3 tasks in the list
```
---

### 2. Delete task

#### `delete` - Deletes a task from the list
Command format: `delete [task index]`

Example of usage: `delete 2`

Expected outcome:

Duke will response with
```
Noted. I've removed this task:
[D][ ] submit work (by: May 05 2021)
Now you have 2 tasks in the list
```
---

### 3. List tasks

#### `list` - Lists out the current tasks
Command format: `list`

Expected outcome:

Duke will response with
```
Here are the tasks in your list:
1.[T][ ] return book
2.[E][ ] attend party (at: Sunday, 6pm)
```
---

### 4. Marks task as done

#### `done` - Marks a task in the list as done
Command format: `done [task index]`

Example of usage: `done 2`

Expected outcome:

Duke will response with
```
Nice! I've marked this task as done:
[E][X] attend party (at: Sunday, 6pm)
```
---

### 5. Find task

#### `find` - Finds tasks with a specific keyword in the list
Command format: `find [keyword]`

Example of usage: `find book`

Expected outcome:

Duke will response with
```
Here are the matching tasks in your list:
1.[T][ ] return book
```
---

### 6. Update task

#### `updates` - Updates an attribute of a task
Command format: `update [task index] [attribute] /[new value]`

__Note:__ The attributes for each task type are:  
- todo task: `description`
- deadline task: `description` and `date`
- event task: `description` and `time`
Example of usage: `update 2 time /Wednesday, 6pm`

Expected outcome:

Duke will response with
```
Got it. I've updated the task to be:
[E][X] attend party (at: Wednesday, 6pm)
```
---

### 7. Exit

#### `bye` - Exits the application
Command format: `bye`

Expected outcome:

Duke will response with
```
Bye. Hope to see you again soon!
```
