# Duke

![Screenshot of Duke](Ui.png)

A command-based todo list made using JavaFX.  
You can add tasks, deadlines, events, and even timed tasks.

## Features 

- A nice GUI
- Add Tasks (Deadlines, Events, Todos, and Timed Todos)
- Delete Tasks
- View Tasks
- Complete Tasks
- Find Tasks
- List all your Tasks
- 

### `todo` - Adds a Todo

Adds a task to the todolist  
`todo eat breakfast`  

Output:
```
Got it. I've added this task:
[T][ ] eat breakfast
Now you have x tasks in the list.
```
### `todo ... /for` (Overloaded) - Adds a timed todo

Adds a timed todo to the todolist  
`todo eat breakfast /for 30 mins`

Output:
```
Got it. I've added this task:
[F][ ] eat breakfast (for: 30 mins)
Now you have x tasks in the list.
```
### `deadline` - Adds a deadline with an end date

Adds a deadline to the todolist  
`event go to 2103 lecture /at 2021-09-17 1600`

Output:
```
Got it. I've added this task:
[E][ ] go to 2103 lecture (at: Sep 17 2021 04.00 PM)
Now you have x tasks in the list.
```
### `delete <number>` - Deletes a Task

Deletes a task from the todolist according to its index, starting from 1   
`delete 1`

Output:
```
Noted. I've removed this task:
[T][ ] eat breakfast
Now you have x tasks in the list.
```

### `done <number>` - Completes a Task

Completes a task from the todolist according to its index, starting from 1  
`done 1`

Output:
```
Nice! I've marked this task as done:
[T][ ] eat breakfast
Now you have x tasks in the list.
```

### `find <text>` - Finds a Task

Finds a task that matches the given string 
`find hi`

Output:
```
Nice! I've marked this task as done:
[T][ ] eat breakfast
Now you have x tasks in the list.
```

### `find <text>` - Finds a Task

Finds a task that matches the given string 
`find breakfast`

Output:
```
Here are the matching tasks in your list:
[T][ ] eat breakfast
```


### `list` - Lists all available Tasks

Lists all available Tasks in the current TaskList.
`list`

Output:
```
Here are the tasks in your list:
1. [T][ ] eat breakfast
2. [T][ ] eat lunch
```
