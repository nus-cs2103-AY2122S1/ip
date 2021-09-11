# User Guide

Welcome to HelpBot, affectionately named Bob. He is everything you wanted, but less.<br />
Bob will help you with what you like, while shaming you for even asking in the process.<br />

## Features

### Task Tracking

HelpBot allows you to track multiple tasks at once, and saves your task list to a file.<br />
Tasks come in three types: `ToDo`, `Deadline`, `Event`<br />

- `ToDo` are simple tasks that have no set date or completion time.<br />
- `Deadline` are tasks that have to be completed by a certain set time.<br />
- `Event` are tasks that are scheduled to happen at specific times.

Tasks can be deleted, marked as done, as well as archived for future use to prevent clutter.

### Text Commands

Commands may be entered via the Grahpical User Interface(GUI) of the bot.<br />
These are all text based and require no mouse input to function.<br />
All command output is logged to a file, and the task list is automatically updated.

## Usage

Here's some information related to the commands listed below.<br />
`()` Round brackets denote compulsory commands.<br />
`[]` Square brackets denote optional commands.<br />
`...` Ellipses means multiple arguments separated by spaces may be accepted.<br />
All date fields have to be in the format `yyyy-mm-dd`.<br />
If you would like to include the time in the date as well, the time has to be in the format `hh:mm:ss`.

### `help` - Show Command List

Shows you the list of commands along with relevant parameters.

Command format: `help`

Sample output:

```
===================To Your Royal Highness===================
Beep... You've reached the voicemail of HelpBot inc. Please speak after the dial tone.
Still here? Sigh... thought that would work. My name is Bob.
Here is the myriad of ways you can inconvenience me:
  [] denotes optional arguments, () denotes REQUIRED arguments.
  > 'list [date]' - view all your tasks, optionally (troublesome for me) by date
  > 'find (keyword)' - finds all tasks containing specified keyword (please no)
  > 'todo (task)' - add a simple todo task
  > 'deadline (task) /by (date) (time)' - add a task to be done by specified date/time
  > 'event (task) /at (date) (time)' - add a task that happens at specified date/time
  > 'update (task index) /to (date) (time)' - modify the date/time of task at specified index
     - date format: yyyy-mm-dd
     - time format: hh:mm:ss
  > 'delete (task index) (task index 2) ...' - delete multiple tasks (LIFT MY BURDEN!)
  > 'delete all' - delete all tasks (YES YES YES YES YES)
  > 'archive (task index) (task index 2) ...' - archives multiple tasks (ahh... temporal relief...)
  > 'archive load' - loads archived tasks
  > 'bye' - (please for the love of God) lets me rest! :)
======================================================
```

### `list` - Show Task List

Shows you the current list of tasks.<br />
If date is specified, only tasks on that specific date will be shown. If not, all tasks are shown.

Command Format: `list [date]`

Sample usage: `list 2021-08-22`

Sample output:

```
===================To Your Royal Highness===================
Do you even realise how hard it was to do this?
Here are your tasks on 22 Aug 2021.
 1. [E][ ] helpbot submission (at: 22 Aug 2021)
=====================================================
```

### `todo` - Add ToDo Task

Adds a new `ToDo` task to the task list.

Command format: `todo (task)`

Sample usage: `todo Homework`

Sample output:

```
===================To Your Royal Highness===================
Urgh, fine. Your task has been added:
 [T][ ] Homework
You now have 1 task(s) to do.
=====================================================
```

### `deadline` - Add Deadline Task

Adds a new `Deadline` task to the task list.

Command format: `deadline (task) /by (date) [time]`

Sample usage: `deadline helpbot submission /by 2021-08-22 19:00:00`

Sample output:

```
===================To Your Royal Highness===================
Urgh, fine. Your task has been added:
 [D][ ] helpbot submission (by: 22 Aug 2021, 07:00:00 pm)
You now have 2 task(s) to do.
=====================================================
```

### `event` - Add Event Task

Adds a new `Event` task to the task list.

Command format: `event (task) /at (date) [time]`

Sample usage: `event dinner /at 2021-08-22`

Sample output:

```
===================To Your Royal Highness===================
Urgh, fine. Your task has been added:
 [E][ ] dinner (at: 22 Aug 2021)
You now have 3 task(s) to do.
=====================================================
```

### `update` - Update Task Date/Time

Updates the date or time of a `Deadline` or `Event` task.<br />
Task index can be found via the `list` command.

Command format: `update (task index) /to (date) [time]`

Sample usage: `update 3 /to 2021-08-22 19:00:00`

Sample output:

```
===================To Your Royal Highness===================
Dude, make up your mind! I'll update it, but just this once, okay?
 [D][ ] helpbot submission (by: 22 Aug 2021, 07:00:00 pm)
=====================================================
```

### `done` - Mark Task as Done

Marks specified task as done.<br />
Task index can be found via the `list` command.

Command format: `done (task index)`

Sample usage: `done 1`

Sample output:

```
===================To Your Royal Highness===================
About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:
 [T][X] test1
=====================================================
```

### `delete` - Delete Task

Deletes the specified task from the task list.<br />
Task index can be found via the `list` command.<br />
Multiple task indexes may be specified, separated by a space each.<br />
Using the `all` argument will delete all tasks.

Command format: `delete (task index...)` or `delete all`

Sample usage: `delete 1 2 3`

Sample output:

```
===================To Your Royal Highness===================
Oh good, they're actually bundled up. Deleted these:
 [E][ ] helpbot submission (at: 22 Aug 2021)
 [T][ ] Homework
 [D][ ] helpbot submission (by: 22 Aug 2021, 07:00:00 pm)
You now have 0 task(s) to do.
=====================================================
```

### `archive` - Archive Task

Archives the specified task from the task list.<br />
Task index can be found via the `list` command.<br />
Archived tasks are not visible in your task list.<br />
Archived tasks are saved in the `archive.txt` file in the same directory as the Bot.<br />
Multiple task indexes may be specified, seprarted by a space each.<br />

Command format: `archive (task index...)`

Sample usage: `archive 1 2 3`

Sample output:

```
===================To Your Royal Highness===================
Finally gonna take some load off me, are you? These better not come back anytime soon:
 [T][ ] task
 [T][ ] task2
 [D][ ] project (by: 22 Aug 2021)
=====================================================
```

### `archive load` - Load Archived Task

Loads all archived tasks from `archive.txt`.<br />
Archived tasks are added to the end of your task list.

Command format: `archive load`

Sample output:

```
===================To Your Royal Highness===================
I knew it... Sometimes I wonder why I even try with you. Archived tasks loaded:
 [T][ ] task
 [T][ ] task2
 [D][ ] project (by: 22 Aug 2021)
You now have 10 task(s) to do.
=====================================================
```

### `find` - Find Task

Finds relevant tasks by keyword.

Command format: `find test`

Sample output:

```
===================To Your Royal Highness===================
Why must you make life hard for me?
Here are your tasks that contain the word 'test'.
 1. [T][ ] test 1
 2. [T][ ] test 2
 3. [T][ ] test 3
=====================================================
```

### `exit` - Exits HelpBot

Exits the HelpBot.

Command format: `exit`

Sample output:

```
===================To Your Royal Highness===================
Good riddance.
=====================================================
```