# **Peppa User Guide** 😁

# PeppaTaskHandler

> "Your mind is for having ideas, not holding them.”
> - David Allen [source](https://dansilvestre.com/productivity-quotes/)

PeppaTaskHandler makes it much easier to plan and manage your tasks on a daily basis. It's

- easy to use
- easy to learn
- very ~~fast~~ responsive

To start using the application,

1. download java if you have yet to install!
2. download the application
3. open it via your terminal
4. tell peppa your tasks and notes
5. Enjoy as Peppa oinks away and managed all your tasks and notes for you 🤩

Most importantly, DukeTaskHandler is **free** 🤪

## *Features that Peppa has to offer you* 🐷

###1. Feature Add Todo
* Adds a **Todo** in your list of tasks

###2. Feature Add Deadline
* Adds a **Deadline** in your list of tasks

###3. Feature Add Event
* Adds an **Event** in your list of tasks

###4. Feature List
* **List** all the tasks that you have added to Peppa

###5. Feature Delete 
* **Deletes** a particular task for you if you no longer need it

###6. Feature Done
* Indicated a task as **done** if you have completed it 

###7. Feature Find a task by a keyword
* Helps you **find** a task by typing a keyword

###8. Feature Add a Note
* Allows you to **add a Note** to remind yourself of details

###9. Feature List all notes
* **Lists all the notes** that you have added to Peppa 

###10. Feature Exit application
* **Say bye** to *Peppa* before you close the application!

## Usage

### 🖊️`todo` 

*This action adds a Todo to the list of tasks and Peppa will respond with the todo that has been added and the total number of tasks in the list.*

Example of usage: 

`todo complete homework`

Expected outcome:

```
Got it. I've added this task:
    [T][] complete homework
Now you have 2 tasks in the list.
```
### 	⏰`deadline` 

*This action adds a Deadline to the list of tasks and Peppa will respond with the deadline that has been added and the total number of tasks in the list.*

Example of usage:

`deadline return book /by 02/12/2021 18:00`

Expected outcome:

```
Got it. I've added this task:
    [D][] return book(by: Dec 2 2021 18:00)
Now you have 3 tasks in the list.
```

### 📆`event` 

*This action adds an Event to the list of tasks and Peppa will respond with the event that has been added and the total number of tasks in the list.*

Example of usage:

`event meet friend /at 03/12/2021 18:00-19:00`

Expected outcome:

```
Got it. I've added this task:
    [E][] meet friend(at: Dec 3 2021 18:00 to 19:00)
Now you have 4 tasks in the list.
```

### 📄`list` 

*This action lists all the tasks in the order that you have told Peppa. It will also indicate the type of each task and whether the task has been done.*

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] sleep
2. [T][] complete homework
3. [D][X] return book(by: Dec 2 2021 18:00)
4. [E][] meet friend(at: Dec 3 2021 18:00 to 19:00) 

```

### 🗑️`delete` 

*This action requires a task number and deletes the task that corresponds to that number. 
Peppa will respond with the task that has been deleted and the total number of tasks left in the list.*

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
    [T][X] sleep
Now you have 3 tasks in the list.
```

### `done`

*This action requires a task number and indicates the task that corresponds to that number as done.
Peppa will respond with the task that has been indicated as done.*

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [X] complete homework
```
### 🔍`find`

*This action requires a keyword and Peppa will respond with the tasks that match with the keyword.
The aim is to allow users to find their tasks more easily especially when the list gets longer 😅*

Example of usage:

`find return`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][X] return book(by: Dec 2 2021 18:00)
```

### 📕`note`

*This action adds a note and Peppa responds with the note that you have added and the total number of notes.*

Example of usage:

`note password is 123`

Expected outcome:

```
Got it. I've added this note:
    password is 123
Now you have 2 tasks in the list.
```
### `List of Notes`

*This action asks Peppa to list all the notes stored in the order that they were added and states the total number of notes.*

Example of usage:

`note password is 123`

Expected outcome:

```
Got it. I've added this note:
    password is 123
Now you have 2 notes in the list.
```

### 👋`bye` - Describe action

*This action says bye to Peppa 😢👋*

Example of usage:

`Bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```