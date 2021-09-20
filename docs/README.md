# User Guide

## Features

### Manage all your tasks with Dukebro!

Dukebro breaks down all your tasks into 3 types: Todos, Deadlines and Events.
You can add, update, delete, list all your tasks! Dukebro does it all.

You can even use keywords to filter out tasks by keywords if your tasklist gets too long!
But that means you are either very active or procrastinating your work :P

## Usage

### `list`

List out all tasks in your tasklist.

Example of usage:

`list`

Expected outcome:

``` 
Here are the tasks in your list:
    1. [T][ ][H] read book
    2. [D][ ][M] return book (by: Jul 7 2021 1800)
    3. [E][ ][L] project meeting (at: Dec 1 2021 1100)
```
### `todo`

Adds a Todo type task to your tasklist.

Example of usage:

`todo buy bread p=H`

Expected outcome:

``` 
Got it. I've added this task:
   [T][ ][H] buy bread
Now you have 1 tasks in the list.
```

### `deadline`

Adds a Deadline type task to your tasklist.

Example of usage:

`deadline work report /by 2021-05-05 1800 p=M`

Expected outcome:

``` 
Got it. I've added this task:
   [D][ ][M] work report (by: May 5 2021 1800)
Now you have 1 tasks in the list.
```
### `event`

Adds a Event type task to your tasklist.

Example of usage:

`event concert /at 2021-05-03 1200 p=L`

Expected outcome:

``` 
Got it. I've added this task:
   [E][ ][L] concert (at: May 3 2021 1200)
Now you have 1 tasks in the list.
```
### `delete`

Deletes a task from your tasklist.

Example of usage:

`delete 1`

Expected outcome:

``` 
Noted. I've removed this task:
    [T][X][H] buy bread
Now you have 2 tasks in the list.
```

### `done`

Updates the status of a task in your tasklist as completed.

Example of usage:

`done 1`

Expected outcome:

``` 
Done.
```

### `find`

Finds tasks that match the given keyword.

Example of usage:

`find bread`

Expected outcome:

``` 
Here are the matching tasks in your list:
1.[T][ ][H] buy bread
```

### `bye`

Exits the bot.

Example of usage:

`bye`

Expected outcome:

``` 
Bye. Hope to see you again soon!
```