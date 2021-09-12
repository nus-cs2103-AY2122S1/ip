# User Guide

## Features 

### Feature - Adding Tasks to the To-Do-List

> ### 1) todo

Adds a task with no specific timing

**Format** - `todo {description of task}`

Example of usage: 

`todo Run a marathon`

Expected outcome:

```
-----------------------------------------------
Got it. I've added this task:
[T][] Run a marathon
Now you have 1 task in your list
-----------------------------------------------
```

> ### 2) deadline

Adds a task with a deadline

**Format** - `deadline {description of task} /by {dd/mm/yyyy hhmm}`

Example of usage: 

`deadline Assignment /by 13/09/2021 2359`

Expected outcome:

```
-----------------------------------------------
Got it. I've added this task:
[D][] Assignment (by: Sep 13 2021 11.59 PM)
Now you have 2 task in your list
-----------------------------------------------
```

> ### 3) event
   
Adds a task that starts at a specified time

**Format** - `event {description of task} /at {dd/mm/yyyy hhmm}`

Example of usage: 

`event Career Fair /at 15/09/2021 1400`

Expected outcome:

```
-----------------------------------------------
Got it. I've added this task:
[E][] Career Fair (at: Sep 15 2021 2.00 PM)
Now you have 3 task in your list
-----------------------------------------------
```

### Feature - Display To-Do-List

**Format** - `list`

Example of usage: 

`list`

Expected outcome:

```
Here is your list of tasks
-----------------------------------------------
1. [T][] Run a marathon
2. [D][] Assignment (by: Sep 13 2021 11.59 PM)
3. [E][] Career Fair (at: Sep 15 2021 2.00 PM)
-----------------------------------------------
```

### Feature - Mark tasks as done

**Format** - `done {index}`

Example of usage: 

`done 3`

Expected outcome:

```
-----------------------------------------------
Nice! I've marked this task as done:
[E][X] Career Fair (at: Sep 15 2021 2.00 PM)
-----------------------------------------------
```

### Feature - Delete tasks from To-Do-List

**Format** - `delete {index}`

Example of usage: 

`delete 3`

Expected outcome:

```
-----------------------------------------------
Nice. I've removed this task:
[E][X] Career Fair (at: Sep 15 2021 2.00 PM)
-----------------------------------------------
```

### Feature - Edit task

> ### 1) Edit description

**Format** - `edit {index} /desc {new description}`

Example of usage: 

`edit 1 /desc Run a triathlon`

Expected outcome:

```
-----------------------------------------------
Got it. I've edited this task to:
[T][] Run a triathlon
-----------------------------------------------
```

> ### 2) Edit time

**Format** - `edit {index} /time {dd/mm/yyyy hhmm}`

Example of usage: 

`edit 2 /time 12/09/2021 2359`

Expected outcome:

```
-----------------------------------------------
Got it. I've edited this task to:
[D][] Assignment (by: Sep 12 2021 11.59 PM)
-----------------------------------------------
```
