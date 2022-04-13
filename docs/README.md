# User Guide

**Wonderland** is your personal assistant for managing tasks, which you can use through Command Line Interface (CLI) 
as well as Graphical User Interface (GUI). Why bother remembering and tracking all the deadlines, events and todos 
when you have Wonderland? 

## Installation

### Prerequisites
1. If you have Java 11 installed in your computer you are all set!
2. Else, [download java](https://java.com/en/download/help/download_options.html).

### Setting Up
1. Download the `Wonderland.jar` from [here](https://github.com/alinaleehx/ip/releases/download/A-Release/Wonderland.jar)
2. Once installed, double-click on `Wonderland.jar` to run the bot.

## Features

### Adding a task

Wonderland allows you to add 3 types of tasks:
1. A todo 
2. A task with deadline
3. An event you'll be attending

### Deleting a task
Just delete a task from your task list if it's not relevant anymore!

### Listing all tasks

Wonderland will collate your tasks and present them neatly showing all information.

### Marking a task as done
 
Wonderland will mark the specified task as completed.

### Finding a task

Wonderland is able to find tasks based on the keywords you provide.

### Exiting the app

Bid farewell to Wonderland when you are done!

## Usage

### Adding a task: `todo`, `deadline` or `event`
Adds a todo, deadline or event into the task list accordingly.

Format:
1. ```todo TASK_DESCRIPTION```
2. ```deadline TASK_DESCRIPTION /by DATE```
3. ```event TASK_DESCRIPTION /at DATE```
- DATE is in YYYY-MM-DD

Example of usage:
- ```todo buy matchsticks```
- ```deadline Quiz /by 2021-11-12```
- ```event Match /at 2021-11-01```

Wonderland will send a message showing type, description and status 
of task once your task is successfully added.

```
Got it. I've added this todo:
  [T][] buy matchsticks
Now you have 1 task in the list.
```

### Deleting a task: `delete` 
Deletes the task of specified index from the task list.

Format:
```delete INDEX```
- Index must be greater than 0. 

Example of usage:
```delete 1```

Wonderland will send a message once your task have been deleted successfully.

```
Noted! I've removed this task:
  [T][] buy matchsticks
Now you have 0 task in the list.
```

### Listing all tasks: `list` 
Lists all the tasks from the task list.

Format: `list`

Wonderland will display all your tasks as an ordered list.

```
Here are the tasks in your list:
1. [T][ ] buy matchsticks
2. [D][ ] Quiz (by: Nov 12 2021)
3. [E][ ] Match (at: Nov 1 2021)
```

### Marking a task as done: `done` 
Marks the task of specified index as done.

Format:
```done INDEX```
- Index must be greater than 0

Example of usage:
```done 1```

Wonderland will send a message once the specified task is marked as done.

```
Nice! I've marked this task as done:
  [T][X] buy matchsticks
```

### Finding a task: `find` 
Finds a list of tasks that contains the specified keywords.

Format:
```find KEYWORD```

- The search is case-insensitive and returns results that matches partially 
  as well. e.g. `mat` will match `buy matchsticks` and `Match`. 

Example of usage:
```find mat```

Wonderland will show you a list of task that match with given keyword.

```
Here are the matching task in your list:
1. [T][ ] buy matchsticks
2. [T][ ] Match 
```

### Exiting the app: `bye` 
Exits the program

Format:
```bye```

### Saving the data
Wonderland data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

## Command summary

Action | Format, Examples
--- | ---
**Add Todo** |```todo TASK```<br/>e.g. ```todo buy noodles```
**Add Deadline** |```deadline TASK /by DATE```<br/>e.g. ```deadline attempt quiz /by 2021-11-12```
**Add Event** |```event TASK /at DATE```<br/>e.g. ```event family dinner /at 2022-9-10```
**Delete** |```delete INDEX```<br/>e.g. ```delete 1```
**List** |`list`
**Done** |```done INDEX```<br>e.g. ```done 1```
**Find** |```find KEYWORD```<br/>e.g.```find home```
**Exit** |`bye`
