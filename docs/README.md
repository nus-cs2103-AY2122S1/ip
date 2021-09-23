# User Guide

## Features 

### Adding an Event: `event`

Adds an Event to the list of tasks to complete.

Format: `event TASK_NAME /at DATE_AND_TIME`

Example: `event project meeting /at 2021-09-16 10:00`

Expected outcome:

The event 'project meeting' with the given date and time is added to the list.
```
Okay! Task added:
  [E][ ] project meeting (at: 16 Sep 2021 10:00)
You now have 1 task(s) in the list.
```

### Adding a Deadline: `deadline`

Adds a Deadline to the list of tasks to complete.

Format: `deadline TASK_NAME /by DATE_AND_TIME`

Example: `deadline project submission /by 2021-09-17 23:59`

Expected outcome:

The deadline 'project submission' with the given date and time is added to the list.
```
Okay! Task added:
  [D][ ] project submission (by: 17 Sep 2021 23:59)
You now have 2 task(s) in the list.
```

### Adding a To Do: `todo`

Adds a to-do to the list of tasks to complete.

Format: `todo TASK_NAME`

Example: `todo buy furniture`

Expected outcome:

The to-do 'buy furniture' is added to the list.
```
Okay! Task added:
  [T][ ] buy furniture
You now have 3 task(s) in the list.
```

### Deleting a task: `delete`

Deletes a task from the list of tasks.

Format: `delete INDEX`

Example: `delete 2`

Expected outcome:

The second task in the list is deleted.
```
Okay! I've deleted this task:
  [E][ ] project meeting (at: 16 Sep 2021 10:00)
You now have 2 task(s) in the list.
```

### Marking a task as completed: `done`

Marks a task as completed.

Format: `done INDEX`

Example: `done 2`

Expected outcome:

The second task in the list is marked as completed.
```
Nice! I've marked this task as done:
  [T][✔︎] buy furniture
```

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Finding tasks: `find`

Finds tasks with the matching keyword.

Format: `find KEYWORD`

Example: `find project` looks for tasks with the word 'project' in the task name. Works with partial words provided such as 'proj'

Expected outcome:

The task(s) containing the keyword are displayed.
```
Here are the tasks that match this keyword:
1.[D][ ] project submission (by: 17 Sep 2021 23:59)
```

### Listing all tasks: `list`

Lists all tasks

Format: `list`

Expected outcome:

All task(s) in the list are displayed.
```
Here are the tasks in your list:
1.[D][ ] project submission (by: 17 Sep 2021 23:59)
2.[T][✔︎] buy furniture
```

### Reversing the effect of a previous command: `undo`

Undoes a previous command.

Format: `undo`

Expected outcome:

The effect of the previous command is reverted.
