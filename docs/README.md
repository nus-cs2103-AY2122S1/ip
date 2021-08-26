
# User Guide

## Gnosis Feature(s)

### gnosis.main.model.gnosis.task Manager

Gnosis provides the ability to create, retrieve and delete different variety of tasks
to manage.</br>
Different types of tasks includes: todos, deadlines and events.

## Usage

### `todo` - Creates a todo gnosis.task

Creates a todo gnosis.task without any date/time.

Example of usage: 

`todo read book`

Expected outcome:

Displays todo gnosis.task added.<br/>
[T] - denotes as a Todo gnosis.task.<br/>
[&nbsp;&nbsp;] - denotes a todo that is not marked done.
```
Todo added:
[T][ ] read book
```
***
### `deadline` - Creates a deadline gnosis.task

Creates a deadline gnosis.task with a specified date/time.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

Displays deadline gnosis.task added.<br/>
[D] - denotes as a deadline gnosis.task.<br/>
[&nbsp;&nbsp;] - denotes a deadline that is not marked done.
```
Deadline added:
[D][ ] return book  (by:  Sunday)
```
***
### `event` - Creates an event gnosis.task

Creates an event gnosis.task with a specified schedule.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

Displays event gnosis.task added.<br/>
[E] - denotes as an event gnosis.task.<br/>
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

Displays all gnosis.task with gnosis.task number added.<br/>
[T] - denotes as a todo gnosis.task.<br/>
[D] - denotes as a deadline gnosis.task.<br/>
[E] - denotes as an event gnosis.task.<br/>
[&nbsp;&nbsp;] - denotes a gnosis.task that is not marked done.
```
Listing all tasks in your list:
1. [T][ ] read book
2. [D][ ] return book  (by: Sunday)
3. [E][ ] project meeting  (at: Mon 2-4pm)
```
***
### `done` - Mark specified gnosis.task done

Marks specified gnosis.task has done.

Example of usage:

`done 2`

Expected outcome:

Marks specified gnosis.task done.<br/>
[X] - denotes a gnosis.task that is marked has done<br/>
```
gnosis.main.model.gnosis.task 2 marked as done:
[D][X] return book  (by: Sunday)
```
***
### `delete` - Deletes gnosis.task from list

Deletes gnosis.task from gnosis.task gnosis.task list.

Example of usage:

`delete 3`

Expected outcome:

Displays deleted gnosis.task.<br/>
```
Understood. gnosis.main.model.gnosis.task has been deleted:
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
