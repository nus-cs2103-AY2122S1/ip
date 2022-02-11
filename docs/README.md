# User Guide

<img src="Ui.png" alt="UI Screenshot" width="144"/>

Duke is a task manager in the form of a chat bot.
It can keep track of all your to-dos, events, and deadlines.
Give Duke commands using just your keyboard and it will answer.

## Features 

### Keyboard-friendly chat bot GUI

Maximum efficiency with our Duke app. Simply type your commands and hit enter and see Duke's output in a friendly message-like interface.

### Add and manage your tasks

Use our Todo, Deadline and Event tasks to keep track of everything in your life:

- **Todo**: a task that has no deadline
- **Deadline**: a task that needs to be completed by a certain date.
- **Event**: a task that must happen between a specified time interval.

You can add, mark as complete or delete tasks whenever you feel like it.

### Recall your tasks

Use list and find to quickly list your tasks by urgency or to list tasks that match a particular query. 

## Usage

### Create Tasks

There are three types of tasks as listed in the [Add tasks section](#add-and-manage-your-tasks). The syntax for creating each type is as follows:

```
todo {description}
deadline {description} /by {date}
event {description} /at {startDate} - {endDate}
```

Currently Duke supports date inputs in the form `YYYY-MM-DD`. You can also optionally include time in the 24 hour format as: `YYYY-MM-DD HHmm`.

#### Example of usage:

```
todo get a book
deadline Visit com 3 when it is completed /by 2040-10-10
event Enjoy this duke bot /at 2021-08-01 - 2021-12-31
```

#### Expected outcome:

Each of the commands will result in its own line of output:

```
added: [T] [ ] get a book
added: [D] [ ] Visit com 3 when it is completed (by: 10 Oct 2040, 12:00 AM)
added: [E] [ ] Enjoy this duke bot (at: 01 Aug 2021, 12:00 AM - 31 Dec 2021, 12:00 AM)
```

### List tasks

Duke lets you easily view your tasks with the command `list`.

The listed tasks are automatically sorted by order of urgency (ascending chronologically). Todos, as they do not have any date are treated as having least urgency and will always be at the bottom of the list. Event tasks will go by their end date and deadlines naturally will use its date.

Tasks will present with a checkbox (marking complete or incomplete `[x]` `[ ]`), the type of task and the description, with dates if applicable.
For user convenience, the dates are displayed in a more natural format (e.g. 12 Jan 2021, 04:30 PM).

#### Example of usage:

```
list
```

#### Expected outcome:

With the inputs from the [Create Task section](#create-tasks):

```
[E] [ ] Enjoy this duke bot (at: 01 Aug 2021, 12:00 AM - 31 Dec 2021, 12:00 AM)
[D] [ ] Visit com 3 when it is completed (by: 10 Oct 2040, 12:00 AM)
[T] [ ] get a book
```


### Find Tasks

Duke will search through all your tasks and present you with the tasks that even partially match the search query you provide with `find {query}`

#### Example of usage:

```
find duke
```

#### Expected outcome:

With the inputs from the [Create Task section](#create-tasks):

```
[E] [ ] Enjoy this duke bot (at: 01 Aug 2021, 12:00 AM - 31 Dec 2021, 12:00 AM)
```

### Done Tasks

The user can mark any task as done with the command `done {task index}`. 
The task index can be identified by running the list command first.

#### Example of usage:

```
done 1
```

#### Expected outcome:

With the inputs from the [Create Task section](#create-tasks):

```
Great! I've marked this task as done:
  [T] [x] get a book
```

### Delete Tasks

The user can mark any task as done with the command `delete {task index}`. 
The task index can be identified by running the list command first.
Beware that there is no recover function or backup copy.

#### Example of usage:


```
delete 1
```

#### Expected outcome:

With the inputs from the [Create Task section](#create-tasks):

```
Noted. I have deleted the following
  [T] [x] get a book
You know have 2 tasks in the list
```

