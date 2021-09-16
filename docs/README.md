# User Guide

Duke is an all-in-one task management system that helps 
you organise your tasks. Interact with Duke using a 
CLI (Command Line Interface) with a chatbot-like user interface.
You will never have to leave the keyboard to interact with Duke.

## Features 
* [Listing all tasks : `list`](#listing-all-tasks--list)
* [Adding a todo : `todo`](#adding-a-todo--todo)
* [Adding an event : `event`](#adding-an-event--event)
* [Adding a deadline : `deadline`](#adding-a-deadline--deadline)
* [Deleting a task :`delete`](#deleting-a-task-delete)
* [Marking a task as complete : `done`](#marking-a-task-as-complete--done)
* [Tagging a task : `tag`](#tagging-a-task--tag)
* [Finding a task :`find`](#finding-a-task-find)
* [Close duke : `bye`](#close-duke--bye)


## Usage
### Listing all tasks : `list`
Shows a list of all tasks in the task list.

Format: `list`

Expected outcome:

<img  width="400" alt="Expected outcome of list" src="https://user-images.githubusercontent.com/39267610/133229641-1a22f34b-35ff-40cb-a6b9-acac365b1712.png">


### Adding a todo : `todo`

Adds a todo into the task list

Format: `todo TASK_NAME`

Examples:
* `todo Go to the gym`
* `todo Complete homework`

Expected outcome:

<img  width="400" alt="Expected outcome of todo" src="https://user-images.githubusercontent.com/39267610/133230344-3aed4ece-c29e-4cf0-86c4-07f149435ec2.png">

### Adding an event : `event`

Adds a event into the task list

Format `event EVENT_NAME /at DATETIME`

Examples:
* `event Career Fair /at 31/12/2021 1800`
* `event Gym session with Mark /at 6/12/2021 0800`

Expected outcome:

<img  width="400" alt="Expected outcome of event" src="https://user-images.githubusercontent.com/39267610/133231593-5288aee8-68c8-499f-8d4f-13324caeecbb.png">

### Adding a deadline : `deadline`

Adds a deadline into the task list.

Format: `deadline DEADLINE_NAME /by DATETIME`

Examples:
* `deadline CS2100 homework /by 31/12/2021 2359`
* `deadline Group project /by 6/9/2021 1200`

Expected outcome:

<img  width="400" alt="Expected outcome of deadline" src="https://user-images.githubusercontent.com/39267610/133230699-b38c6b2a-3d6d-453a-ad79-1253f6bfca09.png">

### Deleting a task :`delete`

Deletes a task from the task list

Format: `delete INDEX`

Example: 
* `delete 2` deletes the second task in the list

Expected outcome:

<img  width="400" alt="Expected outcome of delete" src="https://user-images.githubusercontent.com/39267610/133231806-03ac9394-7b48-4bf9-9c5c-699de09140bf.png">

### Marking a task as complete : `done`

Marks a task as done

Format: `done INDEX`

Example:
* `done 2` marks the second task in the list as done

Expected outcome:

<img  width="400" alt="Expected outcome of done" src="https://user-images.githubusercontent.com/39267610/133231989-e69d50dc-ad20-498d-ad1b-fc0bcf2f839a.png">

### Tagging a task : `tag`

Tags a task

Format: `tag INDEX TAG_NAME`

Example: 
* `tag 1 homework` tags the task at index one with the tag of homework

Expected outcome:

<img  width="400" alt="Expected outcome of tag" src="https://user-images.githubusercontent.com/39267610/133232379-fdc1a311-3c0f-4226-8eb6-b9f54c9fff60.png">

### Finding a task :`find`

Find a task whose name or tag contains the given keyword

Format: `find KEYWORD`

Example:
* `find homework` returns a task with the name CS2100 homework as well as all tasks tagged as comwork

Expected outcome:

<img  width="400" alt="Expected outcome of find" src="https://user-images.githubusercontent.com/39267610/133232513-6818f3ee-317d-485f-93f6-5e7991b4d126.png">

### Close duke : `bye`

Exits Duke

Format: `bye`

## Summary

|Command| Format | Description|
|---|---|---|
|`list`|`list`| Shows a list of all tasks in the task list.|
|`todo`|`todo TASK_NAME`|Adds a todo into the task list|
|`event`|`event EVENT_NAME /at DATETIME`| Adds a event into the task list|
|`deadline`| `deadline DEADLINE_NAME /by DATETIME`| Adds a deadline into the task list.|
|`delete`|`delete INDEX`|Deletes a task from the task list|
|`done`|`done INDEX`|Marks a task as done|
|`tag`|`tag INDEX TAG_NAME`|Tags a task|
|`find`|`find KEYWORD`| Find a task whose name or tag contains the given keyword|
|`bye`|`bye`|Exits Duke|
