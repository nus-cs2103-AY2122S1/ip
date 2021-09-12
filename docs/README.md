# User Guide

> “In order to seek one’s own direction, one must simplify the mechanics of ordinary, everyday life.” - Plato ([source](https://www.minimalray.com/blog/minimalist-quotes))

DukeBai is a **desktop app for managing your upcoming tasks**, optimized for use via a Command Line Interface (CLI) while having the benefits of a Graphical User Interface (GUI). If you can type fast, DukeBai is ~~FAST~~ ***SUPER*** *FAST* to use.

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `.jar` file from [here]().
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the app. The GUI should be similar to below.

<img src="/Users/xiaoyunwu/Library/Application Support/typora-user-images/image-20210912130909497.png" alt="image-20210912130909497" style="zoom: 33%;" />

5. Type the command in the command box and press Enter to execute it. You can try the follow example commands:
    -  `help`: Shows the list of available command and example usages.
    - `list`: Shows the list of tasks you have added.
    - `todo homework `: Adds a new todo task named `homework`.
    - `done 1`: Marks the first item on the list as done.
    - `delete 1`: Deletes the first item on the list.
    - `bye`: Exits the app.
6. Refer to the Features below for details of each command.

## Features

### Viewing hellp - `help`

Displays a list of available commands with example usage.

Format: `help`

<img src="/Users/xiaoyunwu/Documents/NUS/CS2103T/ip/docs/HelpGUI.png" alt="HelpGUI" style="zoom:33%;" />



### Adding a Todo Task: `todo`

Adds a todo task to the task list.

Format: `todo <description>`

Example:

- `todo buy milk`

Example Expected Outcome:

- `Got it. I've added this task:`
  `[T][] buy milk`
  `Now you have 1 tasks in the list.`
    - `[T]` indicates todo task.
    - `[]` indicates that this task is not completed yet.





### Adding a Deadline Task: `deadline`

Adds a deadline task to the task list with time in 24-hour format.

Format: `deadline <description> /by yyyy/MM/dd HHmm`

Example:

- `deadline assignment /by 2021/09/15 1300`

Example Expected Outcome:

- `Got it. I've added this task:`

  `[D][] assignment (by: 15 Sep 2021 1300)`
  `Now you have 2 tasks in the list.`



### Adding an Event Task: `event`

Adds an event task to the task list with time in 24-hour format.

Format: `event <description> /at yyyy/MM/dd HHmm `

Example:

- `event project meeting /at 2021/09/12 1700`

Example Expected Outcome:

- `Got it. I've added this task:`

  `[E][] project meeting (at: 12 Sep 2021 1700)`

  `Now you have 3 tasks in the list`.



### Listing all tasks: `list`

Displays a list of upcoming tasks.

Format: `list`

Example Expected Outcome:

- `Here are the tasks in your list:`

  `1. [T][] buy milk`

  `2. [D][] assignment (by: 15 Sep 2021 1300)`

  `3. [E][] project meeting (at: 12 Sep 2021 1700)`



### Marking a task as done: `done`

Marks a task as done with the specified task number as shown in the *full* task list.

Format: `done <task_number>`

Example:

- `list` followed by `done 1` marks the first task on the list as done.

Example Expected Outcome:

- `Nice! I've marked this task as done:`

  `[T][X] buy milk`



### Deleting a task: `delete`

Deletes a task from the task list with the specified task number as shown in the *full* task list.

Format: `delete <task_number>`

Example:

- `list` followed by `delete 1` deletes the first task on the list as done.

Example Expected Outcome:

- `Noted. I've removed this task:`

  `[T][X] buy milk`



### Finding a task by description: `find`

Finds task(s) matching the specified description.

- The search is case-insensitive. e.g. `MEeting` will match `meeting`.
- All task description with the matching sequence of characters will be returned. e.g. `me` will match `meeting` and `assignment`.

Format: `find <description>`

Example:

- `find meeting`

<img src="/Users/xiaoyunwu/Documents/NUS/CS2103T/ip/docs/FindGUI.png" alt="FindGUI" style="zoom:33%;" />



### Viewing scheduled tasks: `schedule`

Displays a list of deadline tasks to be completed by the specified data, and event tasks scheduled on the speficied date.

Format: `schedule `

Example:

- `schedule 2021/09/15`

Example Expected Outcome:

- `Here are the matching tasks in your list:`

  `1. [D][] assignment (by: 15 Sep 2021)`

  `2. [E][] meeting (at: 15 Sep 2021 1200)`



### Sort tasks: `sort`

Displays the sorted list of tasks in ascending date order (i.e. nearest to furthest date).

- `Todo` tasks will be located at the end of the list after sorting.

Format: `sort`

Example Expected Outcome:

- `Here are the tasks in your list:`

  `1. [E][] project meeting (at: 12 Sep 2021 1700)`

  `2. [E][] meeting (at: 15 Sep 2021 1200)`

  `3. [D][] assignment (by: 15 Sep 2021 1300)`

  `4. [T][] buy milk`



### Exiting the program: `bye`

Exits the program.

Format: `bye`



### Saving the data

Data in the task list is saved in the hard disk at `[JAR file location]/data/tasks.txt`  automatically after any command that modifies the list (e.g. `todo`, `done`, `delete`).

- To transfer your data to another computer: install the app in the other computer and overwrite the empty `tasks.txt` file with the file containing your previous data.

## Command Summary

| Action       | Format                              | Example                                     |
| ------------ | ----------------------------------- | ------------------------------------------- |
| Add Todo     | `todo <description>`                | `todo read book`                            |
| Add Deadline | `deadline <description> /by <date>` | `deadline assignment /by 2021/09/15 1300`   |
| Add Event    | `event <description /at <date>`     | `event project meeting /at 2021/09/12 1700` |
| Mark Done    | `done <task_num>`                   | `done 1`                                    |
| Delete       | `delete <task_num>`                 | `delete 1`                                  |
| Find         | `find <description>`                | `find meeting`                              |
| Schedule     | `schedule <date>`                   | `schedule 2021/09/15`                       |
| List         | `list`                              |                                             |
| Sort         | `sort`                              |                                             |
| Help         | `help`                              |                                             |
| Exit         | `bye`                               |                                             |

