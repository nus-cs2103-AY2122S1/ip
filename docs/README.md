# Duke How-To

Welcome to Duke! Try it out [here](https://github.com/ykwei7/ip/releases/download/A-Release/duke.jar)! <br/>
This application presents a interactive user interface to create, tag and list our tasks.

## Key Features

1. **Task Management** - Add, delete and complete tasks of the following categories:
 - Todo
 - Deadline
 - Event

2. **Search via** (task name)
 - Existing tasks can be filtered via their task names or their tagged categories. 
 
3. **List** tasks
  -Provides an overview of all tasks and their current status.

## Usage

### Task Management

- #### `todo`- Adds a vanilla task

Usage: `todo TASKNAME`

Creates a task of name `TASKNAME`.

<hr />

- #### `deadline` - Adds a task with a deadline

Usage: `deadline TASKNAME /by DEADLINE`

Remarks: `DEADLINE` if placed in the YYYY-MM-DD format translates to the alphabetical version.

Creates a task of name `TASKNAME` by `DEADLINE`.

<hr />

- #### `event` - Adds a task with specific event details

Usage: `event TASKNAME /at EVENT_DETAILS`

Remarks: `EVENT_DETAILS` if placed in the YYYY-MM-DD format translates to the alphabetical version.

Creates a task of name `TASKNAME` at `EVENT_DETAILS`.

<hr />

- ####  `delete` - Removes the task

Usage: `delete 2`

Second item on the list is deleted, displays an error if second item does not exists.

<hr />

- #### `done` - Mark task as completed

Usage: `done 2`

Second item on list is marked as completed, displays an error if it is already completed.

<hr />

- ####  `delete` - Deletes the task 

Usage: `delete 2`

Second item on the list is deleted, displays an error if second item does not exists.

<hr />

### **Search via** (task name)

- #### `find` - Find task containing a specific keyword

Usage: `find KEYWORD`

Displays all the task that include `KEYWORD` in their task name.

<hr />

### List 

- #### `list` - Lists all tasks

Usage: `list`

Displays all the task alongside their completion status.







