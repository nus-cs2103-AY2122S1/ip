# User Guide
Duke is a **Personal Assistant Chatbot** that helps you to keep track of various things. You can think of it as a digital task list, where you can add and delete tasks.

## Features 
Listed below are Duke's features:
1. Add a task to the task list
2. Mark a task as done
3. Find related tasks
4. Delete a task from the task list
5. View your own schedule (list of tasks) for a specific date
6. View your entire list of tasks
7. Auto save and load tasks to your hard drive

### Add Task

Adds a new task to the current list of tasks.
There are currently 3 kinds of tasks available to be added:
1. Todo
2. Event
3. Deadline

### Mark Task as done

Marks a specific task as done.

### Find related Tasks

Find all tasks that contains the keyword that you have entered.

### Delete Task

Removes a specific task from the current task list.

### View schedule

Displays the entire list of tasks by hour on a specified date.

### List

Displays all tasks.

### Exiting the program

Immediately closes the app.

## Usage

### `todo` - Adds a todo Task

Use this command to add a task that you have to do without a hard deadline.
- The description of the todo can contain an arbitrary amount of words (at least 1 word).

Example of usage: 

`todo return book`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [T][] return book
Now you have 1 tasks in the list.
```

### `event` - Adds a event Task

Use this command to add an event that you have to do attend with a specified date and time.
- The description of the event can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy hhmm (24h format) respectively.

Example of usage: 

`event carnival /at 2/12/2021 0930`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [E][] carnival (at: 2 Dec 2021 09:30 AM)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline Task

Use this command to add a deadline that you have to meet with a specified date and time.
- The description of the deadline can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy hhmm (24h format) respectively.

Example of usage: 

`deadline CS2100 Tut 1/at 2/12/2021 0930`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task:
  [E][] carnival (at: 2 Dec 2021 09:30 AM)
Now you have 2 tasks in the list.
```
