# Duke User Guide

## Features 

### Task Management

Access and plan your day-to-day tasks with Duke. 
You may add, delete, complete, and view your tasks with a simple command! 
Track your work with us today ^_^! 

### Search Your Tasks

Find tasks with specific keyword you are thinking of and Duke will 
show you all tasks containing that word or phrase :)

### Natural Dates

Duke accepts natural date formats such as 'mon', 'sun' and 'tmr' U_U.

## Usage

### `todo` - Adds a Todo Task
Adds task that does not require a date/time.

**Format:** `todo TASK_DESCRIPTION`

Example of usage: 

`todo make coffee`

Expected outcome:

Duke registers your todo task and lets you know about it.

```
Got it. I've added this task
  [T][ ] make coffee
Now you have 1 tasks in the list.
```

### `deadline` - Adds a Deadline Task

Adds task that needs to be done before a specific date. 
Time is an optional input here. Feel free to add one!

**Format:** `deadline TASK_DESCRPTION /by DATE [TIME]`

- Distinguish details and datetime with `/by`
- `TIME` is in 24h format (e.g. 0930 or 2359)
- `DATE` is in YYYY-MM-DD format (e.g. 2021-09-15)
- Natural dates (i.e. `mon`,`tues`,`tmr`) can be used as `DATE`

Example of usage:

`deadline brew tea /by 2021-09-15`

Expected outcome:

Duke registers your deadline task and lets you know about it.

```
Got it. I've added this task
  [D][ ] brew tea (at: Sep 15 2021)
Now you have 2 tasks in the list.
```
### `event` - Adds an Event Task

Adds task that starts at a specific time and ends at a specific time. Time is a mandatory input here.

**Format:** `event TASK_DESCRPTION /at DATE START_TIME-END_TIME`

- Distinguish details and datetime with `/at`
- `START_TIME` and `END_TIME` is in 24h format (e.g. 0930 or 2359)
- Separate `START_TIME` and `END_TIME` with `-`
- `DATE` is in YYYY-MM-DD format (e.g. 2021-09-15)
- Natural dates (i.e. `mon`,`tues`,`tmr`) can be used as `DATE`

Example of usage:

`event tea workshop /at 2021-09-20 0930-1300`

Expected outcome:

Duke registers your event task and lets you know about it.

```
Got it. I've added this task
  [E][ ] tea workshop (at: Sep 20 2021, 0930-1300)
Now you have 3 tasks in the list.
```

### `done` - Marks Task as Complete

Marks selected task as complete via a numerical reference.

**Format:** `done INDEX`

- task number reference, `INDEX`, can be obtained by running the `list` command
- numerical index starts from 1 not 0
- completed tasks are marked with an `X`


Example of usage:

`done 2`

Expected outcome:

Duke marks this task as complete with an `X`.

```
Nice! I've marked this task as done:
[D][X] brew tea (at: Sep 15 2021)
```

### `list` - Renders All Tasks at Hand

Displays all tasks stored in Duke with relevant markers and information.

**Format:** `list`

Expected outcome:

Duke lists out all the tasks you have (complete and incomplete).

```
Here are the tasks in your list:
1. [T][ ] make coffee
2. [D][X] brew tea (at: Sep 15 2021)
3. [E][ ] tea workshop (at: Sep 20 2021, 0930-1300)
```

### `find` - Filters Tasks by Keyword

Find tasks that includes the `KEYWORD` (word/phrase) given.

**Format:** `find KEYWORD`

Example of usage:

`find tea`

Expected outcome:

Tasks containing the keyword or phrase given.
```
Here are the matching tasks in your list:
[D][X] brew tea (at: Sep 15 2021)
[E][ ] tea workshop (at: Sep 20 2021, 0930-1300)
```

### `delete` - Deletes Task

Deletes task from Duke completely.

**Format:** `done INDEX`

- task number reference, `INDEX`, can be obtained by running the `list` command
- numerical index starts from 1 not 0

Example of usage:

`delete 2`

Expected outcome:

Duke deletes your task and lets you know about it.

```
Noted. I've removed this task:
  [D][X] brew tea (at: Sep 15 2021)
Now you have 2 tasks in the list.
```

### `bye` - Exits Duke

Ends chat and closes Duke window.

**Format:** `bye`

Expected outcome:

Duke greets farewell and closes the chatbot.

```
Bye! Hope to see you soon.
```
