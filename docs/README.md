
# User Guide

## Gnosis Feature(s)

### Task Manager

Gnosis provides the ability to create, retrieve and delete different variety of tasks
to manage.</br>
Different types of tasks includes: todos, deadlines and events.

## Usage

### `todo` - Creates a todo task

Creates a todo task without any date/time.

Example of usage: 

`todo read book`

Expected outcome:

Displays todo task added.<br/>
[T] - denotes as a Todo task.<br/>
[&nbsp;&nbsp;] - denotes a todo that is not marked done.
```
Todo added:
[T][ ] read book
```
***
### `deadline` - Creates a deadline task

Creates a deadline task with a specified date/time.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

Displays deadline task added.<br/>
[D] - denotes as a deadline task.<br/>
[&nbsp;&nbsp;] - denotes a deadline that is not marked done.
```
Deadline added:
[D][ ] return book  (by:  Sunday)
```
***
### `event` - Creates an event task

Creates an event task with a specified schedule.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

Displays event task added.<br/>
[E] - denotes as an event task.<br/>
[&nbsp;&nbsp;] - denotes an event that is not marked done.
```
Event added:
[E][ ] project meeting  (at: Mon 2-4pm)
```
***
### `list` - Lists all tasks created

Lists all tasks created.

Example of usage:

`list`

Expected outcome:

Displays all task with task number added.<br/>
[T] - denotes as a todo task.<br/>
[D] - denotes as a deadline task.<br/>
[E] - denotes as an event task.<br/>
[&nbsp;&nbsp;] - denotes a task that is not marked done.
```
Listing all tasks in your list:
1. [T][ ] read book
2. [D][ ] return book  (by: Sunday)
3. [E][ ] project meeting  (at: Mon 2-4pm)
```
***
### `done` - Mark specified task done

Marks specified task has done.

Example of usage:

`done 2`

Expected outcome:

Marks specified task done.<br/>
[X] - denotes a task that is marked has done<br/>
```
Task 2 marked as done:
[D][X] return book  (by: Sunday)
```
***
### `delete` - Deletes task from list

Deletes task from task manager list.

Example of usage:

`delete 3`

Expected outcome:

Displays deleted task.<br/>
```
Understood. Task has been deleted:
3. [E][ ] project meeting  (at: Mon 2-4pm)
```
***
### `bye` - Exits Gnosis

Terminates Gnosis program.

Example of usage:

`bye`

Expected outcome:

Displays bye message.
```
Good bye.
I hope your needs have been sparked.
I welcome you back soon.
```
