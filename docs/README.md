# DukePro

Welcome to DukePro. This app houses Duke, a friendly chat bot who can help you manage your tasks effectively.

## Quick start

**Prerequisite: Ensure that you have already installed Java 11.**

1. Download DukePro [here](https://www.youtube.com/watch?v=dQw4w9WgXcQ)
2. Move the jar file `duke.jar` to the folder that you want to be the home folder for DukePro
3. Open this folder in command window
4. Run this command `java -jar duke.jar`
5. Enjoy the app 

## Features

### Add and Delete tasks

The user can add the following types of tasks:
`todo` - a generic task
`event` - a task that happens at a specific time
`deadline` - a task that needs to be done before a specific time

The user can also delete tasks.

### Mark as done

The user can mark tasks as done.

### List all tasks

The user can list all the existing tasks in their list of tasks.

### Find a task

The user can find tasks that contain a specific keyword.

### Set a task to be started after another task/time

The user can set a task that they want to start after another task or after a specified time.

### List all tasks that should be started after a task/time

The user can list all the tasks to be started after another task or a specified time.

### Automatic save

All the information changes will be stored immediately.

### Automatic reload

All the stored information will be loaded before the app starts.

### Exit the app

The user can exit the app with a command.


## Usage

### `todo` - Add a to-do task

Adds a to-do task into your list of tasks.

Format: `todo TASK_DESCRIPTION`

Example: `todo Watching TV`

Expected Outcome: (if you have 0 previous tasks)
```
Got it. I have added this task:
  [T][ ] Watching TV
Now you have 1 task(s) in the list.
```



### `event` - Add an event task

Adds an event task into your list of tasks. An event task has a time at which it will happen.

Format: `event TASK_DESCRIPTION /at yyyy-MM-dd HH:mm`

Example: `event Final Exam /at 2021-11-20 09:00`

Expected Outcome: (if you have 1 previous tasks)
```
Got it. I have added this task:
  [E][ ] Final Exam (at: Nov 20 2021 09:00)
Now you have 2 task(s) in the list.
```



### `deadline` - Add a deadline task `deadline`

Adds a deadline task into your list of tasks. A deadline task has a time before which the task must be completed.

Format: `deadline TASK_DESCRIPTION /by yyyy-MM-dd HH:mm`

Example: `deadline Assignment /by 2021-11-20 12:00`

Expected Outcome: (if you have 2 previous tasks)
```
Got it. I have added this task:
  [D][ ] Assignment (by: Nov 20 2021 12:00)
Now you have 3 task(s) in the list.
```



### `list` - List all your tasks

Lists all the tasks in your list of tasks in the order of when they were added. 

Format: `list`

Example: `list`

Expected Outcome 1: (if no task)
```
There is no task in your list
```

Expected Outcome 2: (if we go by the previous examples)
```
Here are the tasks in your list:
1.[T][ ] Watching TV
2.[E][ ] Final Exam (at: Nov 20 2021 09:00)
3.[D][ ] Assignment (by: Nov 20 2021 12:00)
```

### `done` - Mark a task as done

Marks a task in your list of tasks as done. The `TASK_INDEX` will be the number assigned to the task by the `list` command.

Format: `done TASK_INDEX`

Example: `done 2`

Expected Outcome:
```
Here are the tasks in your list:
1.[T][ ] Watching TV
2.[E][X] Final Exam (at: Nov 20 2021 09:00)
3.[D][ ] Assignment (by: Nov 20 2021 12:00)
```



### `delete` - Delete a task

Deletes a task in your list of tasks as done. The `TASK_INDEX` will be the number assigned to the task by the `list` command.

Format: `delete TASK_INDEX`

Example: `delete 1`

Expected Outcome:
```
Noted. I've removed this task:
  [T][ ] Watching TV
Now you have 2 task(s) in the list.
```



### `find` - Find a task

Finds tasks that contain the specified keyword.

Format: `find KEYWORD`

Example: `find Exam`

Expected Outcome 1: (if not found)
```
There is no matching task
```

Expected Outcome 2: (if found)
```
Here are the matching tasks in your list:
1.[E][X] Final Exam (at: Nov 20 2021 09:00)
```



### `do ... after task ...` - Set a task to be started after another task

Sets the task to be done after another task using the task numbers `TASK_INDEX` assigned by the `list` command.

Format: `do TASK_INDEX_1 after task TASK_INDEX_2`

Example: `do 2 after task 1`

Expected Outcome:
```
Got it. I have set this task:
  [D][ ] Assignment (by: Nov 20 2021 12:00)
After this task:
  [E][X] Final Exam (at: Nov 20 2021 09:00)
```



### `do ... after ...` - Set a task to be started after a time

Sets the task to be done after a time.

Format: `do TASK_INDEX after yyyy-MM-dd HH:mm`

Example: `do 1 after 2021-11-19 12:00`

Expected Outcome:
```
Got it. I have set this task:
  [E][X] Final Exam (at: Nov 20 2021 09:00)
After Nov 19 2021 12:00
```



### `after task` - Get a list of tasks to start doing after another task

Gets a list of tasks to be started after another task with the number `TASK_INDEX` given by the command `list`.

Format: `after TASK_INDEX`

Example: `after task 1`

Expected Outcome: 
```
After this task:
  [E][X] Final Exam (at: Nov 20 2021 09:00)
You need to do the following tasks:
1.[D][ ] Assignment (by: Nov 20 2021 12:00)
```



### `after` - Get a list of tasks to start doing after a time

Gets a list of tasks to be started after a time.

Format: `after yyyy-MM-dd HH:mm`

Example: `after 2021-11-19 13:00`

Expected Outcome: 
```
After:
  Nov 19 2021 13:00
You need to do the following tasks:
1.[E][X] Final Exam (at: Nov 20 2021 09:00)
```



### `bye` - Quit the app

Exits the app immediately.

Format: `bye`

Expected Outcome:
```
poof!
```
