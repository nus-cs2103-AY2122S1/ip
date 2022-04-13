# User Guide
> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes/))

Dino is a desktop application for managing Tasks. 
The main mode of input is via Command Line Interface (CLI) with a Graphical User Interface (GUI) to show the output.

More importantly, Dino frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

## Quick start

1. Download the app from [here](https://github.com/Nature711/ip).
2. Double-click it.
3. Add your tasks.
4. Let it manage your tasks for you 😉

## Features 

### Adding a task

Adds a specific type of task to your task list. 


Each task has the following attributes and is displayed in the format `type | status | description | date (optional)`.

| Attribute | Possible values | Notes |
| --- | --- | --- |
| type | `todo`, `deadline`, `event` |When adding a task, you must prefix the task description with one of the type tags.
| status | `done`, `not done yet`| Tasks that are done are marked with a `X`. By default, when a new task is added, its status is `not done yet`.
| description | [any non-empty string] | The description of a task can be of any length.
| date (for `deadline` and `event` only) | date in the format `YYYY-MM-DD` | This attribute is required only for task of type `deadline` and `event` which should be prefixed with `by` and `at`, respectively.

#### Add a todo: `todo`
- Format: `todo TASK`
- Example: `todo buy gift`

#### Add a deadline: `deadline`
- Format: `deadline TASK /by DATE`
- Example: `deadline CS2103T ip /by 2021-09-17`

#### Add an event: `event`
- Format: `event TASK /at DATE`
- Example: `event dating /at 2021-09-18`

### Listing all tasks : `list`
Shows a list of all tasks in the task list.
- Format: `list`

### Marking a task as done: `done`
Marks the task at the specified index as done.
- Format: `done INDEX`
- Example: `done 1`
- Note: A valid index should be within the range [1, length of task list].

### Deleting a task : `delete`
Deletes the task at the specified index from the task list.
- Format: `delete INDEX`
- Example: `delete 1`
- Note: A valid index should be within the range [1, length of task list].

### Finding a task: `find`
Finds the task(s) from the task list that contain the given keyword(s).
- Format: `find KEYWORD1, KEYWORD2, ...`
- Example: `find eat, sleep`
- Note: Multiple keywords can be supplied, which are separated by commas.

### Editing a task: `edit`
Edits the content of the task at the specified index, without deleting that task.
- Format: `edit INDEX /NEW TASK`
- Example: `edit 3/ event dating /at 2021-09-20`
- Note: The new task should follow exactly the format required for specifying a new task of that type.

### Exiting the app: `bye`
Exits the app and saves the changes made to the task list (if any) to storage.
- Format: `bye`

### Saving of data
The data from the task list is automatically saved in the hard disk after each round of complete execution of the app (i.e., the execution is terminated by a `bye` command instead of any other unexpected error). 
There is no need to manually save the data.

The data is saved as a txt file named `dino.txt` in the data folder of your JAR file. Although this means it is possible to manipulate the data directly by editing that file, it is generally not recommended to do so, as the edition may not follow the required format, which may cause parsing error when the data is retrieved the next time the app launches.

## Command summary

| Command | Format | 
| --- | --- |
| add a todo | `todo [description]`|
| add a deadline | `deadline [description] /by [date]`|
| add an event | `event [description]/ at [date]`|
| list out all the tasks  | `list`|
| mark a task as done | `done [index]`|
| delete a task | `delete [index]`|
| edit a task | `edit [index] / [new task]`|
| exit the app | `bye`|

---
### **_Enjoy your time with Dino~_**  🤗