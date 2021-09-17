# User Guide

## Features 

### Add different kinds of events

Adds a todo event.

### List all events

Lists all events in an indexed list

### Mark tasks as done

Given an index, you can mark tasks as done

### Find tasks

By keywords.

### Delete tasks

Because sometimes in life tasks need to be deleted.

![](Ui.png)

_An example of Jaden being used_

## Usage

### `list` - Lists all the tasks

Describe the action and its outcome.

Example of usage: 

`list`

Expected outcome:



```
1. [T][ ] some task
2. [E][ ] some other task
.....
```

### `todo` - Adds a todo task

Example of usage:

`todo something`

Expected outcome:



```
I Understand. I have Added:
    [T][] something
You have {n} tasks.
```

### `deadline` - Adds a deadline task

Example of usage:

`deadline something /by 12/12/2021`

Expected outcome:



```
I Understand. I have Added:
    [D][] something (by: 12 Dec 2021)
You have {n} tasks.
.....
```


### `event` - Adds a event task

Example of usage:

`event something /at 12/12/2021`

Expected outcome:



```
I Understand. I have Added:
    [D][] something (by: 12 Dec 2021)
You have {n} tasks.
.....
```

### `done` - Adds a task identified bt index as done

Example of usage:

`done 1`

Expected outcome:



```
Your task has been marked as done.
    [D][X] something (by: 12 Dec 2021)
You have {n} tasks.
.....
```


### `delete` - Deletes identified task

Example of usage:

`delete 1`

Expected outcome:



```
Your task has been marked as done.
    [D][X] something (by: 12 Dec 2021)
You have {n} tasks.
.....
```

