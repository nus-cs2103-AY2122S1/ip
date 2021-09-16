# Duke User Guide :robot:

Duke is a chat bot that enables you to mange your tasks.

# Features:
1. [Add tasks](#add-tasks)

2. [List all tasks](#list-all-tasks)

3. [Delete task](#delete-task)

4. [Find task](#find-task)

5. [Mark task as done](#mark-task-as-done)

6. [Set reminder](#set-reminder)

## Add tasks:
### ToDo:
tasks without any date/time(e.g., go travelling)
Command: `todo <description of the task>`
Example: `todo drink milk`

### Deadline:
tasks that need to be done before a specific date/time(e.g., submit the assigment before 2021/09/17 midnight)

Command: `deadline <description of the task> /by <deadline>` (Note that the date time format should be`yyyy/MM/dd HH:mm`)

Example: `deadline submit the assignment /by 2021/09/17 23:59`

### Event:
tasks that has a starting time and an ending time(e.g., group discussion at 2021/09/18 2pm - 3pm)

Command: `event <description of the task> /at <time period>` (Note that the date time format should be`yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm`)

Example: `event group discussion /at 2021/09/18 14:00--2021/09/18 16:00`

## List all tasks:
Show all the tasks

Command: `list`

## Delete task:
Delete a specific task by index

Command: `delete <task index>`

Example: `delete 1` (delete the first task)

## Find task:
Find specific tasks through keywords

Command: `find <keyword>`

Example: `find assignment`

## Mark task as done:
Mark a specific task through task index as done

Command: `done <task index>`

Example: `done 1` (mark the first task as done)

## Set reminder:
Set a reminder time while adding a new task(ToDo, Deadline, Event). Then duke will remind you when the reminder time reaches.

Command: add `/reminder <reminder date>` after your add tasks command (Note that the reminder date format should be `yyyy/MM/dd HH:mm`)

Example: `todo drink milk /reminder 2021/09/20 22:00`,   `deadline submit the assignment /by 2021/09/17 23:59 /reminder 2021/09/15 09:00`,   `event group discussion /at 2021/09/18 14:00--2021/09/18 16:00 /reminder 2021/09/16 14:00`