# Ligma User Guide
Ligma is a new task managing app. Users interact with Ligma through its Graphical User Interface (GUI).<br />
Here's how it looks like:<br />
![Ligma GUI Screenshot](Ui.png)

## Features
* Viewing help `help`
* Adding tasks
  1. Todos `todo`
  2. Events `event`
  3. Deadlines `deadline`
  4. Recurring tasks `recur`
* Deleting tasks `delete`
* Marking tasks as done `done`
* Finding tasks with search term `find`
* Listing all tasks `list`
* Exiting the program `bye`

## Usage
### `help` - Viewing help

Shows a message explaning every possible user command.<br />

![help](screenshots/help.png)

### Adding tasks
#### `todo` - Todos

Adds a todo task to the task list: `todo {description}`<br />

![todo](screenshots/todo.png)

#### `event` - Events

Adds an event task to the task list: `event {description} /at yyyy-mm-dd`<br />

![event](screenshots/event.png)

#### `deadline` - Deadlines

Adds a deadline task to the task list: `deadline {description} /by yyyy-mm-dd`<br />

![deadline](screenshots/deadline.png)

#### `recur` - Recurring tasks

Adds a recurring task to the task list:<br />
`recur {description} /every {d/w/m/y} {time/day of week/date/month}`<br />
d, w, m, y: flags representing frequency of task. The flags means day, week, month, year respectively. <br />
* time: 0000-2359 format
* day of week: monday-sunday
* date: 1-31
* month: january-december<br />

![recur](screenshots/recur.png)

### `delete` - Deleting tasks

Deletes a task from task list according to its index in the list: `delete {index}`<br />

![delete](screenshots/delete.png)

### `done` - Marking tasks as done

Marks a task from task list as completed based on its index in the list: `done {index}`<br />

![Done](screenshots/done.png)

### `find` - Finding tasks containing search term

Shows the user tasks with descriptions that contain the search term: `find {target string}`<br />

![Find](screenshots/find.png)

### `list` - Listing all tasks

Shows all the tasks currently in task list: `list`<br />

![List](screenshots/list.png)

### `bye` - Exiting Ligma program

Terminates Ligma bot and ends program (although GUI remains open) : `bye`<br />

![Bye](screenshots/bye.png)
