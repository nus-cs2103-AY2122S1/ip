# User Guide

Welcome to **MemoCat**, an easy-to-use memo chatbot to track your daily tasks.

- author: Xu Jiheng (aka JeffZincatz)

## Features 

### Feature - Greeting

Our kind MemeCat would greet you with a welcome message when your first launch the app.

### Feature - Show all tasks

You can show a full list of your existing tasks with the `list` command.

### Feature - Add new task

You can add new tasks of different types with the `todo`, `deadline`, and `event` commands.

- Todo: `todo [task description]`
- Deadline: `deadline [task description] /by [YYYY-MM-DD]`
- Event: `event [task description] /at [YYYY-MM-DD]`

### Feature - Mark task as done

You can mark a task as done with the `done` command.

- Mark as done: `done [index]`

### Feature - Delete a task

You can delete a task from the `list` with the `delete` command.

- Delete a task: `delete [index]`

### Feature - Data save

All your tasks data will be automatically saved to `/data/memocat.txt`.

### Feature - Exit

You can exit the MemoCat chatbot by sending `bye`.

## Usage

*(The square brackets `[]` indicates user input.)*

### `list` - Show all tasks

The `list` command shows you a list of all existing tasks.

Example of usage: 

`list`

Sample outcome:

```
T | 1 | finish cs2100 tut
D | 1 | submit cs2103t ip | 2021-08-31
E | 0 | dinner with friends | 2021-09-01
D | 0 | ip week 5 | 2021-09-08
```

Description of the outcome:

- The first letter shows the tasks type.
    - `T`: Todo
    - `D`: Deadline
    - `E`: Event
- The second number indicates its complete status.
    - `1`: done
    - `0`: not done
- For `Deadline` and `Event` tasks, the third number indicates its time.
    - The time is in `YYYY-MM-DD` format e.g. `2021-09-01`.

### `todo` - Create a Todo tasks

The `todo` command add a new Todo task to the `list`.

Example of usage:

`todo watch lecture recording st2334`

Sample outcome:

```
Got it. I've added this task:
    [T][ ] watch lecture recording st2334
Now you have 5 tasks in the list.
```
Description of the outcome:

Our MemoCat has added the tasks to the list and saved to `/data/memocat.txt`.

### `deadline` - Create a Deadline tasks

The `deadline` command add a new Deadline task to the `list`.

Example of usage:

`deadline complete 2100 tutorial /by 2021-09-13`

Sample outcome:

```
Got it. I've added this task:
    [D][ ] deadline complete 2100 tutorial (by: 2021-09-13)
Now you have 6 tasks in the list.
```
Description of the outcome:

Our MemoCat has added the tasks to the list and saved to `/data/memocat.txt`.

### `Event` - Create an Event tasks

The `event` command add a new Event task to the `list`.

Example of usage:

`event cluster group activity /at 2021-09-16`

Sample outcome:

```
Got it. I've added this task:
    [E][ ] event cluster group activity (at: 2021-09-16)
Now you have 5 tasks in the list.
```
Description of the outcome:

Our MemoCat has added the tasks to the list and saved to `/data/memocat.txt`.

### `done` - Mark a task as done

The `done` command mark a task in the `list` as done by index.

Example of usage:

`done 5`

Sample outcome:

```
Nice! I've marked this task as done:
    [T][X] watch lecture recording st2334
```
Description of the outcome:

Our MemoCat has marked the task as done in the list and saved to `/data/memocat.txt`.

### `delete` - Delete a task

The `delete` command delete a task from the `list` by index.

Example of usage:

`delete 5`

Sample outcome:

```
Noted. I've removed this task:
    [T][X] watch lecture recording st2334
```
Description of the outcome:

Our MemoCat has removed the task from the list and saved to `/data/memocat.txt`.

### `bye` - Exit MemoCat chatbot

The `bye` command terminates the MemoCat chatbot.

Example of usage:

`bye`

Sample outcome:

```
Bye. See you next time!
```
Description of the outcome:

Our MemoCat has finished its work. The app will automatically close after 1 second.


## Final words

Hope you enjoyed the cute cat pictures :)

> "The ordinary days that we live in may, in fact, be a series of miracles." -
Koujirou Sasahara