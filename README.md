# Duke User Guide

<img width="868" alt="Ui" src="https://user-images.githubusercontent.com/66522537/132977797-e9c014ff-5db0-471c-bdc2-d58720b7b3f2.png">

Product Screenshot

## Description
Duke is an interactive chatbot that keeps track of and manages your tasks for you. It is targeted for users who prefer typing commands with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). This project is part of the greenfield individual-project for CS2103.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest release of the Duke.jar file.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Enter `java -jar Duke.jar` into your computer's terminal in the same directory as your Duke.jar file.
5. The GUI similar to the below should appear in a few seconds.
6. Upon starting Duke, Duke will attempt to load the previous task history from `./data/duke.txt`. If the directory or file is missing, Duke will create a new storage file and populate it with some sameple date.
7. You can type commands intot the command box and execute them by pressing Enter or by clicking on the Send button on the bottom right.
8. Type `list` and press Enter, notice Duke contains some sample data.
9. Type `help` and press Enter for Duke to display a list of command Duke offers.

## Features

* ### Adding ToDo Tasks: `todo`
ToDo tasks are simple tasks with only a specified description.

Format: `todo <description>`

Example Usage: `todo buy bread`

Expected Outcome:
Duke confirms he has added your task and reminds of the total number of tasks you have.
![image](https://user-images.githubusercontent.com/66522537/132978218-b413a0cd-57c0-4e38-846d-fb008e2d7b0b.png)

* ### Adding Deadline Tasks: `deadline`
Deadline tasks are tasks with a specified description and a text determining when it's due **_by_**. If the text specifying the due date is in the format `yyyy-mm-dd`, it will be formatted into a text in the form `MMM-dd-yyyy`, where MMM is the first 3 characters of the month name.


Format: `deadline <description> /by <date>`

Example Usage:
```
deadline buy bread /by tomorrow evening
deadline buy coffee /by 2021-09-30
```

Expected Outcome:
Duke confirms he has added your task and reminds of the total number of tasks you have.
![image](https://user-images.githubusercontent.com/66522537/132978311-0c9fa739-4ab7-4fb1-b39d-ddc38aabe52f.png)


* ### Adding Event Tasks: `event`
Event tasks are tasks with a specified description and a text determining the period/location it is **_at_**.

Format: `event <description> /at <period>`

Example Usage:
```
event marathon /at central park
event josh's birthday party /at noon next sunday
```

Expected Outcome:
Duke confirms he has added your task and reminds of the total number of tasks you have.
![image](https://user-images.githubusercontent.com/66522537/132978440-37e7772d-d9dc-487c-baf6-d9cf7b334a11.png)


* ### Listing all Tasks: `list`
After creating your tasks, you may view the list of tasks that you have added into Duke. The list of tasks are also stored in an editable text file at `./data/duke.txt`.

Format: `list`

Expected Outcome:
Duke will display your list of tasks, if you have no tasks, Duke will inform you that your list is empty.
![image](https://user-images.githubusercontent.com/66522537/132979359-0ef48954-2626-4414-8a89-c5649591027c.png)


* ### Marking your Tasks as Done: `done`
Once you have finished your task, you can update the task and mark them as done. Refer to the task number that is given in the `list` command.

Format: `done <index>`

`index` should be a valid integer ranging from 1 to the number of tasks you have. Not usable if you have no tasks.

Expected Outcome:
Duke confirms he has marked the indexed task as done.
![image](https://user-images.githubusercontent.com/66522537/132979371-43b3d70b-134b-4521-baa1-3a45f8d661c2.png)


* ### Deleting Tasks: `delete`
You can choose to delete any task from Duke at any time. Refer to the task number that is given in the `list` command.

Format: `delete <index>`

`index` should be a valid integer ranging from 1 to the number of tasks you have. Not usable if you have no tasks.

Expected Outcome:
Duke confirms he has marked the indexed task as done.
![image](https://user-images.githubusercontent.com/66522537/132979410-ee90a522-9b59-4fb1-8d4a-b405e7c02a05.png)


* ### Finding Tasks: `find`
You can search your list for tasks that contains a specified input text as a substring to its description.

Format: `find <keyword>`

Expected Outcome:
Duke will display a list of tasks found, if any.
![image](https://user-images.githubusercontent.com/66522537/132979454-c8625f67-92c9-457d-82fc-b45e235c9e23.png)


* ### Sorting Tasks: `sort`
You can sort your tasks by task type (`Todo`, `Deadline` or `Event`).

Format: `sort`

Expected Outcome:
Duke confirms he has sorted your tasks. You can `list` your tasks to view the sorted order.
![image](https://user-images.githubusercontent.com/66522537/132979524-d33f8c98-5c06-4214-9450-319bf3f6cd72.png)


* ### Getting Help: `help`
You can get Duke to show you a list of available commands.

Format: `help`

Expected Outcome:
Duke displays a list of available commands.
![image](https://user-images.githubusercontent.com/66522537/132979331-b8aafefc-5d6f-4524-afc0-843de68f563b.png)


* ### Exiting Duke: `bye`
You can exit duke by issueing the following command.

Format: `bye`

Expected Outcome:
The Duke application will close.


## Error Messages
When duke encounters an error and doesn't know how to respond to a user's command, Duke will display helpful error messages coloured with a red background.

![image](https://user-images.githubusercontent.com/66522537/132979795-6b8ff994-7712-440a-a601-338f2f8c067a.png)
