# Duke Batman

This is an adaptation of a greenfield Java project from [SE-EDU](https://github.com/se-edu/duke) It's named after the Java mascot _Duke_.
Ui of the project is as show [here](Ui.png).

* [Quickstart](#quickstart)
* [Features](#features)
    * [ToDo](#todo)
    * [Event](#event)
    * [Deadline](#deadline)
    * [Recurring](#recurring)
    * [List](#list)
    * [Delete](#delete)
    * [Done](#done)
    * [Find](#find)
    * [Bye](#bye)

## Quickstart

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from here.

3. Copy the file to the folder you want to use as the home folder.

4. Double-click the file to start the app.

5. Type commands in the command box and press Enter to execute it. e.g typing `list` and pressing Enter will open up the list stored

Refer to the [Features](#features) below for details of each command.

## Features

### ToDo

Adding a ToDo Task to the list

Format: `todo TASKDESCRIPTION`

Examples:
* `todo Homework`
* `todo Buy groceries`

### Event

Adding a Event Task to the list

Format: `event TASKDESCRIPTION /at TIME_OF_EVENT`

Examples:
* `event Concert /at 6pm today`
* `event Lessons /at 12/08/2021 6pm`

### Deadline

Adding a Deadline Task to the list

Format: `deadline TASKDESCRIPTION /by TIME_DUE`

Examples:
* `deadline Homework /by 6pm today`
* `deadline Project work /by 12/08/2021 6pm`

### Recurring

Adding a Recurring Task to the list

Format: `recurring TASKDESCRIPTION /at TIME_DUE /repeat NUM_DAYS`

Examples:
* `recurring Tutorial /at 16/09/2021 /repeat 7`

### List

Display all the tasks in the list

Format: `list`

### Delete

Deletes the specified task from the task list.

Format: `delete INDEX`

* The index must be a positive integer 1, 2, 3, …​

### Done

Mark the specified task from the task list as done.

Format: `done INDEX`

* The index must be a positive integer 1, 2, 3, …​

### Find

Finds task which contact the given keyword exactly.

Format: `find KEYWORD`

Examples:

`find home` returns `homework` but not `Homework`

### Bye

Say goodbye to Duke Batman

Format: `bye`
