# User Guide
- [User Guide](#user-guide)
    * [Quick Start](#quick-start)
    * [Features](#features)
        + [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
        + [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
        + [Adding an Event Task: `event`](#adding-an-event-task-event)
        + [Listing all tasks: `list`](#listing-all-tasks-list)
        + [Marking a task as done: `done`](#marking-a-task-as-done-done)
        + [Deleting a task: `delete`](#deleting-a-task-delete)
        + [Finding a task by description: `find`](#finding-a-task-by-description-find)
        + [Exiting the program: `bye`](#exiting-the-program-bye)
        + [Saving the data](#saving-the-data)
    * [Command Summary](#command-summary)


> “It's not enough to be busy, so are the ants. The question is, what are we busy about?” - Henry David Thoreau ([source](https://www.goodreads.com/quotes/7273089-it-s-not-enough-to-be-busy-so-are-the-ants))

Laa-Laa Bot is a **desktop app to help you with your task management**, optimized for use via a Command Line Interface (CLI) while having the benefits of a Graphical User Interface (GUI). If you can type fast, Laa-Laa Bot is ~~FAST~~ ***SUPER*** *FAST* to use.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `.jar` file from [here](https://github.com/tianyue58/ip/releases/tag/v0.3).
3. Copy the file to an empty folder you want to use as the home folder for your task list.
4. Navigate to that folder and type command java -jar duke.jar or double click duke.jar to run it. The GUI should be similar to below.

<img src="https://github.com/tianyue58/ip/blob/master/docs/welcomeScreen.png?raw=true" style="zoom: 33%;" />

5. Type the command in the command box and press Enter to execute it. You can try the follow example commands:
    - `todo wash dishes`: Adds a new todo task named `wash dishes`.
    - `done 1`: Marks the first item on the list as done.
    - `delete 1`: Deletes the first item on the list.
    - `bye`: Exits the app.
6. Refer to the [Features](#Features) below for details of each command.



## Features

### Adding a Todo Task: `todo`

Adds a todo task to the task list.

Format: `todo <description>`

Shortcut: `t <description>`

Example:

- `todo meditation`
- `t meditation`

Example Expected Outcome:

- `Got it. I've added this task:`

  `[T][] meditation`

  `Now you have 1 task in the list.`


### Adding a Deadline Task: `deadline`

Adds a deadline task to the task list with a date.

Format: `deadline <description> /by yyyy-MM-dd`

Example:

- `deadline submit SEP application /by 2021-10-02`
- `d submit SEP application /by 2021-10-02`

Example Expected Outcome:

- `Got it. I've added this task:`

  `[D][] submit SEP application (by: Oct 2 2021)`
  
  `Now you have 2 tasks in the list.`



### Adding an Event Task: `event`

Adds an event task to the task list with a date.

Format: `event <description> /at yyyy-MM-dd`

Example:

- `event formal dinner /at 2021-09-14`
- `e formal dinner /at 2021-09-14`

Example Expected Outcome:

- `Got it. I've added this task:`

  `[E][] formal dinner (at: Sep 14 2021)`

  `Now you have 3 tasks in the list`.



### Listing all tasks: `list`

Displays a list of upcoming tasks.

Format: `list`

Example Expected Outcome:

- `Here are the tasks in your list:`

  `1. [T][] meditation`

  `2. [D][] submit SEP application (by: Oct 2 2021)`

  `3. [E][] formal dinner (at: Sep 14 2021)`



### Marking a task as done: `done`

Marks a task as done with the specified task number as shown in the *full* task list.

Format: `done <task_index>`

Example:

- `done 1` marks the first task on the list as done.

Example Expected Outcome:

- `Nice! I've marked this task as done:`

  `[T][X] meditation`



### Deleting a task: `delete`

Deletes a task from the task list with the specified task number as shown in the *full* task list.

Format: `delete <task_index>`

Example:

- `delete 2` deletes the second task on the list.

Example Expected Outcome:

- `Noted. I've removed this task:`

  `[D][] submit SEP application (by: Oct 2 2021)`

   `Now you have 2 tasks in the list.`



### Finding a task by description: `find`

Finds task(s) matching the specified keyword.

- All task description with the matching sequence of characters will be returned. e.g. `di` will match `meditaion` and `dinner`.

Format: `find <keyword>`

Example:

- `find dinner`

<img src="https://github.com/tianyue58/ip/blob/master/docs/findScreen.png?raw=true" alt="FindGUI" style="zoom:33%;" />


### Exiting the program: `bye`

Exits the program and automatically save the data.

Format: `bye`


### Saving the data

Data in the task list is saved in the hard disk at `[JAR file location]/data/Duke.txt`  automatically after any command that modifies the list (e.g. `todo`, `done`, `delete`).

- To transfer your data to another computer: install the app in the other computer and overwrite the empty `Duke.txt` file with the file containing your previous data.

## Command Summary

| Action       | Format                              | Example                                         |
| ------------ | ----------------------------------- | ----------------------------------------------- |
| Add Todo     | `todo <description>`                | `todo meditation`                               |
| Add Deadline | `deadline <description> /by <date>` | `deadline submit SEP application /by 2021-10-02`|
| Add Event    | `event <description /at <date>`     | `event formal dinner /at 2021-09-14`            |
| Set as Done  | `done <task-index>`                 | `done 1`                                        |
| Delete       | `delete <task_index>`               | `delete 1`                                      |
| Find         | `find <keyword>`                    | `find dinner`                                   |
| List         | `list`                              |                                                 |
| Exit         | `bye`                               |                                                 |
                                                                                 |

