# User Guide
## Introduction

Duke is a java application, which can help users store their tasks and do other operations on the tasks when they want.
## Features 

Duke provides both CML version and GUI version. User can call it from CLI or just open it as a Desktop Application.

## Usage
### GUI version
1. Download the jar file of this application.
2. Open terminal, Locate your current repository to the repository where
   the jar file located.
3. Run `java -jar (name of the jar file).jar` in terminal.

Or you can
1. Download the code of this file.
2. Open the project in IDE (i.e. IntelliJ)   
3. Run Launcher.java which is located at src/main/java/duke/gui

### CML version
1. Download the code of this file.
2. Open the project in IDE (i.e. IntelliJ)
3. Run DukeCML.java which is located at src/main/java/duke/cml
## Instructions
In order to let Duke understand your instructions, your instructions should follow the format below:
### Get help message:

`help`

Expected outcome:
```
In order to let me help you to store your tasks, please enter your command in this format:
Add Todo task: todo xxx
Add Event task: event xxx /at xxxx-xx-xx xx:xx to xx:xx
Add Deadline task: deadline xxx /by xxxx-xx-xx xx:xx
Show List: list
Mark item as done: done x
delete item: delete x
find item: find xxxx
Show the command list: help
exit the program: bye
```

### Add Todo task:

`todo (TaskName)`

Expected outcome:
```
Got it. I've added this task:
[T][ ] (TaskName)
Now you have (nums) tasks in the list.
```
### Add Event task:

`event xxx /at xxxx-xx-xx xx:xx to xx:xx`

Expected outcome:
```
Got it. I've added this task:
[E][ ] (TaskName)
Now you have (nums) tasks in the list.
```

### Add Deadline task:

`Add Deadline task: deadline xxx /by xxxx-xx-xx xx:xx`

Expected outcome:
```
Got it. I've added this task:
[D][ ] (TaskName)
Now you have (nums) tasks in the list.
```

### Show List:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [x][ ] xxx
...
```

### Mark item as done: 

`done x`

Expected outcome:

```
Nice! I've marked this task as done:
[(TaskType)][X] (TaskName)
```

### Delete item:

`delete x`

Expected outcome:
```
Noted. I've removed this task:
[(taskType)][(done)] xxx
Now you have (num) tasks in the list.
```

### Find items:

`find (string)`

Expected outcome:

```
Sorry, I cannot find any matching task from the list :(
```
or
```
Here are the matching tasks in your list:
1. ...
```
### Exit the program
`bye`



