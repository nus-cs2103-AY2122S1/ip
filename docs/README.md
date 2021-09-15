# User Guide


DukePro frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ *SUPER* FAST to use

## Features and Usages
*Note: Type commands into the text box in GUI.
Please replace content inside`[ ]` with your input. 
Follow the date/time format given.*

### Add Task
- add a new **todo** task : `todo [task name]`
- add a new **deadline** task : `deadline [task name] /by [yyyy-MM-dd HH-mm]`
- add a new **event** task : `event [task name] /at [yyyy-MM-dd HH-mm]`

Sample input format:
```
todo todo1
```

Expected output format:
```
Got it. I"ve added this task:
 [T][] todo1
Now you have 4 tasks in the list.
```
---
### View Task
- view all tasks: `list`

*Note: Tasks are automatically sorted based on priority ( todo > deadline > event ),
as well as ascending time for **deadlines** and **events**.*

Sample input format:
```
list
```
Expected output format:
```
Here are the tasks in your list:
1.[T][] todo1
2.[D][] deadline1 (by:Feb-11-2022 13:21)
3.[E][X] event1 (at:Feb-12-2012 12:12)
4.[E][] event2 (at:Jan-14-2032 13:23)
``` 
---
### Mark Task as Done
- mark a specific task as done: `done [task number]`

*Note: Please use `list` to get the **task number** of desired task before running `done`.
After a task is marked as done, the status of that task will change from `[]` to `[X]`.*

Sample input format:
```
done 2
```
Expected output format:
```
Nice! I've marked this task as done:
[D][X] deadline1 (by:Feb-11-2022 13:21)
```
---
### Delete Task
- mark a specific task as done: `delete [task number]`

*Note: Please use `list` to get the **task number** of desired task before running `delete`.
After a task is deleted, it will no longer exist in the task list.*

Sample input format:
```
delete 1
```
Expected output format:
```
Noted. I've removed this task:
[T][] todo1
Now you have 2 tasks in the list.
```
---
### Find Task
- find a specific task by keyword: `find [keyword]`

*Note: A list of all tasks that contains the keyword will be returned.*

Sample input format:
```
find shampoo
```
Expected output format:
```
Here are the tasks found by your keyword in you list:
1.[T][] buy shampoo
2.[E][] throw away shampoo (at:Feb-11-2024 12:13)
```
---
### Say Bye to Duke
- say bye to Duke: `bye`

Sample input format:
```
bye
```
Expected output format:
```
Duke out! Wake me up when ya need me again:)
```
---
### Invalid Input Handling
- any invalid input : `[invalid input]`

Sample input format:
```
Are you single?
```
Expected output format:
```
OOPS!!! I'm sorry but I don't know what that means :-(
```
