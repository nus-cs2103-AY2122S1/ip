# User Guide

## Features 

### Auto saving

Previous tasks are automatically loaded on start up, and saved on exit.

### Task recording

Add tasks, mark tasks as done, delete tasks, and list tasks.

### Task searching

Find tasks by names.

## Commands

### `help` - Display help message

Displays list of commands and respective names.

---

### `bye` - Exits Duk

Saves tasks to a file, then exits Duk.

---

### `list` - Display all tasks

Displays list of all tasks currently stored in Duk. Any input after list will be ignored.

---

### `done` - Mark task as done

Marks the task at a given index as done.

Example of usage: 

`done 1`

Expected outcome:

Task 1 is marked as done.

```
Nice! I've marked this task as done: 
[T][X] task
```

---

### `delete` - Delete task

Deletes the task at the given index.

Example of usage: 

`delete 1`

Expected outcome:

Task 1 is deleted.

```
Noted. I've removed this task
[T][X] task
Now you have 0 tasks in the list.
```

---

### `find` - Finds tasks

Finds tasks matching given search query.

Example of usage: 

`find some`

Expected outcome:

Finds tasks with "some" in the name.

```
ere are the matching tasks in your list:
[T][X] something
```

---

### `event` - Adds new Event

Adds a new event to the task list. Event must contain a non-empty description.

Example of usage: 

`event name /at 2019-01-02`

Expected outcome:

Adds a new event with date specified.

```
Got it. I've added this task:
[E][ ] name (at: Jan 2 2019)
```

---

### `deadline` - Adds new Deadline

Adds a new deadline to the task list. Deadline must contain a non-empty description.

Example of usage: 

`deadline name /by 2019-01-02`

Expected outcome:

Adds a new event with date specified.

```
Got it. I've added this task:
[D][ ] name (by: Jan 2 2019)
```

---

### `todo` - Adds new Todo

Adds a new todo to the task list. Todo must contain a non-empty description.

Example of usage: 

`todo name`

Expected outcome:

Adds a new todo.

```
Got it. I've added this task:
[T][ ] name
```