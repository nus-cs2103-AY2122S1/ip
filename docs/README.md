# Aisu User Guide

<p align="center">
<img src="Ui.png" width="300">
</p>

Welcome to Aisu! Aisu is a CLI chatbot to assist you in tracking your tasks so that you don't have to!
## Features 

### Support for multiple types of Tasks

Aisu supports the creation of 3 types of tasks!
#### 📓 Todos
A simple task that consists of only a description.<br/>
Format: `todo <description>`
#### 🎩 Events
A detailed task occuring at a specific time frame.<br/>
Format: `event <description> /at <date>`
#### ⏲️ Deadlines
A detailed task due by a certain date.<br/>
Format: `deadline <description> /by <yyyy-mm-dd>`
### Support for Tags
Now, you can even add various tags to your tasks to categorise and organise them better!<br/>
Format: `tag <taskNumber> /with <tagname>`

## Usage

###  `todo <description>` - adds a To-Do task

A _todo_ task will be added to your task list.

Example of usage: 

`todo NM3216 Project 1`

Expected outcome:
```
Got it! I've added this task:
- [To-do] [ ] NM3216 Project 1

Now you have 1 task(s) in the list.
```

###  `event <description> /at <date>` - adds an event type task

An _event_ task will be added to your task list.

Example of usage: 

`event CS2103T Lecture /at 12pm`

Expected outcome:
```
Got it! I've added this task:
- [Event] [ ] CS2103T Lecture (at: 12pm)

Now you have 2 task(s) in the list.
```

###  `deadline <desdription> /by <yyyy-mm-dd>` - adds a deadline type task

A _deadline_ will be added to your task list.

Example of usage: 

`deadline CS2100 Assignment /by 2021-09-15`

Expected outcome:
```
Got it! I've added this task:
- [DeadL] [ ] CS2100 Assignment (at: Sep 15 2021)

Now you have 3 task(s) in the list.
```
#### Accepted date time format
* yyyy-mm-dd (e.g 2021-09-10)

###  `list` - shows the list

Shows you all your tasks that are currently stored in the task list.

Expected outcome:
```
Here's what you have in your list:
1. [To-do] [ ] NM3216 Project 1
2. [Event] [ ] CS2103T Lecture (at: 12pm)
3. [DeadL] [ ] CS2100 Assignment (at: Sep 15 2021)
```

###  `done <index>` - marks a task as completed

Marks a task to be completed indicated by the index after the `done` command. The index specified is with reference to its position in the `list` command.

Example of usage: 

`done 1`

Expected outcome:
```
Nice! I've marked this task as completed:
- [To-do] [x] NM3216 Project 1
```
###  `find <query>` - finds tasks containing the search query

The `find` command is used to search through the tasks and find tasks with descriptions that contains the search query. This command is **case-sensitive**.

Example of usage: 

`find NM`

Expected outcome:
```
Here's what I found:
1. [To-do] [x] NM3216 Project 1
```
###  `delete <index>` - removes a task from the task list

The `delete` command uses the same indexing method as the `done` command. Deletes the task at the specified index.

Example of usage: 

`delete 1`

Expected outcome:
```
Noted~ I've removed this task:
- [To-do] [x] NM3216 Project 1

Now you have 2 task(s) in the list.
```
###  `tag <index> /with <tag name>` - adds a tag to a specified task

The `tag` command will add a tag with the tag name (if it is not pre-existing) to the task at the specified index. 

Example of usage: 

`tag 1 /with CS`

Expected outcome:
```
Noted I've added the tag CS to this task:
- [Event] [ ] CS2103T Lecture (at: 12pm)
Tags: #CS
```

###  `help` - displays the commands available

The `help` command will display all the allowed commands in the GUI.

Expected outcome:
```
 * 1) Type "todo (task)" - Add tasks without any date/time attached to it
 * 2) Type "list"    - Show list
 * 3) Type "done (taskNumber)" - Mark task as done
 * 4) Type "deadline (task) /by (yyyy-mm-dd)" - Add tasks that need to be done before a specific date/time
 * 5) Type "event (task) /at (date)" - Add tasks that start at a specific time and ends at a specific time
 * 6) Type "find (keyword)" - Look for tasks with that keyword
 * 7) Type "delete (taskNumber)" - Delete task
 * 8) Type "tag (taskNumber) /with (tagname)" - Add tag to task
 * 9) Type "bye"     - Exit program
 * 10) Type "help"   - Show help page
```

###  `exit` - terminates Aisu

Closes the GUI window.


