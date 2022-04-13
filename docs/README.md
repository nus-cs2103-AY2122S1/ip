# User Guide (Duke)

![image](https://user-images.githubusercontent.com/61271749/132840301-295ef397-5d68-4e78-b506-c189e0802288.png)

## Features 

### Add and Delete Tasks

User can add and delete to their list of tasks.

### Various types of Tasks

Tracks generic tasks (todos) and tasks with a given date (events and deadlines)

### Mark Tasks as Done

User can mark tasks in the task list as done.

### List and Find Tasks

User can find tasks that contains a given keyword or get a list of all the tasks in the task list.

### Local Storage

Tasks are saved locally. Whenever the user starts Duke, the tasks from previous sessions are loaded.

## Usage

### `list` - List out tasks

Format: `list`

Alias: `li`

Lists out all the tasks in your task list.

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] walk dog
2. [E][ ] workshop (at: Dec 20 2021)
```

### `done` - Completing tasks

Format: `done IDX`

Marks a task at specified `IDX` as done/completed.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [T][X] walk dog
```

### `delete` - Deleting tasks

Format: `delete IDX`

Alias: `del IDX`

Deletes a tasks at specified `IDX` from the task list.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted! I've removed this task:
  [T][X] walk dog
Now you have 1 task in your list.
```

### `find` - Finding tasks

Format: `find KEYWORD`

Alias: `f KEYWORD`

Find tasks in task list that contain a specified `KEYWORD`.

Example of usage: 

`find work`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][ ] workshop (at: Dec 20 2021)
```

### `todo` - Add todos

Format: `todo DESCRIPTION`

Alias: `t DESCRIPTION`

Adds a todo to your task list with given `DESCRIPTION`.

Example of usage: 

`todo walk dog`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] walk dog
Now you have 2 tasks in your list.
```

### `event` - Add events

Format: `event DESCRIPTION [/at DATE]`

Alias: `e DESCRIPTION [/at DATE]`

Adds an event to your task list with given `DESCRIPTION` and occurs at a given `DATE`.

Example of usage: 

`event seminar /at 2021-10-10`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] seminar (at: Oct 10 2021)
Now you have 3 tasks in your list.
```

### `deadline` - Add deadlines

Format: `deadline DESCRIPTION [/by DATE]`

Alias: `d DESCRIPTION [/by DATE]`

Adds a deadline to your task list with given `DESCRIPTION` that is due by a given `DATE`

Example of usage: 

`deadline assignment /by 2021-10-10`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] assignment (by: Oct 10 2021)
Now you have 4 tasks in your list.
```

### `bye` - Exits the app

Exits the program.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
