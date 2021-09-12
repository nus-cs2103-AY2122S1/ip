# 🌊 dory's User Guide

> "just keep swimming" - dory

dory is a chatbot that handles your queries and helps you to remember everything so that you don't have to!

## 🦭 what's there to sea-anemonemonee

-   add tasks (todo/event/deadline)
-   list tasks
-   mark tasks as done
-   find tasks  
-   delete tasks

## Usage

### `todo` - Adding a 'ToDo' task

Adds a 'ToDo' task to your current list.

Example of usage: 

- `todo (task)`
- `todo do my laundry`

Expected outcome:

```
> added: 
 [T][ ] do my laundry
```

### `event` - Adding an 'Event' task

Adds an 'Event' task to your current list.

Example of usage:

- `event (task) /at (yyyy-mm-dd)`
- `event birthday /at 2021-12-31`

Expected outcome:

```
> added: 
 [E][ ] birthday (at: Dec 31 2021)
```

### `deadline` - Adding a 'Deadline'

Adds a 'Deadline' to your current list.

Example of usage:

- `deadline (task) /by (yyyy-mm-dd)`
- `deadline finish homework /by 2021-12-31`

Expected outcome:

```
> added: 
 [D][ ] finish homework (by: Dec 31 2021)
```


### `list` - Listing all of your tasks

Lists all the task on your current list.

Example of usage:

- `list`

Expected outcome:

```
1. [T][ ] do my laundry
2. [E][ ] birthday (at: Dec 31 2021)
3. [D][ ] finish homework (by: Dec 31 2021)
```


### `done` - Completing a task

Marks a task as done on your current list.

Example of usage:

- `done (# of your task)`
- `done 1`

Expected outcome:

```
> i've marked this as done: 
 [T][X] do my laundry
```

### `find` - Finding a task

Finds the task on your list based on user's input

Example of usage:

- `find (search term)`
- `find birthday`

Expected outcome:

```
> here are the matching tasks in your list: 
 1. [E][ ] birthday (at: Dec 31 2021) 
```

### `delete` - Deleting a task

Deletes a task on your current list.

Example of usage:

- `delete (number of the task on your list)`
- `delete 3`

Expected outcome:

```
> i've removed this task: 
  [D][ ] finish homework (by: Dec 31 2021) 
```

## ⚓  where is it? p. sherman 42 wallaby way?

1.  download it from  [_here_](https://github.com/kvihashini/ip/releases/tag/A-Jar)
2.  double-click it
3.  have fun!
