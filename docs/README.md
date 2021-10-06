# User Guide

Duke is a Personal Assistant Chat bot which helps the user to 
keep track of his/her current tasks. It helps the user organise 
his/her tasks and manage their time efficiently. Duke currently 
supports 3 types of task: **Todo**, **Deadline** and **Event**.

## Features 

Notes about command format:
* Words in `UPPER_CASE` are the parameters supplied by the user.
* Command format has to follow the ones specified, else Duke 
  will not organise your tasks correctly.

### View list of tasks: `list`
Shows the current task list.

Format: `list`

### Mark a task as done: `done`
Marks the specified task as done.

Format: `done INDEX`
* `INDEX` refers to the index number of the task shown in the
  task list when `list` command is entered.

Example: 
* `done 2` marks the second task in the list as done. 


Expected outcome:
```
Nice! I've marked this task as done:
  [T][X] join sports club
```

### Deletes a task from the list: `delete`
Deletes the specified task.

Format: `delete INDEX`
* `INDEX` refers to the index number of the task shown in the
  task list when `list` command is entered.

Example: 
* `delete 1` deletes the first task in the list.

Expected outcome:
```
Noted. I've removed this task:
  [T][X] return book
Now you have 2 task(s) in the list.
```

### Filters out relevant tasks: `find`
Shows the tasks which matches the keyword.

Format: `find KEYWORD`
* `KEYWORD` needs to be contained in the task description.
* Partial words will not match the full word in the description.
* `boo` will not match tasks with `book` in their description.

Example: 
* `find book` finds tasks with 'book' in their description.

Expected outcome:
```
Here are the matching tasks in your task list:
  1. [T][X] return book
  2. [D][X] borrow book (by: Tue, 08 Jun 2021 2359)
```

### Add a todo task: `todo`
Adds a task with no deadline/time.

Format: `todo TASK DESCRIPTION`

Example: 
* `todo read book` adds a todo task with description 
'read book'.

Expected outcome:
```
Got it. I've added this task:
  [T][ ] read book
```

### Add a deadline task: `deadline`
Adds a deadline.

Format: `deadline TASK DESCRIPTION /by DATE AND TIME`
* `DATE AND TIME` has to in this format: `DD/MM/YYYY HHmm`.
* `HHmm` is in 24 hours format.

Example: 
* `deadline return book /by 06/06/2021 2359` 
adds a deadline task with description 'read book' and 
date and time '06/06/2021 2359'.

Expected outcome:
```
Got it. I've added this task:
  [D][ ] return book (by: Sun, 06 Jun 2021 2359)
Now you have 2 task(s) in the list.
```

### Add an event task: `event`
Adds an event.

Format: `event TASK DESCRIPTION /at DATE AND TIME`
* `DATE AND TIME` has to in this format: `DD/MM/YYYY HHmm`.
* `HHmm` is in 24 hours format.

Example:
* `event project meeting /at 06/06/2021 2359`
  adds an event with description 'project meeting' and
  date and time '06/06/2021 2359'.

Expected outcome:
```
Got it. I've added this task:
  [E][ ] project meeting (at: Sun, 06 Jun 2021 2359)
Now you have 2 task(s) in the list.
```

### Edits a task: `edit`
Edits the details of a task.

Format: `edit INDEX description/time /to DETAILS`
* `INDEX` refers to the index number of the task shown in the
  task list when `list` command is entered.

Examples: 
* `edit 1 description /to return book`
changes the description of the first task to 'return book'.
* `edit 2 time /to 08/06/2021 1200` changes the
  deadline/time of the second task to '08/06/2021 1200'

Expected outcome:
```
Got it. I've edited this task:
  [D][ ] return book (by: Tue, 08 Jun 2021 2359)
```

```
Got it. I've edited this task:
  [E][ ] project meeting (at: Tue, 08 Jun 2021 1200)
```

### Exits Duke: `bye`
Exits the GUI.

Format: `bye`

Expected outcome:
```
Bye. Hope to see you again!
```