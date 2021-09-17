# User Guide
Duke secretary is a task-managing chatbot that keep your tasks saved and organised in local disk.

Data of the tasks are saved in the `/data` folder in the current directory.

## List of Commands
1. todo
2. deadline
3. event
4. list
5. done 
6. delete
7. find
8. bye

## Features 

### Add tasks 
There are 3 types of task that can be added: Todo, Deadline and Event.
#### Todo
A Todo is task that only has a task name without any date information. 
All trailing and multiple spaces will be replaced with a single space.
An <i>optional</i> field is provided for users to save additional notes for that task.

Command: `todo anyTaskNameYouWouldLike`

Optional flag: `--`

Example: `todo buy groceries --bring cash`

#### Deadline
A Deadline is a task with a task name and a date to complete by. 
All trailing and multiple spaces will be replaced with a single space.
An <i>optional</i> field is provided for users to save additional notes for that task

Command: `deadline anyTaskNameYouWouldLike /by 15/01/2021`

Optional flag: `--`

Example: `deadline submit quiz /by 15/01/2021 --read notes beforehand`

#### Event
A Event is a task with a task name and a date that the event happens.
All trailing and multiple spaces will be replaced with a single space.
An <i>optional</i> field is provided for users to save additional notes for that task

Command: `event anyTaskNameYouWouldLike /at 15/01/2021`

Optional flag: `--`

Example: `event semester quiz /at 15/01/2021 --bring cheatsheet`

### List
Saved task are listed in chronological order when `list` command is used.
1. First field contains the Task Type icon
- T - Todo
- D - Deadline
- E - Event

2. Second field checks if the task is completed.
    
    Completed - `[T][X] Task1`

    Incomplete - `[T][ ] Task1`


3. Third field contains task name and date(if applicable)

Command: `list`

Output: 
```
Here are your tasks:
1.[T][ ] Task1

2.[D][ ] Task2 (by: 01 Jan 2021)
--Additional Notes

3.[E][ ] Task3 (at: 01 Jan 2021)
```

### Done
A task can be marked as complete when using the `done` command, followed by the index.
When a task is marked completed, it cannot be undone.

Command: `done <number>`

Example: `done 2`

Output
```
Nice! I've marked this task as done:
[T][X] Task2
```

### Delete
A task can be deleted by using the `delete` command, followed by the index.
When a task is deleted, it cannot be undone. The number of tasks remaining in the list will be shown at the end.

Command: `delete <number>`

Example: `delete 2`

Output
```
Noted. I've removed this task:
[T][X] Task2

You now have 3 tasks in the list.
```

### Find
A list of task containing the find keyword will be returned when using the `find` command.

Command: `find <taskName>`

Example: `find task1`

Output
```
Here are the matching tasks in your list:
1.[T][ ] task1
```

### Bye
Duke secretary is closed using the `bye` command.

Command: `bye`


