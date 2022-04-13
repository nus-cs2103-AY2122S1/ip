# User Guide

## Features 

### Feature 1: Managing Tasks
Duke supports three main types of tasks:
1. ToDo
2. Deadline
3. Event

#### Deadline
A Deadline is defined as a task that has a specific date of completion. These tasks have to be completed by the specified date. 

#### Event
An Event is a task that occurs at a particular date. Events are tasks where the user has to attend. 

#### ToDo
A ToDo is defined as a task that does not have a specific date of completion. They are meant as a quick way to record tasks that do not have a hard date of completion, or if the date of completion is pending. 

### Feature 2: Sorting Tasks

#### Date
Duke supports sorting of tasks by first their type, then the date. Deadline tasks are sorted with the highest priority, followed by Event tasks, and lastly ToDo tasks. Within each task category, tasks with the earliest dates are sorted first.

Note: only dates in the format ```YYYY-MM-DD``` are accepted. 


## Usage

### ```bye``` - Exits chatbot

Example of usage:
```java
bye
```

Expected outcome:

Closes the Duke chatbot window.


### ```delete``` - Deletes a task from the task list, indicated by the index

Example of usage:
```java
delete 1
```

Expected outcome:

Removes the task indexed by 1 from the list. 

Expected output: 
```
Noted. I've removed this task:
    [D][X] sign up for challenge (by: Sep 20 2021)
Now you have 2 tasks in your list.
```


### ```done``` - Marks a task, indicated by the index, as done

Example of usage:
``` java
done 1
```

Expected outcome:

Marks the task indexed by 1 as done. 

Expected output: 
```
Nice! I've marked this task as done:
    [E][X] Google Day (at: Sep 15 2021)
```


### ```deadline``` - Adds a Deadline task

Example of usage:

``` java
deadline school assignment /by 2021-12-02
```

Expected outcome:

Adds a deadline task to the list.

Expected output: 
```
Got it. I've added this task:
    [D][] school assignment (by: Dec 02 2021)
Now you have 3 tasks in your list.
```


### ```event``` - Adds an Event task

Example of usage: 

``` java
event Google Day /at 2021-09-15
```

Expected outcome:

Adds an event task to the list.

Expected output: 
```
Got it. I've added this task:
    [E][] Google Day (at: Sep 15 2021)
Now you have 4 tasks in your list.
```

### ```find``` - Finds a task based on the given keyword

Example of usage: 

``` java
find school
```

Expected outcome:

Finds and filters all tasks with the keyword "school".

Expected output: 
```
Here are the matching tasks in your list:
    1. [D][] school assignment (by: Dec 02 2021)
```

### ```list``` - Lists all tasks stored in the task list

Example of usage:
``` java
list
```

Expected outcome:

Lists all saved tasks. If there is no saved tasks, Duke will send a message to 
indicate that there is no saved task.

Expected output (if there are saved tasks):
```
1. [D][X] sign up for challenge (by: Sep 20 2021)
2. [E][] Google Day (at: Sep 15 2021)
3. [T][] finish proposal for ILP
```

Expected output (if there are no saved tasks): 
```
You have not added any tasks to your list.
Log any task you wish to add.
```


### ```sort``` - Sorts all tasks from earliest date to latest date, followed by ToDo

Example of usage:
``` java
sort
```

Expected outcome:

Sorts all the task by their type, then sorts by the date of the tasks.
Deadline tasks are sorted with highest priority.
Event tasks are sorted with higher priority than ToDo tasks.
Tasks are sorted by their earliest date to latest date.

Expected output (if there are saved tasks):
```
Sorted! Here are the tasks:
    1. [D][] school assignment (by: Dec 02 2021)
    2. [E][X] Google Day (at: Sep 15 2021)
    3. [T][] read book
```


### ```todo``` - Adds a Todo task

Example of usage:

``` java
todo read book 
```

Expected outcome:

Adds a todo task to the list.

Expected output: 
```
Got it. I've added this task:
    [T][] read book
Now you have 4 tasks in your list.
```
