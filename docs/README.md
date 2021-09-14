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
__________________________________________
Got it. I've added this task:
[T][ ] eat lunch
You now have 1 task(s) in the list.
__________________________________________
```


### `deadline` - To add a task with a specified due date

User enters `deadline` as a keyword followed by the task that he/she
wishes to add to the list. Thereafter, he/she appends the task with a
`/by <YYYY-MM-DD>` argument to indicate the task's due date.

Example of usage: 

`deadline return book /by 2021-09-14`

Expected outcome:

```
__________________________________________
Got it. I've added this task:
[D][ ] return book (by: Sep 14 2021)
You now have 2 task(s) in the list.
__________________________________________
```


### `event` - To add a task with a specified event date

User enters `event` as a keyword followed by the task that he/she
wishes to add to the list. Thereafter, he/she appends the task with a
`/at <DATE or TIME>` argument to indicate the task's due date.

Example of usage: 

`event project meeting /at 2pm`

Expected outcome:

```
__________________________________________
Got it. I've added this task:
[E][ ] project meeting (at: 2pm)
You now have 3 task(s) in the list.
__________________________________________
```


### `list` - To view the complete list of tasks added so far

User types `list` to call up the list of tasks that he/she has
created. This serves as a reference point for the user to mark tasks
as `done` or `delete` them thereafter.

Example of usage:

`list`

Expected outcome:

```
__________________________________________
Here are the tasks in your list:
1.[T][ ] eat lunch
2.[D][ ] return book (by: Sep 14 2021)
3.[E][ ] project meeting (at: 2pm)
__________________________________________
```

### `done` - To mark a task in the list as complete

User enters `done` followed by the index of the task that 
he/she plans to mark as complete.


Example of usage:

`done 1`

Expected outcome:

```
__________________________________________
Nice! I've marked this task as done:
[T][X] eat lunch
__________________________________________
```

### `delete` - To remove a task from the list

User enters `delete` followed by the index of the task that
he/she plans to remove from the list.


Example of usage:

`delete 1`

Expected outcome:

```
__________________________________________
Noted. I've removed this task:
[T][X] eat lunch
You now have 2 task(s) in your list!
__________________________________________
```

### `find` - To  mark a task in the list as complete

User enters `find` followed by the keyword of the task that
he/she plans to locate.


Example of usage:

`find book`

Expected outcome:

```
__________________________________________
Here are the tasks that fit your criteria:
[D][ ] return book (by: Sep 14 2021)
__________________________________________
```