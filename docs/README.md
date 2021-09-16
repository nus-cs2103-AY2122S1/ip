# User Guide
Adapted from [AddresBook3 SE-Edu User Guide](https://se-education.org/addressbook-level3/UserGuide.html#quick-start)

VIN Chatbot is a desktop app for keeping track of your tasks. This application utilizes the Command Line Interface (CLI) for the most part, but it also  has a Graphical User Interface (GUI) to make it more user friendly.

* [Quick Start](#quick-start)
* [Features](#features)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `Vin.jar` from [here](https://github.com/kevinmingtarja/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your VIN Chatbot.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

1. After running VIN for the first time, a `data` directory will be created on the _home folder_ and will act as the local storage to store your tasks.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Listing all your tasks

Lists down all your tasks, including other information such as whether or not they are done, tags (if any), and deadline or time (if any).

```
Here are the tasks in your list:
1. [T][X] read book (tags: #urgent #impt)
2. [D][X] return book (tags: #very urgent) (deadline: Dec 1 2021)
3. [E][ ] project meeting  (tags: #cs2103t) (at: Mon 2-4pm)
```

Format: `list`


### Adding a todo task

Adds a todo task to the task list.

Format: `todo <task>`

Example: `todo finish assignment 1`


### Adding a deadline task

Adds a deadline task to the task list.

Format: `deadline <task> /by <deadline>`

Example: `deadline return book /by 2021-12-01`


### Adding an event task

Adds a deadline task to the task list.

Format: `event <task> /at <time>`

Example: `event project meeting /at Mon 2-4pm`


### Marking a task as done

Marks a task as done. 1-based indexing is used here.

Format: `done <index>`

Example: `done 1`


### Tagging a task

Tags a task with the given index with the keyword that is supplied. A task can have multiple tags, but tags can only be added one by one. 1-based indexing is used here.

Format: `tag <index> #<tag>`

Example: `tag 3 #urgent`


### Finding tasks by name

Finds tasks whose names contain the given keyword.

Format: `find <keyword>`

Example: `find assignment`


### Deleting a task

Deletes the specified task given their index. 1-based indexing is used here.

Format: `delete <index>`

Example: `delete 2`


### Exiting the program

Exits the program.

Format: `bye`


### Saving the data

After executing any command that modifies the data, the new data will be automatically saved. So there is no need to save the data manually.

### Editing the data file

Data are saved as a txt file `[JAR file location]/data/list.txt`. Advanced users are welcome to update data directly by editing that data file.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List** | `list`
**Add Todo** | `todo <task>` <br> e.g., `todo finish assignment 1`
**Add Deadline** | `deadline <task> /by <deadline>` <br> e.g., `deadline return book /by 2021-12-01`
**Add Event** | `event <task> /at <time>` <br> e.g., `event project meeting /at Mon 2-4pm`
**Done** | `done <index>`<br> e.g., `done 2`
**Tag** | `tag <index> #<tag>`<br> e.g., `tag 2 #important`
**Find** | `find <keyword>`<br> e.g., `find book`
**Delete** | `delete <index>`<br> e.g., `delete 3`
**Exit** | `bye`
