# Duke Bot User Guide
This is a greenfield Java project. It's named after the Java mascot Duke. Given below are instructions on how to use it.

## Quick Start
1. Download `duke.jar` [here](http://github.com/dt-td/ip/releases) 
2. Move `duke.jar` to the directory you want to run it.
3. Go to your terminal and run the command `java -jar duke.jar`.
4. If you see an external window pop out, you are good to go!   

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

### Quit Applcation:
Quits the application and closes windows

## Usage

### 1. Adds a todo task: `todo` 

Adds a todo task to your task list.

Format: `todo <description>` 

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T][] read book
Now you have 1 task in your list.
```

### 2. Adds an event task: `event`

Adds an event task to your task list.

Format: `event <event description> /at <dd/MM/yy> <HHmm>-<HHmm>`

Example of usage:

`event Math Exam /at 23/11/21 1700-1830`

Expected outcome:

```
Got it. I've added this task:
[E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
Now you have 2 tasks in your list.
```

### 3. Adds a deadline task: `deadline` 

Adds a deadline task to your task list.

Format: `deadline <deadline description> /by <dd/MM/yy> <HHmm>`

Example of usage:

`deadline Math Assignment /by 14/09/21 2359`

Expected outcome:

```
Got it. I've added this task:
[D][] Math Assignment (by: Sep 14 2021 11:59PM)
Now you have 3 tasks in your list.
```
### 4. Marks task as done: `done` 

Marks the task at this index as done

Format: `done <task index>` 

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
1. [T][X] Read a book 
2. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
3. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```

### 5. Deletes a task: `delete` 

Deletes a task with this index from your task list.

Format: `delete <task index>` 

Example of usage:

`delete 1`

Expected outcome:

```
Got it. I've removed the following task:
[T][X] read book
Now you have 2 tasks in your list.
```

### 6. Shows all tasks: `list` 

Shows all your current tasks

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
2. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```

### 7. Find task(s) containing keyword: `find`

Find task(s) that has this keyword

Format: `find <keyword>`

Example of usage:

`find Math`

Expected outcome:

```
Here are the tasks in your list:
1. [E][] Math Exam (at: Nov 23 2021 5:00PM-6:30PM)
2. [D][] Math Assignment (by: Sep 14 2021 11:59PM)
```

### 8. Quits the application: `bye` 

Quits the application and closes the window

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again!
```
