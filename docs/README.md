# User Guide

![Ui](Ui.png)

## Features 

### Feature - Adding task

You can add 3 different tasks which are Todo, Event, Deadline

### Feature - Auto save

Duke auto saves your tasks that you added and loads it 
when you next time duke is opened. 

### Feature - Find task

You can find task by its task name.

--- 

## Usage

### `todo` 

Adds todo task to duke

`todo [tasks to add] #[priority(HIGH,MID or LOW)]`

Example of usage: 

`todo read book #LOW`

### `event` 

Adds event task to duke

`todo [event to add] at/[time of your event] #[priority(HIGH,MID or LOW)]`

Example of usage:

`event ride class at/ 12/03/2021 2021 #MID`

### `delete` 

Adds deadlist task to duke

`todo [deadline to add] bt/[time of deadline] #[priority(HIGH,MID or LOW)]`

Example of usage:

`deadline submit report by/ 13/03/2021 2021 #HIGH`

### `list` 

Adds event task to duke

`list`

Example of usage:

`list`

Expected outcome:

lists out all the tasks 

```
1. [T][ ] read book #LOW
2. [D][ ] ride class (at 13/03/2021) #MID
3. [E][ ] submit report (by 12/03/2021) #HIGH
```

### `find` 

finds a task by its key word

`find [key word]`

Example of usage:

`find book`

Expected outcome:

lists all the matching tasks 

```
Here are the mathcing task in your list 
1. [T][ ] read book #LOW
```

### `done`

mark task as done 

`done [task id]`

Example of usage:

`done 1`

Expected outcome:

Mark the task as done
```
nice Nice! I have marked this task as done!
1. [T][X] read book #LOW
```

### `delete`

mark task as done

`delete [task id]`

Example of usage:

`delete 1`

Expected outcome:

deletes the task and tells user how many tasks are left
```
Noted, I have removed this task:: 
1. [T][X] read book #LOW
Now you have 2 tasks in the list
```