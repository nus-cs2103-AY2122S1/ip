# User Guide
**Carrot Task Manager** is a desktop app for managing tasks. It provides the functionalities of a todo list in a dialogue format. A user can chat with Ms.Carrot (the computer), and she will help the user keep track of his or her tasks.

## Quick Start
1. Ensure you have Java 11 installed in your computer.
2. Download the **IP.jar** from v0.2 [here](https://github.com/chingh20/ip/releases).
3. Copy the file to the folder you want to use for your **Carrot Task Manager**.
4. Double-click the file to start the app. You will see a window like this:  
  
   ![Image](https://github.com/chingh20/ip/blob/master/src/main/resources/images/StartUpPage.png)
  
5. Type any command in the text box and press Enter on your keyboard or click Send to execute it.  
   Some sample commands you might want to try is:   
   `list` - Shows your todo list.  
   `todo start list` - Adds "start list" as a task in your todo list.     
   Refer to the Usage section below for details of each command.
   
## Features 
The prominent feature of **Carrot Task Manager** is its todo list. 
The tasks in the todo list come with the following format:  
\[\*TypeOfTask\*\]\[\*FinishingStatus\*\] \*TaskDescription\*
  
There are three types of task:   
- **ToDo**  
The most simple type of task, where only a task description is required from the user.  
\[T\]\[\*FinishingStatus\*\] \*TaskDescrption\*  
- **Event**  
A type of task where the user is required to specify a time in which the task occurs.  
\[E\]\[\*FinishingStatus\*\] \*TaskDescrption\* (at: \*EventTime\*)  
- **Deadline**  
A type of task where the user is required to specify a deadline for the task.   
\[D\]\[\*FinishingStatus\*\] \*TaskDescrption\* (by: \*DueDate\*)  
  
Carrot Task Manager allows user to talk to Ms.Carrot (the computer) and perform the following actions to the todo list:  
1. **Add** a task. 
2. **Delete** a task.
3. **Find** a task.
4. **Mark** a task **as done**.
5. **View** the todo list.  
6. **Save** the todo list to the user's device. 

>Note: \* \* is used in the user guide to specify places for the user's own input.  

## Usage

### Adding a Task

A user can add different types of task to the todo list based on the following commands.
- `todo *TaskDescription*` - Adding a ToDo  

- `event *TaskDescription* \at *EventTime*` - Adding an Event 
  
- `deadline *TaskDescription* \by *DueDate*` - Adding a Deadline
  - Note: \*DueDate\* should be in the format of yyyy-mm-dd
  
Example of usage: 
  - `todo lab report`  
  - `event zoom meeting \at 3pm`
  - `deadline cs3230 assignment \by 2021-09-18`  

### Deleting a Task

A user can delete a task in the todo list with the following command:
- `delete *TaskNumber*` - Deleting the task with the specified task number.   
  - \*TaskNumber\* should be an integer between 1 to N, where N is the total number of tasks in the user's todo list.  

Example of usage: 
- `delete 1` - deletes the first task in the todo list, and assigns new task number for all the remaining tasks in the list.  
- User's list **before** task 1 is deleted:  
 1. \[T\]\[ \] dance practice 
 2. \[E\]\[ \] rehearsal (at: 12)
- User's list **after** task 1 is deleted:
 1. \[E\]\[ \] rehearsal (at: 12)   
  
Note: Task number is given according to the order in which a task is added into the todo list. A user can check the task number of a task by viewing the whole todo list with `list`.

### Finding a Task

A user can find a specific task in the todo list based on the following command:
- `find *Keyword*` - Finding a specific task in the user's todo list based on the \*Keyword\*

Example of usage: 
- User's list:  
 1. \[T\]\[ \] dance practice 
 2. \[E\]\[ \] rehearsal (at: 12)
 3. \[T\]\[X\] deliver pizza 
- `find d`
- Result after `find d`:
 1. \[T\]\[ \] dance practice
 2. \[T\]\[X\] deliver pizza 

### Marking a Task as Done

A user can mark a task in the todo list as done with the following command:
- `done *TaskNumber*` - Marking the task with the specified task number as done.   
  - \*TaskNumber\* should be an integer between 1 to N, where N is the total number of tasks in the user's todo list.  

Example of usage: 
- `done 1` - marks the first task in the todo list as done. The FinishingStatus of the first task will now be shown as "X" in the user's todo list.   
- User's list **before** task 1 is marked as done:  
 1. \[T\]\[ \] dance practice 
 2. \[E\]\[ \] rehearsal (at: 12)
- User's list **after** task 1 is marked as done:
 1. \[T\]\[X\] dance practice
 2. \[E\]\[ \] rehearsal (at: 12)  

Note: Task number is given according to the order in which a task is added into the todo list. Users can check the task number of a task by viewing the whole todo list with `list`.

### Viewing the Todo List  

A user can view the entire todo list with the command:
`list`

Example of usuage:
- `list`
- Result after `list`:  
Here are the tasks in your list:  
 1. \[T\]\[ \] dance practice 
 2. \[E\]\[ \] rehearsal (at: 12)  
 
### Saving the Todo List

A user can save the todo list to his or her local device with the command:
`save`

Note: The todo list will be saved in a file called *file.txt*. If the user already has a file called *file.txt*, the content of the original file will be overwritten by this new todo list. The user will be able to access his or her todo list the next time he or she opens the **Carrot Task Manager**. 


