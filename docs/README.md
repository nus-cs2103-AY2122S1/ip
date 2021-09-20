# User Guide
Katheryne can help you keep track of your _daily commissions_ -- that is, the day to day tasks of your life.

## Features 
* Save Todos, Events and Deadlines
* Find tasks by keyword
* Mark tasks as done
* Delete tasks

### Adding Tasks

Adding tasks takes place by using of of the three command keywords, `todo`, `event` or `deadline`.

### Finding Tasks

Allows you to search for tasks via `find KEYWORD`, where `KEYWORD` is the word you are searching for.

## Command reference

### Exit Katheryne: `bye`
Typing `bye` will close the bot.

### Adding Tasks: `todo` , `event` and `deadline`

_Todos_ require a description
* **FORMAT:** `todo DESCRIPTION HERE` -- adds a todo to your list, in the undone state.
* **EXAMPLE:** `todo homework`.

_Event_ requires a description and a single /at time when it occurs in the format YYYY-MM-DD.
* **FORMAT:** `event DESCRIPTION HERE /at YYYY-MM-DD` -- adds an event to your list, in the undone state.
* **EXAMPLE:** `event watch movie /at 2007-12-03`.

_Deadline_ requires a description and a single /at time when it occurs in the format YYYY-MM-DD.
* **FORMAT:** `deadline DESCRIPTION HERE /by YYYY-MM-DD` -- adds an event to your list, in the undone state.
* **EXAMPLE:** `deadline buy laptop /by 2017-12-03`.

### Finding out what tasks you have: `list` and `find`

_List_ returns a list of all tasks. 
* **FORMAT:** `list` -- returns a list of all tasks, and the indexes needed to refer to them
* **EXAMPLE:** `list`.

_Find_ returns a new list containing all tasks with a particular keyword in the description
* **FORMAT:** `find KEYWORD` -- returns a list of all tasks containing that keyword
* **EXAMPLE:** `find work` -- might return tasks with descriptions like "do homework" and "work tonight"

### Actions on tasks: `done` and `delete`
Both of these need the index of the task you want to act on.

_Done_:
* **FORMAT:** `done INDEX` -- marks a task as complete
* **EXAMPLE:** `done 4`. -- marks the fourth task, if it exists, as complete

_Delete_:
* **FORMAT:** `delete INDEX` -- removes the task at the given index
* **EXAMPLE:** `delete 4`. -- removes the fourth task, if it exists, from the task list