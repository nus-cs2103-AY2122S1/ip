# User Guide

## Pika Bot User Guide

Pika Bot is an app that is for managing and keeping track of task.

----

### Quick Start

1. Ensure that you have Java 11 or above installed in your computer.
2. Download the latest version from [here](https://github.com/random689/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for your Pika Bot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Do note that the GUI shown below has already ran some commands.

![GUI](https://random689.github.io/ip/Ui.png)

----

## Features:

* Adding a todo task : [todo](https://random689.github.io/ip/#todo)
* Adding a deadline task : [deadline](https://random689.github.io/ip/#deadline)
* Adding an event task : [event](https://random689.github.io/ip/#event)
* Marking a task as done : [done](https://random689.github.io/ip/#done)
* Deleting a task : [delete](https://random689.github.io/ip/#delete)
* Listing all the current task : [list](https://random689.github.io/ip/#list)
* Tagging a task : [tag](https://random689.github.io/ip/#tag)
* Searching tasks by name : [find](https://random689.github.io/ip/#find)

----

### todo

Adds a new todo task.

Syntax: `todo <description>`
> If the description is empty, an error message will be displayed

----

### deadline

Adds a new deadline task.

Syntax: `deadline <description> /by <date time>`
> If the description or the date/time is empty, an error message will be displayed.
> 
> You can refer to Date Time Format for the allowed date-time format.

----

### event

Adds a new event task.

Syntax: `event <description> /at <date time>`
> If the description or the date/time is empty, an error message will be displayed.
>
> You can refer to Date Time Format for the allowed date-time format.

----

### done

Marks the task at the given index as completed

Syntax: `done <index>`
> If the task at the given index is already marked as completed, an error will be displayed.
>
> If an invalid index is given, an error message will be displayed.

----

### delete

Deletes the task at the given index

Syntax: `delete <index>`
> If an invalid index is given, an error message will be displayed.

----

### list

Lists all the current task

Syntax: `list`

----

### tag

Adds a tag to the task

Syntax: `tag <index> <tag name>`
> If an invalid index is given, an error message will be displayed.
> 
> If the tag name is empty, an error message will be displayed.

----

### find

Finds all the task with the given string pattern and display it as a list

Syntax: `find <pattern>`
> If no pattern is given, an error message will be displayed.
> 
> If the pattern is a space " ", all the task will be returned by default.

----

### bye

Informs Pika Bot that the user is exiting. Pika Bot will save all the current tasks.

Syntax: `bye`

----

### Date Time Format

This application only allows 1 type of date time format.
> Time is optional, and will be omitted if not specified.
>
> Time is in military time / 24-hour clock (E.g 22:55)

Allowed Format:
* YYYY-MM-DD
* YYYY-MM-DD HH:MM

----
