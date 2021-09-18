# User Guide - in progress

DukeAgain is a desktop app for managing your tasks.

## Quick start
To run DukeAgain, 

1. Ensure Java Runtime Environment (JRE) `11` or above is installed on your device.
2. Download and extract `duke_again.zip` from [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ).
3. To run:
    - [Windows] Double-click the `duke.jar` to start the app, or run `duke -jar duke.jar` in the terminal to start DukeAgain.
    - [Mac] Navigate Terminal to the folder containing `duke.jar` and run `java -jar duke.jar`.

### Essential commands

The following are the recognized formats for 3 different types of tasks (items in brackets are optional:

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by START [/ OTHER_INFO]`
- `event DESCRIPTION /at START [~ END] [/ OTHER_INFO]`
- `find KEYWORDS`
- ℹ️ `help` to learn other commands! 

**Note**: Start and end dates and times can be represented as just a date, just a time, or both. See below for examples.

## Features

### Autosave

You tasks are automatically saved to disk in the file `duke.txt`.

### Use aliases

Shorthands to create todos, deadlines, and events.

## Usage

### `help` - View help

Prints the help manual, with details on usage.

**Usage**: `help [command]`

**Examples of usage**:

- `help deadline`
- `help d`
- `help`

### `todo` or `t` - Add todo

Adds an todo task with a *description*.

**Usage**: `todo DESCRIPTION`

**Examples of usage**:

- `todo clean the dishes`
- `todo Exercise 5.1`

**Example outcome**:

```
> todo clean the dishes

Got it. I've added this task:
[T][ ] clean the dishes
 Now you have 8 tasks in the list.
```

 Now you have 8 tasks in the list.

### `event` or `e` - Add event

Adds an event task with a *description*, optional start and end date and/or time, and optional extra descriptions.

**Usage**: `event DESCRIPTION /at START [~ END] [/ OTHER_INFO]`

*Note*: `START` , `END` can be formatted as `yyyy-MM-dd` or time `HH:mm` or both `yyyy-MM-dd HH:mm`.

**Examples of usage**:

- `event Camp /at 2021-09-18 16:00 ~ 2021-09-20 18:00`
- `event Festival Meeting /at 2021-09-18 16:00 ~ 18:00 / bring dongle`
- `event Short Meeting /at 2021-09-11 19:00 / bring dongle`
- `event minimal event /at 12:00`

**Example outcome**:

```
> event Festival Meeting /at 2021-09-18 16:00 ~ 18:00 / bring dongle

Got it. I've added this task:
[E][ ] Festival Meeting (at: 18 Sep 2021 16:00 to 18:00 -- bring dongle)
 Now you have 8 tasks in the list.
```

### `deadline` or `d` - Add deadline

Adds an deadline task with a *description*, optional start and end date and/or time, and optional extra descriptions.

**Usage**: `deadline DESCRIPTION /by START [/ OTHER_INFO]`

*Note*: `START` can be formatted as `yyyy-MM-dd` or time `HH:mm` or both `yyyy-MM-dd HH:mm`.

**Examples of usage**:

- `deadline Assignment /by 2021-09-18 16:00 / check submission`
- `deadline Script /by 23:59 / email Jess`
- `event minimal deadline /by 12:00`

**Example outcome**:

```
> deadline Assignment /by 2021-09-18 16:00 / check submission

Got it. I've added this task:
[D][ ] Assignment (by: 18 Sep 2021 16:00), check submission
 Now you have 8 tasks in the list.
```

### `list` - Prints all tasks

Prints all tasks.

**Usage**: `list`

**Example outcome**:

```
Here are the tasks in your list:
1. [T][ ] sample task
2. [D][ ] Assignment (by: 18 Sep 2021 16:00), check submission
3. [E][ ] Festival Meeting (at: 18 Sep 2021 16:00 to 18:00 -- bring dongle)
```

### `done` - Marks task as done

Marks task at `INDEX` in task list as complete.

**Usage**: `done INDEX`

**Example outcome:**

```
> done 2

Nice! I've marked this task as done:
[T][X] marked todo
```

### `delete` - Deletes task

Deletes task at `INDEX`. **This process is irreversible.**

**Usage**: `delete INDEX`

**Example outcome:**

```
> delete 10

Noted. I've removed this task:
[D][ ] Assignment (by: 18 Sep 2021 16:00), check submission
 Now you have 1 task in the list.
```

### `clear` - Deletes all tasks

Deletes all tasks. **This process is irreversible**.

**Usage**: `clear`

**Example outcome**:

```
Task list was cleared.
```

### `greet` - Greets the user again

Say hi to Duke.

**Usage**: `greet`

### `bye` - Quit DukeAgain

Quits DukeAgain.

**Usage**: `bye`
