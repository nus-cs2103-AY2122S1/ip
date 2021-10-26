# User Guide

## 1. Features 

* Add Task
  * Todo
  * Deadline
  * Event
* Delete Task
* Complete Task
* Display Tasks
* Find Tasks
* Archive Tasks

## 1.1 Adding Tasks

### 1.1.1 Todo `todo`

Adds a task to your list of tasks.

Format:
`todo [description]`

Example of usage: 

`todo Study for CS2103`

Expected outcome:

```
Alright! I added this task:
[T][]: Study for CS2103
You have 1 task(s) at the moment!
```

### 1.1.2 Deadline `deadline .. /by`

Adds a task to your list of tasks with a set deadline.

Format:
`deadline [description] /by [dd/MM/yyyy h:mma]`

Example of usage:

`deadline CS2103 iP /by 16/09/2021 11:59PM`

Expected outcome:

```
Alright! I added this task:
[D][]: CS2103 iP (by: Sep 16 2021 11:59PM)
You have 1 task(s) at the moment!
```

### 1.1.3 Event `event .. /at`

Adds a task to your list of tasks with a start time.

Format:
`event [description] /at [dd/MM/yyyy h:mma]`

Example of usage:

`event CS2103 exam /at 16/09/2021 4:00PM`

Expected outcome:

```
Alright! I added this task:
[E][]: CS2103 exam (at: Sep 16 2021 4:00PM)
You have 1 task(s) at the moment!
```

## 1.2 Deleting Tasks `delete`

Deletes a task to your list of tasks.

Format:
`delete [task index]`

Example of usage:

`delete 1`

Expected outcome:

```
This item will be removed:
[T][]: Study for CS2103
You have 1 task(s) at the moment!
```

## 1.3 Completing Tasks `done`

Marks a task in your list as done.

Format:
`done [task index]`

Example of usage:

`done 1`

Expected outcome:

```
Awesome! I marked this as done:
[T][X]: Study for CS2103
You have 1 task(s) at the moment!
```

## 1.4 Displaying Tasks `list`

Displays list of all your tasks.

Format:
`list`

Example of usage:

`list`

Expected outcome:

```
1: [T][X]: Study for CS2103
2: [D][]: CS2103 iP (by: Sep 16 2021 11:59PM)
3: [E][]: CS2103 exam (at: Sep 16 2021 4:00PM)
4: [D][X]: CS2100 assm (by: Sep 12 2021 11:59PM)
```

## 1.5 Finding Tasks `find`

Find all tasks with a keyword in its description.

Format:
`find [keyword]`

Example of usage:

`find CS2103`

Expected outcome:

```
Here are the tasks containing the word : CS2103

1: [T][X]: Study for CS2103
2: [D][]: CS2103 iP (by: Sep 16 2021 11:59PM)
3: [E][]: CS2103 exam (at: Sep 16 2021 4:00PM)
```

## 1.6 Archiving Tasks `archive-all`

Archives all tasks to a textfile.

Format:
`archive-all`

Example of usage:

`archive-all`

Expected outcome:

```
Alright! I archived all your tasks:
1: [T][X]: Study for CS2103
2: [D][]: CS2103 iP (by: Sep 16 2021 11:59PM)
3: [E][]: CS2103 exam (at: Sep 16 2021 4:00PM)
```

archive.txt:
```
== ARCHIVE FROM: Sep 12 2021 13:00:02 ==
T | 1 | Archive tasks

== ARCHIVE FROM: Sep 13 2021 15:00:27 ==
T | 1 | Study for CS2103
D | 0 | CS2103 iP | 16/09/2021 11:59PM
E | 0 | CS2103 exam | 16/09/2021 4:00PM
```

## 2. Exiting the Program `bye`

Immediately exits the program

Format:
`bye`

Expected outcome:

Program should quit.