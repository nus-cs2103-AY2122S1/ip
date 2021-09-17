# Duke How-To

Welcome to Duke! 
This application presents a interactive user interface to create, tag and list our tasks.

## Key Features

1. **Task Management** - Add, delete and complete tasks of the following categories:
 - Todo
 - Deadline
 - Event

2. **Search via** (task name)

 - Existing tasks can be filtered via their task names or their tagged categories. 

3. **List** tasks

 - Provides an overview of all tasks and their current status.

## Usage

### Task Management
- `todo` - Adds a vanilla task

Usage: `todo TASKNAME`

Creates a task of name `TASKNAME`.

- `deadline` - Adds a task with a deadline

Usage: `deadline TASKNAME /by DEADLINE`

Remarks: `DEADLINE` if placed in the YYYY-MM-DD format translates to the alphabetical version.

- `event` - Adds a task with specific event details

Creates a task of name `TASKNAME` by `DEADLINE`.

Usage: `event TASKNAME /at EVENT_DETAILS`

Remarks: `EVENT_DETAILS` if placed in the YYYY-MM-DD format translates to the alphabetical version.

Creates a task of name `TASKNAME` at `EVENT_DETAILS`.

- `delete` - Removes the task

Usage: `delete 2`

Second item on the list is deleted, displays an error if second item does not exists.

- `done` - Mark task as completed

Usage: `done 2`

Second item on list is marked as completed, displays an error if it is already completed.

- `delete` - Deletes the task from our task manager.

Usage: `delete 2`

Expected outcome: Second item on the list is deleted, displays an error if second item does not exists.

### **Search via** (task name)

- `find` - Find task containing a specific keyword.

Usage: `find KEYWORD`

Expected outcome: Displays all the task that include `KEYWORD` in their task name.

### List 

- `list` - Lists all tasks.

Usage: `list`

Expected outcome: Displays all the task alongside their completion status.







