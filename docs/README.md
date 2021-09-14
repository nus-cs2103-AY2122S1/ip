# User Guide for Duke

> It can't get any easier than Duke. - Everyone

## Features

1. show the current task list
2. add todo
3. add deadline
4. add event
5. mark task as done 
6. delete task
7. find task
8. remind

### Show the current task list

Shows a list of all the tasks added by the user.

### Add todo

Adds a todo task into the list.

### Add deadline

Adds a deadline task with a due date into the list.

### Add event

Adds an event task with a date into the list.

### Mark task as done

Marks a task in the list as done.

### Delete task

Deletes a task from the list.

### Find task

Finds and displays a list of tasks containing the specified keyword.

### Remind

Finds and displays a list of tasks within the specified period.

## Usage

### `list` - Shows the current task list

Shows a list of all the tasks added by the user.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][X] borrow book
2.[T][ ] read book
3.[D][ ] return book (by: 21 Sep 2021)
4.[E][ ] team meeting (at: 17 Sep 2021, 21:00)
```

### `todo {taskName}` - Add todo

Adds a todo task into the list.

Example of usage:

`todo read news`

Expected outcome:

```
Got it. I've added this task:
[T][ ] read news
Now you have 5 tasks in the list.
```

### `deadline {taskName} /by {dueDate (yyyy-mm-dd)} {OPTIONAL time (hh:mm)}` - Add deadline

Adds a deadline task with a dueDate and optional time into the list.

Example of usage:

`deadline assignment /by 2021-09-30 23:59`

Expected outcome:

```
Got it. I've added this task:
[D][ ] assignment (by: 30 Sep 2021, 23:59)
Now you have 6 tasks in the list.
```

### `event {taskName} /at {eventDate (yyyy-mm-dd)} {OPTIONAL time (hh:mm)}` - Add event

Adds an event task with an eventDate and optional time into the list.

Example of usage:

`event play football /at 2021-09-15 16:00`

Expected outcome:

```
Got it. I've added this task:
[E][ ] play football (at: 15 Sep 2021, 16:00)
Now you have 7 tasks in the list.
```

### `done {taskIndex}` - Mark task as done

Marks the task at taskIndex in the list as done.

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### `delete {taskIndex}` - Delete task

Deletes the task at taskIndex from the list.

Example of usage:

`delete 5`

Expected outcome:

```
Noted! I've removed this task:
[T][ ] read news
Now you have 6 tasks in the list.
```

### `find {keyword}` - Find task

Finds and displays a list of tasks containing the specified keyword.

Example of usage:

`find book`

Expected outcome:

```
I have found these tasks in your list matching the keyword:
1.[T][X] borrow book
2.[T][X] read book
3.[D][ ] return book (by: 21 Sep 2021)
```

### `remind {period (today, tomorrow, week, month)}` - Remind

Finds and displays a list of tasks within the specified period.

Example of usage:

`remind week`

Expected outcome:

```
I have found these tasks in your list for this week
1.[T][X] borrow book
2.[T][X] read book
3.[E][ ] team meeting (at: 17 Sep 2021, 21:00)
4.[E][ ] play football (at: 15 Sep 2021, 16:00)
```