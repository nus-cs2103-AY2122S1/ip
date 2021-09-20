# User Guide
Welcome to Duke! Duke is your personal desktop-based task manager, available both on the command line, as well as on Graphical User Interface (GUI).

![Homepage](https://github.com/aakanshanarain/ip/blob/master/docs/Homepage.png)

Continue reading to find out more.
## Features 
Duke provides you with many features to optimise your task management. Here's a quick list:
1. Add different types of tasks
2. List all the tasks in the task list
3. Mark a task as done
4. Delete a task
5. Find tasks that contain a keyword
6. Tag a task
7. Exit Duke
8. View stored tasks from a previous session

### Add different types of tasks

You can input 3 types of tasks; ```todo```, ```deadline``` and ```event```.
> For deadline and event tasks, you will need to specify a date and time as well.

![addTask](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/addTask.png)

### List all the tasks in the task list

You can ask Duke to list all the tasks in your task list at a particular time.

![showTasks](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/showTasks.png)

### Mark a task as done

You can check off a task once you've finished it.

![doneTask](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/doneTask.png)

### Delete a task

You can ask Duke to delete a task from your list.

![deleteTask](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/deleteTask.png)

### Find tasks that contain a keyword

Ask Duke to find all your tasks that contain a specific keyword.

![findTask](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/findTask.png)

### Tag a task

You can also tag tasks.

![tagTask](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/tagTask.png)

### View stored tasks from a previous session

Duke will automatically save your tasks, so the next time you want to view your list, your tasks will be right there for you.

![storedTasks](https://raw.githubusercontent.com/aakanshanarain/ip/master/docs/storedTasks.png)

## Usage

### `todo` - Add todo task

Add a todo task to your task list.

Example of usage: 

`todo research for essay`

Expected outcome:

```
Got it. I've added this task:
 [T][ ] research for essay
Now you have 1 task in the list.
```

### `event` - Add event task

Add an event task to your task list.

Example of usage:

`event CS2100 lab meeting /at 23-09-2021 1600`

Expected outcome:

```
Got it. I've added this task:
 [E][ ] CS2100 lab meeting (at: Sep 23 2021 04:00pm)
Now you have 2 tasks in the list.
```

### `deadline` - Add deadline task

Add a deadline task to your task list.

Example of usage:

`deadline CS2100 lab /at 25-09-2021 2359`

Expected outcome:

```
Got it. I've added this task:
 [D][ ] CS2100 lab (by: Sep 25 2021 11:59pm)
Now you have 3 tasks in the list.
```

### `list` - List all your tasks

Lists all your tasks at a particular time.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] research for essay
2. [E][ ] CS2100 lab meeting (at: Sep 23 2021 04:00pm)
3. [D][ ] CS2100 lab (by: Sep 25 2021 11:59pm)
```

### `done` - Mark a task as done

Marks a specified task as done.

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
 [T][X] research for essay
```

### `delete` - Delete a task

Deletes a specified task from the task list.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
 [T][X] research for essay
Now you have 2 tasks in the list.
```
### `find` - Find tasks with the keyword

Finds all the tasks in the task list which contain the keyword.

Example of usage:

`find lab`

Expected outcome:

```
Here are the matching tasks in your list:
1. [E][ ] CS2100 lab meeting (at: Sep 23 2021 04:00pm)
2. [D][ ] CS2100 lab (by: Sep 25 2021 11:59pm)
```


### `tag` - Tag a task

Tag a task in the task list.

Example of usage:

`tag 1 #meeting`

Expected outcome:

```
I've tagged this task as:
 [E][ ] CS2100 lab meeting (at: Sep 23 2021 04:00pm) #meeting
```

### `bye` - Exit Duke

Exit the current Duke session.

Example of usage:

`bye`

Expected outcome:

```
Bye! See you next time!
```
