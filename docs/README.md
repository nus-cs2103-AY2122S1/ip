![Duke](Ui.png)

# User Guide

Duke is a **desktop app** for tracking your everyday tasks. Implemented as a **chatbot**, its
user-friendly commands and UI will make tracking tasks a breeze. With support for different
types of tasks, its versatile nature makes it perfect for people of all ages who just want
to get their life on track.

## Getting Started
Method 1 - Double-click: 
1. Install `Java 11` or above
2. Download the latest release of `duke.jar` from [here](https://github.com/moreTriangles/ip/releases)
3. Place `duke.jar` in an empty folder
4. Double-click `duke.jar` to start Duke

Method 2 - Command Line:
1. Install `Java 11` or above
2. Download the latest release of `duke.jar` from [here](https://github.com/moreTriangles/ip/releases)
3. Place `duke.jar` in an empty folder
4. Open your terminal or command prompt
5. Navigate to the folder containing `duke.jar`
6. Execute `java -jar duke.jar`

## Features
### Various Task Types
* Todo
  * The most basic type of task which simply contains a task name
* Deadline
  * Tasks which have to be completed by a certain date
* Event
  * Tasks which will happen at a specific date

### Add tasks
To track any tasks you may have, simply add them to Duke.

### Marks tasks as done
After completing your tasks, mark your tasks as done.

### Delete tasks
Delete your tasks if you have no more use for them!

### Find tasks
If you have too many tasks, simply enter some keywords that match the task name to search
for the task.

### Shorthand notation
To make it quicker to enter your tasks, shorthand notation is provided.

Original: `todo Meet friends for dinner`

Shorthand: `t Meet friends for dinner`

## Usage

### `todo` - Adds a new Todo task

To start off, you can add a new Todo task to Duke.

Format: `todo [task name]` or `t [task name]`

Example of usage: `todo Buy breakfast for family`

Expected outcome:
```
Great! I've added your todo. Enter 'list' to see your tasks!

You currently have 1 tasks.
```
Duke will show a message indicating the todo has been added.


### `deadline` - Adds a new Deadline task
Next, you can also add a new Deadline task to Duke.

Format: `deadline [task name] /by [yyyy-mm-dd]` or `d [task name] /by [yyyy-mm-dd]`

Example of usage: `deadline Submit CS2103T iP /by 2021-09-17`

Expected outcome:
```
Great! I've added your deadline. Enter 'list' to see your tasks!

You currently have 2 tasks. 
```
Duke will show a message indicating the deadline has been added.


### `event` - Adds a new Event task
Next, you can also add a new Event task to Duke.

Format: `event [task name] /at [yyyy-mm-dd]` or `e [task name] /at [yyyy-mm-dd]`

Example of usage: `event Guitar Lesson /at 2021-09-13`

Expected outcome:
```
Great! I've added your event. Enter 'list' to see your tasks!

You currently have 3 tasks. 
```
Duke will show a message indicating the event has been added.


### `list` - Shows all current tasks
After adding your tasks, you can use this command to view all current tasks.

Format: `list` or `l`

Example of usage: `list`

Expected outcome:
```
Here are your current tasks:
1. [T][ ] Buy breakfast for family
2. [D][ ] Submit CS2103T iP (by: Friday, 17 September 2021)
3. [E][ ] Guitar Lesson (at: Monday, 13 September 2021)
```
Duke will show a list of all current tasks.

### `done` - Mark a task as done
After completing a task, you can mark a task as done.

Format: `done [task number]`

Example of usage: `done 2`

Expected outcome:
```
Solid work! I've marked task 2 as done.
Here are your current tasks:
1. [T][ ] Buy breakfast for family
2. [D][✔] Submit CS2103T iP (by: Friday, 17 September 2021)
3. [E][ ] Guitar Lesson (at: Monday, 13 September 2021)
```
Duke will mark the corresponding task with a tick symbol to indicate that it is done.

### `delete` - Delete a task
After a task is done, you can choose to delete it.

Format: `delete [task number]`

Example of usage: `delete 2`

Expected outcome:
```
Got it! I've removed task 2.
You currently have 2 tasks.

Here are your current tasks:
1. [T][ ] Buy breakfast for family
2. [E][ ] Guitar Lesson (at: Monday, 13 September 2021)
```
Duke will remove the corresponding task from the list. All subsequent task numbers will decrease by 1.


### `find` - Find a task
Search for a task.

Format: `find [matching task string]`

Example of usage: `find family`

Expected outcome:
```
Here are your tasks that match family:
1. [T][ ] Buy breakfast for family
```
Duke will look for the tasks that match the string provided, and display them.


### `bye` - Shut down Duke
Use this after you are done managing your tasks to shut down Duke.

Format: `bye`


## FAQ
**Q**: Are my tasks saved?\
**A**: Yes, your tasks are saved after every modification of tasks.

**Q**: Where are my tasks saved?\
**A**: Your tasks are saved in `data/duke.txt` in the folder containing `duke.jar`.

**Q**: Can I look for part of a word?\
**A**: Yes, you can. E.g. `find amily` will return a task named `eat with family`

## Command Summary

|Action | Format, Examples|
|-------| ----------------|
|Add Todo | `todo [task name]` <br/> e.g. `todo Walk the dog`|
|Add Deadline | `deadline [task name] /by [yyyy-mm-dd]` <br/> e.g. `d Submit CS2103T iP /by 2021-09-17`|
|Add Event | `event [task name] /at [yyyy-mm-dd]` <br/> e.g. `event Guitar lesson /at 2021-09-13`|
|List | `list`, `l`|
|Mark Done | `done [task number]` <br/> e.g. `done 2`|
|Delete | `delete [task number]` <br/> e.g. `delete 3`|
|Find | `find [string to match]` <br/> e.g. `find family`|
|Exit | `bye`|

## Acknowledgements
Dwayne "The Rock" Johnson Profile picture from [Podchaser](https://www.podchaser.com/creators/dwayne-johnson-107ZzsApFJ/about)

Chatbot Profile Picture from [Chatbots Magazine](https://chatbotsmagazine.com/11-more-best-ux-practices-for-building-chatbots-67362d1104d9)
