# User Guide

## Features

#### Add To-do: `todo`

Adds a todo task to the task list.

Format: `todo <TODO_DESCRIPTION>`

Examples:
- `todo eat breakfast`


#### Add Event: `event`

Adds an event task to the task list.

Format: `event <EVENT_DESCRIPTION> /at <EVENT_DATE>`
- The event date must be given in the format `YYYY-MM-DD`

Examples:
- `event attend concert /at 2021-09-09`


#### Add Deadline: `deadline`

Adds a deadline task to the task list.

Format: `deadline<DEADLINE_DESCRIPTION> /by <DEADLINE_DATE>`
- The deadline date must be given in the format `YYYY-MM-DD`

Examples:
- `deadline finish assm /by 2021-09-09`


#### List Tasks: `list`

Shows a list of all the tasks on the task list.

Format: `list`

#### Delete task: `delete`

Deletes specified task from the task list.

Format: `delete <TASK_INDEX>`
- Deletes the task at the specified `<TASK_INDEX>`.
- The index refers to the index number shown in the displayed task list.

Examples:
- `delete 5` deletes the 5th task on the task list.

#### Mark task as done: `done`

Marks the specified task as done

Format: `done <TASK_INDEX>`
- Marks the task at the specified `<TASK_INDEX>` as done.
- The index refers to the index number shown in the displayed task list.

Examples:
- `done 5` marks the 5th task on the task list as done.

#### Finds task given keyword: `find`

Returns a list of matching tasks given keyword

Format: `find <SEARCH_KEYWORD>`

Examples:
- `find book`
- `find fish `

#### Exiting the program: `bye`

Exits the program.

Format: `bye`
