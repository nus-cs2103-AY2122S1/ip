# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/duke.Launcher.java` file, right-click it, and choose `Run duke.Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
 ![image](https://user-images.githubusercontent.com/77196303/132969928-208e5f7b-13ce-40ab-8874-c7beca36a23e.png)
 
## Command available

1. todo ...    ->     add todo tasks
2. deadline something /by date (time)   ->   add deadline tasks
3. event something /at date (time)   ->   add event tasks
4. date somedate    ->   find tasks with somedate
5. find somecontent   ->   find tasks with somecontent
6. delete somedigit   ->   delete task with index somedigit
7. done somedigit   ->   mark task with index somedigit as done
8. bye   ->   exit program
