# User Guide

## Features 

### Add tasks
Features that you can add
- todos
- deadlines
- events

### Tag task

Tag tasks that you have added with your favourite hashtags #

## Usage

### `todo` - adds a "todo" task.

Example:
`todo clean my room`\
Expected outcome:

```
I have added the task!
 [T][] clean my room
Now you have 1 tasks left!
```

### `deadline` - adds a "deadline" task.

Example:
`deadline clean my room /by 23-09-2021 2359`\
Date time has to be in the form DD-MM-YYYY HHHH\
Expected outcome:
```
I have added the task!
 [D][] clean my room (by: Sep 23 2021 23:59)
Now you have 2 tasks left!
```

### `event` - adds a "event" task.

Example:
`event clean my room /at 7th nov 10am-10pm`\
Expected outcome:
```
I have added the task!
 [E][] clean my room (at: 7th nov 10am-10pm)
Now you have 3 tasks left!
```
### `list` - lists out all your current tasks

Example:
`list`\
Expected outcome:
```
1. [T][] clean my room
2. [D][] clean my room (by: Sep 23 2021 23:59)
3. [E][] clean my room (at: 7th nov 10am-10pm)
```

### `done` - mark a task as done

Example:
`done 3`\
Expected outcome:
```
Nice! I have marked this task as done:
[E][X] clean my room (at: 7th nov 10am-10pm)
```

### `find` - finds all task that match with the search

Example:
`find clean`\
Expected outcome:
```
1. [T][] clean my room
2. [D][] clean my room (by: Sep 23 2021 23:59)
3. [E][X] clean my room (at: 7th nov 10am-10pm)
```

### `tag` - tags a task with your specified tag

Example:
`tag 3 fun`\
Expected outcome:
```
The tag #fun has been added to task
clean my room
```
So now when we do `list`
```
1. [T][] clean my room
2. [D][] clean my room (by: Sep 23 2021 23:59)
3. [E][X] clean my room #fun (at: 7th nov 10am-10pm)
```

### `delete` - delete a task

Example:
`delete 3`\
Expected outcome:
```
I have removed the task:
 [E][X] clean my room #fun (at: 7th nov 10am-10pm)
Now you have 2 tasks left!
```

### `bye` - save your tasks and exit Duke

Example:
`bye`\
Expected outcome:
```
Bye. Hope to see you again soon!
Your tasks have been saved!
```