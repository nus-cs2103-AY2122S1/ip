# User Guide

Welcome to Duke Vision! 
This README will teach you how to navigate and use the chatbot effectively.

## Features 

The following is a list of features currently in use for Duke:
- [x] Add Todos
- [x] Add Events
- [x] Add Deadlines
- [x] Mark tasks as done
- [x] Check list of tasks
- [x] Search for tasks containing keywords
- [x] Delete tasks

### Adding your tasks to Duke

Add a task of a certain type(Deadline, Event or Todo) to your list of tasks to complete.
You can check your outstanding list of tasks as well.

### Manage task list

You can mark tasks as done once you have completed it, delete tasks from the list, or search
for keywords and Duke will highlight to you tasks that match the keywords you searched for!

## Usage

### `todo` - Add a Todo to your task list

Generic usage: 

`todo {task description}` or `t {task description}`

Example of usage: 

`todo mop the floor`

Expected outcome:

Adds a todo with description mop the floor to the task list.

Expected output:

```
Understood! The following task has been added:
 [T][ ] mop the floor
You have 8 tasks in your current list
```

### `event` - Add an Event to your task list. Events require a date and time when the event begins.

Generic usage: 

`event {event description} /on DD/MM/YYYY HH:mm` or `e {event description} /on DD/MM/YYYY HH:mm`

Example of usage: 

`event mahjong night /on 20/09/2021 15:00`

Expected outcome:

Adds an event to task list containing date time of the event and the description of the event.

Expected output:

```
Understood! The following task has been added:
 [E][ ] mahjong night (on: Sep 20, 2021 15:00) 
You have 9 tasks in your current list
```

### `deadline` - Add a Deadline to your task list. Deadlines require a date and time when the deadline is due.

Generic usage: 

`deadline {task description} /by DD/MM/YYYY HH:mm` or `d {task description} /by DD/MM/YYYY HH:mm`

Example of usage: 

`deadline iP submission /by 17/09/2021 23:59`

Expected outcome:

Adds a deadline to task list containing date time of the deadline and the description of the deadline.

Expected output:

```
Understood! The following task has been added:
 [D][ ] iP submission (by: Sep 17, 2021 23:59) 
You have 10 tasks in your current list
```
### `list` - Displays a list of all tasks you have put added in the past that have not been deleted.

Generic usage: 

`list` or `l`

Example of usage: 

`list`

Expected outcome:

Summons a list of items that have been added to your list, including those that you added the previous time
Duke was opened.

Expected output:

```
You have 3 tasks in your current list.
1: [T][ ] Run program
2: [D][X] look at program (by: 16 Sep, 2021 18:00)
3: [E][ ] Attend conference (on 20 Sep, 2021 20:00)
```

### `delete` - Deletes the task specified from the task list. Task number refers to the number on the task list.

Generic usage: 

`delete {task number}` or `del {task number}`

Example of usage: 

`delete 2`

Expected outcome:

Deletes the second task on the list. If following the tasks on the list in the `list` command example, deletes
look at program.

Expected output:

```
Note: I've removed the following task from your list: 
[D][X] look at program (by: Sep 16, 2021 18:00)
```

### `done` - Marks a task as completed. Taask number refers to the number on the task list.

Generic usage: 

`done {task number}` *Note that done does not have a short form command because it is already quite short*

Example of usage: 

`done 1`

Expected outcome:

Marks the 1st task as completed. Following the list that was given in the `list` example, marks Run program as done, 
which is denoted by an X in the second box.

Expected output:

```
Great job! I've marked the following as done:
[T][X] Run program
```

### `help` - Displays a list of commands and their functions so that users can better understand what Duke does.

Generic usage: 

`help` or `h`

Example of usage: 

`help`

Expected outcome:

Shows a list of helpful commands and their uses so users can understand more about the commands Duke have.

Expected output:

```
The following commands are compatible with our task-planning chatbot!
list(or l for short):
provides a list of items in your task list.
done {number}:
ticks the task with that number as done!
delete(or del for short) {number}:
deletes the task with that number off the list.
bye:
ends the program.
```

### `find` - Creates a list of tasks that contain the words that you queried into the program.

Generic usage: 

`find {keywords}` or `f {keywords}`

Example of usage: 

`find Run`

Expected outcome:

Creates a list of tasks that contain the keyword Run.

Expected output:

```
Here are the matching tasks in your list:
[T][X] Run program

```

### `bye` - Bids farewell to the program. 

Generic usage: 

`bye`

Expected outcome:

Duke sadly says goodbye to you too.

Expected output:

```
Bye! Please visit me again!

```

