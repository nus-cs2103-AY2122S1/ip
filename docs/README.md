# User Guide
This is a project template for a greenfield Java project.

It's named after the Java mascot Duke. Given below are instructions on how to use it. 

You can download it from [Here](https://github.com/Kimowarui/ip/releases)

## Introduction
Duke frees your mind of having to remember things you need to do. It's,

* text-based
* easy to learn
* SUPER FAST to use

![Ui](/docs/Ui.png)

## Features 

### 1. Feature-AddNewTask

Add a task of "todo", "event", "deadline" type.

### 2. Feature-ShowTaskList

Show every task you have added into a list.

### 3. Feature-MarkDone

Mark a task as done.

### 4. Feature-DeleteTask

Delete a task in your list.

### 5. Feature-Save&Load

Save the tasks in the hard disk automatically whenever the task list changes. Load the data from the hard disk when Duke starts up. 

### 6. Feature-FindTask

Find a list of task with given keyword.

## Usage

### 1. `list` - List all the tasks

Duke lists all the tasks added to a TaskList.

Example of usage: 

`list`

Expected outcome:

A list of tasks.

```
Here are tasks in your list: 
1.[T][X] read a book
2. [D][ ] return a book (by: 22/08/2019)
```

### 2. `done <index>` - Mark a task as done

Duke marks a task as done.

Example of usage: 

`done 1`

Expected outcome:

Mark a specific task as done.

```
done:
[T][X] return book
```

### 3. `delete <index>` - Delete a task

Duke remove a task from a TaskList.

Example of usage: 

`delete 1`

Expected outcome:

Delete a specific task.

```
removed:
[T][X] return book
Now you have 1 tasks in the list.
```

### 4. `find <keyword>` - Search tasks

Duke searchs a list of tasks by given keyword.

Example of usage: 

`find book`

Expected outcome:

A list of tasks containing the keyword.

```
Here are the matching tasks in your list:
1.[D][ ] return a book (by: 22/08/2019)
2.[T][ ] read a book
```

### 5. `todo <task>` - Add a Todo task to TaskList

Duke adds a Todo task to a TaskList.

Example of usage: 

`todo read a book`

Expected outcome:

Adds a Todo task into TaskList.

```
added: 
[T][ ] read a book
Now you have 2 tasks in the list.
```

### 6. `event <task> /at <time>` - Add a Event task to TaskList

Duke adds a Event task to a TaskList.

Example of usage: 

`event borrow a book /at 2019=08-22`

Expected outcome:

Adds a Event task into TaskList.

```
added: 
[E][ ] borrow a book (at: 22/08/2019)
Now you have 3 tasks in the list.
```

### 7. `deadline <task> /by <time>` - Add a Deadline task to TaskList

Duke adds a Deadline task to a TaskList.

Example of usage: 

`deadline return a book /at 2019=08-29`

Expected outcome:

Adds a Deadline task into TaskList.

```
added:
[D][ ] return a book (by: 29/08/2019)
Now you have 4 tasks in the list.
```

### 7. `snooze <index> <time>` - Change time of task

Duke changes the time of a task.

Example of usage: 

`snooze 1 2019=08-29`

Expected outcome:

A task with newTiming replaces the original one.

```
You have successfully postpone a task!!!
 Here are the delayed task with new timing:
[D][ ] return a book (by: 29/08/2019)
```

