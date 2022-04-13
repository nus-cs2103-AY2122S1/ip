# User Guide

## *Quick Start*
___
1. Ensure that you have Java 11 installed on your computer.
2. Download the latest duke.jar file [here](https://github.com/tanruiquan/ip/releases)
3. Copy the file to the directory that you want to use as the home directory for Duke.
4. Double-click the file to start the app. The GUI similar to the picture below should appear in a few seconds. ![Example GUI](Example.png)
5. Refer below to the features and usage of the program.

## *Features*
___
### Record your tasks

Record 3 different types of task: todo, event and deadline.

### List out your tasks

Show all the task that you have.

### Find you tasks

Quickly search for a task by their description.

### Check off your tasks

Mark completed task as done. Supports mass operation.

### Delete you tasks

Delete tasks that you no longer want to record. Supports mass operation.

## *Usage*
___

Arguments in `<>` are compulsory while those in `[]` are optional.

### `todo` - Add a todo task into your list

Format: `todo <DESCRIPTION>`

Example of usage:
`todo read book`

Expected outcome:
```
Got it. I've added this task:
 [T][ ] read book
Now you have 1 task in the list.
```

### `event` - Add an event task into your list

Format: `event <DESCRIPTION> /at <DATE:YYYY-MM-DD>`

Example of usage: `event party /at 2021-09-12`

Expected outcome:
```
Got it. I've added this task:
 [E][ ] party (at: Sep 12 2021)
Now you have 1 task in the list.
```

### `deadline` - Add deadline task into your list

Format: `deadline <DESCRIPTION> /by <DATE:YYYY-MM-DD>`

Example of usage: `deadline return book /by 2021-09-12`

Expected outcome:
```
Got it. I've added this task:
 [D][ ] return book (by: Sep 12 2021)
Now you have 1 task in the list.
```

### `list` - Listing all tasks

Format: `list` / `ls`

Example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [E][ ] party (at: Sep 12 2021)
3. [D][ ] return book (by: Sep 12 2021)
```

### `find` - Locating task(s) by description

Format: `find <DESCRIPTION>` / `search <DESCRIPTION>`

Example of usage: `find book`

Expected outcome:
```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Sep 12 2021)
```

### `done` - Mark task(s) as completed by index

Format:

`done <INDEX_1> [INDEX_2] ...` 

`finish <INDEX_1> [INDEX_2] ...` 

`complete <INDEX_1> [INDEX_2] ...`

Example of usage: `done 1 2 3`

Expected outcome:
```
Nice! I've marked these tasks as done:
1. [T][X] read book
2. [E][X] party (at: Sep 12 2021)
3. [D][X] return book (by: Sep 12 2021)
```

### `delete` - Deleting task(s) by range

Format: 

`delete <FROM_INDEX> [TO_INDEX]`

`remove <FROM_INDEX> [TO_INDEX]`

Example of usage: `delete 1 3`

Expected outcome:
```
Noted. I've removed these tasks:
1. [T][X] read book
2. [E][X] party (at: Sep 12 2021)
3. [D][X] return book (by: Sep 12 2021)
Now you have 0 task in the list.
```

### `exit` - Exiting the program

Format: `exit` / `bye` / `quit`

Example of usage: `exit`

Expected outcome: Exits the program.
