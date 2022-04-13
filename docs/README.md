# User Guide
Duke is a cross-platform desktop app for managing your tasks (todos, deadlines, events) via a Command Line Interface (CLI),
while still having the comfort of a Graphical User Interface (GUI).\
\
**Latest release** <ins> [here](https://github.com/cyyeu/ip/releases/tag/A-Release) </ins>\
***Run using `java -jar duke.jar`***



## Features
1. [Adding a task](#adding-a-task):
    1. [Adding a todo: `todo`](#adding-a-todo-todo)
    1. [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
    1. [Adding an event: `event`](#adding-an-event-event)
1. [Listing all tasks: `list`](#listing-all-tasks-list)
1. [Updating a task: `update`](#updating-a-task-update)
    1. [Updating a todo](#updating-a-todo)
    1. [Updating a deadline](#updating-a-deadline)
    1. [Updating an event](#updating-an-event)
1. [Deleting a task: `delete`](#deleting-a-task-delete)
1. [Finding a task by description: `find`](#finding-a-task-by-description-find)
1. [Marking a task as done: `done`](#marking-a-task-as-done-done)
1. [Saving your tasks](#saving-your-tasks)
1. [Exiting Duke: `bye`](#exiting-duke-bye)

## Using the CLI

### Adding a task
Adds a task to the list of tasks.
A task can be a todo, deadline, or an event.
* #### Adding a todo: `todo`
  Format: `todo <description>`\
  Example: `todo buy milk`\
  Expected output:
  ```
  Got it. I've added this task:
      [T][] buy milk
  You now have 1 task in the list.
  ```
* #### Adding a deadline: `deadline`
  Format: `deadline <description> /by <date>`
  * Please use the date format as such:`YYYY-MM-DD`

  Example: `deadline ip /by 2021-09-17`\
  Expected output:
  ```
  Got it. I've added this task:
      [D][] ip (by: Sep 17 2021)
  You now have 2 tasks in the list.
  ```
* #### Adding an event: `event`
  Format: `event <description> /at <time period>`\
  Example: `event team meeting /at monday 7-8pm`\
  Expected output:
  ```
  Got it. I've added this task:
      [E][] team meeting (at: monday 7-8pm)
  You now have 3 tasks in the list.
  ```
---

### Listing all tasks: `list`
Lists all your tasks.\
Example: `list`\
Expected output: <br />
```
Here are the tasks in your list:
    1. [T][] buy milk
    2. [D][] ip (by: Sep 17 2021)
    3. [E][] team meeting (at: monday 7-8pm)
```

---
### Updating a task: `update`
Updates a task according to the task id in the list.
* #### Updating a todo:
  Format: `update <id> <updated description>`\
  Example: `update 1 buy egg`\
  Expected output:
   ```
  Your task has been updated from:
    [T][] buy milk
  to:
    [T][] buy egg
   ```
* #### Updating a deadline:
  Format: `update <id> <updated description> /by <updated date>`
  * Please use the date format as such:`YYYY-MM-DD`
  * You can also update only the description or only the date, by omitting the field you do not want to change.

  Example: `update 2 individual project` (updating only the description)\
  Expected output:
  ```
  Your task has been updated from:
      [D][] ip (by: Sep 17 2021)
  to:
      [D][] individual project (by: Sep 17 2021)
  ```
  Example: `update 2 /by 2021-09-18` (updating only the date) \
  Expected output:
  ```
  Your task has been updated from:
      [D][] individual project (by: Sep 17 2021)
  to:
      [D][] individual project (by: Sep 18 2021)
  ```
  Example: `update 2 indiv proj /by 2019-09-22` (updating description and date at the same time) \
  Expected output:
  ```
  Your task has been updated from:
      [D][] individual project (by: Sep 18 2021)
  to:
      [D][] indiv proj (by: Sep 22 2021)
  ```
* #### Updating an event:
  Format: `update <id> <updated description> /at <updated time period>`
  * You can also update only the description or only the time period, by omitting the field you do not want to change.

  Example: `update 3 meeting` (updating only the description)\
  Expected output:
  ```
  Your task has been updated from:
      [E][] team meeting (at: monday 7-8pm)
  to:
      [E][] meeting (at: monday 7-8pm)
  ```
  Example: `update 3 /at monday 5-6pm` (updating only the time period) \
  Expected output:
  ```
  Your task has been updated from:
      [E][] meeting (at: monday 7-8pm)
  to:
      [E][] meeting (at: monday 5-6pm)
  ```
  Example: `update 3 team cohesion /at tuesday 9-10pm` (updating description and time period at the same time) \
  Expected output:
  ```
  Your task has been updated from:
      [E][] meeting (at: monday 5-6pm)
  to:
      [E][] team cohesion (at: tuesday 9-10pm)
  ```

---
### Deleting a task: `delete`
Deletes a task according to the task id on the list.\
Format: `delete <id>`\
Example: `delete 1`\
Expected output:
```
Noted. I've removed this task:
    1. [T][] buy egg
You now have 2 tasks in the list.
```
---
### Finding a task by description: `find`
Finds a task whose description partially matches the query string.\
Format: `find <query>`
* The query string is case-sensitive.

Example: `find team`\
Expected output:
```
Here are the matching tasks in your list:
    1. [E][] team cohesion (at: tuesday 9-10pm)
```
---
### Marking a task as done: `done`
Marks a task as done according to the task id on the list.\
Format: `dome <id>`\
Example: `done 1`\
Expected output:
```
Nice! I've marked this task a done:
    [D][X] indiv proj (by: Sep 22 2021)
```
---
### Saving your tasks
Duke saves your tasks automatically to the disk after every command.\
This data is stored at `./data/duke.txt`, relative to the location of your Duke.jar file.

---
### Exiting Duke: `bye`
Exits the duke app.
