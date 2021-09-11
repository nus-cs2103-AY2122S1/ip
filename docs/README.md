# How to master Jackie 101

> Free thing is great thing. \
> -[Jack](https://github.com/jackgugz)

### Duke frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use
  
  <img src="https://www.vippng.com/png/detail/7-77855_harold-thumbs-up-harold-thumbs-up-transparent.png" alt="Thumb up image" width="200"/>

### All you need to do is,

1. download it from [here](https://github.com/jackgugz/ip/releases/tag/A-Jar).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 👍
   And it is **FREE**! ⚡
   

## Features 

- [X] Managing todos
- [X] Managing deadlines
- [X] Managing events
- [X] Find tasks on specified date
- [X] Find tasks with specified keywords
- [X] Undo previous action

### Feature - Managing todos/deadlines/events

This feature allows CRUD functionalities of todo/deadline/event tasks

### Feature - Find tasks on specified date

This feature allows task list to be filtered by a specified date.

### Feature - Find tasks with specified keywords

This feature allows task list to be filtered by a specified key word/phrase.

### Feature - Undo previous action

This feature allows task list to be restored to the previous version before the last done operation. \
Only a maximum of one undo operation can be executed until a new opeartion is executed. 

## Usage

### `list {optional date}` - Show existing full/filtered task list

Show existing full/filtered task list based on the optional date argument. \
The date has to be in yyyy-MM-dd format.

Example of usage:

`list 2021-09-01`

Expected outcome:

```
Darling, here are the tasks with a schedule of 2021-09-01:
1. [D][ ] i added a deadline test (by: Sep 01 2021) 
2. [E][X] i added another event test (at: Sep 01 2021) 
```



### `find {keywords}` - Find tasks with matched keywords

Show existing filtered task list based on the specified keywords.

Example of usage:

`find CS2103T`

Expected outcome:

```
Darling, here are the matching tasks(with CS2103T) in your list:
1. [D][X] CS2103T ip submission (by: Sep 17 2021) 
```


### `todo {task}` - Add a todo

Add a todo with specified {task} content. \
The todo is added to the task list.

Example of usage: 

`todo finish CS2103T readme`

Expected outcome:

```
Gotcha my dear. I've added this task for you: 
[T][ ] finish CS2103T readme
Now you have 5 tasks in the list.
```



### `event/deadline {task} / {date}` - Add an event/deadline

Add a event/deadline with specified {task} content and specified {date}. \
The event/deadline is added to the task list. \
The date has to be in yyyy-MM-dd format.

Example of usage:

`deadline CS2103T ip submission / 2021-09-17`

Expected outcome:

```
Gotcha my dear. I've added this task for you: 
[D][ ] CS2103T ip submission (by: Sep 17 2021)
Now you have 5 tasks in the list.
```



### `done {index}` - Mark the specified task as done

Mark the specified task as done by an index with reference to the indexing when showing full list. 

Example of usage:

`done 5`

Expected outcome:

```
Noice! I've marked this task as done: 
[D][X] CS2103T ip submission (by: Sep 17 2021)
```



### `delete {index}` - Delete the specified task

Delete the specified task as done by an index with reference to the indexing when showing full list.

Example of usage:

`delete 5`

Expected outcome:

```
okie! I've removed this annoying task: 
[D][X] CS2103T ip submission (by: Sep 17 2021)
Now you have 4 tasks in the list.
```



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



### `bye` - exit the program

Exit the program.

Example of usage:

`bye`

Expected outcome:

```
Bye bye. Love you
```
