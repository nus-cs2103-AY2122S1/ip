# User Guide

## Features 

### Add Task
User is able to add a task to the task list.
The following is the type of tasks available : 

*Todo : This task contains description and marker specifying if it has been done or not.
*Event : This task contains description and marker, as well as date specified by 'at'
*Deadline : This task contains description and marker, as well as date of the deadline specified by 'by'


### Delete Task
User is able to delete task from the list

### Sort Task
User is able to sort the tasks in chronological order

### List Task
User is able to show the list of tasks currently in the list

### Storage
User's list is stored in file called DukeTask.txt

### Mark Task as Done
User is able to mark task as done

### Find Task
User is able to search for task based on a keyword

## Usage

### todo DESCRIPTION

Add a task of type Todo in the list with the specified description

Example of usage: 

todo help clean



### list

Shows the current task list

Example of usage: 

list

Expected outcome:

a list containing the task list

### event DESCRIPTION /at DATE

Add a task of type event in the list with the specified description and date

Example of usage: 

event party /at 2020-10-10

Expected outcome:

Adds an event in the task list

`
### deadline DESCRIPTION /by DATE

Add a task of type deadline in the list with the specified description and date

Example of usage: 

deadline assignment /by 2001-10-10

Expected outcome:

adds a task of type deadline to the list


### done TASK_ID

Mark a task to be done specified by the task id

Example of usage: 

done 1

Expected outcome:

task numbered 1 will be marked as done


### delete TASK_ID

Deletes the task based on the specified task id.


Example of usage: 

delete 1

Expected outcome:

task numbered 1 will be deleted


### sort

Sorts the task list by chronological order


Example of usage: 

sort

Expected outcome:

the task list will be sorted

### find KEYWORD

Shows a list with tasks containing the keyword

Example of usage: 

find party

Expected outcome:

List showing tasks with the keyword party

### bye

Exits the program and close the window

