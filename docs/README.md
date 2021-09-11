# User Guide
Mango is an interactive desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Mango can get your task management tasks done faster than 
traditional GUI apps.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest mango.jar from [here](https://github.com/lrnshk/ip/releases/tag/A-Release).
3. Copy mango.jar to the folder you want to use as the home folder for your Mango.
4. Double-click the file to start the app.
5. Type your commands in the chat box e.g. `list`
6. Refer to the **Features** list below for more information on the commands available.

## Features
* ### Add a Todo task: `todo`

Input the description and tag for the todo you want to create.

Format: `todo DESC /tag TAG`

Example of usage: `todo read book /tag fun`

Expected outcome:

Mango prints a confirmation message and the description of the todo added.

```
Got it. I've added this task:
  [T][ ] read book #fun
Now you have 1 task in the list.
```

* ### Add a Deadline task: `deadline`

Input the description, deadline date in *yyyy-mm-dd* format, and tag for the deadline you want to create.

Format: `deadline DESC /by DATE /tag TAG`

Example of usage: `deadline return book /by 2019-10-15 /tag tedious`

Expected outcome:

Mango prints a confirmation message and the description of the deadline added.

```
Got it. I've added this task:
  [D][ ] return book (by: Oct 15 2019) #tedious
Now you have 2 tasks in the list.
```

* ### Add an Event task: `event`

Input the description, event date, and tag for the deadline you want to create.

Format: `event DESC /at DATE /tag TAG`

Example of usage: `event birthday /at Monday /tag exciting`

Expected outcome:

Mango prints a confirmation message and the description of the event added.

```
Got it. I've added this task:
  [E][ ] birthday (at: Monday) #exciting
Now you have 3 tasks in the list.
```

* ### Find tasks based on keywords: `find` 

Input the search keyword(s).

Format: `find KEYWORD`

Example of usage: `find book`

Expected outcome:

Mango prints a list of tasks that match the given keyword.

```
Here are the matching tasks in your list:
1. [T][ ] read book #fun
2. [D][ ] return book (by: Oct 15 2019) #tedious
```

* ### Mark a task as complete: `done`

Input the index of the task to be marked complete.

Format: `done INDEX`

Example of usage: `done 2`

Expected outcome:

Mango prints a confirmation message and the description of the completed task.

```
Nice! I've marked this task as done:
  [E][X] birthday (at: Monday) #exciting
```

* ### Delete a task: `delete`

Input the index of the task you want to delete.

Format: `delete INDEX`

Example of usage: `delete 2`

Expected outcome:

Mango prints a confirmation message and the description of the task deleted.

```
Noted. I've removed this task:
  [D][ ] return book (by: Oct 15 2019) #tedious
Now you have 2 tasks in the list.
```

* ### Retrieve list of all tasks: `list`

Example of usage: `list`

Expected outcome:

Mango prints the list of all your tasks.

```
Here are the tasks in your list:
1. [T][ ] read book #fun
2. [E][X] birthday (at: Monday) #exciting
```

* ### Exit the app: `bye`

Mango saves your current task list for next time.

Example of usage: `bye`

Expected outcome:

Mango replies with a farewell message.

```
Bye. Hope to see you again soon!
```

