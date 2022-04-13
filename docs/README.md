# User Guide

## Introduction

<p>	Duke is a chatbot which functions as a to-do-list. It has fairly simple commands and can be used to keep track of your schedules!
We will keep uploading the chatbot with new additional features, so look forward to it!</p>

## Features 

### Feature-1 : To-do list
- The chatbot provides a list for the user to interact and create the to-do list with.
- The command line to view the list is 'list'.

### Feature-2 : Add, set status, and remove tasks from list
- The chatbot provides commands for users to add tasks, delete tasks, and set task status to finished from the list.
- There are 3 types of tasks you can add : <br>
<table>
<tr>
<td>
<b>
Task type
</b>
</td>
<td>
<b>
Use
</b>
</td>
</tr>
<tr>
<td>
To Do
</td>
<td>
A general task need to be finished with no time limit.
</td>
</tr>
<tr>
<td>
Deadline
</td>
<td>
A task that needs to be finished before a specific time.
</td>
</tr>
<tr>
<td>
Event
</td>
<td>
A task that happens between a certain time period.
</td>
</tr>
</table>

## Usage

### `bye` - Exit the chatbot

Used to exit the chatbot. The bot will send a goodbye message before exiting.

<b>Example of usage:</b>

`bye`

<b>Expected outcome: </b> 

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### `deadline` - add a new deadline task

Used to add one of the tasks with the type deadline to the list. You can see the deadline tag marked with [D] in the to-do list. <br>
The command takes in two parameters : a <b>string</b> which indicates the deadline task name, and a <b>string</b> which indicates the deadline, separated with a backslash <b>("/")</b>

<b>Example of usage:</b> 

``` 
Here are the tasks on your list:
1. [T][ ] borrow book
```

`deadline return book/8th August 2pm`

<b>Expected outcome: </b>

The list will add the deadline below the newest task, and the deadline task will have 2 as its index number in the list.
```
____________________________________________________________
Got it. I've added this task: 
[D][ ] return book (by: 8th August 2pm)
Now you have 2 task(s) in the list.
____________________________________________________________
```

### `delete` - remove task

Used to remove one of the tasks on the to-do list from the chatbot. You need to specify the index number of the tasks you want to remove (you can refer to it from the list!) <br>
The command takes in one parameter : an <b>integer </b> indicating the index number from the list of the task you want to remove from the list.

<b>Example of usage:</b>

``` 
Here are the tasks on your list:
1. [T][ ] borrow book
```

`delete 1`

<b>Expected outcome: </b>

It will remove the task from the list.

```
____________________________________________________________
Noted. I've removed this task:
[T][ ] borrow book
Now you have 0 task(s) on the list.
____________________________________________________________
```

### `done` - mark the task status as done

Used to mark one of the tasks on the to-do list as done from the chatbot. You need to specify the index number of the tasks you want to mark (you can refer to it from the list!) 
The command takes in one parameter : an <b>integer </b> indicating the index number from the list of the task you want to mark as done.

<b>Example of usage:</b> 

``` 
Here are the tasks on your list:
1. [T][ ] borrow book
```

`done 1`

<b>Expected outcome: </b>

It will mark the task in the list as done.

```
____________________________________________________________
Nice! I've marked this task as done: 
1. [T][X] borrow book
____________________________________________________________
```

### `event` - add a new event task

Used to add one of the tasks with the type event to the list. You can see the event tag marked with [E] in the to-do list. <br>
The command takes in two parameters : a <b>string</b> which indicates the event name, and a <b>string</b> which indicates the event timeline, separated with a backslash <b>("/")</b>

<b>Example of usage:</b>

``` 
Here are the tasks on your list:
1. [T][ ] borrow book
```

`event study group/8th August 2-4pm`

<b>Expected outcome: </b>

The list will add the event below the newest task, and the event will have 2 as its index number in the list.
```
____________________________________________________________
Got it. I've added this task: 
[E][ ] study group (at: 8th August 2-4pm)
Now you have 2 task(s) in the list.
____________________________________________________________
```

### `list` - view the to-do list

Used to view the to-do list.

<b>Example of usage:</b>

`todo borrow book`
```
Got it. I've added this task: 
[T][ ] borrow book
Now you have 1 task(s) in the list.
```

`list`

<b>Expected outcome: </b>

```
____________________________________________________________
Here are the tasks on your list:
1. [T][ ] borrow book
____________________________________________________________
```


### `todo` - add a new to-do task

Used to add one of the tasks with the type todo to the list. You can see the todo tag marked with [T] in the to-do list. <br>
The command takes in a parameter : a <b>string</b> which indicates the task name.

<b>Example of usage:</b>

``` 
Here are the tasks on your list:
1. [T][ ] borrow book
```

`todo borrow pen`

<b>Expected outcome: </b>

The list will add the todo task below the newest task, and the task will have 2 as its index number in the list.
```
____________________________________________________________
Got it. I've added this task: 
[T][ ] borrow pen
Now you have 2 task(s) in the list.
____________________________________________________________
```