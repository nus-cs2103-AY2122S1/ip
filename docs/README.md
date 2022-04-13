# User Guide

## Features 

* Adding task
  * Adds todo task `todo`. 
  * Adds deadline task`deadine`. 
  * Adds event task `event`.
* List task list `list`.
* Set task as done `done`.
* Deletes task `delete`.
* Finds task with keyword `find`.
* Viewing help `help`.


## Usage

### `Todo` - Add todo task.
Adds todo type task to task list.

**Format:**
`todo {TASK}`

**Example of usage:**
`todo run`

Expected outcome:
```
Roger! I will add this task in:
[T][] run 
Now you have X tasks left in the list
```

### `Deadline` - Add deadline task.
Adds deadline type task to task list.

**Format:**
`deadline {TASK} /by YYYY-MM-DD HHMM`

**Example of usage:**
`deadline homework /by 2020-12-31 2359`

Expected outcome:
```
Roger! I will add this task in:
[D][] homework (by: Dec 31 2020 11:59PM)
Now you have X tasks left in the list
```

### `Event` - Add event task.
Adds event type task to task list.

**Format:**
`event {TASK} /at YYYY-MM-DD HHMM`

**Example of usage:**
`event meeting /at 2020-01-02 1115`

Expected outcome:
```
Roger! I will add this task in:
[E][] meeting (at Jan 02 2020 11:15AM)
Now you have X tasks left in the list
```

### `List` - List task list.
List all the task in the task list.

**Format:**
`list`

Expected outcome:
```
1.[T][X] run
2.[D][] homework (by: Dec 31 2020 11:59PM)
3.[E][] meeting (at: Jan 02 2020 11:15AM)
```

### `done` - Sets task as done.
Sets a specific task of the index as done.

**Format:**
`done {INDEX}`

**Example of usage:**
`done 1`

Expected outcome:
```
Well Done. I'll get it marked:
[T][X] run
```

### `Delete` - Deletes task.
Deletes a specific task of the index from the task list.

**Format:**
`delete {INDEX}`

**Example of usage:**
`delete 1`

Expected outcome:
```
Roger! I will remove this task from the list:
[T][X] run 
Now you have X tasks left in the list
```

### `Find` - Finds task.
Finds task with a specific keyword in the list.

**Format:**
`find {KEYWORD}`

**Example of usage:**
`find book`

Expected outcome:
```
Are these what you were looking for?
1.[T][X] borrow book.
2.[D][] return book (by: Aug 09 2020 11:00AM)
```

### `Help` - View help.
View all available commands and their uses and formats.

**Format:**
`help`

Expected outcome:
```
Available commands: todo, deadline, event, list, done, delete, find
for format of specific command input:
help {command}
```

**Format:**
`help {command}`

**Example of usage:**
`help deadline`

Expected outcome:
```
deadline {task} /by {date in yyyy-mm-dd} {time in hhmm 24hr format}
eg. deadline homework /by 2020-12-31 2359
```