# Duke++ User Guide
Duke++ is a desktop chatbot made to help you with task management, capable of storing and organizing different types of tasks.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest jar file from [here](https://github.com/Zenlzb/ip/releases).
3. Double-click the file to start the program.

*Note: A data folder will be created to store data entered into the program even after closing it*

## Features 
### Task management
Types of tasks:
1. Todo: The simplest form of task, simply for recording something that needs to be done
2. Deadline: For tasks that have to be completed by a certain date
3. Event: For events that are happening at a certain date/time

Task operations supported:
1. Create Task
2. View Tasks
3. Complete Task
4. Delete Task
5. Search Tasks
6. Tag Tasks

## Usage
*Capital letters under format represents user input, and square brackets represent optional input*

---

### `todo, deadline and event` - Create Task
Creates a new task, of type todo, deadline or event

*Note: use the formats command to view acceptable date formats*

Format: 

`todo DESCRIPTION [/t TAGS]`    Example: `todo Flower plants /t Gardening`

`deadline DESCRIPTION /by DATE [/t TAGS]`    Example: `deadline Assignment 3 /by 22-09-2021 1500 /t Important`

`event DESCRIPTION /at DATE [/t TAGS]`    Example: `event Team meeting /by Apr 26 2021 12:00 pm /t Discussion`

---

### `list` - View list of Tasks
Displays a list of tasks, and each index they are assigned

Format: 

`list`

---

### `done` - Complete Task
Completes task with specified index

Format: 

`done INDEX`    Example: `done 5` to complete task with index 5

---

### `delete` - Delete Task
Deletes task with specified index

Format: 

`delete INDEX`    Example: `delete 5` to delete task with index 5

---

### `find` - Search Tasks
Finds all tasks that contain the specified search term

Format: 

`find SEARCH_TERM`    Example: `find Assignment` to view a list of all tasks that contain "Assignment"

---

### `tag` - Tag Tasks
Tags task at specified index with specified tags

Format: 

`tag INDEX TAG [ADDITIONAL_TAGS]`    Example: `tag 5 Important Crucial` to tag task with index 5 with "Important" and "Crucial"

---

### `formats` - View Date Formats
Displays a list of valid date formats for use with deadline and event

Format: 

`formats`

---

### `bye` - Exits program
Exits the program

Format: 

`bye`

---
