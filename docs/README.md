# User Guide

## How to use Skeltal

### Prerequisites
1. Ensure that your version of Java is 11 or higher
2. Download the latest version of Skeltal [here](https://github.com/xianlinc/ip/releases)

## What is Skeltal?

Skeltal is a chatbot that can help you manage your tasks and expenses!

##Features

### *Task Management*
### `event` 
 Adds an Event task to the list

### `deadline` 
Adds a Deadline task to the list

### `todo` 
Adds a ToDo task to the list

### `list` 
Lists all the tasks in the list

### `delete` 
Removes a task from the list

### `done` 
Marks a task in the list as done

### *Expense Management*

### `expense`
Adds an Expense to the expense list

### `minus`
Removes an Expense from the expense list

### `bank`
Lists all the expenses in the expense list

### `sum`
Displays the sum of all the expenses in the expense list

### *Miscellaneous*

### `updoot`
Displays a message that says you received calcium

<br>

## Usage

### `event` -  Adds an Event task to the list

Format:

`event <description> /at <date>`

Example of usage:

`event school /at 12122020`

Expected outcome:

Adds a Event task with the description "school" and the date "12 Dec 20" into the list.

```
Got it. I've added this task
[E][ ] 1. school (at: 12 Dec 20)
Now you have 1 tasks in the list.
```

<br>

### `deadline` - Adds a Deadline task to the list

Format:

`deadline <description> /by <date>`

Example of usage:

`deadline homework /by 12122020`

Expected outcome:

Adds a deadline task with the description "homework" and the date "12 Dec 20" into the list.

```
Got it. I've added this task
[D][ ] 2. homework (by: 12 Dec 20)
Now you have 2 tasks in the list.
```

<br>

### `todo` - Adds a ToDo task to the list

Format:

`todo <description>`

Example of usage:

`todo make study guide`

Expected outcome:

Adds a ToDo task with the description "make study guide" to the list.

```
Got it. I've added this task
[T][ ] 3. make study guide
Now you have 3 tasks in the list.
```

<br>

### `list` - Lists all the tasks in the list

Format:

`list`

Example of usage:

`list`

Expected outcome:

Displays a list of tasks in chronological order. With the oldest task being in the first position.

```
[E][ ] 1. school (at: 12 Dec 20)
[D][ ] 2. homework (by: 12 Dec 20)
[T][ ] 3. make study guide
```

<br>

### `delete` - Removes a task from the list

Format: 

`delete <index>`

Example of usage:

`delete 1`

Expected outcome:

Removes the task that is at the 1st position in the list.

```
Removed this task:
[E][ ] 1. school (at: 12 Dec 20)
Now you have 2 tasks in the list.
```

<br>

### `done` - Marks a task in the list as done

Format:

`done <index>`

Example of usage:

`done 1`

Expected outcome:

Marks the task in index 1 as completed

```
Done! I've marked this task as done!
[D][X] 1. homework (by: 12 Dec 20)
```

<br>

### `expense` - Adds an Expense to the expense list

Format:

`expense <description> $<amount>`

Example of usage:

`expense transport $120`
`expense transport $120`
`expense transport $120`

Expected outcome:

Adds 3 Expense objects with the description "transport" and the amount "120" to the expense list.

```
Got it. I've added this expense
[1] $120 -> transport
Now you have 1 expenses in the list.
Got it. I've added this expense
[2] $120 -> transport
Now you have 2 expenses in the list.
Got it. I've added this expense
[2] $120 -> transport
Now you have 3 expenses in the list.
```

<br>

### `minus` - Removes an Expense from the expense list

Format:

`minus <index>`

Example of usage:

`minus 1`

Expected outcome:

Removes the first expense from the list.

```
Removed this expense from the list:
[1] $120 -> transport
Now you have 2 expenses in the list.
```

<br>

### `bank` - Lists all the expenses in the expense list.

Format:

`bank`

Example of usage:

`bank`

Expected outcome:

Displays all the expenses in the list.

```
Here are the expenses in your list:
[1] $120 -> transport
[2] $120 -> transport
```

<br>

### `sum` - Displays the sum of all the expenses in the expense list

Format:

`sum`

Example of usage:

`sum`

Expected outcome:

Displays the sum of all the expenses in the expense list.

```
Your total spending is: $240
```

<br>

### `updoot` - Gives an updoot to Skeltal for good calcium

Format:

`updoot`

Example of usage:

`updoot`

Expected outcome:

Gets calcium from Skeltal.

```
You have been given some Calcium for your bones
Thank Mr. Skeltal.
```
