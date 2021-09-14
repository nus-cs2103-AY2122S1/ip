# User Guide

## Introduction

Tiger is a personal assistant that helps you manage the tasks you're too lazy to do right now. 

![Screenshot](./Ui.png)

### <ins>Font Credits:</ins> [Victor Mono](https://rubjo.github.io/victor-mono/)

Tiger classifies tasks into three categories: Todos, Events, and Deadlines. Events and deadlines are tasks associated with a date or time. Each task can be either marked as completed or not completed. A task also has a priority: low, medium, or high.

Commands include a keyword, some mandatory arguments, and some optional arguments. The keyword comes first, then the mandatory arguments, followed by the optional arguments, ie. `[keyword] [mandatory arguments] [optional arguments].`

## Setting up
**For all platforms:** `cd` to the directory containing `Tiger.jar,` and run:
```
java -jar Tiger.jar
```

## Features 

The features offered are:

- [Adding a task](#adding-tasks)
- [Deleting a task](#deleting-a-task)
- [Marking a task as done](#marking-a-task-as-done)
- [Finding a task](#finding-a-task)
- [Clearing all tasks](#clearing-all-tasks)
- [Listing all tasks](#listing-all-tasks)
- [Searching by priority](#searching-by-priority)
- [Exiting the app](#exiting-the-app)

Some advanced features include:
- [Substitutable keywords](#substitutable-keywords)
- [Automatic date correction](#automatic-date-correction)
- [Partial loading in case the storage file is corrupted](#partial-loading)

## Usage

**Due to the way data is stored, users are not allowed to input semicolons (;).**

### Adding tasks
The string representation of a task is as such:
```
[T|E|D] [H|M|L|X] [task description] [task date]
```
`T, E, D` denotes a Todo, Event or Deadline tasks respectively. `H, M, L` denotes a tasks of high, medium or low priority respectively. `X` marks completed tasks.

_________________________

To add a new Todo, enter `todo [task description] /priority [H|M|L].` The `/priority` command is optional.

Example: `todo homework` adds a new Todo named "Homework".

Expected outcome:
```
Excellent! I've added this task:
[T] [M] Homework
```
**By default, if the priority is not assigned, the task is classified as medium priority.** To change the task's priority, use the priority flag.

Example: `todo homework /priority H` adds a new Todo that is named "Homework" that is of high priority.

Expected outcome:
```
Excellent! I've added this task:
[T] [H] Homework
```
The first letter of the task description is capitalized automatically. There is no way to disable this; free will is a lie.

_________________________

To add a new Event, enter: `event [event description] /at [event time] /priority [H|M|L].` The `/priority` command is optional. Accepted date formats are `HH:mm` or `YYYY-MM-DD HH:mm.` The year input string should be **exactly** 4 characters, and all other date parameters should be **exactly** 2 characters. The year cannot be 0000. 

Example: `event eat dinner /at 2021-05-21 16:00` creates a new Event named "eat dinner" at 2021-05-21 16:00.

Expected outcome:
```
Excellent! I've added this event:
[E] [M] Eat dinner (at 2021-05-21 16:00)
```
**If the user only specifies the time, but not the date, today's date will be used instead.**

Example: `event eat dinner /at 16:00` creates a new Event named "eat dinner" at [today's date] 16:00.

Expected outcome:
```
Excellent! I've added this event:
[E] [M] Eat dinner (at 2021-08-31 16:00)
```
Much like Todos, events can be assigned different priorities:

Example: `event eat dinner /at 15:29 /priority L` creates a new event named "eat dinner" at [today's date] 16:00 of low priority.

Expected outcome: (assume today's date is 2021-08-31).
```
Excellent! I've added this event:
[E] [L] Eat dinner (at 2021-08-31 16:00)
```
_________________________

To add a new Deadline, enter: `deadline [deadline description] /by [deadline due date] /priority [H|M|L].` The `/priority` command is optional. Accepted date formats are `HH:mm` or `YYYY-MM-DD HH:mm.` The year input string should be **exactly** 4 characters, and all other date parameters should be **exactly** 2 characters. The year cannot be 0000. 

Example: `deadline eat dinner /by 16:00` creates a new Deadline named "eat dinner" at [today's date] 16:00.

Expected outcome: (assume today's date is 2021-08-31).
```
Excellent! I've added this event: 
[D] [M] Eat dinner (by 2021-08-31 16:00)
```

Example: `deadline eat dinner /by 15:29 /priority L` creates a new Deadline named "eat dinner" at [today's date] 16:00 of low priority.

Expected outcome:
```
Excellent! I've added this event:
[D] [L] Eat dinner (by 2021-08-31 16:00)
```
> Substitutable keywords: `dateline`



_________________________

### Deleting a task
Deletes the task at the specified index. Use the command `list` to get the list of tasks and indicies. The usage is `delete [task index].`

Example: `delete 1` deletes the task at index 1.

Expected outcome: 
```
Feeling lazy today? I've deleted:
[T] [M] CS2103 Code Review
```
> Substitutable keywords: `del, remove`

_________

### Marking a task as done
Marks the task at a specified index as done.  Use the command `list` to get the list of tasks and indicies. The usage is `done [task index].`

Example: `done 1` marks the task at index 1 as done.

Expected outcome: 
```
Nice! I've marked this tasks as done:
[T] [X] Type out GT assignment
```
_________________________

### Finding a task
Find tasks based on a specific keyword. The search is not case sensitive, ie. `search woodlands` and `search Woodlands` gives the same result. The usage is `search [substring].`

Example: `search Woodlands` searches for tasks with task description containing the string "Woodlands".

Expected outcome: 
```
[T] [M] Buy earphones at Woodlands
```
> Substitutable keywords: `find`

_________________________

### Clearing all tasks
Clears all tasks. The usage is `clear.`

Example: `clear` clears all tasks.

Expected outcome: 
```
I've cleared all your tasks!
```
_________________________

### Listing all tasks
Lists all tasks. (After all, that's the whole point of a todo app right?) The usage is `list.`

Example: `list` lists all tasks.

Expected outcome:
```
1. [T] [M] Buy earphones at Woodlands
2. [E] [H] Feed fish (at 2021-09-01 16:00)
3. [D] [L] Graph theory assignment (by 2022-11-21 14:25)
```
> Substitutable keywords: `ls`

_________________________

### Searching by priority
Search tasks based on a certain priority. The usage is `priority [L|M|H].` **If the task is already marked as done, it won't show up in the search**.

Example: `priority H` lists all tasks with high priority.

Expected outcome:
```
1. [T] [H] Type out homework
2. [E] [H] Feed fish (at 2021-09-01 16:00)
3. [D] [H] Graph theory assignment (by 2022-11-21 14:25)
```
_________________________

### Exiting the app
Exits the app. The usage is `bye.`

Example: `bye` quits the app.

Expected outcome:
```
Bye! Hope to see you again!
```
Note that the window waits a second to close.

> Substitutable keywords: `exit, quit`

_____________________________

## Advanced features

### Substitutable keywords
Some commands can be substituted in place of regular commands. This is indicated by the **Substitutable keywords** section at the bottom of command. 

For instance, `find` performs the same action as `search`. 

Example: `find Woodlands`

Expected outcome:
```
[T] [M] Buy earphones at Woodlands
```

Similarly, `ls` lists all the tasks.

Example: `ls`

Expected outcome:
```
1. [T] [M] Buy earphones at Woodlands
2. [E] [H] Feed fish (at 2021-09-01 16:00)
3. [D] [L] Graph theory assignment (by 2022-11-21 14:25)
```
_________________________

### Automatic date correction
If the user inputs an invalid date by accident, the date is "rounded off" to the nearest valid date.

Example: `event Event /at 2021-09-31 15:00.`

Expected outcome:
```
Excellent! I've added this event:
[E] [M] Event (at 2021-09-30 15:00)
```

Example: `event Event /at 2021-02-29 15:00` (this works for 30 and 31 as well).

Expected outcome:
```
Excellent! I've added this event:
[E] [M] Event (at 2021-02-28 15:00)
```

If the input date is too far from a valid date, the user needs to re-enter the command.

Example: `event Event /at 2021-02-32 15:00.`

Expected outcome:
```
Please ensure you key in dates in the input specified.
```
_________________________

### Partial loading
If for some reason, the file storing user tasks is corrupted, the user can try to recover some tasks. When the data file has been corrupted, the app starts up with a different welcome message:

```
Error encountered in loading the file! Did you alter my memory directly?

If you didn't backup my memory, would you like to try a partial load to see what can be removered? [Y/N]

Pressing N will wipe data currently stored.
```
At this point, the user is expected to enter in `Y` or `N` (lower case accepted). `Y` will attempt to load the subset of tasks which can be recovered.

Example: `Y`

Expected outcome:
```
Hello, I'm Tiger, your personal assistant. I've recovered 5 tasks from my memory.
```

`N` will clear all tasks.

Example: `N`

Expected outcome:
```
Hello, I'm Tiger, your personal assistant. I've recovered 0 tasks from my memory.
```
If the user keys in any other input, the user is clearly incapable of reading. Tiger will prompt the user to input `Y` or `N` until either one of them is keyed in.

Example: `g`

Expected outcome:
```
Please enter Y or N only.
```

## Uninstallation
To uninstall, simply delete the `data` folder and `Tiger.jar` in the directory containing `Tiger.jar.`
