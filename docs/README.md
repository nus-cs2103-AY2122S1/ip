# User Guide
Duke is a **Personal Assistant Chatbot** that helps you to keep track of various things. You can think of it as a digital task list, where you can add and delete tasks.

## Features 
Listed below are Duke's features:
1. Add a task to the task list
2. Mark a task as done
3. View your entire list of tasks.
4. View your own schedule (list of tasks) for a specific date
5. Delete a task from the task list
6. Find related tasks
7. Auto save and load tasks to your hard drive

### Add Task

Adds a new task to the current list of tasks.
There are currently 3 kinds of tasks available to be added:
1. Todo
2. Event
3. Deadline

### List

Displays all tasks.

### View schedule

Displays the entire list of tasks by hour on a specified date.

### Mark Task as done

Marks a specific task as done.

### Find related Tasks

Find all tasks that contains the keyword that you have entered.

### Delete Task

Removes a specific task from the current task list.

### Exiting the program

Immediately closes the app.

## Usage

### `todo` - Adds a todo Task

Use this command to add a task that you have to do without a hard deadline.
- The description of the todo can contain an arbitrary amount of words (at least 1 word).

Example of usage: 

`todo return book`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [T][] return book
Now you have 1 tasks in the list.
```

### `event` - Adds a event Task

Use this command to add an event that you have to do attend with a specified date and time.
- The description of the event can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy and hhmm (24h format) respectively.

Example of usage: 

`event carnival /at 2/12/2021 0930`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [E][] carnival (at: 2 Dec 2021 09:30 AM)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline Task

Use this command to add a deadline that you have to meet with a specified date and time.
- The description of the deadline can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy and hhmm (24h format) respectively.

Example of usage: 

`deadline CS2100 Tut 1 /at 2/12/2021 1200`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [D][] CS2100 Tut 1 (at: 2 Dec 2021 12:00 PM)
Now you have 3 tasks in the list.
```

### `list`

Example of usage: 

`list`

Expected outcome:

Description of the outcome.

```
Here are the tasks in your list:
1.[T][] return book
2. ...
```

### `schedule`

Use this command if you want to view all your tasks on a specific date by hour.
- Date follows the format dd/mm/yyyy.

Example of usage: 

`schedule 2/12/2021`

Expected outcome:

Description of the outcome.

```
Here are the tasks for 2 Dec 2021:

09:00 AM to 10:00 AM:
[E][] carnival (at: 2 Dec 2021 09:30 AM)

12:00 PM to 01:00 PM:
[D][] CS2100 Tut 1 (by: 2 Dec 2021 12:00 PM)
```

### `done`

You can choose to mark a task as done by specifying the INDEX of the task. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...!!!
- You can check if a task is done by using `list` again. There should be a cross to the left of the task name.

Example of usage: 

`done 2`

Expected outcome:

Description of the outcome.

```
Nice! I've marked this task as done:
  [E][X] carnival (at: 2 Dec 2021 09:30 AM)
```

### `delete`

You can choose to delete a task by specifying the INDEX of the task. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...!!!
- You can check if a task is deleted by using `list` again. The task should be gone.

Example of usage: 

`delete 1`

Expected outcome:

Description of the outcome.

```
Noted! I've removed this task:
  [T][] return book
Now you have 2 tasks in the list.
```

### `find`

Find all tasks that contain your specified keyword (case-insensitive).

Example of usage: 

`find tut`

Expected outcome:

Description of the outcome.

```
Here are the matching tasks in your list:
1.[D][] CS2100 Tut 1 (by: 2 Dec 2021 12:00 PM)
```

### `bye`

Exits the program immediately.

Example of usage:

`bye`
