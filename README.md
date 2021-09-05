# Duke project template

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
3. After that, locate the `src/main/java/seedu/duke/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

Duke is an App which allows you to save and store your todo list. You can add a Todo, Event or a Deadline into the list. Once you have completed the item which you have listed, you can make it as DONE.

Eventually, you can delete those items from the list as well. Let me teach you how to use Duke.

Instruction:
1. First you can add `Task` into the `TaskList` by writing these commands:
   1. For `ToDos` type: "**todo {your task}**"
   1. For `Deadline` type: "**deadline {your task} /by {a deadline}**"
   1. For `Events` type: "**event {your task} /at {date and location}**"
1. You can see the list of the `TaskList` by entering the command: "**list**". It will list out all the `Task` with the index number beside it.
1. Once a `Task` is added into the `TaskList`, you can mark those `Task` as DONE when you have completed the `Task`
   1. To mark a `Task` as DONE, you can enter this command: "**done {task index number}**"
1. If you want to delete the `Task` from the `TaskList`, you can enter this command: "**delete {task index number}**"
1. Once you are done with using Duke, just say "**bye**" to Duke and he will send you off.

# SHHHEEEEESHHHHH!!!!
