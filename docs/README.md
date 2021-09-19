# User Guide
Duke is a GUI based bot that can be used to create and manage 3 types of tasks namely - Deadlines, Events, and Todo tasks. Users can create, read, update and delete tasks as per their own requirements.

## Features 

### Easy to Use Keyboard based GUI :keyboard:

Duke features a simple UI showing your commands, and your bots. It's easy to use keyboard interface makes it very intuitive for new users to pick it up after just having one glance at the User Guide.

### Ability to store three different types of Tasks :pushpin:
- [x] ```Todo``` - Tasks which do not have a due date associated with it.
- [x] ```Deadlines``` - Tasks that have a due date associated with it.
- [x] ```Events``` - Tasks which happen over a specifice time interval.

### Accessible anywhere and everywhere :earth_asia:

If you are worried that you might not have any internet, fret not as our bot stores all the tasks offline on your device, and you can close the app and turn off the internet without any worries.

## Usage

### Adding new Tasks

#### 1. `todo {task description}` - Add a new todo task

This command allows you to add a new todo task to your list

Example of usage: 

`todo buy groceries`

Expected outcome:

```
Got it sir, I've added this task
[T][] buy groceries
Now you have 1 tasks in the list
```

#### 2. `deadline {task description} /by {due date in format yyyy-mm-dd}` - Add a new deadline task

This command allows you to add a new deadline task to your list

Example of usage: 

`deadline cs2100 assignment /by 2021-09-15`

Expected outcome:

```
Got it sir, I've added this task
[D][] cs2100 assignment (by: Sep 15 2021)
Now you have 1 tasks in the list
```


#### 3. `event {task description} /at {time of the event}` - Add a new event task

This command allows you to add a new event task to your list

Example of usage: 

`event cs career fair /at 8:30pm`

Expected outcome:

```
Got it sir, I've added this task
[E][] cs career fair (at: 8:30pm)
Now you have 1 tasks in the list
```

### Viewing Tasks

#### 1. `list` - Viewing all tasks

Duke lists all the tasks (both pending and completed) when this command is entered.

Example of usage: 

`list`

Expected outcomes:

a. If there are tasks
```
1. [T][] buy groceries
2. [D][] cs2100 assignment (by: Sep 15 2021)
3. [E][] cs career fair (at: 8:30pm)
```

b. If there are no tasks
```
There are no tasks for you sir
```

#### 2. `find {task description}` - Looking for a specific task(s)

Duke lists all the tasks that match the description when the command is entered.

Examples of usage: 

`find cs2100`
`find non existent task`

Expected outcomes:

a. If there are tasks
```
1. [D][] cs2100 assignment (by: Sep 15 2021)
```

b. If there are no tasks
```
No such tasks found
```

### Engaging with existing tasks
#### 1. `done {task number in list view}` - Mark an existing task as done

This command classifies the task as done and marks it with an X.

Example of usage: 

`done 1`

Expected outcome:

```
One task down sir. Here is the task I 
checked off
[T][X] buy groceries
```

#### 2. `delete {task number in list view}` - Deletes the task from memory

This command deletes the task from the computer memory and is NOT RETRIEVABLE. Use with caution.

Example of usage: 

`delete 1`

Expected outcome:

```
Got it sir, I have removed this task:
[T][X] buy groceries
Now you have 0 tasks in the list
```

#### 3. `update {new task in add task format} /no {task number in list to be updated}` - Updates an existing task

This command can be used to update/modify an existing task in the list view

Example of usage: 

`update todo 5 x 50 bicep curls /no 1`

Expected outcome:

```
Got it sir, I have updated this task:
update todo 5 x 50 bicep curls /no 1
Now you have 1 tasks in the list
```

