# User Guide
Welcome to Duke, a To-Do List app.

## Features 

### Add and Delete Items

Duke includes 3 different items.
* Todo - Simply entry with a descriptor
* Deadline - Deadline with a due date
* Event - Event with a happening time

### Tagging Items

Items can be tagged for information.

### Finding Items

Items can be filtered using a keyword filter.

## Usage

### `todo [description]` - Add ToDo

Adds a ToDo item into the list. It will contain a description string.

Example of usage: 

`todo buy groceries`

Expected outcome:

Description of the outcome.

```
expected output
```


### `deadline [description] /by [YYYY-MM-DD]` - Add Deadline

Adds a Deadline item into the list. It will contain a description string and the specified deadline.

Example of usage:

`deadline submit work /by 2021-09-20`

### `event [description] /at [YYYY-MM-DD]` - Add Event

Adds an Event item into the list. It will contain a description string and the specified time.

Example of usage:

`event celebration /at 2021-09-20`

### `list` - List Items

Displays all items in the todo-list, including those marked as done {see: done} but excluding those deleted.

Example of usage:

`list`

### `find [string]` - Find Items

Shows items with string matching description.

Example of usage:

`find work`

### `done [index]` - Mark Item as Done

Marks item by index as done. Index is as shown when calling `list` command.

Example of usage:

`done 2`

### `delete [index]` - Delete Item

Deletes item at index. Index is as shown when calling `list` command.

Example of usage:

`delete 2`

### `tag [index] [tag]` - Tag Item

Adds specified tag to item at index. Index is as shown when calling `list` command.

Example of usage:

`tag 2 missed`