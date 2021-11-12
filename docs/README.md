# User Guide

DukeBot is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having
 the benefits of a Graphical User Intrerface (GUI). If you can type fast, DukeBot can get your task management done
 faster than tradition GUI apps. 

![Image of GUI](https://chengseong.github.io/ip/Ui.png)
## Features 

**Notes about the command format:**
* Commands are case sensitive.
  e.g. `todo` is a proper command, while `Todo` will return an error.

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `todo TODO`, `TODO` is a parameter which can be used as `todo read book`.

* All date and time input must be in the format `dd/mm/yyyy`.
  e.g. `20/9/2021 1800` 

* Currently, the only `FREQUENCY` supported are `weekly` and `monthly`.
  e.g. inputting `annually` will return an error.

* Items in square brackets are optional.
  e.g. `DATE \[FREQUENCY\]` can be used as `20/9/2021 1800 weekly` or as `20/9/2021 1800`.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Adding a todo: `todo`

Adds a todo the the task list.

Format : `todo TODO`

Examples:
* `todo read book`
* `todo finish chapter 1 of lectures`

### Adding a deadline: `deadline` 

Adds a deadline to the task list.

Format: `deadline TASK /by DATE TIME [FREQUENCY]` 

* `FREQUENCY` defaults to `once` if there is no input for the parameter.

Examples:
* `deadline return library book /by 20/9/2021 1800`
* `deadline finish weekly quiz /by 21/9/2021 2359 weekly`

### Adding an event: `event` 

Adds an event to the task list.

Format: `event TASK /at DATE TIME [FREQUENCY]` 

* `FREQUENCY` defaults to `once` if there is no input for the parameter.

Examples:
* `event CS2106 midterms /at 29/9/2021 1345`
* `event monthly test /at 21/9/2021 2359 monthly`

### Completing an task: `done`

Completes a task in the task list.

Format: `done INDEX`

* Index must be valid. e.g. if the task list has 2 tasks, inputting `done 3` will return an error.

Example: 
* `done 2`

### Deleting a task : `delete`

Deletes a task from the task list.

Format : `delete INDEX`

* Index must be valid. e.g. if the task list has 2 tasks, inputting `delete 3` will return an error.

Example:
* `delete 3`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating tasks by task: `find`

Finds tasks which contains the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive. e.g. `Read` will not match `read`.
* Only the task is searched. e.g. `find event` will not give a list of all the events in the task list.
* Partial matching is supported. e.g. `Re` will match `Read`.

Examples:
* `find read` returns `todo read book`
* `find re` returns both `todo read book` and `todo return book`

### Saving the task list: `bye`

Saves the task list to `[JAR file location]/data/tasks.txt` and closes the window.

Format: `bye`

* If the data folder does not exist, it will create a data folder.
* If the `tasks.txt` file does not exist in the data folder, it will create a `tasks.txt` file.
