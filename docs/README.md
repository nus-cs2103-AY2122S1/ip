# User Guide

## DUKE CHAT BOT

### Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest src.main.java.duke.jar.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app and you should see the following GUI.

![Image of Duke]
(https://github.com/Shivlock221b/ip/tree/master/docs/Ui.png)

### Features

#### Create a todo task : *todo*
 Adds a task with no time limit to the task list

 Format: *todo* **NAME OF TASK**

 *Example*

        * todo get book

#### Create an Event task : *event*
 Adds a task with a specified time to the task list

 Format: *event* **NAME OF TASK** /at **TIME**

 *Example*

        * event read book /at 2pm

#### Create a Deadline task : *deadline*
 Adds a task with a deadline time to the task list

 Format: *deadline* **NAME OF THE TASK** /by **TIME IN THIS FORMAT YYYY-MM-DD**

 *Example*

        * deadline return book /by 2019-12-01

#### Mark a task in the list as done : *done*
 Marks a task in the list as done i.e. puts a cross in front of the task in the list

 Format: *done* **INDEX OF TASK IN THE LIST**

 *Example*

        * done 2

#### View list of all tasks : *list*
 Displays all tasks in the list

 Format: *list*

#### Update a time of tasks in the list : *update*
 Updates the time of tasks in the list, mainly events and deadlines, since todo tasks have no time

 Format for events:    *update* **INDEX OF TASK IN THE LIST** /to **NEW TIME**
 Format for deadlines: *update* **INDEX OF TASK IN THE LIST** /to **NEW TIME IN THIS FORMAT YYYY-MM-DD**

 *Example*

        * update 2 /to 3pm           (for events)
        * update 3 /to 2019-06-06    (for deadlines)

#### Delete a task from the task list : *delete*
 Deletes a task from the list

 Format: *delete* **INDEX OF THE TASK IN THE LIST**

 *Example*

        * delete 2

#### Find a list of matching tasks from the list : *find*
 Finds a list of tasks from teh task list, containing all the tasks the match the given expression

 Format: *find* **EXPRESSION TO BE SEARCHED**

 *Example*

        * find book
