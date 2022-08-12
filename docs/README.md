# User Guide 

## Quick Start
1. Ensure that `Java 11` or higher is installed in your computer.
2. Download the latest version of `Duke.jar` from here.
3. Double click on it to run.
![Sample output for list](https://github.com/marcuspeh/iP/blob/master/docs/Ui.png?raw=true)
4. You should be able to type in commands. Refer to usage or type in `help` to 
view the available commands in the application.

## Features 
   
### Manage Tasks (Event, Todo and Deadline)

Duke will keep track of Event, Todo and Deadline for you. You will be
able to save and delete new Tasks as well as marking task as done.

### Task List

You can see all the task available. Duke will show you the task you saved as a list, 
including the type of task it is and whether it is completed or not.

### Saving Task

Task entered will no longer be lost! Duke will store the task you have
entered and load it whenever you run duke. 

## Usage
Notes on features:
* Items in <> are the parameters to be supplied by the user.
    e.g. in `todo <description>`, description is a parameter which can be used as `todo CS2103T iP`.

* Extraneous parameters for commands that do not take in parameters (such as help, list and quit) will be ignored.
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.
 
* `<N>` in sample output commands refers to the number of task saved.
    e.g. If there is only 1 task saved, `<N>` will be replaced with `1`.
    
* `<date/time>` for commands refer to date time in this format: `dd/MM/yy hhmm`
    e.g. To enter 10 Sep 2021 1am, `<date/time>` will be `10/09/21 0100`.

### `help` - Viewing all the commands

Shows a message displaying all the available commands Duke recognises.

### `todo` - Adding a Todo

Adds a Todo to task list.

Format: `todo <description>`

Example of usage: `todo CS2103T iP`

Expected outcome: A todo containing the task description will be added to the task list.

```
Got it. I've added this task:
[T][ ] CS2103T iP
Now you have <N> task(s).
```

### `deadline` - Adding a Deadline

Adds a Deadline to task list.

Format: `deadline <description> /by <date/time>`

Example of usage: `deadline CS2103T iP /by 10/09/21 1600`

Expected outcome: A deadline containing the task description and due date will be added
to the task list.

```
Got it. I've added this task:
[D][ ] CS2103T iP (by: 10/09/21 1600)
Now you have <N> task(s).
```

### `event` - Adding an Event

Adds a Event to task list.

Format: `event <description> /at <date/time>`

Example of usage: `event CS2103T lecture /at 10/09/21 1600`

Expected outcome: A event containing the task description and due date will be added
to the task list.

```
Got it. I've added this task:
[E][ ] CS2103T lecture (at: 10/09/21 1600)
Now you have <N> task(s).
```

### `list` - Viewing task list

Shows a message containing all the task that is stored

Format: `list`

![Sample output for list](https://github.com/marcuspeh/iP/blob/master/docs/list.png?raw=true)

### `done` - Marking a task

Marks a task as done. Number refers to the position of the task
in the task list when it is printed out.

Format: `done <number>`

Example of usage: `done 1`

Expected outcome: Marks a task as done.

```
Nice! I've did mark this task as done:
[T][X] CS2103T iP
```

### `delete` - Deleting a task

Deletes a task from task list. Number refers to the position of the task
in the task list when it is printed out.

Format: `delete <number>`

Example of usage: `delete 1`

Expected outcome: Delete a task.

```
Noted. I've removed this task:
[T][X] CS2103T iP
Now you have <N> task(s).
```
### `find` - Finding a task

Find task(s) that matches the description.

Format: `find <description>`

Example of usage: `find cs2103t`

Expected outcome: Finds a task. If there is no task found, a message 
will still be printed out.

```
Here are the matching task(s) in your list:
[D][ ] CS2103T iP (by: 10/09/21 1600)
[T][ ] CS2103T tP
```

