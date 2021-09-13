# User Guide

## Features 

### Interactive User Command Line Input
- Receive user command and respond.
- See examples of valid input when input is invalid.
- Exit the app and update local storage using command.

### Basic Task Management

- Create, delete three types of tasks: todo, event and deadline.
- Mark a task as done.
- View the list of tasks.

### Advanced Task Management

- Find a matching task using descriptions.
- Filter and sort different types of tasks.

### Storage
- Store the current list of tasks locally.
- Load local storage of tasks.


## Usage

### `list` - List tasks
List all current tasks.

Format and example of usage: `list`

Expected outcome:

```
Here are Ihe lasks in your list:
  1. [T][✓] eat 
  2. [E][✗] code (at 02 Sep 2021 05:00 PM)
  3. [D][✗] sleep (by: 01 Aug 2021 06:00 AM)
```

### `deadline` - Add deadline
Add a deadline with description and due date.

Format: `deadline <description> /by <yyyy-MM-dd HHmm>`

Example of usage: `deadline study /by 2024-05-01 2359`

Expected outcome:

```
Got it meow! I've added this task:
    [D][✗] study (by: 01 May 2024 11:59 PM)
Now you have 5 tasks in the list.
```

### `event` - Add event
Add an event with description and happening date.

Format: `event <description> /at <yyyy-MM-dd HHmm>`

Example of usage:
 `event study /at 2024-05-01 2359`

Expected outcome:

```
Got it meow! I've added this task:
    [E][✗] study (at: 01 May 2024 11:59 PM)
Now you have 5 tasks in the list.
```

### `todo` - Add todo
Add a todo with description.

Format: `todo <description>`

Example of usage: `todo sleep`

Expected outcome:

```
Got it meow! I've added this task:
    [T][✗] sleep
Now you have 5 tasks in the list.
```

### `delete` - Delete a task
Delete a task from the list.

Format: `delete <index>`

Example of usage:
 `delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][✓] eat 
Now you have 4 tasks in the list.
```

### `done` - Mark a task as done
Delete a task from the list.

Format: `done <index>`

Example of usage:
 `done 3`

Expected outcome:

```
Noted. I've marked this task as done:
    [D][✓] sleep (by: 01 Aug 2021 06:00 AM)
```

### `sort` - Sort a type of tasks

Sort a specific type of tasks in the list. If type is `deadline` or `event`, it will sort chronologically with the latest first.
If type is `todo`, it will sort lexicographically.

Format: `sort <task_type>`

Example of usage:
`sort deadline`

Expected outcome:

```
Here are the sorted deadline in your list:
    1. [D][x] graduation (by: 01 May 2020 11:59 PM) 
    2. [D][x] sleep (by: 01 Oct 2021 06:00 AM) 
    3. [D][✓] hello (by: 31 Aug 2021 06:00 AM) 
```

### `find` - Find a task

Find tasks that match with the given description.

Format: `find <keyword>`

Example of usage: `find life`

Expected outcome:

```
Here are the sorted deadline in your list:
    1. [T][x] no life
    2. [E][x] have fun in life (by: 01 Oct 2023 06:00 AM) 
    3. [D][✓] lifestyle (by: 31 Aug 2021 06:00 AM) 
```

### `bye` - Exit the app

Exit the app. The list data will automatically be saved.

Format and example of usage: `exit`

Expected outcome:
```
Bye meow! I will always wait here meow(>^^<)
```
