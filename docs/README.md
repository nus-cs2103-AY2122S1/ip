# User Guide

Welcome to Duke. Duke is a chatbot that helps you keep track of your tasks.

## Introduction
Duke helps you to get a hang of your life. It is
* text-based
* easy to learn
* SUPER FAST to use

<p align="center">
   <img src="Ui.png" alt="Ui" width="25%"/>
</p>

## Features 

### Tasks

You can add three different types of tasks as stated below.

Task | Description | Icon
----- | ----- | -----
`Todo` | A simple vanilla task | `[T]`
`Deadline` | A task which has a deadline (date & time) in which it should done by. | `[D]`
`Event` | A task which has a date and time to which it is happening on. | `[E]`

### Priorities

You can add priorities to your tasks.

Tag | Description | Icon
----- | ----- | -----
`1` | Low priority | `[!  ]`
`2` | Medium priority | `[!! ]`
`3` | High priority | `[!!!]`

### Date & Time

You can add date & time in the following format. `YYYY-MM-DD HHmm` e.g. `2021-09-13 0151`. 
Duke will recognise your input automatically convert it to `13 Sep 21 1.51am`.

You can also search for tasks on a particular date using the same format without time. `YYYY-MM-DD` e.g. `2021-09-13`.

### Done & Delete

You can mark a task as done. Tasks that are done will have a `[X]` icon beside the task description.
You can also delete a task to de-clutter your list.

### Search

You can search for tasks with a particular word / phrase / part thereof.

## Setting up

1. Ensure you have JDK 11 installed.
    1. Steps to check JDK 11 installation [here](https://java.com/en/download/help/version_manual.html).
    2. Down JDK 11 [here](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html).
2. Download the latest Duke release (jar file) [here](https://github.com/zhenxuantan/ip/releases).
3. Copy the jar file into an empty folder.
4. Open a command window (command prompt, terminal, etc.) in that folder
5. Run the command java -jar {filename}.jar e.g., java -jar Duke.jar (i.e., run the command in the same folder as the jar file)

## Usage

### `todo <<description>> /p <<priority>>` - Adds a todo task

Add a todo task to your task list.

Example of usage: 
```
todo CS2100 Tutorial /p 1
```

Expected outcome:

Todo task added.

```
Got it. I've added this task: [T][ ][! ] CS2100 Tutorial
Now you have 1 task in the list.
```

### `deadline <<description>> /by <<date and time>> /p <<priority>>` - Adds a deadline task

Add a deadline task to your task list.

Example of usage:
```
deadline ES2660 Reflection /by 2021-08-29 2359 /p 2
```

Expected outcome:

Deadline task added.

```
Got it. I've added this task:
[D][ ][!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm)
Now you have 2 tasks in the list.
```

### `event <<description>> /at <<date and time>> /p <<priority>>` - Adds an event task

Add an event task to your task list.

Example of usage:
```
event cs2100 Finals /at 2021-11-20 0900 /p 3
```

Expected outcome:

Event task added.

```
Got it. I've added this task:
[E][ ][!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)
Now you have 3 tasks in the list.
```

### `list (<<date>>)` - Shows task list (of a particular date)

Show the task list. If date is provided (optional argument), o
nly tasks (deadline / event) on that particular date will be shown.

#### Show task list
Example of usage:
```
list
```

Expected outcome:

Task list shown.

```
Here are the tasks in your list:
1.[T][ ][! ] CS2100 Tutorial
2.[D][ ][!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm)
3.[E][ ][!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)
```

#### Search task of a particular date and show task list
Example of usage:
```
list 2021-11-20
```

Expected outcome:

Task list with task on the given date shown.

```
Here are the tasks in your list:
1.[E][ ][!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)
```

### `delete <<index of task>>` - Deletes a task

Deletes a task that is of the given index from the task list.

Example of usage:
```
delete 2
```

Expected outcome:

Task deleted.

```
Noted. I've removed this task:
[D][ ][!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm)
```

### `done <<index of task>>` - Marks a task as done

Marks a task that is of the given index from the task list as done.

Example of usage:
```
done 1
```

Expected outcome:

Task marked done.

```
Nice! I've marked this task as done:
[T][X][! ] CS2100 Tutorial
```

### `find <<word(s)>>` - Finds task with the words given

Shows the task list with tasks whose description contains the given word(s).

Example of usage:
```
find finals
```

Expected outcome:

Shows relevant tasks.

```
Here are the tasks in your list:
1.[E][ ][!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)
```

### `bye` - Exits duke

Exits duke and closes the application window.

Example of usage:
```
bye
```

Expected outcome:

Duke and application window closed.

```
Bye! Hope to see you again!
```

## Command summary

Description <br /> `Command` | Example | Duke's Response
----- | ----- | ----- |
**Add a todo task** <br />  `todo` `<<description>>` `/p <<priority>>`| `todo CS2100 Tutorial /p 1` |  Got it. I've added this task: <br /> \[T]\[ ]\[! ] CS2100 Tutorial <br /> Now you have 1 task in the list.
**Add a deadline task** <br /> `deadline` `<<description>> /by <<date and time (YYYY-MM-DD HHmm)>>` `/p <<priority>>` | `deadline ES2660 Reflection` `/by 2021-08-29 2359` `/p 2` | Got it. I've added this task: <br /> \[D]\[ ]\[!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm) <br /> Now you have 2 tasks in the list.
**Add a event task** <br /> `event` `<<description>>` `/at <<date and time (YYYY-MM-DD HHmm)>>` `/p <<priority>>` | `event cs2100 Finals` `/at 2021-11-20 0900` `/p 3` | Got it. I've added this task: <br /> \[E]\[ ]\[!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)<br /> Now you have 3 tasks in the list.
**List down all of the tasks** <br /> `list` | `list` | Here are the tasks in your list: <br /> 1.\[T]\[ ]\[! ] CS2100 Tutorial <br /> 2.\[D]\[ ]\[!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm) <br /> 3.\[E]\[ ]\[!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)<br />
**List down all of the deadlines / events on that date** <br /> `list` `<<date (YYYY-MM-DD)>>` | `list 2021-11-20` | Here are the tasks in your list: <br /> 1.\[E]\[ ]\[!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)<br />
**Delete task** <br /> `delete` `<<index of task>>` | `delete 2` | Noted. I've removed this task: <br /> \[D]\[ ]\[!! ] ES2660 Reflection (by: 29 Aug 2021 11.59pm)
**Mark task as done** <br /> `done` `<<index of task>>` | `done 1` | Nice! I've marked this task as done: <br /> \[T]\[X]\[! ] CS2100 Tutorial
**Find task with the given words** <br /> `find` `<<word(s)>>` | `find finals` | Here are the tasks in your list: <br /> 1.\[E]\[ ]\[!!!] cs2100 Finals (at: 20 Nov 2021 9.00am)
**Exit the application** <br /> `bye` | `bye` | Bye! Hope to see you again!
