# Iris User Guide

## What is Iris?

Iris is a task management application which the user can set deadlines for upcoming events. It uses a CLI to process instructions from the user, which can provide a faster and more efficient experience that a typical to-do list application.

![image](https://user-images.githubusercontent.com/72136453/133075154-0f11b6a4-7ae4-4672-bc58-c565d2dd7477.png)

## Features

### Create Task

There are 3 different types of tasks users can create in Iris:
* `ToDo` 
* `Deadline`
* `Event`

`ToDo` tasks contains only the description of the task.

`Deadline` and `Event` tasks contains both the description of the task as well as the time to complete or start executing the tasks respectively.

### Complete Task

You can mark a task as completed `[X]` using the `done` command.

### Delete Task

You can `delete` a task from the list regardless of its completion status.

### List Tasks

You can `list` all your current tasks at hand, view its details and completion status simultaneously.

### Find Tasks

You can `find` tasks containing similar descriptions.

### Undo

You can `undo` the previous command that made any changes to the list of task.

The following commands can be undone:
* `todo`
* `deadline`
* `event`
* `done`
* `delete`

## Usage

### `todo` - Creates a ToDo task

Format - `todo <todo description>`

Example of usage:

`todo bake cookies`

Expected outcome:

```
Got it. I've added this task:
[T][] bake cookies
Now you have 1 task in the list.
```

### `deadline` - Creates a Deadline task

Format - `deadline <deadline description> /by <time in yyyy-MM-dd HH:mm>`

Example of usage:

`deadline CS2103T Quiz 6 /by 2021-09-17 17:00`

Expected outcome:

```
Got it. I've added this task:
[D][] CS2103T Quiz 6 (by: 17-09-2021 17:00)
Now you have 2 task in the list.
```

### `event` - Creates an Event task

Format - `event <event description> /at <time in yyyy-MM-dd HH:mm>`

Example of usage:

`event CS2101 Project Meeting /at 2021-09-18 09:00`

Expected outcome:

```
Got it. I've added this task:
[E][] CS2101 Project Meeting (at: 18-09-2021 09:00)
Now you have 3 task in the list.
```

### `done` - Marks a task as done

Format - `done <task number>`

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] bake cookies
```

### `delete` - Deletes a task

Format - `delete <task number>`

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
[E][] CS2101 Project Meeting (at: 18-09-2021 09:00)
Now you have 2 task in the list.
```

### `list` - Lists out all stored tasks

Format - `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][X] bake cookies
2.[D][] CS2103T Quiz 6 (by: 17-09-2021 17:00) 
```

### `find` - Find similar tasks.

Format - `find <description>`

Example of usage:

`find cookies`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] bake cookies
```

### `undo` - Undo previous command.

Format - `undo`

Example of usage:

`undo`

Expected outcome:

```
Your most reent command has been reverted!!!
Got it. I've added this task:
[E][] CS2101 Project Meeting (at: 18-09-2021 09:00)
Now you have 3 tasks in the list.
```

### `bye` - Close the program.

Format - `bye`

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

## Miscellaneous Commands

`(Random Input)` - Provides a helpful list of commands.

Example of usage:

`hi`

Expected outcome:

```
Here are some of the possible inputs:
1. todo - create a todo task
2. deadline - create a deadline task
3. event - create an event task
4. done - mark an existing task as done
5. delete - deletes an existing task
6. list - list all stored tasks
7. find - find task from list
8. undo - undo previous command
9. bye - close program
```

## Summary of Features and Commands

|Features | Commands|
|-------- | --------|
|1. Create a `todo` task | `todo <description>`|
|2. Create a `deadline` task | `deadline <description> /by <yyyy-MM-dd HH:mm>`|
|3. Create an `event` task | `event <description> /at <yyyy-MM-dd HH:mm>`|
|4. Marks a task as `done` | `done <task number>`|
|5. `delete` a task | `delete <task number>`|
|6. `list` all the tasks | `list`|
|7. `find` related tasks | `find <description>`|
|8. `undo` previous command | `undo`|
|9. Close the program | `bye`|
