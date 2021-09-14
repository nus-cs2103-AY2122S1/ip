# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see the GUI.


## Commands

- list
- save
- todo
- deadline
- event
- remove
- done
- find
- tag

## Command formats

###List
`list`

Lists out all the tasks added.
###Save
`save `

Saves the list of tasks to the local file.
###Add Todo
`todo [task_name] `

Adds a todo task with the name **`task_name`**.
#####Sample command
`todo Write essay`
###Add Deadline
`deadline [task_name] /by [date]`

Adds a deadline task by the name **`task_name`**.
**`date`** must be in format **DD/MM/YYYY**.

#####Sample command
`deadline Fill in application /by 15/09/2021`
###Add Event
`event [task_name] /at [date]`

Adds a event task by the name **`task_name`**.
**`date`** must be in format **DD/MM/YYYY**.

#####Sample command
`event Attend lecture /by 22/10/2021`
###Remove
`remove [index]`

Removes a task from the list.
**`index`** refers to the index of the task in the list returned by the **list** command

#####Sample command
`remove 1`
###Done
`done [index]`

Marks a task as done.
**`index` **refers to the index of the task in the list returned by the **list** command.

#####Sample command
`done 1`
###Find
`find [search_string] `

Finds the tasks with containing **`search_string`** in its string form.

#####Sample command
`find add`
###Tag
`tag [index] /tag [tag_name] `

Tags a task with **`tag_name`**.
**`index` **refers to the index of the task in the list returned by the **list** command.

#####Sample command
`tag 1 /tag Temporary`


