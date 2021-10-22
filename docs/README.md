# User Guide

CAPTain is a desktop app for managing tasks, optimized for us via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you are someone who has many things to do everyday, CAPTain can assist you to capture and organise what you need to do into a list.

## Features 

### Adding a task

CAPTain offers you 3 types of tasks you can add into your task list.
1. **Todo** - a todo task
2. **Deadline** - a task with deadline
3. **Event** - a task to do on a specified date

### Deleting a task
Of course, CAPTain can remove a task from your task list as you wish.

### Listing all tasks

CAPTain will present all tasks in your task list.

### Marking a task as done

CAPTain will mark the specified task as completed.

### Finding a task

CAPTain is able to find tasks based on the keywords you provide.

### Exiting the app

Once you are satisfied with managing your task with CAPTain, you can exit the program.

## Usage

### `add` - adding a task
Adds a task into the task list. The type of task added depends on the command format.

Format:
- ```add TASK``` to add a Todo
- ```add TASK /by DATE``` to add a Deadline
- ```add TASK /at DATE``` to add an Event

Example of usage:
- ```add math homework```
- ```add proposal submision /by this sun```
- ```add night cycling /at marina bay```

CAPTain will notify you that your task have been added successfully.
The type and status of the task will be displayed as well.

```
Got it. I've added this task:
  [T][ ] math homework
Now you have 1 task in the list.
```

### `delete` - deleting a task
Deletes the specified task from the task list.

Format:
```delete INDEX```

Example of usage:
```delete 1```

CAPTain will notify you that your task have been deleted successfully.

```
Noted. I've removed this task:
  [T][ ] math homework
Now you have 0 task in the list.
```

### `list` - listing all tasks
Lists all the tasks from the task list.

Format: `list`

CAPTain will display all your tasks as an ordered list.

```
Here are a list of tasks you have:
1. [T][ ] math homework
2. [D][ ] proposal submission (by: this sun)
3. [E][ ] night cycling (at: marina bay)
```

### `done` - marking a task as done
Marks a task as done.

Format:
```done INDEX```

Example of usage:
```done 1```

CAPTain will mark the specified task as complete.

```
Good job! One less task for you!
  [T][X] math homework
```

### `find` - finding a task
Finds a list of task that contains the specified keywords.\
The user can input more than one keyword.

Format:
```find KEYWORD [MORE_KEYWORDS]```

- Each keywords are to be separated by a comma and a whitespace i.e. ", ".
- The search is case-insensitive. e.g. `sunday` will math `Sunday`

Example of usage:
```find homework, night```

CAPTain will show you a list of task containing the keywords you specified.

```
Here's all that I can find with the keywords:
1. [T][ ] math homework
2. [E][ ] night cycling (at: marina bay)
```

### `exit` - exiting the app
Exits the program

Format:
```exit```
