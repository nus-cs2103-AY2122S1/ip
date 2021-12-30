# What is Duke

Duke is a task bot for users to easily track their todo lists. Users can note down their tasks in three types, namely event, todo, and deadline, and add notes as well as time range to each task accordingly. Refer to the following sections for feature details!

# Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello! I'm Duke
   Here are the tasks to be finished today:
   ```

# User Guide

## Features 

### Feature-CRUD
User can add (`todo`, `deadline`, `event`) and delete (`delete + task index`) a task to the task list. A task can also 
be marked as done (`done + task index`).
User can view all tasks added to the task list.

### Feature-Show task on a certain day
User can use `get + dd/mm/yyyy` keyword to get tasks on a particular day.
`get today` can be used to quickly get tasks on a certain day.

### Feature-Reminder
User can see all tasks to be done by today (particularly `event` and `deadline` types) when opening the application.

### Feature-Search for a task
User can use `find + keyword` to search for a certain task.

### Feature-Save 
Tasks added to the task list is auto-saved to local files.
When user reopen the application on the same device, the tasks previously added can be seen.

## Usage

### `todo` - add a task of type todo
A todo type has no time attached to it, user can choose to finish it by anytime.

Example usage:
`todo write essay`

Expected outcome: 
![Image of Todo](https://user-images.githubusercontent.com/46596402/132972836-16c7f2e0-351c-4c3b-a9ee-299bb58ce227.png)

### `event` - add an event task to task list
An event type has a time range to be specified by the users. The date is a must and time range is optional.
`event /at dd/mm/yyyy (time)`

Example usage:
`event wash hair /at 21/09/2021 2-3pm`

Expected outcome:
![Image of Event](https://user-images.githubusercontent.com/46596402/132973045-8b404c5a-1993-4f69-a14f-2bde67236306.png)

### `deadline` - add a deadline task to task list
A deadline type has a time stamp to be specified by the users. The time can be input in the form of `/by dd/mm/yyyy` 
or `/by dd/mm/yyyy hh:mm`

Example usage:
`deadline finish homework /by 23/09/2021`
`deadline finish homework /by 23/09/2021 13:45`

Expected outcome:
![Image of Deadline](https://user-images.githubusercontent.com/46596402/132973047-9f3f9324-0961-4e00-9fbd-754fe80e102d.png)
![Image of Deadline](https://user-images.githubusercontent.com/46596402/132973049-7adb4739-e0d2-4fc1-9b05-caf6b0aae272.png)

### `done` - mark a task as done

Example usage:
`done + task index`
`done 3` where 3 is the position of the task intended to complete in the task list.

Expected outcome:
![Image of Done](https://user-images.githubusercontent.com/46596402/132973052-fbfcdae3-729a-4324-a1fa-05dc4e5c0354.png)

### `delete` - delete a task
Example usage:
`delete task index`
`delete 3` where 3 is the position of the task intended to complete in the task list.

Expected outcome:
![Image of Delete](https://user-images.githubusercontent.com/46596402/132973054-02528a32-7fe0-490f-b74b-e745f8a8df98.png)

### `list` - show all tasks added to the task list
Example usage:
`list`

Expected outcome:
![Image of List](https://user-images.githubusercontent.com/46596402/132973056-4be66d7a-d5bf-4529-84ef-c785021624e7.png)

### `find` - find tasks with specified keyword
Example usage:
`find class`

Expected outcome:
![Image of Find](https://user-images.githubusercontent.com/46596402/132973060-475ef569-469e-4bc7-a165-fb2015f62a52.png)

### `get` - get tasks on a specific date
`get today` to get tasks due today quickly.
`get dd/mm/yyyy` to get tasks on the specified date.

Example usage:
`get today`
`get 23/09/2021`

Expected outcome:
![Image of get today](https://user-images.githubusercontent.com/46596402/132973064-09aa9485-56fa-408e-89f6-0a218c9e1a61.png)
![Image of get a date](https://user-images.githubusercontent.com/46596402/132973070-aec535a8-e8f1-4eb4-ab52-cf2bb0c83a5b.png)

### `bye` - close the application
Example usage:
`bye`

Expected outcome:
The application closes.
