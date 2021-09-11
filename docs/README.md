# User Guide

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `shybot.jar` from [here](https://github.com/CrownKira/ip/releases/download/v0.2/shybot.jar).
3. Copy the file to the folder you want to use as the home folder for your ShyBot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app
   contains some sample data.
   <img width="402" alt="Ui" src="https://user-images.githubusercontent.com/24221801/132957831-ec7634d0-5133-4546-a182-7e911f2b2646.png">
5. Type the command in the input box and press Enter or `Send` button to execute it.  
   Some example commands you can try:

- `list`: Lists all contacts.
- `todo chores`: Add `chores` todo task to the list.
- `remove 3`: Delete the 3rd task shown in the list.

6. Refer to the Features below for details of each command.

## Features

### Adding a todo task `todo`

Adds a todo task to the list.

Format: `todo DESCRIPTION [/tag TAG]`
Tip: A task can have any number of tags (including 0)

Examples:

- `todo 2100 assignment /tag homework`

### Adding an event task `event`

Adds a event task to the list.

Format: `event DESCRIPTION [/at YYYY-MM-DD] [/tag TAG]`
Tip: A task can have any number of tags (including 0)

Examples:

- `event 2100 assignment /at 2021-09-15 /tag homework`

### Adding a deadline task `deadline`

Adds a deadline task to the list.

Format: `deadline DESCRIPTION [/at YYYY-MM-DD] [/tag TAG]`
Tip: A task can have any number of tags (including 0)

Examples:

- `deadline 2100 assignment /at 2021-09-15 /tag homework`

### Listing all tasks: `list`

Shows a list of all tasks in the list.

Format: `list`

### Locating tasks by description or tag: `find`

Finds tasks whose descriptions or tags contain any of the given keyword.

Format: `find KEYWORD`

- The search is case-insensitive. eg. `study` will match `Study`
- Only the description and tag are searched.
- No need to be full words to be matched e.g. `stu` will match `study`

Examples:

- find `Study` returns `Study biolology` and `study`

### Deleting a task: `remove`

Deletes the specified task from the list.

Format: `remove INDEX`

- Deletes the task at the specified `INDEX`.
- the index refers to the index number shown in the displayed person list.
- The index must be a positive integer 1, 2, 3, ...

Examples:

- `list` followed by `remove 2` deletes the 2nd task in the list.

### Saving the data:

ShyBot's data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually

## Command summary

Action | Format, Examples
------------ | -------------
Todo | `todo DESCRIPTION [/tag TAG]`
Event | `event DESCRIPTION [/at YYYY-MM-DD] [/tag TAG]`
Deadline | `deadline DESCRIPTION [/at YYYY-MM-DD] [/tag TAG]`
List | `list`
Find | `find KEYWORD`
Remove | `remove INDEX`

