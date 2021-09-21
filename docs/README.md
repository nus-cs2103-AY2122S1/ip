# Duke User Guide

## Basic usages
### Add different types of task
- `todo {description}` will add in a normal task.
- `deadline {description} /by {date} {time}` will add in a task with deadline.
- `event {description} /at {start date} {start time} {end date} {end time}` will add in a task happening between certain time.
- `event {description} /at {date} {start time} {end time}` is an alternative way to specify events that starts and ends in the same day.

`{date}` should be specified in `yyyy/MM/dd` format
and `{time}` should be specified in `HH:mm` format

### Manage tasks
- `list` will list down all the tasks sorted with priority.
- `done {index}` will mark the task at the index as done. 
- `delete {index}` will delete the task at the index.
- `find {keyword}` will list down all the tasks containing the keyword.

### Feature: Priority system
Duke can order your tasks based assigned priority.
When adding a new task, using flag `-p` to pass the priority argument.
- `todo {description} [-p {priority}]`
- `deadline {description} /by {date} {time} [-p {priority}]`
- `event {description} /at {start date} {start time} {end date} {end time} [-p {priority}]`
- `event {description} /at {date} {start time} {end time} [-p {priority}]`

Note that the priority specifying is optional and should be placed at the end the command.

### Demo
Here is a quick demo of Duke in command line interface:
```
>> todo Watch lecture
————————————————————————————————————————
Got it. I've added this task:
	[T] [M] [ ] Watch lecture
Now you have 1 tasks in the list.
————————————————————————————————————————

>> deadline Finish iP /by 2021/09/17 -p H
————————————————————————————————————————
Got it. I've added this task:
	[D] [H] [ ] Finish iP (by: Sep 17 2021, 23:59)
Now you have 2 tasks in the list.
————————————————————————————————————————

>> event tP meeting /at 2021/09/13 18:00 19:00 -p H
————————————————————————————————————————
Got it. I've added this task:
	[E] [H] [ ] tP meeting (at: Sep 13 2021, 18:00 - Sep 13 2021, 19:00)
Now you have 3 tasks in the list.
————————————————————————————————————————

>> done 2
————————————————————————————————————————
Nice! I've marked this task as done:
	[E] [H] [X] tP meeting (at: Sep 13 2021, 18:00 - Sep 13 2021, 19:00)
————————————————————————————————————————

>> list
————————————————————————————————————————
Here are the tasks in your list:
1.[D] [H] [ ] Finish iP (by: Sep 17 2021, 23:59)
2.[E] [H] [X] tP meeting (at: Sep 13 2021, 18:00 - Sep 13 2021, 19:00)
3.[T] [M] [ ] Watch lecture
————————————————————————————————————————

>> delete 3
————————————————————————————————————————
Noted. I've removed this task:
	[T] [M] [ ] Watch lecture
Now you have 2 tasks in the list.
————————————————————————————————————————

>> find iP
————————————————————————————————————————
Here are the matching tasks in your list:
1.[D] [H] [ ] Finish iP (by: Sep 17 2021, 23:59)
————————————————————————————————————————
```