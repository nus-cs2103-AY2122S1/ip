# Duke User Guide

## Features 

### Adding and viewing Tasks

Duke allows the user to create their own tasks, and keeps track of them in the task list. Users will be able to view and
add on to the list.

### Setting deadlines and dates for tasks

Duke is able to create Deadline and Event tasks, which are tasks associated with a date.

### Completing tasks

Users are able to mark tasks as complete, to check what they shoulddo next.

### Editing and deleting tasks

Duke enables users to delete and updates their task list with ease.

## Usage

### `todo` - Add a todo task

Creates a todo, which is a task to be completed.

Example of usage: 

`todo homework`

Expected outcome:

Duke records the todo in the user's task list, and shows the number of tasks currently in the list.

```
Got it. I've added this task:
  [T][ ] homework
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Creates a deadline, which is a task to be completed by a specified date.

Example of usage:

`deadline assignment /by 09-13-2021`

Expected outcome:

Duke records the deadline in the user's task list, and shows the number of tasks currently in the list.

```
Got it. I've added this task:
  [D][ ] assignment (by: 09-13-2021)
Now you have 2 tasks in the list.
```

### `event` - Add an event task

Creates an event, which is a task that occurs a specified date.

Example of usage:

`event exam /at 09-13-2021`

Expected outcome:

Duke records the event in the user's task list, and shows the number of tasks currently in the list.

```
Got it. I've added this task:
  [E][ ] exam (at: 09-13-2021)
Now you have 3 tasks in the list.
```

### `list` - view the task list

Lists all the tasks in the user's task list.

Example of usage:

`list`

Expected outcome:

Duke displays the user's tasks.

```
Here are your tasks:
1. [T][ ] homework
2. [D][ ] assignment (by: 09-13-2021)
3. [E][ ] exam (at: 09-13-2021)
```

### `done` - Mark as done

Mark a specified task as done.

Example of usage:

`done 2`

Expected outcome:

Duke marks the task at the given index as complete.

```
Nice! I've marked this task as done:
2. [D][X] assignment (by: 09-13-2021)
```

### `delete` - Delete a task

Delete a specified task.

Example of usage:

`delete 3`

Expected outcome:

Duke deletes the task at the given index, and shows the number of remaining tasks.

```
Noted. I've removed this task:
3. [E][ ] exam (at: 09-13-2021)
Now you have 2 tasks in the list.
```

### `update` - Update an existing task

Updates an existing task in the task list.

Example of usage:

`update 1 todo another thing`

Expected outcome:

Duke updates the task at the specified index.

```
Nice! I've updated this task:
1. [T][ ] todo another thing
```

### `bye` - Say goodbye to Duke!

Exits Duke.

Example of usage:

`bye`

Expected outcome:

Duke bids farewell and the program closes.

```
Bye. Hope to see you again soon!
```