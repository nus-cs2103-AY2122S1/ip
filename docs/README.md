# Good Janet User Guide

This chat-bot is a greenfield Java project. It's named after Janet from _The Good Place_.



## Quick start

1. Ensure that Java 11 is installed.
2. Download the latest JAR file from [here](https://github.com/wpinrui/ip/releases).
3. Move janet.jar to an empty folder where you want to run it.
4. Open this folder in a terminal window.
    1. Run the command `java -jar janet.jar`. You should see a chat window with a greeting from Good Janet.



## Features

### Create a to-do: `todo`

Creates a generic task.

Format: `todo DESCRIPTION`



### Create a task with a deadline: `deadline`

Creates a task that has a deadline. Good Janet supports date inputs of format YYYY-MM-DD. All other inputs are treated as strings.

Format: `deadline DESCRIPTION /by DATE`

Examples:

- `deadline Submit Assignment /by 2021-09-19` (date format supported)

- `deadline Buy milk /by tomorrow` (date is treated as a string)



### Create an event with a date: `event`

Creates a task that will occur at a certain time. Like deadlines, Good Janet supports event date inputs of format YYYY-MM-DD. All other inputs are treated as strings.

Format: `event DESCRIPTION /at DATE`

Examples:

- `event Yeri concert /at 2022-01-11` (date format supported)
- `deadline Lunch with Wendy /at 1pm tomorrow` (date is treated as a string)



### Show all tasks: `list`

Shows all the tasks tracked by Good Janet.

Format: `list`



### Mark task as complete: `done`

Marks the task corresponding to the given task number as completed.

Format: `done INDEX`

Example:

- `done 3` (marks the 3rd task shown by the `list` command as completed)



### Remove task from list: `delete`

Deletes the task corresponding to the given task number from the list.

Format: `delete INDEX`

Example:

- `delete 3` (removes the 3rd task shown by the `list` command from the list)



### Search for tasks containing a string: `find`

Searches the task list for tasks containing the given search query in their descriptions, and displays them in a list.

Format: `find QUERY`

Example:

- `find homework` (lists all tasks with "homework" in the description)



### Search for tasks occurring on a certain date: `schedule`

Shows the list of deadlines that are due on a given date, along with any events that occur on that date. The date must be of the format YYYY-MM-DD.

Format: `schedule DATE`

Example:

- `schedule 2021-01-01` (lists all deadlines due on 1 Jan 2021, along with all events happening on 1 Jan 2021)



### Quit the application: `bye`

Quits the application.



## Acknowledgements

Portions of the codebase have been reused from the following sources:

### Reused from [Jeffry Lum](https://se-education.org/guides/tutorials/javaFxPart4.html) with minor modifications

1. MainWindow.java
2. DialogBox.java
3. MainWindow.fxml
4. DialogBox.fxml

### Reused from [Datsabk](https://mkyong.com/java/how-to-read-and-write-java-object-to-a-file/) with minor modifications

1. `Storage::readSave()`
2. `Storage::writeSave()`