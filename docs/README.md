# User Guide
![Project Poseidon](./Logo.png)

**Table of Contents**:
- [Introduction](#ocean-introduction)
- [Quick Start](#ocean-quick-start)
- [Features](#ocean-features)
  - [Help - `help`](#trident-help)
  - [Todo - `todo`](#trident-todo)
  - [Deadline - `deadline`](#trident-deadline)
  - [Event - `event`](#trident-event)
  - [Done - `done`](#trident-done)
  - [Delete - `delete`](#trident-delete)
  - [List - `list`](#trident-list)
  - [List Sorted - `list -s`](#trident-list-sorted)
  - [Find - `find`](#trident-find)
  - [Bye - `bye`](#trident-bye)
  - [Case Sensitivity](#trident-case-sensitivity)
- [Command Summary](#ocean-command-summary)

## :ocean: Introduction
**P.O.S.E.I.D.O.N** (Poseidon) is a **desktop application for keeping track of todo tasks, upcoming deadlines and planning events**. Poseidon's efficient and intelligent algorithm, combined with an optimized Command Line Interface (CLI) and a simple Graphical User Interface (GUI) gives you a fast and smooth user experience. If you can type fast, Poseidon can help you plan and organize your day faster than traditional GUI apps.

:trident: :ocean:

**P.O.S.E.I.D.O.N** -  stands for

**P** lanner

**O** rganizer that's

**S** imple

**E** fficient

**I** ntelligent

**D** igital

**O** ptimized and

**N** ifty.

## :ocean: Quick Start
1. Ensure you have Java  `11` or above installed on your Computer.

2. Download the latest  `poseidon.jar` from  [here](https://github.com/YeluriKetan/ip/releases).

3. Copy the file to the folder you want to use as the  _home folder_ for your Poseidon.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. (If you face trouble opening the app this way, follow this [tutorial](#alternate-way))

![Poseidon](./Ui.png)

5. Type the command in the command box and press Enter to execute it. For starters type `help` and press Enter to find out more about the commands you can use.

### Alternate way
Open up a command prompt in the working directory that contains `poseidon.jar` and type in the following command.
```sh  
java -jar poseidon.jar
```  
## :ocean: Features

### :trident: Help
Lists all the commands you can use.

***Syntax:*** `help`

e.g., `help`

### :trident: Todo
A todo task is a simple task with description of the task.  
This command adds a todo task with a non-empty description.

***Syntax:*** `todo 'description'`

e.g., `todo Eat Dinner`

### :trident: Deadline
A deadline task is a task with a description of the task and a date and time by which the task is to be completed.  
This command adds a Deadline task with a non-empty description and a valid date and time.

***Syntax:*** `deadline 'description' /by 'yyyy MM dd HHmm'`

e.g., `deadline finish project /by 2021 10 01 2359`

### :trident: Event
An Event is a task with a description, a start date and time and an end date and time.  
This command adds an Event task with a non-empty description, "from" valid date and time and a "to" valid date and time.

***Syntax:*** `event 'description' /from 'yyyy MM dd HHmm' to 'yyyy MM dd HHmm'`

e.g., `event team meeting /from 2021 10 01 2000 to 2021 10 01 2200`

### :trident: Done
Marks a task as done based on the index of the task as shown in the normal list.

***Syntax:*** `done 'index'`

e.g., `done 2`

### :trident: Delete
Deletes a task based on the index of the task as shown in the normal list.

***Syntax:*** `delete 'index'`

e.g., `delete 3`

### :trident: List
Lists all the tasks with their description, time(s) (if-applicable) and the done status.

***Syntax:*** `list`

e.g., `list`

### :trident: List Sorted
Lists all the tasks with their description, time(s) (if-applicable) and the done status, sorted based on time.

***Syntax:*** `list -s`

e.g., `list -s`

### :trident: Find
Searches all the tasks based on the given content and shows the results in the form of a list.

***Syntax:*** `find 'content'`

e.g., `find meeting`

### :trident: Bye
Exits the application.

***Syntax:*** `bye`

e.g., `bye`

### :trident: Case Sensitivity
All commands are case insensitive.

e.g., `bye`,`BYE` and `Bye` all work.

## :ocean: Command Summary
Action | Command Format
--------|----------------  
For help | `help`
Add todo task | `todo 'description`
Add deadline task| `deadline 'description' /by 'yyyy MM dd HHmm'`
Add event | `event 'description' /from 'yyyy MM dd HHmm' to 'yyyy MM dd HHmm'`
Mark task done | `done 'index'`
Delete task | `delete 'index'`
List tasks | `list`
List tasks sorted | `list -s`
Find content | `find 'content'`
Exit app | `bye`
