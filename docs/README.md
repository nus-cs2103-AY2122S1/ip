# BroBot User Guide

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest brobot.jar from [here](https://github.com/markuslim24/ip/releases/tag/v2.0).

3. Copy the file to the folder you want to use as the home folder for the application. Data will be saved in the home folder by default.

4. Double-click the file to start the app. 

## Features 
* * *
### Add a todo :`todo`
Adds a todo task to the tasklist.

Format: `todo [DESCRIPTION]`

Examples:
* `todo eat my lunch`
* `todo think about life`


### Add a deadline :`deadline`
Adds a deadline task to the tasklist.

Format: `deadline [DESCRIPTION]/by [DD/MM/YYYY HHMM]`

Examples:
* `deadline finish cs2103t ip /by 17/09/2021 1700`
* `deadline go to sleep/by 16/09/2021 2359`


### Add an event : `event`
Adds an event task to the tasklist.

Format: `event [DESCRIPTION]/at [DD/MM/YYYY HHMM]`

Examples:
* `event attend birthday party /at 20/09/2021 1300`
* `event go for lecture/at 15/09/2021 1400`


### List my tasks: `list`
Shows the current list of tasks by date added.

Format: `list`


### Mark a task as done: `done`
Mark a task on the tasklist as done.

Format: `done [TASK_NUMBER]`

Examples:
* `done 1`
* `done 5`


### Delete a task: `delete`
Delete a task from the tasklist.

Format: `delete [TASK_NUMBER]`

Examples:
* `delete 2`
* `delete 3`


### Search tasks: `find`
Search for all tasks with descriptions that contain the specified keyword.

Format: `find [KEYWORD]`

Examples:
* `find work`
* `find cs2103t`


### Change storage location: `storage`
Allows users to select a new save location for which data for the app will be stored.

Format: `storage`

