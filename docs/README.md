# User Guide

## Features 

### Add Task

User can add a todo, event and deadline to list of tasks.

### Mark Task As Done

User can mark completed tasks as done.

### Delete Task

User can delete unnecessary tasks.

### Find Task

User can find tasks from list of tasks.

### List Tasks

User can list all tasks.

### Get Help

User can access help for types of commands to use.

## Usage

### `todo` - Adds a todo to list

Create task with no date and time attached to it.

Example of usage: 

`todo math homework`

Expected outcome:

A Todo with description math homework is added to list of tasks.

```
Got it. I've added this task:
  [T][]math homework
 Now you have 1 task in the list.  
```

### `event` - Adds an event to list

Create task with time attached to it.

Example of usage: 

`event project meeting /at Mon 2-4pm`

Expected outcome:

An Event with description project meeting from 2pm to 4pm on Monday is added to list of tasks.

```
Got it. I've added this task:
  [E][]project meeting (at: Mon 2-4pm) 
 Now you have 2 tasks in the list.  
 ```

### `deadline` - Adds a deadline to list

Create task with date attached to it.

Example of usage: 

`deadline return book /by 2022-02-11`

Expected outcome:

An Deadline with description return book on 11/02/2022 is added to list of tasks.

```
Got it. I've added this task:
  [D][]return book (by: Feb 11 2022) 
 Now you have 3 tasks in the list. 
```

### `done` - Marks task as done

Mark a completed task as done using task's index.

Example of usage: 

`done 1`

Expected outcome: 

Task at index 1 is marked done as indicated by an "X" in the second bracket.

```
Nice! I've marked this task as done!
  [T][X] math homework
```

### `delete` - Removes task from list

Delete an unnecessary task using task's index.

Example of usage: 

`delete 2`

Expected outcome:

Task at index 2 is deleted from the list of tasks.

```
Noted. I've removed this task:
[E][] project meeting (at: Mon 2-4pm)
  Now you have 2 tasks in the list.
```

### `find` - Finds tasks from list

Find a task by searching for a keyword.

Example of usage: 

`find homework`

Expected outcome: 

Tasks with matching keyword, "homework" are listed out.

```
Here are the matching tasks in your list:
1.[T][X] math homework
```

### `list` - List all tasks

Lists out all tasks present in list of tasks.

Example of usage: 

`list`

Expected outcome:

The full list of tasks.

```
Here are the tasks in your list:
1.[T][X]math homework
2.[D][]return book (by: Feb 11 2022)
```

### `help` - Provides help

Provides concise set of instructions to use all commands.

Example of usage: 

`help`

Expected outcome:

A list of instructions for using the application.

```
Quick help for actions you can perform: 
1. Add todo using format “todo <task>” (e.g. todo math)
2. Add event using format “event <task> /at <time>” (e.g. event project meeting /at Mon 2-4pm)
3. Add deadline using format “deadline <task> /by <date>” (e.g. deadline return book /by 2021-02-11)
4. Mark task as done with the task’s index using format “done <index of task>”  (e.g. done 2)
5. Delete task with the task’s index using format “delete <index of task>” (e.g. delete 2)
6. Find tasks similar to a keyword you entered using format “find <your input>” (e.g. find math)
7. View all tasks in task list using command “list”
```

