# User Guide

**Blue** helps you manage tasks. Type your commands in the textbox and click send or ENTER to communicate with **Blue**.

![image](https://user-images.githubusercontent.com/19379090/133379127-e476a467-60c2-44f2-af21-8a0582905f53.png)

## Features 

### Store Tasks

#### ToDos

Tasks without any time.

Syntax: `todo <title>`

Example: `todo borrow book`

#### Deadlines

Tasks that has to be done before a certain time.

Syntax: `deadline <title> /by <date and time>`

* note that date and time must be in the format `yyyy-MM-ddTHH:mm:ss`

Example: `deadline return book /by 2021-09-15T23:59:59`

#### Events

Tasks that start at a specified time, and last for 1 hour.

Syntax: `event <title> /at <date and time>`

* note that date and time must be in the format `yyyy-MM-ddTHH:mm:ss`

Example: `event project meeting /at 2021-09-15T23:59:59`

The current version (v0.1) only supports events that last exactly 1 hour.

### Display Stored Tasks

Displays all tasks that have been stored.

Syntax: `list`

Sample response:

![image](https://user-images.githubusercontent.com/19379090/133378636-fe7d1313-f9a6-4789-b9c2-1c5116e9fa84.png)

### Mark Tasks as Done

Mark the task at the specified index as done.

Syntax: `done <index>`

Example: `done 1` marks the first task in the list as done. 

💡 Tip: You can use `list` to know the index of the tasks.

### Delete Tasks

Delete the task at the specified index.

Syntax: `delete <index>`

Example: `delete 3` deletes the third task in the list.

💡 Tip: You can use `list` to know the index of the tasks.

### Find Tasks

Find tasks by searching for keywords in the titles of the tasks.

Syntax: `find <keyword>`

Example: `find book`

### Say Goodbye to **Blue**

Say goodbye to **Blue**. **Blue** will say goodbye to you as well.

Syntax: `bye`

Sample response:

![image](https://user-images.githubusercontent.com/19379090/133377841-c8a5a6d1-2b5a-4a8e-9570-741af76292be.png)

Note that **Blue** is reluctant to leave you, so it still responds to your commands even after you said `bye`.

The only way to leave **Blue** is by closing the application window.
