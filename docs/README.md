# User Guide
Duke helps you keep track of your task

## Features 


### Exit program: `bye`

Exits Duke

Format: `bye`

### deadline: `deadline`

Creates a deadline task that has a
description and deadline to complete 
the task by

Format: `deadline [task] /by [yyyy-MM-dd]`

Example: `deadline Assignment /by 2021-09-21`
creates a deadline and adds it into the list

### delete: `delete`

Deletes task at index

Format: `delete [index]`

### done: `done`

Marks task at index as done

Format: `done [done]`

### event: `event`

Creates an event task that has a 
description and a time that the
event will be held

Format: `event [task] /at [yyyy-MM-dd]`

Example: `event Party /at 2021-09-25`
creates an event and adds it into the list

### find: `find`

Find tasks containing a reference string

Format: `find [ref]`

### help: `help`

Displays help for specified keyword

Format: `help [keyword]`

Example: `help find` returns 
`find [keyword] ; 
looks for tasks containing [keyword]`

### list: `list`

Displays all tasks currently on the list and
if the task is done or not and a date if 
applicable

Format: `list`

### todo: `todo`

Creates a todo task that contains a description

Format: `todo [task]`

Example: `todo buy milk`
creates a todo task and adds it into the list

