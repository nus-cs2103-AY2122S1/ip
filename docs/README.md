# User Guide for Duke
Duke is a GUI application that allows you to unleash your productivity potentials. Duke helps you to keep track of your
tasks at hand. It provides you with handy features that will bring your productivity to the next level.
* Text-based
* User Friendly
* Very fast to use

## Quick Start
1. Ensure that you have `Java 11` or above installed in your Computer.
2. Download the latest `jar` file in the release section.
3. Copy the file to a home directory for your Duke application.
4. Double-click the `jar` file, and you should be greeted by Duke.
5. Refer below for the Features that Duke can offer.

## Features 
1. [**Getting Help** - `help`](#help)
2. [**Listing all the Tasks** - `list`](#list)
3. [**Exiting the application** - `bye`](#bye)
4. [**Adding a new Task**](#todo)
   1. [`todo`](#todo)
   2. [`event`](#event)
   3. [`deadline`](#deadline)
5. [**Finding a task using a keyword** - `find`](#find)
6. [**Deleting a task** - `delete`](#delete)
7. [**Marking a task as done** - `done`](#done)

## Usage

### <a name="help"></a>`help`
Displays an in-app explanation of all the available features.

**Format:** `help` (no other arguments)

**Example of usage:**
```
help
```

**Expected outcome:**

Duke responds with a help message.
<hr/>

### <a name="list"></a>`list`

Displays a message containing all the tasks that Duke is keeping track of.

**Format:** `list` (no other arguments)

**Example of usage:** 
```
list
```

**Expected outcome:** 

Duke responds with a numbered list of all the tasks that is being tracked.
<hr/>

### <a name="bye"></a>`bye`

Exits the Duke program.

**Format:** `bye` (no other arguments)

**Example of usage:**
```
bye
```

**Expected outcome:**

Duke saves all the current tasks onto hard disk and exits the program.
<hr/>

### <a name="todo"></a>`todo`

Adds a todo task that contains a description.

**Format:** `todo <description>`

**Example of usage:**
```
todo task number 1
```

**Expected outcome:**

Adds a `todo` task with the description `task number 1` into the list that Duke is keeping track of.
<hr/>

### <a name="event"></a>`event`

Adds an event task that contains a description with a date of the event.

**Format:** `event <description> /at <yyyy-mm-dd>`

**Example of usage:**
```
todo task number 2 /at 2021-09-14
```

**Expected outcome:**

Adds an `event` task with the description `task number 2` and scheduled for `14 Sep 2021` into the list that 
Duke is keeping track of.
<hr/>

### <a name="deadline"></a>`deadline`

Adds a deadline task that contains the description with a date that the deadline is due.

**Format:** `deadline <description> /by <yyyy-mm-dd>`

**Example of usage:**
```
todo task number 3 /by 2021-09-14
```

**Expected outcome:**

Adds an `deadline` task with the description `task number 3` and a deadline of `14 Sep 2021` into the list that 
Duke is keeping track of.
<hr/>

### <a name="find"></a>`find`

Sieves out all the tasks with description containing the keyword.

**Format:** `find <keyword>`

**Example of usage:**
```
find submission
```

**Expected outcome:**

Displays a list of tasks that has the word `submission` in their description.
<hr/>

### <a name="delete"></a>`delete`

Deletes the task located at index (1-indexed).

**Format:** `delete <index>`

**Example of usage:**
```
delete 1
```

**Expected outcome:**

Deletes the task that is located at index 1 (take note that 1-indexing is used here).
<hr/>

### <a name="done"></a>`done`

Deletes the task located at index (1-indexed).

**Format:** `done <index>`

**Example of usage:**
```
done 1
```

**Expected outcome:**

Marks the task that is located at index 1 as completed (take note that 1-indexing is used here).
<hr/>

## Command Summary

Task | Command format
------------ | -------------
help | `help`
list | `list`
bye | `bye`
add | 1. `todo <description>` <br> 2. `event <description> /at <yyyy-mm-dd>` <br> 3. `deadline <description> /by <yyyy-mm-dd>`
find | `find <keyword(s)>`
delete | `delete <index>`
done | `done <index>`

## Acknowledgements

This project is done as part of a NUS academic module, CS2103. My sincere gratitude goes out to the 
Professor, Tutors and everyone who has supported me in one way or another. Thank you!