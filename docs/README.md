# User Guide for Duke
Always forgetting those pesky tasks? Afraid of missing deadlines? 
Fret not! Duke remembers these tasks for you - whether they be todos, upcoming deadlines or events.

<img src="Ui.png" alt=Duke GUI>

## Features
1. Add 3 different types of tasks 
   * ToDo
   * Deadline
   * Event
2. Mark tasks as completed
3. Add tags to tasks
4. Search for a task you have added before
5. Delete any task
6. An intuitive GUI

### ToDo

Todos are tasks that have a description but no time associated with them.

### Deadline

Deadlines are tasks with a description and a date before which they must be completed.

### Events

Events are tasks with a description and a date at which they take place.


## Usage

Note: 
* Dates should be entered in the following format `yyyy-mm-dd`

### `todo <description>` - Adds a ToDo

Adds a ToDo to your task list.

Usage example:

```todo final presentation```


### `deadline <description> by <date>` - Adds a Deadline

Adds a Deadline to your task list.

Example of usage:

```deadline iP submission by 2020-12-10```


### `event <description> at <date>` - Adds an Event

Adds an Event to your task list.

Example of usage:

```event party at 2020-12-10```


### `find <query>` - Finds a task

Find tasks in Duke whose description contains any of the keywords in the query. 

Duke then shows a list of matches.

Example of usage:

```find important due soon```


### `list` - Lists all the tasks you have added

List all the tasks you have added. The list also contains information about which tasks have been completed and which tags have been added to each task.

Example of usage:

```list```


### `done <index>` - Marks tasks as completed

Marks tasks as completed. You can mark a task as done using its index.

Example of usage:

```done 3```


### `delete <index>` - Deletes a task

Delete a task from your list. You can delete a task using its index.

Example of usage:

```delete 3```


### `tag <index> <tag>` - Adds a tag to a given task.

Add a tag to a task in your list. You can add a tag to a task using its index.

Example of usage:

```tag 3 wholesome```


### `bye` - Terminates Duke.

This command bids you goodbye and exits the program.

Example of usage:

```bye```


## Acknowledgements
* [Imranr2/iP](https://github.com/Imranr2/ip) - The structure of the user guide is inspired by Imran's UG.
