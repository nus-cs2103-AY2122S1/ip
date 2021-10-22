How to use GraceTask App?

## Features

1. Save a list of tasks that you want the app to keep track of. 
2. Find a task by inputting the keywords.
3. Mark a task as done. 
4. Delete a task when no longer needed.
5. Output the current list of tasks.

## Usage

### `todo` - Add a task that you need to do

The task will be stored as a todo in the task list. 

Example of usage: 

`todo homework`

Expected outcome:

The todo task is added to the bottom of the task list with T tag representing todo.

```
Added the task! :)
[T][] homework 
Jiayou! you have 1 task in the list. 
```

### `event` - Add an event that you need to attend and it's time

The task will be stored as an event in the task list with it's corresponding event time. 

Example of usage: 

`event party /at 6pm`

Expected outcome:

The event task is added to the bottom of the task list with E tag representing event.

```
Added the task! :)
[E][] party (at 6pm)
Jiayou! you have 2 tasks in the list. 
```

### `deadline` - Add a deadline that you need to rush for 

The task will be stored as a deadline in the task list with it's corresponding due time. 

Example of usage: 

`deadline assignment /by 2021-12-10`

Expected outcome:

The deadline task is added to the bottom of the task list with D tag representing deadline.

```
Added the task! :)
[D][] assignment (by Dec 10 2021)
Jiayou! you have 3 tasks in the list. 
```

### `find` - find a task 

The list of task that includes the keyword will be outputed. 

Example of usage: 

`find homework`

Expected outcome:

The list of tasks that include the word 'homework' will be outputed.

```
Found these matching tasks!
1. [T][] CS2100 homework
2. [D][] CS1231S homework (by Dec 10 2021)
```

### `done` - mark the task you completed as done

The corresponding task in the task list will be marked as done 

Example of usage: 

`done 2`

Expected outcome:

The second task in the task list will be marked as done

```
Yay! you have finished this task!
[T][X] CS2100 homework
```

### `delete` - delete a task from the task list

The corresponding task in the task list will be deleted

Example of usage: 

`delete 2`

Expected outcome:

The second task in the task list will be removed.

```
Congrats! You have completed this task!
[T][X] CS2100 homework
3 more to go!! Press on!!
```

### `list` - output the list of tasks 

The current task list will be ouputted.

Example of usage: 

`list`

Expected outcome:

```
Do these soon:
1. [T][X] hw 
2. [E][] party (at 6pm)
3. [D][] assignment (by Dec 10 2021) 
```

### `bye` - save the current tasks in a text file.

The current task list will be saved.

Example of usage: 

`bye`

Expected outcome:

```
Bye bye! Hope to see you again soon!
```
