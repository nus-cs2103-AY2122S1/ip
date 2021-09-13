# User Guide

Use duke to help you manage your todos, deadlines and events!

If you are on Mac, please run duke from the terminal with `java -jar duke.jar`

## Features

### Autosave

Your data is automatically saved to disk.

## Usage

### `todo` or `t` - Add todo

Adds a todo item with a *description*

```
todo borrow book
	____________________________________
	 Got it. I've added this task:
	   [T][ ] borrow book
	 Now you have 2 tasks in the list.
	____________________________________
```

### `deadline` or `d` - Add deadline

Adds a deadline with a *description* and *datetime* in `d/M/yyyy HHmm` format

```
deadline return book /by 20/8/2021 1500
	____________________________________
	 Got it. I've added this task:
	   [D][ ] return book (by: Fri, 20 Aug 2021 03:00PM)
	 Now you have 3 tasks in the list.
	____________________________________
```

### `event` or `e` - Add event

Adds an event with a *description* and *datetime* in `d/M/yyyy HHmm` format

```
event project meeting /at 22/8/2021 0800
	____________________________________
	 Got it. I've added this task:
	   [E][ ] project meeting (at: Sun, 22 Aug 2021 08:00AM)
	 Now you have 5 tasks in the list.
	____________________________________
```

### `list` or `l` - List all tasks

Lists all todos, deadlines and events.

```
list
	____________________________________
	 Here are the tasks in your list:
	 1.[T][X] borrow book
	 2.[E][X] project meeting (at: Sun, 22 Aug 2021 08:00AM)
	 3.[D][ ] return book (by: Fri, 20 Aug 2021 03:00PM)
	____________________________________
```

Note that for `1.[T][X] borrow book`:

* `1.` means at index 1
* `[T]` means todo
* `[X]` means that the task is done

### `done` - Mark task as done

Marks the given todo, deadline or event at the specified *index* as done

Use the `list` command to obtain the index of the desired item.

```
done 1
	____________________________________
	 Nice! I've marked this task as done:
	   [T][X] borrow book
	____________________________________
```

### `delete` - Delete task

Deletes the given todo, deadline or event at the specified *index*

Use the `list` command to obtain the index of the desired item.

```
delete 1
	____________________________________
	 Noted. I've removed this task:
	   [T][X] borrow book
	 Now you have 4 tasks in the list.
	____________________________________
```

### `find` - Find tasks

Searches tasks for descriptions matching the *search string*

```
find book
	____________________________________
	 Here are the matching tasks in your list:
	 1.[D][ ] return book (by: Fri, 20 Aug 2021 03:00PM)
	____________________________________
```

### `bye` - Exit application

Exits the app
