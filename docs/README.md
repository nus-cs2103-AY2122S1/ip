User Guide
---

Duke is  a **desktop app for keep tracking of tasks ranging from task types such as todo, event and deadline.**

## Features 

### Listing All Tasks: `list`

Shows a list of all tasks added by the user.

### Adding Todo Task: `todo`

Adds a todo task with the description of task.

Format: `todo TASK_DESCRIPTION`

### Adding Event Task: `event`

Adds an event task with the task description, and the date that event is held.

Format: `event TASK_DESCRIPTION /at dd/MM/YYYY`

### Adding Deadline Task: `deadline`

Adds a deadline task with the task description, and the date of the deadline.

Format: `deadline TASK_DESCRIPTION /by dd/MM/YYYY` 

### Deleting tasks: `delete`

Delete task/s by task number/s which is based on the order of task added.

Format: `delete TASK_NUMBER..`

### Marking tasks as done: `done`

Mark task/s as done by task number/s which is based on the order of task added.

Format: `done TASK_NUMBER..`

### Saving the data
By default, Duke automatically saved and store the tasks at `/data/Duke.txt`.

### Exiting: `bye`

Exits from the app.

## Usage

Type the command in the command box and press Enter to execute it. e.g. typing

### Examples
**`list`** : Lists all tasks.

Expected Output: 

![](images/list%20example.JPG)

`todo CS2100 Assignment`: Adds a CS2100 Assignment as todo task.

Expected Output: 

![](images/todo%20example.JPG)

`event Meeting with boss /at 11/01/2022`: Adds a meeting with boss event task at 11/01/2022.

Expected Output: 

![](images/event%20example.JPG)      

`deadline CS2103T IP /by 17/09/2021`: Adds CS2103T IP with deadline of 17/09/2021 as a deadline task.

Expected Output: 

![](images/deadline%20example.JPG)      

`done 1 2 3`: Marks 1st, 2nd and 3rd task as done.

Expected Output: 


![](images/done%20example.JPG)    

`delete 1 2 3`: Deletes 1st, 2nd and 3rd task from your list.

Expected Output: 

![](images/delete%20example.JPG)      
