# User Guide
![Duke](Ui.png)

*Duke* is a Personal Assistant Chatbot that helps a person to keep track of various things. It is optimized for use via 
a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Features 

### Support for multiple types of Tasks
*Duke* supports 3 different types of tasks, namely Todo, Deadline and Event.
- Todo: A task without any date and time attributes.
- Deadline: A task with a deadline.
- Event: A task with a date and timeframe of the event.

### Manage Tasks
*Duke* allows you to efficiently find tasks on specific dates or with matching keywords so that you will never lose 
track of what you have on hand!

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `todo [description]` - Adds a ToDo task type 

Adds a ToDo task to the task list. 

Example of usage:

`todo homework`

Expected outcome:

```
--------------------------------------------------------------------------------
Got it! I've added this task:
  [T][ ] homework
Now you have 1 task(s) in the list.
--------------------------------------------------------------------------------
```

### `deadline [description] /by [dueDate] [dueTime]` - Adds a Deadline task type

Adds a Deadline task to the task list.

> Note: Format supported for date: DD-MM-YYYY, YYYY-MM-DD, DD/MM/YYYY. Format supported for time: HHMM, HH:MM

Example of usage:

`deadline cs2100 assignment /by 15/09/2021 1300`

Expected outcome:

```
--------------------------------------------------------------------------------
Got it! I've added this task:
  [D][ ] cs2100 assignment (by: Sep 15 2021 13:00)
Now you have 2 task(s) in the list.
--------------------------------------------------------------------------------
```

### `event [description] /at [date] [startTime]-[endTime]` - Adds a Event task type

Adds an Event task to the task list. 

> Note: Format supported for date: DD-MM-YYYY, YYYY-MM-DD, DD/MM/YYYY. Format supported for time: HHMM, HH:MM

Example of usage:

`event photoshoot /at 01/01/2022 1100-2300`

Expected outcome:

```
--------------------------------------------------------------------------------
Got it! I've added this task:
  [E][ ] photoshoot (at: Jan 01 2022 11:00-23:00)
Now you have 3 task(s) in the list.
--------------------------------------------------------------------------------
```

### `list` - Display task list

Display the task list with all items that are currently being tracked back to the user when requested.

Example of usage:

`list`

Expected outcome:

```
--------------------------------------------------------------------------------
Here are the tasks in your list:
1.[T][ ] homework
2.[D][ ] cs2100 assignment (by: Sep 15 2021 13:00)
3.[E][ ] photoshoot (at: Jan 01 2022 11:00-23:00)

--------------------------------------------------------------------------------
```

### `done [index]` - Mark task as done

Mark task at specified index of task list as done.

Example of usage:

`done 1`

Expected outcome:

```
--------------------------------------------------------------------------------
Good job! I've marked this task as done:
  [T][X] homework
--------------------------------------------------------------------------------
```

### `delete [index]` - Deletes a task

Deletes a task at specified index of task list.

Example of usage:

`delete 1`

Expected outcome:

```
--------------------------------------------------------------------------------
Noted! I've removed this task:
  [T][X] homework
Now you have 2 task(s) in the list.
--------------------------------------------------------------------------------
```


### `filter [date]` - Filter out relevant tasks

Filters out list of task on the specified date.

> Note: Format supported for date: DD-MM-YYYY, YYYY-MM-DD, DD/MM/YYYY.

Example of usage:

`filter 15/09/2021`

Expected outcome:

```
--------------------------------------------------------------------------------
Here is your list of task on this day:
1.[D][ ] cs2100 assignment (by: Sep 15 2021 13:00)

--------------------------------------------------------------------------------
```

### `find [keyword]` - Find a task

Find a task with a description that matches/contains the keyword.

Example of usage:

`find cs2100`

Expected outcome:

```
--------------------------------------------------------------------------------
Here are the matching tasks in your list:
1.[D][ ] cs2100 assignment (by: Sep 15 2021 13:00)

--------------------------------------------------------------------------------
```

### `help` - Get help

Gets the list of available command available to *Duke*.

Example of usage:

`help`

Expected outcome:

```
--------------------------------------------------------------------------------
Here is the list of my available commands!
1. todo [description] - Adds a ToDo task to task list
2. deadline [description] /by [date] [time] - Adds a Deadline to task list.
3. event [description] /at [date] [time]-[time] - Adds a Event to task list
4. filter [date] - Filters out list of task on this date
Date formats: dd/mm/yyyy, dd-mm-yyyy, yyyy-mm-dd
Time format: HHmm, HH:mm
5. list - Display list of items you have added
6. done [index of completed task] - Marks specified tasks as completed
7. delete [index of task to be deleted] - Deletes specified task
8. find [keyword to search for] - Finds tasks by specific keyword
9. bye - End the program
--------------------------------------------------------------------------------
```

### `bye` - quit program

Exits the program and close *Duke* GUI.

Example of usage:

`bye`

## Command summary
|      Action      | Format, Examples                                                                                            |
|:----------------:|:----------------------------------------------------------------------------------------------------------- |
|   **Add ToDo**   | `todo [description]`  <br> eg. `todo stuff`                                                                 |
| **Add Deadline** | `deadline [description] /by [dueDate] [dueTime]` <br> eg. `deadline cs2100 assignment /by 15/09/2021 1300`  |
|  **Add Event**   | `event [description] /at [date] [startTime]-[endTime]` <br> eg. `event photoshoot /at 01/01/2022 1100-2300` |
|     **List**     | `list`                                                                                                      |
|     **Done**     | `done [index]` <br> eg. `done 1`                                                                            |
|    **Delete**    | `delete [index]` <br> eg. `delete 1`                                                                        |
|    **Filter**    | `filter [date]` <br> eg. `filter 02/02/2020`                                                                |
|     **Find**     | `find [keyword]` <br> eg. `find stuff`                                                                      |
|     **Help**     | `help`                                                                                                      |
|     **Exit**     | `bye`                                                                                                       |
