# User Guide

## Features 

### Add tasks

Three different types of tasks can be added: Todo, Deadline, Event.
* Todo: contains task description
* Deadline: contains task description and date of deadline
* Event: contains task description and date of event

### Search for tasks based on keywords or date
* By date: search for tasks that are associated with a specific date
* By keywords: search for tasks with keywords in the description

### Track tasks
Tasks can be displayed in a list and are saved locally. Tasks can also be marked done.  

### Add tags to task
Extra information for tasks can be added to task through tags. 

## Usage

### `todo` - add todo task
Add todo task containing a description.

Example of usage:
`todo read book`

Expected outcome:
```
Alright! New task added:
[T][X] read book
There is currently 1 task in your list
```
### `deadline` - add deadline task
Add deadline task containing a description and date in YYYY-MM-DD format.

Example of usage:
`deadline return book /by 2021-09-01`

Expected outcome:
```
Alright! New task added:
[D][ ] return book (by: Sep 1 2021)
There are currently 2 tasks in your list
```
### `event` - add event task
Add deadline task containing a description and date in YYYY-MM-DD format.

Example of usage:
`event presentation /at 2021-09-01`

Expected outcome:
```
Alright! New task added:
[E][ ] presentation (at: Sep 1 2021)
There are currently 3 tasks in your list
```
### `list` - list tasks
Display the list of tasks with its type, status, description, date and tags.

Example of usage:
`list`

Expected outcome:
```
These are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Sep 1 2021)
3.[E][ ] presentation (at: Sep 1 2021)

There are currently 3 tasks in your list
```
### `done` - mark task as done
Marks a task as done according to the task number in the list.

Example of usage:
`done 3`

Expected outcome:
```
Good work! Task is now marked as done:
[E][X] presentation (at: Sep 1 2021)
```
### `delete` - delete task from list
Deletes task from list according to the task number in the list.

Example of usage:
`delete 1`

Expected outcome:
```
Alright! I've deleted this task:
[T][X] read book
There are currently 2 tasks in your list
```
### `find` - find tasks with query string
Displays list of tasks with description containing the query string.

Example of usage:
`find book`

Expected outcome:
```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: Sep 1 2021)
```
### `on` - find tasks with date
Displays list of tasks with query date in YYYY-MM-DD format.

Example of usage:
`on 2021-09-01`

Expected outcome:
```
The tasks that you have on Sep 1 2021 are:
[D][ ] return book (by: Sep 1 2021)
[E][X] presentation (at: Sep 1 2021)
```
### `tag` - add tags to a task
Add tags to a task according to the task number in the list.
Tags are separated by `/`

Example of usage:
`tag 2 Important/Urgent`

Expected outcome:
```
Got it! Tags are added to task:
[D][ ] return book (by: Sep 1 2021) {Important, Urgent}
```
### `bye` - exit
Displays goodbye message and exits the bot.

Example of usage:
`bye`

Expected outcome:
```
Goodbye! Hope to see you again soon!
```