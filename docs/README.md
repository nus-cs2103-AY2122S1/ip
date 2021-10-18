# User Guide
Iron Bot (also known as Your Friendly Neighbourhood Chat Bot) is a task organiser that helps you, the user, to keep track of your tasks. 

## Getting Started
1. Ensure that you have `Java 11` or above installed.
2. Download the `ironbot.jar` file from `Releases`.
3. Double-click on the `ironbot.jar` file.
4. Start using the application.

Alternatively, you can: 
1. Ensure that you have `Java 11` or above installed.
2. Download the `ironbot.jar` file from `Releases`.
3. Open up `command prompt`.
4. Change the working directory to the directory where the `ironbot.jar` file is located.
5. Run the program using `java -jar ironbot.jar`.
6. Start using the application!

## Features 
* Add Task (3 different categories: Todo, Deadline, Event)
* View Task
* Delete Task
* Complete Task
* Search Tasklist by Keyword
* Update Task (3 different modes for the 3 categories: title, status, date)


## Usage

### `todo` - Add a todo task to your tasklist

Adds a new todo task to your tasklist, with the specified title/description.

Format: `todo <title/description>` 

Example of usage: 

`todo CS2103T Assignment`

Expected outcome:

You will receive a message from Iron Bot telling you that your todo task has been added.

```
Roger. I've added this task:
[T][ ] CS2103T Assigment
1 task(s) are in your list now!
```

### `deadline` - Add a deadline task to your tasklist

Adds a new deadline task to your tasklist, with the specified title/description and due date.

Format: `deadline <title/description> /by <yyyy-mm-dd>` 

Example of usage: 

`deadline CS2103T iP /by 2021-09-17`

Expected outcome:

You will receive a message from Iron Bot telling you that your deadline task has been added.

```
Roger. I've added this task:
[D][ ] CS2103T iP (by: Sep 17 2021)
2 task(s) are in your list now!
```

### `event` - Add an event task to your tasklist

Adds a new event task to your tasklist, with the specified title/description and due date.

Format: `event <title/description> /at <yyyy-mm-dd>` 

Example of usage: 

`event start of recess week /at 2021-09-20`

Expected outcome:

You will receive a message from Iron Bot telling you that your event task has been added.

```
Roger. I've added this task:
[E][ ] start of recess week (at: Sep 20 2021)
3 task(s) are in your list now!
```

### `list` - Shows the all the tasks in the tasklist

Displays all the tasks in your tasklist.

Format: `list` 

Example of usage: 

`list`

Expected outcome:

You will receive a message from Iron Bot showing your current tasklist.

```
These are the task(s) you have:
1.[T][ ] CS2103T Assigment
2.[D][ ] CS2103T iP (by: Sep 17 2021)
3.[E][ ] start of recess week (at: Sep 20 2021) 
```

### `delete` - Delete a task from your tasklist

Deletes an existing task from your tasklist, with the specified task id.
The task id follows the numbering shown when you enter the `list` command.
A valid id is required for this action to be executed by Iron Bot.

Format: `delete <id>` 

Example of usage: 

`delete 1`

Expected outcome:

You will receive a message from Iron Bot telling you that your task has been deleted.

```
Roger. I've deleted this task:
[T][ ] CS2103T Assignment
2 task(s) are in your list now!
```

### `done` - Mark a task in your tasklist as completed

Marks an existing task in your tasklist as completed, with the specified task id.
The task id follows the numbering shown when you enter the `list` command.
A valid id is required for this action to be executed by Iron Bot.

Format: `done <id>` 

Example of usage: 

`done 1`

Expected outcome:

You will receive a message from Iron Bot telling you that your task has been marked as completed.

```
Well done! You have completed"
[D][X] CS2103T iP (by: Sep 17 2021)
```

### `find` - Find all the tasks that contains the specified keyword

Gives a list of tasks that contains the specified keyword.
A valid keyword is required for this action to be executed by Iron Bot. (i.e. keyword cannot be blank)

Format: `find <keyword>` 

Example of usage: 

`find CS2103T`

Expected outcome:

You will receive a message from Iron Bot showing the list of tasks containing the `CS2103T` keyword.

```
These are the task(s) you have:
1.[D][X] CS2103T iP (by: Sep 17 2021)
```

### `update` - Updates the task in your tasklist

Updates the task, as specified by the id, in the tasklist.
The task id follows the numbering shown when you enter the `list` command.
A valid id is required for this action to be executed by Iron Bot.

Format: `update <id> <tag> <info>`

There are 3 update options for you to choose from:
1) title `update <id> title <new title>`
2) date (only for deadline and event tasks) `update <id> date <yyyy-mm-dd>` 
3) completion status `update <id> status <true/false>`

Example of usage: 

`update 2 date 2021-09-16`

Expected outcome:

You will receive a message from Iron Bot showing the task that has been updated.

```
Roger! I have updated this task:
[D][ ] CS2103T iP (by: Sep 16 2021)
```

## Summary 
Feature | Command
------------ | -------------
Add Todo task | `todo <title/description>`
Add Deadline task | `deadline <title/description> /by <yyyy-mm-dd>`
Add Event task | `event <title/description> /at <yyyy-mm-dd>`
View tasklist | `list`
Delete task | `delete <id>`
Mark task as complete | `done <id>`
Find task by keyword | `find <keyword>`
Update task | `update <id> <tag> <info>`

## Acknowledgement
* Iron Bot Icon by Ega Maulana from Pixabay (https://pixabay.com/vectors/iron-man-chibi-cartoon-superhero-5783522/).
* User Icon by André Santana AndreMS from Pixabay (https://pixabay.com/vectors/captain-america-marvel-hero-6192855/).
