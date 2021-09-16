# User Guide
Duke is a personal assistant desktop app for managing your tasks via a Command Line Interface (CLI). This is suitable for people who type fast.

There are 3 types of tasks you can manage:
1. ToDo
2. Deadline
3. Event

This user guide details how to download Duke and get started, as well as all the commands you can use to interact with Duke.

## Quick Start
1. Ensure you have Java 11 or above installed.
2. Download the latest `duke.jar` from [here](https://github.com/kslui99/ip/releases/tag/v0.1).
3. Double-click the `duke.jar` file to start the app.

## Features and Usage
### Adding a ToDo: ```todo [description]```
Adds a new ToDo to your task list.

Example of usage:

`todo return book`

Expected outcome:

A new ToDo 'return book' is added to the task list.

```
Got it. I've added this task:
[T][] return book
Now you have 2 tasks in the list.
```


### Adding a Deadline: ```deadline [description] /by [yyyy-mm-dd hhmm]```
Adds a new Deadline to your task list.

Example of usage:

`deadline assignment 1 /by 2021-09-17 2359`

Expected outcome:

A new Deadline 'assignment 1', due on Sep 17 2021 2359, is added to the task list.

```
Got it. I've added this task:
[D][] assignment 1 (by: Sep 17 2021 2359)
Now you have 3 tasks in the list.
```

### Adding an Event: ```event [description] /at [yyyy-mm-dd hhmm]```
Adds a new Event to your task list.

Example of usage:

`event project meeting /at 2021-09-17 1400`

Expected outcome:

A new Event 'project meeting', happening at Sep 17 2021 1400, is added to the task list.

```
Got it. I've added this task:
[E][] project meeting (at: Sep 17 2021 1400)
Now you have 4 tasks in the list.
```

### Listing all tasks: ```list```
Lists all the tasks in your task list.

Example of usage:

`list`

Expected outcome:

All the tasks in the list are displayed.

```
1. [T][] read book
2. [T][] return book
3. [D][] assignment 1 (by: Sep 17 2021 2359)
4. [E][] project meeting (at: Sep 17 2021 1400)
```

### Marking a task as done: ```done [index]```
Marks the task at the specified index as done. Index is the number in front of the task returned by the list command. 

Example of usage:

First, list all the tasks:

`list`

Expected Output:
```
1. [T][] read book
2. [T][] return book
3. [D][] assignment 1 (by: Sep 17 2021 2359)
4. [E][] project meeting (at: Sep 17 2021 1400)
```

Then to mark the third task as done:

`done 3`

Expected Output:
```
Nice! I've marked this task as done:
[D][X] assignment 1 (by: Sep 17 2021 2359)
```


### Searching for a task: ```find [description]```
Finds tasks whose description, date or time contain any of the given keywords.
* The search is case-sensitive. For example, ```Assignment 1``` will not match ```assignment 1```
* The order of the keywords matters. For example, ```1 assignment``` will not match ```assignment 1```.
* Partial words can be matched. For example, ```assign``` will match ```assignment 1```.

Example of usage:

`find book`

Expected outcome:

Returns all tasks with 'book' in its description.

```
Here are the matching tasks in your list:
1. [T][] read book
2. [T][] return book
```


### Deleting a task: ```delete [index]```
Deletes the task at the specified index. Index is the number in front of the task returned by the list command. 

Example of usage:

First, list all the tasks:

`list`

Output:
```
1. [T][] read book
2. [T][] return book
3. [D][] assignment 1 (by: Sep 17 2021 2359)
4. [E][] project meeting (at: Sep 17 2021 1400)
```

Then to delete the third task:

`delete 3`

Expected Outcome:

The third task, 'assignment 1', is deleted.

```
Noted. I've removed this task:
[D][] assignment 1 (by: Sep 17 2021 2359)
Now you have 3 tasks in the list.
```


### Saving the data
Duke's data is automatically saved in the hard disk after using any command that changes the data. There is no need to manually save the data.


### Exiting the app: `bye`
Closes the app.
