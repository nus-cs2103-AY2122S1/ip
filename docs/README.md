# User Guide

---

Duke is a desktop todo-list application that allows user to manage
their tasks through text-based interaction with a GUI (Graphical User Interface)

![Duke showcase](./Ui.png)

## Content

---

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Usage](#usage)

## Quick Start

Quick guide to getting Duke set up

---

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) 
installed on your computer
2. Download and extract the Duke.jar file to your desired location
3. For Mac users, double-click to open the app
4. For Windows users, open command prompt and change directory to Duke.jar's
directory. Replace DUKE_JAR_DIRECTORY with your corresponding Duke.jar's
directory in the following command.

```
cd DUKE_JAR_DIRECTORY
```

5. (Continuing from 4) In the same command prompt, run this command to start Duke

```
java -jar Duke.jar
```

6. Duke application will run and greet you with

```
Duke: Hello! I'm Duke
What can I do for you?
```



## Features 

Quick summary of Duke's core features

**Note**: tasklist refers to Duke's storage for tasks

---

### 1. [Add Todo](#todo---adds-todo-task)

Adds an undone task to your tasklist

### 2. [Add Event](#event---adds-event-task)

Adds an undone task with a date to your tasklist

### 3. [Add Deadline](#deadline---adds-deadline-task)

Adds an undone task with a date to your tasklist

### 4. [Add Period](#period---adds-period-task)

Adds an undone task with a start & end date  to your tasklist

### 5. [List](#list---lists-all-tasks)

Shows all tasks in your tasklist

### 6. [Mark as Done](#done---marks-a-task-as-done)

Marks a task in your tasklist as done

### 7. [Delete](#delete---deletes-a-task)

Deletes a task from your tasklist

### 8. [Find](#find---find-a-task-by-keyword)

Searches for tasks with descriptions matching a specific keyword

### 9. [Date](#date---find-a-task-by-date)

Searches for tasks with a specific date



## Usage

Usage of features are grouped by starting command with examples provided
to supplement explanation

**Note**: Expected outcome refers to a sample Duke visual response 
to user via GUI. Total number of tasks or tasks shown **may differ** 
depending on your existing tasklist

**Note**: When referring to **dd/mm/yyyy** format, it is not a 
must to pad your input with zeroes. For instance, dates such 
as 01/01/2021, 1/1/2021, 1/01/2021 and 01/1/2021 are all acceptable

---

### `todo` - Adds Todo task

Adds a Todo task to your tasklist

Must Contain:
* Task description: Short description of your task

Example of usage: 

`todo finish homework`

Expected outcome:

```
Duke: Got it. I have added this task:
[T][ ] finish homework
Now you have 1 tasks in the list.
```

### `event` - Adds Event task

Adds an Event task to your tasklist

Must Contain:
* Task description: Short description of your task
* Descriptor: `/at` to separate the task description and date
* Date: date in dd/mm/yyyy format

Example of usage:

`event pick up daughter from school /at 01/01/2021`

Expected outcome:

```
Duke: Got it. I have added this task:
[E][ ] pick up daughter from school (at: 1 January 2021)
Now you have 1 tasks in the list. 
```

### `deadline` - Adds Deadline task

Adds a Deadline task to your tasklist

Must Contain:
* Task description: Short description of your task
* Descriptor: `/by` to separate the task description and date
* Date: date in dd/mm/yyyy format

Example of usage:

`deadline submit assignment /by 01/01/2021`

Expected outcome:

```
Duke: Got it. I have added this task:
[D][ ] submit assignment (by: 1 January 2021)
Now you have 1 tasks in the list.
```

### `period` - Adds Period task

Adds a Period task to your tasklist

Must Contain:
* Task description: Short description of your task
* Descriptor: `/within` to separate the task description and date period
* Date period: start date and end date in dd/mm/yyyy format 

**Note**: Date period must be valid in the sense that the start date
must be less than the end date

Example of usage:

`period learn java /within 01/01/2021 02/02/2021`

Expected outcome:

```
Duke: Got it. I have added this task:
[P][ ] learn java (from: 1 January 2021 to: 2 February 2021)
Now you have 1 tasks in the list.
```

### `list` - Lists all tasks

Lists all existing tasks in your tasklist

Example of usage:

`list`

Expected outcome:

```
Duke: Here are the tasks in your list:
1.[T][ ] finish homework
2.[E][ ] pick up daughter from school (at: 1 January 2020)
3.[D][ ] submit assignment (by: 1 January 2021)
4.[P][ ] learn java (from: 1 January 2021 to: 2 February 2021)
```

### `done` - Marks a task as done

Marks task at an index in your tasklist as done

Must Contain:
* Index: Index is 1-based (Starting from 1) and must be an integer

Example of usage:

`done 1`

Expected outcome:

```
Duke: Nice! I've marked this task as done: 
[T][X] finish homework
```

### `delete` - Deletes a task

Deletes a task from your tasklist

Must Contain:
* Index: Index is 1-based (Starting from 1) and must be an integer

Example of usage:

`delete 1`

Expected outcome:

```
Duke: Noted. I've removed this task:
[T][X] finish homework
Now you have 3 tasks in the list.
```

### `find` - Find a task by keyword

Searches for tasks with task descriptions that matches a specific keyword

Must Contain:
* Keyword: Single keyword to be searched for

**Note**: Space in keyword is allowed, such as "finish homework".
You may even search for just a space like so, " "

**Note**: Index shown for each matching task is its actual index
in your tasklist

Example of usage:

`find finish homework`

Expected outcome:

```
Duke: Here are the matching tasks in your list:
5.[E][ ] finish homework (at: 1 January 2020)
A total of 1 tasks in your list match your search keyword, finish homework.
```

### `date` - Find a task by date

Searches for tasks with date or date period that either matches
or contains a specific search date

Must Contain:
* Date: date to be searched for in dd/mm/yyyy format

**Note**: Index shown for each matching task is its actual index 
in your tasklist

Example of usage:

`date 01/01/2020`

Expected outcome:

```
Duke: Here are the Deadlines, Events or Periods that fall on 1 January 2020:
1.[E][ ] have fun (at: 1 January 2020)
3.[P][ ] finish assignment (from: 1 January 2020 to: 1 February 2020)
4.[E][ ] finish homework (at: 1 January 2020)
A total of 3 tasks (0 deadlines, 2 events and 1 periods) fall on 1 January 2020
```

### `bye` - Exit Duke

Exits Duke application after a delay

Example of usage:

`bye`

Expected outcome:

```
Duke: Bye. Hope to see you again soon!
```