# User Guide for Du
> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))

## Features 

### Add Tasks

You can add Todos, Deadlines and Events for the bot to manage it for you!
### Add Recurring Tasks

Deadlines and Events can be recurring as well:D

### Store Data

Stores the tasks you have added and lists it once you open it again :smile:

### Search 

Helps you find tasks easily through the name!
## Usage

### `todo` - Adds a todo task

Example of usage:

`todo return book`

Expected outcome: Adds the todo task to the whole list of task.

Description of the outcome.

```
Got it, I have added this task: 
[T][ ] return book
```

### `deadline` - Adds a deadline task

Example of usage:

`deadline return book /by 2021-08-08 16:30`

Expected outcome: Adds the deadline task with the date attached to it.

Description of the outcome.

```
Got it, I have added this task: 
[D][ ] return book (by: August 08 2021, 1630)
```

### `event` - Adds a event task

Example of usage:

`event project meeting /at 2021-08-08 16:30`

Expected outcome: Adds the event task with the date attached to it.

Description of the outcome.

```
Got it, I have added this task: 
[E][ ] project meeting (at: August 08 2021, 1630)
```

### `recurring` - Adds a recurring task

Example of usage:

`recurring weekly, 3, deadline do assignment /by 2021-08-08 16:30`

Expected outcome: Adds the recurring event task with the date attached to it with the specified frequency and number of times.

Description of the outcome.

```
Got it, I have added this task: 
[D][ ] do assignment (by: August 08 2021, 1630)
weekly for 3 times
```
### `done` - Mark a task as done

Example of usage:

`done 2`

Expected outcome: Marks the task with the corresponding number as done.

Description of the outcome.

```
Nice! I've marked this task as done: 
[D][X] return book (by: August 08 2021, 1630)
```

### `find` - finds tasks with the same keyword

Example of usage:

`find book`

Expected outcome: find tasks with names that contains book.

Description of the outcome.

```
Here are the matching tasks in your list:
[D][X] return book (by: August 08 2021, 1630)
```

### `list` - Lists the tasks you have currently

Lists all the tasks you have done or not done.
Example of usage:

`list`

Description of the outcome.

```
Here are the tasks in your list:
[T][X] buy bread
```

