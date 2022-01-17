# User Guide

## Set Up
1. Ensure that you have Java 11 or above installed in your Computer (verify by typing java --version in command prompt)
2. Download the latest release from the repo
3. Double-click the file to launch Friend!
4. Try any of the commands out! (e.g. list)

## Features 

### Keep Tasks
Ability to quickly add tasks from a text interface.

### Categorise tasks - ToDos, Deadlines, Events
Choose between 3 main types of tasks to keep your workflow in check.

### Update tasks
Mark you tasks as done, or delete them if you would like to clean your list.

### Find tasks
Find tasks by keywords or tags

### Spice it up with tags! #UseFriend
Use some tags to organise your existing tasks, or to give yourself some additional motivation or fun factor!


## Usage

### `list` - View your tasks
Shows the list of existing tasks.

Example of usage:

`list`

Expected outcome:
```
These are your existing tasks!
1. [D][X] CS2103T Quiz #important (by: Friday 4pm)
2. [E][X] CS2101 OP1 (at: Sep 13 2021)
3. [T][ ] play Pacman
```

### `todo` - Add a todo
Adds a todo to your list.

Example of usage:

`todo assignment`

Expected outcome:
```
added: [T][ ] assignment to your to-do list!
Now you have 4 tasks in the list.
```

### `deadline` - Add a deadline
Adds a deadline to your list.

Example of usage:

`deadline read notes /by 2021-09-30`

Expected outcome:
```
added: [D][ ] read notes (by: Sep 30 2021) to your to-do list!
Now you have 5 tasks in the list.
```

### `event` - Add an event
Adds an event to your list.

Example of usage:

`event presentation /at 9.30am tomorrow`

Expected outcome:
```
added: [E][ ] presentation (at: 9.30am tomorrow) to your to-do list!
Now you have 6 tasks in the list.
```

### `done` - Mark task as done
Marks completed tasks with an 'X'.

Example of usage:

`done 4`

Expected outcome:
```
Hooray! You've completed task
[X] assignment
```

### `delete` - Delete a task
Deletes a task from the list forever.

Example of usage:

`delete 3`

Expected outcome:
```
removed the following task from your to-do list:
[E][ ] Pacman with Friend #exciting (at: Sep 10 2021)
Now you have 5 tasks in the list.
```

### `tag` - Tag a task!
Tags a task with any #tag that you choose.

Example of usage:

`tag 5 productivity`

Expected outcome:
```
added tag #productivity to task number 5!
```

### `find` - Filter your tasks
Find tasks that match a specific keyword or tag.
Search is case-insensitive.
For keywords, partial matches are displayed.
For tags, only full matches will be displayed. 

Example of usage:

`find cs`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][X] CS2103T Quiz #important (by: Friday 4pm)
2. [E][X] CS2101 OP1 (at: Sep 13 2021)

```

Example of usage:

`find #important`

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][X] CS2103T Quiz #important (by: Friday 4pm)
```

### `bye` - Exit the program
Exit Friend.

Example of usage:

`bye`

Expected outcome:
```
See you again, my friend!
```

## Saving data
All of your tasks will be saved to your local disk.

Friend remembers your tasks everytime you restart the program!
