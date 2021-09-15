# User Guide

## Quick Start
1. Ensure you have Java `11` installed in you computer.
1. Download `Duke.jar`
1. Double click the file to open the app.
---
## Features 

### Feature- `todo`

Add todo task into your task list.

### Feature- `deadline`

Add deadline into your task list.

### Feature- `event`

Add event into your task list.

### Feature- `delete`

Delete specified task in your task list.

### Feature- `done`

Mark specified task done in your task list.

### Feature- `list`

See all tasks in your task list

### Feature- `tag`

Add tag to the specified task in your task list.

### Feature- `find`

Find a list of tasks that match the keyword.

---
## Usage

### `todo` - Add todo task

Adds to do task into the task list

Format: `todo [DESCRIPTION OF TASK]`

Example of usage: 

* `todo go for a run`

Expected outcome:

```
Got it. I've added this task:
[T][] go for a run
Now you have 4 task(s) in the list.
```

### `deadline` - Add deadline

Adds deadline into the task list with specific date to be completed by.

Format: `deadline [DESCRIPTION OF TASK] /by [DATE]`
* The date of the deadline has to be in YYYY-MM-DD

Example of usage:

* `deadline Math Assignment /by 2021-10-18`

Expected outcome:

```
Got it. I've added this task:
[D][] Math Assignment (by:Oct 18 2021)
Now you have 5 task(s) in the list.
```

### `event` - Add event

Adds event into the task list which include specific time.

Format: `event [DESCRIPTION OF TASK] /at [TIME]`
* Time of the event can be in any format

Example of usage:

* `event Maths Exam /at tmr 12pm`
* `event swiming lesson /at 1pm - 3pm`

Expected outcome:

```
Got it. I've added this task:
[E][] Maths Exam (at:tmr 12pm)
Now you have 7 task(s) in the list.

Got it. I've added this task:
[E][] swiming lesson (at:1pm - 3pm)
Now you have 8 task(s) in the list.
```

### `Delete` - Delete task

Delete specified task from the task list.

Format: `delete [TASK NUMBER]`
* Task number is the order in which the task is in the task list

Example of usage:

* `delete 2` - delete the second task in the list

Expected outcome:

```
Noted. I've removed this task:
[E][] Maths Exam (at:tmr 12pm)
Now you have 7 task(s) in the list.
```

### `done` - Mark task done

Mark a specified task as done. 

Format: `done [TASK NUMBER]`
* Task number is the order in which the task is in the task list

Example of usage:

* `done 3` - Mark third task in the list as done

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] go for a run
```

### `List` - Show all task in the list

Display all task in the list in order in which it was added. Show whether the task is completed

Format: `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] go for a run
2. [D][] Math Assignment (by:OC=ct 18 2021)
3. [E][] swiming lesson (at:1pm - 3pm) 
```
### `tag` - Add tag to task

Add tag to specific task in the list.

Format: `tag [TASK NUMBER] #[TAG]`
* Task number is the order in which the task is in the task list

Example of usage:

* `tag 3 #fun` - Tag fun to third task in the list

Expected outcome:

```
fun has been tagged to this task:
[E][] swiming lesson (at:1pm - 3pm) 
```


### `find` - Find task based on keyword

Find task based on keyword. A list of tasks that contain the keyword in the description or tag will be shown 

Format: `find [KEYWORD]`

Example of usage:

* `find fun`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] go for a run
2. [E][] swiming lesson (at:1pm - 3pm) 
```