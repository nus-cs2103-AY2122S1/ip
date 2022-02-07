# DukeBob User Guide

## Features 

### 3 Different types of task to be tracked

1. ToDos - Simple tasks that has to be completed.
2. Deadlines - Tasks that has to be completed by an indicated date.
3. Events - Task occurring at a specific date.

### Variety of List functions

To aid your personal task tracking, you can mark tasks as complete, search for
specific tasks etc.

### Automated Saving and Loading

Changes and updates to your task list are automatically saved/loaded in a local txt file.

### Archive Function

Ability to archive your current task list (saved under a txt file named using a time stamp), and start on a new task list.

## Usage

### `todo` - Creates a todo task & add to task list

Adds a todo into your task list.

**Example of usage:**

`todo downloadDukeBob`

**Expected outcome:**

```
Got it. I've added this task:
[T][ ] downloadDukeBob
Now you have 1 tasks in the list.
```
<br/><br/>
### `deadline` - Creates a deadline task & add to task list

Adds a deadline into your task list.

**Example usage:**

`deadline submitIP /by 2021-01-02`

* Date must be given in YYYY-MM-DD format.

**Expected outcome:**

```
Got it. I've added this task:
[D][ ] submitIP (by: 2 Jan 2021)
Now you have 2 tasks in the list.
```
<br/><br/>

### `event` - Creates an event task & add to task list

Adds an event into your task list.

**Example usage:**

`event graduation /at 2024-05-12`
* Date must be given in YYYY-MM-DD format.

```
Got it. I've added this task:
[E][ ] graduation (at: 12 May 2024)
Now you have 3 tasks in the list.
```

<br/><br/>

### `list` - Lists your tasks

Lists out all tasks with index number.

**Example usage:**

`list`

**Expected outcome:**

A list of all your tasks (if any) would be sent by DukeBob.

```
Here are the tasks in your list:
1. [T][ ] downloadDukeBob
2. [D][ ] submitIP (by: 2 Jan 2021)
3. [E][ ] graduation (at: 12 May 2024)
```

<br/><br/>

### `done` - Mark task as complete

Toggles completion of task at given index. If task is already done, DukeBob will inform you that task has already been completed.

**Example usage:**

`done 1`

**Expected outcome:**

Outcome based on current list in this help document.

```
Nice! I've marked this task as done!
[T][X] downloadDukeBob
```

```
Task has already been marked as complete!
```

<br/><br/>

### `delete` - Delete task

Deletes a task in the list **permanently**.

**Example usage:**

`delete 1`

**Expected outcome:**

Outcome based on current list in this help document.
```
Noted. I've removed this task:
[T][X] downloadDukeBob
There are 2 tasks in your list
```

<br/><br/>

### `find` - Search for a matching task

Uses the key term to search for matching tasks in current task list.

**Example usage:**

`find grad`

**Expected outcome:**
```
Here are the matching tasks in your list:
1. [E][ ] graduation (at: 12 May 2024)
```

<br/><br/>

### `archive` - Archives the current tasks and wipes the list

Saves the current task list in an archive file and set the current list to be empty.

**Example usage:**

`archive`

**Expected outcome:**

```
Task list archived at: 20 Sep 2021, 11:02:20 PM.txt
```
* `list` would now show an empty list.
* Archival file can be found in same folder as duke.txt in /data folder.
<br/><br/>

### `bye` - DukeBob waves bye
Exits the app & saves the current state of the task list into data.txt, 
overriding the previous version.

**Example usage:**

`bye`

**Expected outcome:**

The following goodbye message will be displayed. The app would close after 2 seconds.

`Bye. Hope to see you again soon!`

