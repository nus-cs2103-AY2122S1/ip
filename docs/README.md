# User Guide: DuC - That time I created a Chat Bot named myself because everyone keep mispronounce and butcher it in front of me

Random title inserted

## Menu

1. Feature
   - [Add, update, track completion, and delete tasks](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#add-update-track-completion-and-delete-tasks)
   - [View task and task date](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#view-tasks-and-query-tasks)
   - [Load and save](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#load-and-save)
2. Usage
   - [`help` - Getting help](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#help---getting-help)
   - [`todo` - Add a task of type TODO](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#todo---add-a-task-of-type-todo)
   - [`deadline` - Add a task of type DEADLINE](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#deadline---add-a-task-of-type-deadline)
   - [`event` - Add a task of type EVENT](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#event---add-a-task-of-type-event)
   - [`done` - Mark a task in the list as completed](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#done---mark-a-task-in-the-list-as-completed)
   - [`update` - Change the description of a specified task in the list](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#update---change-the-description-of-a-specified-task-in-the-list)
   - [`delete` - Delete a task from the list](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#delete---delete-a-task-from-the-list)
   - [`find` - Query a task in your list](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#find---query-a-task-in-your-list)
   - [`list` - View all the current tasks you are having](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#list---view-all-the-current-tasks-you-are-having)
   - [`bye` - Exit the program](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#bye---exit-the-program)
3. Command Summary
   - [Summary table](https://github.com/hmanhduc2k/ip/blob/master/docs/README.md#command-summary)

&nbsp;

## Features 

### Add, update, track completion, and delete tasks.

Great news! You can add in three types of task to the list, and let DuC handle everything for you. 
What are the tasks in mention?
1. TODO: Task without deadline or timing, for you to do at your own pace
2. DEADLINE: Task with an upcoming end date (deadline) for you to complete before
3. EVENT: Self-explanatory - a task that occurs on a date for you to join

What can you do with DuC? You can:
- Add a task (there are 3 types of task to add!)
- Update a task description (you can even change the type of task you are updating!)
- Mark a task as completed (if it is done, mark it X rightaway!)
- Or delete a task at will (the list is too long? Delete it and save some spaces!)

&nbsp;

### View tasks and query tasks

View the list of all ongoing task on the list, and the date associated with them. Search for task that match a given query.

&nbsp;

### Load and Save

Want to visit the list later? We save everything for you, just reload the app again and see your previously-saved list

&nbsp;

## Usage

### `help` - Getting help

Don't know how to use DuC? Like how you keep mispronouncing my name? Fear not, enter `help` in the conversation block 
and see the shortened-version of this User Guide. At your Service!

Syntax: `help`

Expected outcome: Shortened user guide to detail all command syntax for DuC

```
Welcome to DuC!
Here is a comprehensive list of command you can use:
...
```

&nbsp;

### `todo` - Add a task of type TODO

Add a task with no deadline, do it at your own time! No pressure involved, skip and skim through it!

Syntax: `todo + { task name } `

Example of usage: 

`todo go to gym at least 1 hour`

Expected outcome: A task of Type TODO is added, with description being the following example statements

```
Nice! I've added the following task to your list:
[T][ ] go to gym at least 1 hour
Now you have 1 task in your list

```

&nbsp;

### `deadline` - Add a task of type DEADLINE

Do you have CS2103T IP deadline this weekend? Add them to the list, with the expected deadline date
you need to complete. 

*Note that the date entered should be in YYYY-MM-DD for the syntax to work*

Syntax: `deadline + { task name } + /by + { date in YYYY-MM-DD }`

Example of usage: 

`deadline CS2103T IP Submission /by 2021-09-17`

Expected outcome: A task with deadline is added, with deadline date associated like following:

```
Nice! I've added the following task to your list:
[D][ ] CS2103T IP Submission (by Sep 17 2021)
Now you have 2 tasks in your list
```

&nbsp;

### `event` - Add a task of type EVENT

Do you have a lecture later at some fixed timing? Simple, add an event to it - with the date of occurrence

*Note that the date entered should be in YYYY-MM-DD for the syntax to work*

Syntax: `event + { task name } + /at + { date in YYYY-MM-DD }`

Example of usage: 

`event NUS Career Fair /at 2021-09-10`

Expected outcome: An event and occurred date is added to the list like following:

```
Nice! I've added the following task to your list:
[E][ ] NUS Career Fair (at Sep 10 2021)
Now you have 3 tasks in your list
```

&nbsp;

### `done` - Mark a task in the list as completed

Mark a specified task in the list as completed. Optionally, mark every task in the list as done.

Syntax: To mark a specific task as done, use `done + { task's index number }`. 
To mark all task as done, use `done all`

Example of usage: 

`done 2`

Expected outcome: The specified task in the list is marked as completed, with an X being checked beside.

```
Okay, I've marked this task as done:
[D][X] CS2103T IP Submission (by Sep 17 2021)
```

&nbsp;

### `update` - Change the description of a specified task in the list

Do you mess up a task list description but you do not want to delete it? Fear not, update it instead!
Update a task at a specified position to change its structure and date. Simply adding the keyword update
and the index number before a task addition syntax and you are done!

Syntax: `update + { task's index number } + { full syntax for either todo, deadline, or event task }`

Example of usage: 

- `update 1 todo run at least 3km`
- `update 2 event CS2103T Lecture /at 2021-09-17`
- `update 3 deadline CS2103T IP submission /by 2021-09-18`

Expected outcome: The task given at the specified index number is changed accordingly to the new task

```
Deal, I have updated the task at position 1 as:
[T][ ] run at least 3km
```

&nbsp;

### `delete` - Delete a task from the list

Don't want to see the task? Delete it! Or delete everything from the list to clear it.

Syntax: To delete a specific task, use `delete + { task's index number }`
To delete all tasks in the list, use `delete all`

Example of usage: 

`delete 3`

Expected outcome: The indicated task is deleted from the list. Optionally, the list is cleared

```
Noted! I've deleted the following task: 
[E][ ] NUS Career Fair (at Sep 10 2021)
```

&nbsp;

### `find` - Query a task in your list

Find a task in your list that matches a given query you enter. To be used in case you have too many things
in your list!

Syntax: `find + { query }`

Example of usage: 

`find CS2103T`

Expected outcome: DuC will show you a filtered tasklist, containing only the tasks that matches your query. Alternatively,
if no task in your list match the query, another message will show up to indicate that search query does not have any result.

```
The following task(s) matches given query 'CS2103T':
[D][X] CS2103T IP Submission (by Sep 17 2021)
```

&nbsp;

### `list` - View all the current tasks you are having

View all current tasks in your list, in its state of completion.

Syntax: `list`

Expected outcome: Display all the tasks you are having at the moment, like the following (assuming that task 3 hasn't been deleted)
```
Here's the list of all tasks:
[T][ ] run at least 3km
[D][X] CS2103T IP Submission (by Sep 17 2021)
[E][ ] NUS Career Fair (at Sep 10 2021)
```

&nbsp;

### `bye` - Exit the program

Exit DuC, stop the chat bot, and save everything into the file.

Syntax: `bye`

Expected outcome: A goodbye message and the app exiting in 3 seconds.

&nbsp;

## Command Summary

Command | Syntax
------------ | -------------
Seeking help | `help`
Add a todo task | `todo + { task name }`
Add a deadline task | `deadline + { task name } + /by + { date in YYYY-MM-DD }`
Add an event task | `event + { task name } + /at + { date in YYYY-MM-DD }`
Mark a task as completed | `done + { task's index number }` or `done all`
Update/Replace a task | `update + { task's index number } + { add task syntax }`
Delete a task from list | `delete + { task's index numebr }` or `delete all`
Find a task with query | `find + { query }`
List down all tasks | `list`
Exit the program | `bye`
