# User Guide

Duke is a task management application that provides support for:
* Todos
* Deadlines
* Events
* Do Afters

---
## Table of Contents- [User Guide](#user-guide)
  - [Features](#features)
    - [Autosave](#autosave)
  - [Usage](#usage)
    - [`todo` - Add a Todo](#todo---add-todo)
    - [`deadline` - Add a Deadline](#deadline---add-deadline)
    - [`event` - Add an Event](#event---add-event)
    - [`doafter` - Add a Do After](#doafter---add-doafter)
    - [`list` - List all tasks](#list---list-all-tasks)
    - [`done` - Mark task as done](#done---mark-task-as-done)
    - [`delete` - Delete a task](#delete---delete-task)
    - [`find` - Find tasks](#find---find-tasks)
    - [`bye` - Exit application](#bye---exit-application)

## Autosave

When performing any operation that changes the state of your tasks (adding, deleting, editing tasks), changes are automatically saved to disk.

---
## Usage

### `todo` - Add todo

Adds a todo item with a *description*.

Input:
```
todo borrow book
```
Expected Output:
```
Got it. I've added this task:
    [T][ ] borrow book
Now you have 1 task in the list.
```
---
### `deadline` - Add deadline

Adds a deadline with a *description* and *datetime* in `dd-MM-yyyy HH:mm` format.

Input:
```
deadline return book /by 20-08-2021 15:00
```
Expected Output:
```
Got it. I've added this task:
    [D][ ] return book (by: Aug 20 2021 15:00)
Now you have 2 tasks in the list.
```
---
### `event` - Add event

Adds an event with a *description* and *datetime* in `dd-MM-yyyy HH:mm` format.

Input:
```
event project meeting /at 22-08-2021 08:00
```

Expected Output:
```
Got it. I've added this task:
    [E][ ] project meeting (at: Aug 22 2021 08:00)
Now you have 3 tasks in the list.

```
---
### `doafter` - Add do after

Adds a do after with a *description* and *do after datetime* in `dd-MM-yyyy HH:mm` format.

Input:
```
doafter Rewatch lecture /after 22-08-2021 23:59
```

Expected Output:
```
Got it. I've added this task:
    [DA][ ] Rewatch lecture (after: Aug 22 2021 23:59)
Now you have 4 tasks in the list.
```
---
### `list` - List all tasks

Lists all previously added todos, deadlines, events and do afters.

Input:
```
list
```
Expected Output:
```
Here are the items in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Aug 20 2021 15:00)
3. [E][ ] project meeting (at: Aug 22 2021 08:00)
4. [DA][ ] Rewatch lecture (after: Aug 22 2021 23:59)
```
Note the general format displayed by list is as follows:
```
<index>. <Task><Status> <Description> <Datetime (if any)>
```
Example:
```
1. [DA][ ] Rewatch lecture (after: Aug 22 2021 23:59)
```
* `1. ` Indicates the index of the task is 1
* `[DA]` Indicates the task is a do after
* `[ ]` Indicates the task is not completed
  * `[X]` would indicate a completed task
* `Rewatch lecture` is the description of the task
* `after: Aug 22 2021 23:59` is the datetime associated with the task

---
### `done` - Mark task as done

Marks the given todo, deadline, event or do after at the specified *index* as done.

Use the `list` command to obtain the index of the desired item.

Input:
```
done 1
```
Expected Output:
```
Nice! I've marked this task as done:
    [T][X] borrow book
```
---

### `delete` - Delete task

Deletes the given todo, deadline, event or doafter at the specified *index*.

Use the `list` command to obtain the index of the desired item.

Input:
```
delete 1
```
Expected Output:
```
Noted. I've removed this task:
    [T][X] borrow book
Now you have 3 tasks in the list.
```
---
### `find` - Find tasks

Searches tasks for descriptions matching the *search string*.

Input:
```
find book
```
Expected Output:
```
Here are the matching tasks in your list:
1.[D][ ] return book (by: Fri, 20 Aug 2021 03:00PM)
```
---
### `bye` - Exit application

Input:
```
bye
```
Expected Behaviour:

Exits from application.