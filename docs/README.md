# User Guide
TauBot is a desktop app for managing your tasks, which can be used through the Command Line Interface (CLI) or the Graphical User Interface (GUI) for a more familiar chatting experience!
<img width="704" alt="Ui" src="https://user-images.githubusercontent.com/61085398/132985278-c504b348-da99-41f1-bdf7-d764a8f32b3e.png">

## Content
1. [Quick Start](#quick-start)
2. [Command Summary](#command-summary)
3. [Features](#features) 

## Quick Start

1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html) 
installed on your computer
2. Download and extract the Duke.jar file to your desired location
3. Open command prompt/terminal and change directory to taubot.jar's
directory. Replace ```TAUBOT_JAR_DIRECTORY``` with your corresponding taubot.jar's
directory in the following command.

```
cd TAUBOT_JAR_DIRECTORY
```
4. In the same command prompt/terminal, run this command to start Duke

```
java -jar Duke.jar
```
5. Taubot will run and you should see this:

![Screenshot 2021-09-12 at 12 39 56 PM](https://user-images.githubusercontent.com/61085398/132972393-841cfde1-c4aa-4f4a-b18f-adfa6d86ee3d.png)

Try running your first command!

### Example of commands you can try:
1. `todo eat apple` Adds eat apple to the list of tasks.
2. `list` Shows the tasks you have.
3. `done 1` Marks the first task as done.
4. `delete 1` Deletes the first task from the list.
5. `bye` Exits the program.

## Command Summary

Action | Format | Examples
------------ | ------------- | -------------
[todo](#adding-a-todo) | `todo TASK_DESCRIPTION` | `todo eat apple`
[event](#adding-an-event) | `event TASK_DESCRIPTION /at DATE TIME` | `event khai's wedding /at 2024-12-12 0900`
[deadline](#adding-a-deadline) | `deadline TASK_DESCRIPTION /by DATE TIME` | `deadline maths homework /by 2021-09-13 1300`
[list](#list-all-your-tasks) | `list` | ~
[done](#marking-a-task-as-done) | `done INDEX` | `done 1`
[delete](#delete-a-task) | `delete INDEX` | `delete 1`
[find](#find-a-task-by-keyword) | `find KEYWORD` | `find homework`
[schedule](#view-the-schedule-on-a-certain-date) | `schedule` or `schedule DATE` | `schedule` `schedule 2021-09-12`
[bye](#exit-the-program-bye) | `bye` | ~


## Features
In Taubot, there are 3 different tasks, `todo`, `event`, `deadline`.

### Adding a `todo`
Format: `todo TASK_DESCRIPTION`  

Examples:
* `todo cook lunch`
* `todo mop the floor :-(`

### Adding an `event`
Format: `event TASK_DESCRIPTION /at DATE TIME`  
* The date must be in yyyy-mm-dd format.
* The time must be in hhmm format.  

Examples: 
* `event charity run /at 2021-09-17 0600`
* `event khai's wedding /at 2024-12-12 0900`

### Adding a `deadline`
Format: `deadline TASK_DESCRIPTION /by DATE TIME`    
* The date must be in yyyy-mm-dd format.
* The time must be in hhmm format.  

Examples: 
* `deadline CS2103 project /by 2021-09-17 2359`
* `deadline maths homework /by 2021-09-13 1300`

### `list` all your tasks
Format: `list`
* Taubot will list all the tasks that are stored.

### Marking a task as `done`  
Format: `done INDEX`  
* Marks a task as done at the speficied index from the list.
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer 1,2,3...**

Example: `done 1`

### `delete` a task 
Format: `delete INDEX`
* Deletes a task at the specified index from the list. 
* The index refers to the index number shown in the displayed tasks list.
* The index **must be a positive integer 1,2,3...**

Example: `delete 1`

### `find` a task by keyword
Finds tasks containing a single keyword.  
Format: `find KEYWORD`  
* The search is case-insensitive e.g. `Homework` will match `homework`
* Only full words will be matched e.g. `home` will not match `homework`  

Examples:
* `find homework` returns:
<img width="683" alt="Screenshot 2021-09-12 at 1 07 59 PM" src="https://user-images.githubusercontent.com/61085398/132972966-e4b2437b-e00f-42df-965b-96faa7c64417.png">

### View the `schedule` on a certain date
Finds tasks using an **optional** date.
Format: `schedule` or `schedule DATE`
* If a date is passed in, a list of tasks with that date will be shown.
* If no date is passed in, a list of deadlines and events are shown.
* The date must be in yyyy-mm-dd format.

Examples:
* `schedule 2021-10-12` returns:
<img width="687" alt="Screenshot 2021-09-12 at 1 14 17 PM" src="https://user-images.githubusercontent.com/61085398/132973059-48277ae0-5246-413f-a45d-d514be4760d8.png">

* `schedule` returns:
<img width="682" alt="Screenshot 2021-09-12 at 1 15 45 PM" src="https://user-images.githubusercontent.com/61085398/132973110-22c84254-233b-44e2-8f6a-f414c280b359.png">

### Exit the program `bye`
Format: `bye`

## Acknowledgements
The GUI was designed with inspiration from Apple's Messages app interace.
This user guide was written following the structure of [AB3's User Guide](https://se-education.org/addressbook-level3/UserGuide.html).
