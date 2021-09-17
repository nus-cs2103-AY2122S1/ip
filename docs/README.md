# User Guide

![Energy Logo](../src/main/resources/images/GivePLZTransparent.png?raw=true)

Energy is a desktop app that helps you manage your tasks, optimized for use via a Command-Line Interface,  
combined with a sleek Graphical User Interface with minimal clutter. If you are a fast typist, Energy is the ideal task
management app for you.

* [Quick Start](#quick-start)
* [Features](#features)
    * [Tasks](#tasks)
        * [Adding a todo task: `todo`](#adding-a-todo-task-todo)
        * [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
        * [Adding an event task: `event`](#adding-an-event-task-event)
        * [Listing all tasks: `list`](#listing-all-tasks-list)
        * [Marking a task as done: `done`](#marking-a-task-as-done-done)
        * [Deleting a task: `delete`](#deleting-a-task-delete)
        * [Listing all tasks due in X hours/days/months: `due`](#listing-all-tasks-due-in-x-hoursdaysmonths-due)
        * [Listing all tasks occurring on a certain date: `ondate`](#listing-all-tasks-occurring-on-a-certain-date-ondate)
        * [Listing all tasks containing a keyword/keyphrase: `find`](#listing-all-tasks-containing-a-keywordkeyphrase-find)
    * [Aliases](#aliases)
        * [Adding an alias: `addalias`](#adding-an-alias-addalias)
        * [Listing all aliases: `listalias`](#listing-all-aliases-listalias)
        * [Deleting an alias: `deletealias`](#deleting-an-alias-deletealias)
    * [Exiting the program](#exiting-the-program-bye)
    * [Files](#files)
        * [Saving the task/alias data](#saving-the-taskalias-data)
        * [Editing the save file](#editing-the-save-file)
        * [Editing the config file](#editing-the-config-file)
* [Command Summary](#command-summary)

---

## Quick Start

1. Ensure that you have [Java 11](https://www.oracle.com/java/technologies/downloads/) or above installed.
2. Go to the [releases page](https://github.com/danielsimre/ip/releases) for this repository, and download the latest
   version.
3. Copy the jar file to the folder that you want to use as the home folder.
4. Double-click the jar file to start the app. A GUI (like the one shown below) should appear.
   ![Energy UI](./Ui.png?raw=true)
5. Type the command in the command box and press enter, or click the send button. Here are some commands you could try:
    * `todo Task 1`: Adds a todo task with the description "Task 1".
    * `list`: Lists all tasks.
    * `done 1`: Marks the first task in the list as done.
    * `delete 1`: Deletes the first task in the list.
    * `bye`: Exits the app.
6. Refer to the Features section below for a detailed list of all commands.

---

## Features

Notes for all commands:

* Words within rounded brackets specify parameters that the user will have to input. Do not include the brackets in your
  input.

  Example: For `todo (description)`, a valid input would be `todo Task 1`.
* The format for parameters are specified within square brackets.

  Example: For `deadline (description) /by (date[YYYY-MM-DD]) (time[HH:MM])`, the format for time is HH:MM.
* | is a reserved character. Parameters that include the | symbol will be rejected.
* When supplying parameters to commands, you must follow the format given exactly.
  (Do not exclude parameters, include any extra parameters or reorder the parameters.)
* Parameters for commands that do not take in any parameters such as `bye` or `list`
  will be ignored.

---

### Tasks

#### Adding a todo task: `todo`

Adds a todo task, which is a task with a description, to the task list.

Format: `todo (task description)`

Example: `todo Buy groceries` adds a task with the description "Buy groceries".

#### Adding a deadline task: `deadline`

Adds a deadline task, which is a task with a description along with a date and time, to the task list.

Format: `deadline (description) /by (date[YYYY-MM-DD]) (time[HH:MM])`,

Example: `deadline Finish presentation /by 2021-09-30 23:59` adds a task with the description "Finish presentation"
with a date of September 30th, 2021, 11:59 PM.

#### Adding an event task: `event`

Adds an event task, which is a task with a description along with a date, start time and end time, to the task list.

Format: `event (description) /at (date[YYYY-MM-DD]) (start time[HH:MM]) (end time[HH:MM])`,

Example: `event Meet with professor /at 2021-09-18 11:00 13:00` adds a task with the description "Meet with professor"
with a date of September 18th, 2021, 11:00 AM - 1:00 PM.

#### Listing all tasks: `list`

Lists all tasks currently stored in the program, in order of when they were added.

Format: `list`

#### Marking a task as done: `done`

Marks a task as done, depending on the task index specified.

Format: `done (task index)`

Example: `done 2` will mark the second task in the list as done.

#### Deleting a task: `delete`

Deletes a task from the list, depending on the task index specified.

Format: `delete (task index)`

Example: `delete 3` will delete the third task in the list.

#### Listing all tasks due in X hours/days/months: `due`

Lists all tasks due in X hours/days/months, depending on the user input.

Format: `due (number)(h/d/m)`

Example: `due 10d` gets all tasks due within 10 days from now. (This includes tasks that are already due now.)

#### Listing all tasks occurring on a certain date: `ondate`

Lists all tasks occurring on a given input date.

Format: `ondate (date[YYYY-MM-DD])`

Example: `ondate 2021-09-30` gets all tasks occurring on September 30th, 2021.

#### Listing all tasks containing a keyword/keyphrase: `find`

Lists all tasks containing a given keyword or keyphrase.

Format: `find (keyword/keyphrase)`

Example: `find return` lists all tasks containing the word `return` in their description.

---

### Aliases

#### Adding an alias: `addalias`

Adds an alias to a command. In the future, this alias can be used in place of the original command keyword.

Format: `addalias (command) (alias)`

Example: `addalias todo t` Adds the alias t to the todo command. Then `t Return book` will function exactly the same way
as `todo Return Book`.

Notes:

* To add on to the above example, `addalias t t2` will be a valid command! `t2` would have the same function as `todo`.
  (Since `t` is an alias of `todo`)
* You can even add an alias for `addalias`, such as `addalias addalias aa`. Then `aa todo t` is a valid command.
* You cannot use commands as aliases. (e.g. `addalias todo deadline` is invalid.)
* You cannot use an existing alias as an alias for another command. (e.g. If `t` is already an alias for `todo`
  , `addalias deadline t` is invalid.)

#### Listing all aliases: `listalias`

Lists all aliases added by the user.

Format: `listalias`

#### Deleting an alias: `deletealias`

Deletes an alias, which frees up the alias for use for other commands.

Format: `deletealias (alias)`

Example: `deletealias t` removes the alias t.

---

### Exiting the program: `bye`

Exits the program.

Format: `bye`

---

### Files

#### Saving the task/alias data

There is no need to save manually. Any change made to task or alias data is saved automatically to the hard drive.

#### Editing the save file

Task data is saved in the file [JAR file location]/data/tasks.txt. Advanced users can edit this file directly to update
the task list. If any changes made cause the format to the invalid, Energy will start with a blank task list on the next
run.

#### Editing the config file

Alias data is saved in the file [JAR file location]/data/config.txt. Advanced users can edit this file directly to
add/delete aliases. If any changes made cause the format to the invalid, Energy will start with no aliases on the next
run.
---

## Command Summary

| Action      | Format, Examples |
|-------------|-----------------| 
| Add Alias   | `addalias (command) (alias)` <br /> e.g. `addalias todo t` |
| Add Todo Task    | `todo (description)` <br /> e.g. `todo Go for a run` |
| Add Deadline Task | `deadline (description) /by (date[YYYY-MM-DD]) (time[HH:MM])` <br/> e.g. `deadline Finish presentation /by 2021-09-30 23:59`
| Add Event Task | `event (description) /at (date[YYYY-MM-DD]) (start time[HH:MM]) (end time[HH:MM])` <br/> e.g. `event Meet with professor /at 2021-09-18 11:00 13:00`
| Delete Alias | `deletealias (alias)` <br/> e.g. `deletealias t`
| Delete Task | `delete (task index)` <br/> e.g. `delete 3`
| Exit Program | `bye`
| Mark Task as Done | `done (task index)` <br/> e.g. `done 3`
| List all Aliases | `listalias`
| List all Tasks | `list`
| List all Due Tasks | `due (number)(h/d/m)` <br/> e.g. `due 12h`
| List all Tasks occurring on a date | `ondate (date[YYYY-MM-DD])` <br/> e.g. `ondate 2021-09-30`
| List all Tasks containing keyphrase | `find (keyword/keyphrase)` <br/> e.g. `find borrow`