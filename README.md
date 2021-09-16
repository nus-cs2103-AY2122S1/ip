# The Duke
<p>
The Duke (inspired by a character in the show Solar Opposites) is a personal helper chatbot. The Duke allows you to 
keep track of tasks such as:
</p>

 - To-do type tasks
 - Tasks with deadlines
 - Events

See below for a full list of The Duke's commands and how to use The Duke :smiley:

> Welcome to my humble abode. I'm the Duke. The ruler of shadows.
> The main vein. The big boy. The top puppy.

## User Guide

### Add To-Do `todo <task name>`
Adds a to-do type task to your list of tasks.
###### Example
`todo New Task` will add a to-do task called New Task to your list. 

### Add Deadline `deadline <deadline name> /<date and time>`
Adds a task with a deadline to your list of tasks.
 - Date and time is preceded with a backslash (/).
 - Date and time must be entered in the format YYYY-MM-DD HH-MM.
###### Example
`deadline New Deadline /2021-12-30 18:00` will add a deadline task called 
New Deadline with the deadline set to the 30th of december 2021 at 6pm.

### Add Event `event <event name> /<date and time>`
Adds an event with a date and time to your list of tasks.
- Date and time is preceded with a backslash (/).
- Date and time must be entered in the format YYYY-MM-DD HH-MM.
###### Example
`event New Event /2021-12-30 18:00` will add an event called
New Event scheduled for the 30th of december 2021 at 6pm.

### List Tasks `list`
Lists out all the tasks which you currently have in your list.

### Complete Task `done <task index>`
Sets a task to be marked as completed.
 - `task index` is based on the index indicated with the command `list`.
###### Example
`done 1` will mark the first task in the list as completed.

### Delete Task `delete <task index>`
Deletes a task from your task list.
- `task index` is based on the index indicated with the command `list`.
###### Example
`delete 1` will delete the first task in your list.

### Close Application `bye`
Closes the application.

### Search List of Tasks `find <keyword>`
Shows you your tasks which names contain the keyword specified. 
###### Example
`find CS2103` will show you a list of all your tasks which have 
"CS2103" in their name.

### Reschedule a Task `snooze <task index> /<date and time>`
Changes the deadline/time of a Deadline or Event task to the date and 
time specified.
- Command is only applicable to Deadline or Event tasks.
- `task index` is based on the index indicated with the command `list`.
- Date and time is preceded with a backslash (/).
- Date and time must be entered in the format YYYY-MM-DD HH-MM.
###### Example
`snooze 1 /2021-12-30 23:59` will change the date and time of the first
task to the 30th of December, 11:59pm.
