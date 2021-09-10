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

### Feature Filter

Filter your list and only see tasks with a certain string of
characters within them.

### Feature Sort

Sort deadlines and events based on the time attached to them.

## Usage

### `list` - lists your tasks

Enter the 'list' command to see your tasks
in a list format.

Lists your tasks in the order that they were added.

Example of usage:

`list`

```
expected output
```

### `todo` - add a to-do task

Enter the 'todo' command to add a task to-do to your list.

Example of usage:

`todo some task`

Expected outcome:

The todo is added to your list.

```
expected output
```

### `deadline` - add a deadline task

Enter the 'deadline' command to add a task with a time deadline to your list.

Example of usage:

`deadline some task /by YYYY-MM-DD`
`deadline some other task /by YYYY-MM-DD HH:MM`

Expected outcome:

If time is not specified, the task is set to a default time of
0000 hours.

The deadline is added to your list.

```
expected output
```

### `event` - add an event task

Enter the 'event' command to add an event with a time to your list.

Example of usage:

`event some event /at YYYY-MM-DD`
`event some other event /at YYYY-MM-DD HH:MM`

Expected outcome:

If time is not specified, the task is set to a default time of
0000 hours.

The event is added to your list.

```
expected output
```

### `done` - marks a task as done

Enter the 'done' command to mark a task as done.

Example of usage:

`done <index in list>`

Expected outcome:

Marks a task as done in the list.

```
expected output
```

### `delete` - delete a task

Enter the 'delete' command to delete a task.

Example of usage:

`delete <index in list>`

Expected outcome:

Deletes task from list.

```
expected output
```

### `filter` - filters tasks

Enter the 'filter' command to filter tasks.

Example of usage:

`filter <regex>`

Expected outcome:

Shows a list with tasks containing the regex.

```
expected output
```

### `sort` - filters tasks

Enter the 'sort' command to sort events and deadlines.

Example of usage:

`sort deadlines and events`
`sort events and deadlines`

Expected outcome:

Shows a list that contains the sorted sub-list of
the events and deadlines in the list based on time.

```
expected output
```

### `bye` - Terminates chat session

Enter the 'bye' command to close the bot.

Example of usage:

`bye`

Expected outcome:

Kills the chatbot.

```
expected output
```
