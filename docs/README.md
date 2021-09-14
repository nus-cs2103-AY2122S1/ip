# User Guide
![Ui](Ui.png)

Duke is a **to-do list application, which can be used to track to-dos, events and deadlines**. 
Duke is built to replicate a chat-bot, so that users feel more comfortable using Duke.

Currently, tasks are divided into 3 categories: to dos, events and deadlines.

To dos are basic tasks, while events have a date which represents when the event is, 
and deadlines have a date which represents when the task is due by.

1. [Getting started](#start)
2. [Features](#features)
   1. [Adding a task](#add)
   2. [Listing tasks](#list)
   3. [Finish a task](#done)
   4. [Delete a task](#delete)
   5. [Tagging a task](#tag)
3. [Getting started](#usage)

## Getting started with Duke <a name="start"/>
1. Ensure you have Java `11` or above installed.
2. Download the latest version of `duke.jar` from [here](#insertlinklater)
3. Place `duke.jar` into the desired folder
4. Double click `duke.jar` and the GUI should pop up

## Features <a name="features"/>
<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** The task list lists tasks in this format:

`[Type][isDone] taskName`

If the type contains a date,

`[Type][isDone] taskName (by/at: date)`

If a task is done, the box will be `[x]`, else `[]`.

Types are T, D and E, where T is task, D is deadline, E is event.
</div>

### Adding a task: `todo`,`deadline` and `event` <a name="add"/>
Since there are three types of tasks, there are three different commands to add the three different types of tasks.

`todo` adds a to-do typed task to the task list:
```aidl
todo CS2103 assignment

Got it, I've added this task to your task list:
[T][] CS2103 assignment
Now you have 1 task in your task list.
```

`deadline` adds a deadline typed task to the task list:
```aidl
deadline CS2103 assignment /by 2021-11-21 06:00

Got it, I've added this task to your task list:
[D][] CS2103 assignment
Now you have 1 task in your task list.
```

`event` adds an event typed task to the task list:
```aidl
event CS2103 finals /at 2021-11-21 06:00

Got it, I've added this task to your task list:
[D][] CS2103 finals
Now you have 1 task in your task list.
```

**Use the following syntax:**
```aidl
todo (task name)
deadline (task name) /by (date)
event (task name) /at (date)
```

### Listing out tasks: `list` <a name="list"/>

This command will list out all the tasks present in the task list, along with its state and type.

For example:
```aidl
list

Here are the tasks in your list:
1.[T][] CS2103 Assignment
2.[D][] CS2103 Assignment (by 2021-11-21 06:00)
3.[E][] CS2103 Finals (at 2021-11-21 06:00)
```

### Finish a task: `done` <a name="done"/>

This command will mark a task as done. 

For example:
```aidl
done 1

Nice! I've marked this task as done:
[T][X] CS2103 Assignment

list

Here are the tasks in your list:
1.[T][X] CS2103 Assignment
2.[D][] CS2103 Assignment (by 2021-11-21 06:00)
3.[E][] CS2103 Finals (at 2021-11-21 06:00)
```

**Use the following syntax:**
```aidl
done (index as seen in the list)
```

### Delete a task: `delete` <a name="delete"/>

This command will delete a task from the task list.

For example:
```aidl
delete 1

Noted. I've removed this task:
[T][X] CS2103 Assignment
Now you have 3 tasks in the list.

list

Here are the tasks in your list:
1.[D][] CS2103 Assignment (by 2021-11-21 06:00)
2.[E][] CS2103 Finals (at 2021-11-21 06:00)
```
**Use the following syntax:**
```aidl
delete (index as seen in the list)
```

### Tag tasks: `tag` <a name="tag"/>

This command can be used to tag tasks with anything the user wishes to.

For example:
```aidl
tag 1 #assignments

Got it. I've tagged the following item:
[D][] CS2103 Assignment (by 2021-11-21 06:00) (tags: #assignments)

list

Here are the tasks in your list:
1.[D][] CS2103 Assignment (by 2021-11-21 06:00) (tags: #exam)
2.[E][] CS2103 Finals (at 2021-11-21 06:00)
```

**Use the following syntax:**
```aidl
tag (index in list) #(tag)
```

