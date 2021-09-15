# Welcome to Duke!
Duke is a **desktop application for managing user tasks, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). Duke lets you issue **simple commands** to `add` and `delete` tasks, track your `list` of tasks, as well as mark them as `done`. What are you waiting for? Get started with our **Quick Start** guide below!

### Table of Contents
* Quick Start
* Features
  * `todo`
  * `deadline`
  * `event`
  * `list`
  * `delete`
  * `done`
  * `find`
  * `bye`
* FAQ
* Command Summary
-----------------------------------------------------------------------------------------------------------------
## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/nhjryan/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Duke.
4. **Double-click** the file to start the app.
5. Type in commands, such as `list`, `todo First Task`, and `delete 1`, to try out some of the app's functions.
-----------------------------------------------------------------------------------------------------------------
## Features 

### `todo` - Add a To-do

Adds a To-do Task to your task list.

Example of usage: 

`todo TASK`

Expected outcome:

A To-do Task is added to your task list.

```
Got it. I've added this task:
[T][ ] TASK
Now you have NUMBER OF TASKS tasks in the list.
```
------------------------------------------------------------------------------------------------------------------
### `deadline` - Add a Deadline

Adds a Deadline Task to your task list.

Example of usage: 

`deadline TASK /by YYYY-MM-DD`

❗ **Note:** Remember to use the specified YYYY-MM-DD format when filling in the date!

Expected outcome:

A Deadline Task is added to your task list.

```
Got it. I've added this task:
[D][ ] TASK (by: MMM D YYYY)
Now you have NUMBER OF TASKS tasks in the list.
```
------------------------------------------------------------------------------------------------------------------
### `event` - Add an Event

Adds an Event Task to your task list.

Example of usage: 

`event TASK /at YYYY-MM-DD`

❗ **Note:** Remember to use the specified YYYY-MM-DD format when filling in the date!

Expected outcome:

An Event Task is added to your task list.

```
Got it. I've added this task:
[E][ ] TASK (at: MMM D YYYY)
Now you have NUMBER OF TASKS tasks in the list.
```
------------------------------------------------------------------------------------------------------------------
### `list` - Lists all entries

Shows a list of all Tasks in the task list.

Example of usage: 

`list`

Expected outcome:

A list of all your Tasks are shown.

```
Here is a list of all your tasks:
[T][ ] TASK 1
[E][X] TASK 2 (at: MMM D YYYY)
```
------------------------------------------------------------------------------------------------------------------
### `delete` - Deletes the specified entries

Deletes the Tasks specified by the given indexes.

Example of usage: 

`delete INDEX [MORE_INDEXES]`

💡 **Tip:** If you need to remove multiple tasks, you can specify them in a row with a space between each entry (e.g. `delete 2 3 4`)

Expected outcome:

The Tasks which are specified by the user are deleted.

```
Noted. I've removed these tasks:
[T][ ] TASK 1
Now you have NUMBER OF TASKS tasks in the list.
```
------------------------------------------------------------------------------------------------------------------
### `done` - Marks the specified entries as completed

Marks the Tasks specified by the given indexes as completed.

Example of usage: 

`done INDEX [MORE_INDEXES]`

💡 **Tip:** If you need to mark multiple tasks, you can specify them in a row with a space between each entry (e.g. `done 2 3 4`)

Expected outcome:

The Tasks which are specified by the user are marked as done.

```
Nice! I've marked these tasks as done:
[E][X] TASK 2 (at: MMM D YYYY)
There were no invalid entries entered.
```
------------------------------------------------------------------------------------------------------------------
### `find` - Finds a Task

Lists all Tasks whose descriptions contain the given keyword

Example of usage: 

`find KEYWORD`

Expected outcome:

A list of all Tasks that contain the given keyword are shown.

```
Here are the matching tasks in your list:
[T][ ] TASK 1
[E][X] TASK 2 (at: MMM D YYYY)
```

Alternative outcome:

No matches containing the given keyword are found.

```
No matches found.
```
------------------------------------------------------------------------------------------------------------------
### `bye` - Exits the program

Closes the window and terminates the program.

Example of usage: 

`bye`

Expected outcome:

A goodbye message is shown and the program terminates.

```
Bye!
```
------------------------------------------------------------------------------------------------------------------
## FAQ
**Q:** How do I transfer my data to another Computer?
**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke home folder.

**Q:** The program keeps telling me that my file is corrupted. What should I do?
**A:** This might be the result of accidental tampering with your `.txt` file in your Duke home folder. You may either open it to see if there is any erroneous formatting and make the necessary changes, or delete the file and restart the app to create a new `.txt` file.

**Q:** Is there a command that allows me to clear all my tasks?
**A:** This current version of Duke does not support such a function, but may do so in the future!

**Q:** I tried searching for multiple keywords with the `find` command, but received no results. What is wrong?
**A:** Currently, there is no support for searching for multiple keywords, so the app will take the command to be looking for a single phrase instead. This may be implemented in the future!

**Q:** Is there support for adding multiple tasks?
**A:** Not at the moment, but it may do so in the future!

----------------------------------------------------------------------------------------------------------------------

## Command Summary
Action | Format
------ | -------
**Add to-do** | `todo TASK` e.g., `todo homework`
**Add deadline** | `deadline TASK /by YYYY-MM-DD` e.g., `deadline assignment /by 2021-09-15`
**Add event** | `event TASK /at YYYY-MM-DD` e.g., `event meet Tim /at 2021-10-15`
**List** | `list`
**Delete** | `delete INDEX [MORE_INDEXES]` e.g., `delete 3 4 5`
**Done** | `done INDEX [MORE_INDEXES]` e.g., `done 1 2 3`
**Find** | `find KEYWORD` e.g., `find read`
**Bye** | `bye`
