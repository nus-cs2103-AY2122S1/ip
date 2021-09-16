# Mako User Guide
Besides being an expert fire bender, Mako is also great at helping you manage your todos, events and deadlines! The application uses quick-text inputs via a Command Line Interface (CLI) while having an excellent Graphical User Interface (GUI). Organise your tasks by using Mako today! 

* [Quick Start](#quick-start-guide)
* [Features](#features)
* [Usage](#usage)
* [Command Summary](#command-summary)

### Quick Start Guide

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the Mako.jar file from the repository.
3. Move the file to a folder you would want to use as the home folder for Mako.
4. Double-click the file to start the app. The GUI similar to the one shown below should appear in a few seconds.
5. Refer to the Features below for details of each command.

<img src="https://domszy.github.io/ip/Ui.png" width="400" >

# Features 
* insert tasks which are categorised as todos, deadlines and events
* mark your tasks as done
* remove your tasks from the list 
* view your scheduled events and deadlines on a date 
* find tasks with a specific keyword

### Note about the command format:
Words in `{ }` are the parameters to be supplied by the user. 

e.g. `todo {task's name}` where {task's name} is the user's input. 

# Usage

### View tasks: `list`
showcases the list of tasks given to Mako. Mako also indicates the type of the task and whether the task have been completed or not.

Format: 
`list`

Example: 
`````
Here are the tasks in your list: 
1. [T] [✓] homework 
2. [D] [✕] CS2100 Assignment (by: 2nd Sep 2021 13:00)
3. [E] [✕] Meeting (at: 2nd Sep 2021 15:00)
`````
* [T] indicates that the task is a todo task
* [D] indicates that the task is a deadline
* [E] indicates that the task is an event
* [✕] indicates that the task have not been completed
* [✓] indicates that the task have been completed

### Insert todos: `todo`
Todos are tasks that doesn't have a set deadline or time frame. use the `todo` keyword to insert a todo into Mako. 

Format: 

`todo {task's name}`

Example of `todo` command:

`todo homework`

Expected outcome:
`````
Got it. I've added this task:
[T] [✕] homework
Now you have 1 task in the list.
`````

### Insert deadlines: `deadline`
Deadlines are tasks that have a set completion or submission date and time. use the `deadline` command to insert a deadline into Mako.

Format: 

`deadline {task's name} /by {the deadline of the task in the format: Year-Month-Date Hour:Minute}`

Example of `deadline` command:

`deadline assignment /by 2021-09-15 12:00` 

Expected outcome:
`````
Got it. I've added this task:
[D] [✕] assignment (by: 15 Sep 2021 12:00)
Now you have 1 task in the list.
`````

### Insert events: `event`
Events are tasks that have a set date and time. use the `event` command to insert an event into Mako.

Format: 

`event {task's name} /at {time of the event in the format: Year-Month-Date Hour:Minute}`

Example of `event` command: 

`event carnival /at 2021-09-15 20:00`

Expected outcome:
`````
Got it. I've added this task:
[E] [✕] carnival (by: 15 Sep 2021 20:00)
Now you have 1 task in the list.
`````

### Mark a task as done: `done`
marks a task as completed. If the task is marked as comepleted, performing this task again marks the task as uncompleted.
* Marks the task as completed at the specified INDEX.
* The index refers to the index number shown in the task list when viewing tasks

Format: 

`done {task's reference number on the list}`

Example of `done` command: 

`list` then `done 2`, then `done 2` again. Suppose the "homework" todo is the 2nd task in the list.

Expected outcome:
`````
Nice! I've marked this task as done: 
[T] [✓] homework

I've marked this task as not done:
[T] [✕] homework
`````

### Remove tasks: `remove`
remove a task from the task list
* Removes a task at the specified INDEX.
* The index refers to the index number shown in the task list when viewing tasks

Format: 

`remove {task's reference number on the list}`

Example of `remove` command: 

`list` then `remove 2`. Suppose you have 2 tasks and the "homework" todo is your 2nd task in your list.  

Expected outcome:
`````
Noted. I have removed the task:
[T] [✓] homework
Now you have 1 task in the list.
`````

### Find tasks with specific key word: `find`
finds tasks with the search term in its name. 

Format: 

`find {search term}`

Example of `find` command: 

`find key`. 

Expected outcome: 
`````
Here's the matching tasks in your list:
1. [E] [✓] keynote speech (at: 18 Sep 2021 12:00)
2. [T] [✓] bake key lime cake for movie event 
3. [D] [✕] give key to Sasha (by: 19 Sep 2021 15:00)
`````

### View schedule: `schedule`
sorts tasks chronologically that are on a given date.

Format: 

`schedule {date of schedule in the format: Year-Month-Day}`

Example or `schedule` command: 

`schedule 2021-01-01` 

Expected outcome: 
`````
Here's your schedule for Jan 01 2021:
1. [E] [✕] BTS concert (at: 01 Jan 2021 12:00)
2. [D] [✕] CS2100 assignment (by: 01 Jan 2021 15:00)
3. [E] [✕] Meeting with prof Aaron (at: 01 Jan 2021 20:00)
`````

## Command Summary

| Action | Format | Example |
| ------ | ------ | ------ |
| todo | `todo {task's name}` | `todo homework` |
| deadline | `deadline {task's name} /by {Year-Month-Day Hour:Minute}` | `deadline assignment /by 2021-09-15 13:00` |
| event | `event {task's name} /at {Year-Month-Date Hour:Minute}` | `event carnival /at 2021-09-15 20:00` |
| list | `list` | `list` |
| done | `done {task's reference number}` | `done 2` |
| remove | `remove {task's reference number}` | `remove 2` |
| find | `find {search term}` | `find word` |
| scedule | `schedule {Year-Month-Day}` | `schedule 2021-09-15` |
