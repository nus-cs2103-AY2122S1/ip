# User Guide

## Features 

### Keeps track of your personal life tasks!

Duke can easily store tasks you have in your life (which includes Todos, Events, and Deadlines)! Tell
Duke to store it for you, and have a peace of mind when going about your day, never to worry about missing
a deadline, or even forgetting a task on your todo lists!

### Checks for duplicated items!

Duke is smart enough to know when you have added the same item in the past. This ensures that you do not
double add a task, and having too much items in your todolist, which clogs up cognitive abilities. Duke will 
notify you to the best of its abilities, functioning as your onw and only lifelong assistant!

## Usage

### `todo` - Creates a todo item

Adds a task that can be completed at any point of time!

Example of usage: 

`todo [DESCRIPTION]`

Expected outcome:

Duke will inform you the `todo` that is added to its permanent memory.

```
Got it. Added the following task
[T][ ] [DESCRIPTION]
```

### `event` - Creates an event

Adds an event that will happen in the future.

Example of usage:

`event [DESCRIPTION] /at [DD/MM/YYYY]`

Expected outcome:

Duke will inform you the `event` that is added to its permanent memory.

```
Got it. Added the following task
[E][ ] [DESCRIPTION] (at: [DD/MM/YYYY])
```

### `deadline` - Creates a deadline

Adds deadline that needs to be completed by a certain time.

Example of usage:

`deadline [DESCRIPTION] /by [DD/MM/YYYY]`

Expected outcome:

Duke will inform you the `deadline` that is added to its permanent memory.

```
Got it. Added the following task
[D][ ] [DESCRIPTION] (by: [DD/MM/YYYY])
```

### `done` - Marks a task as done

Marks a task as done when it is completed.

Example of usage:

`done [INDEX]`

Expected outcome:

Duke will tell you that the task has been marked as done.

```
Nice! I've marked this task as done:
[T][X] [DESCRIPTION]
```

### `find` - Finds any task that were stored

Finds tasks that contains the search term

Example of usage:
`find [DESCRIPTION]`

Expected outcome:

Duke will show you a list of tasks that has the search term
```
Here are the tasks in the list:
...[ALL TASKS WITH DESCRIPTION]
```

### `list` - Shows all your tasks

Shows all your tasks

Example of usage:
`list`

Expected outcome:
```
Here are the tasks in the list:
...[ALL TASKS WITH DESCRIPTION]
```

### `bye` - Terminates Duke

When you are done using Duke, simple say `bye`, and he will terminate.

Example of usage:
`bye`

Expected outcome:

Duke window will exit.