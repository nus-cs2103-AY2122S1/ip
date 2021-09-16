# User Guide

## Features 

### Manage tasks

Duke can manage 3 different types of tasks for the user. 

- Task types:
    - Todo
    - Deadline
    - Event

- Functionality:
    - Add
    - Delete 
    - Mark a task as done
    - List
    - Clear
    - Check Date
    - Find

### Archive

Duke allows the user to save the current task list into an archive file for future access.

#### Note: Source Images
Find the source for images used:
* Background: https://www.pinterest.com/pin/602215781414404190/
* Character with notepad: https://wantubizhi.com/image.aspx
* Thinking Cat: https://tenor.com/view/peach-cat-and-goma-cat-thinking-planning-cat-gif-20587989


## Usage

### `todo` - Add a Todo Task

Create a new Todo task and add it to the end of the task list.

Example of usage: 

`todo Read book`

Expected outcome: 

Todo task with the description "Read me" is added to the task list.

```
Got it. I've added this task:
  [T][ ] Read book
Now you have 1 tasks in the list.
```
### `deadline` - Add a Deadline Task

Create a new Deadline task, with a date specified in the format yyyy-mm-dd and add it to the end of the task list.

Example of usage:

`deadline Assignment 1 /by 2021-09-04`

Expected outcome:

Deadline task with the description "Assignment 1" is added to the task list.
Date format has been changed to 'mmm d yyyy'.

```
Got it. I've added this task:
  [D][ ] Assignment 1 (by: Sep 4 2021)
Now you have 2 tasks in the list.
```

### `event` - Add an Event Task

Create a new Event task, with a date specified in the format yyyy-mm-dd and add it to the end of the task list.

Example of usage:

`deadline Project Meeting /at 2021-09-06`

Expected outcome:

Deadline task with the description "Project Meeting" is added to the task list.
Date format has been changed to 'mmm d yyyy'.

```
Got it. I've added this task:
  [E][ ] Project Meeting (by: Sep 6 2021)
Now you have 3 tasks in the list.
```

### `help` - List all available commands

Displays all available commands to the user.

Example of usage:

`help`

Expected outcome:

list of all available commands to the user.
```
(Note – date format: yyyy/mm/dd)
Commands you can run:
* todo (DESC) – creates a todo task
* deadline (DESC) /by (DATE) – creates a deadline task
* event (DESC) /at (DATE) – creates an event task
* list – lists all tasks
* done (INDEX) – mark a task as done
* delete (INDEX) – deletes a task
* find (KEYWORD) – finds all tasks that contain the keyword
* check (DATE) – finds all tasks with matching date
* clear – clears task list and hard disk
* archive /saveAs (FILENAME) – creates new archive file
* archive /load (FILENAME) – loads an archive file
* archive /delete (FILENAME) – deletes an archive file
* archive /list – lists all archive files
```

### `list` - List all tasks

Displays all tasks in the task list to the user.

Example of usage:

`list`

Expected outcome:

list of all tasks are displayed to the user.
```
Here are the tasks in your list:
  1. [T][ ] Read book
  2. [D][ ] Assignment 1 (by: Sep 4 2021)
  3. [E][ ] Project Meeting (by: Sep 6 2021)
```

### `done` - Marks as Done

Marks the given task (based on its position in the task list) as done.

Example of usage:

`done 2`

Expected outcome:

Second task in the list is marked as done.
```
Nice! I've marked this task as done:
  [D][X] Assignment 1 (by: Sep 4 2021)
```

### `delete` - Deletes a task from the task list

Deletes the given task (based on its position in the task list).

Example of usage:

`delete 1`

Expected outcome:

First Task is deleted from the list and hard disk.

```
Noted. I've removed this task:
  [T][ ] Read book
Now you have 2 tasks in the list.
```

### `find` - Searches for tasks by keyword

Displays a list of tasks with descriptions that contains the word specified.
Example of usage:

`find Project`

Expected outcome:

List of tasks with the word 'Project' in the description
```
Here are the matching tasks in your list:
  1. [E][ ] Project Meeting (at: Sep 6 2021)
```

### `check` - Searches for tasks by date

Displays a list of tasks, of which the date matches the date specified.

Example of usage:

`check 2021-09-04`

Expected outcome:

List of tasks that occur on 4 Sep 2021 is returned.
```
Here are the tasks on Sep 4 2021:
  1. [D][X] Assignment 1 (by: Sep 4 2021)
```

### `clear` - Clears task list and hard disk.

Removes all tasks in the task list and all tasks saved in the hard disk.

Example of usage:

`clear`

Expected outcome:

Task list and hard disk are now both empty.
```
Noted, I have cleared your list and hard disk.
```

### `archive /saveAs` - Creates a new Archive file

New Archive file created with the name specified by the user and current task list are saved to the Archive file.

Example of usage:

`archive /saveAs Y2_Sem1`

Expected outcome:

Saves the current tasks into a new Archive file, with the name 'Y2_Sem1.txt'

```
Got it! A new Y2_Sem1 Archive has been created.
```

### `archive /load` - Loads tasks from Archive

Loads in the tasks from the given archive file.
Error occurs if the current task list and hard disk are not empty.

Example of usage:

`archive /load Y2_Sem1`

Expected outcome:

Loads in the tasks from Y2_Sem1.txt Archive file.
```
You have loaded Y2_Sem1 Archive.
```


### `archive /list` - List all archive files

Displays a list of all archive files the user has.

Example of usage:

`archive /list`

Expected outcome:

Lists all archive files.

```
Here are your archives:
  1. Y2_Sem1.txt
  2. Personal.txt
```

### `archive /delete` - Deletes an Archive file

Deletes an existing Archive file, according to the file name specified by the user.

Example of usage:

`archive /delete Y2_Sem1`

Expected outcome:

Y2_Sem1.txt Archive file is deleted.

```
Noted, Y2_Sem1 Archive has been deleted.
```

### `bye` - Exits the application

Close out of the application.

Example of usage:

`bye`

Expected outcome:

Close out of the application.

```
Bye. Hope to see you again soon!
```