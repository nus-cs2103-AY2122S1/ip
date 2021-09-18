# Duke - User Guide
Duke is a task manager that will make help you stay on top of the things you need to achieve

## Features 

### Feature- Ability to add tasks

Duke currently supports adding of three type of tasks:
1. Todos
2. Events (you can specify the event date and time)
3. Deadlines (you can specify the dueDate)

### Feature - Ability to mark tasks as done  

Once you have completed a todo, attended and event or met a deadline 
you can mark it as done.

### Feature - List all tasks

You can list all the tasks you have to have a high level overview of the 
things you need to achieve.

### Feature - Delete Tasks

If you mistakenly added a task or no longer have a need for it you can delete it.

### Feature - Find Tasks

If you find yourself having a lot of tasks, you do have the ability to filter tasks by keywords

## Usage

### `todo [description]` 

This command adds a todo to your task list (along with its description).

Example of usage: 

`todo essay assignment`

Duke should respond as follows:
```
Got it. I've added this task:
 [T][] essay assignment
You have 1 tasks in the list.
```


### `deadline [description] /by [dd/MM/YYYY HHmm]`

This command adds a deadline to your task list (along with its description and due date)
Please note that the date time input has to match the above format strictly.

Example of usage :

`deadline return book /by 17/09/2021 1159`

Duke should respond as follows:
```
Got it. I've added this task:
 [D][] return book (by:Sep 17 2021 11:59 AM)
You have 2 tasks in the list.
```

### `event [description] /at [dd/MM/YYYY HHmm]`

This command adds a event to your task list (along with its description and the date and time it takes place)
Please note that the date time input has to match the above format strictly.

Example of usage:

`event book festival /at 17/09/2021 1159`

Duke should respond as follows:
```
Got it. I've added this task:
 [E][] book festival (at:Sep 17 2021 11:59 AM)
You have 3 tasks in the list.
```

### `list`

This command should list all your tasks.

Example of usage:

`list`

Duke should respond as follows (supposing you followed the three above commands to add the tasks):
```
Here are the tasks in your list:
1. [T][] essay assignment
2. [D][] return book (by:Sep 17 2021 11:59 AM)
3. [E][] book festival (at:Sep 17 2021 11:59 AM)
```

### `done [taskIndex]`

This command will mark the task corresponding to the taskIndex as done.

Example of usage:

`done 1`

Duke should respond as follows :
```
Nice this task has been marked done:
 [T][X] essay assignment
```

### `find [searchTerm]`

This command will search for tasks that match the searchTerm.

Example of usage:

`find book`

Duke should respond as follows (supposing you followed the three above commands to add the tasks):
```
Here are the matching tasks in your list:
  1. [D][] return book (by:Sep 17 2021 11:59 AM)
  2. [E][] book festival (at:Sep 17 2021 11:59 AM)
```

### `delete [taskIndex]`

This command will delete the task corresponding to the taskIndex.

Example of usage:

`delete 1`

Duke should respond as follows (supposing you followed the three above commands to add the tasks):
```
Noted. I've removed this task:
 [T][X] essay assignment
You have 2 tasks in the list.
```

The above commands should help you get started with using Duke.

Acknowledgement: The GUI of the app was done using JavaFX and heavily followed the tutorial from:
[JavaFX tutorial @SE-EDU/guides](https://se-education.org/addressbook-level3/UserGuide.html#features)





