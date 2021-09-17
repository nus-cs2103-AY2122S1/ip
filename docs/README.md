![Image of UI](Ui.png)

## Features

### To-do List

Duke allows you to create todos easily by typing just a few words! Very speedy 🏁
You can even update your todos, and mark them for completion!

### Time-sensitive events and deadlines

Duke comes with pre-set event and deadline task configurations that add time-related data to your tasks!

## Usage

### Use the following commands to let Duke work for you!

### `todo <task description>` - Creates todo

Duke will create a todo-type task, populated with provided <task description>

Example of usage:

`todo Create Documentation`

Expected outcome:

```
Success message indicating that the todo has been successfully created

Got it. I've added this task:
  [T][] Create Documentation
Now you have 1 task in the list
```

### `deadline <task description> /by <YYYY-MM-DD>` - Creates deadline task

Duke will create a deadline-type task, populated with provided <task description> and <date of deadline>.

Example of usage:

`deadline Eat Supper /by 2021-09-15`

Expected outcome:

Success message indicating that the deadline has been successfully created

```
Got it. I've added this task:
  [D][] Eat Supper (by: Sep 15 2021)
Now you have 2 tasks in the list
```
### `event <task description> /at <event start-end>` - Creates event task

Duke will create a event-type task, populated with provided <task description> and <event start-end>.

Example of usage:

`event Movie /at Mon 2-4pm`

Expected outcome:

Success message indicating that the deadline has been successfully created
```
Got it. I've added this task:
  [E][] Movie (at: Mon 2-4pm)
Now you have 3 tasks in the list
```

### `list` - Displays task list

Duke will display your tasks in a nicely formatted list, populated with task-specific data such as time-related data
and completion.

Example of usage:

`list`

Expected outcome:
```
1. [T][] Create Documentation
2. [D][] Eat Supper (by: Sep 15 2021)
3. [E][] Movie (at: Mon 2-4pm)
```

### `done <task number>` - Complete a task

Marks a task as completed. A cross will be shown beside the task description to indicate completion.

Example of usage:

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
  [E][X] Movie (at: Mon 2-4pm)
```

### `update <task number> </field> <new value for field>` - Update task

Duke will update the corresponding field of task specified by task number to use new value supplied.
Task number is based on the task's position in the list as dictated by list.

Field can be specified using the following tags:
1. /desc - description of task
2. /by - deadline (only applicable to deadline tasks)
3. /at - event timing (only available to event tasks)

Example of usage:

`update 1 /desc Fix Documentation`

Expected outcome:
```
Success message indicating that the task has been successfully updated

Updated task:
[T][] Fix Documentation
```

### `delete <task number>` - Delete task

Duke will delete the corresponding task

Example of usage:

delete 1

```Expected outcome:

Success message indicating that the task has been successfully deleted.

Noted. I've removed this task:
[T][] Fix Documentation
Now you have 2 tasks in the list.
```

### `bye` - Close the application

Terminates the programme. Chat bot will issue a farewell message before closing the chat window automatically.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```