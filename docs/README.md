# User Guide

- User Guide
  - [Quick Start](https://github.com/Xiaoyunnn/ip/tree/master/docs#quick-start)
  - Features
    - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
    - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
    - [Adding an Event Task: `event`](#adding-an-event-task-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking a task as done: `done`](#marking-a-task-as-done-done)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Undoing a command: `undo`](#undoing-a-command-undo)
    - [Finding a task by keyword: `find`](#finding-a-task-by-keyword-find)
    - [Snooze tasks: `snooze`](#snooze-tasks-snooze)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](https://github.com/Xiaoyunnn/ip/tree/master/docs#saving-the-data)
  - [Command Summary](https://github.com/Xiaoyunnn/ip/tree/master/docs#command-summary)

> "I'm afraid for the calendar. Its days are numbered." - Dad jokes ([source](https://www.countryliving.com/life/a27452412/best-dad-jokes/))

Duke is a **chat bot for managing your upcoming tasks**, chat with Duke to get your life sorted out! 

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` file from [here](https://github.com/Xiaoyunnn/ip/releases/tag/V1.1).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![img](/DefaultGUI.png)

1. Type the command in the command box and press Enter to execute it. You can try the follow example commands:
   - `list`: Shows the list of tasks you have added.
   - `todo iP `: Adds a new todo task named `iP`.
   - `done 1`: Marks the first item on the list as done.
   - `delete 1`: Deletes the first item on the list.
   - `Bye!`: Exits the app.
2. Refer to the [Features](#Features) below for details of each command.

## Features

### Adding a Todo Task: `todo`

Adds a todo task to the task list.

Format: `todo <description>`

Example:

- `todo iP`

Example Expected Outcome:

- `added: [T][] iP`

  `You have 4 task(s) to go! (]><)]`

  - `[T]` indicates todo task.
  - `[]` indicates that this task is not completed yet.

### Adding a Deadline Task: `deadline`

Adds a deadline task to the task list with date and time in 24-hour format.

Format: `deadline <description> /by dd/MM/yyyy HHmm`

Example:

- `deadline iP /by 17/09/2021 2359`

Example Expected Outcome:

- `added: [D][] iP (by: 17 Sep 2021 2359)` 

  `You have 5 task(s) to go! (]><)]`

### Adding an Event Task: `event`

Adds an event task to the task list with date and time in 24-hour format.

Format: `event <description> /at dd/MM/yyyy HHmm`

Example:

- `event iP submission /at 17/09/2021 2359`

Example Expected Outcome:

- `added: [E][] iP submission (by: 17 Sep 2021 2359)`

  `You have 6 task(s) to go! (]><)]`

### Listing all tasks: `list`

Displays a list of upcoming tasks.

Format: `list`

Example Expected Outcome:

![list GUI](/listGUI.png)

### Marking a task as done: `done`

Marks a task as done with the specified task number as shown in the *full* task list when you use `list` command.

Format: `done <task_number>`

Example:

- `list` followed by `done 1` marks the first task on the list as done.

Example Expected Outcome:

- `(@_@); What a rarity! This task has been marked as done:`

  `[D][X] hello (by 23 Sep 2021 2359)`

### Deleting a task: `delete`

Deletes a task from the task list with the specified task number as shown in the *full* task list when you use `list` command.

Format: `delete <task_number>`

Example:

- `list` followed by `delete 1` deletes the first task on the list as done.

Example Expected Outcome:

- `(~_~) Ok... This task has been deleted: `

  `deleted: [E][X] 1234 (at: 23 Sep 2021 2350)`

  `You have 4 task(s) to go! (]><)]`

### Undoing a command: `undo`

Undo the last command that has modified the task list. This includes commands:

* `todo`
* `event`
* `deadline`
* `delete`
* `done`
* `snooze`

Format: `undo`

Expected Outcome:

* Previous Command: `done 1`
* `Done Command has been undone!`

### Finding a task by keyword: `find`

Finds task(s) matching the specified description.

- All task description with the matching sequence of characters will be returned. e.g. `p` will match `iP` and `polycarbonate plate`.

Format: `find <description>`

Example:

- `find submission`

![FindGUI](/findGUI.png)

### Snooze tasks: `snooze`

Delays or changes the dates for Event tasks and Deadline tasks.

Format: `snooze <task_number> dd/MM/yyyy HHmm`

Example: `snooze 3 18/09/2021 0000`

Example Expected Outcome:

- `Ok... [D][] iP (by: 18 Sep 2021 0000) has been snoozed OwO`

### Exiting the program: `bye`

Exits the program.

Format: `Bye!`

### Saving the data

Data in the task list is automatically saved in the hard disk at `[JAR file location]/data/tasks.txt` after any command that modifies the list (e.g. `todo`, `done`, `delete`) is called.

## Command Summary



| Action       | Format                                   | Example                          |
| ------------ | ---------------------------------------- | -------------------------------- |
| Add Todo     | `todo <description>`                     | `todo read book`                 |
| Add Deadline | `deadline <description> /by <date time>` | `deadline iP/by 17/09/2021 2359` |
| Add Event    | `event <description /at <date time>`     | `event iP /at 17/09/2021 2359`   |
| Mark Done    | `done <task_num>`                        | `done 1`                         |
| Delete       | `delete <task_num>`                      | `delete 1`                       |
| Find         | `find <description>`                     | `find meeting`                   |
| Snooze       | `snooze <task_number> <date time>`       | `snooze 3 18/09/2021 0000`       |
| List         | `list`                                   |                                  |
| Undo         | `undo`                                   |                                  |
| Exit         | `Bye!`                                   |                                  |