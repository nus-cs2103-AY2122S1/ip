# T-800 User Guide

### Fast and Easy Task Tracking

Use this bot to help you manage 3 types of task through just typing commands without the need to use your mouse!

Task Types:
1. Todos - Any task with a simple description of your choice
2. Deadlines - Todos with the addition of a deadline in a date & time format
3. Events - Todos that has a duration on a specific date

Moreover, you can easily specify the number of repetitions for deadline tasks too!

### Available features:
* [Viewing commands: `commands`](#commands---displays-a-list-of-all-available-commands)
* [Listing all tasks: `list`](#list---displays-a-list-of-all-your-current-tasks)
* [Marking task done: `done`](#done---mark-a-task-as-done)
* [Deleting a task: `delete`](#delete---delete-a-task)
* [Deleting all tasks: `clear`](#clear---delete-all-tasks)
* [Finding a task: `find`](#find---find-a-matching-task-through-keywords)
* [Adding a Todo: `todo`](#todo---add-a-todo-type-task)
* [Adding a Deadline: `deadline`](#deadline---add-a-deadline-type-task)
* [Adding an Event: `event`](#event---add-an-event-type-task)

## Get Started
  1. Ensure that you have Java `11` or above installed in your Computer.
  2. Download the latest version of the .jar file from [here](https://github.com/NUSmhk/ip/releases/tag/A-Release).
  3. Double-click the downloaded .jar file to get started!

## Usage

### `commands` - Displays a list of all available commands

Example of usage: `commands`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmBMOD.png)


### `list` - Displays a list of all your current tasks

Example of usage: `list`

Expected outcome:

![](https://i.im.ge/2021/09/14/Tm4J48.png)


### `done` - Mark a task as done

Format: `done [INDEX]`

Marks the task at the specified `[INDEX]` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Example of usage: `done 2`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmBdOW.png)


### `delete` - Delete a task

Format: `delete [INDEX]`

Deletes the task at the specified `[INDEX]`.

Example of usage: `delete 1`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGMCa.png)


### `clear` - Delete all tasks

Example of usage: `clear`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGQpJ.png)


### `find` - Find a matching task through keywords

Format: `find [SEARCH TERM]`

Displays a list of existing tasks that contain the search term.
* Search term can be any number of words and characters.
* Search term is case-insensitive.

Example of usage: `find Test 1`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGX6S.png)


### `todo` - Add a Todo type task

Format: `todo [TASK_DESCRIPTION]`

Adds a task with the specified `[TASK_DESCRIPTION]`.
* The task description cannot be empty.
* The task description can be any number of words and characters.

Example of usage: `todo Task 1`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGul6.png)


### `deadline` - Add a Deadline type task

Format: `deadline [TASK_DESCRIPTION] /by [DATE] [TIME] /[REPETITION_TYPE] [INDEX]`

Adds a Todo type task with a specified `[DATE] [TIME]` as deadline.
* Available Date formats:
    * `dd/MM/yyyy`
    * `dd-MM-yyyy`
    * `yyyy/MM/dd`
    * `yyyy-MM-dd`
    * `dd MMM yyyy`
* Available Time formats:
    * `HHmm`
    * `hh:mm a`

Deadline task can be added with specified `[REPETITION_TYPE]` and
specified number of times at `[INDEX]`.
* `/[REPETITION_TYPE]` can be /weekly or /daily

Example of usage: `deadline Task 1 /by 09/09/2021 1900 /daily 3`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGF0F.png)


### `event` - Add an Event type task

Format: `event [TASK_DESCRIPTION] /at [DATE] [START_TIME]-[END_TIME]`

Adds an Event type task that is on specified `[DATE]` and lasting from
`[START_TIME]` to `[END_TIME]`.
* Available Date and Time formats are listed under [Deadline type task](#deadline---add-a-deadline-type-task).

Example of usage: `event Task 1 /at 09/09/2021 1900-2000`

Expected outcome:

![](https://i.im.ge/2021/09/14/TmGOcK.png)
