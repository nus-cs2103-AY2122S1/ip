# User Guide for Duke

## Features 

### Add Tasks

Allows the user to add tasks to the list. Tasks include event, deadline and todo tasks

## Usage

### `todo` - Adds todo task

Adds a todo task to be stored to the list

Example of usage: 

`todo assignment 1`

Expected outcome:

Stores a todo task in the list

```
Got it. I've added this task:
[T][] assignment 1
```

### `event` - Adds event task

Adds an event task to be stored to the list

Example of usage: 

`event family dinner /2021-10-10`

Expected outcome:

Stores an event task and a date into the list

```
Got it. I've added this task:
[E][] family dinner (at: 2021-10-10)
```
### `deadline` - Adds deadline task

Adds a deadline task to be stored to the list

Example of usage: 

`deadline cs2103 tp /2021-09-17`

Expected outcome:

Stores a deadline task and a date into the list

```
Got it. I've added this task:
[D][] cs2103 tp (by: 2021-09-17)
```

### Delete

Allows the user to delete a task from the list

## Usage

### `delete` - Deletes the specified task

Example of usage: 

`delete 2`

Expected outcome:

Deletes task 2 in the list

```
Got it. I've added this task:
[D][] cs2103 tp (by: 2021-09-17)
```

### List

Allows the user to view the entire list

## Usage

### `list` - Displays the entire list

Example of usage: 

`list`

Expected outcome:

Displays the entire list

### Done

Allows the user to mark a task as done

## Usage

### `done` - Marks the task as done

Example of usage: 

`done 2`

Expected outcome:

Marks task 2 in the list as done

```
Got it. I've added this task:
[D][X] cs2103 tp (by: 2021-09-17)
```
### Delete

Allows the user to delete a task from the list

## Usage

### `find` - Finds all tasks with a specified keyword

Example of usage: 

`find book`

Expected outcome:

Displays all tasks with 'book' in their task

### Reschedule

Reschedules the selected task to another date

## Usage

### `reschedule` - Reschedules the task date to another date

Example of usage: 

`reschedule 2 /2021-09-18`

Expected outcome:

Reschedules task 2 to 2021-09-18 as its date
