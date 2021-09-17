# User Guide
With Duke, you can better keep track of your daily errands, to-do list,
school schedules and events!
> "The secret of getting ahead is getting started" - Mark Twain

## Features 

Manage todo, events and deadline tasks. This includes: 
- `add`, `delete` a task
- `find` a task
- `list` all tasks
- mark a task as `done`  
- `undo` a command

## Usage

### 1. Add a task:

### `todo` - Add a todo task to the list.

Syntax: `todo task1`

Example of usage: 

`todo grocery`

Expected outcome:

Task will be added to the list, and the following message will
be printed out.

```
Got it. I've added this task.
[T][] grocery
Now you have 5 tasks in the list.
```

### `deadline` - Add a deadline to the list.
Syntax: `deadline task1 /by date (time)`

*Note:*
- `date` must be in the format of YYYY-MM-DD
- `time` must be in the format of HH:MM
- `time` field is assumed to be 23:59 if not indicated

Example of usage:

`deadline CS2103T ip /by 2021-09-17`

Expected outcome:

Task will be added to the list, and the following message will
be printed out.

```
Got it. I've added this task.
[D][] CS2103T ip (by: Sep 17 2021 23:59)
Now you have 6 tasks in the list.
```

### `event` - Add an event to the list.

Syntax: `event task1 /by date (time1-time2)`

*Note:*
- `date` must be in the format of YYYY-MM-DD
- `time1` and `time2` must be in the format of HH:MM

Example of usage:

`event CCA meeting /at 2021-09-17 15:00-16:00`

Expected outcome:

Task will be added to the list, and the following message will
be printed out.

```
Got it. I've added this task.
[E][] CCA meeting (at: Sep 17 2021 15:00-16:00)
Now you have 7 tasks in the list.
```

### 2. Delete a task:

Delete the task at the indicated index

Syntax: `delete index` 

Example of usage:

`delete 1` 

Expected outcome: 

Task at index 1 on the list will be deleted, and the following message 
will be printed out.
````
Noted. I've removed this task:
[T][] Take dog for a walk
Now you have 6 tasks in the list.
````

### 3. Find a task:

Find tasks that contains one or more specified keywords

Syntax: `find keyword1 (keyword2...)`

Example of usage:

`find CS2103T`

Expected outcome: 

If matching tasks are found, the following message 
will be printed out.

````
Here are the matching tasks in your list:
1. [D][] CS2103T Assignment (by: Sep 17 2021 23:59)
2. [E][] CS2103T Group meeting (at: Sep 20 2021 16:00-18:00)
````

Otherwise, the following message will be printed out:

`Sorry. No such task is found :( Try another key word?`

### 4. List:

List all tasks that are currently on the list

Syntax: `list`

Example of usage:

`list`

Expected outcomes:

The following message will be printed out.
````
Here are the tasks on your list:
1. [T][X] grocery shopping
2. [D][X] CS2100 Assignment (by: Sep 15 2021 23:59)
3. [E][] CCA Exco meeting (at: Sep 21 2021 21:00-23:00)
4. [T][] meal prep for the week
````

### 5. Mark a task as done:

Mark a task at the specifed index as done

Syntax: `done index`

Example of usage:

`done 4`

Expected outcomes:

The at index 3 on the list will be marked as done, 
and the following message will be printed out.

````
Nice! I have marked this task as done!
[T][X] meal prep for the week
````
### 6. Undo a command:

Undo the most current command

Syntax: `undo`

Example of usage:

`delete 1`

Task with index 1 will be deleted, and the 
following message will be printed out:
````
Noted. I've removed this task:
[T][X] grocery shopping
Now you have 3 tasks in the list.
````

`undo`

The command is undone, and the following message will be printed out:
````
Got it. I've added this task.
[T][X] grocery shopping
Now you have 4 tasks in the list.
````


