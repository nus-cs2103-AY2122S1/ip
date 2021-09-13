# Kayu (Duke) project template

This is a project template for a greenfield Java project. It's renamed after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`. Alternatively, you can directly open the `build.gradle` file in the directory and `Open as Project` for automated project setup.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Main.java` file, right-click it, and choose `Run main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    /$$   /$$  /$$$$$$  /$$     /$$ /$$   /$$  
   | $$  /$$/ /$$__  $$|  $$   /$$/| $$  | $$  
   | $$ /$$/ | $$  \ $$ \  $$ /$$/ | $$  | $$  
   | $$$$$/  | $$$$$$$$  \  $$$$/  | $$  | $$  
   | $$  $$  | $$__  $$   \  $$/   | $$  | $$  
   | $$\  $$ | $$  | $$    | $$    | $$  | $$  
   | $$ \  $$| $$  | $$    | $$    |  $$$$$$/  
   |__/  \__/|__/  |__/    |__/     \______/  
   
   ___________________________________________________________________________
   Hello!
   I'm Kayu, your alternative personal task management to Duke!
   What can I do for you?
   ___________________________________________________________________________
   ```
