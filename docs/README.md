# Duke Bot User Guide

## Features 

### Add Task:
Adds one of the following task to your task list:
1. Todo 
2. Event
3. Deadline 

### Mark Task as Done:
Marks one of the task in your task list as done

### Delete Task:
Deletes one of the task in your task list

### Show Tasks:
Shows all of your current tasks

### Find Task(s):
Finds all task that contains given keyword

## Usage

### 1. `todo <description>` - Adds a todo task

Adds a todo task to your task list.

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][] read book
Now you have 1 task in your list.
```

### 2. `event <event description> /at <dd/MM/yy> <HHmm>-<HHmm>` - Adds an event task

Adds an event task to your task list.

Example of usage:

`event Math Exam /at 23/11/21 1700-1830`

Expected outcome:

```
Got it. I've added this task:
[E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
Now you have 2 tasks in your list.
```

### 3. `deadline <deadline description> /by <dd/MM/yy> <HHmm>` - Adds a deadline task

Adds a deadline task to your task list.

Example of usage:

`deadline Math Assignment /by 14/09/21 2359`

Expected outcome:

```
Got it. I've added this task:
[D][] Math Assignment (by: Sep 14 2021 11:59PM)
Now you have 3 tasks in your list.
```
### 4. `done <task index>` - Marks task as done

Marks the task at this index as done

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
1. [T][X] Read a book 
2. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
3. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```

### 5. `delete <task index>` - Deletes a task

Deletes a task with this index from your task list.

Example of usage:

`delete 1`

Expected outcome:

```
Got it. I've removed the following task:
[T][X] read book
Now you have 2 tasks in your list.
```

### 6. `list` - Shows all tasks

Shows all your current tasks

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
2. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```

### 7. `find <keyword>` - Find task(s) containing keyword

Find task(s) that has this keyword

Example of usage:

`find Math`

Expected outcome:

```
Here are the tasks in your list:
1. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
2. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```
