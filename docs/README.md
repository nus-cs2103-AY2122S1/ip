# How to master Jackie 101

> Free thing is great thing. \
> -[Jack](https://github.com/jackgugz)

### Jackie frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use
  

### All you need to do is,

1. download it from [here](https://github.com/jackgugz/ip/releases/download/A-Release-2/Jackie-1.0.jar).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 👍
   And it is **FREE**! ⚡
   
<br/>

---

## Features 

- [X] Managing todos
- [X] Managing deadlines
- [X] Managing events
- [X] Find tasks on specified date
- [X] Find tasks with specified keywords
- [X] Undo previous action

### Feature - Managing todos/deadlines/events

Allow create, read and delete functionalities of todo/deadline/event tasks

### Feature - Find tasks on specified date

Allow task list to be filtered by a specified date.

### Feature - Find tasks with specified keywords

Allow task list to be filtered by a specified key word/phrase.

### Feature - Undo previous action

Allow task list to be restored to the previous version before the last done operation. \
Only a maximum of one undo operation can be executed until a new opeartion is executed. 

<br/>

---

## Usage
- Note that the actual output with input code from _example of usage_ may **NOT** produce the exact same result 
as _expected outcome_ with varying state of the existing task list.

### `list` - Show existing full task list

Show existing full task list.

Example of usage:

`list`

Expected outcome:

```
Darling, here are the tasks in your list:
1. [T][X] i added a todo test
2. [D][ ] i added a deadline test (by: Sep 01 2021)
3. [E][ ] i added an event test (at: Mar 28 1998)
4. [E][X] i added another event test (at: Sep 01 2021)
5. [D][X] CS2103T ip submission (by: Sep 17 2021)
```

<br/>

### `list DATE` - Show existing filtered task list by DATE

Show existing filtered task list based on the DATE argument. \
The date has to be in _yyyy-MM-dd_ format.

Example of usage:

`list 2021-09-01`

Expected outcome:

```
Darling, here are the tasks with a schedule of 2021-09-01:
1. [D][ ] i added a deadline test (by: Sep 01 2021) 
2. [E][X] i added another event test (at: Sep 01 2021) 
```

<br/>

### `find KEYWORD` - Find tasks with matched keyword

Show existing filtered task list based on the specified KEYWORD.\
If more than one KEYWORDs is provided, only the first will be considered.

Example of usage:

`find CS2103T`

Expected outcome:

```
Darling, here are the matching tasks(with CS2103T) in your list:
1. [D][X] CS2103T ip submission (by: Sep 17 2021) 
```

<br/>

### `todo TASK` - Add a todo

Add a todo with specified TASK content. \
The todo is added to the task list.

Example of usage: 

`todo finish CS2103T readme`

Expected outcome:

```
Gotcha my dear. I've added this task for you: 
[T][ ] finish CS2103T readme
Now you have 5 tasks in the list.
```


<br/>

### `deadline TASK / DATE` - Add an deadline

Add a deadline with specified TASK content and specified DATE. \
The deadline is added to the task list. \
The date has to be in _yyyy-MM-dd_ format.

Example of usage:

`deadline CS2103T ip submission / 2021-09-17`

Expected outcome:

```
Gotcha my dear. I've added this task for you: 
[D][ ] CS2103T ip submission (by: Sep 17 2021)
Now you have 5 tasks in the list.
```
<br/>

### `event TASK / DATE` - Add an event

Add an event with specified TASK content and specified DATE. \
The event is added to the task list. \
The date has to be in _yyyy-MM-dd_ format.

Example of usage:

`event internship day / 2021-10-13`

Expected outcome:

```
Gotcha my dear. I've added this task for you: 
[E][ ] internship day (at: Oct 13 2021)
Now you have 6 tasks in the list.
```


<br/>

### `done INDEX` - Mark the specified task as done

Mark the specified task as done by the INDEX with reference to the indexing when showing full list. 

Example of usage:

`done 5`

Expected outcome:

```
Noice! I've marked this task as done: 
[D][X] CS2103T ip submission (by: Sep 17 2021)
```

<br/>

### `delete INDEX` - Delete the specified task

Delete the specified task as done by the INDEX with reference to the indexing when showing full list.

Example of usage:

`delete 5`

Expected outcome:

```
okie! I've removed this annoying task: 
[D][X] CS2103T ip submission (by: Sep 17 2021)
Now you have 4 tasks in the list.
```

<br/>

### `undo` - Undo the previous operation

Undo the previous operation. \
Restore the task list to the previous version. \
A maximum of 1 undo operation can be done until a new operation is executed.

Example of usage:

`undo`

Expected outcome:

```
Gotcha my dear! I have restored the previous version.
1. [T][X] i added a todo test
2. [D][ ] i added a deadline test (by: Sep 01 2021)
3. [E][ ] i added an event test (at: Mar 28 1998)
4. [E][X] i added another event test (at: Sep 01 2021)
5. [D][X] CS2103T ip submission (by: Sep 17 2021)
```

<br/>

### `bye` - Show Farewell Message

Show Farewell Message.\
The program window will remain open and the program will continue running. 

Example of usage:

`bye`

Expected outcome:

```
Bye bye. Love you
```
