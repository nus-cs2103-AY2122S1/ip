# User Guide

## Features 

![Image of UI](Ui.png)

### Task List

Duke allows you to keep track of your tasks in a list that is saved to 
your computer!

### Keeping Track of Todos

>`todo` - simple tasks that have no set date or completion time.

Manage your `TODO` tasks in Duke's task list! 

### Keeping Track of Deadlines

>`deadline` - tasks that have to be completed by a specific time.

Never miss a deadline again!

### Keeping Track of Events

>`event` -  tasks that are scheduled to happen at a specific time.

Never forget another event!

## Usage

### `todo` - Adds a ToDo task

Adds a new `todo` task to the task list.

**Command format:** `todo {description}`

**Example of usage:** 

```
todo Do the laundry
```

**Expected outcome:**

A confirmation of the `todo` task that has been added, 
as well as the number of tasks currently in the task list.

```
Got it. I've added this task:  
  [T][ ] Do the laundry
Now you have 1 tasks in the list.
```
### `deadline` - Adds a Deadline task

Adds a new `deadline` task to the task list.

**Command format:** `deadline {description} /by {dd-mm-yyyy HH:mm}`

**Example of usage:** 

```
deadline Assignment 1 /by 31-12-2021 23:59
```

**Expected outcome:**

A confirmation of the `deadline` task that has been added,
as well as the number of tasks currently in the task list.

```
Got it. I've added this task:  
  [D][ ] Assignment 1 (by: Dec 31 2021 23:59)
Now you have 1 tasks in the list.
```
### `event` - Adds an Event task

Adds a new `event` task to the task list.

**Command format:** `event {description} /at {dd-mm-yyyy HH:mm}`

**Example of usage:**

```
event Tutorial 1 /at 15-09-2021 14:00
```

**Expected outcome:**

A confirmation of the `event` task that has been added,
as well as the number of tasks currently in the task list.

```
Got it. I've added this task:  
  [E][ ] Tutorial 1 (at: Sep 15 2021 14:00)
Now you have 1 tasks in the list.
```
### `list` - Shows Task List

Displays the current list of tasks.

**Command format:** `list`

**Example of usage:**

```
list
```

**Expected outcome:**

Displays all the current tasks in a list.

```
Here are the tasks in your list:
1.[T][ ] Do the laundry
2.[D][ ] Assignment 1 (by: Dec 31 2021 23:59)
3.[E][ ] Tutorial 1 (at: Sep 15 2021 14:00)
```

### `done` - Marks a Task as Done

The specified tasks in the task list will be marked as `done`. 
Multiple tasks can be `done` at the same time.

**Command format:** `done {task index} {task index} {...}`

**Example of usage:**

```
done 1 3
```

**Expected outcome:**

A confirmation of the tasks that have been marked as done.

```
Nice! I've marked these tasks as done:
[T][X] Do the laundry
[E][X] Tutorial 1 (at: Sep 15 2021 14:00)
```
### `delete` - Deletes a Task

The specified tasks in the task list will be deleted.
It is possible to `delete` multiple tasks can be at the same time.

**Command format:** `delete {task index} {task index} ...`

**Example of usage:**

```
delete 1 3
```

**Expected outcome:**

A confirmation of the tasks that have been deleted, 
and the remaining number of tasks in the task list.

```
Got it. I've removed these tasks:
[T][X] Do the laundry
[E][X] Tutorial 1 (at: Sep 15 2021 14:00)
Now you have 1 tasks in the list.
```
### `find` - Finds Related Tasks 

Displays a list of tasks relevant to the specified keyword.

**Command format:** `find {keyword}`

**Example of usage:**

```
find assignment
```

**Expected outcome:**

Shows the task list that has been filtered by the keyword.

```
Here are the matching tasks in your list:
1.[D][ ] Assignment 1 (by: Dec 31 2021 23:59)
```

### `bye` - Exits Duke

Says goodbye to Duke and closes the application.

**Command format:**

`bye`

**Example of usage:**

```
bye
```

**Expected outcome:**

Exits the application and closes the application window.