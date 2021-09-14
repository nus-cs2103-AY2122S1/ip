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
`deadline DEADLINE_NAME /by DATE`  
( Note that DATE must be in format : `YYYY-MM-DD`)

**Example**  
Input : `deadline finish episode /by 2021-05-21`  
Output : 
```
Okay! I've added the following:
[D][ ] finish episode (by: 21 May 2021)
2 more tasks to go!
```
___

### Add Event: `event`
Adds an Event task to the list of tasks.

Format : `event EVENT_NAME /at EVENT_DESCRIPTION`

Example:
Input : `event afa /at suntec`
```
Okay! I've added the following:
[E][ ] afa (at: suntec)
3 more tasks to go!
```
___

### Add Todo: `todo`
Adds a Todo task to the list of tasks.

Format : `todo TODO_NAME`

Example:  
Input: `todo watch anime`  
Output:
```
Okay! I've added the following:
[T][ ] watch anime
2 more tasks to go!
```
---

### List Tasks: `list`
Lists out all the tasks.

Format : `list`

Example:

Input : `list`  
Output :
```
Here are your tasks:
1. [T][ ] watch anime
2. [D][ ] finish episode (by: 21 MAY 2021)
3. [E][ ] afa (at: suntec)
```
---

### Mark Tasks as Done: `done`
Mark a task as done.

Format : `done INDEX`

Example:  
Input : `done 1`  
Output : 
```
Yay good job! [T][X] watch anime has been completed
```
---
### Tag Task: `tag`
Tag tasks with additional descriptions.

Format : `tag INDEX TAG_DESCRIPTION`

Example:  
Input: `tag 1 fun`  
Output:
```
Oki! The tag fun has been added. Here is the modified task!
[T][X] watch anime #fun
```

---
### Delete Tasks: `delete`
Delete tasks from list of tasks.

Format : `delete INDEX`

Example:  
Input : `delete 3`  
Output :
```
Oki! I have removed this task:
[E][ ] afa (at: suntec)
2 more tasks to go!
```
---
### Find Tasks: `find`
Search for tasks that match the given keyword.

Format : `find KEYWORD`

Example:  
Input : `find book`  
Output:
```
Here are the matching tasks in your list:
2.  [D][ ] finish episode (by: 21 MAY 2021)
3.  [T][ ] watch lecture episode
Good luck!
```
---
### Exit Program: `bye`
Exits the program.

Format: `bye`

---
## FAQ  
Q: How do I save my progress?  
A: Data is automatically saved in your computer :)

Q: Why does my jar file not run?  
A: Check the data/duke.txt file if there are any errors, or clear it if it does not work.   
Do note that doing so erases your progress. 
