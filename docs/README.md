# User Guide

This is the user guide for Frosty! The (slightly) Christmas themed task manager that is happy to assist you.

## Features 

### Adding tasks

The application allows you to add the following types of tasks:

* Todo 
* Deadline 
* Event

Deadline and Event tasks have an added functionality of allowing you to add a date.

### Saving tasks

You can input a save command into the application to save your list for future use!
If saved data is found, your previous session will be loaded into the application automatically.

### Other task related utilities:
The application also comes with the following list of helpful functions to aid you in managing your tasks:

1. Finding tasks related to a keyword
2. Deleting tasks from the list
3. Update the date/label of a task conveniently

## Usage

To close the application, simply click on the X at the top right-hand! Below, the 
various other functions supported are demonstrated; so the expected outcome reflects
what should happen if you sequentially key in all the commands under "example usage"
below.

### `todo` - Adds a Todo task to the list

Example of usage:

`todo check giftlist`

Expected outcome:

```
Got it. I've added this task:
  [T][] check giftlist
Now you have 1 tasks in the list.
```


### `deadline` - Adds a Deadline task to the list

Example of usage:

`deadline make gifts /by 2020-12-20`

Expected outcome:

```
Got it. I've added this task:
  [D][] make gifts (by: Dec 20 2020)
Now you have 2 tasks in the list.
```

### `event` - Adds a Event task to the list

Example of usage:

`event train raindeers to fly /at 2020-12-01`

Expected outcome:

```
Got it. I've added this task:
  [E][] train raindeers to fly (at: Dec 1 2020)
Now you have 3 tasks in the list.
```

### `list` - Displays the current list of tasks
Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
[T][] check giftlist
[D][] make gifts (by: Dec 20 2020)
[E][] train raindeers to fly (at: Dec 1 2020)

```


### `done` - Marks a task as done

Example of usage: 

`done 1`

Expected outcome:

The relevant task in the list will have its checkbox marked with an X

```
Nice! I've marked this task as done:
[T][X] check giftlist
```

### `save` - Saves tasklist for future sessions

Example of usage:

`save`

Expected outcome:

```
Your list has been saved!
```

### `find` - Searches for tasks related to given prompt

Example of usage:

`find gift`

Expected outcome:

```
Here are the matching tasks in your list:
[T][X] check giftlist
[D][] make gifts (by: Dec 20 2020)
```

### `delete` - Deletes a task from the list

Example of usage:

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
  [E][] train raindeers to fly (at: Dec 1 2020)
Now you have 2 tasks in the lsit.
```

### `update` - Update either the label or date of a task

Example of usage:

`update 1 create giftlist`

Expected outcome:

```
I've updated your selected task to: 
  [T][] create giftlist
```

Example of usage:

`update date 2 2021-12-10`

Expected outcome:

```
I've updated your selected task to:
  [D][] make gifts (by: Dec 20 2021)
```