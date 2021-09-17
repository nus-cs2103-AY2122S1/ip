# Duke User Guide
Duke is a chatbot that helps you keep track of tasks, todos 
and deadlines.

## Features

- Various task types including todos, events and deadlines
- Creating and deleting tasks
- Marking tasks as done
- Viewing task list
- Finding tasks by name

### Different task types 

Duke supports 3 different task types: 
- Todo: A regular task 
- Deadline: A task with a due date
- Event: A task with a specified start time

### Managing tasks

Duke allows you to 
- add tasks
- delete tasks
- mark tasks as done
- list tasks
- search tasks

with commands that are easy to remember.

### Saving tasks

Duke automatically saves tasks to a text file, so your tasks 
are backed up.


## Usage

### `help` - Display help page

Help displays a list of Duke's commands and how to use them.

Example of usage: `help`


### `todo` - Add todo

Adds a todo task with the provided description to your task list. 

Example of usage: `todo <description>`

Sample output:

```
Got it. I've added this task:
[T][ ] Read book 
Now you have 1 task in your list.
```

### `deadline` - Add deadline 

Adds a deadline task with the provided description and
due date to your task list.

Example of usage: `deadline <description> /by <dd/mm/yyyy HHMM>`

Note: The date and time provided must follow the above format, 
where `dd` and `mm` are the date and month represented by 2 digits
and `yyyy` is the year represented as 4 digits. `HHMM` is the time
in 24-hour format.

Sample output:

```
Got it. I've added this task:
[D][ ] Return book (by: May 14 2021 1300)
Now you have 2 tasks in your list.
```

### `event` - Add event

Adds a event task with the provided description and start time
to your task list.

Example of usage: `event <description> /at <dd/mm/yyyy HHMM>`

Note: The date and time provided must follow the above format,
where `dd` and `mm` are the date and month represented by 2 digits
and `yyyy` is the year represented as 4 digits. `HHMM` is the time
in 24-hour format.

Sample output:

```
Got it. I've added this task:
[E][ ] Tutorial (at: Jan 13 2021 1000)
Now you have 3 tasks in your list.
```

### `list` - List all tasks

Displays all tasks you have asked Duke to remember.

Example of usage: `list`

Sample output:

```
Here are all the tasks in your list.
1. [T][ ] Read book 
2. [D][X] Return book (by: May 14 2021 1300)
3. [E][ ] Tutorial (at: Jan 13 2021 1000)
```

### `done` - Mark as done 

Marks a task in your list as done.

Example of usage: `done <task number>`

Note: The task number must be a positive number, and
corresponds to the task number in the task list displayed.

Sample output:

```
Nice! I've marked this task as done:
[D][X] Return book (by: May 14 2021 1300)
```

### `delete` - Delete task

Deletes a task from your task list.

Example of usage: `delete <task number>`

Note: The task number must be a positive number, and
corresponds to the task number in the task list displayed.

Sample output:

```
Noted. I've removed this task: 
[D][X] Return book (by: May 14 2021 1300)
Now you have 3 tasks in your list.
```

### `find` - Find task

Finds a task in your task list by the name provided.

Example of usage: `find <search term>`

Sample output:

```
Here are the tasks in your list:
[T][ ] Read book
[D][X] Return book (by: May 14 2021 1300)
```

### `bye` - Exit program 

The bye command ends the Duke program.

Example of usage: `bye`

Sample output:

```
Bye! Hope to see you again soon!
```
