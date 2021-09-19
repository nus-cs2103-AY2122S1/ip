# User Guide: *Duke ver. T*

## Features 

### Feature- Save Todos, Events, & Deadlines

You can add 3 different types of tasks to your list to keep track of.

### Feature- Event clash detection

Duke ver. T warns you if you have events that overlap.

## Usage

### `list` - View your task list

Displays all tasks currently in your task list.

Example of usage:

`list`

Expected outcome:

Duke ver. T shows your list.

Example list:

```
1. [T][ ] Finish assignment
2. [T][X] Schedule meeting
3. [E][ ] Group meeting (11 Sep 2021 15:00 - 17:00)
4. [D][ ] Proposal submission (by: 31 Oct 2021)
```
Output if your list is empty:
```
No tasks in list!
```

### `todo` - Add a 'To-do' task to your list

Adds a new todo task to your list.

Format:
`todo [NAME OF TASK]`

Example of usage:

```
todo buy groceries
```

Expected outcome:

Duke ver. T adds a new todo task called `buy groceries`.

```
Sure thing. Added to list:
[T][ ] buy groceries
Number of tasks in list: 1
```

### `event` - Add an event to your list

Adds an event that spans a given duration to your list.

Format:
`event [NAME OF EVENT] /from [START] /to [END]`

`[START]` and `[END]` are to be in the format `yyyy-MM-dd HH:mm`.

Example of usage:

```
event group meeting /from 2021-09-11 09:11 /to 2021-09-11 11:09
```

Expected outcome:
Duke ver. T adds a new event to your list.
```
Sure thing. Added to list:
[E][ ] group meeting (11 Sep 2021 09:00 - 11:00)
Number of tasks in list: 2
```
Duke ver. T will warn you if the event you just added clashes with another event on your list.
```
Take note of clashing event(s):
[E][ ] concert (11 Sep 2021 10:00 - 11:00)
```

### `deadline` - Add a deadline to your list
Adds a deadline with a given date to your list.

Format: `deadline [NAME OF DEADLINE] /by [DATE]`

`[DATE]` must be in the format `[yyyy-MM-dd]`.

Example of usage:
```
deadline assignment /by 2021-09-11
```
Expected outcome:

Duke ver. T adds given deadline to your list.
```
Sure thing. Added to list:
[D][ ] assignment (by: 11 Sep 2021)
Number of tasks in list: 3
```
### `done` - Mark a task as done

Marks the task with the given index in your list as 'done'.

Format: `done [INDEX]`

`[INDEX]` starts from 1.

Example of usage:
```
done 1
```
Expected outcome:

Duke ver. T marks the given task as 'done'.
```
Alrighty, marking this task as done:
[T][X] Finish assignment
```

### `delete` - Delete a task from your list

Permanently deletes a given task from your list.

Format: `delete [INDEX]`

`[INDEX]` starts from 1.

Example of usage:
```
delete 2
```
Expected outcome:

Duke ver. T permanently removes given task from your list.
```
Okay then, I've removed this from the list:
[T][X] Schedule meeting
Number of tasks in list: 5
```

### `find` - Find a task in your list

Finds all tasks in your list from your given search terms.

Format: `find [SEARCH TERMS]`

Example of usage:
```
find meeting
```
Expected outcome:

Duke ver. T returns a list of tasks that match your search term.

```
Here's a list of the stuff I found that match what you're looking for:
2. [T][X] Schedule meeting
3. [E][ ] Group meeting (11 Sep 2021 15:00 - 17:00)
```
### `bye` - Close *Duke ver. T* application

Stops Duke ver. T and closes the application.

Example of usage:

```
bye
```

Expected outcome:

Duke ver. T closes after you press `enter` or hit the 'Send' button.

```
Seeya, press enter again to close.
```
