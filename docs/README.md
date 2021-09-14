# User Guide
Duke helps you keep track of your tasks.

## Quick Start
1. Ensure you have Java `11` installed on your computer.
2. Download the latest `duke.jar` file [here](https://github.com/driip-ddrop/ip/releases)
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Double-click the file to start the app.
5. Refer to the features below for details on each command.


## Features 

### Add Tasks

Add 3 different types of tasks, Todo, Deadline and Event to Duke.

Task Type | Symbol
----------|---------
todo | `T`
deadline | `D`
event | `E`

<br />

### List Current Tasks

Show all existing tasks you have entered.

<br />

### Find Task

Find the task by inputting the description.

<br />

### Check Off Your Task

Mark completed tasks as done.

<br />

### Delete Your Task

Delete tasks that you do not want in Duke anymore.

<br />

### Update Your Task

Update information of existing task.

<br />

### Exit Duke

Exits the App.

<br />

## Usage
### `todo` - Add todo task to Duke to keep track of

Format: `todo <DESCRIPTION>`

Example of usage:  `todo borrow a book`

Expected outcome:

```
Got it. I've added this task:
 T |0| borrow a book
Now you have 1 task in the list.
```
<br />

### `deadline` - Add deadline task to Duke to keep track of

Format: `deadline <DESCRIPTION> /by yyyy-MM-dd HH:mm`

Example of usage:  `deadline return book /by 2021-08-12 18:30`

Expected outcome:

```
Got it. I've added this task:
 D |0| return book | 12 Aug 2021 6.30PM
Now you have 1 task in the list.
```
<br />

### `event` - Add event task to Duke to keep track of

Format: `event <DESCRIPTION> /at yyyy-MM-dd HH:mm`

Example of usage:  `event concert /at 2021-12-12 18:30`

Expected outcome:

```
Got it. I've added this task:
 E |0| concert | 12 Dec 2021 6.30PM
Now you have 1 task in the list.
```
<br />

### `list` - List all existing events

Format: `list`

Example of usage:  `list`

Expected outcome:

```
Here are the tasks in your list:
1.T |0| borrow a book
2.D |0| return book | 12 Aug 2021 6.30PM
```
<br />

### `find` - Locating task by description

Format: `find <DESCRIPTION>`

Example of usage:  `find book`

Expected outcome:

```
Here are the matching tasks in your list:
1.T |0| borrow a book
2.D |0| return book | 12 Aug 2021 6.30PM
```
<br />

### `done` - Check Off Task when completed

Format: `done <INDEX>`

Example of usage:  `done 1`

Expected outcome:

```
Nice! I've marked this task as done:
 T |1| borrow a book
```
<br />

### `delete` - Delete task from list

Format: `delete <INDEX>`

Example of usage:  `delete 1`

Expected outcome:

```
Noted. I've removed this task:
 T |1| borrow a book
Now you have 0 tasks in the list.
```
<br />

### `update` - Provide instructions for update

Format: `update`

Example of usage:  `update`

Expected outcome:

```
To update Task Name input:
edit-N/(Task Index) (New Task Name)

To update Task Duration input:
edit-D/(Task Index) (New Task Date and Time)
```
<br />

### `edit-N` - Update Task Description

Format: `edit-N/<INDEX> <NEW DESCRIPTION>`

Example of usage:  `edit-N/1 do homework`

Expected outcome:

```
Got it. I've updated this task:
T |0| do homework
```
<br />

### `edit-D` - Update Task Duration

Format: `edit-D/<INDEX> <NEW DURATION>`

Example of usage:  `edit-D/1 2021-11-14 18:00`

Expected outcome:

```
Got it. I've updated this task:
D |0| concert | 14 Nov 2021 6.00PM
```
<br />

### `bye` - Exits Duke

Format: `bye`

Example of usage:  `bye`

Expected outcome:

Exits Duke after the message below.

```
Bye. Hope to see you again soon!
```
<br />
