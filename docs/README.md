# User Guide
Duke is a **Personal Assistant Chatbot** that helps you to keep track of various things. You can think of it as a digital task list, where you can add and delete tasks.

<img width="427" alt="Hello, I'm Duke" src="https://user-images.githubusercontent.com/65549665/133202028-9bd38670-12c3-43c4-9f5e-be9c5be4d005.png">

## Features 
Listed below are Duke's features:
1. Add a task to the task list
2. Mark a task as done
3. View your entire list of tasks.
4. View your own schedule (list of tasks) for a specific date
5. Delete a task from the task list
6. Find related tasks
7. Auto save and load tasks to your hard drive

### Add Task

Adds a new task to the current list of tasks.
There are currently 3 kinds of tasks available to be added:
1. Todo
2. Event
3. Deadline

### List

Displays all tasks.

### View schedule

Displays the entire list of tasks by hour on a specified date.

### Mark Task as done

Marks a specific task as done.

### Find related Tasks

Find all tasks that contains the keyword that you have entered.

### Delete Task

Removes a specific task from the current task list.

### Exiting the program

Immediately closes the app.

## Usage

### `todo` - Adds a todo Task

Use this command to add a task that you have to do without a hard deadline.
- The description of the todo can contain an arbitrary amount of words (at least 1 word).

Example of usage: `todo return book`

Expected outcome:

<img width="408" alt="todo" src="https://user-images.githubusercontent.com/65549665/133202878-54b4553d-ff4e-40cd-8483-2c20c7b580f2.png">

```
Got it. I've added this task:
  [T][] return book
Now you have 1 tasks in the list.
```

### `event` - Adds a event Task

Use this command to add an event that you have to do attend with a specified date and time.
- The description of the event can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy and hhmm (24h format) respectively.

Example of usage: `event carnival /at 2/12/2021 0930`

Expected outcome:

<img width="408" alt="event" src="https://user-images.githubusercontent.com/65549665/133203145-634352ee-f11e-494c-b589-07ff4dcf1d92.png">

```
Got it. I've added this task:
  [E][] carnival (at: 2 Dec 2021 09:30 AM)
Now you have 2 tasks in the list.
```

### `deadline` - Adds a deadline Task

Use this command to add a deadline that you have to meet with a specified date and time.
- The description of the deadline can contain an arbitrary amount of words (at least 1 word).
- Time and date follows the format dd/mm/yyyy and hhmm (24h format) respectively.

Example of usage: `deadline CS2100 Tut 1 /at 2/12/2021 1200`

Expected outcome:

<img width="407" alt="deadline" src="https://user-images.githubusercontent.com/65549665/133203198-96adf05a-c82e-4c20-8bdb-7d90216ad94c.png">

```
Got it. I've added this task:
  [D][] CS2100 Tut 1 (at: 2 Dec 2021 12:00 PM)
Now you have 3 tasks in the list.
```

### `list`

Example of usage: `list`

Expected outcome:

<img width="412" alt="list" src="https://user-images.githubusercontent.com/65549665/133203214-91d226b5-8626-4d11-8e5f-d42fe403aa8b.png">

Description of the outcome.

```
Here are the tasks in your list:
1.[T][] return book
2. ...
```

### `schedule`

Use this command if you want to view all your tasks on a specific date by hour.
- Date follows the format dd/mm/yyyy.

Example of usage: `schedule 2/12/2021`

Expected outcome:

<img width="412" alt="schedule" src="https://user-images.githubusercontent.com/65549665/133203253-53aa5551-abf9-4b37-be5a-07ff78073b23.png">

```
Here are the tasks for 2 Dec 2021:

09:00 AM to 10:00 AM:
[E][] carnival (at: 2 Dec 2021 09:30 AM)

12:00 PM to 01:00 PM:
[D][] CS2100 Tut 1 (by: 2 Dec 2021 12:00 PM)
```

### `done`

You can choose to mark a task as done by specifying the INDEX of the task. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...!!!
- You can check if a task is done by using `list` again. There should be a cross to the left of the task name.

Example of usage: `done 2`

Expected outcome:

<img width="411" alt="done" src="https://user-images.githubusercontent.com/65549665/133203284-ac2dbbc1-6ddc-4427-bc2c-bed489d68e70.png">

```
Nice! I've marked this task as done:
  [E][X] carnival (at: 2 Dec 2021 09:30 AM)
```

### `delete`

You can choose to delete a task by specifying the INDEX of the task. 
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...!!!
- You can check if a task is deleted by using `list` again. The task should be gone.

Example of usage: `delete 1`

Expected outcome:

<img width="409" alt="delete" src="https://user-images.githubusercontent.com/65549665/133203318-04ac033a-9b51-4f24-ace2-cb47931531f3.png">

```
Noted! I've removed this task:
  [T][] return book
Now you have 2 tasks in the list.
```

### `find`

Find all tasks that contain your specified keyword (case-insensitive).

Example of usage: `find tut`

Expected outcome:

<img width="409" alt="find" src="https://user-images.githubusercontent.com/65549665/133203447-0d8c0863-aa19-4f28-8a18-ae749940b7a9.png">

```
Here are the matching tasks in your list:
1.[D][] CS2100 Tut 1 (by: 2 Dec 2021 12:00 PM)
```

### `bye`

Exits the program immediately.

Example of usage:`bye`

### `save` - Saving data to local drive

For every new task that had been added or deleted from Duke, Duke will perform an auto-save to the *data.txt* file in the same directory as the *jar* file. Closing the app will not cause Duke to lose track of the tasks that had been previously added. Restarting the app should load previously added tasks and performing the command `list` should display these tasks.
