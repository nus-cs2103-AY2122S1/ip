# User Guide for Duke

## Features 

### Adding tasks

Duke allows you to add 3 types of tasks:
- ToDo
- Deadline
- Event

### Saving tasks

Duke saves your tasks automatically when you exit the program.

### Deleting tasks

Duke lets you delete any task from your task list.

### Finishing tasks

Duke lets you mark any task on your task list as complete, without deleting them.

### Undoing previous command

Duke lets you undo your last undo-able command, say goodbye to accidentally deleting a task! :grin:

### List tasks on your task list

Duke lets you see everything on your task list at a glance.

## Usage

### `todo` - Adds a todo task

Example of usage: 

`todo nap`

Expected outcome:

The todo task 'nap' will be added to your task list.

```
New todo task added:
[T][ ] nap
You now have x item(s) in your task list.
```

### `deadline` - Adds a deadline task

Example of usage:

`ip due /by 2021-09-20`

Expected outcome:

The deadline task 'ip due' will be added to your task list.

```
New deadline task added:
[D][ ] ip due (by: 2021 09 20)
You now have x item(s) in your task list.
```

### `event` - Adds an event task

Example of usage:

`event family meal /at 2021-09-21`

Expected outcome:

The event task 'family meal' will be added to your task list.

```
New event task added:
[E][ ] family meal (by: 2021 09 21)
You now have x item(s) in your task list.
```

### `bye` - Saves tasks and exits the program

Example of usage:

`bye`

Expected outcome:

Description of the outcome.

```
expected output
```

### `delete` - Deletes a task from your task list

Deletes the task at the indicated index.

Example of usage:

`delete 3`

Expected outcome:

Description of the outcome.

```
1.[T][ ] nap
2.[D][ ] ip submit (by: 2021 09 20)
```

### `done` - Marks a task as done

Marks the task at the indicated index as done with an 'X'.

Example of usage:

`done 1`

Expected outcome:

The task at the indicated index will be marked as done.

```
Congratulations! You have finished this task:
[T][X] nap
```

### `undo` - Undoes the last command

Undoes the last valid undo-able command.

Example of usage:

`undo`

Expected outcome:

The previous command's effect will be reverted.

```
I undid your previous command:
done 1
Here is your current task list:
1.[T][ ] nap
2.[D][ ] ip due (by: 2021 09 20)
3.[E][ ] family meal (at: 2021 09 21)
```

### `list` - Lists all tasks in the task list.

Returns all the tasks in the task list

Example of usage:

`list`

Expected outcome:

A list of all the tasks on your task list.

```
1.[T][ ] nap
2.[D][ ] ip due (by: 2021 09 20)
3.[E][ ] family meal (at: 2021 09 21)
```