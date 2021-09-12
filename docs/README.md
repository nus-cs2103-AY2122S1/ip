# User Guide
## Victor
Victor aims to improve your productivity in managing your Tasks.

## Features 

### Feature-ABC

### `list`
List all the tasks

### `todo`
Add a todo task

### `deadline`
Add a deadline task

### `event`
Add an event task


### Feature-XYZ

### `done`
Complete a task

### `delete`
Delete a task

### `find`
Find matching tasks

### `update`
Update a task

### `bye`
Terminate the program

## Usage

### `list` - List all the tasks

Expected outcome:

```
Here are the tasks in your list:
 1.[T][X] read book
 2.[D][ ] return book (by: Aug 25 2021, 8 AM)
 3.[E][ ] project meeting (at: Aug 26 2021, 8 AM)
 4.[T][X] join sports club
```

### `todo` - Add a todo task

`todo borrow book`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] borrow book
Now you have 5 tasks in the list.
```

### `deadline` - Add a deadline task

`deadline gym /by 25/8/2021 0800`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] gym (by: Aug 25 2021, 8 AM)
Now you have 6 tasks in the list.
```

### `event` - Add an event task

`event marathon /at 27/8/2021 0800`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] marathon (at: Aug 27 2021, 8 AM)
Now you have 7 tasks in the list.
```

### `done` - Complete a task

`done 5`

Expected outcome:

```
Nice! I've marked this task as done:
  [X] [T][X] borrow book
```

### `delete` - Delete a task

`done 7`

Expected outcome:

```
Noted. I've removed this task:
  [E][ ] marathon (at: Aug 27 2021, 8 AM)
Now you have 6 tasks in the list.
```

### `find` - Find matching tasks

`find book`

Expected outcome:

```
Here are the tasks in your list:
 1.[T][X] read book
 2.[D][ ] return book (by: Aug 25 2021, 8 AM)
```

### `update` - Update a task

`update 1 -t 27/9/2021 0900`

Expected outcome (Change time with -t flag):

```
Nice! I've modified this task:
[D][] return book (by: Sep 27 2021, 9am)
```

`update 2 -d read book`

Expected outcome (Change description with -d flag):

```
Nice! I've modified this task:
[D][] read book (by: Sep 27 2021, 9am)
```

`update 5 -D`

Expected outcome (Mark incomplete with -D flag):

```
Nice! I've modified this task:
[T][] borrow book
```
