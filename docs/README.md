# Tokio User Guide
Tokio is a personal assistant chatbot that helps the user, Rio, to keep track of various things.

Here is a sample interaction with Tokio,  
<img src="https://sharmainec.github.io/ip/images/sample.png" width=35% height=35%>

## Features 
> __Notes about the Command Format :heart:__
> 1. Words in UPPER_CASE are the parameters to be supplied by the user.  
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo task 1`  
> 2. Date accepted is strictly in `yyyy-MM-dd` format  
> e.g. in `deadline DESCRIPTION /by yyyy-MM-dd`, `yyyy-MM-dd` is a parameter which can be used as `deadline DESCRIPTION /by 2021-09-16`  
> 3. Time accepted is strictly in `HH:mm` format  
> e.g. in `event DESCRIPTION /at yyyy-MM-dd HH:mm`, `HH:mm` is a parameter which can be used as `event DESCRIPTION /at yyyy-MM-dd 12:00`
> 

### Types of tasks
Tokio is able to store 3 types of tasks:
1. `todo`: A task without any date/time attached to it *e.g., visit new theme park*
2. `deadline`: A task that needs to be done before a specific date *e.g., submit report by 11/10/2019*
3. `event`: A task that starts at a specific time *e.g., team project meeting on 2/10/2019 14:00*

### Feature 1 - Add tasks
Adds specified type of task to list.

#### Add todo
- Format: `todo DESCRIPTION`
<img src="https://sharmainec.github.io/ip/images/exampleTodo.png" width=40% height=40%>

#### Add deadline
- Format: `deadline DESCRIPTION /by yyyy-MM-dd`
<img src="https://sharmainec.github.io/ip/images/exampleDeadline.png" width=40% height=40%>

#### Add event
- Format: `event DESCRIPTION /at yyyy-MM-dd HH:mm`
<img src="https://sharmainec.github.io/ip/images/exampleEvent.png" width=40% height=40%>

*__note__: cannot add tasks with the same description*  
<img src="https://sharmainec.github.io/ip/images/exampleDuplicate.png" width=40% height=40%>


### Feature 2 - List tasks
Lists all tasks in list.
- Format: `list`
<img src="https://sharmainec.github.io/ip/images/exampleList.png" width=40% height=40%>


### Feature 3 - Mark task as done
Marks a task as done.
- Format: `done INDEX` 
 
<img src="https://sharmainec.github.io/ip/images/exampleDone.png" width=40% height=40%><br>
*__note__: total number of tasks does not change after you have completed your task*


### Feature 4 - Delete task
Removes a task from the list.
- Format: `delete INDEX`
<img src="https://sharmainec.github.io/ip/images/exampleDelete.png" width=40% height=40%>


### Feature 5 - Find task
Lists out all task with the specified keyword.
- Format: `find KEYWORD`
<img src="https://sharmainec.github.io/ip/images/exampleFind.png" width=40% height=40%>


### Feature 6 - Bye
Exits Tokio after 2s.
- Format: `bye`
<img src="https://sharmainec.github.io/ip/images/exampleBye.png" width=40% height=40%>  


## Command Summary
Action | Format, Examples
------ | ----------------
todo | `todo DESCRIPTION` <br> e.g., `todo CS2103T Tut 4`  
deadline | `deadline DESCRIPTION /by yyyy-MM-dd` <br> e.g., `deadline CS2013T iP Submission /by 2021-09-17`
event | `event DESCRIPTION /at yyyy-MM-dd HH:mm` <br> e.g., `event CS2013T tP meeting /at 2021-09-28 20:00`
list | `list`
done | `done INDEX` <br> e.g., `done 1`
delete | `delete INDEX` <br> e.g., `delete 1`
find | `find KEYWORD` <br> e.g., `find sep`
bye | `bye`
