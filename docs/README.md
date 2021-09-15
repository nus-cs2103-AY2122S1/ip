# Pingu

Pingu helps you organize your tasks, using local storage to keep your files saved between sessions, making task management *easy* and *effortless*

![Welcome](https://github.com/lwj1711/ip/blob/master/docs/start.png?raw=true)

## Features 
- **Task Creation**
  
  Three different types of tasks:
    1. Todos: Tasks without a specific date
    2. Deadlines: Tasks to do **by** a specific date/time
    3. Events: Tasks to do **at** a specific date/time
- **Display Current List of Tasks**
- **Mark Task As Done**
- **Task Deletion**
- **Find Tasks Matching Keywords**

## Commands
### Command Format
- Items in square brackets are to be provided by user
    - e.g. for command `todo [taskname]`, a user may enter `todo homework` 


Function            | Command                  | Remarks   
--------------------|--------------------------|----------------------------------
Create task         | `todo [task name]` <br> `deadline [task name] /by [date] ` <br> `event [task name] /at [date]`| Format of date must be YYYY-MM-DD <br> You can optionally include time in `[date]`
Display all tasks  | `list`                   | Displays numbered list of tasks, and their completion status
Mark task as done   | `done [task number]`     | `[task number]` is according to that shown in list
Delete task         | `delete [task number]`   | `[task number]` is according to that shown in list
Search for keyword  | `find [keyword]`         | Returns every task that contains `[keyword]`
Show commands       | `help`                   | ---
Exit the program    | `bye`                    | ---

## Usage

### Task Creation 
`todo [task name]` - Adds a task without date or time

`deadline [task name] /by [date]` - Adds a task to be done **by** a date (and time)

`event [task name] /at [date]` - Adds a task to be done **at** a date (and time)

![taskCreation](https://github.com/lwj1711/ip/blob/master/docs/taskCreation.png?raw=true)

### Display Tasks
`list` - Displays a numbered list of every single task

![list](https://github.com/lwj1711/ip/blob/master/docs/list.png?raw=true)

### Mark Task as Done
`done [task number]` - Marks a certain task as done 

![mark](https://github.com/lwj1711/ip/blob/master/docs/mark.png?raw=true)

### Delete Task
`delete [task number]` - Deletes a certain task

![delete](https://github.com/lwj1711/ip/blob/master/docs/delete.png?raw=true)

### Find Tasks
`find [keyword]` - Returns every task containing the keyword(s)

![find](https://github.com/lwj1711/ip/blob/master/docs/find.png?raw=true)

### Show Command help
`help` - Provides a brief guide for the commands

![help](https://github.com/lwj1711/ip/blob/master/docs/help.png?raw=true)
