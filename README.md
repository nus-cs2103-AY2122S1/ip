# Katheryne 
✨✨a genshin impact themed todolist tracker ✨✨
> Ad astra abyssosque! I am Katheryne, the receptionist here at the Adventurers' Guild.

Katheryne can help you remember your daily commissions, and even plan ahead. She can keep track of three kinds of tasks:
* Todos 
* Deadlines (With a deadline time to complete it by) 
* Events (With a date the event is at)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Katheryne.java` file, right-click it, and choose `Run Katheryne.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

## Features
- [x] Add, Delete, Read your tasks. Mark tasks as done.
- [x] Find Tasks by keywords
- [ ] Find Tasks by date added ( to come? )

## Commands
Here are a list of possible commands with Katheryne.
* Add tasks
  * Todos `todo <DESCRIPTION>`
  * Deadlines `deadline <DESCRIPTION> /by <DATE in YYYY-MM-DD format>`
  * Events `event <DESCRIPTION> /at <DATE in YYYY-MM-DD format>`
 * `list` -- List out current tasks
 * Actions on tasks
   * Find tasks by keyword `find <KEYWORD>`
   * Mark task as done by index `done <number>`
   * delete a task `done <number>`
 * `bye` Close the Katheryne Chatbot


