# User Guide For Neko

## Features 

### Add/delete a task

### Mark a task as done

### List all tasks

### Search for a task
Using keyword to search or find a task on a particular date.

##Extension

### Add/Delete/Search/List a contact

## Usage

### `todo x` - add a todo task "x" to the task list
### `event x /at yyyy/mm/dd hh:mm` - add an event "x" with the given time to the task list
### `deadline x /by yyyy/mm/dd` - add a deadline "x" with the given date to the task list
### `list` - list all tasks in the current task list
### `delete x` - delete a task with the given index x from the task list
### `find x` - list tasks in the task list with the keyword x
### `on yyyy/mm/dd` - list events/deadlines with the given date
### `bye` - exit the chat bot
### `friend xx yyyyyyyy` - add a contact xx with contact number yyyyyyyy
### `deleteC xx` - delete a contact with the given name from the task list
### `searchC xx` - show the contact number for the contact of xx
### `list` - list all contacts in the current contact list

Example of usage: 

`todo assignment 1`

Expected outcome:

```
Got it. I've added this task:
[T][ ] assignment 1
Now you have 1 task in the list.
```

Example of usage:

`event team meeting /at 2021/09/13 09:00`

Expected outcome:

```
Got it. I've added this task:
[E][ ] team meeting:2021/09/13T09:00
Now you have 2 tasks in the list.
```

Example of usage:

`deadline submit essay /by 2021/09/13`

Expected outcome:

```
Got it. I've added this task:
[D][ ] submit essay:2021-09-13
Now you have 3 tasks in the list.
```

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][ ] assignment 1
Now you have 2 tasks in the list.
```

Example of usage:

`listC`

Expected outcome:

```
Here are the contacts in your list:
1.alice:12341234
2.allen:12345678
```

Enjoy chatting with Neko!