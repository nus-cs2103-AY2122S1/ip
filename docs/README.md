# User Guide

Duke allows users to easily manage their Deadlines, Events and ToDos through the use of the command line application.
Users can _create_, _list_, _find_, _update_ and _delete_ tasks as they desire in the application. Furthermore, Duke
also allows users to mark these tasks as _done_, allowing them to more easily keep track of what is yet to be completed.

---

## Getting started

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest duke.jar from [here]().
3. Copy the file to the folder you want to use as the _home folder_ for Duke.
4. Double-click the file to start the application. The GUI should appear in a few seconds. 
5. Type the command in the command box and press Enter to execute it. eg. typing `list` and pressing Enter will list all
   the tasks stored in the application.
   Some example commands you can try:
    * `todo wash dishes`: Adds a ToDo with the description set as `wash dishes`.
    * `find wash`: Finds all tasks with the keyword `wash` in their task description.
6. Refer to the Features below for details of each command.

---

## Features 

### `bye` - Exit

Exits the Duke application.

Example of usage: 

`bye`

Expected outcome:

Prints the following line of text on the screen and thereafter exits the application. 

```
Bye. Hope to see you again soon!
```

### `deadline` - Create a deadline task

Creates a Deadline task with the provided task description and due date. 

Example of usage:

`deadline complete IP tasks /by 2021-09-17`

Expected outcome:

Creates a Deadline with the description `complete IP tasks` to be completed by _17th September 2021_. The response will
include the total number of tasks currently stored in the list.

```
Got it. I've added this task:
  [D][ ] complete IP tasks (by: 17 Sep 2021)
Now you have 5 tasks in the list.
```

### `delete` - Delete a task

Deletes the specified task. 

Example of usage:

`delete 1`

Expected outcome:

Deletes the task stored at position 1 of the task list. 

```
Noted. I've removed this task:
  [T][X] read book
Now you have 4 tasks in the list.
```

### `done` - Mark a task as completed

Marks the specified task as completed.

Example of usage:

`done 1`

Expected outcome:

Marks the task stored at position 1 of the task list as completed.

```
Nice! I've marked this task as done:
  [T][X] join sports club
```

### `event` - Create an event task

Creates an Event task with the provided task description and date.

Example of usage:

`event CS2103T Lesson /at 2021-09-17`

Expected outcome:

Creates an Event with the description `CS2103T Lesson` on _17th September 2021_. The response will also include the 
total number of tasks currently stored in the list.

```
Got it. I've added this task:
  [E][ ] CS2103T Lesson (at: 17 Sep 2021)
Now you have 5 tasks in the list.
```

### `find` - Finds tasks

Find tasks with descriptions that matches the specified string.

Example of usage:

`find lesson`

Expected outcome:

Finds all tasks with descriptions that include the string `lesson`.

```
Here are the matching tasks in your list:
  1.[E][ ] CS2103T Lesson (at: 17 Sep 2021)
```

### `list` - List all tasks

List all tasks stored in the Duke application.

Example of usage:

`list`

Expected outcome:

List all tasks currently stored in the Duke application.

```
Here are the tasks in your list:
  1.[T][X] join sports club
  2.[T][ ] play hockey
  3.[T][ ] play football with friends
  4.[D][ ] complete IP tasks (by: 17 Sep 2021)
```

### `todo` - Create a Todo task

Creates a Todo task with the provided task description.

Example of usage:

`todo sleep earlier`

Expected outcome:

Creates a Todo with the description `sleep earlier`. The response will also include the
total number of tasks currently stored in the list.

```
Got it. I've added this task:
  [T][ ] sleep earlier
Now you have 5 tasks in the list.
```

---