# User Guide

Duke helps you keep track of your todos, deadlines and events.

## Features 

### 1. Keep track of your tasks with Duke's todo list!

Duke supports 3 types of tasks:
A. **todo**: Tasks to be completed in your own time.
B. **deadline**: Tasks to be completed by a deadline.
C. **event**: Events that occur on an event date.


### 2. Mark tasks as done

Checks off the tasks that you have marked as done.


### 3. Delete tasks

Delete the tasks that are no longer relevant.


### 4. List

Lists all your current tasks.


### 5. Search among tasks

Search for tasks containing a keyword of your choice.


### 6. Save

Automatically save task to your hard disk.


### 7. Exit

Close the program with a single command.



## Usage

### 1A. `todo <Task Name>` - Add a todo task

Add a todo task to the current task list.

_Example of usage:_

`todo read book`

_Expected outcome:_

Update the current task list with your new todo task.

```
Got it. I've added this task:
    [T][ ] read book
Now you have 1 task in the list.
```


### 1B. `deadline <Task Name> /by <yyyy-mm-dd>` - Add a deadline task

Add a deadline task to the current task list.

_Example of usage:_

`deadline finish iP /by 2021-09-17`

_Expected outcome:_

Add a deadline task and reflects its respective deadline in MMM dd yyyy format.

```
Got it. I've added this task:
    [D][ ] finish iP (by: Sep 17 2021)
Now you have 2 tasks in the list.
```


### 1C. `event <Task Name> /at <yyyy-mm-dd>` - Add an event task

Add an event task to the current task list.

_Example of usage:_

`event party /at 2021-09-18`

_Expected outcome:_

Add a event task and reflects its event date in MMM dd yyyy format.

```
Got it. I've added this task:
    [E][ ] party (at: Sep 18 2021)
Now you have 3 tasks in the list.
```


### 2. `done <Task Numbers>` - Mark tasks as done

Check task off in the task list when it is done. Only able to input valid task numbers. Able to check multiple tasks off at once by entering multiple task numbers, each separated by a whitespace. 

_Example of usage:_

`done 1 2`

_Expected outcome:_

Mark tasks indicated by the task numbers given as done.

```
Nice! I've marked these tasks as done:
    [T][X] read book
    [D][X] finish iP (by: Sep 17 2021)
```


### 3. `delete <Task Numbers>` - Delete tasks of your choice

Delete tasks off the task list. Only able to input valid task numbers. Able to delete multiple tasks at once by entering multiple task numbers, each separated by a whitespace.

_Example of usage:_

`delete 1 3`

_Expected outcome:_

Delete tasks at the given task numbers.

```
Noted. I've removed these tasks:
    [T][X] read book
    [E][ ] party (at: Sep 18 2021)
Now you have 1 tasks in the list.
```


### 4. `list` - Display current task list

Display current task list.

_Example of usage:_

`list`

_Expected outcome:_

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



### 5. `find <Keyword>` - Search for tasks containing keyword

Display tasks containing any keyword, phrase or letter of the user's choice. 

_Example of usage:_

`find book`

_Expected outcome:_

Search for tasks containing the keyword 'book'.

```
Here are tasks that contain the keyword 'book':
1.  [T][X] read book
```


### 6. Save tasks to disk

Automatically save the tasks to disk.


### 7. `bye` - Close the application

Terminate and close the application.

_Example of usage:_

`bye`

_Expected outcome:_

Terminate and close the application.



## Acknowledgements:
* Original Duke template forked from Prof Damith CS2103T Github
* GUI Design based on JavaFX tutorials
