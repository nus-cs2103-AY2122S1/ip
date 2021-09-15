# User Guide

[![Java CI](https://github.com/czhi-bin/ip/actions/workflows/gradle.yml/badge.svg)](https://github.com/czhi-bin/ip/actions)

Angry Bot is a GUI application that helps to keep track of tasks.

## Quick start
1. Ensure that you have `Java 11` installed on your computer.
2. Download the latest `angrybot.jar` from [here](https://github.com/czhi-bin/ip/releases/tag/A-Release).
3. Move the `angrybot.jar` to an empty directory.
4. Double-click the file to start the app. A GUI should appear in a few seconds.
5. Type `help` to get a list of command and instructions on how to use those commands.
6. All the information will be stored locally inside a textfile `angrybot.txt` inside `data` folder.
7. Refer to below for a more detailed explanation on how to use the commands.

## Features 

AngryBot supports keeping track of 3 types of tasks.

| Task         | Symbol      |
|--------------|-------------|
|Todo          |`T`          |
|Deadline      |`D`          |
|Event         |`E`          |

***

### Viewing help: `help`
Shows a long message consisting of all the commands supported.

Format: `help`

***

### Adding a todo task: `todo`
Adds a todo task to the task list.

Format: `todo <TASK_DESCRIPTION>`

***

### Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline <TASK_DESCRIPTION> </by> <YYYY-MM-DD> <HH:MM>`

***

### Adding a event: `event`
Adds an event to the task list.

Format: `event <TASK_DESCRIPTION> </at> <YYYY-MM-DD> <HH:MM>`

***

### Showing the task list: `list`
Shows all the task in the task list.

Format: `list`

***

### Deleting a task: `delete`
Removes a specific task corresponding to the task number from the task list.

Format: `delete <TASK_NUMBER>`

***

### Marking a task as completed: `done`
Marks a specific task corresponding to the task number as completed..

Format: `done <TASK_NUMBER>`

***

### Finding tasks: `find`
Searches for tasks that are related to the keyword entered.

Format: `find <KEYWORD>`

***

### Sorting the task list: `sort`
Sorts the task list. Default sorting order is by chronological order then by alphabetical order of the task description. Todo task without date and time are put at the last. Adding the optional `reverse` flag can be used to sort the list in the reverse order.

Format: `sort [OPTIONAL: "reverse"]`

Examples: sort, sort reverse

***

### Exiting the program: `bye`
Exits the program and the GUI will close 3 seconds after the user type the command.

Format: `bye`

***

| Command      | Format                                                  | Example                                         |
|--------------|---------------------------------------------------------| ------------------------------------------------|
| help         | `help`                                                  | `help`                                          |
| list         | `list`                                                  | `list`                                          |
| todo         | `todo <TASK_DESCRIPTION>`                               | `todo talk to Angry Bot`                        |
| deadline     | `deadline <TASK_DESCRIPTION> </by> <YYYY-MM-DD> <HH:MM>`| `deadline submit CS2103T /by 2021-09-06 18:00`  |
| event        | `event <TASK_DESCRIPTION> </at> <YYYY-MM-DD> <HH:MM>`   | `event CS2103T Finals /at 2021-09-08 18:00`     |
| done         | `done <TASK_NUMBER>`                                    | `done 2`                                        |
| delete       | `delete <TASK_NUMBER>`                                  | `delete 2`                                      |
| find         | `find <KEYWORD>`                                        | `find CS2103T`                                  |
| sort         | `sort [OPTIONAL: "reverse]`                             | `sort` / `sort reverse`                         |
| bye          | `bye`                                                   | `bye`                                           |