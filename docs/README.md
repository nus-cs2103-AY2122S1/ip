# User Guide
> Yiyang-bot, a GUI-based all-in-one task manager :D

## Features 
- Manage todo items, deadlines and events
- Mark entries as done, archive or delete them
- Search entries by keywords

### Todo, Deadline, and Events
Tasks are divided into 3 categories, where
- a Todo is a simple task to be completed.
- a Deadline is a task with a date indicating when you must complete it by.
- an Event is a task with a date showing when it will take place.

### Archive
- You are able to archive or unarchive any task depending on its relevance.
- When you archive a task, you will no longer be able to see it in your normal task list, but in the arhived list.
- When you search tasks by keywords, matched results in your normal list and the archive will be shown separetely.

## Usage

### `todo` - Add a new Todo
Add a new Todo item with specified content.

Example of usage:

`todo read book`

Expected outcome:

A new Todo instance with content "read book" is created.
```
Got it. I've added this task: 
  [T][✘] read book
Now you have 1 tasks in the list.
```



### `deadline` - Add a new Deadline
Add a new Deadline item with specified content and due date.

Example of usage:

`deadline assignment /by 2021-12-31`

Expected outcome:

A new Deadline instance with content "assignment" that is due by 31 Dec. 2021 is created.
```
Got it. I've added this task: 
  [D][✘] assignment (by: Dec 31 2021)
Now you have 2 tasks in the list.
```



### `event` - Add a new Event
Add a new Event item with specified content and date.

Example of usage:

`event meeting /at 2021-12-31`

Expected outcome:

A new Deadline instance with content "meeting" that is on 31 Dec. 2021 is created.
```
Got it. I've added this task: 
  [E][✘] meeting (by: Dec 31 2021)
Now you have 3 tasks in the list.
```



### `done` - Mark a task as done
Mark the item with the specified index as done.

Example of usage:

`done 1`

Expected outcome:

Mark the first item in the list as done.
```
Nice! I've marked this task as done: 
  [T][✓] read book
```



### `delete` - Delete a task
Delete the item with the specified index in the list.

Example of usage:

`delete 1`

Expected outcome:

Delete the first item in the list. 
It will also notify you how many items left.
```
Noted. I've removed this task:
  [T][✓] read book
Now you have 2 tasks in the list.
```



### `archive` - Archive a task
Archive the item with the specified index in the list.

Example of usage:

`archive 1`

Expected outcome:

Archive the first item in the list.
It will also notify you how many items left.
```
Noted. I've archived this task:
  [D][✘] assignment (by: Dec 31 2021)
Now you have 1 tasks in the list.
```



### `unarchive` - Unarchive a task
Move the archived item with the specified index in the archive list back to the normal task list.

Example of usage:

`unarchive 1`

Expected outcome:

Unarchive the first item in the archive, and move it to the end of the normal task list.
It will also notify you how many items left.
```
Noted. I've get back this task from archive:
  [D][✘] assignment (by: Dec 31 2021)
Now you have 2 tasks in the list.
```



### `list` - Display all tasks
Display all items in the task list.

Example of usage:

`list`

Expected outcome:

Display all items.

```
Here are the tasks in your list:
1. [E][✘] meeting (by: Dec 31 2021)
2. [D][✘] assignment (by: Dec 31 2021)
```



### `list-archive` - Display all archived tasks
Display all items in the task list.

Example of usage:

`list-archive 1`

Expected outcome:

Display all archived items.

```
You don't have any task in archive now.
```



### `find` - Search tasks
Find all items and archived items that match the keyword.

Example of usage:

`find meeting`

Expected outcome:

All items with keyword "meeting" are displayed.
Since there is no archived items matched, nothing is shown for the archived results.
```
Here are the matching tasks in your list:
1.[E][✘] meeting (by: Dec 31 2021)
```


### `bye` - Exit the application
Exit the application.

Example of usage:

`bye`