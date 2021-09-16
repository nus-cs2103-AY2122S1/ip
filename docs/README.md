# GARY 🐌
---

## User Guide
---
Gary is a desktop app for anyone who needs to keep track of their myriad of tasks. It is optimized for quick text-based inputs via a Command Line Interface (CLI) while still having the ease of use of a Graphical User Interface (GUI). This app will make users lives easier through search and update functionalities.

- [Quick start](#quick-start)
- [Features](#features)
- [Usage](#usage)
- [Command summary](#command-summary)

## Quick start
---
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the application [here](https://github.com/BryannYeap/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Gary.
4. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds. The image below shows an example of the app with sample data.

![Image of Gary](https://github.com/BryannYeap/ip/blob/master/docs/Ui.png)

## Features 
---
Listed below are the features of Gary. 

### List tasks

Lists all the tasks you have on your list.

### Add Todo task

Adds a Todo task into your list. A Todo is a task with no restricted time or deadline. 

### Add Deadline task

Adds a Deadline task into your list. A Deadline is a task with a deadline.

### Add Event task

Adds an Event task into your list. An Event is a task that happens at a specific period of time.

### Mark tasks as done

Checks the task as done.

### Delete tasks

Removes tasks from your list.

### Find tasks

Searches for a task based on your search terms.

### Update tasks

Updates a task in your list.

## Usage
---
Stated below is how you use Gary to utilise the listed features.


### `list` - List tasks

Lists out all tasks in your list, including tasks that are marked as done or undone. Tasks include Todo, Deadline and Event.

Format: 

`list`

Expected outcome:

```
This is your current list!

    1.[T][]  Borrow a book
    2.[D][X] Complete Math assignment (by: Sep 18 2021)
    3.[E][]  Biology exam (at: Tuesday 2-4pm)
```

* Todo tasks are marked as `[T]`
* Deadline tasks are marked as `[D]`
* Event tasks are marked as `[E]`
* Tasks that are done are marked as `[X]`
* Tasks that are not done are marked as `[]`

### `todo` - Adds a Todo task 

Adds a Todo task into your list with the given description.

Format:

`todo <description>`

Example of usage:

`todo Borrow a book`

Expected outcome:

```
You have successfully added the following task!
    
    [T][] Borrow a book

You now have 1 task in your list!
```

* `[T]` means that this is a Todo task
* `[]` means that the task is not marked as done (default state)

### `deadline` - Adds a Deadline task

Adds a Deadline task into your list with the given description and deadline date (yyyy-mm-dd).

Format:

`deadline <description> /by <yyyy-mm-dd>`

Example of usage:

`deadline Complete Math assignment /by 2021-09-18`

Expected outcome:

```
You have successfully added the following task!

    [D][] Complete Math assignment (by: Sep 18 2021)

You now have 2 tasks in your list!
```

* `[D]` means that this is a Deadline task
* `[]` means that the task is not marked as done (default state)

### `event` - Adds an Event task

Adds an Event task into your list with the given description and event time.

Format:

`event <description> /at <event time>`

Example of usage: 

`event Biology exam /at Tuesday 2-4pm`

Expected outcome:

```
You have successfully added the following task!

    [E][] Biology exam (at: Tuesday 2-4pm)

You now have 3 tasks in your list!
```

* `[E]` means that this is an Event task
* `[]` means that the task is not marked as done (default state)

### `done` - Marks task as done

Marks a specified task as done to signify its completion.

Format:

`done <task number>`

Example of usage:

`done 1`

Expected outcome:

```
This task is successfully marked as done!

    [T][X] Borrow a book
```

* `[X]` means that this task is marked as done

### `delete` - Deletes task

Deletes a specified task.

Format:

`delete <task number>`

Example of usage:

`delete 2`

Expected outcome:

```
This task is successfully deleted!

    [D][] Complete math assignment (by: Sep 18 2021)

You now have 2 tasks in your list!
```

### `find` - Finds task

Finds a task based on your search terms.

Format:

`find <search terms>`

Example of usage:

`find Biology`

Expected outcome:

```
These tasks have descriptions that contain the phrase: `Biology`

    Task 2. [E][] Biology exam (at: Tuesday 2-4pm)
```

* Search terms are case sensitive
* Order of words and letters matter (i.e Searching for `find exam Biology` would not have worked for the above task)

### `update` - Updates task

Updates a specified task based on your given descriptions so that you do not have to delete and re-add a task in order to change it.

Format:

`update <task number> <task description + required parameters>`

Example of usage:

`update 2 deadline Study for Biology exam /by 2021-09-18`

Expected outcome:

```
This Task is successfully updated from:

    [E][] Biology exam (at: Tuesday 2-4pm)

to:

    [D][] Study for Biology exam (by: Sep 18 2021)
```

### `bye` - Exits the application

Exits Gary 0.5s after showing a goodbye message.

Format:

`bye`

### Command summary
---

Action | Format | Example
-------|--------|--------
List | `list` | `list`
Add Todo | `todo <description>` | `todo Borrow a book`
Add Deadline | `deadline <description> /by <yyyy-mm-dd>` | `deadline Complete Math assignment /by 2021-09-18`
Add Event | `event <description> /at <event time>` | `event Biology exam /at Tuesday 2-4pm`
Done | `done <task number>` | `done 1`
Delete | `delete <task number>` | `delete 2`
Find | `find <search terms>` | `find Biology`
Update | `update <task number> <task description + required parameters>` | `update 2 deadline Study for Biology exam /by 2021-09-18`
