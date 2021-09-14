# User Guide

## Quick Start 
1. Ensure that you have `Java 11` installed on your computer. 
2. Download the latest `duke.jar`. 
3. Copy the file to the folder you want to use as the *home address* for Duke. 
4. Double-click the file to start the app. Alternatively, open your terminal and run the following    command: `java -jar duke.jar` to start the program. 
5. Refer below for features and usage of the program. 


## Features 

### Add Your Tasks 

&nbsp;&nbsp;&nbsp;&nbsp;Add 3 different tasks: `todo`, `deadline`, `event` to your tasklist. 


### Mark Your Tasks As Done

&nbsp;&nbsp;&nbsp;&nbsp;Checks off the task on the list. 


### List Your Tasks

&nbsp;&nbsp;&nbsp;&nbsp;List all the tasks you have. 

### Find Your Tasks

&nbsp;&nbsp;&nbsp;&nbsp;Search for your tasks by using keywords. 

### Delete Your Tasks 

&nbsp;&nbsp;&nbsp;&nbsp;Delete task on the list. 



## Usage

### `help` - Show All Commands

Show all available commands. 

Format: `help`

Example of usage: `help`

Expected outcome: A list of commands would be shown. 

<br>

### `todo` - Create a ToDo task

Adds a ToDo task to the list of tasks. 

Format: `todo <DESCRIPTION>`

Example of usage: `todo eat lunch` 

Expected output:
```
Got it. I've added this task:
 [T][ ] eat lunch
Now you have 1 task in the list.
```

<br>

### `deadline` - Create a Deadline task

Adds a Deadline task to the list of tasks. 

Format: `deadline <DESCRIPTION> /by <yyyy-MM-dd HH:mm>`

Example of usage: `deadline finish assignment /by 2021-09-15 23:59` 

Expected output:
```
Got it. I've added this task:
 [D][ ] finish assignment (by: Sep 15 2021 11:59pm)
Now you have 1 task in the list.
```

<br>

### `event` - Create an Event task

Adds an Event task to the list of tasks. 

Format: `event <DESCRIPTION> /by <yyyy-MM-dd HH:mm>`

Example of usage: `event lecture /by 2021-09-18 11:59` 

Expected output:
```
Got it. I've added this task:
 [E][ ] lecture (by: Sep 18 2021 11:59am)
Now you have 1 task in the list.
```

<br>

### `list` - Show All Tasks

Shows all tasks in the list. 

Format: `list`

Expected output: 
````
Here are the tasks in your list:
    1. [T][ ] eat lunch
    2. [D][ ] finish assignment (by: Sep 15 2021 11:59pm)
    3. [E][ ] lecture (by: Sep 18 2021 11:59am)
````

<br>

### `done` - Mark Task as Done

Marks selected task as done. 

Format: `done <TASK_NUMBER>`

Example of usage: `done 3`

Expected output: 
```
Nice! I've marked this task as done: 
  [D][X] finish assignment (by: Sep 15 2021 11:59pm) 
```

<br>

### `delete` - Delete A Task

Removes selected task from list. 

Format: `delete <TASK_NUMBER>`

Example of usage: `delete 2`

Expected output:
```
Noted, I've removed this task: 
  [D][ ] finish assignment (by: Sep 15 2021 11:59pm)  
Now you have 2 tasks in the list.
```

<br>

### `find` - Find A Task 

List tasks that matches keyword. 

Format: `find <KEYWORD>`

Example of usage: `find lunch`

Expected output: 
```
Here are the tasks that matches your keyword: 
  1.[T][ ] eat lunch
```

<br>

### `bye` - Exit Duke

Exits the Program 

Format: `bye`

Example of usage: `bye`

Expected output: 
```
Bye. Hope to see you again soon!
```
