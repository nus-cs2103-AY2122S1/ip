# Cynthius User Guide
This is a greenfield Java project based on the generic project called
[***Project Duke***](https://nus-cs2103-ay2021s1.github.io/website/se-book-adapted/projectDuke/index.html).
<br>
The result of this project is a personal assistant chat-bot named [***Cynthius***](https://github.com/El0hime/ip/releases)
capable of keeping track of tasks and contacts given by the user.


* [Features](#features)
    * [Help](#help-help)
    * [Adding a ToDo](#adding-a-todo-todo)
    * [Adding an Event](#adding-an-event-event)
    * [Adding a Deadline](#adding-a-deadline-deadline)
    * [Adding a Contact](#adding-a-contact-contact)
    * [Deleting tasks/contacts](#deleting-a-task-or-contact-delete)
    * [Marking task as done](#mark-task-as-done-done)
    * [Finding tasks/contacts](#finding-tasks-or-contacts-find)
    * [Listing tasks/contacts](#list-tasks-and-contacts-list)
    * [Saving the data](#saving-data)
    * [Exiting the program](#exiting-cynthius-bye)
* [Command summary](#command-summary)

## Features 

- [x]  Managing tasks(todo, deadline, event)
- [x]  Autosave
- [x]  Manage contacts

## Usage
<hr>

### Help: `help`
Displays a guide of all commands.
<hr>

### Adding a todo: `todo`
Adds a todo task to the task list.

Format: `todo TASK`

Example: `todo homework`

Expected outcome: 
```
Information added successfully!
```
<hr>

### Adding an event: `event`
Adds an event task to the task list.

Format: `event TASK /at TASK_LOCATION`

Example: `event birthday party /at Calvin's house`

Expected outcome: 
```
Information added successfully!
```
<hr>

### Adding a deadline: `deadline`
Adds a deadline task to the task list.

Format: `deadline TASK /by TASK_DATE_TIME`

Example: `deadline ES2660 draft /by 16/9`

Expected outcome: 
```
Information added successfully!
```
<hr>

### Adding a contact: `contact`
Adds a contact to the contact list.

Format: `contact NAME (/about NAME_DESCRIPTION)`

Do note that `(/about NAME_DESCRIPTION)` is optional

Example: `contact Calvin /about orbital partner`

Expected outcome: 
```
Information added successfully!
```
<hr>

### Deleting a task or contact: `delete`
Removes a task/contact from the list.

Format: `delete CONTACT/TASK INDEX`

For `CONTACT/TASK`, `c` represents deleting from the contact list 
while `t` represents deleting from the task list

Example 1: `delete c 1` (delete first contact)

Example 2: `delete t 3` (delete third task)

Expected outcome: 
```
Information deleted successfully!
```
<hr>

### Mark task as done: `done`
Marks a task in task list as done.

Format: `done INDEX`

Example: `done 2` (marks second task in task list as done)

Expected outcome: 
```
Task marked as done!
```
<hr>

### Finding tasks or contacts: `find`
Searches for task(s) and contact(s) that contain the keyword(s)

Format: `find KEYWORD(S)`

Example: `find k3soju`

Expected outcome (if tasks/contacts were found):
```
Here are your tasks:
[T][ ] twitch sub to k3soju

Here are your contacts:
[C] k3soju (detail: twitch sub)
```

Expected outcome (if NO tasks/contacts were found):
```
No tasks found!

No contacts found!
```
<hr>

### List tasks and contacts: `list`
Lists all tasks/contacts currently saved.

Format: `list`

Example: `list`

Expected outcome:
```
Here are your tasks:
[T][ ] homework
[D][X] CS2103T review (by: Sep 15 2021)
[E][ ] sleep (at: home)

Here are your contacts:
[C] Calvin (detail: im sponsored by him)
```
<hr>

### Saving data
__Cynthius__ automatically saves any changes made once a user has inputted a valid command.
<hr>

### Exiting Cynthius: `bye`
Closes Cynthius.

Format: `bye`

Example: `bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
<hr>

### Command summary

Command | Format | Example
------------ | ------------- | -------------
Add a **ToDo** | `todo TASK` | `todo homework`
Add an **Event** | `event TASK /at TASK_LOCATION` | `event birthday party /at Calvin's house`
Add a **Deadline** | `deadline TASK /by TASK_DATE_TIME` | `deadline ES2660 draft /by 16/9`
Add a **Contact** | `contact NAME (/about NAME_DESCRIPTION)` | `contact Calvin /about orbital partner`
**Delete** Task/Contact | `delete CONTACT/TASK INDEX` | `delete c 1`, `delete t 3`
Mark task as **Done** | `done INDEX` | `done 1`, `done 2`
**Find** Tasks/Contacts| `find KEYWORD(S)` | `find k3soju`
**List** All Tasks/Contacts| `list`| `list`
**Help** | `help`| `help`
**Exit** | `bye` | `bye`

