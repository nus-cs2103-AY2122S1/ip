# User Guide

## Features

* Add Tasks
* Delete Tasks
* Complete Tasks
* View List of Tasks
* Sort Tasks
* Find Tasks

## Type of Tasks Available

* Events
* Deadlines
* Todos
* Normal Tasks

## Usage

### `todo {description}` - Add Tasks

A Todo task with the description you entered will be created. `event`, `deadline`, or `add`
can be used in place of `todo` to create different types of tasks.

Example of usage:

`todo CS2103T assignment`

`add CS2103T assignment`

`deadline CS2103T assignment /by 05/09/21`

`event CS2103T meeting /at 05/09/21 07:00`

Note that deadline and event type of tasks require you to add time argument after
`/at` or `/by` 

While Saber supports multiple time format, to be safe, please follow either of the above format

Expected outcome:

Saber will reply you acknowledging the creation of a Todo / Normal Tasks

(This reply may vary depending on what type of task you create)

```
      Yes, Master.
      I'll add the following to your Todo list:

        [T][ ] CS2103T assignment

      Currently, Master has {total task} tasks
      in the list.
```

### `list` - List Tasks

Your current tasks will be listed

Example of usage:

`list`

Expected outcome:

Saber will show you your task list.

```
      Would you like to know what you
      told me to remember?

      I'll list them for you, Master.

      {task list}
```

### `delete {task number}` - Delete Tasks

You can remove a task by entering `delete` followed by the task number.

Example of usage:

`delete 1`

Expected outcome:

Saber will remove the first task.

```
      Understand, Master.
      I have deleted this task.

        {task deleted}

      Currently, Master has {total task} tasks
      in the list.
```

### `done {task number}` - Mark Task as Done

You can complete a task by entering `done` followed by the task number.

Example of usage:

`done 1`

Expected outcome:

Saber will mark the first task as done.

```
      Understand, Master.
      I'll mark this done right away.

        {task marked as done}
```

### `sort` - Sort Tasks

Sort your current tasks by complete-ness status. 
Tasks that are not yet done will be on top, while tasks that are done will be listed after.

Example of usage:

`sort`

Expected outcome:

Saber will sort your current tasks.

```
      Alright, I'll list them in done-ness
      order, Master.
      
      {list of tasks that are not done}
      {list of tasks that are done}
```

### `find {task description}` - Find Tasks

Find all tasks that contain the given word(s) in their description.

Example of usage:

`find hello`

Note that the given word(s) is not case-sensitive

Expected outcome:

Saber will find and list out all tasks that contain the given word(s) in their description.

```
      Right away, Master. Please give me a
      moment to recollect everything ...

      I'll list them for you, Master.

      {tasks that contain the given word(s)}
```

### `bye` - Quit Saber

Quit Saber application

Example of usage:

`bye`

Expected outcome:

Saber will say bye and you will quit the program.