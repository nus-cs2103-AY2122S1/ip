# Duck User Guide
Duck is a cute and easy way to keep track of your tasks.

![Duck GUI](Ui.png)

## Features 
* [Add Todo](#add-todo-todo)
* [Add Deadline](#add-deadline-deadline)
* [Add Event](#add-event-event)
* [List Tasks](#list-tasks-list)
* [Mark Task as Done](#mark-tasks-as-done-done)
* [Delete Task](#delete-tasks-delete)
* [Find Tasks](#find-tasks-find)
* [Close Duck](#close-duck-bye)
* [Get Help](#get-help-help)

## Usage

### Add Todo: `todo`
Add a new Todo task to your task list.

**Usage Format:** `todo TASK_NAME`

**Example**

User Input: `todo Read Book`

Expected outcome:
```
Got it. I've added this task:
[T][ ] Read Book
Now you have 1 task in your list.
```

### Add Deadline: `deadline`

Add a new Deadline task with a due date to your task list.

**Usage Format:** `deadline TASK_NAME /by YYYY-MM-DD`

**Example**

User Input: `deadline Return Book /by 2021-09-08`

Expected outcome:
```
Got it. I've added this task:
[D][ ] Return Book (by: Sep 8 2021)
Now you have 2 tasks in your list.
```

### Add Event: `event`

Add a new Event task with a date to your task list.

**Usage Format:** `event TASK_NAME /at YYYY-MM-DD`

**Example**

User Input: `deadline Buy Coffee /at 2021-09-22`

Expected outcome:
```
Got it. I've added this task:
[E][ ] Buy Coffee (at: Sep 22 2021)
Now you have 3 tasks in your list.
```

### List Tasks: `list`

List out the tasks on your task list. 

**Usage Format:** `list`

**Example**

```
Here are the tasks on your list:
1. [T][ ] Read Book
2. [D][ ] Return Book (by: Sep 8 2021)
3. [E][ ] Buy Coffee (at: Sep 22 2021)
*quack*
```

### Mark Task as Done: `done`

Cross a task off your task list by marking it as completed.

**Usage Format:** `done TASK_NUMBER`

**Example**

User Input: `done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] Read Book
```

### Delete Task: `delete`

Tidy up your task list by deleting tasks.

**Usage Format:** `delete TASK_NUMBER`

**Example**

User Input: `delete 3`

Expected outcome:

```
Noted. I've removed this task:
[E][ ] Buy Coffee (at: Sep 22 2021)
Now you have 2 tasks in your list.
```

### Find Tasks: `find`

Find tasks easily by searching for them.

**Usage Format:** `find SEARCH_TERMS`

**Example**

User Input: `find book`

Expected outcome:

```
Here are the tasks I found:
[T][ ] Read Book
[D][ ] Return Book (by: Sep 8 2021)
*quack*
```

### Close Duck: `bye`

Close the Duck window directly or by using the `bye` command.

**Usage Format:** `bye`

**Example**
```
Bye. Hope to see you again soon!
*quack*
```

### Get Help: `help`

Find commands easily by asking Duck for help!

**Usage Format:** `help`

**Example**

```
Here's a list of commands that Duck can help you with!
1. todo TASK: Add a Todo task
2. deadline TASK /by YYYY-MM-DD: Add a Deadline task with a specified date
3. event TASK /at YYYY-MM-DD: Add a Event task with a specified date
4. list: Display your task list
5. done TASK_NO: Mark a task as completed
6. delete TASK_NO: Delete a task
7. find SEARCH_TERMS: Search for tasks
8. help: View this list of commands again
*quack*
```

### Have fun using Duck!
