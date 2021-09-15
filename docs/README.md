# User Guide

## Features list
1. Add/Delete Todos, Events and Deadlines
2. View lists anytime
3. Mark your completed tasks


### 1. Add/Delete Todos, Events and Deadlines

- Add or delete 3 types of tasks; Todos, Deadlines and Events.


### 2. View lists anytime

- Use the **_list_** command to view the tasks stored in your list!

### 3. Mark your completed tasks

- Mark your tasks as done when you have completed them!

## Usage

### `list` - list out all your added tasks
####Example of usage:

`list`

####Expected outcome:
```
Here are the tasks in your list:
1. [T][X] hello
2. [T][X] hi
Describe the action and its outcome.
```

### `todo <taskDescription>` - Adds todo task to your list

####Example of usage: 

`todo myTodo`

####Expected outcome:
```
Got it. I've added this task:

[T][ ] myTodo

Now you have 3 tasks in the list.
```

### `deadline <deadlineDescription> /by <yyyy-mm-dd> <hhmm>`- Adds deadline task to your list
####Example of usage:
`deadline myDeadline /by 2021-09-15 1800`
 
####Expected outcome:
```
Got it. I've added this task:

[D][ ] myDeadline (by: Sep 15 2021 6pm)

Now you have 4 tasks in the list.
```

###`event <taskDescription> /at <yyyy-mm-dd> <hhmm>` - Adds event task to your list 

####Example of usage:
`event myEvent /at 2021-09-15 1200`

####Expected outcome:
```
Got it. I've added this task:

[E][ ] myEvent (at: Sep 15 2021 12pm)

Now you have 5 tasks in the list.
```

###`delete <taskIndex>` - Deletes task at the given index in the list

####Example of usage:
`delete 3`

###Expected outcome:
```
Noted. I've removed this task:

[T][ ] myTodo

Now you have 4 tasks in the list.
```

###`undo` - Undo the most recent action if it is undo-able

####Example of usage:
`undo`

####Expected outcome:
```
Got it. I've added this task:

[T][ ] myTodo

Now you have 5 tasks in the list.
```
###`done <taskIndex>` - Marks the task with the given taskIndex as done

####Example of usage:
`done 3`

####Expected outcome:
```
Nice! I've marked this task as done:

[D][X] myDeadline (by: Sep 15 2021 6pm)
```