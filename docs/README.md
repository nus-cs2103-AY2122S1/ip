# User Guide
**LebronChatBot** is a desktop app that can help you manage your tasks and to-do lists.
It is text-based and optimised for use via a GUI. If you can type fast, **LebronChatBot** manage your tasks 
faster than traditional GUI apps.
## Features 

### Simple Task Management

Quickly create any task and Lebron will take note of them. You can store todos, deadlines and events here!


### Date and Time

Lebron accepts date and time for the **deadline** and **event** tasks. This will allow you to store more
information about your tasks!

## Usage

### `list` - Lists the tasks currently stored by Lebron

Shows a list of all tasks in LebronChatBot.

Format: `list`

Example output:
```
Here are the tasks in your list:
[T][] wash the dishes
```

### `done` - Marks a task as done

Format: `done INDEX`

Example of usage: done 1

Note: 
* The index must be a positive integer 1,2,3...
* The index must not exceed the number of tasks currently stored by Lebron.

Example output:
```
Nice! I've marked this task as done:
[T][X] wash the dishes
```

### `todo` - Add a *todo* task in Lebron

Format: `todo DESCRIPTION`

Example of usage: todo clean my room

Example output:
```
Got it. I've added this task:
[T][] clean my room
Now you have 2 tasks in the list
```

### `deadline` - Add a *deadline* task in Lebron

Format: `deadline DESCRIPTION /by DATETIME`

Example of usage: deadline 2103 iP /by 2021-09-17 2359

Note:
* The format of the DATETIME must be **YYYY-MM-DD HHmm**

Example output:
```
Got it. I've added this task:
[D][] c2103 iP (by: 17 Sep 2021 23:59)
Now you have 3 tasks in the list
```

### `event` - Add an *event* task in Lebron

Format: `event DESCRIPTION /at DATETIME`

Example of usage: event movie night /at 2021-09-18 0001

Note:
* The format of the DATETIME must be **YYYY-MM-DD HHmm**

Example output:
```
Got it. I've added this task:
[E][] movie night (at: 18 Sep 2021 00:01)
Now you have 4 tasks in the list
```

### `delete` - Delete a task from Lebron

Format: `delete INDEX`

Example of usage: delete 3

Note:
* The index must be a positive integer 1,2,3...
* The index must not exceed the number of tasks currently stored by Lebron.

Example output:
```
Noted. I've removed this task:
[D][] c2103 iP (by: 17 Sep 2021 23:59)
Now you have 3 tasks in the list
```

### `find` - Find tasks that have descriptions containing the given keywords

Shows a list of all tasks in LebronChatBot that contain the given keywords.

Format: `find KEYWORD`

Example of usage: find clean

Note:
* if you enter more than 1 keyword (ie *find* clean my), lebron will search for tasks that match the string "clean my". 

Example output:
```
Here are the matching tasks in your list:
1. [T][] clean my room
```

### `undo` - Undo your last "undo-able" action

Undo-able actions are actions that change the state of the Tasks stored by Lebron. These are todo,
deadline, event, delete, done commands. 

Format: `undo`

Note:
* Lebron will always undo the latest "undo-able" action, if possible.

Example output:
```
I have removed your last action.
```

### `bye` - Ends the program

Lebron replies and quickly ends the program.

Format: `bye`

Note:
* The program will terminate.

Example output:
```
Bye. Hope to see you again soon!
```

