# User Guide

## Quick start
1. Ensure that you have Java 11 or above installed in your Computer (type **java --version** in command prompt!)
2. Download the latest release from the repo!
3. Double click the file to launch Neo!
4. Try any command! (e.g. list)

## Features

### Keep track of your Tasks!

Add tasks for Neo!

### 3 types of tasks

Neo supports:
1. **todo**: Tasks that have no deadline.
2. **deadline**: Tasks that needs to be completed by a deadline.
3. **event**: Events that you want to attend, with the event date!

### Display your tasks!

Display all tasks that you have added.

### Marks tasks as done!

Mark tasks that have been done with a simple command.

### Powerful search for tasks!

Search for tasks based on description, and based on whether the task has been done, or the type of task.

### Delete your tasks!

Delete any tasks that have been completed, or not, completely up to you!

## Usage

### `todo` - Adds a todo task

Add a todo task for Neo to keep track of.

Example of usage: 

`todo download Neo`

Expected outcome:

```
Got it. I've added this task:
[T][] download Neo
Now you have 1 task in the list.
```

### `deadline` - Adds a deadline task

Add a deadline task for Neo to keep track of.

Example of usage: 

`deadline download Neo /by Now`

Expected outcome:

```
Got it. I've added this task:
[D][] download Neo (by: Now)
Now you have 1 task in the list.
```

### `event` - Adds an event task

Add an event task for Neo to keep track of.

Example of usage: 

`event download Neo /by 2021-09-10`

Expected outcome:

```
Got it. I've added this task:
[E][] download Neo (by: Sep 10 2021)
Now you have 1 task in the list.
```

### `delete` - Deletes a task

Deletes a task tracked by Neo. 

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][] download Neo
Now you have 0 tasks in the list.
```

### `done` - Marks a task as done

Marks as task as done.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] download Neo
```

### `list` - Lists all tasks

Displays all tasks that you have added.

Example of usage: 

`list`

Expected outcome:

```
Here are your tasks!
1. [T][X] download Neo
```

### `find` - Searches for a task

Searches for a task based on:
1. Description
2. Tasks that have not been done (**-d** flag)
3. By task type (**-t** flag)(todo(t), deadline(d), event(e))

Example of usage: 

`find Neo`
`find -d`
`find -t e`

Expected outcome:

```
Here's what I found!
1. [T][X] download Neo
2. [D][] download Neo (by: Now)
3. [E][] download Neo (at: Sep 10 2021)
Here's what I found!
1. [D][] download Neo (by: Now)
Here's what I found!
1. [E][X] download Neo (by: Sep 10 2021)
```

### `bye` - Closes Neo

Closes Neo and saves tasks data.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Thank you for using Neo! :)
```

## Saving the data

All data are saved to disk upon using the command 'bye'.
