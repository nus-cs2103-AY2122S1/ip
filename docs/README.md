# User Guide
This user guide will help you understand what Shin can offers as well as how you could partner up with Shin.

## Features 

### To Do List

Shin helps you keep track of all the Tasks you have and organise them into a list sorted according to the order win which they are added.

### Different Type of Tasks

Shin can remember up to 3 different type of Tasks at this point. Any Tasks other than Events, Todos or Deadlines are merely gibberish to Shin.

### Operate on Tasks

Done with a Task? Let Shin know and he'll mark it as completed! Intentions to delete? Just let Shin know as well! You want to search through your To Do List? Say the word and Shin will deliver the list containing matching results to you.

## Usage

### `bye` - Bids farewell to Shin

Shin closes and saves the current state of your To Do List by overwriting the previously saved state.

***Example of usage:***

`bye`

***Expected outcome:***

Shin sends you a farewell message.

```
Wow! I can get off work now :D
Saved your work by the way!
```
### `deadline <name> /by <dueDate in YYYY:MM:DD HH:mm>` - Adds a Deadline

Shin adds the Deadline into your To Do List and initializes it as incompleted.

***Example of usage:***

`deadline return books /by 2021-09-28 12:00`

***Expected outcome:***

Shin tells you that he has successfully added this Deadline into your ToDoList

```
Aye Aye Capt'. I've added this Deadline:
    [D][ ] return books (by: Sep 28 2021 12:00)
Now you have 1 Task in the list.
Wew...
```

### `delete <index>` - Gets Shin to delete Task indexed at `index`.

Shin goes to your To Do List and deletes the Tasks indexed at <index>. He re-indexes the Tasks to 

***Example of usage:***

`delete 2`

***Expected outcome:***

Shin sends a confirmation message that the item 2 has been deleted.

```
Got it sir. I've removed this task:
    [T][ ] swim
Now you have 5 Tasks in the list.
```

### `done <index>` - Tells Shin that you have completed the Task indexed at `index`.

Shin whips out his notebook and mark the Task indexed at `index` as completed.

***Example of usage:***

`done 1`

***Expected outcome:***

Shin tells you that it is about the time you completed the Task 1.

```
Good! Good! Took you long enought to complete this:
    [E][X] Conference (at: Thursday 4pm)
```

### `event <name> /at <duration>` - Adds an Event

Shin adds the Deadline into your To Do List and initializes it as incompleted.

***Example of usage:***

`event concert /at Monday 7pm-12am`

***Expected outcome:***

Shin tells you that he has successfully added this Deadline into your ToDoList

```
Aye Aye Capt'. I've added this Event:
    [E][ ] concert (at: Monday 7pm-12am)
Now you have 6 Tasks in the list.
Wew...
```

### `Find <input>` - Lets Shin know that you are looking for Tasks that matches `input` (partially/fully)

Shin rushes off to look for Tasks that matches (partially/fully) to the `input`. He compiles his findings diligently into a list and presents it to you.

***Example of usage:***

`find er`

***Expected outcome:***

Shin comes back with a handful of results containing `er`.

```
Here's what I've found:
1. [E][X] Conference (at: Thursday 4pm)
2. [E][X] Concert (at: Monday 7pm-12am)
3. [D][ ] Submission (by: Oct 12 2021 16:00)
4. [E][ ] Dinner (at: Friday 7pm-9pm)
```

### `list` - Bids farewell to Shin

Shin opens his notebook and recites the Tasks from your To Do List.

***Example of usage:***

`list`

***Expected outcome:***

Shin reads the entire of your To Do List.

```
Here are the Tasks on your ToDoList:
1. [E][X] Conference (at: Thursday 4pm)
2. [T][ ] Swim
3. [E][X] Concert (at: Monday 7pm-12am)
4. [D][ ] Submission (by: Oct 12 2021 16:00)
5. [D][ ] Return Books (by: Sep 23 2021)
```

### `todo <name>` - Adds a ToDo 

Shin adds the ToDo into your To Do List and initializes it as incompleted.

***Example of usage:***

`todo run`

***Expected outcome:***

Shin sends you a farewell message.

```
Aye Aye Capt'. I've added this Event:
    [T][ ] run
Now you have 7 Tasks in the list.
Wew...
```
