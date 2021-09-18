# User Guide

## Features 

### Adding tasks: `todo`/`deadline`/`event`

Adds the task of specified task type to the user's task list.

Format:
1. `todo DESCRIPTION`
2. `deadline DESCRIPTION /by DATE_TIME`
3. `event DESCRIPTION /by DATE_TIME`

* Adds a task with the specified `DESCRIPTION` and `DATE_TIME`.
* DATE must be in the format `yyyy-mm-dd`.
* TIME must be in the format `hh:mm`.

Examples:
* `todo run` adds `[T][] run` into the task list.
* `deadline homework /by 2021-11-11 18:00` adds `[D][] homework (by: Nov 11 2021 06:00 PM)` into the task list.
* `event birthday /at 2021-12-12 00:00` adds `[E][] birthday (at: Dec 12 2021 12:00 AM)` into the task list.  

&nbsp;  
&nbsp;  

### List tasks: `list`

Lists all the tasks in the user's task list.

Format: `list`

Example:
![list example](https://github.com/Moley456/ip/tree/master/docs/list.png)  
A `list` command with some sample data.


### Mark a task as done: `done`

Marks a task in the task list as done.

Format: `done INDEX`
* Marks the task at the specified `INDEX` as done.
* The `INDEX` refers to the task number in the task list.
* `INDEX` must be a positive integer e.g. 1 ,2, 3,...

Example:
* `done 2` marks the second task in the task list as done.

&nbsp;  
&nbsp;  

### Find tasks by keyword: find

Finds all tasks that description, date or time matches the given keywords exactly.

Format: `find KEYWORDS`

* The search is case-insensitive. e.g. `run` will match `RuN`.
* Tasks can be matched partially e.g. `Oct` will match `October`.
* Order of `KEYWORDS` matter. e.g. `birthday bob's` will not match `bob's birthday`.

Examples:

* `find run` returns `[T][] run` and `[E][] 2.4km run (at: Nov 11 2021 06:00 PM)`.
* `find oct` returns `[D][] presentation slides (by: Oct 10 2021 06:00 PM)`.

&nbsp;  
&nbsp;  

### Delete a task: `delete`

Deletes a task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The `INDEX` refers to the task number in the task list.
* `INDEX` must be a positive integer e.g. 1 ,2, 3,...

Example:
* `delete 1` deletes the first task in the task list.

&nbsp;  
&nbsp;  

### Update a task: `update`

Updates a task's description or/and date and time.

Format: `update UPDATE_TYPE INDEX [DESCRIPTION] [DATE_TIME]`

* `UPDATE_TYPE` must be of three different types:
  * `dt` updates the date and time of the specified task.
  * `desc` updates the description of the specified task.
  * `full` updates description, date and time of the specified task.
* Updates the task at the specified `INDEX`.
* The `INDEX` refers to the task number in the task list.
* `INDEX` must be a positive integer e.g. 1 ,2, 3,... 
* `[DESCRIPTION]` must be entered when using `desc` and `full` update types.
* `[DATE_TIME]` must be entered when using `dt` and `full` update types.

Examples:
* `update desc 1 sleep` updates the first task `[T][] run` to `[T][] sleep`.
* `update dt 2 2021-10-10 00:00` updates the second task `[D][] homework (by: Jan 10 2021 06:00 PM)`  
to `[D][] homework (by: Oct 10 2021 12:00 AM)`.

&nbsp;  
&nbsp;  

### Exit the application: `bye`

Exits the application.

&nbsp;  
&nbsp;  

Format: `bye`

&nbsp;  
&nbsp;  

### Saving the data

Duke's task list data is saved in the hard disk automatically after any command that changes the data.  
There is no need to save manually.

&nbsp;  
&nbsp;  

### Editing the data file.

Duke's task list data is saved as a .txt file `[JAR file location]/data/data.txt`.  
Users are encouraged *NOT* to update the data file unless they are sure of the valid format. If changes  
to the data file makes the format invalid, Duke will not be able to run until the format is fixed.
