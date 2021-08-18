# User Guide

## Features 

### Keeps track of a variety of tasks

Users can classify their tasks according to:
    1. General Tasks (`todo`)
    2. Deadlines (`deadline`)
    3. Events (`event`)

Duke will assist the user in storing the above events for his/her
convenience.

### Feature-XYZ

Description of the feature.

## Usage

### `todo` - To add general task

User enters `todo` as a keyword followed by the task that he/she
wishes to add to the list. 

Example of usage: 

`todo eat lunch`

Expected outcome:

```
__________________________________
Got it. I've added this task:
[T][ ] eat lunch
You now have 1 task(s) in the list.
__________________________________
```


### `deadline` - To add a task with a specified due date

User enters `deadline` as a keyword followed by the task that he/she
wishes to add to the list. Thereafter, he/she appends the task with a
`/by <DATE>` argument to indicate the task's due date.

Example of usage: 

`deadline return book /by June 6th`

Expected outcome:

```
__________________________________
Got it. I've added this task:
[D][ ] return book (by: June 6th)
You now have 2 task(s) in the list.
__________________________________
```


### `event` - To add a task with a specified event date

User enters `event` as a keyword followed by the task that he/she
wishes to add to the list. Thereafter, he/she appends the task with a
`/at <DATE>` argument to indicate the task's due date.

Example of usage: 

`event project meeting /at 2pm`

Expected outcome:

```
__________________________________
Got it. I've added this task:
[E][ ] project meeting (at: 2pm)
You now have 3 task(s) in the list.
__________________________________
```


### `list` - To view the complete list of tasks added so far

User types `list` to call up the list of tasks that he/she has
created. This serves as a reference point for the user to mark tasks
as `done` or `delete` them thereafter.

Example of usage:

`list`

Expected outcome:

```
__________________________________
Here are the tasks in your list:
1.[T][ ] eat lunch
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (at: 2pm)
__________________________________
```
