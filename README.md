# User Guide

![image of Biscuit](https://marcustxk.github.io/ip/Ui.png)

Biscuit is a **desktop chat-bot app for managing tasks, optimised for use via a Command Line Interface** (CLI) while
still having the benefits of a Graphical User Interface (GUI). It was made as part of CS2103T's individual project
component, applying the best practices learnt in CS2103T Software Engineering.

* [Quick Start](#quick-start)
* [Features](#features)
   * [Adding a To Do task: `todo`](#adding-a-to-do-task-todo)
   * [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline)
   * [Adding an Event task: `event`](#adding-an-event-task-event)
   * [Listing Tasks: `list`](#listing-tasks-list)
   * [Marking a Task as done: `done`](#marking-a-task-as-done-done)
   * [Deleting a Task: `delete`](#deleting-a-task-delete)
   * [Finding a Task: `find`](#finding-a-task-find)
   * [Exiting the Program: `bye`](#exiting-the-program-bye)
   * [Saving the data](#saving-the-data)
* [FAQ](#faq)
* [Command summary](#command-summary)

---
## Quick start

1. Ensure you have [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or above installed in your
   Computer.
2. Download the latest biscuit.jar from [here](https://github.com/MarcusTXK/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder for `Biscuit`.
4. Double-click the file to start the app or run the jar with the following command from the home folder for `Biscuit`: 
   `java -jar biscuit.jar`.
5. Type in commands in the command box and press Enter to execute it.
---
## Features
There three types of tasks:
* To Do - A task without any fixed deadlines
* Deadline - A task with a set deadline date
* Event - A task with a date and time

### Adding a To Do task: `todo`
Adds a todo list to the task list.  
Format: `todo <task description>`  
Examples:
* `todo borrow book`

### Adding a Deadline task: `deadline`
Adds a deadline list to the task list.  
Format: `deadline <task description> /by dd-MM-yyyy`  
Examples:
* `deadline return book /by 22-02-2022`

### Adding an Event task: `event`
Adds an event list to the task list.  
Format: `event <task description> /by dd-MM-yyyy HH:mm`  
Examples:
* `event project meeting /at 22-02-2021 22:00`

### Listing Tasks: `list`
Lists all tasks in the task list as well as the task types and status.
Format: `list`  
Examples:
* `list`

### Marking a Task as done: `done`
Marks a task in the task list as done by their task number from `list`.  
Note: You can specify multiple tasks by adding a space between each number.
Format: `done <index>`  
Examples:
* `done 1`
* `done 1 2 3`

### Deleting a Task: `delete`
Deletes a task in the task list by their task number from `list`.  
Note: You can specify multiple tasks by adding a space between each number.
Format: `delete <index>`  
Examples:
* `delete 1`
* `delete 1 2 3`

### Finding a Task: `find`
Finds all tasks in the task list matching the key word.
Format: `find <key word>`  
Examples:
* `find book`

### Exiting the Program: `bye`
Exits from the program.
Format: `bye`  
Examples:
* `bye`

### Saving the data
Biscuit's task list data are saved in the hard disk automatically after any command that changes data. There is not need
to save data.
* By default, files are stored in the home folder of `biscuit.jar` in `data/biscuit.csv`
* If you wish to customise the location of the save file or load a save file from a different file, run the jar with
  the following command: `java -jar biscuit.jar <custom file path>`
   *  Example: `java -jar biscuit.jar custom/file/path/fileName.csv`

---
## FAQ
**Q:** How do I transfer mu data to another Computer?  
**A:** Download `biscuit.jar` in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous `biscuit.jar` home folder.

---
## Command summary

|Action      | Format : Examples                                                                              |
|------------|------------------------------------------------------------------------------------------------|
| To Do     | `todo <task description>` : `todo borrow book`                                                  |
| Deadline  | `deadline <task description> /by dd-MM-yyyy` : `deadline return book /by 22-02-2022`            |
| Event     | `event <task description> /by dd-MM-yyyy HH:mm`  : `event project meeting /at 22-02-2021 22:00` |
| List      | `list`                                                                                          |
| Done      | `done <index>` : `done 1`, `done 1 2 3`                                                         |
| Delete    | `delete <index>` : `delete 1`, `delete 1 2 3`                                                   |
| Exit      | `bye`                                                                                           |
