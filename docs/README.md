# DukeMan User Guide
DukeMan is a simple task manager application that seeks to make your life better by helping you manage your todos, events and deadlines!

### Quick Start Guide 🤘🏻
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the DukeMan.jar file from the repository.
3. Double-click the file to start the app. 
4. Refer to the Features below for details of each command.

# Features 🤖
* insert tasks which are categorised as todos, deadlines and events
* mark your tasks as done, or remove them
* view your scheduled events and deadlines 
* find tasks with a specific keyword

### Insert todos: `todo`
Todos are tasks that doesn't have a set deadline or time frame. use the `todo` keyword to insert a todo into DukeMan. 

Format: `todo {task's name}`

Example: `todo homework` inserts the task "homework" into DukeMan


### Insert deadlines: `deadline`
Deadlines are tasks that have a set completion or submission date and time. use the `deadline` command to insert a deadline into DukeMan.

Format: `deadline {task's name} /by {the deadline of the task in the format of Year-Month-Date Hour:Minute}`

Example: `deadline assignment /by 2021-09-15 12:00` inserts the task "assignment" into DukeMan that due on 15th September 2021 on 12:00PM.


### Insert events: `event`
Events are tasks that have a set date and time. use the `event` command to insert an event into DukeMan.

Format: `event {task's name} /at {the beggining timeframe of the task in the format of Year-Month-Date Hour:Minute}`

Example: `event carnival /at 2021-09-15 20:00` inserts the event "carnival" into DukeMan that's beginning on 15th September 2021, 8:00PM.


### View tasks: `list`
showcases the list of tasks inputted into DukeMan. 

Format: `list`


### Mark a task as done: `done`
marks a task as completed. 
* Marks the task as completed at the specified INDEX.
* The index refers to the index number shown in the task list when viewing tasks

Format: `done {task's reference number on the list}`

Example: `list` then `done 2` marks the 2nd task on the tasks list as completed


### Remove tasks: `remove`
remove tasks from the task list
* Removes a task at the specified INDEX.
* The index refers to the index number shown in the task list when viewing tasks

Format: `remove {task's reference number on the list}`

Example: `list` then `remove 2` deletes the 2nd task on the tasks list


### Find tasks with specific key word: `find`
finds tasks with a similar key word in its name. 

Format: `find {key word}`

Example: `find key` finds tasks with the word "key" in its name


### View schedule: `schedule`
sorts tasks that are on a given date.

Format: `schedule {date of schedule in the format: Year-Month-Day}`

Example: `schedule 2021-01-01` prints out the list of deadlines and events on 2021-01-01 in chronological order

## Command summary
Action | Format | Example
------------ | ------------- | -------------
todo | `todo {task's name}` | `todo homework`
deadline | `deadline {task's name} /by {Year-Month-Day Hour:Minute}` | `deadline assignment /by 2021-09-15 13:00`
event | `event {task's name} /at {Year-Month-Date Hour:Minute}` | `event carnival /at 2021-09-15 20:00`
list | `list` | `list`
done | `done {task's reference number}` | `done 2`
remove | `remove {task's reference number}` | `remove 2`
find | `find {word to find}` | `find word`
scedule | `schedule {Year-Month-Day}` | `schedule 2021-09-15`

