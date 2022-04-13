# User Guide


## Features Overview 
1. Add `todo` task 
2. Add `event` task
3. Add `deadline` task 
4. Mark tasks as done 
5. Delete tasks from `TaskList`
6. View your task list
7. Find a particular task from `TaskList`
8. C-BetterSearch
9. C-DetectDuplicates


## Details of Features 

### 1. Add `todo` Task

This feature allows you to categorize a task as `todo` and add it to your `TaskList`. 
However, duplicate todo task is not allowed.

### 2. Add `event` Task

This feature allows you to categorize a task as `event` and add it to your `TaskList`. You need to specify details 
such as date and time for this task. However, duplicate event task is not allowed.


### 3. Add `deadline` Task

This feature allows you to categorize a task as `dealine` and add it to your `TaskList`. You need to specify details
such as date and time for this task. However, duplicate deadline task is not allowed.


### 4. Mark Task as Done

This feature allows you to mark various tasks in your `TaskList` as done. 

### 5. Delete a Task

This feature allows you to delete a task from the `TaskList`.

### 6. View `TaskList`

This feature allows you to view your current `TaskList`. 

### 7. Find tasks in `TaskList`

This feature allows you to find tasks in `TaskList` that match a given search key.

### 8. C-BetterSearch

More flexibility in search e.g., find items even if the keyword matches the item only partially.

### 9. C-DetectDuplicates

Add the ability to recognize and deal with duplicate items. e.g., the same task cannot be added multiple times. 

## Usage

### 1. `todo <task name>` - Add a to-do task

Example of usage: `todo complete IP`

### 2. `event <task name> /at <YYYY-MM-DD>T<HH:MM>` - Add an event task

Example of usage: `event submit IP project /at 2021-09-17T23:59`

### 3. `event <task name> /at <YYYY-MM-DD>T<HH:MM>` - Add a deadline task

Example of usage: `deadline submit IP project /by 2021-09-17T23:59`

### 4. `done <task index>` - Marks a task in the task list done 

Example of usage: `done 1`

### 5. `delete <task index>` - Deletes a task in the task list 

Example of usage: `delete 1`

### 6. `list` - View your current task list

Example of usage: `list`

### 7. `find` - Find tasks that match a search key in your current task list

Example of usage: `find <search key>`