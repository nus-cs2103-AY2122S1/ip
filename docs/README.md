
# User Guide

This is a project for a greenfield CS2103 Individual Project.
The name Gnosis means knowledge in Greek.
Why I named it Gnosis?
Simply, because my aim in CS2103 (and of course in my other mods) is to expand my knowledge and learning.


```
Welcome, I am Gnosis.
The spark to meet your needs.
How can I assist you ?
```

![](Ui.png)

## Gnosis Feature(s)

### Gnosis

Gnosis provides the ability to create, retrieve and delete different variety of tasks
to manage as well as manage places visited.
</br>

Different types of tasks includes: todos, deadlines and events.

## Commands

### `todo` - Creates a todo

Creates a todo without any date/time.

Example of usage: 

`todo read book`

Expected outcome:

Displays task added.<br/>
[T] - denotes as a Todo<br/>
[&nbsp;&nbsp;] - denotes a todo that is not marked done.
```
Todo added:
[T][ ] read book
```
***
### `deadline` - Creates a deadline

Creates a deadline with a specified date/time.

Example of usage:

`deadline return book /by Sunday`

Expected outcome:

Displays deadline task added.<br/>
[D] - denotes as a deadline.<br/>
[&nbsp;&nbsp;] - denotes a deadline that is not marked done.
```
Deadline added:
[D][ ] return book  (by:  Sunday)
```
***
### `event` - Creates an event

Creates an event with a specified schedule.

Example of usage:

`event project meeting /at Mon 2-4pm`

Expected outcome:

Displays event added.<br/>
[E] - denotes as an event.<br/>
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
[T] - denotes as a todo gnosis.task.<br/>
[D] - denotes as a deadline gnosis.task.<br/>
[E] - denotes as an event gnosis.task.<br/>
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
gnosis.main.model.gnosis.task 2 marked as done:
[D][X] return book  (by: Sunday)
```
***
### `delete` - Deletes task from list

Deletes task from task task list.

Example of usage:

`delete 3`

Expected outcome:

Displays deleted task.<br/>
```
Understood. task has been deleted:
3. [E][ ] project meeting  (at: Mon 2-4pm)
```
***
### `find` - find task from list

find task matching a keyword

Example of usage:

`find cs2103`

Expected outcome:

Displays matching tasks with ``cs2103``.<br/>

```
Listing all tasks with matching keyword: cs2103
1. [E][ ] CS2103 Lecture  (at: Sep 17 2021, 04:00PM)
2. [D][ ] CS2103 Quiz  (by: Sep 13 2021, 23:59PM)
```

***
***
### `place` - list all places visited

displays all places visited

example of usage: 

`place`

Expected outcome:
displays all place visited
```
Listing all places visited:
1. visited SOC at NUS on Sep 13 2021, 02:30PM
2. visited Fine Foods at U-Town on Sep 13 2021, 05:30PM
```

***
***
### `visited` - add place visited

add specific place visited

example of usage:

`visited SOC /at NUS /on Sep 13 2021, 02:30PM`

Expected outcome: displays place visited
```
visited : SOC /at NUS /on Sep 13 2021, 02:30PM
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
***
This project is developed using JDK11 and JavaFX.
