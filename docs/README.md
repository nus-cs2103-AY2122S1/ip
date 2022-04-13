# User Guide

## Description
Butler is an interactive task list manager that engages with you through a text-messaging based interface.

## Product Screenshot
![GUI](Ui.png)

## Features 
1. Adding a task
2. Listing tasks
3. Marking tasks as completed
4. Deleting a task
5. Finding tasks by description
6. Task reminders
7. Exiting program

## Usage 

### 1. Adding a task

There are 3 main types of tasks you can add. 
1. Todos - tasks that only contain a description.
2. Deadlines - tasks that include a description and a due date. 
3. Events - tasks that include a description and a venue which the event takes place at. 

#### 1.1 Adding a todo - ```todo```
Adds a Todo to the task list. 

Usage: ```todo DESCRIPTION```

Example(s): 
* ```todo wash the dishes```

#### 1.2 Adding a deadline - ```deadline```
Adds a deadline and its associated due date to the task list.

Usage: ```deadline DESCRIPTION /by DUEDATE```
* The ```DUEDATE``` should be written in yyyy-mm-dd format.

Example(s): 
* ```deadline complete CS2103T assignment /by 2021-09-14``` Adds a deadline with the description 'complete CS2103T 
  assignment' and the due date '14 September 2021'.

#### 1.3 Adding an event
Adds an event and its associated venue to the task list.

Usage: ```event DESCRIPTION /at VENUE``` 

Example(s):
* ```event celebrate friend's birthday /at friend's house``` Adds an event with the description 'celebrate friend's
birthday and the venue as 'friend's house' to the task list. 

### 2. Listing tasks
Displays the list of all tasks along with the integer id representing each task.

Usage: ```list```
* Each task is displayed in the format ```[Task type][Completed] DESCRIPTION (ADDITIONAL INFO)```
* For the task type, ```[T]``` is used to represent Todo, ```[D]``` for Deadline and ```[E]``` for Event. 
* ```[X]``` is used to represent a completed task, while ```[]``` represents an incomplete task.
* The integer index beside each task is used to refer to the associated task for other commands.

Example output: 
```
Here are the tasks in your list: 
1. [D][] complete CS2103T assignment (by: SEPTEMBER 14 2021) 
2. [T][X] wash the dishes
3. [E][] celebrate friend's birthday (at: friend's house)
```

All tasks which have been added are listed with their task types, completion status and description shown.

### 3. Setting a task as completed - ```done```
Mark a task as completed. 

Usage: ```done INDEX```
* The ```INDEX``` is the integer associated with each task in the ```list``` command.

Example(s):
* ```done 2``` Marks the second task displayed by the ```list``` command as completed.
* If the ```INDEX``` is invalid, no task is marked and Butler will send a response indicating that an 
error occurred due to invalid ```INDEX```. 

### 4. Deleting a task - ```delete```
Deletes an existing task in the task list.

Usage: ```delete INDEX```
* The ```INDEX``` is the integer associated with each task in the ```list``` command.
* If the ```INDEX``` is invalid, no task is deleted and Butler will indicate that ```INDEX``` is invalid.

Examples(s): 
* ```delete 1``` Removes the first task displayed by the ```list``` command. 

### 5. Finding tasks by description - ```find```
Displays all tasks whose description contains the provided ```SEARCH_QUERY```.

Usage: ```find SEARCH_QUERY```

Example: ```find hello``` Finds and displays tasks that contain 'hello' in the description in a list.

Example output:
```
Here are the tasks with matching descriptions in your list:
1.[T][X] hello world
```
Displays a list including tasks that contain 'hello' in the description.

### 6. Deadline reminders - ```reminder```
Displays all deadline tasks which have not yet been completed and due dates are earlier or until the specified filter 
time.

There are 3 due date time specifications Butler can remind you of current deadlines for: 
1. All deadlines
2. Is due within a week from today or earlier(overdue).
3. Is due today or earlier(overdue).

#### 6.1 Reminder for all incomplete deadlines
Displays all deadline tasks in a list that are incomplete regardless of due date.

Usage: ```reminder```

Example output:
```
Here's the list of incomplete tasks sorted by deadline(All):
[D][] finish homework A (by: SEPTEMBER 10 2021) 
[D][] finish homework B (by: SEPTEMBER 14 2021) 
[D][] finish homework C (by: SEPTEMBER 22 2021) 
[D][] finish homework D (by: SEPTEMBER 30 2021) 
```
All deadlines that are incomplete are displayed as reminder.

#### 6.2 Reminder for incomplete tasks which are overdue or are due today.
Displays all incomplete deadline tasks in a list that are overdue or are due today.

Usage: ```reminder today```

Example output:
```
Here's the list of incomplete tasks sorted by deadline(Due today or earlier):
[D][] finish homework A (by: SEPTEMBER 10 2021) 
[D][] finish homework B (by: SEPTEMBER 14 2021) 
```
Assuming the current date is SEPTEMBER 14, 2021, only incomplete deadlines that are overdue or 
are due by today are displayed.

#### 6.3 Reminder for incomplete tasks which are overdue or are due within a week from today
Displays all incomplete deadline tasks in a list that are overdue or are due within a week from today.

Usage: ```reminder week```

Example output:
```
Here's the list of incomplete tasks sorted by deadline(Due within a week from now or earlier):
[D][] finish homework A (by: SEPTEMBER 10 2021) 
[D][] finish homework B (by: SEPTEMBER 14 2021) 
[D][] finish homework C (by: SEPTEMBER 22 2021) 
```

Assuming the current date is SEPTEMBER 14, 2021, only incomplete deadlines that are overdue or
are due within a week from today (due SEPTEMBER 22, 2021 or earlier) are displayed.

### 7. Exiting the program - ```bye```
Displays shutdown message and terminates the Butler program. 

Usage: ```bye```

