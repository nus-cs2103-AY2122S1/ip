# Duke User Guide

## Features 

### Keep track of tasks

You can add to-dos, events and deadlines.

### Be on top of your schedule

Easily schedule different types of tasks and view your schedule for individual days.

## Usage

### `list` - List all tasks

Display all tasks in order of entry into Duke.

Example of usage: 

`list`

Expected outcome:

All tasks will be displayed.

```
Here are the tasks in your list:
1.[T][ ] Sell cow milk
2.[T][ ] Drink goat milk
```

### `todo` - Add a todo task

Add a todo task without a deadline.

Syntax: `todo [description]`

Example of usage:

`todo Buy almond milk`

Expected outcome:

The todo will be added to the list.

```
Got it. I've added this task:
[T][ ] Buy almond milk
Now you have 3 tasks in the list.
```

### `event` - Add an event task

Add an event task with a specified date.

Syntax: `event [description] /at YYYY-MM-DD`

Example of usage:

`event Milk Festival /at 2021-12-31`

Expected outcome:

The event will be added to the list.

```
Got it. I've added this task:
[E][ ] Milk Festival
Now you have 4 tasks in the list.
```

### `deadline` - Add a deadline task

Add a deadline task with a specified date.

Syntax: `deadline [description] /by YYYY-MM-DD`

Example of usage:

`deadline Feed milk to my cow /by 2021-11-11`

Expected outcome:

The deadline will be added to the list.

```
Got it. I've added this task:
[D][ ] Feed milk to my cow (by Nov 11 2021)
Now you have 5 tasks in the list.
```

### `done` - Mark a task as completed

Marks a task by indicating its index in the list.

Syntax: `done [task number]`

Example of usage:

`done 5`

Expected outcome:

The 5th task in the list will be marked as done.

```
nice! I've marked this task as done:
[D][X] Feed milk to my cow (by Nov 11 2021)
```

### `delete` - Delete a task

Delete a task by indicating its index in the list.

Syntax: `delete [task number]`

Example of usage:

`delete 5`

Expected outcome:

The 5th task in the list will be deleted.

```
Noted. I've removed this task:
[D][X] Feed milk to my cow (by Nov 11 2021)
Now you have 4 tasks in the list.
```

### `find` - Find tasks using a search query

Find all tasks which contain a search query.

Syntax: `find [search query]`

Example of usage:

`find cow`

Expected outcome:

All tasks containing the word `cow` will be displayed.

```
Here are the matching tasks in your ist:
1.[T][ ] Sell cow milk
```

### `schedule` - Get schedule for a specific day

Display all tasks for a specified date.

Syntax: `schedule YYYY-MM-DD`

Example of usage:

`schedule 2021-12-31`

Expected outcome:

All tasks on the December 12 2021 will be displayed.

```
Here are the tasks schedule for this date:
1.[E][ ] Milk Festival (at: Dec 31 2021)
```

### `bye` - Exit Duke

Display all tasks for a specified date.

Syntax: `bye`

Example of usage:

`bye`

Expected outcome:

Duke application will be closed.