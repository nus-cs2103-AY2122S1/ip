# User Guide for Baba the Duke
    
## Features 
### Keeping track of various tasks (✓)
* Deadlines - tasks to be done ***by*** a certain date and time
* Events - tasks happening ***at*** a certain time and date
* ToDos - tasks with *no specified time or date*

### Show current tasks and filtering them (✓)
Display all current tasks and search accordingly.

### Mark completed tasks and delete them (✓)
Mark tasks as complete and consequently delete them if needed.

### Automatic saving and loading of task list (✓)
No opening or saving of files required! :)

### Shortcuts for experienced users (✓)
Saves you time by using short letters as commands.

## Usage

### `todo` or `t` - Creates a todo task.
Example of usage:

`todo house chores`

Expected Outcome:
```
***
Understood. Added the task:
    [T][] house chores
You now have 2 tasks in the list.
***
```

### `deadline` or `d` - Creates a deadline task.
Usage:

`deadline task_name /by YYYY-MM-DD HHMM`
- *task_name* The name or description of the deadline
- */by* - The slash '/' is optional.
- *YYYY-MM-DD* - Date format
- *HHMM* - Time format in 24HR.

Example: `deadline homework by 2021-01-02 2359`

Expected Outcome:
```
***
Understood. Added the task:
    [D][] homework (by 02 Jan 2021 11:59PM)
You now have 1 task in the list.
***
```

### `event` or `e` - Creates an event task.
Usage:

`event task_name /by YYYY-MM-DD HHMM`
- *task_name* The name or description of the deadline
- */at* - The slash '/' is optional.
- *YYYY-MM-DD* - Date format
- *HHMM* - Time format in 24HR.

Example: `event party at 2021-05-07 2000`

Expected Outcome:
```
***
Understood. Added the task:
    [E][] party (at 05 Jul 2021 8:00PM)
You now have 2 tasks in the list.
***
```

### `list` or `l` - Returns the current list of tasks.
Example usage: `list`

Expected Outcome:
```
***
These are the tasks in the list: 
   1. [D][] homework (by 05 Jul 2021 23:59PM)
   2. [E][] party at Zac's (at 09 Sep 2021 4:00PM)
***
```

### `delete` or `del` or `rm` - Deletes a task.
Usage: `delete x` where x refers to the task's number
in the list.

Example: `delete 1`

Expected Outcome:
```
***
You have successfully removed this task: 
   [D][] homework (by 05 Jul 2021 23:59PM)
***
```

### `find` or `f` - Finds tasks containing given keyword(s).
Example usage: `find party`

Expected Outcome:
```
***
Here are the matching tasks in your list: 
   1. [E][] party (at 05 Jul 2021 8:00PM)
   2. [E][] party at Zac's (at 09 Sep 2021 4:00PM)
***
```

### `done` - Mark a task as completed.
Usage: `done x` where x refers to the task's number 
in the list.

Example: `done 1`

Expected Outcome:
```
***
You have successfully done this task: 
   [E][] party (at 05 Jul 2021 8:00PM)
***
```

### `bye` or `b` - Saves all tasks and exits the application.
Example of usage: 

`bye`

Expected Outcome:
```
Goodbye! Have a nice day. :)
```
Example of usage:

`keyword (optional arguments)`