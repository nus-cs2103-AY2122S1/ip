# User Guide: That time I created a Chat Bot named myself because Singaporean keep f**king mispronounce it and butcher it in front of me

## Features 

### Add, delete, and track completion of various different tasks.

Great news! You can add in three types of task to the list, and let DuC handle everything for you. 
What are the tasks in mention?
1. TODO: Task without deadline or timing, for you to do at your own pace
2. DEADLINE: Task with an upcoming end date (deadline) for you to complete before
3. EVENT: Self-explanatory - a task that occurs on a date for you to join

What can you do? You can add a task, update a task, mark a task as done, or delete a task at will.

### View task and task date

View the list of all ongoing task on the list, and the date associated with them

### Load and Save

Want to visit the list later? Dai-jo-bu, we save everything for you, just reload the app again and see your previously-saved list

## Usage

### `help` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

### `help` - Getting help

Don't know how to use DuC? Like how you keep mispronouncing my name? Fear not, enter `help` in the conversation block 
and see the shortened-version of this User Guide. At your Service!


Expected outcome: Shortened user guide to detail all command syntax for DuC


### `todo` - Add a task of type TODO

Add a task with no deadline, do it at your own time! No pressure involved, skip and skim through it!

Example of usage: 

`todo go to gym at least 1 hour`

Expected outcome: A task of Type TODO is added, with description being the following statements

```
Nice! I've added the following task to your list:
[T][ ] go to gym at least 1 hour
Now you have 1 task in your list

```

### `deadline` - Add a task of type DEADLINE

Do you have CS2103T IP deadline this weekend? Add them to the list, with the expected deadline date
you need to complete. 

*Note that the date entered should be in YYYY-MM-DD for the syntax to work*

Example of usage: 

`deadline CS2103T IP Submission /by 2021-09-17`

Expected outcome: A task with deadline is added, with deadline date associated

```
Nice! I've added the following task to your list:
[D][ ] CS2103T IP Submission (by Sep 17 2021)
Now you have 2 tasks in your list
```

### `event` - Add a task of type EVENT

Do you have a lecture later at some fixed timing? Simple, add an event to it - with the date of occurrence
*Note that the date entered should be in YYYY-MM-DD for the syntax to work*

Example of usage: 

`event NUS Career Fair /at 2021-09-10`

Expected outcome: An event and occurred date is added to the list

```
Nice! I've added the following task to your list:
[E][ ] NUS Career Fair (at Sep 10 2021)
Now you have 3 tasks in your list
```

### `done` - Mark a task in the list as completed

Mark a specified task in the list as completed. Optionally, mark every task in the list as done.
Example of usage: 

`done 2`
`done all`

Expected outcome: The specified task in the list is marked as completed, with an X being checked beside.

```
Nice! I've marked this task as done:
[D][X] CS2103T IP Submission (by Sep 17 2021)
```

### `update` - Change the description of a specified task in the list

Do you mess up a task list description but you do not want to delete it? Fear not, update it instead!
Update a task at a specified position to change its structure and date.

Example of usage: 

`update 1 todo run at least 3km`
`update 2 event CS2103T Lecture /at 2021-09-17`

Expected outcome: The task given at the specified index number is changed accordingly to the new task

```
Noted, I have updated the task at position 1 as:
[T][ ] run at least 3km
```

### `delete` - Delete a task from the list

Don't want to see the task? Delete it! Or delete everything from the list to clear it.

Example of usage: 

`delete 3`
`delete all`

Expected outcome: The indicated task is deleted from the list. Optionally, the list is cleared

```
Noted! I've deleted the following task: 
[D][ ] NUS Career Fair (at Sep 10 2021)
```

### `list` - View all the current tasks you are having

View all current tasks in your list

Expected outcome: Display all the tasks you are having at the moment

### `bye` - Exit the program

Exit the program and save everything into the file
