# User Guide
Duke is built to help keep track of simple tasks! It functions like a chat bot and
will help you remember the tasks you wish to note down.
## Features 

### Adding various task of different categories

Duke is able to add and update up to 3 types of tasks:
1. *Deadline*
2. *Event*
3. *Todo*

### Display all tasks

Duke is able to remember all tasks you have given to him and can show you all tasks should you wish to view everything at one place.


## Usage

###Adds a deadline - `deadline <description> /by <date>`

This tells Duke to add a Task under the Deadline category with the appropriate description and date.

Example of usage: 

`deadline homework /by 2029-09-09 2100`

Expected outcome:

```
Got it. I've added this task:
[D][] homework (by: Sep 09 2029 9:00PM)
Now you have 1 tasks in the list
```
###Adds an event -  `event <description> /at <date>`

This tells Duke to add a Task under the Event category with the appropriate description and date.

Example of usage:

`event party /at 2029-09-09 2100`

Expected outcome:
```
Got it. I've added this task:
[E][] party (by: Sep 09 2029 9:00PM)
Now you have 2 tasks in the list
```
###Adds a todo - `todo <description>`

This tells Duke to add a Task under the Todo category with the appropriate description and date.

Example of usage:

`todo chores`

Expected outcome:

```
Got it. I've added this task:
[T][] chores
Now you have 3 tasks in the list
```
###Displays all Tasks - `list`

This requests all tasks that you have given to Duke.
Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][] chores
```
###Delete a task - `delete <number>`

This tells Duke that you wish to delete the task of that specific number as done.

Example of usage:
`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][] chores
Now you have 2 tasks in the list
```
###Mark a task as done - `done <number>`

This tells Duke that you wish to mark the task of that specific number as done.

Example of usage:
`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] chores
```
###Find based on keyword - `find <keyword>`

This asks Duke to search through all tasks and display all that contain that keyword.

Example of usage:
`find chores`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][X] chores
```
###Stop Duke - `bye`

This bids Duke farewell, and he stops taking any commands.

Example of usage:
`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

