# TaskMe

TaskMe is based on a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Clone the repository.
1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
![Image of TaskMe](https://github.com/Th-429B/ip/blob/master/docs/TaskMe.PNG?raw=true)
   
## Setting up using Jar file

1. Download the Jar file from ```releases```
2. Drag the Jar file from downloads folder to wherever you wish to run TaskMe from
3. Open up Command Prompt
4. Change directory to where you saved TaskMe to
5. Run the command ```java -jar taskme.jar```

## Features
### Task Manager
- Add 3 different tasks to TaskMe, ``todo``, ``deadline``, ``event``
- Tracker to track if tasks are done! 
- Delete tasks that are completed
- List all the tasks
- Sort the current Tasks by ``name`` or ``type``
