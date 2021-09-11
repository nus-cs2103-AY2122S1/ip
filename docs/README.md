# User Guide

## Features 

### Todo

Add a todo task into your list of tasks.

### Deadline

Add a deadline into your list of tasks.

### Event

Add an event into list of tasks.

### List

Lists out all tasks in the tasks list.

### Done

Marks a task at specified index as done.

### Delete

Deletes a tasks at specified index.

### Sort

Sorts all tasks in chronological order with the earliest at the top and latest at the bottom.

(Tasks without date and time associated will be automatically allocated at the bottom, sorted lexicographically)


### Bye

Exits the program


## Usage

### `t`  or  `todo` - Adds a Todo into tasks list 

Example of usage: 

`todo <Description>`

Examples:

`todo Go jogging` - adds a todo "Go jogging" into your task list.

`t Go jogging` - have the same effect and output as the above command.

Expected outcome:

```
Got it. I've added this task:
[T][ ] Go jogging
Now you have 1 task in the list.
```

### `d` or `deadline` - Adds a deadline into tasks list

Example usage:

`deadline <Description> /by <Date> <Time>` - Date must be in YYYY-MM-DD format and Time in 24h format.

Examples:

`deadline Submit assignment /by 2021-08-21 2359` - adds a deadline for "Submit assignment"
to be done by 21st Aug 2021 11:59pm.

`d Submit assignment /by 2021-08-21 2359` - will add the same deadline as above.

Expected outcome:

```
Got it. I've added this task:
[D][ ] Submit assignment (by: Aug 21 2021 11:59PM)
Now you have 2 tasks in the list.
```



### `e` or `event` - Adds an event into tasks list

Example usage:

`event <Description> /at <Date> <Time>` - Date must be in YYYY-MM-DD format and Time in 24h format.

`event John's birthday /at 2021-08-21 0800` - Adds an event for "John's birthday" at 21st Aug 2021 8am

`e John's birthday /at 2021-08-21 0800` - will add the same event as above.

Expected outcome:

```
Got it. I've added this task:
[E][ ] John's birthday (at: Aug 21 2021 8:00AM)
Now you have 3 tasks in the list.
```

### `ls` or `list` - Prints out the full tasks list

Example usage:

`ls` or `list` - Any additional parameters after will be ignored.

Expected outcome:

A list of all tasks will be printed
```
Your current tasks are:
1. [T][ ] Go jogging
2. [D][ ] Submit assignment (by: Aug 21 2021 11:59PM)
3. [E][ ] John's birthday (at: Aug 21 2021 8:00AM)
```


### `dn` or `done` - Marks a task at specific index as done.

Example usage:

`done <Number>` - Number should be a valid number shown on the list.

Example:

`done 2` - Marks the task at index 2 as done.

`dn 2` - Produces the same outcome as the command above.

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] Submit assignment (by: Aug 21 2021 11:59PM)
```

### `dlt` or `delete` - Deletes a task at specific index from tasks list.

Example usage:

`delete <Number>` - Number should be a valid number shown on the list.

Example:

`delete 2` - Deletes the task at index 2.

`dlt 2` - Produces the same outcome as above command.

Expected outcome:

```
Noted. I've removed this task:
[D][X] Submit assignment (by: Aug 21 2021 11:59PM)
Now you have 2 tasks in the list.
```

Using `list` again gives the following outcome:

```
Your current tasks are:
1. [T][ ] Go jogging
2. [E][ ] John's birthday (at: Aug 21 2021 8:00AM)
```

### `sort` - Sorts the task list chronologically

Example usage:

`sort` - Sorts all tasks in the list of tasks chronologically with those without date and time at the end sorted lexicographically.

Expected output:

Notice the event is brought forward.

```
Your current tasks are:
1. [E][ ] John's birthday (at: Aug 21 2021 8:00AM)
2. [T][ ] Go jogging
```

### `bb` or `bye` - Exits the program.

This shuts down the application.
