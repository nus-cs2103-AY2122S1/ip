# Duke User Guide

## Features 

* [Add to-dos](#add-to-do-todo)
* [Add deadlines](#add-deadline-deadline)
* [Add events](#add-event-event)
* [Delete tasks](#delete-tasks-delete)
* [List out tasks](#list-tasks-list)
* [Mark multiple tasks as done](#mark-tasks-done)
* [Delete multiple tasks](#delete-tasks-delete)
* [Search for tasks](#search-for-tasks-find)

## How to use

### Add to-do: `todo`
Adds a to-do task to the list of tasks.

Format : `todo TASK_NAME`

Example:

`todo read book`
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in your list.
```


### Add deadline: `deadline`
Adds a deadline task to the list of tasks.

Format : `deadline TASK_NAME /by DATE`
* DATE must be in format : `d/M/uu H:mm` or `d/M/uuuu H:mm`

Example:

`deadline return book /by 15/9/21 14:00` or `deadline return book /by 15/9/2021 14:00`
```
Got it. I've added this task:
[D][ ] return book (at: Sep 15 2021 2:00 PM)
Now you have 1 task in your list.
```


### Add event: `event`
Adds an event task to the list of tasks.

Format : `event TASK_NAME /at DATE`
* DATE must be in format : `d/M/uu H:mm` or `d/M/uuuu H:mm`

Example:

`event project meeting /at 15/9/21 14:00` or `event project meeting /at 15/9/2021 14:00`
```
Got it. I've added this task:
[E][ ] return book (at: Sep 15 2021 2:00 PM)
Now you have 1 task in your list.
```

### List tasks: `list`
Lists out all your tasks.

Format : `list`

Example:

`list`

```
Here are your tasks:
1.  [T][ ] borrow book
2.  [D][ ] return book (by: Jun 6 2021 4:30 PM)
3.  [E][ ] project meeting (at: Sep 6 2021 9:30 PM)
4.  [T][ ] join sports club
5.  [T][ ] read book
```

### Mark tasks: `done`
Mark tasks as done.

Format : `done INDEX [MORE_INDEXES]`
* You can enter more than one index

Example:

`done 2 5`

```
Very well. These tasks have been marked.
2.  [D][X] return book (by: Jun 6 2021 4:30 PM)
5.  [T][X] read book
```


### Delete tasks: `delete`
Delete tasks from list of tasks.

Format : `delete INDEX [MORE_INDEXES]`
* You can enter more than one index

Example:

`delete 2 5`

```
Very well. These tasks have been deleted.
2.  [D][ ] return book (by: Jun 6 2021 4:30 PM)
5.  [T][ ] read book
```


### Search for tasks: `find`
Search for tasks that match the given keyword.

Format : `find KEYWORD`

Example:

`find book`

```
Here are the tasks you were looking for:
1.  [T][ ] borrow book
2.  [D][ ] return book (by: Jun 6 2021 4:30 PM)
3.  [T][ ] read book
```

### Saving the data
Data is saved in the hard disk automatically after a command that changes the data. 
There is no need to save manually.