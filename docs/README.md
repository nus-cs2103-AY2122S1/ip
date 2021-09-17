# User Guide

## Features 

### Add tasks to Duke

- `event` represents an event that occurs on a user-specified date.
- `deadline` represents a task with a deadline on a user-specified date.
- `todo` represents a generic task that the user plans to do.
- Checks if the task is already present in the task list to prevent duplicate
tasks from being added.
  
### Update list of tasks

- `done <index>` marks the task at `index` as done.
- `delete <index>` deletes the task at `index`.

### Display and search task list

- `list` shows the user the list of task currently in Duke, in
the order the user added them in.
- `find` finds all tasks that contains all specified keywords.

### Saves task list to disk whenever there are changes to any tasks

- Data will be saved to `./data/duke.txt`.
  
### Closing Duke

- `bye` closes Duke.
- The user can also click the X icon on the top-right corner to close Duke,
similar to any other windowed program.

## Usage

### `list` - Display task list

Displays the list of tasks the user has entered into Duke in the order
they were added in.

Example of usage:
`list`

Expected outcome:
```
1. [D][ ] return book (by: Jun 6 2021, 6:00PM)
2. [T][X] join sports club
3. [D][ ] do CS3243 tutorial (by: Aug 25 2021, 8:00AM)
```
This shows that the user currently has 2 `deadline` tasks and 1 `todo` task.

### `find` - Finds tasks that contain keywords

Finds all tasks that contain all specified keywords

Format:
`find <keyword1> <keyword2> ... <keyword_n>`

Suppose the current task list is:
```
1. [D][ ] return book (by: Jun 6 2021, 6:00PM)
2. [T][X] join sports club
3. [D][ ] do CS3243 tutorial (by: Aug 25 2021, 8:00AM)
4. [T][X] join chess club
5. [D][ ] Fill up application to join investment soc (by: Aug 25 2021, 8:00AM)
```

Example of usage: `find join club`

Expected outcome:
```
1. [T][X] join sports club
2. [T][ ] join chess club
```
`[D][ ] Fill up ...` was not selected as it does not contain the keyword `club`.

Example of usage: `find join investment club`

Expected outcome:
```
No matching tasks found!
```
No task in the task list contains all 3 keywords, `join`, `investment` and `club`.

### `event` - Adds an Event task into Duke

Adds an `event` task into the current task list.

Format:
`event <description> /at <d/M/YYYY HHMM>`

Example of usage:
`event Recess week /at 17/9/2021 2359`

Expected outcome:
```
Got it. I've added this task:
        [E][ ] Recess week (at: Sep 17 2021, 11:59PM)
Now you have 4 tasks in the list.
```
The `event` has been successfully added, and there are now 4 tasks in the list.

### `deadline` - Adds a Deadline task into Duke

Adds a `deadline` task into the current task list.

Format:
`deadline <description> /by <d/M/YYYY HHMM>`

Example of usage:
`deadline Finalize iP /at 17/9/2021 2359`

Expected outcome:
```
Got it. I've added this task:
        [E][ ] Finalize iP (by: Sep 17 2021, 11:59PM)
Now you have 5 tasks in the list.
```
The `event` has been successfully added, and there are now 5 tasks in the list.

### `todo` - Adds a Todo task into Duke

Adds a `todo` task into the current task list.

Format:
`todo <description>`

Example of usage:
`todo Internship applications`

Expected outcome:
```
Got it. I've added this task:
        [T][ ] Internship applications
Now you have 6 tasks in the list.
```
The `event` has been successfully added, and there are now 6 tasks in the list.

### `done` - Marks selected task as done

Marks selected task as done.

Format:
`done <index>`

Suppose the current task list is:
```
1. [D][ ] return book (by: Jun 6 2021, 6:00PM)
2. [T][X] join sports club
3. [D][X] do CS3243 tutorial (by: Aug 25 2021, 8:00AM)
4. [D][ ] Submit user guide (by: Sep 17 2021, 11:59PM)
5. [T][ ] Internship applications
```
Example input: `done 5`

Expected outcome:
```
Nice! I've marked this task as done:
        [T][X] Internship applications
```

The selected `todo` task has been marked as done.

Example input: `done 2`

Expected outcome:
```
This was completed previously:
    [T][X] join sports club
```

The selected `todo` was previously completed, and there are no changes to the
task list.

Example input: `done 6`

Expected outcome: `OOPS!!! Sorry, this task index is invalid! Please input an integer between 1-5`

### `delete` - Deletes selected task from Duke

Deletes selected task from Duke.

Format:
`delete <index>`

Suppose the current task list is:
```
1. [D][ ] return book (by: Jun 6 2021, 6:00PM)
2. [T][X] join sports club
3. [D][X] do CS3243 tutorial (by: Aug 25 2021, 8:00AM)
4. [D][ ] Submit user guide (by: Sep 17 2021, 11:59PM)
5. [T][ ] Internship applications
```

Example of usage:
`delete 5`

Expected outcome:
```
I have deleted this task:
    [T][X] Internship applications
Now you have 4 tasks in the list.
```
The selected `todo` task has been deleted.

### `bye` - Exits Duke

Exits Duke after 2 seconds.

Format:
`bye`

Expected outcome:
`Bye. Hope to see you again soon!`

## Miscellaneous

### Save data format
- All tasks are saved as a single line within `./data/duke.txt`
- Todo: `T | <1/0> | <description>`
    - 2nd entry is 1 if the task is done, and 0 otherwise.
- Deadline: `D | <1/0> | <description> | <d/M/YYYY HHMM>`
    - 2nd entry is 1 if the task is done, and 0 otherwise.
- Event: `E | <1/0> | <description> | <d/M/YYYY HHMM>`
    - 2nd entry is 1 if the task is done, and 0 otherwise.
    
Sample file:
```
T | 1 | join sports club
D | 1 | do CS3243 tutorial | 25/8/2021 0800
D | 0 | Submit user guide | 17/9/2021 2359
T | 0 | join tkd
T | 0 | join chess club
```