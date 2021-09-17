# User Guide

Welcome to the DukeBot by Nikki. What's better than a bot
to help you keep track of your tasks in your day to day busy life?
The same bot, but with extra attitude.

##Quick Start 
Launch the bot and type "help" to see the list of commands available.
## Features 
You can add 3 different types of tasks.
1. todo 
2. deadline
3. event

Here are some additional features you can use to navigate the bot.
1. list
2. find
3. delete
4. done
5. help
### ToDo

To add a simple task without any attached time or deadline. Add a `todo` task.
For example, you might want to go running.

    todo go running

### DeadLine

To add a task with a deadline attached. Add a `deadline` task.
For example, you might have an assignment due on 02/12/2021 2359.

    deadline hand in assignment /by 2/12/2021 2359

Remember to indicate your deadline using this format `"/by dd/mm/yyyy HHmm"`.

### Event

To add a task with a non-standard time format. Add an `event` task.
For example, you might have a carnival you want to go at 10-ish pm.

    event carnival /at 10-ish pm

Remember to indicate your timing using this format `"/at <whatever format>"`.

## Additional Features

### `list` - Displays all the current tasks in your task list.

When you input `list`, duke bot will respond with the items in your 
task list. 

Example of usage: 

`list`

The expected outcome:
````
Here are the items in your list
1. [T][X] Running
2. [D][ ] return book (by: Dec 02 2019, 6.00 pm)
3. [E][ ] Project meeting (at: Mon 2-4pm)
4. [D][ ] run 2.4km (by: Sep 02 2019, 6.00 pm)
````
Duke Bot will show you the tasks in your task list in the 
appropriate format. 
1. [T] Stands for todo task.
2. [D] Stands for deadline task.
3. [E] Stands for event task.

The square next to it indicates whether the task has been completed.

### `find` - Helps you find tasks in your task list.

When you input `find`, duke bot will find tasks that contain the 
keyword you have input.

Example of usage:

`find run`

The expected outcome:
````
We found these for you boss
1. [T][X] Running
2. [D][ ] run 2.4km (by: Sep 02 2019, 6.00 pm)
````
Duke Bot will find tasks that contain the keywords partially or fully.

### `delete` - Helps you remove tasks from your task list.

When you input `delete`, duke bot will delete the task that corresponds
to the number you have input.

Example of usage:

`delete 1`

The expected outcome:
````
Alrighty! I have deleted this task:
  [T][X] Running
Now you have 3 task(s) in total!
````
Duke Bot will mark the task that corresponds to the index it is given
in the task list.

### `done` - Helps you mark tasks as done in your task list.

When you input `done`, duke bot will mark the task that corresponds
to the number you have input as done.

Example of usage:

`done 2`

The expected outcome:
````
Good job for this thing done man:
  [E][X] project meeting (at Mon 2-4pm)
````
Duke Bot will mark the task that you have indicated as done by checking the
box as done as follows `[X]`.

### `help` - Shows you what commands can be used.

When you input `help`, duke bot will display the help message.

Example of usage:

`help`




