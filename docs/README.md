# User Guide

## Features 

### Feature - Adding variety of tasks.

This chatbot application allows one to keep track of the tasks that one has, including events, deadlines and todos.


### Feature - Marking as done
Once a task has been completed, it can be marked as done.

It allows users to check tasks that are done and delete unwanted tasks.

### Feature - List all
All tasks can be displayed in a convenient format for easy viewing.

### Feature - Deleting tasks
Tasks that are no longer required can be deleted from the list.

## Usage

### `help` - Displays the help menu
Example of usage:
`help`

Expected outcome:

Shows the help menu
``` 
Welcome to Duke!
Here are the available commands:
help: Show the current help message.
todo DESCRIPTION: Adds a new TODO task.
event DESCRIPTION /at TIME: Adds a new EVENT task.
deadline DESCRIPTION /at TIME: Adds a new DEADLINE task.
find WORD: Find tasks with given word in the description.
bye: Exits the program.
list: Shows all tasks.
delete INDEX: Removes the task at the INDEX number on the task list.
done INDEX: Marks the task at the INDEX number on the task list as done.
```
### `todo` - Adds a todo task
Example of usage:
`todo buy milk`

Expected outcome:
Creates a new todo task
```
Got it. I've added this task:
[T][] buy milk
Now you have 1 tasks in the list.
```

### `event` - Adds a todo task
Example of usage:
`event buy milk /at Sunday`

Expected outcome:
Creates a new event task
```
Got it. I've added this task:
[E][] buy milk (at: Sunday)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a todo task
Example of usage:
`deadline buy milk /by Sunday`

Expected outcome:
Creates a new deadline task
```
Got it. I've added this task:
[D][] buy milk (by: Sunday)
Now you have 3 tasks in the list.
```

### `find` - List tasks containing a word
Example of usage:
`find milk`

Expected outcome:
List all tasks in the task list which contains the word in the description

```
Here are the matching tasks in your list:
1. [T][] buy milk
2. [E][] buy milk (at: Sunday)
3. [D][] buy milk (by: Sunday)
```

### `list` - List all tasks
Example of usage:
`list`

Expected outcome:
List all tasks in the task list

```
Here are the tasks in your list:
1. [T][] buy milk
2. [E][] buy milk (at: Sunday)
3. [D][] buy milk (by: Sunday)
```

### `bye` - Exits the program
Example of usage:
`bye`

Expected outcome:
Exits the program
```

```

### `delete` - Deletes a task
Example of usage:
`delete 1`

Expected outcome:
List the task with the corresponding index in the task list

```
Noted. I've removed this task:
[T][] buy milk
Now you have 2 tasks in the list.
```

### `done` - Marks a task as done
Example of usage:
`done 1`

Expected outcome:
List the task with the corresponding index in the task list

```
Nice! I've marked this task as done:
[D][X] buy milk (by: Sunday)
```