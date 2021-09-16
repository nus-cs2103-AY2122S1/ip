---
layout: page
title: User Guide
---

This repo contains the code for the Misaki chatbot as part of CS2103T: Software Engineering.

🌸 Misaki 🌸 is a **desktop chatbot app for managing and storing tasks, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
Start chatting with this adorable Japanese bot to manage your tasks effectively! („• ◡ •„) ♡

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `misaki-v3.0.0.jar` from [here](https://github.com/hsiaotingluv/ip/releases/tag/misaki-v3.0.0).

3. Copy the file to the folder you want to use as the home folder for your Misaki.

4. Double-click the file to start the app.

5. Try entering `help` and `list` commands. You should see a similar GUI as below. 🌷
![Image of ](Ui.png)

6. Type the command in the command box and press Enter to execute it.
e.g. typing **`help`** and pressing Enter will open the help window.<br>
Some example commands you can try:

   🌼 **`help`**: Shows all available command lines.<br>

   ☘️ **`todo`**`complete assignment`: Adds a todo task `complete assignment` to the list.<br>
   
   🌼 **`deadline`**`submit iP /by 2021-09-17 23:59`: Adds a deadline task `submit iP (by: 17 Sep 2020 23:59pm)` to the
   list.<br>

   ☘️ **`event`**`birthday party /at 2021-09-19 18:00`: Adds an event task `birthday party (at: 19 Sep 2021 18:00pm)` to
   the list.<br>

   🌼 **`list`**: Lists all the tasks in the list.<br>

   ☘️ **`done`**`2`: Marks the task at the `2` index in the list as done.<br>

   🌼 **`delete`**`2`: Deletes a task at the `2` index from the list.<br>

   ☘️ **`find`**`assignment`: Finds all tasks with matching keyword `assignment`.<br>

   🌼 **`bye`**: Exits the program.<br>

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. <br>
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo submit assignment`.

</div>

### 🌸 Add a todo task: `todo`

Adds a todo task to the list.<br>
Format: `todo DESCRIPTION`

### 🌸 Add a deadline task: `deadline`

Adds a deadline task to the list.<br>
Format: `deadline DESCRIPTION /by yyyy-mm-dd HH:mm`

### 🌸 Add an event task: `event`

Adds an event task to the list.<br>
Format: `event DESCRIPTION /at yyyy-mm-dd HH:mm`

### 🌸 List all tasks: `list`

Lists all the tasks in the list.<br>
Format: `list`

### 🌸 Mark task done: `done`

Marks a task in the list as done.<br>
Format: `done INDEX`

* Marks the task at the specified `INDEX` as done.
* The index refers to the index number of task in the list.
* The index must be a positive integer 1, 2, 3, ...

### 🌸 Delete task: `delete`

Deletes a task from the list.<br>
Format: `delete INDEX`

* Deletes the task at the specified `INDEX` from the list.
* The index refers to the index number of task in the list.
* The index must be a positive integer 1, 2, 3, ...

### 🌸 Find tasks: `find`

Finds all tasks with matching keyword.<br>
Format: `delete KEYWORD`

* Finds all tasks containing `KEYWORD`.

### 🌸 View help: `help`

Shows all available commands that the Masaki bot understands.<br>
Format: `help`

### 🌸 Exit program: `bye`

Exits the program.<br>
Format: `bye`

### 🌸 Save data

Misaki saves data automatically in the local storage after every valid user input that changes the data. 
There is no need to save manually. 🌟

--------------------------------------------------------------------------------------------------------------------

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
