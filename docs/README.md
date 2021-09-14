
# Dino User Guide

This is a greenfield Java project based on the generic project called
[***Project Duke***](https://nus-cs2103-ay2021s1.github.io/website/se-book-adapted/projectDuke/index.html).
<br>
The result of this project is a personal assistant chat-bot named *Dino*
capable of keeping track of tasks given by the user.
## Features 

### Manages 3 types of tasks!
 1. Todos
	 - Basic tasks with just a description 
 2. Deadlines
	 - Tasks which have a fixed deadline
 3. Events
	 - Tasks which has a fixed location

### Keep track of your tasks
 - Mark tasks as done
 - Delete old tasks from your list
 - Find tasks by a keyword
 - View your entire task list

### Saves your tasks between each use
 - Your tasks are saved locally
 - Quickly add a new task anytime!

### View a help window from just a click away!

## Usage

### `todo` - Add a new todo task

Format of usage: 

`todo (description of todo)`

Example usage: 

`todo Buy lunch`

Expected outcome:

A new todo task will be added to your task list

```
added [T][] Buy lunch
now you have: (number) tasks! 
```
&nbsp;

### `deadline` - Add a new deadline task

Format of usage: 

`deadline (description of deadline) /by (YYYY-MM-DD HH:MM)`

Example usage: 

`deadline do CS2100 Assignemnt /by 2021-09-15 13:00`

Expected outcome:

A new deadline task will be added to your task list

```
added [D][] deadline do CS2100 Assignemnt (by: 15 SEPTEMBER 2021 13:00)
now you have: (number) tasks! 
```

&nbsp;

### `event` - Add a new event task

Format of usage:

`event (description of event) /at (event location)`

Example usage: 

`event Attend Bob's Birthday Party /at The Club`

Expected outcome:

A new event task will be added to your task list

```
added [E][] Attend Bob's Birthday Party (at: The Club)
now you have: (number) tasks! 
```

&nbsp;


### `list` - Displays all your tasks

Example usage: 

`list`

Expected outcome:

Your task list will be displayed

```
Here are the items in your task list:
1. [T][X] Buy lunch
2. [D][] Do CS2100 Assignment (by: 15 SEPTEMBER 2021 13:00)
3. [E][] Attend Bob's Birthday Party (at: The Club)
```

&nbsp;


### `delete` - Deletes a task from your task list

Format of usage:

`delete (task number)`

Example usage: 

`delete 2`

Expected outcome:

The deleted task will be displayed and removed from the task list

```
Nice! Noted. I've removed this task:
[T][X] Buy lunch
You now have: (number) tasks remaining! 
```

&nbsp;


### `done` - Mark a task as done

Format of usage: 

`done (task number)`

Example usage: 

`done 2`

Expected outcome:

The task will be marked as done

```
Nice! I've marked this task as done:
[E][X] Attend Bob's Birthday Party (at: The Club)
```

&nbsp;


### `find` - Search for tasks
Format of usage: 

`find (keyword)`

Example of usage: 

`find Bob`

Expected outcome:

A list of tasks which contains the keyword

```
Here are the matching tasks in your list:
1. [E][X] Attend Bob's Birthday Party (at: The Club)
```

&nbsp;


### `bye` - Ends the program

Example of usage: 

`bye`

Expected outcome:

The program should terminate 

```
Bye! Hope to see you again soon!
```
