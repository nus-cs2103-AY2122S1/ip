# Project Duke

Duke is a task manager application with a chatbot-like interface. 

It manages your tasks for you so you can focus on more important stuff.

<img width="400" alt="Ui" src="https://user-images.githubusercontent.com/68073477/133919775-ff4d2fe7-8c61-4b27-8b9b-cead14975c09.png">



# User Guide


## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `duke.jar` from here.
3. Copy the file to the folder you want to use as the home folder for the Duke.
4. Double-click the file to start the app. The GUI similar to the one above should appear in a few seconds.
5. Type the command in the command box and press Enter to execute it.
   - Some examples:
     - `list`: display all current tasks.
     - `todo task1`: add a Todo called task1.
     - `delete 3`: delete the 3rd task in your list.
     - `bye`: exit the Duke application.
6. Refer to the Features below for details of each command.


## Features


### `list`: display all tasks

Shows all current tasks in Duke.

Format: `list`

Expected response example:
```
Here are the tasks in your list:
1. [T][X] task1
2. [D][] deadline1 (by: Thu, 26/Aug/2021 08:20 PM)
3. [E][] event1 (at: Mon, 20/Sep/2021 12:00 PM)
```


### `bye`: end the application

Ends and exits from Duke application.

Format: `bye`


### `done`: mark a task as done

Mark a certain task in Duke as completed.

Format: `done INDEX_OF_TASK`

Example: `done 1` 

Mark the first task in Duke as done.

Expected response example:
```
Nice! I've marked this task as done:
[D][X] deadline1 (by: Thu, 26/Aug/2021 08:20 PM
```


### `delete`: delete a task

Delete a task from Duke.

Format: `delete INDEX_OF_TASK`

Example: `delete 1` 

Delete the first task in Duke.

Expected response example:
```
Noted. I've removed this task:
[T][] task4
```


### `todo`: add a Todo

Add a task of type Todo to Duke.

Format: `todo NAME_OF_TODO`

Example: `todo lunch` 

Add a Todo task named `lunch` into Duke.

Expected response example:

  - If the Todo is new:
  ```
  Roger! Added the task:
    [T][] lunch
  Now you have 4 tasks in your list.
  ```
  
  - If the Todo already exists in Duke:
  ```
  This Todo is already in the task list.
  ```

### `deadline`: add a Deadline

Add a task of type Deadline to Duke.

Format: `deadline NAME_OF_DEADLINE /by DD/MM/YYYY HH:MM`

Example: `deadline assigment1 /by 20/09/2021 23:59` 

Add a Deadline task named `assignment1` with deadline at 20 Sep 2021, 11.59PM.

Expected response example:

  - If the Deadline is new:
  ```
  Roger! Added the task:
    [D][] assignment1 (by: Mon, 20/Sep/2021 11:59 PM)
  Now you have 5 tasks in your list.
  ```
  
  - If the Deadline already exists in Duke:
  ```
  This Deadline is already in the task list.
  ```


### `event`: add an Event

Add a task of type Event to Duke.

Format: `event NAME_OF_EVENT /at DD/MM/YYYY HH:MM`

Example: `event go shopping /at 21/09/2021 16:00`

Add an Event task named `go shopping` with deadline at 21 Sep 2021, 4.00PM.

Expected response example:

  - If the Event is new:
  ```
  Roger! Added the task:
    [E][] go shopping (at: Tue, 21/Sep/2021 04:00 PM)
  Now you have 6 tasks in your list.
  ```
  
  - If the Event already exists in Duke:
  ```
  This Event is already in the task list.
  ```


### `find`: find a matching task

Find a task with name containing the content to be found.

Format: `find CONTENT_TO_BE_FOUND`

Example: `find shopping`

Find a task with name containing `shopping`.

Expected response example:

```
Here are the matching tasks in your list:
1. [E][] go shopping (at: Tue, 21/Sep/2021 04:00 PM)
```


## Format

Here is an explanation of the format displayed of a Duke task.

```
1. [E][] go shopping (at: Tue, 21/Sep/2021 04:00 PM)
```

Component | Explanation
--------- | -----------
`1.` | The index of the task
`[E]` | The type of this task. `[T]` is Todo. `[D]` is Deadline. `[E]` is Event.
`[]` | The status of this task. `[X]` means it is done. `[]` means it is not done.
`go shopping` | The name of this task.
`(at: Tue, 21/Sep/2021 04:00 PM)` | The time of a task, only applicable to Deadline and Event
