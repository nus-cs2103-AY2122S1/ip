# Aoi Todo Bot User Guide

Losing track of your tasks? Aoi Todo Bot is a todo list app to manage your daily tasks with ease.

* Features
    * [Adding a Todo: `todo`](#adding-a-todo:`)
    * Adding a Deadline: `deadline`
    * Adding a Event: `event`
    * Listing all tasks: `list`
    * Deleting a Task: `delete`
    * Exiting the program: `bye`
    
---
## Features 
Tasks in Aoi Todo Bot are split into **three** types of tasks:

### Todos
Tasks without any date/time attached to it.

### Deadlines
Tasks that need to be done before a specific date/time.

### Events
Tasks that start at a specific time and ends at a specific time.


## Usage

### Adding a Todo: `todo`

Adds a Todo to your todo list.

Format:`todo [description]`

Example: 

`todo study for test`

### Adding a Deadline: `deadline`

Adds a Deadline to your todo list.

Format: `deadline [description] /by [dd/mm/yyyy HHmm]`

Example:

`deadline submit cs2103t quiz /by 19/09/2021 2359`

Expected outcome:

Created a list

### Adding a Event: `event`

Adds an Event to your todo list.

Format: `event [description] /at [dd/mm/yyyy HHmm]`

Example:

`event team meeting /at 19/09/2021 2030`

### Listing all tasks: `list`

Lists all tasks in your todo list.

Format: `list`

### Deleting a Task: `delete`

Deletes a Task from your todo list.

Format: `delete [number]`

Example:

`delete 3`

### Exiting the program: `exit`
Exits the program.

Format: `exit`

