![Picture of the UI](UI.png)

# User Guide

## Features 

### Track your tasks!

Duke can help you to track 3 types of tasks, To dos, Deadlines and Events

## Usage

### `todo` - Adds a Todo to your list 

The `todo` keyword adds a new todo task to your list of tasks 

Example of usage: 

`todo walk dog`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] Walk dog
Now you have 1 task in the list
```

### `deadline` - Adds a task with a deadline to your list

The `deadline` command takes in a name and a deadline, separated by `/by`
and adds a new task with a deadline to your list

Example of usage:

`deadline essay /by 20/09/2021 2359`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] essay (by: 20/09/2021 2359)
Now you have 2 tasks in the list
```

### `event` - Adds an event to your list

The `event` command takes in a name and an event time, separated by `/at` 
and adds a new task with a deadline to your list

Example of usage:

`event meeting /at 19/09/2021 1500`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] meeting (at 19/09/2021 1500)
Now you have 3 tasks in the list
```

### `done` - Marks a task as done

The `done` command takes in the index of the task to mark as done

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [T][X] read book
```

### `delete` - Deletes a task from the list

The `delete` command takes in the index of the task to delete and removes
it from the list

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][X] read book
Now you have 2 tasks in the list
```

### `list` - Lists out all tasks

Lists out all tasks

Example of usage:

`list`

Expected outcome:

```
1. [D][ ] essay (by: 20/09/2021 2359)
2. [E][ ] meeting (at: 19/09/2021 1500)
```

