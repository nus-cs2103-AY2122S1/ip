# Duke User Guide :robot:

Duke is a chat bot that enables you to mange your tasks. It is based on **CommandLine Interface**(CLI) and also has a **Graphical User Interface(GUI)**.

# Quick start
1. Ensure you have `Java 11` installed in your Computer.
2. Download the duke.jar from [here](https://github.com/LuoZhijie-tom/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app. You can also run it in cmd by typing `java -jar <path>`(Replace `<path>` with your duke.jar's path e.g. `D:\duke.jar`.
5. After that, you will see the following window

![start](https://user-images.githubusercontent.com/77223932/133730182-ae5488aa-a415-49fa-8a8b-4f31d2ad93e4.jpg)

6. Type the command in the command box and press send to execute it. The Complete table of commands can be found [here](#command-summary).

# Features
* [Add task](#add-task)
  * [ToDo](#todo)
  * [Deadline](#deadline)
  * [Event](#event) 

* [Set reminder](#set-reminder)

* [Mark task as done](#mark-task-as-done)

* [See all tasks](#see-all-tasks)

* [Find task](#find-task)

* [Delete task](#delete-task)

## Add task:
* ### ToDo: 
    **Definition:** Tasks without any date/time. (e.g., go travelling)

    **Command:** `todo <task description>`

    **Example:** `todo drink milk`

* ### Deadline: 
    **Definition:** Tasks that need to be done before a specific date/time. (e.g., submit the assigment before 2021/09/17 midnight)

    **Command:** `deadline <description of the task> /by <deadline>`(Note that the date time format should be`yyyy/MM/dd HH:mm`)

    **Example:** `deadline submit the assignment /by 2021/09/17 23:59`

* ### Event: 
    **Definition:** Tasks that has a starting time and an ending time. (e.g., group discussion at 2021/09/18 2pm - 3pm)

    **Command:** `event <description of the task> /at <time period>` (Note that the date/time format should be`yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm`)

    **Example:** `event group discussion /at 2021/09/18 14:00--2021/09/18 16:00`

## Set reminder:
Set a reminder time while adding a new task(ToDo, Deadline, Event). Then duke will remind you when the reminder time reaches.

**Command:** add `/reminder <reminder date>` after your add tasks command (Note that the date/time format should be `yyyy/MM/dd HH:mm`)

**Example:** 
  * `todo drink milk /reminder 2021/09/20 22:00`
  * `deadline submit the assignment /by 2021/09/17 23:59 /reminder 2021/09/15 09:00`
  * `event group discussion /at 2021/09/18 14:00--2021/09/18 16:00 /reminder 2021/09/16 14:00`

## Mark task as done:
Mark a specific task through task index as done.

**Command:** `done <task index>`

**Example:** `done 1` (mark the first task as done)

## See all tasks:
Show all the tasks.

**Command:** `list`

## Find task:
Find specific tasks by keywords.

**Command:** `find <keyword>`

**Example:** `find assignment` (find tasks with the keyword "assignment")

## Delete task:
Delete a specific task.

**Command:** `delete <task index>`

**Example:** `delete 1` (delete the first task)

# Command summary

Action | Command format
-------|---------------
  Add ToDo | `todo <task description>`
  Add Deadline | `deadline /by <deadline>`
  Add Event | `event /at <event period>`
  Set reminder | add `/reminder <reminder time>` after the add command. (Reminder time should be in the format `yyyy/MM/dd HH:mm`)
  Mark task as done | `done <task index>`
  See all tasks | `list`
  Find task | `find <keyword>`
  Delete task | `delete <task index>`
  
