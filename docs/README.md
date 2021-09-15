# User Guide
**Duke Chatbot** is a desktop application for managing your
tasks. Even though it provides GUI, the application is optimized
to be used with CLI (Command Line Interface).

* [Quick start](#quick-start)
* [Features](#features)
* [Command summary](#command-summary)


## Quick start
1. Ensure you have Java `11` or above installed in your c
   computer.
2. Download the latest `Duke.jar` from [here](https://github.com/radiankrisno/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the
   *home folder* for your Duke.
4. Double-click the file to start the app. The GUI 
   similar to the below should appear in a few seconds.
5. Folder `data` will be created as the local Duke storage in your home folder.
6. Type the command in the command box and press `Enter/Send` to
   execute it.
7. Refer to the [Features](#features) below for details of each
   command.
![Duke GUI](Ui.png)

## Features
### Listing all tasks
List all tasks available, including todos, deadlines, and events.

Example:

`list`

Output:
```
Here are the tasks in your list!
     1. [T][X] buy pens
     2. [D][ ] submit assignment (by: Sep 18 2021)
```

### Add a Todo Task
Add a Todo Task with description.

Format:

`todo [description]`

Example:

`todo buy pens`

Output:
```
Got it. I've added this task:
  [T][ ] buy pens
Now you have 1 tasks in the list.
```

### Add a Deadline Task
Add a Deadline Task with description and deadline in yyyy-MM-dd format.

Format:

`deadline [description] /by [deadline]`

Example:

`deadline submit assignment /by 2021-09-18`

Output:
```
Got it. I've added this task:
  [D][ ] submit assignment (by: Sep 18 2021)
Now you have 1 tasks in the list.

```

### Add an Event Task
Add an Event Task with description and time in yyyy-MM-dd format.

Format:

`event [description] /at [time]`

Example:

`event attend workshop /at 2021-09-19`

Output:
```
Got it. I've added this task:
  [E][ ] attend workshop (at: Sep 19 2021)
Now you have 1 tasks in the list.
```

### Mark a Task as done
Task needs to be set into done manually by user, by giving the Task index.

Format:

`done [index]`

Example:

`done 1`

Output:
```
Nice! I've marked this task as done:
  [E][X] attend workshop (at: Sep 19 2021)
```

### Delete a Task
Delete a Task by specifying its index.

Format:

`delete [index]`

Example:

`delete 1`

Output:
```
Got it! I've removed this task:
  [E][X] attend workshop (at: Sep 19 2021)
Now you have 0 tasks in the list.

```

### Undo an action
Undo the latest action and restore the previous version of Task list.

Example:

`undo`

Output:
```
Here are the tasks in your list!
1. [E][X] attend workshop (at: Sep 19 2021)
```

### Redo an action
Redo the latest action that has been undone. If there is none, return the current Task list.

Example:

`redo`

Output:

```
Here are the tasks in your list!
```

### Exiting the application
Exit Duke Chatbot.

Example:

`bye`

Output:
Duke application will be closed.


## Command summary

|action  |Format, Examples|
|--------|----------------|
|list    |`list`|
|deadline|`deadline [description] /by [deadline]`<br>e.g.,`deadline submit assignment /by 2021-09-18`|
|event   |`event [description] /at [time]`<br>e.g.,`event attend workshop /at 2021-09-19`|
|todo    |`todo [description]`<br>e.g.,`todo buy pens`|
|delete  |`delete [index]`<br>e.g., `delete 1`|
|done    |`done [index]`<br>e.g., `done 1`|
|undo    |`undo`|
|redo    |`redo`|
|bye     |`bye`|
