# User Guide

## Meeseeks 
Meeseeks is a task manager with the personality of [Mr. Meeseks](https://rickandmorty.fandom.com/wiki/Mr._Meeseeks), 
a fictional character from the television series Rick and Morty. 
Existence is a pain for Meeseeks and they will complete their 
task as a task manager to remove themselves from existence. Meeseeks 
can add, delete, modify, find tasks and mark tasks as complete. 
It can also differentiate 3 types of task, deadlines, events and todos.

## Usage
### `todo <Task Name>` - Adds a todo

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

### `event <Event Name> /at <dd-mm-yyyy HHmm>` - Adds an event

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

### `deadline <Dealine Name> /by <dd-mm-yyyy HHmm>` - Adds a deadline

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

### `done <valid task number>` - Marks task as completed

Example of usage:

`done 1`

Expected outcome:

Meeseeks replies after marking task at given task number as complete with a cross.
```
====== Oooo yeah! =======
Can do! Task 1 marked as done:
[T][X] task one
```

### `find <Search Term>` - Find by task name

Example of usage:

`find one`

Expected outcome:

Meeseeks replies with a list of task with search term in its task name.
```
====== Oooo yeah! =======
Can do! Found 1 tasks:
[T][X] task one
```
### `update name <Valid task number> <New Name>` - Updates task name

Example of usage:

`update name 1 one task`

Expected outcome:

Meeseeks replies after update task name of task at given task number in the task list.
```
====== Oooo yeah! =======
Can do! Task updated:
[T][X] one Task
```

### `update date time <Valid task number> <dd-mm-yyyy HHmm>` - Updates task date time

Example of usage:

`update datetime 3 one task`

Expected outcome:

Meeseeks replies after update date time of task at given task number in the task list.
```
====== Oooo yeah! =======
Can do! Task updated:
[D][] IP submission (by: Sep 16 2021 2359)
```

### `list` - Lists all tasks

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

### `delete <valid task number>` - Removes task 

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

### `bye` - Terminates application

Example of usage:

`bye`

Expected outcome:

Meeseeks replies before terminating application.
```
====== Oooo yeah! =======
Existence is a pain to a Meeseeks Jerry.
```