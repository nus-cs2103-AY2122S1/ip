# 🐷 **Peppa User Guide**

# PeppaTaskHandler

### *Peppa's got your back* 🤪
> Oink oink!
> - It's easy to manage tasks with my help!
> - I am very **fast and responsive** - peppa

To start using the application,
1. download java if you have yet to install!
2. download Peppa from [PeppaTaskHandler](https://github.com/Neha-5678/ip/releases/tag/A-Release)
3. open your terminal and type java -jar peppa.jar
   4Enjoy as Peppa oinks away and manages all your tasks and notes for you 🤩

## *Features that Peppa has to offer you* 🐷
1. Feature Add Todo
2. Feature Add Deadline
3. Feature Add Event
4. Feature List
5. Feature Delete
6. Feature Done
7. Feature Find a task by a keyword
8. Feature Add a Note
9. Feature List all notes
10. Feature Delete a Note
## Usage

### 🖊️`todo`

*Adds a Todo*

Example of usage:

`todo complete homework`

Expected outcome:

```
Got it. I've added this task:
    [T][] complete homework
Now you have 2 tasks in the list.
```
### 	⏰`deadline`

*Adds a Deadline*

Example of usage:

`deadline return book /by 02/12/2021 18:00`

Expected outcome:

```
Got it. I've added this task:
    [D][] return book(by: Dec 2 2021 18:00)
Now you have 3 tasks in the list.
```

### 📆`event`

*Adds an Event*

Example of usage:

`event meet friend /at 03/12/2021 18:00-19:00`

Expected outcome:

```
Got it. I've added this task:
    [E][] meet friend(at: Dec 3 2021 18:00 to 19:00)
Now you have 4 tasks in the list.
```

### 📄`list`

*Lists all the tasks in the order that you have told Peppa.*

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

*Deletes a task.*

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
    [T][X] sleep
Now you have 3 tasks in the list.
```

### `done`

*Indicates a task as done.*

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [X] complete homework
```
### 🔍`find`

*Find tasks that match with a keyword.*

Example of usage:

`find return`

Expected outcome:

```
Here are the matching tasks in your list:
1. [D][X] return book(by: Dec 2 2021 18:00)
```

### 📕`note`

*Adds a Note.*

Example of usage:

`note password is 123`

Expected outcome:

```
Got it. I've added this note:
    password is 123
Now you have 2 tasks in the list.
```
### `n/list`

*List all the notes.*

Example of usage:

`note password is 123`

Expected outcome:

```
Got it. I've added this note:
    password is 123
Now you have 2 notes in the list.
```
### `n/delete`

*Deletes a note.*

Example of usage:

`n/delete 2`

Expected outcome:

```
Noted. I've removed this note:
    password is 123
Now you have 1 note in the list.
```

### 👋`bye` - Describe action

*This action says bye to Peppa 😢👋*

Example of usage:

`Bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```