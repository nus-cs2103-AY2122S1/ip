# User Guide - Duke

## Introduction
Thank you for using Duke! \
Duke is a task managing system that can help you organize your everyday tasks.

## Quick Start 
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar from
3. Copy the file to the folder you want to use as the home folder for your duke.
4. Open the command line and type `java -jar duke.jar` to get started.

## Features 

### Feature - Manage

You can add tasks, delete tasks, and mark tasks as done.

### Feature - Categorize

Your tasks can be categorized into three types: todo, deadline and event. 
For deadline and event, you can add a specific time for the task.

### Feature - Find

You can find your tasks by the content or the time of the task. 

## Usage

### `todo` - add a task of type todo

The command needs to start with `todo` and a space. Contents are followed by the space.

Example of usage:

`todo make a call to friend`

Expected outcome:

The todo task is added to the task list. The number of tasks will be shown.

```
    ____________________________________________________________
     Got it. I've added this task: 
      [T][ ] make a call to friend
     Now you have 6 tasks in the list. 
    ____________________________________________________________

```

### `event` - add a task of type event

The command needs to start with `event` and a space. Contents are followed by the space.
At the end of the command, use `/at` to indicate the time. 
If the time is in the format `YYYY-MM-DD`, the event will be recognized and found by `schedule` command. 

Example of usage:

`event table tennis game /at 2021-10-02`

Expected outcome:

The event task is added to the task list. The number of tasks will be shown.

```
    ____________________________________________________________
     Got it. I've added this task: 
      [E][ ] table tennis game (at: Oct 2 2021)
     Now you have 8 tasks in the list. 
    ____________________________________________________________

```

### `deadline` - add a task of type deadline

The command needs to start with `deadline` and a space. Contents are followed by the space.
At the end of the command, use `/by` to indicate the time.
If the time is in the format `YYYY-MM-DD`, the deadline will be recognized and found by `schedule` command.

Example of usage:

`deadline project /by 2021-11-11`

Expected outcome:

The deadline task is added to the task list. The number of tasks will be shown.

```
    ____________________________________________________________
     Got it. I've added this task: 
      [D][ ] project (by: Nov 11 2021)
     Now you have 9 tasks in the list. 
    ____________________________________________________________

```



### `delete` - delete a task

The command needs to start with `delete` and a space, followed by the index of the task.

Example of usage:

`delete 7`

Expected outcome:

The task is deleted from the task list. The number of tasks will be shown.

```
    ____________________________________________________________
     Noted. I've removed this task: 
      [E][ ] table tennis game (at: 2021-10-2)
     Now you have 8 tasks in the list. 
    ____________________________________________________________
```


### `done` - mark a task as done

The command needs to start with `done` and a space, followed by the index of the task.

Example of usage:

`done 7`

Expected outcome:

The task is marked as done. 

```
    ____________________________________________________________
    Nice! I've marked this task as done: 
       [E][X] table tennis game (at: Oct 2 2021)
    ____________________________________________________________

```

### list - print all tasks

This command will print all the tasks recorded in Duke. 

Example of usage: 

`list`

Expected outcome:

A indexed list of tasks with two brackets before each task. 
The first bracket indicates the type of the task and second indicates whether the task is done.

```
    ___________________________________________________________
     1. [T][ ] submit iP 
     2. [D][ ] cs2100assignment (by: Sep 15 2021)
     3. [E][ ] cs4243 quiz (at: Sep 16 2021)
     4. [D][X] cs1231assignment (by: Sep 15 2021)
     5. [E][ ] start of recess week (at: Sep 18 2021)
    ____________________________________________________________
```



### `find` - find tasks with certain contents. 

The command needs to start with `find` and a space, followed by the contents.

Example of usage:

`find project`

Expected outcome:

Print the tasks with certain contents.

```
    ____________________________________________________________
    [D][ ] project (by: Nov 11 2021)

    ____________________________________________________________

```


### `schedule` - find tasks on certain date.

The command needs to start with `schedule` and a space, followed by the date.

Example of usage:

`schedule 2021-09-15`

Expected outcome:

```
    ____________________________________________________________
    [D][ ] cs2100assignment (by: Sep 15 2021)
    [D][X] cs1231assignment (by: Sep 15 2021)

    ____________________________________________________________

```


### `help` - get help

Example of usage:

`help`

Expected outcome:

```
    ____________________________________________________________
    bye: end the program 
    delete: delete a task 
    done: mark a task as done 
    find: find a task 
    list: get a list of tasks 
    schedule: get tasks of a certain day
    ____________________________________________________________
```


### `bye` - exit the program

Example of usage:

`bye`


