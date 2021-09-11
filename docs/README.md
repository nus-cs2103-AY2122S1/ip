# Duke User Guide

The Duke is a CLI-based chat-bot that helps users track their day-to-day tasks.

![](Ui.png?raw=true)

## Features 

### Easy Task Management

Add and delete your tasks with single line commands. 
Duke will help you remember saved tasks.
Tag your tasks to time or deadline, or mark them as complete/incomplete.
Why bother remembering all these nitty-gritty details?


### Flexible Task Retrieval

Quickly list all your tasks with a single command. You can even:
- Sort your tasks by time or completion status.
- Search for specific tasks with keyword search 

## Usage of Commands

### `todo`/`event`/`deadline` - Add tasks

Add different types of task into your list of saved tasks. 

1. Add a generic task with `todo [description]`:
```
todo my homework
```

2. Add a task with a time of occurrence with `event [description] /on [date]`:
```
event CS2103 Finals /at 12/11/2021
```
**Note:** Date must be formatted as _DD/MM/YYYY_

3. Add a task with due date with `deadline [description] /by [date]`:
```
deadline CS2103 iP submission /by 20/09/2021
```
**Note:** Date must be formatted as _DD/MM/YYYY_

### `list`/`find` - List out saved tasks

Use `list` to list all saved tasks.

Alternatively, use the flags:
1. `list -name` to list tasks in alphabetical order.
2. `list -time` to list tasks in chronological order.
3. `list -done` to list incomplete tasks first.

Use the command `find [keyword]` to search for tasks.

![](search-example.png?raw=true)

### `done` - Check off tasks

Use `done [id]` to mark tasks as complete.


e.g. To complete task #2:
```
done 2
```

### `delete` - Delete tasks 

Use `delete [id]` to delete tasks.
e.g. To delte task #3:
```
delete 3
```

### `bye` - Exit the program

Use `bye` to exit and close the program. All tasks will be saved so don't worry!