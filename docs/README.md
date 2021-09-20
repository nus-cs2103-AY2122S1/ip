# User Guide
Hello this is Duke!

It will help you keep track of your tasks, and save them on your device

## Features 

### Feature - Managing Tasks

Three kind of tasks are currently handled by the Duke:

1. ToDo - A ToDo is a simple task that does not require any particular date. It is
a way for the user to add reminder tasks or daily goals.

2. Event - An Event is a task the happens on a date, they can be ones where the 
user needs to attend a meeting or even a presentation.


3. Deadline - A deadline is a task that needs to be done before a data. These can
be assignments or projects.


## Usage

### `bye` - Quits duke

Quits the duke and the tasks are stored in the file.

Example of usage: 

`bye`

Expected outcome:

Displays the message telling the user to quit the app.

```
Bye. Hope to see you again soon!
```

### `delete` - Delete a task 

Deletes a task in the list.

Example of usage:

`delete 3`

Expected outcome:

Deletes the 3rd task in the list and displays the following message.

```
Noted. I've removed this task:
[T][] do the quiz
Now you have 4 tasks in the list.
```

### `done` - Marks a task done

Changes the status of a task to done.

Example of usage:

`done 5`

Expected outcome:

The 5th task in the list is marked as done.

```
Nice! I've marked this task as done:
[D][X] CS3243 project (by: Sep 19 2021)
```

### `deadline` - Add a Deadline task

Adds a deadline task to the list. The date entered must be in YYYY-MM-DD format.

Example of usage:

`deadline CS2103 iP /by 2021-10-25`

Expected outcome:

Adds the deadline to the duke list with the given time
and displays a message upon successful addition.

```
Got it. I've added this task:
[D][] CS2103 iP (by: Oct 25 2021)
Now you have 5 tasks in the list.
```

### `event` - Add an Event task

Adds an event task to the list. The date entered must be in YYYY-MM-DD format.

Example of usage:

`event guitar lesson /at 2019-10-09`

Expected outcome:

Adds the event to the duke list with the given time 
and displays a message upon successful addition.

```
Got it. I've added this task:
[E][] guitar lesson (at: Oct 09 2019)
Now you have 6 tasks in the list.
```

### `find` - Find a task containing the given keyword

Displays all the tasks that contain the keyword entered by the user.

Example of usage:

`find new`

Expected outcome:

Will display tasks which contain the keyword new in their description.

```
Here are the matching tasks in the list:
1. [D][X] new description (by: Sep 09 2019)
```

### `list` - Display all the tasks

Displays all the tasks that are added by the user or were stored previously.

Example of usage:

`list`

Expected outcome:

All the tasks in the list are displayed to the user along with the type of tasks,
completion status and date where applicable.

```
1. [D][X] new description (by: Sep 09 2019)
2. [T][] dance tonight
3. [E][] new event (at: Sep 09 2019)
```

### `todo` - Add a ToDo task

Adds a todo task to the list. The date entered must be in YYYY-MM-DD format.

Example of usage:

`todo dance tonight`

Expected outcome:

Adds the task to the duke list and displays a message upon successful addition.

```
Got it. I've added this task:
[T][] dance tonight
Now you have 7 tasks in the list.
```

### `update` - Update the task

Update the tasks already created on the duke, you can update either the description,
the time or both. The date entered must be in YYYY-MM-DD format.

Example of usage:

`update 1 d/new description t/2019-09-09`

Expected outcome:

The first task is updated with the description now being "new description"
and date entered is updated as well.

```
Noted. I've updated the task:
[D][X] new description (by: Sep 09 2019)
```
