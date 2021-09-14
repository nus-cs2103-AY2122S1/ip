# User Guide

## Features 

### Keep track of your tasks

Tasks are separated into three categories: todo, event and deadline.
Todo is just a simple task, event is a task that happens during a specific period while deadline is a task that has to be done before a specific timing.

You can create tasks, mark tasks as done, as well as delete tasks.

### Search tasks

Search through your list of tasks to find those whose description matches your query.

## Usage

### `list` - List all tasks

List all tasks in alphabetical order.

Sample usage:

`list`

Sample outcome:
```
1.[E][ ] celebrate (at: Sep 18 2021)
2.[T][X] demo duke
3.[D][ ] submit duke (by: Sep 17 2021)
```

These are tasks in alphabetical order. E, T and D represents event, task and deadline respectively. The presence of and 'X' indicates that a task is done.

### `done` - Mark task as done

Use this command and provide task number to mark a task as done.

Sample usage:

`done 2`

Sample outcome:
```
Nice! I've marked this task as done:
[T][X] demo duke
```

This displays the task that is successfully marked as done.

### `delete` - Delete a task

Use this command and provide task number to delete a task.

Sample usage:

`delete 3`

Sample outcome:
```
Noted. I've removed this task:
[T][X] demo duke
Now you have 3 tasks in the list.
```

This displays the task that is successfully deleted, then informs you of the number of tasks left.

### `todo` - Create a Todo Task

Use this command and provide task description to create a todo.

Sample usage:

`todo make cake`

Sample outcome:
```
added: [T][ ] make cake
```

Shows the todo that you created.

### `event` - Create an Event Task

Use this command and provide task description and timing to create an event.
Use /at to specify when the task description ends and the timing begins.

Sample usage:

`event attend funeral /at Sep 11 2001 to Sep 12 2001`

Sample outcome:
```
added: [E][ ] attend funeral (at: Sep 11 2001 to Sep 12 2001)
```

Shows the event that you created. The timing is shown in the (at: ) portion.

### `deadline` - Create a Deadline Task

Use this command and provide task description and timing to create an event.
Use /at to specify when the task description ends and the timing begins.

The format of the date must be in this format: YYYY-MM-DD.

Sample usage:

`deadline water plant /by 1987-01-29`

Sample outcome:
```
added: [D][ ] water plant (by: 29 Jan 1987)
```

Shows the deadline task that you created. The deadline is shown in the (by: ) portion.
The deadline is also formatted.

### `find` - Find matching tasks

Find tasks which contain your query as a substring. Your query will be everything after the command.

Sample usage:

`find celeb`

Sample outcome:
```
Here are the matching tasks in your list:
1.[E][ ] celebrate (at: Sep 18 2021)
```

These are the matching tasks in alphabetical order. E, T and D represents event, task and deadline respectively. The presence of and 'X' indicates that a task is done.

### `bye` - Say farewell

Wave goodbye to duke before you close the program as a nice gesture.

Sample usage:

`bye`

Sample outcome:
```
Bye. Hope to see you again soon!
```

Duke reluctantly bidding you farewell.