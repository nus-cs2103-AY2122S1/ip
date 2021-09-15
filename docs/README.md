# User Guide
![Ui.png](Ui.png)

Welcome to *Duke*! *Duke* is a simple CLIBot designed to assist you in getting your life back on track.

*Duke* can remember tasks that you forget or just simply don't bother to remember!
## Features 
ℹ️ Notes about the command format:
* Words in angle brackets are the parameters to be supplied by the use.
  * e.g. in `todo <description>`, `description` is a parameter which can be used as `todo quiz`.
* Commands are case-insensitive.
    * e.g. `todo quiz`, `TODO quiz`, `Todo quiz` are all acceptable command formats.
* Extraneous parameters for commands that do not take in parameters (such as `list`, `exit`) are not accepted.
    * e.g. `list 123` is not an acceptable format.
  


### Support for multiple types of Tasks:

*Duke* supports 3 different types of tasks, namely Todos, Events and Deadlines.
### 1. Todos
Todos are your basic tasks with just a description.

### 2. Events
Events allow you to fix a specified time to your tasks.

### 3. Deadlines
Deadlines are tasks with a hard due date.

### Manage tasks efficiently
*Duke* allows you to efficiently find tasks with matching keywords and mark tasks as done so that you will never lose track of what you have on hand!
## Usage
### `todo <description>` -  adds a Todo task type

Adds a Todo task to the task list

Example of usage: 

`todo quiz`

Expected outcome:


```
Voila! ^_^ I've added this task:
- [T][ ] quiz
You currently have 1 task(s) in the list.
```
### `event <description> /at <date-time>` - adds an Event task type
Adds an Event task to the task list.
> Note: Formats supported for *date-time*:
> * yyyy-mm-dd (e.g 2021-12-26)
> * yyyy-mm-dd HHmm (e.g 2021-12-26 1900)
> * any other string literal (e.g USC, 3PM)

Example of usage:

`event volleyball training /at 2021-12-26 1900`

Expected outcome:
```
Voila! ^_^ I've added this task:
- [E][ ] volleyball training (at: 26 Dec 2021, 7.00 PM)
You currently have 2 task(s) in the list.
```
### `deadline <description> /at <due-date>` - adds a Deadline task type
Adds a Deadline task to the task list.
> Note: Formats supported for *due-date*:
> * yyyy-mm-dd (e.g 2021-12-26)
> * yyyy-mm-dd HHmm (e.g 2021-12-26 1900)
> * any other string literal (e.g Monday, 3PM)

Example of usage:
```
deadline cs2100 assignment /by 2021-09-15 1300
```
Expected outcome:
```
Voila! ^_^ I've added this task:
- [D][ ] cs2100 assignment (by: 15 Sep 2021, 1.00 PM)
You currently have 3 task(s) in the list.
```
###  `list` - displays task list
Displays all the items stored in the task list.

Example of usage:

`list`

Expected outcome:
```
Here are the task(s) in your list! ^_^

1. [T][ ] quiz
2. [E][ ] volleyball training (at: 26 Dec 2021, 7.00 PM)
3. [D][ ] cs2100 assignment (by: 15 Sep 2021, 1.00 PM)
```
### `done <index>` - marks a task as done
Marks the task at the specified index as done.

Example of usage:

`done 1`

Expected outcome:
```
Good Job! :D I've marked this task as done:
- [T][✓] quiz
You currently have 2 undone task(s) in the list.
```
### `delete <index>` - deletes a task
Deletes the task at the specified index.

Example of usage:

`delete 1`

Expected outcome:
```
Voila! ^_^ I've deleted this task:
- [T][✓] quiz
You currently have 2 task(s) in the list.
```
### `find <keyword>` - finds a task
Finds a task within the task list that matches or contains the keyword.

Example of usage:

`find training`

Expected outcome:

```
Here are the matching task(s) in your list!
2. [E][ ] volleyball training (at: 26 Dec 2021, 7.00 PM)
```
### `sort` - sorts task list in chronological order and displays task list
Sorts the task list in chronological order, with completed tasks being ordered first and then displays the task list.

Example of usage:

`sort`

Expected outcome:
```
Task list is sorted!
Here are the task(s) in your list! ^_^

1. [D][ ] cs2100 assignment (by: 15 Sep 2021, 1.00 PM)
2. [E][ ] volleyball training (at: 26 Dec 2021, 7.00 PM)
```
### `sort reverse` - sorts task list in reverse chronological order and displays task list.
Similar to `sort`, but task list is ordered in reverse chronological order, with completed tasks being ordered last.

Example of usage:

`sort`

Expected outcome:
```
Task list is sorted!
Here are the task(s) in your list! ^_^

1. [E][ ] volleyball training (at: 26 Dec 2021, 7.00 PM)
2. [D][ ] cs2100 assignment (by: 15 Sep 2021, 1.00 PM)
```
### `bye` - exits the program
Exits the program and closes the GUI window.

Example of usage:

`bye`

Expected outcome:

```
Goodbyeeee! Hope to see you again soon! :>
```
*Duke* GUI closes.