# User Guide
## The Seagull Tasker
Welcome to the User Guide for the Seagull Tasker! Follow this
guide to easily get your next handy Task Manager set up and
ready to start keeping track of your tasks!

The Seagull Tasker is a standalone application that can manage
your _todos_, _deadlines_ and _events_, **all from one app**! Using the
integrated task list, you can easily add and remove tasks,
mark a task as done, as well as searching through them easily!

<br>
Sample Image of the Seagull Tasker being used: 

![Ui Sample Image](Ui.png)


## Installation
Go to the [Releases](https://github.com/TLChicken/ip/releases) page
for the Seagull Tasker, and download the latest executable version.
Place it in a folder that is convenient to access. The folder
that contains the save file for your tasks would be created
in this folder.

### To use the app:
1. First check that your computer has Java 11 or above.
1. Next, double-click the Seagull Tasker to start the app!
1. Test some commands! Send `help` to the Seagull Tasker to view the help screen.

## Features 

### Task Tracking

The Seagull Tasker can keep track of 3 types of tasks:
* Todos - Simple tasks!
* Deadlines - Set a deadline by including a date!
* Events - Include the location of your task!

Fully make use of it to organise your life!!!

### Search Feature
Use the `find` command to easily search through your tasklist
using keywords!

### Local XML Save Data
Your tasklist would be saved in your local storage as an XML file.
Hence data in the save file would be structured better than if a plaintext save file
was used, and it could also be read using an external XML file editor!

Easily transfer your data across devices by sending the save file over.

## Usage

Info: When describing the syntax of a command,
`<` and `>` may be used to enclose a description of the type of thing to include in the command.
When using the command in the app, please do not type `<` nor `>`.

### `help` - Show the help screen

The Seagull Tasker will show you a summary of the available commands
and their syntax, right from within the app.

Example of usage: 

`help`

Expected outcome:

![Help Screen](Help.png)




### `todo` - Add a new todo task

The Seagull Tasker will create a new todo task and add it to your task list.

Example of usage:

`todo Finish iP`

Expected outcome:


```
Ok, I have added this task: 
[T][ ] Finish iP
Current total amount of tasks in list: 1
```


### `deadline` - Add a new deadline task

The Seagull Tasker will create a new deadline task and add it to your task list.
A deadline task has a name/description as well as a date due.
The date provided must be in the format: **'D/M/YY', 'DD/MM/YYYY' or 'DD Month YYYY'**.

Syntax: `deadline <taskName> /by <date>`

Example of usage:

`deadline iP /by 17/9/2021`

Expected outcome:

```
Ok, I have added this task: 
[D][ ] iP (by: 17 September 2021)
Current total amount of tasks in list: 2
```


### `event` - Add a new event task

The Seagull Tasker will create a new event task and add it to your task list.
An event task contains a name/description as well as an "/at" string to store additional details.

Syntax: `event <taskName> /at <additionalDetails>`

Example of usage:

`event CS2103T Finals /at Home`

Expected outcome:

```
Ok, I have added this task: 
[E][ ] CS2103T Finals (at: Home)
Current total amount of tasks in list: 3
```

### `list` - List out all tasks in task list

Shows all the current tasks being managed by your Seagull Tasker.

Example of usage:

`list`

Expected outcome:

```
Here are your current tasks: 
1. [T][ ] Finish iP
2. [D][ ] iP (by: 17 September 2021)
3. [E][ ] CS2103T Finals (at: Home)
```

### `done` - Mark a task as done

Marks a task in your task list as completed. Requires an integer
to specify which task in the task list to mark as done.

Example of usage:

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] iP (by: 17 September 2021)

```

### `delete` - Delete a task

Deletes a chosen task from the task list. Requires an integer
to specify which task in the task list to delete.

Example of usage:

`delete 1`

Expected outcome:

```
Ok, this task has been removed:
[T][ ] Finish iP
Current total amount of tasks in list: 2
```

### `find` - Find all tasks with a certain keyword

Finds all tasks with a certain name in it's **task name**.
Note that each search can only use one **keyword**.


Example of usage:

`find CS`

Expected outcome:

```
Here are the matching tasks found: 
1. [E][ ] CS2103T Finals (at: Home)
```

### `bye` - Close the app

Use this command to close the Seagull Tasker.

Example of usage:

`bye`

Expected outcome: The app shuts down.




## Command Summary






