# User Guide

Duke is a personal assistant chatbot that will help you  keep track of various things.
It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical 
User Interface (GUI).

![Image of Duke](https://github.com/pranav-ganesh/ip/blob/master/docs/Ui.png?raw=true)

## Features 

1) Add tasks with deadline
2) Add todos
3) Add events
4) Edit tasks
5) View the task list
6) Mark tasks as done
7) Delete tasks
8) Find tasks by keyword
9) Clone tasks

### Storage file

Store the task list in a local file.

### Add, Edit, View, Delete, Find

Add, Edit, View, Delete, Clone and Find tasks.

### Mark as done

Mark tasks as done once the task is completed.

## Usage

### Adding a todo: `<description>`

Adds a task of type todo to the task list.

Example of usage: 

`todo purchase laptop`

Expected outcome:

The task is added to the task list.

```
Got it. I've added this task:
[T][] purchase laptop
Now you have 1 task(s) in the list.
```

### Adding a deadline: `<description>/by <yyyy-MM-dd> <HHmm>`

Adds a task of type deadline to the task list.

Example of usage:

`deadline CS2103T iP/by 2021-09-20 2359`

Expected outcome:

The task is added to the task list.

```
Got it. I've added this task:
[D][] CS2103T iP (by: Sep 20 2021, 11 PM)
Now you have 2 task(s) in the list.
```

### Adding an event: `<description>/at <yyyy-MM-dd> <HHmm>`

Adds a task of type event to the task list.

Example of usage:

`event concert/at 2021-09-25 1800`

Expected outcome:

The task is added to the task list.

```
Got it. I've added this task:
[E][] concert (at: Sep 25 2021, 6 PM)
Now you have 3 task(s) in the list.
```

### Viewing the task list: `list`

View the current task list.

Example of usage:

`list`

Expected outcome:

The task list is printed on the screen.

```
1. [T][] purchase laptop
2. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
3. [E][] concert (at: Sep 25 2021, 6 PM)
```

### Finding tasks with a keyword: `find <keyword>`

Finds task in the task list that contain the keyword.

Example of usage:

`find laptop`

Expected outcome:

Tasks containing the keyword 'laptop' in the description will be displayed.

```
Here are the matching task(s) in your list:
1. [T][] purchase laptop
```

### Marking tasks as done: `done <task index>`

Marks task as complete. The task index refers to number this task is associated
to in the task list.

Example of usage:

`done 1`

Expected outcome:

The first task in the task list is marked as done. [X] denotes that the task is done.

```
Nice! I've marked this task as done:
[T][X] purchase laptop
```

### Deleting tasks: `delete <task index>`

Deletes task in the task list. The task index refers to the number this task is associated
to in the task list.

Example of usage:

`delete 1`

Expected outcome:

The first task in the task list is deleted.

```
Noted. I've removed this task:
[T][X] purchase laptop
Now you have 2 tasks in the list.
```

### Cloning tasks: `clone <task index>`

Clones task in the task list and adds it to the task list. The task index refers to the number 
this task is associated to in the task list.

Example of usage:

`clone 1`

Expected outcome:

The first task in the task list is cloned and added to the task list.

```
Task 1 from the task list has been cloned! Here is 
the latest task list:
1. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
2. [E][] concert (at: Sep 25 2021, 6 PM)
3. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
```

### Editing tasks: `edit <task index>/task <new task command>`

Replaces the task in the specified task index with the new task. The new task command can be a
deadline, event or todo. The task index refers to the number this task is associated to in the task list.

Example of usage:

`edit 1/task event football match/at 2021-12-03 1900`

Expected outcome:

The first task in the task list is updated with the new task.

```
Task 1 in the task list has been updated! Here is 
your latest list:
1. [E][] football match (by: Dec 3 2021, 7 PM)
2. [E][] concert (at: Sep 25 2021, 6 PM)
3. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
```

### Editing task descriptions: `edit <task index>/description <new description>`

Replaces the task description of the task in the specified task index with the new description. 
The task index refers to the number this task is associated to in the task list.

Example of usage:

`edit 1/description badminton match`

Expected outcome:

The first task in the task list is updated with the new task.

```
The description of Task 1 in the task list has been 
updated! Here is your latest list:
1. [E][] badminton match (by: Dec 3 2021, 7 PM)
2. [E][] concert (at: Sep 25 2021, 6 PM)
3. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
```

### Editing task date and time: `edit <task index>/datetime <yyyy-MM-dd> <HHmm>`

Replaces the date and time of the task in the specified task index with the new date and time. 
The task in the specified task index has to be a deadline or event task since todos don't have a date and time.
The task index refers to the number this task is associated to in the task list.

Example of usage:

`edit 1/datetime 2022-01-01 2000`

Expected outcome:

The first task in the task list is updated with the new task.

```
The DateTime of Task 1 in the task list has been 
updated! Here is your latest list:
1. [E][] badminton match (by: Jan 1 2022, 8 PM)
2. [E][] concert (at: Sep 25 2021, 6 PM)
3. [D][] CS2103T iP (by: Sep 20 2021, 11 PM)
```

### Exiting the application: `bye`

Terminates and closes the application window.

Example of usage:

`bye`

Expected outcome:

The application displays the message 'Bye. Have a great day!' and then exits. The application window closes.

```
Bye. Have a great day!
```

## Command summary

Command | Format | Example
------------ | ------------- | -------------
todo | ```todo <description>``` | ```todo homework```
event | ```event <description>/at <yyyy-MM-dd> <HHmm>``` | ```event concert/at 2021-03-04 1900```
deadline | ```deadline <description>/by <yyyy-MM-dd> <HHmm>``` | ```deadline assignment/by 2021-09-20 2359```
list | ```list``` | ```list```
done | ```done <task index>``` | ```done 2```
delete | ```delete <task index>``` | ```delete 2>```
find | ```find <task index>``` | ```find assignment```
edit task | ```edit <task index>/task <new task command>``` | ```edit 2/task deadline worksheet/by 2021-10-10 1000```
edit description | ```edit <task index>/description <new description>``` | ```edit 1/description CS2103T iP```
edit date and/or time | ```edit <task index>/datetime <yyyy-MM-dd> <HHmm>``` | ```edit 3/datetime 2022-03-08 2300```
bye | ```bye``` | ```bye```
 