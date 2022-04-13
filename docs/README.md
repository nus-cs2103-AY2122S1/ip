# User Guide

## Features 

### Tasks

There are 4 types of tasks you can add.

1. Todo
2. Event
3. Deadline
4. Fixed-Duration

### Instructions

You can talk to Duke using 7 types of instructions.

1. Start Duke
2. Add a task
3. List tasks
4. Mark a task as done
5. Delete a task
6. Find a task
7. End Duke

## Usage

### Start Duke

Start Duke by running the following command in your terminal

`java -jar duke.jar`

Ensure `duke.jar` is located in the folder inside now.
```
Hello from
     ____        _        
    |  _ \\ _   _| | _____ 
    | | | | | | | |/ / _ \\
    | |_| | |_| |   <  __/
    |____/ \\__,_|_|\\_\\___|
    ____________________________________________________________
    Hello! I'm Duke
    What can I do for you?
```

### Add

Add a task to Duke.

* Todo

`todo [TASK DESCRIPTION]`

Example:

`todo Feed my dog Spotty!`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
      [T][ ] Feed my dog Spotty!
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

* Event

`event [TASK DESCRIPTION] /at [yyyy-mm-dd]`

Example:

`event Zoom party /at 2021-10-21`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
      [E][ ] Zoom party (at: Oct 21 2021)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

* Deadline

`deadline [TASK DESCRIPTION] /by [yyyy-mm-dd]`

Example:

`deadline Submit Assignment 1 /by 2021-09-15`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
      [D][ ] Submit Assignment 1 (by: Sep 15 2021)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

* Fixed-Duration

`fixed [TASK DESCRIPTION] /for [DURATION]`

Example:

`fixed Final Exam /for 2 hours`

Expected outcome:

```
    ____________________________________________________________
     Got it. I've added this task:
      [F][ ] Final Exam (needs 2 hours)
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

### List

Show all the tasks stored in Duke.

`list`

Expected outcome:

```
    ____________________________________________________________
     Here are the tasks in your list:
      1. [T][ ] Feed my dog Spotty!
      2. [E][ ] Zoom party (at: Oct 21 2021)
    ____________________________________________________________
```

### Mark as done

Mark your existing task as done.

`done [INDEX]`

Example:

`done 1`

Expected outcome:

```
    ____________________________________________________________
     Nice! I've marked this task as done:
      [T][X] Feed my dog Spotty!
    ____________________________________________________________
```

### Delete

Delete the task from Duke.

`delete [INDEX]`

Example:

`delete 2`

Expected outcome:

```
    ____________________________________________________________
     Noted. I've removed this task:
      [E][ ] Zoom party (at: Oct 21 2021)
    ____________________________________________________________
```

### Find

Find all tasks containing a keyword in its task description.

`find [KEYWORD]`

Example:

`find dog`

Expected outcome:

```
    ____________________________________________________________
     Here are the matching tasks in your list:
      1.[T][X] Feed my dog Spotty!
    ____________________________________________________________
```

### End Duke

End your Duke program.

`bye`

This will close the UI. All your tasks will be added to a storage file `duke.txt`, created under `/data`

## Acknowledgements

* [JavaFX tutorial @SE-EDU/guides](https://se-education.org/guides/tutorials/javaFx.html)
