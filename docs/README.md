# :scroll: User Guide

### About
This app helps the user track tasks.
There are 3 types of tasks that the user can add and track:
1. **ToDo**
2. **Event** 
3. **Deadline**

`ToDos` are the most basic type of task, with just a name and a completion status, whereas `Deadlines` and `Events` can additionally store date and time information.
***
## Features and commands

&nbsp;
### Adding a new task to the list
Simply specify the task type followed by the details of the task.  
To add a new Todo: `todo <task name>`  
To add a new Event: `event <task name> / <date and time of event>`  
To add a new Deadline: `deadline <task name> / <date and time of deadline>`  

Note that all information in angled brackets are compulsory.  
Note that date and time have to be specified in the format: "dd-mm-yyyy hhmm", where hhmm is in 24 hour time.

Example usage 1:
```
todo buy bread
```
Expected output 1:
```
New task added to list:  
T: [] buy bread
```
Example usage 2:
```
deadline ph1234 essay / 15-09-2021 2359
```
Expected output 2:
```
New task added to list:  
D: [] ph1234 essay before: Wed, 15 Sep 2021, 23:59
```

&nbsp;
### Listing all tasks currently in list: `list`
Continuing from the example above, entering the command `list` will give the following output:
```
Your list contains:
1. T: [] buy bread
2. D: [] ph1234 essay before: Wed, 15 Sep 2021, 23:59
```

&nbsp;
### Marking a task as completed: `done`
Simply append the **name of the task** behind the command `done`.  
Continuing from the example above, entering `done ph1234 essay` will give the following output:
```
Task marked as completed:  
D: [X] ph1234 essay before: Wed, 15 Sep 2021, 23:59
```

&nbsp;
### Deleting a task from the list: `delete`
Enter the command `delete <task number>`, where `<task number>` is the number of the task to delete.  
For example, to delete the task `buy bread` from the list in the previous examples, enter  `delete 1` since the task is task 1 on the list.  
> :bulb: tip: you can check the task number using `list`

&nbsp;
### Searching for tasks: `find`
Searches for tasks with task names that contain the specified keywords.  
To use, enter `find <keyword(s)>`. For example, if your list has tasks with the following names:
1. cs2103 individual project submission
2. prepare for cs2103 tutorial
3. client meeting (John)
4. cs2103 team project user guide

Entering `find cs2103` will display items 1, 2 and 4 to the user.
>:heavy_exclamation_mark: note that the app checks if the given `<keyword(s)>` are a substring of the name of the tasks.  
> Therefore, entering `find cs2103 project` **will not** bring up tasks 1 and / or 4.  
> Entering `find cs` **will** bring up 1, 2 and 4 since those names have "cs" as a substring.

&nbsp;
### Bringing up the schedule for a particular date: `schedule`
Entering `schedule <date>` brings up all the events and deadlines that have a date that matches the specified date.  
Note that the date specified has to be in the format: "dd-mm-yyyy".

&nbsp;
### Closing the app: `bye`
Entering `bye` will close the app.
