# User Guide

[![Java CI](https://github.com/James-Kua/ip/actions/workflows/gradle.yml/badge.svg)](https://github.com/James-Kua/ip/actions/workflows/gradle.yml)

Duke is a GUI application that helps keep track of tasks. 

## Usage
1. Ensure that you have `Java 11` installed on your computer.
2. Download the [duke.jar](https://github.com/James-Kua/ip/releases/tag/A-Release) file to an empty directory where you want to store the program.
3. Open your terminal and run the following command: `java -jar duke.jar` to start the program. A GUI should appear.
4. Type `help` to see all available commands.
5. All the tasks will be stored automatically inside the `duke.txt` file inside the folder.
6. Refer below to the usage of each command.

## Features 

Duke supports the addition of 3 tasks types as shown below:

| Task         | Symbol      |
|--------------|-------------|
|Deadline      |`[D]`        |
|Event         |`[E]`        |
|Todo          |`[T]`        |


***


### `help`

Shows the available commands supported.

Format: `help`

Example of usage:
`help`

Expected outcome:
A list of supported commands and how to use them will be shown. 


***


### `list`

Lists all the tasks in the task list. 

Format: `list`

Example of usage:
`list`

Expected outcome:
A list containing the tasks will be printed. 

***


### `todo`

Creates a Task of Todo type. 

Format: `todo <DESCRIPTION>`

Example of usage: 
`todo sleep`

Expected outcome: 
The successfully created todo task will be printed out.

**Expected output:**
```
Got it. I've added this task: [T][ ] sleep 
Now you have 1 task in the list.
```

***


### `deadline`

Creates a Task of Todo type. 

Format: `deadline <DESCRIPTION> (by: <DATE> <TIME>)`

*Note: Date should follow `YYYY-MM-DD` format and time should follow `HH:mm` format. Time is optional.* 

Example of usage: 

`deadline return book (by: 2021-09-06 18:00)` 

`deadline return book (by: 2021-09-06)`

Expected outcome: 
The successfully created deadline task will be printed out.

**Expected output:**
```
Got it. I've added this task: [D][ ] return book (by: Sep 06 2021, 6.00pm) 
Now you have 1 task in the list.
```

***


### `event`

Creates a Task of Event type. 

Format: `event <DESCRIPTION> (at: <DATE> <TIME>)`

*Note: Date should follow `YYYY-MM-DD` format and time should follow `HH:mm` format. Time is optional.* 

Example of usage: 

`event group project (at: 2021-09-08 18:00)`

`event group project (at: 2021-09-08)`

Expected outcome: 
The successfully created event task will be printed out.

**Expected output:**
```
Got it. I've added this task: [E][ ] group project (at: Sep 08 2021, 6.00pm) 
Now you have 1 task in the list.
```

***


### `done`

Marks a task as done.

Format: `done <TASK_NUMBER>`

Example of usage: 

`done 2`

Expected outcome: 
The task done will be printed.

**Expected output:**
```
Nice, I've marked this task as done: [D][✓] return book (by: Sep 08 2021, 6.00pm) 
```

***


### `delete`

Deletes a task.

Format: `delete <TASK_NUMBER>`

Example of usage: 

`delete 2`

Expected outcome: 
The task deleted will be printed.

**Expected output:**
```
Noted, I've removed this task: [D][ ] return book (by: Sep 08 2021, 6.00pm) 
Now you have 3 tasks in the list.
```

***


### `find`

Finds a task with a keyword.

Format: `find <KEYWORD>`

*Note: keyword is **case sensitive***

Example of usage: 

`find book`

Expected outcome: 
All the tasks matching the keyword will be printed out.

**Expected output:**
```
Here are the tasks that matches your keyword: 
1.[D][ ] return book (by: Sep 08 2021, 6.00pm) 
2.[E][ ] buy book (at: Sep 09 2021, 6.00pm) 
```

***


### `bye`

Exits the program. 

Format: `bye`

Expected outcome: An exit message will be shown and the program will close after a delay.


***


## Command Summary

| Command      | Format                                               | Example                                       |
|--------------|------------------------------------------------------| ----------------------------------------------|
| help         | `help`                                               | `help`                                        |
| list         | `list`                                               | `list`                                        |
| todo         | `todo {DESCRIPTION}`                                 | `todo sleep`                                  |
| deadline     | `deadline {DESCRIPTION} (by: {YYYY-MM-DD} {HH:mm})`  | `deadline return book (by: 2021-09-06 18:00)` |
| event        | `event {DESCRIPTION} (at: {YYYY-MM-DD} {HH:mm})`     | `event group project (at: 2021-09-08 18:00)`  |
| done         | `done {TASK_NUMBER}`                                 | `done 2`                                      |
| delete       | `delete {TASK_NUMBER}`                               | `delete 2`                                    |
| find         | `find {KEYWORD}`                                     | `find book`                                   |
| bye          | `bye`                                                | `bye`                                         |



## Acknowledgements
[Faker Icon](https://skt-faker.tumblr.com/post/161856846188/skt-chibi-iconsmore-skt-faker-sky-peanut)

[Faker Avatar 1](https://www.pngfind.com/mpng/hRhRomi_vave-skt-fan-art-faker-hd-png-download/)

[Faker Avatar 2](https://www.artstation.com/artwork/xz6GKE)

[Faker Background](https://skt-faker.tumblr.com/post/170861397283/galio-skill-guid-r-heros-entrance)

