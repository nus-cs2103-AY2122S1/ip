# User Guide for Hagician

Hagician is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Hagician can help you manage your tasks than traditional GUI apps.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `duke.jar` from [here](https://github.com/tsiyuk/ip/releases/tag/v0.2).

1. Copy the file to the folder you want to use as the _home folder_ for your Hagician.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

1. Type the command in the command box and press Enter to execute it. <br>
   Some example commands you can try:

    * **`list`** : Lists all tasks.

    * **`todo`**`read books` : Adds a todo: read books in the tasks list.

    * **`delete`**`3` : Deletes the 3rd task shown in the current list.

    * **`bye`** : Exits the app.

1. Refer to the [Usage](#Usage) below for details of each command.

## Features

### Add, delete, edit, and list the tasks

You are able to add new tasks, delete, edit, and list all the tasks you have added. There are 3 kinds of tasks:
* __todo__: tasks with description of what needs to be done.
* __deadline__: tasks with description and deadline.
* __event__: tasks with description and happen at a specified time.

### Mark tasks as done
You can mark a task as done, which will be indicated by X at the header.

### Find tasks
You can find all the tasks that contains your query key word.

### Clone task
You can clone a task in the list and add it to the tail of the list.

## Usage

### **`list`** - List all the tasks
List all the tasks already in the task list.

__Example of usage:__

`list`

__Expected outcome:__

List all the tasks.
```
Here are the tasks in your list:
1. [T][X] buy books
2. [D][ ] project (by: 23:59 Sept 08 2021)
3. [E][ ] meeting (at: 08:00 Oct 10 2021)
4. [T][ ] return books
5. [T][ ] borrow books
```

### **`todo`** - Add a todo task
Add a todo task with description of what needs to be done.

__Example of usage:__

`todo read books`

__Expected outcome:__

Add todo `read books` to the task list. And shows you the number of tasks in the task list after the addition.
```
Got it. I've added this task:
[T][] read books
Now you have 5 works in the list.
```

### **`deadline`** - Add a deadline task
Add a deadline task with description and deadline.

__Example of usage:__

`deadline Lab1 /by 22/09/2021 14:00`

__Expected outcome:__

Add deadline task `Lab1` with the deadline time `22/09/2021 14:00`. And shows you the number of tasks in the task list after the addition.
```
Got it. I've added this task:
[D][ ] Lab1 (by: 14:00 Sep 22 2021)
Now you have 6 tasks in the list.
```

### **`event`** - Add an event task
Add an event task with description and the time when the event will happen.

__Example of usage:__

`event team meeting /at 16/09/2021 10:00`

__Expected outcome:__

Add event task `team meeting` with the time `16/09/2021 10:00`. And shows you the number of tasks in the task list after the addition.
```
Got it. I've added this task:
[E][ ] team meeting (at: 10:00 Sep 16 2021)
Now you have 7 tasks in the list.
```

### **`done`** - Mark a task as done
Mark a task specified by the index shown in task list as done.

__Example of usage:__

`done 5`

__Expected outcome:__

Mark the 5th task in the list as done.
```
Nice! I've marked this task as done:
[T][X] borrow books
```

### **`delete`** - Delete a task
Delete a task specified by the index shown in task list.

__Example of usage:__

`delete 4`

__Expected outcome:__

Delete the 4th task in the task list. And shows you the number of tasks in the task list after the deletion.
```
Got it. I've removed this task:
[T][ ] return books
Now you have 6 tasks in the list.
```

### **`edit`** - Edit an existing task
Edit an existing task specified by the index shown in task list.

__Example of usage:__

`edit 3 presentation meeting /t 02/10/2021 10:00`

__Expected outcome:__

Edit the existing 3th event, change it into `presentation meeting` with new time `02/10/2021 10:00`.
```
Got it. Here is the updated task:
[E][ ] presentation meeting (at: 10:00 Oct 02 2021)
```

### **`find`** - Find tasks
Find the tasks whose description contains the query key word provided by the user.

__Example of usage:__
`find books`

__Expected outcome:__
All the tasks whose description contains `books`.
```
Here are the matching tasks in your list:
1. [T][X] buy books
2. [T][ ] return books
3. [T][ ] borrow books
```

### **`clone`** - Clone task
Clone the task specified by the index shown in task list.

__Example of usage:__
`clone 2`

__Expected outcome:__
Clone the task specified by the index shown in task list, and add it to the tail of the list.
```
Got it. I've added this task:
[D][ ] project (by: 23:59 Sep 08 2021)
Now you have 6 tasks in the list.
```

### **`bye`** - Exit
Exit the program

__Example of usage:__
`bye`

__Expected outcome:__
Say goodbye exit the program.
```
Bye. Hope to see you again soon!
```

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo DESCRIPTION`<br> e.g., `todo read books`
**Deadline** | `deadline DESCRIPTION /by DD/MM/YYYY HH:MM`<br> e.g., `deadline Lab1 /by 22/09/2021 14:00`
**Event** | `event DESCRIPTION /at DD/MM/YYYY HH:MM`<br> e.g., `event team meeting /at 16/09/2021 10:00`
**Done** | `done INDEX`<br> e.g., `done 3`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX DESCRIPTION [/t DD/MM/YYYY HH:MM]`<br> e.g.,`edit 3 presentation meeting /t 02/10/2021 10:00`
**Find** | `find KEYWORD`<br> e.g., `find books`
**List** | `list`
**Clone** | `clone INDEX`<br> e.g., `clone 3`

Notes about the command format:
* Words in `UPPER_CASE` are the parameters to be supplied by the user.
* Items in square brackets are optional.