# User Guide

Bot is a quick to use task manager that uses a chatbot style interface.

## Quick Start
1. Ensure that Java 11 is installed (https://www.oracle.com/sg/java/technologies/javase-jdk11-downloads.html)
2. Run the jar file in an empty folder

## Features 

### Task Tracking

Bot is able to track 3 types of tasks. Todos, Deadlines and Events.
* Todos: Task with only a description
* Event: Task with a description, and a freeform "at" field
* Deadline: Task with a description, and a "by" date

### Deadline Reminder 

Bot is able to remind you of the Deadlines that is approaching in a few days

### Saving and Loading
Bot automatically saves your tasks after any action which is loaded at the start of the program

## Usage

### `list`

Returns the list of all events.

Example of usage: 

`list`

Example Outcome:
1. [T][x] task1
2. [T][x] task2
3. [E][ ] event task3 (at: 2020)
4. [D][x] test1 (by: Sep 9 2021)

### `todo`

Add a new todo task.

Example of usage:

`todo taskName`

Example Outcome:
Got it. I've added this task:
[T][ ] taskName
Now you have 5 task in the list

### `event`

Add a new event task.

Example of usage:

`event taskName /at day1`

Example Outcome:
Got it. I've added this task:
[E][ ] event taskName (at: day1)
Now you have 6 task in the list

### `deadline`

Add a new deadline task.
Format: `deadline {taskName} /by {YYYY-MM-DD}`

Example of usage:

`deadline taskName /by 2021-12-03`

Example Outcome:
Got it. I've added this task:
[D][ ] taskName (by: Dec 3 2021)
Now you have 7 task in the list

### `deadline`

Add a new deadline task.
Format: `deadline {taskName} /by {YYYY-MM-DD}`

Example of usage:

`deadline taskName /by 2021-12-03`

Example Outcome:
Got it. I've added this task:
[D][ ] taskName (by: Dec 3 2021)
Now you have 7 task in the list

### `delete`

Deletes a task.
Format: `delete {index}`

Example of usage:

`delete 3`

Example Outcome:
Noted. I've removed this task
Now you have 6 task in the list 

### `done`

Marks a task as done.
Format: `done {index}`

Example of usage:

`done 3`

Example Outcome:
Nice! I've marked this task as done:
[E][x] event task3 (at: 2020)

### `remind`

List all deadlines due in x days.
Format: `remind {days}`

Example of usage:

`remind 30`

Example Outcome:
1.[D][ ] taskName (by: Dec 3 2021)

### `find`

Find a task which has a description matching to the given keyword.
Format: `remind {keyword}`

Example of usage:

`find name`

Example Outcome:
1.[D][ ] taskName (by: Dec 3 2021)