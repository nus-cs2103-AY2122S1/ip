# User Guide

Duke is a **desktop app** for _managing tasks_ and having the benefits of a Graphical User Interface (GUI).
You can add 3 types of tasks (Todo, Deadline, Event) and use a whole range of features to manage those tasks. 

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/loose-bus-change/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Double-click the file to start the app.

1. Type the command in the popup and press **Send** to execute it.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features 

### Add Todo Task

Have a task that does not have a deadline ? Use this feature to add a **Todo** task.

#### `todo <task description>` - add a todo task

Example of usage:

`todo read book`

Here is the output you should see from Duke: 

```
Got it! I've added this task:
[T][ ] read book
Now you have 1 task(s) in the list. 
```

### Add Deadline Task

Have a task that is due by a particular date and time ? Use this feature to add a **Deadline** task.

#### `deadline <task description> /by <date and time in dd/mm/yyyy hhmm>` - add a deadline task

Example of usage:

`deadline return book /by 2/12/2021 1800`

Here is the output you should see from Duke:

```
Got it! I've added this task:
[D][ ] return book by: 2021-12-02 18:00
Now you have 2 task(s) in the list. 
```

### Add Event Task

Have an event that happens at a particular time ? Use this feature to add an **Event** task. 

#### `event <task description> /at <date and time in dd/mm/yyyy hhmm> ` - add an event task

Example of usage:

`event project meeting /at 15/10/2021 1900`

Here is the output you should see from Duke:

```
Got it! I've added this task:
[E][ ] project meeting at: 2021-10-15
Now you have 3 task(s) in the list. 
```

### Mark as done

Finished with a task ? Yay! You can use this feature to indicate that you have finished the task. 

#### `done <index> ` - to indicate you are done with a task

Example of usage:

`done 2`

Here is the output you should see from Duke:

```
Nice! I've marked this task as done:
[X] return book
```

### View Task List

Use this feature to view the current list of tasks that you have.

#### `list` - to view list of tasks

Example of usage:

`list`

Here is the output you should see from Duke:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][X] return book by: 2021-12-02 18:00
3. [E][ ] project meeting at: 2021-10-15
```

### Search by keyword

Struggling to find a particular task ? Use this feature to search for a task by using a keyword

#### `find <keyword>` - add a todo task

Example of usage:

`find meeting`

Here is the output you should see from Duke:

```
[E][ ] project meeting at: 2021-10-15
```

### Delete a task

Use this feature to remove a task from the list.

#### `delete <index>` - delete a particular task

Example of usage:

`delete 2`

Here is the output you should see from Duke:

```
Noted. I've removed this task:
[D][X] return book by: 2021-12-02 18:00
Now you have 2 task(s) in your list
```

### Get schedule

Want to get a schedule of your tasks. Use this feature to get tasks in three different ways. 

Here is a sample task list to demonstrate this feature:
```
[T][ ] read book 
[E][ ] project meeting at: 2021-10-15 19:00
[E][ ] make pasta at: 2021-08-16 13:00
[D][ ] submit ip by: 2021-08-16 23:59
[E][ ] attend party at: 2022-03-07 15:00
```

#### `schedule on <date in dd/mm/yyyy>` - get the list of tasks on a particular date

Example of usage:

`schedule on 16/08/2021`

Here is the output you should see from Duke:

```
Here are the list of tasks scheduled on 16/08/2021:
1. [E][ ] make pasta at: 2021-08-16 13:00
2. [D][ ] submit ip by: 2021-08-16 23:59
```
#### `schedule until <date in dd/mm/yyyy>` - get the list of tasks until a particular date

Example of usage:

`schedule until 16/12/2021`

Here is the output you should see from Duke:

```
Here are the list of tasks scheduled until 16/12/2021:
1. [E][ ] project meeting at: 2021-10-15 19:00
2. [E][ ] make pasta at: 2021-08-16 13:00
3. [D][ ] submit ip by: 2021-08-16 23:59
```

#### `schedule after <date in dd/mm/yyyy>` - get the list of tasks after a particular date

Example of usage:

`schedule after 16/08/2021`

Here is the output you should see from Duke:

```
Here are the list of tasks scheduled after 16/08/2021:
1. [E][ ] project meeting at: 2021-10-15 19:00
2. [E][ ] attend party at: 2022-03-07 15:00
```

**Important Note:** you can also enter your dates in the following formats:
* d/m/yyyy
* dd/m/yyyy
* d/mm/yyyy

### Help

If you ever forget the format for the commands or want to refresh your memory on what commands are available, use this feature to have Duke remind you! 

#### `help` - get a list of Duke command formats

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo Task** | `todo <task description>` <br> e.g., `todo read book`
**Add Deadline Task** | `deadline <task description> /by <date and time in dd/mm/yyyy hhmm>` <br> e.g., `deadline return book /by 2/12/2021 1800`
**Add Event Task** | `event <task description> /at <date and time in dd/mm/yyyy hhmm>` <br> e.g., `event project meeting /at 16/08/2021 1900`
**Find Task** | `find <keyword>` <br> e.g., `find book`
**Delete Task** | `delete <INDEX>`<br> e.g., `delete 2`
**Schedule On** | `schedule on <date in dd/mm/yyyy>`<br> e.g., `schedule on 16/08/2021`
**Schedule Until** | `schedule until <date in dd/mm/yyyy>` <br> e.g., `schedule until 16/08/2021`
**Schedule After** | `schedule after <date in dd/mm/yyyy>` <br> e.g., `schedule after 16/08/2021`
**Show Task List** | `list`
**Help** | `help`
