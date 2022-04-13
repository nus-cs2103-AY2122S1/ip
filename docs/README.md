# User Guide

##Using the Duke Productivity Tool
1. Check that you have Java 11 or higher installed on your device.
2. Download the latest version of "Duke Productivity Tool.jar" 
[here](https://github.com/chrus-chong/ip/releases).
3. Try out the application for yourself!

To familiarise yourself with the application, it is recommended that you
* Browse through this user guide to understand the commands
* Type the `help` command into Duke

## Key Features 


### Task Handling
The Duke Productivity Tool supports the handling of various types of tasks.
Multiple task types have been implemented to facilitate tasks that end by a certain time or occur between 
a range of timings. The application keeps track of ToDo, Deadline, Event, and DoAfter tasks.
You can choose to save or delete the aforementioned tasks as they see fit.

### Task List
The Duke Productivity Tool allows for easy access to all the tasks that you have input into the application.
You can view all tasks along with the status of completion and start or end times if applicable.

### Remembering Tasks
Tasks that are input or removed from the task list will have the respective changes applied to the data file.
Even after closing Duke and loading into it again, the most updated task list will show up.

## Usage

### 1. `bye` - Exits Duke Productivity Tool

Exits the program.

Format:
`bye`

### 2. `help` - Displays all possible commands

Displays message that lists all possible commands,
as well as instructions on how to format these commands.

Format:
`help`


### 3. `todo` - Stores ToDo task in task list

Creates a ToDo task based on your description and stores it into the Duke task list.

Format:
`todo <description>`

Example of usage:

`todo Plan schedule for recess week`

Expected outcome: A ToDo task with description "Plan schedule for recess week" is stored in Duke.

```
Sure. The following task has been added:
[T][] Plan schedule for recess week

You now have 1 task in the list
```

### 4. `deadline` - Stores Deadline task in task list

Creates a Deadline task based on your input and stores it into the Duke task list.

Format:
`deadline <description> /by <date> <time>`

Example of usage:

`deadline Complete assignmnet 2 /by 2021-09-16 23:59`

Expected outcome: A Deadline task with description "Complete assignment 2" is stored in Duke.

```
Sure. The following task has been added:
[D][] Complete assignment 2 (by: Sep 16 2021 11:59pm)

You now have 1 task in the list
```


### 5. `event` - Stores Event task in task list

Creates a Event task based on your input and stores it into the Duke task list.

Format:
`event <description> /at <date> <startTime> <endTime>`

Example of usage:

`event Zoom meeting with Prof /at 2021-09-15 09:00 12:00`

Expected outcome: An Event task with description "Zoom meeting with Prof" is stored in Duke.

```
Sure. The following task has been added:
[E][] Zoom meeting with Prof (at: Sep 15 2021 9:00am to 12:00pm)

You now have 1 task in the list
```

### 6. `doafter` - Stores DoAfter task in task list

Creates a DoAfter task based on your input and stores it into the Duke task list.
Input an integer after the `doafter` command to indicate when this task should be done after.

Format:
`doafter <description> /aft <number>`

Example of usage:

`doafter Consolidate notes /aft 1`

Expected outcome: A DoAfter task with description "Consolidate notes" is stored in Duke.

```
Sure. The following task has been added:
[A][] Consolidate notes (to be done after: Zoom meeting with Prof)

You now have 2 tasks in the list
```


### 7. `list` - List all tasks in Duke

Displays message containing all task all tasks
along with the status of completion and start or end times if applicable.

Format: `list`


### 8. `done` - Marks task as done

Marks a specific task as done in the Duke task list.
Called with an integer to indicate the index of the task in the task list to mark as done.

Format: `done <index>`

Example of usage:

`done 1`

Expected outcome: Task is marked as done.

Description of the outcome.
```
I see that you have completed a task! Keep up the good work!

This task has now been marked as completed
[X] Zoom meeting with Prof
```

### 9. `delete` - Removes task from Duke

Removes a specific task from the Duke task list.
Called with an integer to indicate the index of the task in the task list to remove.

Format: `delete <index>`

Example of usage:

`delete 1`

Expected outcome: Task removed.

Description of the outcome.
```
Successfully removed task 1
```


### 10. `find` - Find tasks in Duke based on keywords

Finds task in the Duke task list that matches the input keywords

Format: `find <keywords>`

Example of usage:

`find sch`

Expected outcome: Filters out all tasks that contain "sch".

Description of the outcome.
```
Here are your tasks that contain "sch"
1. [T][] Plan schedule for recess week
```