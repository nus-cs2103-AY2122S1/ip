# User Guide

## Features 

### Add tasks to Duke

* `todo` represents a generic task that the user intends to do.
* `event` represents an event that occurs on a certain date or time.
*  `deadline` represents a task that the user has to complete by a deadline.

### Update Tasks within the task list

* `done <index>` marks the task at `index` as complete.
* `delete <index>` deletes the task at `index`.

### Display and search task list
* `list` displays to the user the list of all tasks currently in Duke. They are arranged in the order the user added them.
* `find <keyword>` finds and displays tasks which contain the keyword.

### Exit from Duke
* `bye` exits from Duke.

### Save task list to local storage
* Each time the task list is updated, its contents will be stored in `./data/duke.txt`. The storage file and directory
will be automatically created if they do not exist.

## Usage

### `list` - Display task list

Displays the list of all tasks currently in Duke, arranged in the order the user added them.

Format of usage: `list`<br>
Shortcut: `l`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
```
In this example, Duke contains 2 `todo` tasks and 1 `deadline` task.

### `todo` - Add a `todo` task

Adds a `todo` task to the task list.

Format: `todo <task description>`
Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
```

Example of usage: `todo buy milk`<br>
Shortcut: `t buy milk`

Expected task list after command:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
4. [T][ ] buy milk
```
The `todo` has been successfully appended to task list.


### `event` - Add an `event` task

Adds an `event` task to the task list.

Format: `event <task description> /at <time>`<br>
Dates in `YYYY-MM-DD` format will be translated into `MMM DD YYYY` format.

Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
```

Example of usage: `event David's birthday celebration /at 2022-01-15 6pm`<br>
Shortcut: `e David's birthday celebration /at 2022-01-15 6pm`

Expected task list after command:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
4. [E][ ] David's birthday celebration (at: Jan 15 2022 6pm)
```
The `event` has been successfully appended to task list.

### `deadline` - Add a `deadline` task

Adds a `deadline` task to the task list.

Format: `deadline <task description> /at <time>`<br>
Dates in `YYYY-MM-DD` format will be translated into `MMM DD YYYY` format.

Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
```

Example of usage: `deadline submit MA1101R assignment /by 2020-10-20`

Expected task list after command:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
The `deadline` has been successfully appended to task list.

### `find` - Find tasks that contain the keyword

Find all tasks that contain the keyword

Format: `find <keyword>`

Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
Example of usage: `find assignment`<br>
Shortcut: `f assignment`

Expected outcome:
```
Here are the matching tasks in your list:
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```

### `done` - Mark a task as completed
Format: `done <index>`

Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][ ] mop the floor
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
Example of usage: `done 3`

Expected task list after command:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][X] mop the floor
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
The 3rd task in the list is marked as done.

### `delete` - Removes a task from the list
Format: `delete <index>`

Suppose the current task list is:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [T][X] mop the floor
4. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
Example of usage: `delete 3`

Expected task list after command:
```
1. [T][ ] iron clothes
2. [D][ ] complete CS2100 assignment (by: Sep 15 2021 12pm)
3. [D][ ] submit MA1101R assignment (by: Oct 20 2020)
```
The 3rd task on the list has been deleted.

Example of usage: `delete 6`

Expected outcome: `:( OOPS!!! Your index is out of range`

### `bye` - Exits from Duke

Example of usage: `bye`<br>
Shortcut: `b`

Expected outcome: Duke terminates after the farewell message: `Bye. Hope to see you again soon!`

## Miscellaneous

### Summary of commands
| Command      | Example of Usage |
| ----------- | ----------- |
| `list`      | `list`                                                 |
| `todo`      | `todo buy milk`                                        |
| `event`     | `event David's birthday celebration /at 2022-01-15 6pm`|
| `deadline`  | `deadline submit MA1101R assignment /by 2020-10-20`    |
| `find`      | `find assignment`                                      |
| `done`      | `done 3`                                               |
| `delete`    | `delete 3`                                             |
| `bye`       | `bye`                                                  |
