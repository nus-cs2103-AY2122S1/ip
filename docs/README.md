# Duke User Guide

## Features 

### ToDo

Adds a task to be done with no particular deadline to the list.

### Deadline

Adds a task to be done with a concrete deadline to the list.

### Event

Adds a task to be done at a certain time to the list.

### List

Displays all tasks added to the list in order.

### Delete

Removes a task in the list according to its index in the list.

### Done

Marks a task in the list as done according to its index in the list.

### Find

Searches for tasks which contain the same string of letters. 

### Undo

Undoes the effects of the previous command including other Undos.

### Exit

Exit from Duke via typing in a command.

## Usage

### `ToDo` - Adds a task to be done

Example of usage:

`todo (description of task to be done)`

todo eat bread

Expected outcome:

Adds the displayed task to the list.

```
Got it. I've added this task:
[T][ ] eat bread
Now you have 1 tasks in the list.
```

### `Deadline` - Adds a task to be done with a concrete deadline

Example of usage:

`deadline (description of task to be done) /by (date in YYYY/MM/DD format)`

deadline finish bread /by 2021/09/11

Expected outcome:

Adds the displayed task to the list.

```
Got it. I've added this task:
[D][ ] finish bread (by: 11 Sep 2021)
Now you have 2 tasks in the list.
```
### `Event` - Adds a task to be done at a certain time

Example of usage:

`event (description of task to be done) /at (description of time)`

event bread sale /at when its dark outside

Expected outcome:

Adds the displayed task to the list.

```
Got it. I've added this task:
[E][ ] bread sale (at: when its dark outside)
Now you have 3 tasks in the list.
```

### `List` - Displays all tasks added to the list in order

Example of usage:

`list`

Expected outcome:

Displays all tasks added to the list in order.

```
Here are the tasks in your list:
1.[T][ ] eat bread
2.[D][ ] finish bread (by: 11 Sep 2021)
3.[E][ ] bread sale (at: when its dark outside)
```

### `Delete` - Removes a task in the list

Example of usage:

`delete (index of task in the list)`

delete 2

Expected outcome:

Removes the displayed task from the list.

```
Noted. I've removed this task:
[D][ ] finish bread (by: 11 Sep 2021)
Now you have 2 tasks in the list.
```

### `Done` - Marks a task as done

Example of usage:

`done (index of task in the list)`

done 1

Expected outcome:

Marks the displayed task as done.

```
Nice! I've marked this task as done:
[T][X] eat bread
```

### `Find` - Finds tasks with similar string of letters

Finds tasks with similar string of letters.

Example of usage:

`find (string of letters)`

find ale

Expected outcome:

Displays tasks with similar string of letters along with their index in the list.

```
2.[E][X] bread sale (at: when its dark outside)

Are these the tasks you're looking for?
```

### `Undo` - Undoes the last command

Example of usage:

`undo`

undo

Expected outcome:

Undoes the last command.

```
Undone!
```

For instance the list was:

```
1.[T][ ] eat bread
2.[D][ ] finish bread (by: 11 Sep 2021)
3.[E][X] bread sale (at: when its dark outside)
```
If the previous command was done 3 ie. mark task 3 as done undo would change the list to:
```
1.[T][ ] eat bread
2.[D][ ] finish bread (by: 11 Sep 2021)
3.[E][ ] bread sale (at: when its dark outside)
```
Undo again would result in:
```
1.[T][ ] eat bread
2.[D][ ] finish bread (by: 11 Sep 2021)
3.[E][X] bread sale (at: when its dark outside)
```

If the previous command did not change the list, undo would result in no change.

### `Exit` - Exits the application

Example of usage:

`bye`

bye

Expected outcome:

Displays farewell message and closes.

```
Bye. Hope to see you again soon!
```