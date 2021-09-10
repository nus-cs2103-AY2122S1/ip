# User Guide
Duke is a chat-bot style task organizer that allows users to 
keep track of tasks that needs to be completed.

## Installation
1. Download the latest duke.jar file from ```Releases```.
2. Place the jar file in your preferred file location.
3. Open up the ``command prompt`` and change directory to where duke.jar is located.
4. Run the jar file using ``java -jar duke.jar``
## Features 
- Add/delete tasks
- 3 different categories of tasks (ToDo, Event, Deadline)
- Complete tasks
- Find tasks
- List tasks
- Sort tasks
### Find tasks

Finds the all tasks that matches the user input keyword.

### Sort tasks

Sorts all the tasks by their name or by the event type and lists it all out.

## Usage

### `Deadline` - Adds a deadline task

Adds a Deadline task to the list in the format of:<br/>`deadline {deadline name} /by {dd/mm/yyyy hhmm}`

Example of usage:<br/>
`deadline meeting /by 12/12/2021 1400`

Expected outcome: <br/>
```
I've added this task:
[D][] meeting (by: Dec 12 2021 Sun 14:00)
You have 1 tasks left!
```

### `Sort` - Sorts the list of tasks

Sort command syntax:<br/>
`sort {name/type}`

Example of usage:<br/>
`sort name`

Expected outcome: <br/>
```
Tasks are sorted alphabetically.
```
