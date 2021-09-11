# Herbert User Guide

Herbert is here to assist you in organizing all your Todos, Events and Deadlines. 
As it is CLI-based, you're guaranteed fast and efficient access as needed.

## Features 

### `list` 

View your current list of tasks

### `todo`

Add a new todo

### `deadline`

Add a new deadline

### `event`

Add a new event

### `done`

Mark a task as complete

### `delete`

Delete a task

### `find`

Find tasks containing a keyword

### `sort`

Sort tasks as needed

### `saving`

Tasks are auto-saved and will be re-initiated when on start

### `bye`

End the chat


## Usage

### `list` - View your current list of tasks

`list`

Expected outcome:

```
1. [E][ ] kth (at: Aug 23 2021)
2. [T][X] do that
3. [D][X] tp meeting (by: Aug 13 2021)
```

### `todo` - Add a new todo

`todo complete ug draft`

 - A description is required.

Expected outcome:

```
Got it! I've added this task:
    [T][ ] complete ug draft
Now you have 10 tasks in the list.
```

### `deadline` - Add a new deadline

`deadline meeting /by 2021-09-14 18:00`

 - A description is required
 - /by separates the description and the date/time
 - A date is required while time is optional

Expected outcome:

```
Got it! I've added this task:
	[D][ ] meeting (by: Sep 14 2021 06:00 PM)
Now you have 11 tasks in the list.
```

### `event` - Add a new event

`event presentation /at 2021-09-21`

 - A description is required
 - /at separates the description and the date/time
 - A date is required while time is optional

Expected outcome:

```
Got it! I've added this task:
	[E][ ] presentation (at: Sep 21 2021)
Now you have 12 tasks in the list.
```

### `done` - Mark a task as complete

`done 12`

 - A valid task integer must be included

Expected outcome:

```
Nice, I've marked this task as done!
	[E][X] presentation (at: Sep 21 2021)
```

### `delete` - Delete a task

`delete 10`

 - A valid task integer must be included

Expected outcome:

```
Noted, I've removed this task:
	[T][ ] complete ug draft
Now you have 11 tasks in the list.
```

### `find` - Find tasks containing a keyword

`find meet`

 - A keyword must be included

Expected outcome:

```
Noted, I've removed this task:
	[T][ ] complete ug draft
Now you have 11 tasks in the list.
```

### `sort` - Sort tasks as needed

`sort -Td`

 - defaults to all tasks in ascending
 - optional flags -T, -E, -D will filter by todos, events or deadlines
 - optional flag -d will sort in descending

Expected outcome:

```
[D][ ] meeting (by: Sep 14 2021 06:00 PM)
```

### `bye` - End the chat

Expected outcome:

```
Sad to see you go :(
...shutting down...
```

