# A quick guide on how to use Duke!
Duke is a basic To-Do list chatbot that helps you keep track of your task

## Usage

### `bye` - bids farewell to Duke :(

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```

### `deadline` - adds a deadline task to Duke

Example of usage: 

`deadline <name of task> /by <YYYY-MM-DD>`

Expected outcome:

```
Got it. I've added this task:
[D][] <name of task> (by: MMM-DD-YYYY)
Now you have <number of tasks in list> tasks in the list
```

### `delete` - removes a task from Duke

Example of usage: 

`delete <valid task number>`

Expected outcome:

```
Noted. I've removed this task:
<details of task>
Now you have <number of tasks in list> tasks in the list
```

### `done` - marks a task as done

Example of usage: 

`done <valid task number>`

Expected outcome:

```
Nice! I've marked this task as done:
[][X] <details of task>
```

### `event` - adds an event task to Duke

Example of usage: 

`event <name of event> /at <event date/time>`

Expected outcome:

```
Got it. I've added this task:
[E][] <name of event> (at: <event date/time>)
Now you have <number of tasks in list> tasks in the list
```

### `find` - Describe action

Example of usage: 

`find <keyword>`

Expected outcome:

```
Here are the matching tasks in your list:
1. <task that contains keyword>
2. <task that contains keyword>
.
.
.
```
### `list` - shows the current tasks Duke has

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. <task>
2. <task>
.
.
.
```
### `todo` - adds a todo task to Duke

Example of usage: 

`todo <task name>`

Expected outcome:

```
Got it. I've added this task:
[T][] <task name>
Now you have <number of tasks in the list> tasks in the list
```
