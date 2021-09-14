# User Guide
Welcome to Nee, your pocket task manager!

<p align="center"><img src="Ui.png" width="50%"/></p>

<br>

## Features 

### Help

Show the help message and available commands, by typing `help`.

<br>

### Create different tasks

Nee supports 3 different types of tasks:
- Todos
- Deadlines
- Events

#### 1. Todos

Todos are tasks with a description and completion status.

#### 2. Deadlines

Deadlines are tasks with a description, due date, and completion status.

#### 3. Events

Lastly, events are tasks with a description, time it is at, and completion status.

<br>

### View your tasks

Forgot what you added? Nee tracks them for you. You can even mark them as completed.

<br>

### Prefer CLI to GUI?

`java -jar ip-0.2.jar -mode cli` to switch to CLI mode.

`java -jar ip-0.2.jar -mode gui` or simply `java -jar ip-0.2.jar` to use the GUI instead.


<br>

### Dark mode

Saves your eyes!

<br>

## Usage

### Add todo: `todo`

Adds a todo to the list of tasks.

Command format: 

`todo [description]`

Example of usage:

`todo get coffee`

Expected outcome:

```
Nee added this task:
  [T][ ] get coffee
Nee has 1 task in the list.
```

<br>

### Add deadline: `deadline`

Adds a deadline to the list of tasks.

Command format:

`deadline [description] /by [due_date]`

💡 Ensure `due_date` is `[dd/MM/yy] [HHmm] `

Example of usage:

`deadline do ip /by 20/9/21 2359`

Expected outcome:

```
Nee added this task:
  [D][ ] do ip (by: Sep 20 2021, 11:59 PM)
Nee has 1 task in the list.
```

<br>

### Add event: `event`

Adds an event to the list of tasks.

Command format:

`event [description] /by [time]`

💡 Ensure `time` is `[dd/MM/yy] [HHmm] `

Example of usage:

`event play ctf /at 20/9/21 2359`

Expected outcome:

```
Nee added this task:
  [E][ ] play ctf (at: Sep 20 2021, 11:59 PM)
Nee has 1 task in the list.
```

<br>

### Mark Task as Done: `done`

Marks the task as completed.

Format: `done [index]`

💡 Ensure index is within `{1, 2, ... list_size}`

Example of usage:

`done 2` marks the second task in the list as done.

<br>

### Find Tasks: `find`

Lists all tasks that match the keyword.

Command format:

`find [keyword]`

Example of usage:

`find apple` lists all tasks that contain `apple`.

<br>

### Undo commands: `undo`

Command format:

`undo [number_of_commands]`

Example of usage:

`undo 1` Undos one command.

💡 Ensure there is a previous command to undo.

<br>

### List tasks: `list`

Lists all tasks that have been added.

Sample task list:
```
[T][X] get coffee
[D][ ] do ip (by: Dec 12 2021, 11:59 PM)
[E][ ] play ctf (at: Sep 9 2021, 8:00 PM)
```

<br>

### Exit the program: `bye`

Exits the program and closes the GUI window.

Example of usage:

`bye`

Expected outcome:

GUI closes