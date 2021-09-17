# User Guide
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest release from [here](https://github.com/willyamped/ip/releases/download/v0.3/duke.jar).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Double-click the .jar file to start the app. The GUI should look similar to the screenshot below.<br>![Screenshot](Ui.png)
5. Type in a command in the command box and press Enter to execute it. <br>

## Features


### Adding a task : `todo/deadline/event`

Adds a task to the list. <br>

A todo is a task with a description and no deadline. <br>
An event is a task with a description and needs to be done before a specific deadline.<br>
A deadline is a task with a description and starts at a specific deadline. <br>

Format: [task] [description] /[time preposition] [deadline]
<br>
Deadline format:<br>
- Date format: yyyy-mm-dd
- Time format: hh:mm
- Without a format e.g Sunday 2pm



### Viewing all tasks: `list`

Views all tasks in the list.

Format: list
<hr>


### Finding a task based on a keyword that exactly matches a description: `find`

Finds a task based on a search term.

Format: find [keyword] 
<hr>


### Finding a task based on a keyword that partially/ exactly matches a description: `search`

Finds a task based on a keyword that partially/exactly matches a task.

Format: search [keyword]
<hr>


### Marking a task as done: `done`

Marks a task as done as shown by the symbol 'X'.

Format: done [number]
<hr>

### Deleting a task: `delete`

Deletes a particular task in the list.

Format: done [number]
<hr>


### Exiting the program: `bye`

Exits the whole program.

Format: bye
<hr>


## Usage
`todo`

Creates a todo task.

Example:
- todo math homework

Expected outcome:

Returns an acknowledgement message and outputs the number of tasks in the list.

```
Duke says: Got it. I've added this task:
[T][] math homework
Now you have 3 tasks in the list.
```
<hr>

`deadline`

Creates a deadline task.

Example:
- deadline math homework /by Sunday

Expected outcome:

Returns an acknowledgement message and outputs the number of tasks in the list.

```
Duke says: Got it. I've added this task:
[D][] math homework (by: Sunday)
Now you have 3 tasks in the list.
```
<hr>

`event`

Creates an event task.

Example:
- event formal dinner /on Monday

Expected outcome:

Returns an acknowledgement message and outputs the number of tasks in the list.

```
Duke says: Got it. I've added this task:
[E][] formal dinner (on: Monday)
Now you have 3 tasks in the list.
```
<hr>

`list`

Shows all tasks in the list

Example:
- list

Expected outcome:

Outputs all tasks in the list.

```
Duke says: Here are the tasks in your list:
1.[E][] formal dinner (on: Monday)
2.[T][] cs2100
3.[T][] math homework
```
<hr>

`find`

Finds a task based on a search term.

Example:
- find swimming

Expected outcome:

Returns tasks that match the keyword.

```
Duke says: Here are the matching task(s) in your list:
1.[E][] swimming (on: Monday)
```
<hr>

`search`

Finds a task based on a keyword that partially/exactly matches a task.

Example:
- find swim

Expected outcome:

Returns tasks that contain the keyword in the description.

```
Duke says: Here are the matching keyword(s) in your list:
1.[E][] swimming (on: Monday)
2.[E][] swim (on: Sunday)
```
<hr>

`done`

Marks a task as done as shown by the symbol 'X'.

Example:
- done 2

Expected outcome:

Returns an acknowledgement message.

```
Duke says: Nice! I've marked this task as done:
[E][X] swimming (on: Monday)
```
<hr>

`delete`

Deletes a particular task in the list.

Example:
- delete 1

Expected outcome:

Returns an acknowledgement message and outputs the number of tasks in the list.

```
Duke says: Noted. I've removed this task:
[E][X] swimming (on: Monday)
Now you have 3 tasks in the list.
```
<hr>

`bye`

Exits the program

Example:
- bye

Expected outcome:

Exits the whole program.

```
Duke says: Good Bye. Have a nice day!
```
