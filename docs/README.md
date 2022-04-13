<h1>Jarvis User Guide</h1>


Based on a project template for a greenfield Java project, previously named [_Duke_](https://www.oracle.com/java/duke.html).

The project aims to build a product named Jarvis, a Personal Assistant Chatterbot that helps a person to keep track of various things.


## Features

- [Adding a task `add`](#adding-a-task)
- [Listing all task `list`](#listing-all-task)
- [Deleting a task `delete`](#deleting-a-task)
- [Completing a task `done`](#completing-a-task)
- [Finding a task `find`](#finding-a-task)
- [Sorting timed task `sort`](#sorting-timed-task)
- [Exiting the program `exit`](#exiting-the-program)

### Adding a Task

Adds a new task into Jarvis.

Three different types of tasks which can be created; `todo`,`deadline` and `event`
- `todo` contains a description of task
- `deadline` and `event` contains a description and time (d/M/yyyy [HHmm]) of task.


### Listing all Task

Displays a lists all task and its details in Jarvis.

### Deleting a Task

Deletes the specified task from Jarvis.

### Completing a Task

Marks the specified task as completed from Jarvis.

### Finding a Task

Displays a lists filtered task and its details in Jarvis.

### Sorting timed Task

Displays a lists sorted task type and its details in Jarvis.

### Exiting the Program 

Ends Jarvis and exits the programs


## Usage

### `add` - Adds a Task

Format: `add TASK_TYPE DESCRIPTION [/by DEADLINE_TIME] [/at EVENT_TIME]`

Adds a task to the Jarvis's task list

Example of usage: 

`add deadline return book /by 15/10/2021 1800`

Expected outcome:

Jarvis reply's with successful message

```
Got it. I've added this task: [D][] return book (by: 2021 Oct 15  6:00 PM)
Now you have 3 task in the list.
```

### `list` - Lists all Task

Format: `list`

Lists out all task and their details stored in Jarvis

Example of usage:

`list`

Expected outcome:

Jarvis reply's list of task

```
Here are the tasks in your list:
1.[T][] read book
2.[D][] return book (by: 2021 Jun 6 4.00PM)
```

### `delete` - Deletes a Task

Format: `delete INDEX`

- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

Example of usage:

`delete 2`

Expected outcome:

Jarvis reply's with successful message

```
Got it. I've deleted this task:
[D][] return book (by: 2021 Jun 6 4.00 PM)
Now you have 2 task in the list
```

### `done` - Completes a Task

Format: `done INDEX`

- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …

Example of usage:

`done 2`

Expected outcome:

Jarvis reply's with successful message

```
Nice! I've marked this task as done:
[D][X] return book (by: 2021 Jun 6 4.00 PM)
```

### `find` - Finds list of Task

Format: `find WORD`

- Finds list of task that contains specified `WORD` in its description.
- The word refers to a letter, phrase, or word shown in the description of each task.
- The word cannot be empty.

Example of usage:

`find return`

Expected outcome:

Jarvis reply's with successful message

```
Here are the matching task in your list:
[D][] return book (by: 2021 Jun 6 4.00 PM)
```

### `sort` - Sorts Deadline / Event Task

Format: `sort TASK_TYPE`

- Sorts all `TASK_TYPE` task in ascending order.
- The task type must be `deadline` or `event` only.

Example of usage:

`sort deadline`

Expected outcome:

Jarvis reply's with successful message

```
Here are the sorted task in your list:
[D][] return book (by: 2021 Jun 6 4.00 PM)
[D][] play (by: 2022 Feb 2 11.59 PM)
```

### `bye` - Exits program

Format: `bye`

Example of usage:

`bye`

Expected outcome:

Jarvis reply's with successful message

```
Bye. Hope to see you again!
```
