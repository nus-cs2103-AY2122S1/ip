# Duke

## Features

### Add tasks

Three types of tasks - todo, deadline and event can be added to the task list.
### Deleting tasks

Tasks can be removed from the list
### Marking tasks as done

Tasks in the list can be marked as done
### Editing tasks in the list

Various fields (like description and date) can be edited for a task in the list
### Finding tasks by description

The task list can be searched for task(s) containing a given keyword
### Finding tasks on a given date

The task list can be searched for task(s) occuring on a certain date

### Reading and Saving tasks

The input tasks are saved and reloaded every time the app is launched
## Usage

### Tasks

### `todo` - To add a todo task to the list

A todo task is a task with a description. It is denoted by `[T]` in the list of tasks

Input format:

`todo [description]`

Example of usage:

`todo read book`

Expected outcome:

```
Got it. I've added this task:
[T] [ ] read book
Now you have 1 task in the list.
```

### `deadline` - To add a deadline to the list

A deadline is a task with a description and a due date.
It is denoted by `[D]` in the list of tasks. The due date is to be input in the YYYY-MM-DD format.

Input format:

`deadline [description] /by [due_date]`

Example of usage:

`deadline return book /by 2021-10-10`

Expected outcome:

```
Got it. I've added this task:
[D] [ ] return book (by: Oct 10 2021)
Now you have 2 tasks in the list.
```

### `event` - To add an event to the list

An event is a task with a description, due date and due time.
It is denoted by `[E]` in the list of tasks. The due date is to be input in the YYYY-MM-DD HHmm format.

Input format:

`event [description] /at [due_date_time]`

Example of usage:

`event book club sharing /at 2021-10-12 1800`

Expected outcome:

```
Got it. I've added this task:
[E] [ ] book club sharing (at: Oct 12 2021 1800)
Now you have 3 tasks in the list.
```
### `list` - To view all the tasks in the list

The list command is used to view all the tasks in the list

Input format:

`list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T] [ ] read book
2. [D] [ ] return book (by: Oct 10 2021)
3. [E] [ ] book club sharing (at: Oct 12 2021 1800)
```

### `done` - To mark a task as complete in the list

The done command is used to mark a task as done in the list.
The task to be marked completed is referenced by its index in the list.

Input format:

`done [task_no]`

Example of usage:

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
[E] [X] book club sharing (at: Oct 12 2021 1800)
```

### `delete` - To mark a task as complete in the list

The delete command is used to remove a task from the list.
The task to be removed is referenced by its index in the list.

Input format:

`delete [task_no]`

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[D] [ ] return book (by: Oct 10 2021)
Now you have 2 tasks in the list.
```

### `edit` - To edit a field of a task

The edit command is used to edit a field of a task in the list.

Depending on the type of task, they can update the following fields:
1. todo: description (desc)
2. deadline: description (desc), due date (date)
3. event: description (desc), due date and time (datetime)


Input format:

`edit [task_no] [field] [revised_contents]`

Example of usage:

`edit 2 datetime 2021-10-20 1900`

Expected outcome:

```
This task has been successfully edited:
Here is the previous version
[E] [X] book club sharing (at: Oct 12 2021 1800)

Here is the edited version
[E] [ ] book club sharing (at: Oct 20 2021 1900)
```

### `find` - To search the list for a given keyword

The find command is used to search the list for a given keyword.

Input format:

`find [query_word]`

Example of usage:

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T] [ ] read book
2. [E] [ ] book club sharing (at: Oct 20 2021 1900)
```

### `tasks_on` - To search the list for tasks on a given date

The tasks_on command is used to retrieve all the tasks due on a given date.

Input format:

`tasks_on [date]`

Example of usage:

`tasks_on 2021-10-20`

Expected outcome:

```
Here are the tasks due on 2021-10-20
1. [E] [ ] book club sharing (at: Oct 20 2021 1900)
```


