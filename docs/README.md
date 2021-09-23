# User Guide
Duke is a task manager and is named after the Java mascot Duke.

## Setting up
1. Download `duke.jar` from V0.2 from the Releases tab.
2. Run `duke.jar` and begin using it to keep track of all the tasks you need to attend to.

## Features 
1. Add a task of type(todo/event/deadline) to the list.
2. Show all the tasks in the list.
3. Delete specific tasks in the list.
4. Mark specific tasks as done.
5. Search for tasks with keywords.
6. Edit specific tasks.
7. Save tasks, so they can be tracked even after closing app.

##Commands

### `todo`- Add a general task to the list

Input format: `todo <description>`

Example:
`todo go for a run`

Expected reply:
```
Got it. I've added this task:
[T][ ] go for a run
Now you have 1 task in the list
```

### `event`- Add an Event task to the list

Input format: `event <description> /at dd-mm-yyyy HHmm`

Example:
`event cs2103 tutorial /at 17-09-2021 1100`

Expected reply:
```
Got it. I've added this task:
[E][ ] cs2103 tutorial (at: 17 Sep 2021 1100)
Now you have 2 task in the list
```

### `deadline`- Add a Deadline task to the list

Input format: `deadline <description> /at dd-mm-yyyy HHmm`

Example:
`deadline cs2103 Ip submission /by 16-09-2021 2359`

Expected reply:
```
Got it. I've added this task:
[D][ ] cs2103 Ip submission (by: 16 Sep 2021 2359)
Now you have 3 task in the list
```

### `done`- Mark a task in the list as done

Input format: `done <task number>`

Example:

`done 1`

Expected reply:
```
Nice! I've marked this task as done:
[T][X] go for a run
```

### `delete`- Deletes a task in the list
Input format: `delete <task number>`

Example:

`delete 1`

Expected reply:
```
Noted I've removed this task:
[T][X] go for a run
Now you have 2 tasks in the list.
 ```

### `list`- shows all the tasks in the list
Input format: `list`

Example:

`list`

Expected reply:
```
1.[E][ ] cs2103 tutorial (at: 17 Sep 2021 1100)
2.[D][ ] cs2103 Ip submission (by: 16 Sep 2021 2359)
```

### `find`- shows all the tasks in the list with the relevant keyword
Input format: `find <keyword>`

Example:

`find cs`

Expected reply:
```
Here are the matching tasks in your list:
1.[E][ ] cs2103 tutorial (at: 17 Sep 2021 1100)
2.[D][ ] cs2103 Ip submission (by: 16 Sep 2021 2359)
```

### `edit`- Edits a task in the list
Input format: `edit <task number> /description cs 2100 lab`

Example:

`edit 1 /description cs2100 lab`

Expected reply:
```
Noted I've edited this task: 
[E][ ] cs2100 lab (at: 17 Sep 2021 1100)
```

