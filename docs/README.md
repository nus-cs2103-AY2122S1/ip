# User Guide for Duke

Duke is a desktop app for managing your tasks,  optimized for use via a Command Line Interface (CLI) while still having 
the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your contact management tasks done 
faster than traditional GUI apps.

- **Quick Start**
- **Features**
  - Viewing help: `help`
  - Adding a Task: `todo`, `deadline`, `event`
  - Listing all tasks: `list` 
  - Deleting a task: `delete` 
  - Editing a task: `edit` 
  - Marking a task as done: `done`
  - Searching for a task by name: `find` 
  - Searching for a task by date: `occurring on`
  - Quitting the program: `bye`
- **Usage**
- **FAQ**

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar. 
3. Copy the file to the folder you want to use as the home folder for your Duke. 
4. Double-click the file to start the app.

## Features 

### Viewing help

Duke will show all the available commands.

### Adding a Task

Duke can add these 3 types of tasks into your task list:
- Todo: A simple task to be done (eventually).
- Deadline: A task that has to be done by a certain date.
- Event: An event that is happening on a certain date.

### Listing all tasks

Duke will list all the tasks you have in your task list.

### Deleting a task

Duke will delete the task from your task list.

### Editing a task

Duke can help you change the name of a task, or the date of a deadline or event.

### Marking a task as done

Duke will mark the task as done.

### Searching for a task by name

Duke will list all the tasks in your task list that contain the keyword.

### Searching for a task by date

Duke will list all the tasks in your task list that are occurring on the date.

### Quitting the program

Duke will save your task list into your computer and stop accepting input.

## Usage

### `help` - Viewing help

Duke will show all the available commands you can use.

Example of usage: 

`help`

Expected outcome:

```
Here is the list of commands I can understand:
list: displays your task list.
todo <task>: adds a todo to your task list.
deadline <task> /by <yyyy-mm-dd>: adds a deadline to your task list.
event <task> /at <yyyy-mm-dd>: adds an event to your tasks list.
done <task number>: marks the specific task as done.
delete <task number>: deletes the specific task from your task list.
occurring on <yyyy-mm-dd>: displays tasks occurring on the specified day.
find <keyword>: displays tasks that contain the keyword.\n"
edit <n or d> <task number> /to <new name or date>: changes the name or date of the task.
bye: quits the program."
```

### `todo` - Adding a Todo

Duke will add a Todo to your task list.

Example of usage:

`todo eat peanuts`

Expected outcome:

```
Got it. I've added this task:
[T][ ] eat peanuts
Now you have 1 task in the list.
```

### `deadline` - Adding a Deadline

Duke will add a Deadline to your task list.

Example of usage:

`deadline shower /by 2021-09-09`

Expected outcome:

```
Got it. I've added this task:
[D][ ] shower (by: Sep 09 2021)
Now you have 2 tasks in the list.
```

### `event` - Adding an Event

Duke will add an Event to your task list.

Example of usage:

`event party /at 2021-09-09`

Expected outcome:

```
Got it. I've added this task:
[E][ ] party (at: Sep 09 2021)
Now you have 3 tasks in the list.
```

### `list` - Listing all tasks

Duke will list all the tasks in your task list.

Example usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] eat peanuts
2. [D][ ] shower (by: Sep 09 2021)
3. [E][ ] party (at: Sep 09 2021)
```

### `delete` - Deleting a task

Duke will delete the task from your task list.

Example usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] eat peanuts
Now you have 2 tasks in the list.
```

### `edit` - Editing a task

Duke will change the name of your task, or the date of your Deadline or Event.

Example usage:

`edit n 1 /to bathe`

Expected outcome:

```
Okay! I've updated your task to
[D][ ] bathe (by: Sep 09 2021)
```

Example usage:

`edit d 2 /to 2021-10-10`

Expected outcome:

```
Okay! I've updated your task to
[E][ ] party (on: Oct 10 2021)
```

### `done` - Marking a task as done

Duke will mark your task as done.

Example usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[D][X] bathe (by: Sep 09 2021)
```

### `find` - Searching for a task by name

Duke will find tasks with names that contain the keyword.

Example usage:

`find bathe`

Expected outcome:

```
Here are the matching tasks in your list:
[D][X] bathe (by: Sep 09 2021)
```

### `occurring on` - Searching for a task by date

Duke will find tasks that occur on the date.

Example usage:

`occurring on 2021-10-10`

Expected outcome:

```
You have 1 task occurring on Oct 10 2021:
[E][ ] party (on: Oct 10 2021)
```

### `bye` - Quitting the program

Duke will save your task list into your computer and stop accepting input.

Example usage:

`bye`

Expected outcome:

```
Bye little dude! Remember, the very things that hold you down are gonna carry you up and up and up!
```

## FAQ

Q: How do I transfer my data to another Computer?

A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the
data of your previous AddressBook home folder.