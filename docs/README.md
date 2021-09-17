# User Guide

## Features 

### Keep track of your tasks with Duke's todo list!

Duke supports 3 types of tasks:
1. **todo**: Tasks to be completed in your own time.
2. **deadline**: Tasks to be completed by a deadline.
3. **event**: Events that occur on an event date.

### Mark tasks as done

Checks off the tasks that you have marked as done.

### List

Lists all your current tasks.

### Delete tasks

Delete the tasks that are no longer relevant.

### Search among tasks

Search for tasks containing a keyword of your choice.

## Usage

### `todo` - Add a todo task

Add a todo task to the current task list.

Example of usage: 

`todo read book`

Expected outcome:

Update the current task list with your new todo task.

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task in the list.
```


### `deadline` - Add a deadline task

Add a deadline task to the current task list.

Example of usage:

`deadline finish iP /by 2021-09-17`

Expected outcome:

Add a deadline task and reflects its respective deadline in MMM dd yyyy format.

```
Got it. I've added this task:
    [D][ ] finish iP (by: Sep 17 2021)
Now you have 2 tasks in the list.
```


### `event` - Add an event task

Add an event task to the current task list.

Example of usage:

`event party /at 2021-09-18`

Expected outcome:

Add a event task and reflects its event date in MMM dd yyyy format.

```
Got it. I've added this task:
    [E][ ] party (at: Sep 18 2021)
Now you have 3 tasks in the list.
```


### `done` - Mark tasks as done

Check task off in the task list to represent it as done. Able to check multiple tasks off at once.

Example of usage:

`done 1 2`

Expected outcome:

Mark tasks indicated by the task numbers given as done.

```
Nice! I've marked these tasks as done:
    [T][X] read book
    [D][X] finish iP (by: Sep 17 2021)
```


### `delete` - Delete tasks of your choice

Delete tasks off the task list. Able to delete multiple tasks at once.

Example of usage:

`delete 1 3`

Expected outcome:

Delete tasks at the given task numbers.

```
Noted. I've removed these tasks:
    [T][X] read book
    [E][ ] party (at: Sep 18 2021)
Now you have 1 tasks in the list.
```


### `list` - Display current task list

Display current task list.

Example of usage:

`list`

Expected outcome:

If the task list is empty:
```
There are currently no tasks in your list.
```

If the task list contains tasks:
```
Here are the tasks in your list:
1.  [T][X] read book
2.  [D][X] finish iP (by: Sep 17 2021)
3.  [E][ ] party (at: Sep 18 2021)
```



### `find` - Find tasks containing keyword

Display tasks containing any keyword, phrase or letter of the user's choice.

Example of usage:

`find book`

Expected outcome:

Description of the outcome.

```
Here are tasks that contain the keyword 'book':
1.  [T][X] read book
```


### `bye` - Close the application

Terminate and close the application.

Example of usage:

`bye`

Expected outcome:

Terminate and close the application.


### Save tasks to disk

Automatically save the tasks to disk.