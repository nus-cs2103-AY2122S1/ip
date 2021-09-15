# User Guide

## Features 
## Features
1. Add todos, events, deadlines to your task list
2. Mark finished tasks as completed
3. Display all tasks that contain a specified keyword
4. Delete tasks from the task list.
5. Save a task list to offline storage.
## Usage

### `todo` - Add a todo to task list.

The todo command adds a todo task to the task list.

Example of usage: 

`todo (task name)`

Expected outcome:

![todo usage results](./todo.png?raw=true)



### `event` - Add an event to task list.

The event command adds an event task to the task list.

Example of usage:

`todo (event name) \at yyyy-mm-dd`

Expected outcome:

![event usage results](./event.png?raw=true)



### `deadline` - Add a deadline to task list.

The deadline command adds a deadline task to the task list.

Example of usage:

`deadline (deadline name) \by yyyy-mm-dd`

Expected outcome:

![deadline usage results](./deadline.png?raw=true)



### `items` - List all items on the task list.

List all the items in the task list.

Example of usage:

`items`

Expected outcome:

![list usage results](./items.png?raw=true)


### `completed` - Mark an item as completed.

Marks the specified task as completed.

Example of usage:

`completed (index)`

Expected outcome:

![completed usage results](./completed.png?raw=true)


### `delete` - Delete a task from the task list.

Delete a task specified by the index from the task list.

Example of usage:

`delete (index)`

Expected outcome:

![delete usage results](./delete.png?raw=true)


### `find` - Find a task(s) from the task list

Find all tasks that match a keyword from the task list.

Example of usage:

`find (keyword)`

Expected outcome:

![find usage results](./find.png?raw=true)


### `bye` - Save the task list offline

Saves all tasks offline into a text document.

Example of usage:

`bye`

Expected outcome:

![save usage results](./save.png?raw=true)

