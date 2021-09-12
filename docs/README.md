# User Guide

## Features 

### Manage tasks within a task list
Operations that are supported include:
* Adding a task
* Deleting a task
* Marking task as completed
* Finding tasks that match a keyword
* Sorting tasks chronologically

### Provides support for different types of tasks
Type of tasks supported include:
* todo: 
A task that only has an associated description.
* deadline: A task that has a description that needs 
to be done before a specified date.
* event: A task that has a description that will happen at
a specified date.

## Usage

### Displaying Task List : ```list```

Shows all tasks in the task list.

Format: ```list```

### Adding a Todo Task : ```todo```

Appends a todo task to the Task List.

Format: ```todo [DESCRIPTION]```

Outcome: Todo task is appended to the Task List.

Examples:
* ```todo Sweep the floor```

### Adding a Deadline Task : ```deadline```

Appends a deadline task to the Task List.

Format: ```deadline [DESCRIPTION] \by dd/mm/yyyy hhmm```

Examples:
* ```deadline finish cs2103t ip /by 17/9/2021 2359```

### Adding a Event Task : ```event```

Appends an event task to the Task List.

Format: ```event [DESCRIPTION] \at dd/mm/yyyy hhmm```

Examples:
* ```event attend cs2100 lecture ip /at 17/9/2021 1600```

### Deleting a Task: ```delete```

Removes permanently a selected task from the task list.

Format: ```delete TASK_ID```

Examples:
* ```delete 1``` marks the 1<sup>st</sup> task in the task 
list.

### Marking a Task as Done: ```done```

Marks a selected task as completed.

Format: ```done TASK_ID```

Examples:
* ```done 4``` marks the 4<sup>th</sup> task in the list
  with a cross ```[X]```in the checkbox.

### Finding tasks that contains a keyword : ```find```

Filters your task list so that it only shows tasks that
contains a particular keyword.

Format: ```find [KEYWORD]```

Examples:
* ```find cs2103``` will display a list of task that contains
  cs2103 in its description.

### Sorting tasks by date : ```sort```

Sorts your task list in chronological order. Tasks with no 
dates are placed to the bottom of the task list.

Format: ```sort```

Examples:

Given the list below:
```
    1. [E][] Eat Dinner (at: 14 Sep 2021, 7pm)
    2. [T][] Sweep the floor
    3. [D][] Eat Breakfast (by: 14 Sep 2021, 7am)
    4. [E][] Eat Lunch (at: 14 Sep 2021, 1pm)
```
```sort``` will output:
```
    1. [D][] Eat Breakfast (by: 14 Sep 2021, 7am)
    2. [E][] Eat Lunch (at: 14 Sep 2021, 1pm)
    3. [E][] Eat Dinner (at: 14 Sep 2021, 7pm)
    4. [T][] Sweep the floor
```

### Exiting the application: ```bye```

Tohru will bid you farewell before shortly closing the 
application.

Format: ```bye```




