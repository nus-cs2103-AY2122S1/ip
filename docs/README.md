# User Guide for Jarvis
This is Jarvis from Stark Industries. 
Mr. Tony Stark has granted you exclusive access to his personal 
artificial intelligence so that you can keep track of your tasks!
Make full use of Jarvis!

<img src="Ui.png" alt="Jarvis GUI">

## Features
1. Add Todos
2. Add Tasks with deadline
3. Add Events
4. Mark tasks as completed/done
5. Find tasks by keyword
6. Delete tasks
7. View a list of your tasks
8. Undo previous commands
9. Support for both CLI and GUI

### ToDo

Todos are tasks with only a description.

### Deadline

Deadlines are tasks with a deadline that is specified by the user.

### Events

Events are tasks with a date, start time and end time.

### Manage tasks and commands easily

You can mark tasks as completed. You can also delete tasks. If you had entered a command wrongly, you can undo it
and try again. You can also find tasks by a keyword for easy reference.

### CLI

For those who prefer a CLI interface, Jarvis supports CLI mode. 
Run the following command to launch the application in CLI mode.

```java -jar jarvis.jar --console```

## Usage

Important points to note:
* Some commands that are followed by <>. It represents user input.
* All dates are in "dd-MM-yyyy" format.
* All times are in "HHmm" format.

### `todo <description>` - Adds a todo

Adds a todo.

Example usage:

`todo complete assignment`

Expected outcome:

A todo will be added to your task list.

```text
Alright! I have added this to the Stark Industries Database:
	[T][ ] complete assignment
Now you have 1 task(s) in the list.
```

### `deadline <description> /by <date> <time>` - Adds a task with a deadline

Adds a task with a deadline.

Example usage:

`deadline complete weekly quiz /by 22-10-2021 2359`

Expected outcome:

A task with a deadline will be added to your task list.

```text
Alright! I have added this to the Stark Industries Database:
	[D][ ] complete weekly quiz (by: Oct 22 2021 2359)
Now you have 2 task(s) in the list.
```

### `event <description> /at <date> <start time> <end time>` - Adds an Event

Adds event with the given start and end time.

Example usage:

`event John's Birthday Party /at 22-10-2021 1600 2100`

Expected outcome:

An event with a start and end time will be added to your task list.

```text
Alright! I have added this to the Stark Industries Database:
	[E][ ] John's Birthday Party (at: Oct 22 2021 4:00 PM to 9:00 PM)
Now you have 3 task(s) in the list.
```

### `find <keyword>` - Finds all tasks that contain the keyword

Find tasks that contain the keyword. They will be displayed in a list format.

Example usage:

`find birthday party`

Expected outcome:

All matching tasks will be displayed for easy reference.

```text
Stark Industries Database contains these matching tasks:
1. [E][ ] John's Birthday Party (at: Oct 22 2021 4:00 PM to 9:00 PM)
```

### `list` - List all your tasks

List will list your tasks in a list format. You can see the task number, the task type and the task completion status.
Any commands after `list` will be ignored.

Example usage:

`list`

Expected outcome:

All tasks will be displayed in a list format.

```text
Here are the tasks in your list:
1. [T][ ] complete assignment
2. [D][ ] complete weekly quiz (by: Oct 22 2021 2359)
3. [E][ ] John's Birthday Party (at: Oct 22 2021 4:00 PM to 9:00 PM)
```

### `done <task number>` - Marks the task as completed/done.

Mark tasks as completed/done. The task number refers to the number the task is associated to in the `list` view.

Example usage:

`done 1`

Expected outcome:

The first task in the list will be marked as completed/done.

```text
Nice! I've marked this task as done:
    [T][X] complete assignment
```

### `delete <task number>` - Delete a task.

Deletes the task with the specified task number.
The task number refers to the number the task is associated to in the `list` view.

Example usage:

`delete 1`

Expected outcome:

The first task in the list will be deleted.

```text
Initiated Delete protocol. Delete confirmed for:
	[T][X] complete assignment
Now you have 2 task(s) in the list.
```

### `help` - Displays the help message.

The help message is a quick way to see all available commands and command format without closing the application.
Any commands after `help` will be ignored.

Example usage:

`help`

Expected outcome:

The help message will be shown.

```text
These are the valid commands in the Stark Industries Database:

1. deadline
usage: add a task with a specific deadline
format: deadline <description> <dd-MM-yyyy HHmm>

2. delete
usage: delete a task using the task number shown in the tasks list
format: delete <task number>

3. done
usage: mark a task as done using the task number shown in the tasks list
format: done <task number>

4. event
usage: add a event with a date, start time and end time
format: event <description> <dd-MM-yyyy HHmm HHmm>

5. bye
usage: quits the application
format: bye

6. find
usage: finds all task that contains a keyword
format: find <keyword>

7. help
usage: shows the help text for the application
format: help

8. list
usage: shows the tasks list
format: list

9. todo
usage: add a todo
format: todo <description>

10. undo
usage: undo the previous commands
format: undo <undo amount>
```

### `undo <number of undos>` - Undos the specified amount of commands.

If you had previously made a mistake in entering a command, undo allows you to revert to a previous state.
Note that only commands that affect the task list will be undoable. Examples of commands that affect the task list,
`todo`, `deadline`, `done`, etc.

Example usage:

`undo 1`

Expected outcome:

Undos the last command.

`undo 2`

Expected outcome:

Undos the last two commands.

```text
Undo protocol complete! 1 command(s) reverted!
```

### `bye` - Closes the application.

Exits and closes the application window.

Example usage:

`bye`

Expected outcome:

Closes the application and exits.
