# Duke Bot Project

This is a greenfield Java project. It's named after the Java mascot _**Duke**_. Given below are instructions on how to use it.

## Introduction
Duke Bot is an offline PC Task Manager that helps you to keep track of your tasks on the go.

Before you get started with Duke Bot, here is an introduction of the components and how you can work with them in Duke Bot:
   1. Types of Tasks:
      1. Todo: Tasks without any date/time
      1. Event: Tasks that needs to be done on a specific date from a start time to an end time
      1. Deadline: Tasks that needs to be done by a specific date/time
      
   2. Types of Commands:
      1. Adds Todo task: `todo <description>`
      1. Adds Event task: `event <event description> /at <dd/MM/yy> <HHmm>-<HHmm>`
      1. Adds Deadline task: `deadline <deadline description> /by <dd/MM/yy> <HHmm>`
      1. Marks task as done: `done <task index>`
      1. Deletes task: `delete <task index>` 
      1. Shows all tasks: `list`
      1. Find task(s): `find <keyword>`
      1. Quit application: `bye`

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/duke/Launche.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, an external window will pop out with the following as the output:

   ```
   Hi there! How may I help you? 
   To access the help page, type "help"
   ```
1. And you are ready to use Duke Bot!
