# User Guide for Duke

Welcome to your best task manager **Duke**! 

Duke is a **desktop chatbot for managing tasks**. You can ask duke to help you remember your tasks.

## Introduction
Duke frees you from worry of taking care of everything. It is:

* Text-based!
* Easy to understand!
* Super Fast to use! :grin:

![alt text](https://hangzelin.github.io/ip/Ui.png)

## Features 

### 1.Save&Load

Able to save history tasks into local files and load data from it.

### 2.SearchTask

Able to search a specific task using keyword of time or taskInfo.

### 3.AddNewTask

Able to add a task of type "todo", "event", "deadline" into Duke.

### 4.DeleteTask

Able to delete a task no longer needed.

### 5.MarkDone

Able to mark a finished task as done.

## Usage

### 1.`list` - list all the tasks

Duke displays the list of tasks stored.

Example of usage: 

`list (suppose originally there are four tasks in the list)`

Expected outcome:

A list of all tasks stored in Duke.

```
Here are all the tasks in your list:
1.[T][X] borrow book.
2.[D][] return book (by: Dec 02 2019 18:00)
3.[D][X] return book (by: Sep 20 2020 15:25)
4.[E][X] project meeting (at: I don't know the time.)
```

### 2.`done <index>` - Mark a task as done 

Duke marks a finished task as done. 

Example of usage: 

`done 2`

Expected outcome:

Marks a specific task as done.

```
Nice! I've marked this task as done:
[D][] return book (by: Dec 02 2019 18:00)
```

### 3.`delete <index>` - Delete a task in Duke 

Duke deletes a task user no longer needs. 

Example of usage: 

`delete 4`

Expected outcome:

Deletes a task in a specific index.

Also display the number of tasks left.

```
Noted. I've removed this task:
[E][X] project meeting (at: I don't know the time.)
Now you have 3 tasks in the list.
```

### 4.`tell <time>` - Find tasks with keyword of time. 

Duke tells tasks user hope to search on a specific time. 

Example of usage: 

`tell Sep 23`

Expected outcome:

All tasks match the keyword of time in Duke.

```
Here are all the tasks taking place on the date you give me:
1.[D][X] return book (by: Sep 23 2020 15:25)
```

### 5.`find <task>` - Find tasks with keyword of taskInfo. 

Duke finds tasks user hope to search by keywords of the taskInfo. 

Example of usage: 

`find return book`

Expected outcome:

All tasks match the keyword of taskInfo in Duke.

```
Here are all the matching tasks in your list:
1.[D][X] return book (by: Dec 02 2019 18:00)
2.[D][X] return book (by: Sep 23 2020 15:25)
```

### 6.`todo <task>` - Add a task of type todo. 

Duke creates a new task of type todo and adds it into the list. 

Example of usage: 

`todo finish assignemnt`

Expected outcome:

Adds a new task of todo type into taskList in Duke.

```
Got it. I've added this task:

[T][] finish assignment
Now you have 5 tasks in the list.
```

### 7.`deadline <task> /by <time>` - Add a task of type deadline. 

Duke creates a new task of type deadline and adds it into the list. 

Example of usage: 

`deadline CS2103 Ip /by 17/9/2021 1600`

Expected outcome:

Adds a new task of deadline type into taskList in Duke.

```
Got it. I've added this task:

[D][] CS2103 Ip (by: Sep 17 2021 16:00)
Now you have 6 tasks in the list.
```

### 8.`event <task> /at <time>` - Add a task of type event. 

Duke creates a new task of type event and adds it into the list. 

Example of usage: 

`event Watch NBA 2022 New Season /at 2021-10-04`

Expected outcome:

Adds a new task of event type into taskList in Duke.

```
Got it. I've added this task:

[E][] Watch NBA 2022 New Season (at: Oct 04 2021 00:00)
Now you have 7 tasks in the list.
```

### 9.`undo` - Undo a most recent operation 

Duke undose a mostly executed command.

It can be:
1. Undo adding a task.
2. Undo deleting a task.
3. Undo marking a task as done.

Example of usage: 

`undo`

Expected outcome:

Undo a most recent operation in Duke.

```
Okay, I have helped undo your most recent command!
Good! I have undone your newly added task!
```

### 10.`help` - Print a simple version of User Guide. 

Duke gives user a brief introduction of his features and points to note.

Example of usage: 

`help`

Expected outcome:

Prints a brief User Guide.

```
I support the following commands:
1) list: See all tasks in your list.
2) done: Mark a specific task as done.
3) delete: Delete a specific task.
4) tell: Find tasks by keyword of time.
5) find: Find tasks by keyword of info.
6) undo: Undo a most recent command.
7) todo: Create a task of type 'todo'.
8) deadline: Create a task of type 'todo'
9) event: Create a task of type 'event'
10) help: Take a look user guide!

 -- Points to Note -- 
1) Time format for Deadline is '/by'.
2) Time format for Event is '/at'.
3) yy-mm-dd time format is supported.
4) dd/mm/yy hhmm time format is supported.

Hope you enjoy your experience in Duke!
```

## Points to note: ✋
* Duke only supports time format of: 

  **1. yy-mm-dd.**
  
  **2. dd/mm/yy hhmm(minute).**
* Deadline should have "/by" as prefix.
* Event should have "/at" as prefix. 

**Hope you enjoy your experience in Duke!**
