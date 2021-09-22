# User Guide
Calico is a desktop app for managing tasks through a Graphical User Interface (GUI). 
Let your productivity **grow**, with Calico.


## Features 

### Adding a todo task: `todo`

Adds a todo task to the task list.

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

### Adding an event task: `event`

Adds an event task to the task list.

### Listing all tasks: `list`

Shows a list of all tasks in the list.

### Marking a task as done: `done`

Marks a task in the list as done.

### Deleting a task: `delete`

Removes a task from the task list.

### Locating tasks by name: `find`

Finds all tasks whose names contain any of the given keywords.

### Exiting the program : `exit`

Exits the program.

### Saving the tasks

Tasks are saved in a file on the hard disk automatically after any
command that modifies the task list. There is no need to save manually.

### Loading the tasks

Tasks are loaded from a file on the hard disk automatically after the
program is run. There is no need to load manually.


## Usage

### `todo` - Adds a todo task

Adds a general task that has to be completed to the task list which
can be viewed later.

Format: `todo TASK_NAME`

Example of usage: 

`todo borrow books`

Expected outcome:

Adds todo task successfully to task list and shows number of tasks
in the list.

```
noted! ive added this task:
  [T][ ] borrow books
now you have 1 tasks in your list
```

### `deadline` - Adds a deadline task

Adds a task that has to be completed by a set deadline to the task list.
The task can be viewed later.

Format: `deadline TASK_NAME /by YYYY-MM-DD HHmm`

Example of usage:

`deadline return book /by 2021-12-25 0000`

Expected outcome:

Adds deadline task successfully to task list and shows number of tasks
in the list.

```
noted! ive added this task:
  [D][ ] return book (by: Dec 25 2021 12:00 AM)
now you have 2 tasks in your list
```

### `event` - Adds an event task

Adds a task that will happen at a set time to the task list.
The task can be viewed later.

Format: `event TASK_NAME /by YYYY-MM-DD HHmm`

Example of usage:

`event project meeting /at 2021-12-31 1234`

Expected outcome:

Adds event task successfully to task list and shows number of tasks
in the list.

```
noted! ive added this task:
  [E][ ] project meeting (at: Dec 31 2021 12:34 PM)
now you have 3 tasks in your list
```

### `list` - Shows list of tasks

Shows all tasks as a numbered list.

Format: `list`

Example of usage:

`list`

Expected outcome:

Displays all tasks in the order they were added.

```
1.[T][ ] borrow book
2.[D][X] return book (by: Dec 25 2021 12:00 AM)
3.[E][ ] project meeting (at: Dec 31 2021 12:34 PM)
4.[D][ ] do homework (by: Dec 31 2021 11:59 PM)
```

### `done` - Marks a task as done

Records a task as completed.

Format: `done INDEX`

Example of usage:

`done 2`

Expected outcome:

Successfully marks the second task as done.

```
congrats on completing this task! :
  [D][X] return book (by: Dec 25 2021 12:00 AM)
```

### `delete` - Removes a task

Deletes a task from the list of tasks to be completed.

Format: `delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

Successfully removes the first task from the list and shows number of tasks
in the list.

```
ok, ive deleted this task :
  [T][ ] borrow book
now you have 3 tasks in your list
```

### `find` - Finds a task

Finds all tasks in the task list whose names contain the given keyword.

Format: `find KEYWORD`

Example of usage:

`find o`

Expected outcome:

Returns all tasks that contains "o" in their names and displays them 
in a numbered list. 

```
ive found some matches for your search query:
1.[D][X] return book (by: Dec 25 2021 12:00 AM)
2.[E][ ] project meeting (at: Dec 31 2021 12:34 PM)
3.[D][ ] do homework (by: Dec 31 2021 11:59 PM)
```

### `exit` - Exits the program

Closes the Calico chatbot.

Format: `exit`

Example of usage:

`exit`

Expected outcome:

Calico window closes.


## Command summary
Action 	 | Format, Examples
------------ | -------------
todo | `todo TASK_NAME` e.g., `todo borrow books`
deadline| `deadline TASK_NAME /by YYYY-MM-DD HHmm` e.g., `deadline return book /by 2021-12-25 0000`
event | `event TASK_NAME /by YYYY-MM-DD HHmm` e.g., `event project meeting /at 2021-12-31 1234`
list | `list`
done| `done INDEX` e.g., `done 2`
delete | `delete INDEX` e.g., `delete 1`
find | `find KEYWORD` e.g., `find o`
exit | `exit`
