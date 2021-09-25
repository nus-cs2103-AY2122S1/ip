# Duke: Task Manager for Dudes

<div align="center">
   <img src="/docs/Duke.png" width=400/>
   <p>    This is a project based on a greenfield Java project named after the Java mascot <em>Duke</em>. </p>
</div>


<div align="center">
   <img src="https://user-images.githubusercontent.com/40263305/133960735-95a3f33b-691f-4a64-b314-4dd6cb819f7c.gif" width="400" height="600"/>
</div>


Want to try Duke out? Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
4. Alternatively, if you prefer a GUI version, locate the `src/main/java/duke/Launcher.java`and choose `Run Launcher.main()`. You should see the GUI pop up.


<div align="center">
   <h3>Here's GUI Duke! </h3>
</div>

<div align="center">
   <img src="/docs/Ui.png" width=400/>
</div>

