# Guide to using Memo-Assistant Duke

Memo-Assistant Duke is an easy-to-use program with Graphical User Interface(GUI).
It can help you track different tasks to be done.

## Features 


### Feature-Todo

Adds an entry that is descriptive of a task that is to be done, one that does not require details on date.

Expected input format: todo {description} 

**Example:**
`todo read book` 

**Will result in the following**
```
Successfully added: 
[T][] read book
```


### Feature-Event

Adds an entry that is descriptive of a task that is to be done on a specific date.

Expected input format: event {description} /at {date in DD/MM/YYYY}

**Example:**
`event quiz /at 12/09/2021` 

**Will result in the following:**
```
Successfully added: 
[E][] quiz (at: 12 SEPTEMBER 2021)
```


### Feature-Deadline

Adds an entry that is descriptive of a task that is to be done by a specific date.

Expected input format: deadline {description} /by {date in DD/MM/YYYY}

**Example:**
`deadline assignment /by 20/09/2021` 

**Will result in the following:**
```
Successfully added: 
[D][] assignment (by: 20 SEPTEMBER 2021)
```


### Feature-List

Shows all entries of tasks currently stored.

Expected input format: list

**Example:**
`list` 

**Will result in the following:**
```
The current list has these items:
1.[T][] read book
2.[E][] quiz (at: 12 SEPTEMBER 2021)
3.[D][] assignment (by: 20 SEPTEMBER 2021)
```


### Feature-Done

Marks an entry of a task as completed.

Expected input format: done {index}

**Example:**
`done 1` 

**Will result in the following:**
```
Well done! The task is completed!
[T][X] read book
```


### Feature-Delete

Deletes an entry of task.

Expected input format: delete {index}

**Example:**
`delete 1` 

**Will result in the following:**
```
Noted, the following task has been deleted:
[T][X] read book
```


### Feature-Due

Checks for tasks due on a specific date.

Expected input format: due {date in DD/MM/YYYY}

**Example:**
`due 12/09/2021` 

**Will result in the following:**
```
The task(s) due are:
1.[E][] quiz (at: 12 SEPTEMBER 2021)
```


### Feature-Find

Checks for tasks with any matching keyword in their description.

Expected input format: find {keyword}

**Example:**
`find september`

**Will result in the following:**
```
The task(s) found are:
1.[E][] quiz (at: 12 SEPTEMBER 2021)
2.[D][] assignment (by: 20 SEPTEMBER 2021)
```


### Feature-Reschedule

Change the date associated with an entry of task.

Expected input format: reschedule {index} {new date in DD/MM/YYYY}

**Example:**
`reschedule 2 21/09/2021`

**Will result in the following:**
```
Noted, the following task have been rescheduled:
From [D][] assignment (by: 20 SEPTEMBER 2021)
To [D][] assignment (by: 21 SEPTEMBER 2021)
```


### Feature-Snooze

Upon launch of application, Duke checks for tasks due on the day itself and results in a dialog box displaying list of tasks due.
There will be 2 options to choose for the user.

`Snooze` results in relevant tasks to be rescheduled to the subsequent day, without a time specification.

`Confirm` does not result in any further changes, choose this to manually reschedule later or to ignore the alert.


### Feature-Bye

Close the program.

Expected input format: bye
**Example:**
`bye`

**A new dialog box will appear on top of Duke's dialog window, displaying the exit message:**

```
Bye. Hope to see you again soon!
```

Click `confirm` or `X` at the top right corner to proceed to close the program.
