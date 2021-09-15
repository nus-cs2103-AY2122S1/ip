# User Guide

This repo contains the code for the Misaki chatbot as part of CS2103T: Software Engineering. Misaki is a desktop chatbot for managing and storing tasks. 

Start chatting with this adorable Japanese bot to manage your tasks effectively! („• ◡ •„) ♡
___
## Quick start
1. Ensure you have Java `11` or above installed.
2. Download the latest `.jar` from [here].
3. Move the file to the desired path as the home folder for your Misaki. 
4. Right-click the file and open the app. 
5. Try entering help and other commands. You should see a similar GUI as below.

![Image of ](Ui.png)

5. Type the command in the command box and press `Enter` to execute it.
   Some example commands you can try:
   * `help`: Shows all available command lines.
   * `todo` `complete assignment`: Adds a todo task `complete assignment` to the list.
   * `deadline` `submit iP /by 2021-09-17 23:59`: Adds a deadline task `submit iP (by: 17 Sep 2020 23:59pm)` to the list.
   * `event` `birthday party /at 2021-09-19 18:00`: Adds an event task `birthday party (at: 19 Sep 2021 18:00pm)` to the list.
   * `list`: Lists all the tasks in the list.
   * `done` `2`: Marks the task at the `2` index in the list as done.
   * `delete` `2`: Deletes a task at the `2` index from the list.
   * `find` `assignment`: Finds all tasks with matching keyword `assignment`.
   * `bye`: Exits the program.
___
## Features
* Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo submit assignment`.
### 1. Add a todo task: `todo`
Adds a todo task to the list.<br>
Format: `todo DESCRIPTION`

### 2. Add a deadline task `deadline`
Adds a deadline task to the list.<br>
Format: `deadline DESCRIPTION /by yyyy-mm-dd HH:mm`

### 3. Add an event task `event`
Adds an event task to the list.<br>
Format: `event DESCRIPTION /at yyyy-mm-dd HH:mm`

### 4. List all tasks `list`
Lists all the tasks in the list.<br>
Format: `list`

### 5. Mark task done `done`
Marks a task in the list as done.<br>
Format: `done INDEX`
* Marks the task at the specified index as done.
* The index refers to the index number of task in the list.
* The index must be a positive integer 1, 2, 3, ...

### 6. Delete task `delete`
Deletes a task from the list.<br>
Format: `delete INDEX`
* Deletes the task at the specified index from the list.
* The index refers to the index number of task in the list.
* The index must be a positive integer 1, 2, 3, ...

### 7. Find tasks `find`
Finds all tasks with matching keyword.<br>
Format: `delete KEYWORD`
* Finds all tasks containing the keyword.

### 8. Get help `help`
Shows all available commands that the Masaki bot understands.<br>
Format: `help`

### 9. Exit program `bye`
Exits the program.<br>
Format: `bye`

### 10. Save data
Misaki saves data automatically in the local storage after every valid user input.
___
## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo DESCRIPTION` <br> e.g., `todo complete assignment`
**Deadline** | `deadline DESCRIPTION /by yyyy-mm-dd HH:mm` <br> e.g., `deadline submit iP /by 2021-09-17 23:59`
**Event** | `event DESCRIPTION /at yyyy-mm-dd HH:mm` <br> e.g., `birthday party /at 2021-09-19 18:00`
**List** | `list`
**Done** | `done INDEX`<br> e.g., `done 2`
**Delete** | `delete INDEX`<br> e.g., `delete 2`
**Find** | `find KEYWORD`<br> e.g., `find concert`
**Help** | `help`
**Exit** | `bye`
