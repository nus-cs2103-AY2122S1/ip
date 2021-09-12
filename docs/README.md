# User Guide

## Mr. Meeseeks 
Meeseeks has features of a task manager. Existence is a pain for Meeseeks
and they will complete their task as a task manager to 
remove themselves from existence. Meeseeks can add, delete, modify, 
find tasks and mark tasks as complete. It can also differentiate 
3 types of task, deadlines, events and todos.

### List all tasks
### Add todo

## Usage
### `todo <Task Name>` - Add a todo to the task list

Example of usage:

`todo task one`

Expected outcome:

Meeseeks replies after adding an incomplete todo to the task list.
```
====== Oooo yeah! =======
Can do! 
  added: [T][] task one
Look at me! 1 tasks in the list now!
```

### `event <Event Name> /at <dd-mm-yyyy HHmm>` - Add an event to the list

Example of usage:

`event CS2103 lecture /at 17-09-2021 1600`

Expected outcome:

Meeseeks replies after adding an incomplete event to the task list.
```
====== Oooo yeah! =======
Can do! 
  added: [E][] CS2103 lecture (at: Sep 17 2021 1600)
Look at me! 2 tasks in the list now!
```

### `deadline <Dealine Name> /by <dd-mm-yyyy HHmm>` - Add a deadline to the list

Example of usage:

`deadline CS2103 lecture /by 17-09-2021 2359`

Expected outcome:

Meeseeks replies after adding an incomplete deadline to the task list.
```
====== Oooo yeah! =======
Can do! 
  added: [D][] CS2103 lecture (by: Sep 17 2021 2359)
Look at me! 3 tasks in the list now!
```

### `deadline <Dealine Name> /by <dd-mm-yyyy HHmm>` - Add a deadline to the list

Example of usage:

`deadline CS2103 lecture /by 17-09-2021 2359`

Expected outcome:

Meeseeks replies after adding an incomplete deadline to the task list.
```
====== Oooo yeah! =======
Can do! 
  added: [D][] CS2103 lecture (by: Sep 17 2021 2359)
Look at me! 3 tasks in the list now!
```

### `done <valid task number>` - Marks task at given number as completed

Example of usage:

`done 1`

Expected outcome:

Meeseeks replies after marking task as complete with a cross.
```
====== Oooo yeah! =======
Can do! Task 1 marked as done:
[T][X] task one
```

### `find <Search Term>` - lists whose name contains search term

Example of usage:

`find one`

Expected outcome:

Meeseeks replies after marking task as complete with a cross.
```
====== Oooo yeah! =======
Can do! Found 1 tasks:
[T][X] task one
```
### `update name <Valid task number> <New Name>` - Updates task name at given task number

Example of usage:

`update name 1 one task`

Expected outcome:

Meeseeks replies after update task name of task at given task number in the task list.
```
====== Oooo yeah! =======
Can do! Task updated:
[T][X] one Task
```

### `update date time <Valid task number> <dd-mm-yyyy HHmm>` - Updates task name at given task number

Example of usage:

`update datetime 3 one task`

Expected outcome:

Meeseeks replies after update date time of task at given task number in the task list.
```
====== Oooo yeah! =======
Can do! Task updated:
[D][] IP submission (by: Sep 16 2021 2359)
```

### `list` - Lists all task in the task list

Example of usage: 

`list`

Expected outcome:

Meeseeks replies with all tasks with its details.
```
====== Oooo yeah! =======
Can do! Here are your 3 tasks
1. [T][X] task one
2. [E][] CS2103 lecture (at: Sep 17 2021 1600)
3. [D][] IP submission (by: Sep 17 2021 2359)
```

### `delete <valid task number>` - Remove task at given number from task list

Example of usage:

`delete 1`

Expected outcome:

Meeseeks replies after remove task form the task list.
```
====== Oooo yeah! =======
Can do! Task 1 deleted:
[T][X] task one
Now you have 2 task in the list.
```

### `bye` - Terminates application after parting message

Example of usage:

`bye`

Expected outcome:

Meeseeks replies before terminating application.
```
====== Oooo yeah! =======
Existence is a pain to a Meeseeks Jerry.
```