# User Guide

## Features

### Keep track of your tasks

Tasks are separated into three categories: Todo, Event and Deadline.
Todo is for simple tasks.
Event is a task that happens during a specific period.
Deadline is a task that has to be done before a specific date.

You can create tasks, mark tasks as done, as well as delete tasks.
You can also view tasks and search for tasks.

## Usage

### `list` - List all tasks

List all tasks.

Sample usage:

`list`

Sample outcome:
```
1.[T][ ] Buy Groceries 
2.[E][X] Project Meeting (at: 17/09/2021)
3.[D][ ] Project Submission (by: Sep 17 2021)
``` 
T, E and D represents Todo, Event and Deadline respectively.
The '[X]' indicates that a task has been marked as done.

### `done` - Mark task as done

Use this command and provide task number to mark a task as done.

Sample usage:

`done 2`

Sample outcome:
```
Nice! I've marked this task as done:
[E][X] Project Meeting (at: 17/09/2021)
``` 
Shows the task that has been marked as done.

### `delete` - Delete a task

Use this command and provide task number to delete a task.

Sample usage:

`delete 3`

Sample outcome:
```
Noted. I've removed this task:
[D][ ] Project Submission (by: Sep 17 2021)
``` 
Shows the task that has been deleted.

### `todo` - Create a Todo Task

Use this command and provide a description to create a todo.

Sample usage:

`todo Homework`

Sample outcome:
```
Got it. I've added this task:
[T][ ] Homework 
Now you have 3 tasks in the list
``` 
Shows the todo that has been created.

### `event` - Create an Event Task

Use this command and provide a description and date/time to create an event.

Sample usage:

`event Project meeting /at 17 Sept 9pm - 10pm`

Sample outcome:
```
Got it. I've added this task:
[E][ ] Project Meeting (at: 17 Sept 9pm - 10pm) 
Now you have 3 tasks in the list
``` 
Shows the event that has been created.

### `deadline` - Create a Deadline Task

Use this command and provide a description and date to create a deadline.
The format of the date should be in this format: `YYYY-MM-DD`.

Sample usage:

`deadline Duke /by 2021-09-17`

Sample outcome:
```
Got it. I've added this task:
[D][ ] Duke (by: Sep 17 2021) 
Now you have 3 tasks in the list
``` 
Shows the deadline that has been created.

### `find` - Find matching tasks

Use this command and provide a search query to find all tasks with matching description.

Sample usage:

`find Duke`

Sample outcome:
```
Here are the matching tasks in your list:
1.[D][ ] Duke (by: Sep 17 2021) 
``` 
Shows the tasks with description matching your search query.

### `tag` - Find matching tasks

Use this command and tag your tasks.

Sample usage:

`tag 1 important`

Sample outcome:
```
Got it. I've tagged this task:
1.[D][ ] Duke#important(by: Sep 17 2021) 
``` 
Shows the tasks with description matching your search query.

### `bye` - Exit

Exit the program.

Sample usage:

`bye`

Sample outcome:
```
Bye. Hope to see you again soon!
``` 
Shows Duke bidding farewell.