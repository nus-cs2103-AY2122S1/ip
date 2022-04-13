# User Guide


Dog-and-Cat (DaC) consists of 2 columns:
* Left column is the chat screen
  * Input your instructions here
* Right column is your list of tasks
  * Displays all the tasks in the task list unless otherwise stated
in the feature description

Upon opening, DaC reads in from a file named 'taskList.txt' to
retrieve previously stored tasks and initializes the task list with
those tasks.
DaC would automatically create the file if it does not exist.

## Features 

#### Notes:
* (): brackets mean that you are free to write anything you want,
but it must not be empty and must follow the input format if 
specified.
* []: square brackets mean that these parts are optional.
* All commands must follow the examples given aside from text in
().
* Commonly used input formats:
  * YYYY-MM-DD: input is in the form of (year)-(month)-(days)
  and each letter represents a numerical value. Note that
  the dashes are required.
  * HHmm: input is in the 24hr format where HH is the hour and
  mm is the minutes


### 1. Adding tasks

Adds a task to the task list.

 `todo`: Adds a ToDo task.

Example of usage: `todo (task description)`

 `deadline`: Adds a Deadline task.

Example of usage: `deadline (task description) /by 
(task deadline in YYYY-MM-DD HHmm format)`

`event`: Adds an Event task.

Example of usage: `event (task description) /at
(task timing in YYYY-MM-DD HHmm-HHmm format)`

### 2. Completing a task

`done`: Marks a task in the task list as done.

Example of usage: `done (task number)`

### 3. Deleting a task

`delete`: Deletes a task in the task list.

Example of usage: `delete (task number)`

### 4. List tasks

`list`: Lists all the tasks in the task list.

Example of usage: `list`

### 5. Filter tasks by date

`date`: Lists all the tasks in the task list 
that occur on a given date.

Example of usage: `date (date in YYYY-MM-DD format)`

### 6. Filter tasks by keyword

`find`: Lists all the tasks in the task list 
that contain the given keyword in their task description.

Example of usage: `date (date in YYYY-MM-DD format)`

### 7. Sort the tasks

`sort`: Sorts the task in the task list by date.
Adding reverse after sort reverse the sort order.
Tasks that are completed are arranged before incomplete tasks.

Example of usage: `date [reverse]`

### 8. Save and exit the program

`bye`: Saves the tasks in the task list in a file named 'taskList.txt'
before exiting the program.

Example of usage: `bye`

**Warning:** Do not edit 'taskList.txt' as DaC reads in the file 
in a specific order and format.
**Warning:** If you exit the program by other means other than
'bye' (e.g. clicking the close button), the tasks inputted will
not be saved.


__*Hope you enjoy using DaC!!!*__

