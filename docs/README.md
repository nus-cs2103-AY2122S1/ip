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
* [Clear chat](#clear-chat-clear)
* [Exiting the program](#exiting-the-program-exit)

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
* DATE must be in format : `d/M/yy H:mm` or `d/M/yyyy H:mm`
* Time must be in 24-hour format. e.g `14:30` is 2:30 p.m
* Hour can either be single or double digit. e.g `09:50` or `9:50`

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
* DATE must be in format : `d/M/yy H:mm` or `d/M/yyyy H:mm`
* Time must be in 24-hour format. e.g `14:30` is 2:30 p.m
* Hour can either be single or double digit. e.g `09:50` or `9:50`

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

### Clear chat: `clear`
Clear the chatbox.  

Format : `clear`

### Exiting the program: `exit`
Exits the program.  

Format : `exit`


### FAQ
Q: How is my data being saved?  
A: Data is saved in the hard disk automatically after a command that changes the data. 
There is no need to save manually.

Q: What is the data/tasks.txt file generated after running the app for the first time?  
A: That is the save file. Please avoid deleting or editing the file as it could cause the save file to corrupt, 
resulting in loss of your data.

Q: I cannot run the app. How do I fix this?  
A: Ensure you have the Java version 11 or newer installed on your computer.  
If it still fails to run, it could be due to corruption of the save file, data/tasks.txt.   
Clear or delete the file. Do note that doing this erases your data.