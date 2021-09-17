# Duke User Guide
Duke is a text-based reminder chatbot that helps you keep track of your 
tasks. In memory of Prince Philip, Duke of Edinburgh 😭😭😭

## Quick Start
1. Ensure that you have Java 11 installed on your machine.
2. Download `Duke.jar` from the latest release [here](https://www.github.com/hanif-kamal/ip)
3. Double-click to start Duke. It might take a few seconds to start.
4. Get started with managing your tasks!

## Features 

### Task management

There are 3 types of tasks.
1. Todos: General tasks that need to be done.
2. Deadlines: Tasks that need to be finished by the specified date.
3. Events: Tasks that will occur at the specified date and time.


#### What you can do with Tasks:
1. Create them
2. View them
3. Delete them
4. Search for them with keywords
5. Mark them as done

### Adding Notes

You can also take notes about anything under the sun. The date and time of when you created the note is saved, so you can take down whatever you like and Duke will remember!

Note commands are quite similar to Task commands.
## Commands

### `todo` - Create a Todo

Creates a Todo with the specified description.

Format: `todo TODO_DESCRIPTION`

Example: `todo Wash the dishes`

Expected outcome: Creates an incomplete Todo.

```
Got it. I've added this task:
 [T][] Wash the dishes
```
### `deadline` - Create a Deadline

Creates a Deadline with the specified description and deadline date.

Format: `deadline DEADLINE_DESCRIPTION /by DEADLINE_DATE` (Date as DD/MM/YY)

Example: `deadline Finish Maths assignment /by 24/09/21`

Expected outcome: Creates an incomplete Deadline.

```
Got it. I've added this task:
 [D][] Finish Maths assignment (by: 24 Sep 2021)
```
### `event` - Create an Event

Creates a Event with the specified description, event date and time.

Format: `event EVENT_DESCRIPTION /at EVENT_DATE EVENT_TIME` (Date as DD/MM/YY, time as HHMM)

Example: `event Attend group meeting /at 01/10/21 1000`

Expected outcome: Creates an incomplete Deadline.

```
Got it. I've added this task:
 [E] Attend group meeting (at: 01 Oct 2021 10:00 AM)
```
### `list` - List out all tasks

Lists out all tasks.

Format: `list`

Example: `list`

Expected outcome: Displays all tasks.

```
Here are the tasks in your list:
1. [T][] Shop for clothes
2. [T][] Get Adblock to make the Shopee ads go away
3. [D][] Return library book (by: 31 Sep 2021)
4. [E][] Catch movie with friends (at: 01 Oct 2021 07:00 PM)
```
### `done` - Mark task as done

Marks a task as done in the list.

Format: `done TASK_INDEX` (Index as a positive integer)

Example: `done 1`

Expected outcome: Marks the 1st task in the list as done.

```
Nice! I've marked this task as done:
 [T][X] Shop for clothes
```

### `delete` - Delete a task in the list

Deletes a task in the list.

Format: `delete TASK_INDEX` (Index as a positive integer)

Example: `delete 1`

Expected outcome: Deletes the 1st task in the list.

```
Noted. I've removed this task:
 [T][X] Shop for clothes
Now you have 4 tasks in the list.
```

### `find` - Finds tasks in the list matching a search term

Finds all tasks in the list with descriptions that match the given search term. Note that the search term is case-sensitive.

Format: `find SEARCHTERM`

Example: `find Shop`

Expected outcome: Lists all the tasks with descriptions that match the given search term.

```
Here are the matching tasks in your list:
1. [T][X] Shop for clothes
2. [T][] Get Adblock to make the Shopee ads go away
```
### `note` - Creates a note

Creates a note. Notes are stored in a separate list.

Format: `note NOTE_DESCRIPTION`

Example: `note They got Jackie Chan too he's doing Shopee ads now`

Expected outcome: Creates a note. The note also indicates when it was created.

```
Got it. I've added this note:
 They got Jackie Chan too he's doing Shopee ads now | created: 17/09/21 10:00 PM
Now you have 1 note in the list. 
```

### `notelist` - Lists out all notes

Lists out all notes.

Format: `notelist`

Example: `notelist`

Expected outcome: Displays all notes.

```
Here are the notes in your list:
1. They got Jackie Chan too he's doing Shopee ads now | created: 17/09/21 10:00 PM
2. It all started with Cristiano Ronaldo | created: 17/09/21 10:04 PM
```

### `notedelete` - Delete a note in the list

Deletes a note in the list.

Format: `notedelete NOTE_INDEX` (Index as a positive integer)

Example: `notedelete 2`

Expected outcome: Deletes the 2nd note in the list.

```
Noted. I've removed this note:
 It all started with Cristiano Ronaldo | created: 17/09/21 10:04 PM
Now you have 1 note in the list.
```
### `notefind` - Finds notes in the list matching a search term

Finds all notes in the list with descriptions that match the given search term. Note that the search term is case-sensitive.

Format: `notefind SEARCHTERM`

Example: `notefind Shopee good`

Expected outcome: Lists all the notes with descriptions that match the given search term.

```
I couldn't find any notes with that particular search term. Please try again.
```

