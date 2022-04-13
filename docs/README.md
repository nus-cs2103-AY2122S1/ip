# User Guide

**Duke** is a desktop app for managing your daily tasks, optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface** (GUI).

Using the app, you can categorize your tasks into **To-Dos**, **Deadlines**, and **Events**. You can add and delete tasks, mark them as done, view them in a list, and search for them - all within a single, handy app.

Additionally, you can even tag your tasks, helping you to categorise and customise your tasks even further!

## Features 

### Adding a task (To-Do): `todo`

Adds a task (To-Do) for Duke to track.

Example of usage:

`todo MY_TASK`

Expected outcome:

```
Alright. I've added the following task (Todo):
--> [T][ ] MY_TASK

You now have 1 task in the list.
```

Optionally, you can tag the task (To-Do) while adding the task.

Example of usage:

`todo MY_TASK #todo`

Expected outcome:

```
Alright. I've added the following task (Todo):
--> [T][ ] MY_TASK
#todo

You now have 1 task in the list.
```

---

### Adding a task (Deadline): `deadline`

Adds a task (Deadline) for Duke to track.

Example of usage:

`deadline MY_DEADLINE /by 12/09/2021 2359`

Expected outcome:

```
Alright. I've added the following task (Deadline):
--> [D][ ] MY_DEADLINE (By: Sep 12 2021 - 11:59 PM)

You now have 1 task in the list.
```

Optionally, you can tag the task (Deadline) while adding the task.

Example of usage:

`deadline MY_DEADLINE /by 12/09/2021 2359 #deadline`

Expected outcome:

```
Alright. I've added the following task (Deadline):
--> [D][ ] MY_DEADLINE (By: Sep 12 2021 - 11:59 PM)
#deadline

You now have 1 task in the list.
```

---

### Adding a task (Event): `event`

Adds a task (Event) for Duke to track.

Example of usage:

`event MY_EVENT /at 12/09/2021 1100`

Expected outcome:

```
Alright. I've added the following task (Event):
--> [E][ ] MY_EVENT (At: Sep 12 2021 - 11:00 AM)

You now have 1 task in the list.
```

Optionally, you can tag the task (Deadline) while adding the task.

Example of usage:

`event MY_EVENT /at 12/09/2021 1100 #event`

Expected outcome:

```
Alright. I've added the following task (Event):
--> [E][ ] MY_EVENT (At: Sep 12 2021 - 11:00 AM)
#event

You now have 1 task in the list.
```

### Marking a task as done: `done`

Marks a specified task as done. Takes in a non-zero positive integer (i.e. 1 onwards) that corresponds to the task number.

Example of usage:

`done 1`

Expected outcome:

```
Great! I've marked the following task as done:
1. [T][X] MY_TASK
```

---

### Deleting a task: `delete`

Deletes a specified task from Duke's tracking list. Takes in a non-zero positive integer (i.e. 1 onwards) that corresponds to the task number.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed the following task:
1. [T][X] MY_TASK
```

---

### Viewing all task(s): `list`

Lists all tasks that Duke is currently tracking, according to the task number.

Example of usage:

`list`

Expected outcome:

```
1. [T][X] MY_TASK

2. [D][ ] MY_DEADLINE (By: Sep 12 2021 - 11:59 PM)

3. [E][ ] MY_EVENT (At: Sep 12 2021 - 11:00 AM)
```

---

### Searching for task(s) using keywords: `find`

Searches for tasks that contain the given keyword. Takes in a string that will be used as the keyword for finding tasks.

Example of usage:

`find EVENT`

Expected outcome:

```
Here are the matching tasks in your list:

3. [E][ ] MY_EVENT (At: Sep 12 2021 - 11:00 AM)

Hope you found what you were looking for!
```

---

### Tagging task(s): `tag`

Tags a specified task. Takes in a non-zero positive integer (i.e. 1 onwards) that corresponds to the task number, and a list of tags to tag the specified task with.

All tags must start with #.

Example of usage:

`tag 3 #important #event`

Expected outcome:

```
Alright. I've tagged the following task (Event):
--> [E][ ] MY_EVENT (At: Sep 12 2021 - 11:00 AM)
#important #event
```

---

### Exiting Duke: `bye`

Exits the Duke app. The list of tasks is automatically saved in the user's hard drive whenever a new task is added, and will load this save file when starting up again.

Example of usage:

`bye`

Expected outcome:

```
Bye... Hope to see you again soon!
```

---