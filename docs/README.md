# User Guide

## Features

### Feature List

View all your tasks-to-do, deadlines, and events.

### Feature Todo

Add a task that you have to do in the future.

### Feature Deadlines

Add deadlines that you have to meet in the future.

### Feature Events

Add events that you have to attend in the future.

### Feature Delete

Delete items from your task list.

### Feature Done

Mark items from your task list as done.

### Feature Find

Filter your list and only see tasks with a certain string of
characters within them.

### Feature Sort

Sort deadlines and events based on the time attached to them.

## Usage

### `list` - List your tasks

Enter the 'list' command to see your tasks
in a list format.

Lists your tasks in the order that they were added.

Example of usage:

`list`

```
Here are the tasks in your list:
1. [T][ ] some task
```

### `todo` - Add a to-do task

Enter the 'todo' command to add a task to-do to your list.

Example of usage:

`todo some task`

Expected outcome:

The todo is added to your list.

```
Got it. I've added this task:
[T][ ] some task
Now you have <N> tasks in the list.
```

### `deadline` - Add a deadline task

Enter the 'deadline' command to add a task with a time deadline to your list.

Example of usage:

`deadline some task /by YYYY-MM-DD`
`deadline some other task /by YYYY-MM-DD HH:MM`

Expected outcome:

If time is not specified, the task is set to a default time of
0000 hours.

The deadline is added to your list.

```
Got it. I've added this task:
[D][ ] some task (by: 2022-01-01 08:00)
Now you have 3 tasks in the list.
```

### `event` - Add an event task

Enter the 'event' command to add an event with a time to your list.

Example of usage:

`event some event /at YYYY-MM-DD`
`event some other event /at YYYY-MM-DD HH:MM`

Expected outcome:

If time is not specified, the task is set to a default time of
0000 hours.

The event is added to your list.

```
Got it. I've added this task:
[E][ ] some event (at: 2022-01-01 00:00)
Now you have 2 tasks in the list.
```

### `done` - Mark a task as done

Enter the 'done' command to mark a task as done.

Example of usage:

`done <index in list>`

Expected outcome:

Marks a task as done in the list.

```
Nice! I've marked this task as done:
[T][X] some task
```

### `delete` - Delete a task

Enter the 'delete' command to delete a task.

Example of usage:

`delete <index in list>`

Expected outcome:

Deletes task from list.

```
Noted. I've removed this task:
[T][ ] some task
Now you have 0 tasks in the list.
```

### `find` - Find tasks

Enter the 'find' command to filter tasks.

Example of usage:

`find <regex>`

`find some`

Expected outcome:

Shows a sub-list with only tasks containing the regex.

```
Here are the tasks in your list:
1. [T][X] some task
```

### `sort` - Sort tasks

Enter the 'sort' command to sort events and deadlines.

Example of usage:

`sort deadlines and events`
`sort events and deadlines`

Expected outcome:

Shows a list that contains the sorted sub-list of
the events and deadlines in the list based on time.

```
Here are the tasks in your list:
1. [E][ ] some event (at: 2022-01-01 00:00)
2. [D][ ] some task (by: 2022-01-01 08:00)
```

### `bye` - Terminate chat session

Enter the 'bye' command to close the bot.

Example of usage:

`bye`

Expected outcome:

Kills the chatbot.

```
Bye. Hope to see you again soon!
```

### Screenshot of ChatBot

![alt text](https://github.com/ramapriyan912001/ip/blob/master/docs/Ui.png?raw=true)
