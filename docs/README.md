# User Guide for Butler Pooh
Butler Pooh makes managing tasks a whizz.

## Features 

### What it can do
- Add 3 different types of task: todo, deadline, event
- Mark task as done
- Delete task
- Find task by keyword
- Undo last action
- List all the task
- Clear all the task

### Adding a *Todo* task: `todo`
Adds a *todo* task to the task list.
These are tasks without any date/time attached to it e.g., visit new theme park.

Format: `todo <TASK_DESCRIPTION>`

Example:
`todo borrow book`

Expected outcome:
```
[T][ ] borrow book
```

### Adding a *Deadline* task: `deadline`

Adds a *deadline* task to the task list.
These are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm.

Format: `deadline <TASK_DESCRIPTION> /by <DEADLINE>`
*Note: `<DEADLINE>` should be in `d/m/y hhmm` format e.g.`1/1/2000 1200`*

Example:
`deadline return book /by 17/9/2021 2100`

Expected outcome:
```
[D][ ] return book (by: 17 Sep 2021 9:00 PM)
```

### Adding an *Event* task: `event`

Adds an *event* task to the task list.
These are tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm.

Format: `event <TASK_DESCRIPTION> /at <TIMEFRAME>`

Example:
`event book discussion /at Mon 2-4pm`

Expected outcome:
```
[E][ ] book discussion (at: Mon 2-4pm)
```
### Mark task as done: `done`

Format: `done <TASK_NUMBER>`

Example:
`done 1`

Expected outcome:
<br> from `1. [T][ ] read book` 
to `1. [T][X] read book`

### Remove task from task list: `delete`

Format: `delete <TASK_NUMBER>`

Example:
`delete 1`

### Find task from task list: `find`

Format: `find <KEYWORD>`

Example:
`find book`

### Undo last action: `undo`
Format: `undo`

### List all task: `list`
Format: `list`

### Clear all task: `clear`
Format: `clear`

### Quit the program: `bye`
Format: `bye`