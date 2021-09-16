# User Guide

JIJI is a desktop chatbot that manages your tasks in an interactive fashion.
If tracking tasks by yourself gets dull, give JIJI a try!

## Getting Started
1. Ensure you have Java 11 installed on your computer
2. Copy `jiji.jar` to an empty folder
3. Double click the file to start the app

## Features

> Words in `<>` brackets are parameters that should be supplied by the user
> You cannot add duplicate tasks to a list

### Adding a deadline task: `deadline`

Adds a task with a deadline to the list.

Format: `deadline <description> /by <dd-MM-yyyy HHmm>`

Examples:
* `deadline do assignment /by 26-09-2021 2359`
* `deadline return book /by 10-10-2021 1700`

### Adding an event task: `event`

Adds a task that is occurring on a date, within a time period

Format: `event <description> /at <dd-MM-yyyy HHmm HHmm>`

> In `<dd-MM-yyyy HHmm HHmm>`, the first `HHmm` is the start time and the second is the end time!

Examples:
* `event watch movie /at 20-10-2021 1300 1500`
* `event attend fair /at 23-10-2021 0800 1600`

### Adding a todo task: `todo`

Adds a task with only a description.

Format: `todo <description>`

Examples:
* `todo read book`
* `todo water the plants`

### Deleting a task: `delete`

Deletes a task.

Format: `delete <task number in list>`

> Task number is the number next to the task of the latest list you have. 
> Use `list` to view the full list if you are unsure!

Examples:
* `delete 1` deletes the first task in the latest list

### Exiting the app: `bye`

Exits the app.

Format: `bye`

### Marking a task as done: `done`

Marks a task as done.

Format: `done <task number in list>`

> Task number is the number next to the task of the latest list you have. 
> Use `list` to view the full list if you are unsure!

Examples:
* `done 1` marks the first task in the latest list as done

### Viewing the full list: `list`

Shows all the tasks in your list.

Format: `list`

### Viewing tasks that match a keyword: `find`

Finds all the tasks with a description containing the keyword.
* The search is case insensitive
* Descriptions with words that match partially with the keyword will be returned

Format: `find <keyword>`

Examples:
* `find ca` lists all tasks that have `ca` in the description. e.g. `cake`, `car`
* `find jump` shows words such as `jump` and `JUMP` if they are in your list

### Viewing help: `help`

Shows all the available commands and their formats.

Format: `help`

## Command Summary

Command | Format
--------|--------
bye | `bye`
deadline | `deadline <description> /by <dd-MM-yyyy HHmm>`
delete | `delete <task number in list>`
done | `done <task number in list>`
event | `event <description> /at <dd-MM-yyyy HHmm HHmm>`
find | `find <keyword>`
help | `help`
list | `list`
todo | `todo <description>`

## Credits
These are the sources of Studio Ghibli images used
* [Jiji from Kiki's Delivery Service](https://www.ghibli.jp/works/majo/#frame&gid=1&pid=18)
* [Totoro from My Neighbor Totoro](https://www.ghibli.jp/works/totoro/#frame&gid=1&pid=29)

