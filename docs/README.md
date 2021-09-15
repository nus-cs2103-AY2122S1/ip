# Morgan User Guide

## About Morgan
Morgan is a virtual task assistant that provides both 
speed and convenience in managing your day-to-day tasks.
Equipped with a simple Graphical User Interface (GUI), 
Morgan is optimized for use via a Command Line Interface (CLI).

![image](Ui.png) 

Download Morgan [here](https://github.com/jennibearduit/ip/releases) 
and you'll never have to worry about missing a single deadline!

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest release of the morgan.jar file.
3. Copy the file to the folder you want to use as the home folder for Morgan.
4. Enter `java -jar Morgan.jar` into your computer's terminal in the same directory 
as your Morgan.jar file. The GUI should appear in a few seconds.
5. Upon starting Morgan, Morgan will attempt to load the previous task history from 
`./morgan-files/tasks.txt`. 
6. You can type commands into the command box and execute them by pressing Enter or 
by clicking on the Send button on the bottom right.
7. Type `help` and press Enter for Morgan to display the list of available commands.

## Commands

### `help`

Lists all available commands in Morgan.

Usage: `help`

### `list`

Lists all existing tasks in storage.

Usage: `list`

### `fixed`

Adds a fixed duration task into the list of tasks.

Usage: `fixed [task] /for [duration (in hours)]`

Example Usage: fixed do homework /for 2

### `todo` 

Adds a to-do task into the list of tasks.

Usage: `todo [task]`

Example Usage: `todo do homework`

### `event`

Adds an event task into the list of tasks.

Usage: `event [task] /at [dd-mm-yyyy hh:mm]`

Example Usage: `event do homework /at 01-12-1997 07:00`

### `deadline`

Adds a deadline task into the list of tasks.

Usage: `deadline [task] /by [dd-mm-yyyy hh:mm]`

Example Usage: `deadline do homework /by 01-12-1997 07:30`

### `delete`

Deletes a task from the list of tasks.

Usage: `delete [task number]`

Example Usage: `delete 1`

### `done`

Marks a specific task as done.

Usage: `done [task number]`

Example Usage: `done 1`

### `find`

Lists all existing tasks containing a specified keyword.

Usage: `find [keyword]`

Example Usage: `find homewrok`