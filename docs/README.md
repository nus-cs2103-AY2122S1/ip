# User Guide

## Features 

### Manage your tasks!

* Todo tasks
* Deadline tasks
* Event tasks

Manage them all!

### Mark your tasks!

* When a task is completed, mark it as done!

### Undo your actions!

* Made a mistake? Undo it!


## Usage

### `Keyword` - list

Lists the tasks in your list with their type and status (done or not).

Example of usage: 

`list`

Expected outcome:

Tasks in the list are listed.

```
Here are the tasks in your list:
1. [T][] fly
```


### `Keyword` - done

Marks a task as done.

Example of usage:

`done 1`

Expected outcome:

Task number 1 in the list is marked as done.

```
Nice: I've marked this task as done:
[X] fly
```


### `Keyword` - delete

Deletes a task from the list.

Example of usage:

`delete 1`

Expected outcome:

Deletes the specified task from the list.

```
Noted. I've removed this task.
[T][X] fly
```


### `Keyword` - todo

Adds a todo to the list.

Example of usage:

`todo eat food`

Expected outcome:

Adds a todo with the title entered into the list.

```
Got it. I've added this task:
[T][] eat food
```


### `Keyword` - deadline

Adds a deadline task to the list along with its deadline.

Example of usage:

`deadline exercise /by 2021-09-20 1830`

Expected outcome:

Adds the deadline to the list.

```
Got it. I've added this task:
[D][] exercise | (by Sep 20 2021 1830)
```

### `Keyword` - event

Adds a event task to the list along with its event time.

Example of usage:

`event go school /at 2021-09-20 1230`

Expected outcome:

Adds the event to the list.

```
Got it. I've added this task:
[E][] go school | (at: Sep 20 2021 1230)
```

### `Keyword` - find

Searches for tasks in the list with a keyword.

Example of usage:

`find food`

Expected outcome:

Displays all tasks in the list containing the keyword.

```
Here are the matching tasks in your list:
1. [T][] eat food
```

### `Keyword` - undo

Undoes the last executed action on the list.

Example of usage:

`undo`

Expected outcome:

Reverts the list to the list before the execution of the last action.

```
I've undone your last command!
```

