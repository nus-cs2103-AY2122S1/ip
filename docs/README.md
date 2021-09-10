# User Guide

Duke is an application for managing your tasks via a CLI while having the benefits of a simple GUI that is easy to use.

## Quick start
1. Ensure you have Java 11 or higher installed in your computer.
2. Download the latest release of `duke.jar` from [here](https://github.com/benedictchuajj/ip/releases)
3. Double-click on the jar file to start the application. You will see a GUI that is similar to this:
   <img src="https://raw.githubusercontent.com/benedictchuajj/ip/master/docs/StartUi.png" width="426" height="650">
   
4. Input some commands to start using Duke! Here are some simple commands you can try:
    * `todo try app` : Adds a ToDo task named `try app` to the task list.
    * `list` : lists all tasks in the task list.
    * `bye` : closes the application. 
      
You can check out the [Features](https://github.com/benedictchuajj/ip/tree/master/docs#features) section for an extensive list of the commands available in Duke.

## Features 

### Add Tasks:

There are 3 types of tasks that you can add to the task list and this is how you can add them:

#### ToDo
ToDo is a task that is not assigned to any dates.
To add a Todo to the list:

`todo task_name`

#### Deadline
Deadline is a task that has a deadline.
To add a Deadline to the list:

`deadline task_name /by dueDate dueTime`
where `dueDate` is given in *dd/mm/yyyy* and `dueTime` is given in the 24-hour format.

#### Event
Event is a task that occurs during a stipulated timing.
To add an Event to the list:

`event task_name /by eventDate startTime endTime`
where `eventDate` is given in *dd/mm/yyyy* and `startTime` & `endTime` is given in the 24-hour format.


### Display Tasks

Displays the tasks in the current task lists. There are a few ways to display the task with various filtering.

#### List all tasks
To list all tasks without any filtering:
`list`

#### Filter by date - List all tasks that occurs on a particular date
To list the tasks with a filtering based on a date:

`check filterDate`
where `filterDate` is given in *dd/mm/yyyy*.

#### Filter by description - List all tasks that has a keyword in the description
To list the tasks with a filtering based on a keyword:

`find filterKeyword`
where `filterKeyword` the text that you want to find in the task's description.

#### Reminders - List upcoming tasks
There are a few ways you can get reminders:

`remind x`
where `x` is one of the 3 arguments below:
* next - the next upcoming task
* today - all upcoming tasks in the next 24 hours
* week - all upcoming tasks in the coming week

### Complete Tasks:

Tasks can be marked as being completed by inputting:

`done x` where `x` is the index of the task in the list.

You can also input multiple indexes to mark multiple tasks as completed:

`done i j k` where `i`, `j` and `k` are the indexes of the task in the list.

### Remove Tasks:

Tasks can be removed from the list by inputting:

`delete x` where `x` is the index of the task in the list.

### Exiting the program:

Close the application by inputting:

`bye`

## Command Summary

| Action | Format, Examples |
| --- | --- |
| Add ToDo | `todo TASK`<br />e.g., `todo try app` |
| Add Deadline | `deadline TASK /by DATE TIME`<br />e.g., `deadline assignment submission /by 16/09/2021 1300` |
| Add Event | `event TASK /at DATE sTIME eTIME`<br />e.g., `event team meeting /at 11/09/2021 1200 1400` |
| List | `list` |
| Check | `check DATE`<br />e.g., `check 25/12/2021` |
| Find | `find KEYWORD`<br />e.g., `find meeting` |
| Reminder | `remind METHOD`<br />e.g., `remind next`, `remind today`, `remind week` |
| Done | `done INDEX(ES)`<br />e.g., `done 2`, `done 1 3 5` |
| Delete | `delete INDEX`<br />e.g., `delete 2` |
| Exit | `bye` |

## Acknowledgements:
Images used in Duke are from the following sources:
* [backgroundImage](https://www.enjpg.com/nice-18/)
* [Duke profile image](https://twitter.com/suisei_hosimati/status/1435255188818853893/photo/1)
* [User profile image](https://www.google.com/search?q=kaigainiki+face&tbm=isch&ved=2ahUKEwiXvoXHjPTyAhVChUsFHfnJBBcQ2-cCegQIABAA&oq=kaigainiki+face&gs_lcp=CgNpbWcQAzIFCAAQgAQ6BggAEAUQHjoGCAAQChAYUMFAWOxDYLZEaABwAHgAgAFDiAGBApIBATWYAQCgAQGqAQtnd3Mtd2l6LWltZ8ABAQ&sclient=img&ei=BCY7YZesO8KKrtoP-ZOTuAE&bih=919&biw=1920#imgrc=DIAvV7AqZsT0OM)

Referenced User Guide format from:
* [AB-3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html)
* [WeiJie96](https://github.com/WeiJie96/ip/tree/master/docs)
