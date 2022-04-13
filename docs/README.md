# User Guide
Hello this is Duke!

It will help you keep track of your tasks, and save them on your device. 

## Features 

### Feature-Managing Tasks

Duke helps you manage three kind of tasks:

1. Deadline - A deadline task is one which needs to be completed within a stipulated period of time. For example, deadline to finish an assignment or group project submission. 
2. Event - An event task is one which occurs on a particular time. For example attending an online conference, zoom call, family function or official meetings. 
3. ToDo - A toDo task is one which does not have any particular date or time. This task serves the purpose of reminding the user that it needs to done. For example, visiting the dentist.  

### Feature-Prioritizing Tasks

Duke helps you prioritize your tasks in the following way: 

1. High - The tasks with high priority need to be given utmost importance. 
2. Low - The tasks with low priority need to be given the least importance. 

## Usage

### `todo` - Add a ToDo task

This command adds a toDo task to the existing list of tasks. You do not have to enter the date for this type of task. 

Format:

`todo breakfast`

Output:

```
Got it. I've added this task:
[T][][Low] breakfast
Now you have 8 tasks in the list
```
### `list` - Display all the tasks

This command is used to display the list of all the tasks. 

Format:

`list`

Output:

```
Here are the tasks in your list
1.[T][][Low]dance
2.[D][X][High]jogging (by:Sep 28 2021, 16:00)
3.[E][][Low]gym (at:Sep 23 2021, 17:00)
```
### `delete` - Delete a task

This command is used to delete a task from the list of tasks. 

Format:

`delete 7`

Output:

```
Noted. I've removed this task:
[D][][Low]CS2103Tip (by:Sep 20 2021, 11;59)
Now you have 11 tasks in the list
```
### `done` - Mark the task as Completed

This command is used to mark the task as completed. This command is basically used to change the status of task from incomplete to complete.  

Format:

`done 3`

Output:

```
Nice! I've marked this task as done:
[E][X][Low]gym(at:Oct 26 2021,13:00)
```
### `find` - Find tasks similar to the given keyword

This command is used to find similar tasks from the list of tasks matching the keyword entered by the user. 

Format:

`find book`

Output:

```
Here are the matching tasks in your listbook
11.[T][][Low]read book
12.[T][][Low]revise book
```
### `deadline` - Add a Deadline task 

This command is used to add a deadline task to the list of tasks. You need to specify the date and time as well for this type of task. The date and time should strictly follow this format: dd/mm/yyyy HHmm 

Format:

`deadline assignment /by 24/09/2021 1800``

Output:

```
Got it. I've added this task:
[D][][Low] assignment (by: Sep 24 2021, 18:00)
Now you have 10 tasks in the list
```
### `event` - Add an Event task

This command is used to add an event task to the list of tasks. You need to specify the date and time as well for this type of task, The date and time should strictly follow this format: dd/mm/yyyy HHmm

Format:

`event Flutter /at 25/09/2021 1100`

Output:

```
Got it. I've added this task:
[E][][Low] Flutter (at: Sep 25 2021, 11:00)
Now you have 9 tasks in the list
```

### `high` - Change the Priority of Task to High

This command is used to change the priority of the task to High. The default priority of the task is set to by Low. In this release Duke has only two levels of Priority for each type of tasks:

1. High
2. Low

Format:

`high 5`

Output:

```
I've given HIGH priority to this task
[T][x][High] dinner
```
