# User Guide for Project Xiri

Project Xiri is a **Command Line Interface (CLI) 
Java Desktop Application** dedicated to managing 
your todo tasks, your deadlines and events.

## Features

### Add new task

Add a new task, which can be a todo task, a deadline, 
or an event.

### Delete task

Delete a task from the list.

### List all tasks

Display all the tasks with their relevant 
information.

### Mark task as done

Mark a task as done. A done task will have [X] if 
it is marked as done.

### Find tasks by keyword

Search for all tasks with matching description
with some given keywords.

### Help

See all available commands and the usage of a 
particular command.

## Usage

### `help` - List all commands

> Display all available commands

Example of usage:
```text
help
```

Expected outcome:
List of all available commands is displayed.

Description of outcome:
```text
Available commands: deadline, delete, done, event, bye, find, list, todo
Type `help [command]` to see more about [command]
```

### `help` - View command usage

> Display the usage of a command.

Example of usage:
```
help deadline
```

Expected outcome:
Usage of the command `deadline` is displayed

Description of outcome:
```text
deadline [description] /by [yyyy-MM-dd] [HHmm], e.g. deadline homework /by 2021-09-04 2359
- Add a deadline [description] to be done before [yyyy-MM-dd] [HHmm]
```

### `todo` - Add a todo task

> Add a new todo task to the list

Example of usage:
```text
todo eat mcdonald
```

Expected outcome:
Todo task is added to the list

Description of outcome:
```text
Got it. I have added this task:
[T][ ] eat mcdonald
Now you have 1 task in your list.
```

### `deadline` - Add a deadline

> Add a new deadline with a due time to the list

Example of usage:
```text
deadline cs2106 lab im dying /by 2021-09-22 1400
```

Expected outcome:
Deadline is added to the list

Description of outcome:
```text
Got it. I have added this task:
[D][ ] cs2106 lab im dying (by: Sep 22, 2021 14:00)
Now you have 2 tasks in your list.
```

### `event` - Add an event

> Add an event with occurring time to the list

Example of usage:
```text
event cs2103t meeting /at 2021-09-14 2200
```

Expected outcome:
Event is added to the list

Description of outcome:
```text
Got it. I have added this task:
[E][ ] cs2103t meeting (at: Sep 14, 2021 22:00)
Now you have 3 tasks in your list.
```

### `done` - Mark as done

> Mark a task in the list as done by its index number

Example of usage:
```text
done 1
```

Expected outcome:
The task with the given index is marked as done

Description of outcome:
```text
Nice, I have marked this task as done:
[T][X] eat mcdonald
```

### `delete` - Delete a task

> Delete a task from the list by its index number

Example of usage:
```text
delete 3
```

Expected outcome:
Task with the given index is deleted

Description of outcome:
```text
Okay, I have removed this task:
[E][ ] cs2103t meeting (at: Sep 14, 2021 22:00)
Now you have 2 tasks in your list.
```

### `find` - Find tasks with keyword

> Find tasks that match the given keyword

Example of usage:
```text
find eat
```

Expected outcome:
Task with matching description is displayed

Description of outcome:
```text
Here are the matching tasks in your list:
1. [T][X] eat mcdonald
```

### `bye` - Exit the program

> Exit the program with a goodbye message

Example of usage:
```text
bye
```

Expected outcome:
Goodbye message is displayed, 
and the program exits in a few seconds

Description of outcome:
```text
Got it. I have added this task:
[E][ ] cs2103t meeting (at: Sep 14, 2021 22:00)
Now you have 3 tasks in your list.
```
