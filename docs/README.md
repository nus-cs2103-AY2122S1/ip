# 2Butler User Guide

## What is 2Butler?
2Butler is a desktop task management application that can be used to set dates and times for various tasks and events. 
It features a Command line Interface (CLI) for you to input text commands and provides a fast and efficient way of 
laying down your todo list. It is themed based on the Nier Automata video game, and sports a unique look that will  
appease any Nier Automata and 2B fans out there.


<p align="center">
<img src=https://github.com/aikenwx/ip/blob/master/docs/Ui.png?raw=true>
</p>

## Features 

### Add Task

There are 3 different types of tasks in 2Butler- `todo`, `deadline` and `event`. `todo` features a description of 
the task. `deadline` features a description along with a 'done-by' date. `event` includes a description as well as 
an event start date and an event end date. Both `deadline` and `event` can also take in a time along with the given 
dates.

### List tasks
You can `list` all your tasks. Each task will be assigned a task index in the order that they were added to the task 
list.

### Delete task
All tasks are arranged by index in a list. You can then `delete` a task by specifying its task index.

### Mark task as done
Similar to deleting a task, you can also mark a task as `done` by specifying its task index.

### Find task by description
You can `find` tasks by their description by inputting a search string. 

### Find task by date
You can use `find/date` to find a `deadline` or `event` by date and/or time. 

### Sort all tasks

You can `sort` your tasks by chronological order to reorder your `deadline` and `event` tasks in a more pragmatic way.

### Feature-XYZ

Description of the feature.

## Usage

###`todo`- Create a todo task

Format:

`todo TASK`

Example of usage:

`todo Homework`

Expected outcome:

```
Understood Sir/Mdm, I have added the 
indicated task:
    [T][ ] Homework
Now you have 1 task.
```

###`deadline`- Create a deadline task

Format:

- `deadline TASK \by DD/MM/YYYY`
- `deadline TASK \by DD/MM/YYYY TTTT`

Example of usage:

`deadline Project /by 15/09/2021`

Expected outcome:

```
Understood Sir/Mdm, I have added the 
indicated task:
    [D][ ] Project (by: 15 September 
2021)
Now you have 1 task.
```


###`event`- Create an event task

Format:

- `event TASK /at DD/MM/YYYY to DD/MM/YYYY`
- `event TASK /at DD/MM/YYYY TTTT to DD/MM/YYYY TTTT`

Example of usage:

`event Staycation with Mary /at 15/09/2021 1200 to 16/09/2021 1400`

Expected outcome:

```
Understood Sir/Mdm, I have added the 
indicated task:
    [E][ ] Staycation with Mary (at: 15
September 2021 12:00 to 16 September 
2021 14:00)
Now you have 1 task.
```

###`list`- Create an event task

Format:

`list`
- The task list will be indexed by order of addition. e.g. `todo Assignment` followed by `todo Homework` results in 
  `1. [T][ ] Assignment` ordered before `2. [T][ ] Homework`

Example of usage:

`todo Assignment` followed by `deadline Assignment /by 15/09/2021` and then`list`


Expected outcome after line 3:

```
Here are your tasks Sir/Mdm:
1. [T][ ] Assignment
2. [D][ ] Assignment (by: 15 September 
2021)
```

###`delete`- Delete a task by index

Format: 

`delete INDEX`

Example of usage: 

`todo Assignment` followed by `delete 1`

Expected outcome:

```
Much obliged Sir/Mdm! I shall delete this task:
    [T][ ] Assignment
Now you have 1 task.
```

###`done`- Mark a task as done by index

Format:

`done INDEX`

Example of usage:

`todo Assignment` followed by `done 1`

Expected outcome:

```
Good job Sir/Mdm! I shall mark this
task as complete:
    [T][X] Assignment
```
###`find`- Find a task by description

Format:

`find KEYWORD [MORE_KEYWORDS]`
- The search is case-sensitive. e.g. `hans` will not match `Hans`
- The searched string can be a substring of the target description. e.g. `Hans` will match `dinner at Hans`

Example of usage:

`todo Assignment` followed by `find Assignment`

Expected outcome:

```
Here are the results of the search 
Sir/Mdm:
1.  [T][X] Assignment
```

###`find/date`- Find a task by date

Format:

`find/date DD/MM/YYYY`
- You can search for events by finding a date in between the start and end times of events. e.g. `find/date 15/09/2021` 
  will match `[E][ ] Staycation (at: 14 September 2021 to 16 September 2021`
- `find/date` only looks at dates and not times hence `find/date 15/09/2021` matches with `[D][ ] Assignment (by: 15 
  September 2021 13:00)`.

Example of usage:

`deadline Assignment /by 15/09/2021 ` followed by `find/date 15/09/2021`

Expected outcome:

```
Here are the deadlines and events that
match the date Sir/Mdm:
1.  [D][ ] Assignment (by: 15 September
2021)
```

###`find/date`- Find a task by date

Format:

`find/date DD/MM/YYYY`
- You can search for events by finding a date in between the start and end times of events. e.g. `find/date 15/09/2021`
  will match `[E][ ] Staycation (at: 14 September 2021 to 16 September 2021`
- `find/date` only looks at dates and not times hence `find/date 15/09/2021` matches with `[D][ ] Assignment (by: 15
  September 2021 13:00)`.

Example of usage:

`deadline Assignment /by 15/09/2021 ` followed by `find/date 15/09/2021`

Expected outcome:

```
Here are the deadlines and events that
match the date Sir/Mdm:
1.  [D][ ] Assignment (by: 15 September
2021)
```

###`sort`- Sort tasks in chronological order

Format:

`sort`
- When sorting `deadline` and `event` tasks, the start date of `event` tasks will be compared with the 'done-by' 
  dates of `deadline` tasks. e.g. `[E][ ] Staycation (at: 14 September 20201 to 16 September 2021)` will be ordered 
  before `[D][ ] Assignment (by: 15 September 2021` by `sort`.
- `todo` tasks (which do not include a date) will always be ordered before `deadline` and `event` tasks by the `sort` 
  command.

Example of usage:

`deadline Assignment /by 15/09/2021` followed by `event Staycation /at 14/09/2021 to 16/09/2021`, `todo Homework` 
and then `sort`.

Expected outcome:

```
Roger Sir/Mdm! Your tasks are now 
sorted:
1. [T][ ] Homework
2. [E][ ] Staycation (at: 14 September 
2021 to 16 September 2021)
3. [D][ ] Assignment (by: 15 September 
2021)
```
