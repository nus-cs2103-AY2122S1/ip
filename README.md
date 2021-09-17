# User Guide

**Wonderland** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) 
while also having the benefits of a Graphical User Interface (GUI). If you are someone who has many things to do 
everyday, Wonderland can assist you to capture and organise what you need to do into a list.

## Installation

### Prerequisites
1. If you have Java 11 installed in your computer you are all set!
2. Else, [download java](https://java.com/en/download/help/download_options.html).

### Setting Up
1. Download the `Wonderland.jar` from here
2. Once installed, double click on `Wonderland.jar` to run the bot.

## Features

### Adding a task

Wonderland offers you 3 types of tasks you can add into your task list.
1. A todo task
2. A task with deadline
3. An event you'll be attending

### Deleting a task
Of course, Wonderland can remove a task from your task list as you wish.

### Listing all tasks

Wonderland will present all tasks in your task list.

### Marking a task as done
 
Wonderland will mark the specified task as completed.

### Finding a task

Wonderland is able to find tasks based on the keywords you provide.

### Exiting the app

Bid farewell to Wonderland when you are done!

## Usage

### `add` - Adding a task
Adds a task into the task list.

Format:
1. ```todo TASK_DESCRIPTION```
2. ```deadline TASK_DESCRIPTION /by DATE```
3. ```event TASK_DESCRIPTION /at DATE```
- DATE is in YYYY-MM-DD

Example of usage:
- ```todo buy matchsticks```
- ```deadline Quiz /by 2021-11-12```
- ```event Match /at 2021-11-01```

Wonderland will notify you that your task have been added successfully.
The type and status of task will be displayed as well.

```
Got it. I've added this task:
  [T][ ] buy matchsticks
Now you have 1 task in the list.
```

### `delete` - deleting a task
Deletes the specified task from the task list.

Format:
```delete INDEX```
- Index must be greater than 0. 

Example of usage:
```delete 1```

Wonderland will notify you that your task have been deleted successfully.

```
Noted! I've removed this task:
  [T][ ] buy matchsticks
Now you have 0 task in the list.
```

### `list` - listing all tasks
Lists all the tasks from the task list.

Format: `list`

Wonderland will display all your tasks as an ordered list.

```
Here are a list of tasks you have:
1. [T][ ] buy matchsticks
2. [D][ ] Quiz (by: Nov 12 2021)
3. [E][ ] Match (at: Nov 1 2021)
```

### `done` - marking a task as done
Marks a task as done.

Format:
```done INDEX```
- Index must be greater than 0

Example of usage:
```done 1```

Wonderland will mark the specified task as complete.

```
Nice! I've marked this task as done:
  [T][X] buy matchsticks
```

### `find` - finding a task
Finds a list of task that contains the specified keywords.\
The user can input more than one keyword.

Format:
```find KEYWORD```

- The search is case-insensitive and returns results that match partially 
  as well. e.g. `mat` will match `buy matchsticks` and `Match`. 

Example of usage:
```find mat```

Wonderland will show you a list of task containing the keywords you specified.

```
Here are the matching task in your list:
1. [T][ ] buy matchsticks
2. [T][ ] Match 
```

### `bye` - exiting the app
Exits the program

Format:
```bye```

## Command summary

Action | Format, Examples
--- | ---
**Add Todo** | ```todo TASK```<br/>e.g., ```todo math homework```
**Add Deadline** | ```deadline TASK /by DATE```<br/>e.g., ```deadline proposal submision /by 2021-11-12```
**Add Event** | ```event TASK /at DATE```<br/>e.g., ```event night cycling /at 2022-9-10```
**Delete** | ```delete INDEX```<br/>e.g., ```delete 1```
**List** | `list`
**Done** | ```done INDEX```<br>e.g., ```done 1```
**Find** | ```find KEYWORD```<br/>e.g.,```find home```
**Exit** | `exit`
