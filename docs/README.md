# Mr. Meeseeks Bot

Mr. Meeseeks Bot can chat with you 
to help keep track of various tasks you have!

![Ui Mockup](./Ui.png)

## Features 

### Track 3 Different Types of Tasks

Mr. Meeseeks helps to keep track of 3 different types of tasks: 
To-do, Deadline & Event

### Manipulate and Sort Tasks 

Mr. Meeseeks can help add new tasks, mark your tasks as done, delete completed tasks, 
and sort tasks by their category and date/time!

### Save and Load Tasks for Later

Mr. Meeseeks saves your list of tasks in a separate text file on every update. 

When you exit the bot and return the next time, 
the bot reloads the list from the text file. 
You can continue where you left off! 

## Usage

### SETUP

Download Meeseeks.jar from the repo 

### `start/help` - Shows list of bot commands

If you are new and unfamiliar with using the bot, 
this should be the first command you type into the GUI, 
it shows a list of possible user commands to use!

Type into the GUI and Mr. Meeseeks Bot will process and show you a response!

### `todo` - Creates a new Todo task
A Todo task is the most basic task. 
It consists of a simple description

To create a todo task, follow the following syntax:

`todo (description)`

**Example:**
- Input: `todo eat lunch`
- Output: `[T] [] eat lunch`
- Result : New ToDo task is created and added to Task List.

### `deadline` - Creates a new Deadline task
A Deadline task consists of a description like a ToDo.
It also has a Date and Time which represents the deadline.

Follow the following syntax:

`deadline (description) yyyy-mm-dd hh:mm`

(If the date & time are not valid, an error message will prompt the user.)

**Example:**
- Input: `deadline CS2100 Assignment 2021-09-14 13:00`
- Output: `[D] [] CS2100 Assignment (by: 2021-09-14 13:00)`
- Result : New Deadline task is created and added to Task List.

### `event` - Creates a new Event task

An Event task consists of a description like a ToDo.
It has a Date, a startTime and endTime to represent the duration.

`event (description) yyyy-mm-dd hh:mm hh:mm`

(If the date & times are not valid, an error message will prompt the user.)

**Example:**
- Input: `event CS2101 Meeting 2021-09-18 15:00 16:00`
- Output: `[E] [] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)`
- Result : New Event task is created and added to Task List.

### `list` - Shows the current list of tasks

**Example:**
- Input: `list`
- Output: 
```
  1. [T] [ ] eat lunch
  2. [D] [ ] CS2100 Assignment (by: 2021-09-14 13:00)
  3. [E] [ ] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)
```

### `sort` - Sorts the task list by type and date/time

Sorts the list by type first: todos, then deadlines, then events.

Deadlines and Events will then be sorted by dates and (start) times

**Example:**

Existing List:
```
1. [D] [ ] SEP Application (by: 2021-10-05 23:59)
2. [E] [ ] New Year's Day (at: 2021-01-01 15:00 to 17:00)
3. [T] [ ] Eat lunch
4. [D] [ ] CS2103T project (by: 2021-09-23 23:59)
5. [E] [ ] CCA Practice (at: 2021-09-25 19:00 to 21:00) 
6. [E] [ ] CS2103T meeting (at: 2021-09-25 15:00 to 16:00)
```

- Input: `sort`
- Output:
```
1. [T] [ ] Eat lunch
2. [D] [ ] CS2103T project (by: 2021-09-23 23:59)
3. [D] [ ] SEP Application (by: 2021-10-05 23:59)
4. [E] [ ] New Year's Day (at: 2021-01-01 15:00 to 17:00)
5. [E] [ ] CS2103T meeting (at: 2021-09-25 15:00 to 16:00)
6. [E] [ ] CCA Practice (at: 2021-09-25 19:00 to 21:00)
```
- Result: The list is sorted according to task type, 
  then within deadlines and events, the tasks are ordered chronologically by date and then time


### `done` - Marks a task in the list as done


First find the task number of the task to mark as done (use the `list` command)

Then input `done (task Number)`, the task will be marked as done with an 'X'

Re-marking a task that is already marked as done will have no effect.

**Example:**

Existing list:
```
  1. [T] [ ] eat lunch
  2. [D] [ ] CS2100 Assignment (by: 2021-09-14 13:00)
  3. [E] [ ] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)
```

- input: `done 2`
- output:`[D] [X] CS2100 Assignment (by: 2021-09-14 13:00)`
- result: The deadline task at position 2 is marked as done.

### `delete` - Deletes a task from the list

Similar to the `done` command, find the task number of the task in the list to be deleted.

Then input `delete (task Number)` and the task will be deleted.

**Example:**

Existing list:
```
  1. [T] [ ] eat lunch
  2. [D] [ ] CS2100 Assignment (by: 2021-09-14 13:00)
  3. [E] [ ] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)
```

- Input: `delete 2`
- Output: 
```
    1. [T] [ ] eat lunch
    2. [E] [ ] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)
```
- Result: The deadline task at position 2 is deleted, the remaining tasks are 
shifted up the list.
  
### `find` - Returns all tasks which match a target description

Based on what the user inputs, the bot will search for all tasks which 
have a description containing the exact target string and show to the user.

`find (target description string)`

**Example:**

Existing list:
```
  1. [T] [ ] eat lunch
  2. [D] [ ] CS2100 Assignment (by: 2021-09-14 13:00)
  3. [E] [ ] CS2101 Meeting (at: 2021-09-18 15:00 to 16:00)
  4. [D] [ ] CS2100 Project (by: 2021-10-01 23:59)
```

- Input: `find CS2100`
- Output:
```
    [D] [ ] CS2100 Assignment (by: 2021-09-14 13:00)
    [D] [ ] CS2100 Project (by: 2021-10-01 23:59)
```