# User Guide

**Duke** is a desktop app for managing tasks, optimized for use via a Command Line Interface.
## Quick start

1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `duke.jar` from [here](https://github.com/Li-Lehao/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details of each command.

## Features

* ### Add a new task
```
todo n/TASK_NAME
```
```
event n/TASK_NAME /at d/DATE [t/TIME]
```
```
deadline n/TASK_NAME /by d/DATE [t/TIME]
```
Examples:
* `todo cs2103 topic`
* `event cs2103 lecture /at 10-09-2021 1600`
* `deadline cs2103 quiz /by 17-09-2021`

note that the format of DATE should be dd-MM-yyyy,
and the format of TIME should be HHmm
* ### Find tasks on a specific day
```
date d/DATE
```
note that the format of DATE should be dd-MM-yyyy

* ### Delete a task
```
delete n/NUMBER_OF_TASK
```
delete the nth task in the current task list

* ### Mark a task as done
```
done n/NUMBER_OF_TASK
```
mark the nth task in the current task list as done

* ### Exit
```
bye
```
Duke will save the current task list to hard disk

* ### Find tasks by keyword
```
find k/KEYWORD
```

* ### List all tasks
```
list
```

* ### Sort the current task list by time
```
sort by time
```
