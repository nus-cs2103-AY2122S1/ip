# User Guide

## Features 
=======

### Feature - Add Tasks: ToDo, Deadline and Event

Add a new task. It can be any of the following types:
- ToDo: A task without any deadline or timing.
- Deadline: A task with a specific deadline.
- Event: A task with a specific timing at which it will happen.

### Feature - Manage Tasks: List, Done, Delete and Find

You can execute the following commands on your task list:
- List: Returns a number list of your tasks.
- Done: Marks the specified task as "Done".
- Delete: Removes the specified task from the list.
- Find: Returns a numbered list of tasks which contain a specified keyword.

### Feature - Save and Load Data

- Saves your task data to a local data file when you enter the Exit command.
- Loads your save data when reopening the app after closing.


## Usage


### `todo` - Add a ToDo type task to your list.

Shows the added task, as well as the full list of tasks.

Example of usage: 

`todo CS2100 Tutorial 4`

Expected outcome:

A new ToDo for CS2100 Tutorial 4 will be added to the list.

```
Fine. I've added this meaningless task to your list:

 --> 1. [T][ ] CS2100 Tutorial 4
 
Satisfied now? You have 1 item in your list:

1. [T][ ] CS2100 Tutorial 4
```


### `deadline` - Add a Deadline type task to your list.

Shows the added task, as well as the full list of tasks.

Example of usage: 

`deadline Assignment 2 /by 23/09/2021 2359`

Expected outcome:

A new Deadline for Assignment 2 will be added to the list.

```
Fine. I've added this meaningless task to your list:

 --> 2. [D][ ] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
 
Satisfied now? You have 1 item in your list:

1. [T][ ] CS2100 Tutorial 4
2. [D][ ] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
```


### `event` - Add an Event type task to your list.

Shows the added task, as well as the full list of tasks.

Example of usage: 

`event 2103T tp meeting /at Friday 4-6pm`

Expected outcome:

A new Event for the 2103T tp meeting will be added to the list.

```
Fine. I've added this meaningless task to your list:

 --> 3. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
 
Satisfied now? You have 1 item in your list:

1. [T][ ] CS2100 Tutorial 4
2. [D][ ] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
3. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
```


### `list` - Lists out all your tasks

List out all your tasks in a numbered list, with each task's full description.

Example of usage: 

`list`

Expected outcome:

The list of all your tasks.

```
Here are the tasks in your list:

1. [T][ ] CS2100 Tutorial 4
2. [D][ ] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
3. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
```


### `done` - Marks a task as "Done"

Marks a specific task as "Done", if the task exists.

Example of usage: 

`done 2`

Expected outcome:

Marks task number 2 as "Done".

```
Wow. Congratulations. You have completed the following task:

[D][X] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)

Are you happy now? This is your updated list:

1. [T][ ] CS2100 Tutorial 4
2. [D][X] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
3. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
```


### `delete` - Deletes a task from your list

Deletes an existing task from your list.

Example of usage: 

`delete 1`

Expected outcome:

Deletes task number 1 from the list.

```
Since you are so lazy, I've helped you delete this task:

1. [T][ ] CS2100 Tutorial 4

Go do something useful with your life, like maybe some of your remaining tasks:

1. [D][X] Assignment 2 (by: Thursday, 23 September 2021, 11:59 PM)
2. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
```


### `find` - Returns all the tasks that fit a contain a certain keyword

Finds all the tasks that contain a specified case-insensitive keyword and return the list. 

Example of usage: 

`find MEeting`

Expected outcome:

Returns a list of all the tasks which contain the word "meeting".

```
Your keyword is: "meeting".

Here are the matching tasks in your list:

2. [E][ ] 2103T tp meeting (at: Friday 4-6pm)
```


### `help` - Shows the user a general help page, and optional help sub-pages

Shows a help page, as well as instructions on how to view more specific help pages.

Example of usage: 

`help`

Expected outcome:

Returns a help screen with more instructions on how to get more help.

```
You can search for help for any of the following commands:
- list
- todo
- deadline
- event
- done
- delete
- find
- exit

Just type "help" followed by a space and any of the above keywords, then hit enter. 

Example: "help deadline"

For full documentation of my abilities, type the following url into your browser:

https://necrowolf28.github.io/ip/

and then press enter.
```

Another example of usage: 

`help delete`

Expected outcome:

Returns a help screen with instructions on using the "delete" command.

```
The Delete command removes a task from the list "Done".

To delete a task, type "delete" followed by a space, then the task number of the task you want to remove.

Example: "delete 6"

Take note that the number must be a valid number, i.e. the tasks exists.

For full documentation of my abilities, type the following url into your browser:

https://necrowolf28.github.io/ip/

and then press enter.
```
