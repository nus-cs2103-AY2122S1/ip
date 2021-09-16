# User Guide
## Features
1. [**Add tasks**](#add-tasks)
   - [`todo`](#todo)
   - [`deadline`](#deadline)
   - [`event`](#event)
2. [**View tasks**](#view-tasks)
   - [`list`](#list)
3. [**Update tasks**](#update-tasks)
   - [`done`](#done)
   - [`delete`](#delete)
4. [**Search tasks**](#search-tasks)
   - [`find`](#find)
5. [**Additional Features**](#additional-features)
   - [`help`](#help)
   - [`bye`](#bye)
   - [save](#save)
 
## [Download here](https://github.com/amzhy/ip/releases/download/A-Release/duke.jar)

<br />
  
## Add Tasks

---
Adds a task of type `todo`/ `deadline`/ `event` to your list of tasks.

<br />

### `todo`

Add a todo task that is not time-sensitive to your list of tasks.


**Format**: `todo [detail]`

| Argument | Remarks |
| ------------ | ------------- |
| [detail] | description of the task |


**Example of Usage**: `todo read book`

**Expected outcome**: 

![Screenshot 2021-09-16 at 5 15 17 PM](https://user-images.githubusercontent.com/76540550/133602205-c19cc254-7348-4e2a-aa59-3aed2acf5104.png)


<br />

### `deadline`
Add a deadline task with a specified date and time your list of tasks.

**Format**: `deadline [detail] /by [dd/MM/yyyy HH:mm]`

| Argument | Remarks |
| ------------ | ------------- |
| [detail] | description of the deadline |
| [dd/MM/yyyy HH:mm] | date and time of the deadline |


**Example of Usage**: `deadline math hw /by 19/02/2021 19:00`

**Expected outcome**:

![Screenshot 2021-09-16 at 5 37 26 PM](https://user-images.githubusercontent.com/76540550/133602163-86e99996-2c38-4a73-9905-31fe3ffe1c8f.png)

<br />

### `event`
Add an event with a specified date and time to your list of tasks.

**Format**: `event meeting /at [dd/MM/yyyy HH:mm]`

| Argument | Remarks |
| ------------ | ------------- |
| [detail] | description of the event |
| [dd/MM/yyyy HH:mm] | date and time of the event |


**Example of Usage**: `event meeting /at 19/08/2021 09:00`

**Expected outcome**:

![Screenshot 2021-09-16 at 5 42 44 PM](https://user-images.githubusercontent.com/76540550/133602124-1bbc3501-806d-4240-9bd9-ee72f135d32f.png)

<br />


## View Tasks

---
### `list`
Shows all your current tasks with the completion status and task type.

**Format**: `list`

**Expected Outcome**:

![Screenshot 2021-09-16 at 6 07 02 PM](https://user-images.githubusercontent.com/76540550/133601901-5028d4ba-b37e-40f7-96c7-373c1fb6c542.png)

<br />

## Update Tasks

---
### `done`
Complete a task in your list of tasks.

**Format**: `done [index]`

| Argument | Remarks |
| ------------ | ------------- |
| [index] | index of task starting from 1 |


**Example of Usage**: `done 1`

**Expected Outcome**:

![Screenshot 2021-09-16 at 6 05 20 PM](https://user-images.githubusercontent.com/76540550/133601954-40b78698-65c8-43c3-ac9c-9032467316d8.png)


<br />

### `delete`
Remove a task from your list of tasks.

**Format**: `delete [index]`

| Argument | Remarks |
| ------------ | ------------- |
| [index] | index of task starting from 1 |


**Example of Usage**: `delete 2`

**Expected Outcome**:


![Screenshot 2021-09-16 at 6 07 02 PM](https://user-images.githubusercontent.com/76540550/133601901-5028d4ba-b37e-40f7-96c7-373c1fb6c542.png)

<br />

## Search Tasks

---
### `find`
Find relevant tasks with keyword.

**Format**: `find [keyword]`

| Argument | Remarks |
| ------------ | ------------- |
| [keyword] | string that appears in the detail of a task |


**Example of Usage**: `find meet`

**Expected Outcome**:


![Screenshot 2021-09-16 at 6 11 45 PM](https://user-images.githubusercontent.com/76540550/133601607-c24acd3d-c661-4bd7-8bbf-43a95f38f149.png)

<br />

## Additional Features

---
### `help`
List all  supported commands with their respective formats.

**Format**: `help`

### `bye`
Exits the application.

**Format**: `bye`

### save 
Saves and loads your current tasks from your local file`data/list.txt` whenever you use the application. 
