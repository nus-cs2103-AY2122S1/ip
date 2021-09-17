# _**Notaro User Guide**_

# Features

* [Add Deadline](#add-deadline-deadline)
* [Add Event](#add-event-event)
* [Add Todo](#add-todo-todo)
* [List Tasks](#list-tasks-list)
* [Mark Task as Done](#mark-tasks-as-done-done)
* [Tag Task](#tag-task-tag)
* [Delete Task](#delete-tasks-delete)
* [Find Tasks](#find-tasks-find)
* [Exit-Program](#exit-program-bye)

# How to use
### Add Deadline: `deadline`
Adds a Deadline task to the list of tasks.

**Format**  
`deadline DEADLINE_NAME /by DATE TIME`  
(Note that the "DATE TIME" must be in format : `YYYY-MM-DD HH:MM`)

**Example**  
Input : `deadline finish episode /by 2021-05-21 23:59`  
Output : 
```
Okay! I've added the following:
[D][ ] finish episode (by: 21 May 2021 11.59 pm)
1 more tasks to go!
```
___

### Add Event: `event`
Adds an Event task to the list of tasks.

**Format**  
`event EVENT_NAME /at EVENT_DESCRIPTION DATE START_TIME~END_TIME`
(Note that the dates must be in format : `YYYY-MM-DD`, and times in format: `HH:MM`)

**Example**  
Input : `event afa /at 2019-11-27 15:00~20:00`
```
Okay! I've added the following:
[E][ ] afa (at: 27 Nov 2019 3:00 pm - 8:00 pm)
2 more tasks to go!
```
___

### Add Todo: `todo`
Adds a Todo task to the list of tasks.

**Format**  
`todo TODO_NAME`

**Example**  
Input: `todo watch anime`  
Output:
```
Okay! I've added the following:
[T][ ] watch anime
3 more tasks to go!
```
---

### List Tasks: `list`
Lists out all the tasks.

**Format**  
`list`

**Example**  
Input : `list`  
Output :
```
Here are your tasks:
1. [D][ ] finish episode (by: 21 May 2021 11.59 pm)
2. [E][ ] afa (at: 27 Nov 2019 3:00 pm - 8:00 pm)
3. [T][ ] watch anime
```
---

### Mark Tasks as Done: `done`
Mark a task as done.

**Format**  
`done INDEX`

**Example**  
Input : `done 1`  
Output : 
```
Yay good job! [D][X] finish episode (by: 21 May 2021 11.59 pm) has been completed
```
---
### Tag Task: `tag`
Tag tasks with additional descriptions.

**Format**  
`tag INDEX TAG_DESCRIPTION`

**Example**  
Input: `tag 3 fun`  
Output:
```
Oki! The tag fun has been added. Here is the modified task!
[T][ ] watch anime #fun
```

---
### Delete Tasks: `delete`
Delete tasks from list of tasks.

**Format**  
`delete INDEX`

**Example**  
Input : `delete 2`  
Output :
```
Oki! I have removed this task:
[E][ ] afa (at: 27 Nov 2019 3:00 pm - 8:00 pm)
2 more tasks to go!
```
---
### Find Tasks: `find`
Search for tasks that match the given keyword.

**Format**  
`find KEYWORD`

**Example**  
Input : `find episode`  
Output:
```
Here are the matching tasks in your list:
2.  [D][ ] finish episode (by: 21 May 2021)
3.  [T][ ] watch lecture episode
Good luck!
```
---
### Exit Program: `bye`
Exits the program.

Format:  
`bye`

---
## FAQ  
Q: What version of Java should I use?  
A: Please use Java 11 or a later version.

Q: How do I save my progress?  
A: Data is automatically saved in your computer :)

Q: What is the data/duke.txt file generated after running the app?  
A: It is the save file of the app. Please do not edit the folder and file in any way, 
as it can lead to file corruption.

Q: Why does my JAR file not run?  
A: It is likely that the save file has been corrupted. 
Check the duke.txt file saved in the data folder for any noticeable errors.
If all else fails, clear the file or delete the folder.   
Do note that doing so erases your progress.

