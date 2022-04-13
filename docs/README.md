# ChadBot User Guide

## Features 

On startup, this is what you will see:
![img.png](img.png)
To talk to ChadBot, type into the textbox at the bottom of the screen!

### Showing usage menu

ChadBot can display the available commands.
If you need to review the available commands, enter: ```help```

### Listing all tasks

ChadBot can list all the existing tasks in the task list.
To list tasks, enter: ```list```

### Adding a Todo task

A Todo task has a description, and doesn't need a date.
To add a Todo to the task list, enter: ```todo [Description]```
where the description is the description of the task you want to add.

Eg. *todo Book hair appointment*


### Adding a Deadline task

A Deadline task has a description and a date.
To add a Deadline to the task list, enter: ```deadline [Description] /by [yyyy-mm-dd]```.

The date follows the format: yyyy-mm-dd.

Eg. *deadline Coding assignment /by 2021-09-18*

### Adding an Event task

An Event task has a description, date, and time.
To add an Event to the task list, enter: ```event [Description] /at [yyyy-mm-dd HH:mm]```.

The date follows the format: yyyy-mm-dd.
The time is in 24hr format: HH:mm.

Eg. *event Mom's birthday /at 2021-10-13 14:00*

### Marking a task as done

ChadBot can mark a task in the task list as done.
To view the list of tasks, enter: ```list```. Then enter: ```done [Task Number]``` to mark the task with the 
corresponding number in the list as done.

Eg. *done 2*

### Searching for tasks

ChadBot can filter the list to show tasks with a keyword.
To search for tasks, enter: ```find [Keyword]```, where the keyword can be any word or partial word in a task.
Eg. *find homework*

### Exit

To end the ChadBot application, enter: ```bye```.




