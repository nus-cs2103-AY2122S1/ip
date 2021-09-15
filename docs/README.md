# User Guide

Duke is a program used for helping you keep track of your own tasks at **anytime**!
Duke can track:
1. Things **to do**
2. Your **deadlines** :clock3:
3. Any **events** that are happening :tada:

## Features 

### Managing Tasks

Simply enter information of the task you want to keep note of, and Duke will do the memory work for you!
You can also mark them as done :white_check_mark:
and also delete :wastebasket: them when they are not needed anymore.
 
### Tagging

You can tag tasks :label: by adding tags to them. The tags will allow you to group up tasks that are similar or
help you prioritize your work!

## Usage

### `help` - Opens the help menu

Opens the help text, where you can find all the available commands

**Usage:** 

`help`


### `list` - View your list of tasks

Displays a list of your tasks and their status / tags

**Usage: `list`**

**Expected outcome:** 

Your lists of tasks will be displayed in the order you added them

### `todo` - Add a todo item to your task list

Adds a todo item to your list of tasks

**Usage:**

`todo (description)`

**Expected outcome:**

A new todo with the given `description` is added to your task list

### `deadline` - Add a deadline to your task list

Adds a deadline to your list of tasks

**Usage:** 

`deadline (description) /by (yyyy-mm-dd)`

**Expected outcome:**

A new deadline with the given description and due date is added to your task list

### `event` - Add an event to your task list

Adds an event to your list of tasks

**Usage:** 

`event (description) /at (timing)`

**Expected outcome:**

A new event with the given `description` and `timing` is added to your task list

### `done` - Mark a task as done

Marks a certain task in your list as completed

**Usage:** 

`done (index)`

**Expected outcome:**

The task with the number `index` (as seen in the list given by the `list` command) is marked as done.

### `delete` - Deletes a task from your list

Deletes a certain task from your list

**Usage:** 

`delete (index)`

**Expected outcome:**

The task with the number `index` (as seen in the list given by the `list` command) is deleted.

### `find` - Finds all tasks that contains a given keyword

Finds all tasks in your task list that contains a given keyword

**Usage:** 

`find (keyword)`
Maximum one keyword only. If `find homework assignment` is entered, Duke will attempt to find
all tasks that contain `"homework assignment"`.

**Expected outcome:**

A list of tasks that contain the given keyword will be displayed.

### `tag` - Tags a given task with a given set of tags

Tags a task with tags with the given name(s).

**Usage:** 

`tag (index) (tagname) (tagname)`

Can include multiple tag names, as long as they are separated by a whitespace. 
For example: `tag 1 urgent red` will tag the task at index 1 with the tags "urgent" and "red".


**Expected outcome:**

The task with the number `index` (as seen in the list given by the `list` command) is tagged with the given tags.