# User Guide

Duke is a desktop app which serves to help you manage your daily tasks as well as your future schedules. While this application is fully functional using Command Line Interface (CLI), it saves you the trouble having to switch between your keyboard and your mouse. If you enjoy or incline with typing, feel free to download and use Duke to kick start your productive journey today.

# Quick start

1. Ensure you have Java 11 or above installed in your computer
1. Download the latest Duke.jar from [here](https://github.com/rickyaandrew/ip/releases/download/A-Release-Fix/duke.jar).
1. Copy the file into a folder which you may want to use it as the home folder for your Duke.
1. Double-click on the Duke.jar file, and you will be able to see a similar GUI appearing on your screen.
   Type your command in the command box and press Enter to execute the command.
1. Do read up on our feature list for this Duke Application.

# Features

### Adding a ToDo Task: _todo \<task>_

-   Adds a ToDo Task in Duke.
-   Format: todo run 5 rounds around the field

### Adding a Deadline Task: _deadline \<task> /by \<date and time>_

-   Adds a Deadline Task in Duke, where it shows when the Task needs to be done by.
-   Format: deadline return book /by 12/01/2020 2000

### Adding an Event Task: _event \<task> /at \<day and time or description>_

-   Adds an Event Task in Duke, where it shows when will the event takes place.
-   Format: event project meeting /at Tue 6-8pm

### Adding a TimedTask Task: _timedtask \<task> /needs \<amount of time>_

-   Adds a TimeTask Task in Duke, where it shows how much time is needed to complete this Task.
-   Format: timedtask read books /needs 2hours

### Adding a PeriodTask Task: _periodtask \<task> /between \<date from> and \<date to>_

-   Adds a PeriodTask Task in Duke, where it shows within which period of time this task should be done by.
-   Format: periodtask return book /between 25-01-2021 and 26-1-2021

### Chaining a Task: _chaintask \<task> /after \<task id>_

-   Adds a property to the chosen Task, such that chain task will be generated right after the chosen task is marked as done.
-   Format: chaintask run 6 rounds /after 2

### Scheduling a Task on a fixed time: _scheduledtask \<task> /on \<date> /from \<start time> /to \<end time>_

-   Adds a Scheduled Task in Duke if this Scheduled Task does not clash with other Scheduled Tasks.
-   Format: scheduletask run this /on 12-01-2020 /from 1400 /to 1600

### Find you Task which user have added in Duke: _find \<keywords>_

-   Helps to locate the Task which is previously added in Duke.
-   Format: find return book

### listing all the tasks: _list_

-   Duke will show a list of all the Tasks which has already been added to it.
-   Format: list

### Viewing all scheduled task: _viewschedule_

-   Duke will show a list of all the Scheduled Tasks which has already been added to Duke. (Tasks which is not scheduled will not be shown here)
-   Format: viewschedule

### Completing a Task: _done \<task id>_

-   This command allows Duke to understand that user have already completed a certain Task and will mark the given Task as DONE.
-   Format: done 2

### Deleting a Task: _delete \<task id>_

-   This command allows Duke to understand that user would like to delete a certain Task and will completely delete the given Task.
-   Format: delete 3

### Exiting the program: _bye_

-   Exits the Application.
-   Format: bye

# FAQ

Q: How to transfer Duke from one computer to another computer while retaining all the data?

> A: You can just copy the original Duke.jar file from the computer and run it in the other computer. Do make sure that you have the set up done in the other computer as well as stated in this User Guide at the **Quick Start** Section on top.

Q: Can Duke add different Tags to different groups of Tasks?

> A: Current version of Duke still does not have the Tagging function. However, we will implement it in the furture version.

Q: Will Duke able to check if the current Schedule which I will be added in clashes with other existing schedules?

> A: Yes. Before adding the schedule into Duke, Duke will always check if the schedule clashes with other existing schedules.

# Command Summary

| Action                   | Format/Example                                                                                                                   |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------- |
| help                     | help -> type this for some simple command suggestions                                                                            |
| Adding Todo Task         | todo \<task> <br> todo run 5 rounds around the field                                                                             |
| Adding Deadline          | deadline \<task> /by \<date and time> <br> deadline return book /by 12-01-2020 2000                                              |
| Adding an Event          | event \<task> /at \<date and time> <br> event project meeting /at Tue 6-8pm                                                      |
| Adding a Timed Task      | timedtask \<task> /needs \<amount of time> <br> timedtask read books /needs 2hours                                               |
| Adding a Period Task     | periodtask \<task> /between \<start date> and \<end date> <br> periodtask return book /between 25-01-2021 and 26-1-2021          |
| Chaining a Task          | chaintask \<task> /after \<task index> <br> chaintask run 6 rounds /after 2                                                      |
| Schedule a Task          | scheduletask \<task> /on \<date> /from \<time from> /to \<time to> <br> scheduletask run this /on 12-01-2020 /from 1400 /to 1600 |
| Adding a Tag             | tag \<task id> \<your tags> <br> tag 1 fun exciting                                                                              |
| Deleting tags            | deletetag \<task id> <br> deletetag 2                                                                                            |
| Find an added Task       | find \<task> <br> find return book                                                                                               |
| View all the Tasks       | list                                                                                                                             |
| View all scheduled Tasks | viewschedule                                                                                                                     |
| Mark Task as done        | done \<index> <br> done 2                                                                                                        |
| Delete Task              | delete \<index> <br> delete 3                                                                                                    |
| Exit                     | bye                                                                                                                              |
