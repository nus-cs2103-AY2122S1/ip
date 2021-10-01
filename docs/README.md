# Duke User Guide
Duke is an awesome way to save your tasks and interact with a bot.
## Features
### 3 different tasks: todo, event, deadline

Duke can store to-dos, events and deadlines.

### Search for tasks
Duke can search for your tasks when given a matching sub-word of the task.
### View upcoming tasks
Duke can list out all the tasks, including upcoming ones in sorted order.
## Getting started
Download Duke from [here](https://github.com/chunweii/ip/releases).
Ensure that you have [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) installed.
Open the program, enter your name and a file name for your data.
Start managing tasks!
Note: All your tasks are saved in the file that you entered when starting the chatbot.

## Usage
Note: All date and time format should be in DD/MM/YYYY HH:mm, where either date or time is optional.

### `todo` - Add a new todo task
`todo DESCRIPTION`

Adds a new todo task to be saved by Duke

Example of usage:

`todo Buy Milk`

Expected outcome:  A todo task with description "Buy Milk" will be added.

### `event` - Add a new event task
`event DESCRIPTION /from START_TIME [/to END_TIME]`

Adds a new event task to be saved by Duke.

Example of usage:

`event Mid-Term /from 19/9/2021`

Expected outcome:  An event task with description "Mid-Term" that starts on 19 September 2021 is added.

### `deadline` - Add a new deadline task
`deadline DESCRIPTION /by DEADLINE`

Example of Usage:

`deadline tP v1.1 /by 1/10/2021`

Expected outcome: A deadline task with description "tP v1.1" that needs to be completed by 1 October 2021 is added.

### `find` - Find a task based on a keyword
`find KEYWORD`

Example of usage:

`find project`

Expected outcome: Displays a list of all tasks containing the string "project".

### `upcoming` - View upcoming tasks
`upcoming`

Expected outcome: Displays the list of all upcoming tasks, sorted in chronological order.

### `done` - Mark a task as completed
`done TASK_NUMBER`

Example of usage:
`done 1`

Expected outcome: Marks the task at task no. 1 as done (with a cross).

### `delete` - Delete a task
`delete TASK_NUMBER`

Example of usage:
`delete 1`

Expected outcome: Deletes the task at task no. 1.

### `list` - Display all tasks
`list`

Expected outcome: Displays all the tasks saved in Duke.

### `bye` - Exits the program
`bye`

Expected outcome: Closes the Duke chat.

### `help` - View help list
`help COMMAND`

Shows the help message for the command.
List of commands:

- bye
- deadline
- delete
- done
- event
- find
- help
- list
- todo
- upcoming
