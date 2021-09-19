# Nekobot | User Guide

## Features 

- [X] Add todos, deadlines and events
- [X] Mark as done
- [X] List and Delete tasks
- [X] Find tasks by word

## Usage

### `list`

Lists all tasks.

Expected outcome:

![image](https://user-images.githubusercontent.com/77230723/132998603-3a529422-7efd-4120-91dd-998d442e0531.png)


### `todo`

Adds a todo to the task list. 

Format: `todo DESCRIPTION`

Expected outcome: 

![image](https://user-images.githubusercontent.com/77230723/132998560-ff2d64fd-9340-4b5f-a3d6-c0c5d7a09726.png)


### `deadline`

Adds a deadline task with a description and a due date.

Format: `deadline DESCRIPTION /by DUE_DATE`

Example of usage:

`deadline Submit Essay /by 2021-9-14`

Expected outcome:

![image](https://user-images.githubusercontent.com/77230723/132998669-07368c49-ebcc-44d5-8c53-8714a9a2737c.png)


### `event`

Adds an event to the task list. An event has a description and date/time.

Format: `event DESCRIPTION /at DATE_TIME`

Example of usage:

`event Movie night /at 24/12/2021 2000`

Expected outcome: 

![image](https://user-images.githubusercontent.com/77230723/132998953-3804f91a-8574-4c81-81fe-38c40e19a929.png)


### `done`

Marks the task at the specified index in the list as done. May want to use `list` first to view task index.

Format: `done INDEX`

Example of usage:

`done 2`

Expected outcome:

![image](https://user-images.githubusercontent.com/77230723/132998832-e69f8fee-5cbc-4d6f-adcc-22e791a23646.png)

  
### `delete`

Deletes a task at the specified index in the list. May want to use `list` first to view task index.

Format: `delete INDEX`

Expected outcome:

![image](https://user-images.githubusercontent.com/77230723/132998699-55195cc9-643b-4e81-a0d0-59a7afcb60d3.png)


### `find`

Finds tasks which match given words.

Format: `find SEARCH_WORD`

Expected outcome: 

![image](https://user-images.githubusercontent.com/77230723/132998976-5598c564-cfa9-414e-8879-5860d8d9ced8.png)


### `bye`

Exits the program.

