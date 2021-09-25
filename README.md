# Abyss

Abyss is a *personal, lightweight chatbot* that helps you manage your daily tasks.

<p align="center">
  <img height="500" src="https://github.com/ailing35/ip/blob/master/docs/Ui.png">
</p>

# User Guide

## Features 

### Manage Your Tasks

Abyss allows you to add, edit, delete tasks and mark your tasks as done.

It supports three different types of tasks.

#### Deadlines
Deadlines are tasks that need to be done before a specific date.

#### Events
Events are tasks that take place on a specific date.

#### Todos
Todos are tasks without a specific date of completion.

## Usage

### `list` - Lists all tasks

Lists all the existing tasks.

Example of usage: 

`list`

Expected outcome:

```
1. [T][] CS2103 Quiz
2. [D][] CS2106 Lab 3 (by: 10 Oct 2021)
3. [T][] Internship application
4. [E][] Important birthday (at: 27 Dec 2021)
```

### `deadline` - Adds a deadline

Adds a deadline task to the existing list of tasks.

Example of usage:

`deadline CS2106 Lab 3 /d 2021-10-10`

Expected outcome:

```
Task piece is added to the Abyss.
  [D][] CS2106 Lab 3 (by: 10 Oct 2021)
The Abyss now contains 2 task piece(s).
```

### `event` - Adds an event

Adds an event task to the existing list of tasks.

Example of usage:

`event Important birthday /d 2021-12-27`

Expected outcome:

```
Task piece is added to the Abyss.
  [E][] Important birthday (at: 27 Dec 2021)
The Abyss now contains 4 task piece(s).
```

### `todo` - Adds a todo

Adds a todo task to the existing list of tasks.

Example of usage:

`todo CS2103 Quiz`

Expected outcome:

```
Task piece is added to the Abyss.
  [T][] CS2103 Quiz
The Abyss now contains 1 task piece(s).
```

### `edit` - Edits a task

Edits either the description or date of a task.

Example of usage:

`edit 2 CS2106 Lab 4`

Expected outcome:

```
Task piece is edited.
  [D][] CS2106 Lab 4 (by: 10 Oct 2021)
The Abyss now contains 2 task piece(s).
```

Example of usage:

`edit 2 /d 2021-10-03`

Expected outcome:

```
Task piece is edited.
  [D][] CS2106 Lab 4 (by: 03 Oct 2021)
The Abyss now contains 2 task piece(s).
```

### `done` - Marks a task as done

Marks a task in the existing task list as done.

Example of usage:

`done 1`

Expected outcome:

```
1. [T][X] CS2103 Quiz
2. [D][] CS2106 Lab 3 (by: 10 Oct 2021)
3. [T][] Internship application
4. [E][] Important birthday (at: 27 Dec 2021)
```

### `delete` - Deletes a task

Deletes a task from the existing task list.

Example of usage:

`delete 1`

Expected outcome:

```
Task piece is swallowed by the Abyss.
  [T][X] CS2103 Quiz
The Abyss now contains 3 task piece(s).
```

### `find` - Finds tasks with keywords

Filters and shows tasks which contains given keywords in the description.

Example of usage:

`find cs`

Expected outcome:

```
1. [T][X] CS2103 Quiz
2. [D][] CS2106 Lab 3 (by: 10 Oct 2021)
```

### `exit` - Exits the program

Terminates the program gracefully.

Example of usage:

`exit`

Expected outcome:

```
Exiting the Abyss. We look forward to your return.
```

# Credits

The user interface theme is inspired by [Little Nightmares](https://en.bandainamcoent.eu/little-nightmares/little-nightmares) and the images used are taken from the sources below:

* [Abyss icon](https://igoutu.cn/icon/RKGFNVsfSVzt/little-nightmares)
* [Abyss profile image](https://www.subpng.com/png-pgi8cv/)
* [User profile image](https://toppng.com/six-sitsleep-small-little-nightmares-main-character-PNG-free-PNG-Images_256614)
* [Background image](https://wallpaperaccess.com/little-nightmare-4k)

