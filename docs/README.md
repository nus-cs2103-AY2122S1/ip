# User Guide

Duii is a todo-list application that allows users to keep track of their tasks through a Graphical User Interface (GUI). This project is adapted from Project Duke. Display pictures used are taken from Pinterest, original author 不觉晓晓.

## Starting the Program

To get started, ensure that you have the following installed:
1. [Java Version 11](https://www.java.com/en/) or higher.
2. The .jar file for [Duii v0.2](https://github.com/boonhaii/ip/releases/tag/v0.2).

To start up the application, navigate to the folder with the which the .jar file is in, and execute the file.

## Features 
Listed below is a summary of the features available in Duii.

### Todos/Deadlines/Events

Duii supports 3 types of tasks, namely todos, deadlines and events. 
Todos are generic tasks, which have an optional field for duration.
Deadlines are tasks which have to be completed by a certain date.
Events are tasks which will be done at a specific time.

### Add Todos/Deadlines/Events

Duii can add Todos/Deadlines/Events into your task list.

### Delete Todos/Deadlines/Events

Duii can remove specific Todos/Deadlines/Events into your task list.
Duii supports multiple deletion of tasks at one go.

### Mark Done

Duii can help you mark tasks as done once you have completed a task.
Duii supports multiple marking of tasks at one go.

### Listing Tasks

Duii can list out all your existing tasks in your current list.

### Finding Tasks

Duii can find tasks which contain a specific keyword in its task description or in its tagging.

### Task Tagging

Tasks can be tagged with single-worded keywords, which can be used to search for the specific task again in the future.
These tags can also be removed when required.

### Saving

Duii can keep track of the tasks in your current session, allowing you to continue from where you stopped for your future sessions.

### Help Page

Duii provides a help page listing all the available commands.

## Usage

Listed below are the available commands for Duii. Do note that the output would differ based on the tasks saved in the data file upon opening the application, as well as the users' inputs.

### `todo` - Adds a todo the list

Arguments:
  * Task Description (Required)
  * Duration (Optional)

Example of usage: 

`todo Assignement 1 (2h)`

Expected outcome:

A todo is added to the list.

```
New Task? I've added it to the list:
[T] [] assignment 1 (needs 2h)
Now you have 1 task(s) in the list.
```

### `deadline` - Adds a deadline the list

Arguments:
  * Task Description (Required)
  * Input seperator ie. "/by" (Required)
  * Date of deadline, in the format "DD/MM/YYYY" (Required)
  * Time of deadline (Optional), default value is 0000

Example of usage: 

`deadline cs2103 ip /by 19/09/2021 1900`

Expected outcome:

A deadline is added to the list.

```
New Task? I've added it to the list:
[D] [] cs2103 ip (by: 19 Sep 2021 19:00)
Now you have 2 task(s) in the list.
```

### `event` - Adds a event the list


Arguments:
  * Task Description (Required)
  * Input seperator ie. "/at" (Required)
  * Date of deadline, in the format "DD/MM/YYYY" (Required)
  * Time of deadline, default value is 0000 (Optional)

Example of usage: 

`event cs2101 project meeting /by 12/09/2021 2130`

Expected outcome:

An event is added to the list.

```
New Task? I've added it to the list:
[E] [] cs2101 project meeting (by: 12 Sep 2021 21:30)
Now you have 2 task(s) in the list.
```
### `list` - Lists all the tasks in the list

Arugments:
  * None.

Example of usage: 

`list`

Expected outcome:

Lists all the existing events in the current list.

```
Here's your current list:
1. [T] [] Assignment 1(needs 2h)
2. [D] [] cs2103 ip (by: 19 Sep 2021 19:00)
3. [E] [] cs2101 project meeting (at: 12 Sep 2021 21:30)
```

### `done` - Marks task(s) as completed

Arguments:
  * IDs of the tasks to be marked as done, If multiple IDs are present, seperate them by commas. (Required)

Example of usage: 

`done 1,2`

Expected outcome:

The tasks with the inputted IDs would be marked as done.

```
You've finished the task? Good job!
These task has been marked as done:
[T] [X] Assignment 1(needs 2h)
[D] [X] cs2103 ip (by: 19 Sep 2021 19:00)
```

### `delete` - Deletes task(s) from the list

Arguments:
  * IDs of the tasks to be removed. If multiple IDs are present, seperate them by commas. (Required)

Example of usage: 

`delete 1,2`

Expected outcome:

The tasks with the inputted IDs would be removed.

```
Okay! Removing these tasks:
[T] [X] Assignment 1(needs 2h)
[D] [X] cs2103 ip (by: 19 Sep 2021 19:00)
```
### `find` - Searches for tasks with a specified keyword

Arguments:
  * Keyword (Required)

Example of usage: 

`find ip`

Expected outcome:

Displays the tasks with the specified keyword

```
Here are the matching results:
1. [D] [X] cs2103 ip (by: 19 Sep 2021 19:00)
```
If the keyword does not match any task description, a notification message will still appear.
```
There were no keyword matches!
```

### `tag add` - Tags tasks with the relevant keyword

Arguments:
  * Whole/Part of Task Description (Required)
  * Keyword (Required)

Example of usage: 

`tag add cs2101 project meeting urgent`

Expected outcome:

Tags the specified task with the keyword passed.

```
Here are the tasks which have been tagged with #urgent:
1. [E] [] cs2101 project meeting (at: 12 Sep 2021 21:30)
```
If the keyword does not match any task description, a notification message will still appear.
```
There were no keyword matches!
```

### `tag delete` - Tags tasks with the relevant keyword

Arguments:
  * Whole/Part of Task Description (Required)
  * Keyword (Required)

Example of usage: 

`tag delete cs2101 project meeting urgent`

Expected outcome:

Removes the specified tag the specified task(s).

```
Here are the tasks which have had the tag #urgent removed:
1. [E] [] cs2101 project meeting (at: 12 Sep 2021 21:30)
```
If the keyword does not match any task description, a notification message will still appear.
```
There were no keyword matches!
```

### `/help` - Lists out all the availble commands.

Arguments:
  * None

Example of usage: 

`/help`

Expected outcome:

Displays all available commands.

```
The following commands are available:
list - Lists all the tasks in the current task list.
done - Marks the tasks with the specified IDs as done.
Eg. done 1,2,3 
delete - Deletes the tasks with the specified IDs.
Eg. delete 5,1,2 
tag add - Tags a certain task with the relevant keywords with a tag.
Eg. tag add book nerd
tag delete - Removes a certain tag from task(s) with the relevant keywords.
Eg. tag delete book nerd
todo - Adds a toDo activity to the list. Optional to specify duration in brackets.
Eg. todo read book (2h)
event - Adds an event activity to the list.
Eg. event Dinner /at 19/02/2021 1900
deadline - Adds a deadline activity to the list. 
Eg. deadline Assignment 1 /by 19/03/2021 1500
find - Lists all the tasks with the specified keyword in its description or tag.
Eg. find books 
bye - Exits the program.
```

### `bye` - Exits the application

Arguments:
  * None

Example of usage: 

`bye`

Expected outcome:

Displays the exit message, before exiting the application.

```
You're going already? Hope to see you again soon!
```


