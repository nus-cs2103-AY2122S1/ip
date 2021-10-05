# User Guide

Welcome to the Duke user guide. <br/>
This will teach you all you need to know about Duke so you can start using it.

Here are is a summary of commands that Duke responds to: (You can find the detailed usage guide below)

Command | Format | What it does
------- | ------ | ------------
`list` | `list` | Displays a list of your current tasks.
`todo` | `todo {task description}` | Adds a todo to your task list.
`deadline` | `deadline {task description} /by {date/time}` | Adds a deadline to your task list.
`event` | `event {task description} /at {date/time}` | Adds an event to your task list.
`done`  | `done {number}` | Marks a task as done.
`delete` | `delete {number}` | Deletes a task from the task list.
`find` | `find {keyword}` | Finds all matching tasks that contain the specified keyword.
`bye` | `bye` | Exits Duke.

## Features 

Feature list:
- [x] List tasks
- [x] [Add to-dos](#2-todo-add-a-to-do-task)
- [x] Add deadlines
- [x] Add events
- [x] Complete tasks
- [x] Delete tasks
- [x] Search for tasks 
- [x] Check for duplicate tasks

## Usage

### 1. `list`: Displays all your tasks you entered so far. Tasks are saved even if you quit Duke.

**Format:** `list`

Example usage: 
1. `list`

Expected outcome (Assuming you haven't added any tasks):

  <img width="400" alt="Screenshot 2021-09-13 at 5 02 00 PM" src="https://user-images.githubusercontent.com/85099754/133055753-78652a23-6a44-4d83-aff9-f3734312b8b4.png">


### 2. `todo`: Add a to-do task.

**Format:** `todo {taks description}`

Example usage: 
1. `todo read book`

Expected outcome:

  <img width="400" alt="Screenshot 2021-09-13 at 5 03 06 PM" src="https://user-images.githubusercontent.com/85099754/133055961-abc6c861-5c0e-49f1-a542-15d254da9c5c.png">


### 3. `deadline`: Adds a deadline task 

**Format:** `deadline {task description} /by {date/time}` <br/>
*(Note that `date` is in `YYYY-MM-DD format` while time is in `24 hour clock` format)* 

When entering date and time you can choose to just enter either one or both.

For example
1. `deadline submit iP /by 2021-09-17 18:00` 
2. `deadline submit iP /by 2021-09-17` (time will be set to 23:59 by default)
3. `deadline submit iP /by 18:00` (Date will be set to the current date by default)

Are all acceptable.

Expected outcome (using example 2):

  <img width="400" alt="Screenshot 2021-09-13 at 5 41 03 PM" src="https://user-images.githubusercontent.com/85099754/133061750-5b17daf2-6d15-4dd9-bcd4-c348a68eb041.png">



### 4. `event`: Adds an event task 

**Format:** `event {task description} /at {date/time}` <br/>
*(Note that `date` is in `YYYY-MM-DD format` while time is in `24 hour clock` format)* 

When entering date and time you can choose to just enter either one or both.

For example
1. `event Attend a concert /at 2021-10-27 18:00` 
2. `event Attend a concert /at 2021-10-27` (time will be set to 23:59 by default)
3. `event Attend a concert /at 18:00` (Date will be set to the current date by default)

Are all acceptable.

Expected outcome (using example 1): 

<img width="400" alt="Screenshot 2021-09-13 at 5 08 23 PM" src="https://user-images.githubusercontent.com/85099754/133056732-e0e6ca2c-d3cf-43cd-b73a-5a71c16b39cc.png">

### 5. `done`: Marks a task in the task list as done.

**Format:** `done {number}`

Example usage: 
1. `done 1` (Assuming task number 1 exists in your task list)

Expected outcome:

<img width="400" alt="Screenshot 2021-09-13 at 5 10 15 PM" src="https://user-images.githubusercontent.com/85099754/133056981-f1e26db2-ff14-4b7a-bdb0-0d932e467f47.png">


### 5. `delete`: Deletes a task in the task list.

**Format:** `delete {number}`

Example usage: 
1. `delete 1` (Assuming task number 1 exists in your task list)

Expected outcome:

<img width="400" alt="Screenshot 2021-09-13 at 5 11 03 PM" src="https://user-images.githubusercontent.com/85099754/133057104-edc9bc53-403f-4c91-adc6-74a9d6fd6a6f.png">


### 5. `find`: Searches for all tasks in the task list that matches the specified keywords.

**Format:** `find {keywords}`

Example usage: 
1. `find September 17 2021`
2. `find 6:00 PM`
3. `find book`

Expected outcome (using example 1):

<img width="400" alt="Screenshot 2021-09-13 at 5 12 51 PM" src="https://user-images.githubusercontent.com/85099754/133057445-9f272bf3-76dd-4dfc-af42-1958e01e4eab.png">

### 6. `bye`: Exits Duke.

**Format:** `bye`

Example usage: 
1. `bye`

Expected outcome:

<img width="400" alt="Screenshot 2021-09-13 at 5 13 16 PM" src="https://user-images.githubusercontent.com/85099754/133057511-b98dccd7-6147-4d62-b9b9-bad110df4cb4.png">


#### Warning
* Do not edit the `data/Duke.txt` file as Duke saves and loads your user data through this file. <br/>
* As such there is a specific way that Dukes reads this file and any interferences can cause Duke to crash.
* You should only edit your tasks through the Duke application with the commands given above.


## Duke Demo

The inputs entered here are from the examples given above. Feel free to try Duke with your own inputs!

![DukeDemoGif](https://user-images.githubusercontent.com/85099754/133879352-980365da-f243-4893-883d-e71ca47ccdbe.gif)


*Have fun using Duke!*
