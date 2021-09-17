# User Guide

**Duke** is a convenient desktop app for keeping track of your daily `Todo`'s, `Event`'s and `Deadline`'s
with a command-line like experience. Gone are the days of wrestling with clunky buttons and UI elements to 
get things done. With **Duke**, you can finally manage tasks with full command line efficiency!

## Features 

1. [Adding Todos](#todo)
2. [Adding Events](#event)
3. [Adding Deadlines](#deadline)
4. [Completing Tasks](#done)
5. [Deleting Tasks](#delete)
6. [Listing Tasks](#list)
7. [Finding a Tasks](#find)
8. [Exiting the application](#bye)

---

## Usage
<a name="todo"></a>
### Adding a `todo`

Adds a `todo` task to the task list.

**Format**: `todo TASK_DESCRIPTION`

**Example**: `todo something`

**Expected outcome**:

```
_________________________________________________
 Got it. I've added this task:
  [T][ ] something
 Now you have 1 task(s) in the list
_________________________________________________
 ```

---

<a name="event"></a>
### Adding an `event`

Adds an `event` task to the task list.

**Format**: `event TASK_DESCRIPTION /at DD/MM/YYYY HHMM`

**Example**: `event something /at 17/09/2021 1110` 

**Expected outcome**:

```
_________________________________________________
 Got it. I've added this task:
  [E][ ] something (at: Sep 17 2021 - 11 10 AM)
 Now you have 2 task(s) in the list
_________________________________________________
 ```

---

<a name="deadine"></a>
### Adding a `deadline`

Adds a `deadline` task to the task list.

**Format**: `deadline TASK_DESCRIPTION /at DD/MM/YYYY HHMM`

**Example**: `deadline something /by 25/09/2021 1110`

**Expected outcome**:

```
_________________________________________________
 Got it. I've added this task:
  [D][ ] something (by: Sep 25 2021 - 11 10 AM)
 Now you have 3 task(s) in the list
_________________________________________________ 
```
---

<a name="done"></a>
### Marking a task as `done`

Marks specified task(s) as `done`.

**Format**: `done TASK_ID [, TASK_ID, ...]`

**Example**: `done 1, 2`

**Expected outcome**:

```
_________________________________________________
 Nice! I've marked this task as done:
  [E][X] something (at: Sep 12 2021 - 11 10 AM)
 
 Nice! I've marked this task as done:
  [T][X] something
 
_________________________________________________
```
---

<a name="delete"></a>
### `delete`ing task(s)

Deletes specified task(s) from the task list.

**Format**: `delete TASK_ID [, TASK_ID, ...]`

**Example**: `delete 2, 3`

**Expected outcome**:

```
_________________________________________________
 Noted. I've removed this task:
  [D][ ] something (by: Sep 25 2021 - 11 10 AM)
 Now you have 2 task(s) in the list
 
 Noted. I've removed this task:
  [E][X] something (at: Sep 12 2021 - 11 10 AM)
 Now you have 1 task(s) in the list
 
_________________________________________________
```
---


<a name="list"></a>
### `list`ing task(s)

Displays the task list

**Example**: `list`

**Expected outcome**:

```
_________________________________________________
 1. [T][ ] something
 2. [E][ ] something (at: Sep 12 2021 - 11 10 AM)
 3. [D][ ] something (by: Sep 25 2021 - 11 10 AM)
_________________________________________________
```


---


<a name="find"></a>
### `find`ing task(s)

finds task with the given keyword in its description

***Format**: `find KEYWORD`

**Example**: `find ome`

**Expected outcome**:

```
_________________________________________________
 Here are the matching tasks in your list:
 1. [T][X] something
 2. [T][ ] homework
_________________________________________________
```
---


<a name="bye"></a>
### Exiting the application (`bye`)

Exists the application

**Example**: `bye`

**Expected outcome**:

```
_________________________________________________
 Bye. Hope to see you again!
_________________________________________________
```

