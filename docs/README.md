# User Guide

Hello, welcome to a very unpolished chatbot! Due to current time issues (god bless CS schedules), I don't have much time to work on this.
I am considering working on this (and adding obscure easter eggs that little people will understand) after this semester, but it all depends
I guess. Anyways, here are the features currently available on this chatbot:

##Acknowledgements

A large portion of code used for the JavaFX GUI was adapted from SE-EDU:
https://se-education.org/guides/tutorials/javaFx.html

Images used for the GUI can also be found here:

Hagrid: https://64.media.tumblr.com/7fe67a781662f959c9e9e33ad85306bc/tumblr_p95s2cou6l1wkf3ifo1_500.jpg
Snape: https://i.ytimg.com/vi/hXyxGvCZsxk/maxresdefault.jpg


## Features

### Add tasks

Add a task to your list.

### Find tasks

Get a list of tasks containing a user-specified keyword.

### Delete tasks

Delete a task from your list.

### List all tasks

Retrieve a list of all your tasks.

### Give tasks a priority

Set a priority for a selected task.

### Check commands and how to use them

Retrieve a list of commands available for the chatbot.

## Usage

### `todo` - Creates a task without a time limit

Adds a Todo task to your task list.

Example of usage:

`todo task-name`

Expected outcome:

The chatbot should respond that your task has been added to the list successfully.

```
Ok can, sure. I have added this task as you wanted.
[T][] task-name
Now you have 1 tasks remaining. Get to work!
```

### `deadline` - Creates a task with a date limit

Adds a deadline task to your task list.

Example of usage:

`deadline task-name /by 2021-09-15`

Expected outcome:

The chatbot should respond that your task has been added to the list successfully.

Description of the outcome.

```
Ok can, sure. I have added this task as you wanted.
[D][] task-name (by: Sep 15 2021)
Now you have 2 tasks remaining. Get to work!
```

### `event` - Creates a task with a starting date

Adds a event task to your task list.

Example of usage:

`event task-name /at 2021-09-15`

Expected outcome:

The chatbot should respond that your task has been added to the list successfully.

Description of the outcome.

```
Ok can, sure. I have added this task as you wanted.
[E][] task-name (at: Sep 15 2021)
Now you have 3 tasks remaining. Get to work!
```

### `help` - Get a list of available commands

Retrieves a list of available commands from the chatbot.

Example of usage:

`help`

Expected outcome:

The chatbot will print a paragraph explaining the commands.


```
Oh, you need my help? Here are some commands:
todo <Task Name>
deadline <Task Name> /by <Date in YYYY-MM-DD format>
event <Task Name> /at <Date in YYYY-MM-DD format>
list
delete <Task Index>
done <Task Index>
find <Search Term>
setpr <Task Index> <Priority Level>
bye
```

### `list` - See all your tasks

Chatbot will give you a list of all your tasks.

Example of usage:

`list`

Expected outcome:

You should see a list of all your tasks.

Description of the outcome.

```
1. [T][] task-name
2. [D][] task-name (by: Sep 15 2021)
3. [E][] task-name (at: Sep 15 2021)
```

### `setpr` - Set task priority

Give a specified task a specified level of priority. The levels of priority available are:
```
ASAP
high
medium
low
```

Example of usage:

`setpr 1 medium`

Expected outcome:

Chatbot should respond saying that the priority has been set.

Description of the outcome.

```
Ok, very nice. I have set a priority for the following task.
[T][] task-name (Medium Priority)
Now you have 3 tasks remaining. Get to work!
```

### `find` - Search for a task

Searches and displays any task that matches a specified search term.

Example of usage:

`find task-name`

Expected outcome:

Chatbot should respond with a list of tasks matching the search term.

Description of the outcome.

```
Lucky for you, the following Tasks seem to match your search term.
1. [T][] task-name (Medium Priority)
2. [D][] task-name (by: Sep 15 2021)
3. [E][] task-name (at: Sep 15 2021)
```

### `done` - Set a task as done

Sets a task as completed. Completed tasks will have a X in their 2nd box.

Example of usage:

`done 2`

Expected outcome:

Chatbot should respond saying that he has set a task as completed.

Description of the outcome.

```
Ok, very nice. I have set the following task as completed.
[D][X] task-name (by: Sep 15 2021)
Now you have 3 tasks remaining. Get to work!
```

### `delete` - Deletes a task

Deletes a task from your list of tasks.

Example of usage:

`delete 1`

Expected outcome:

Chatbot should respond saying that the specified task has been deleted.

Description of the outcome.

```
Ok, very nice. I have deleted the following task.
[T][] task-name (Medium Priority)
Now you have 2 tasks remaining. Get to work!
```

### `bye` - Exits the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

The program window should close.