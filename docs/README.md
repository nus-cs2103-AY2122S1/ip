# Duke User Guide
Duke can help you sort out your life! Just give him a chance.

## Interface
![Image of Duke](https://github.com/wilburrito/ip/tree/master/docs/Ui.png)

## Features 

### Add Different Types of Tasks.

Categorise your tasks, from **deadlines, to to-dos and even events**!

### Find tasks

Fret not if you are a busy bee, you can sieve through your tasks with our search function!

## Usage

### `todo` - Creates a new ToDo task.


Example of usage: 

`todo kill rick and morty`

Expected outcome:
A ToDo is added to your list of stuff to do.

```
Got it. I've added this task:
[T][ ] kill rick and morty
Now you have x tasks in your list.
```

### `event` - Creates a new Event Task.


Example of usage:

`event chemistry lesson /at 15-09-2021 12:00`

Expected outcome:

An event is added to your list of stuff to do.

```
Got it. I've added this task:
[E][ ] chemistry lesson (at: MONDAY 12PM)
Now you have x tasks in your list.
```

### `deadline` - Creates a Deadline Task.

Example of usage:

`deadline chemistry homework /by 27-09-2021 12:00`

Expected outcome:

A Deadline is added to your list of stuff to do.

```
Got it. I've added this task:
[D][ ] chemistry homework (by: MONDAY 12PM)
Now you have x tasks in your list.
```

### `find` - Finds a specific task.

Example of usage:

`find morty`

Expected outcome:

A list will be generated with Tasks that have substrings that match your query.

```
Here is what I found:
1. .....
2. .....
3. .....
```
