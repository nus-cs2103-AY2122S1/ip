# Cygnus Chatbot :rocket:
Here are the tasks in your list:
- [x] Go to GitHub
- [ ] Download Cygnus
- [ ] Manage your time better!

> Cygnus has helped me identify what needs to be done and manage my time better. Definitely recommend!
> -- A satisfied user

**Cygnus** is a *Personal Assistant Chatbot* designed for people who type fast. It features: 
* **Command-line interface** for keyboard-only navigation
* **Adding** and **removing** tasks
* Marking tasks as done
* **Filtering** tasks by name

With more features to come!

Using Cygnus is simple:
1. Click on [this link](https://github.com/jyrw/ip/releases/tag/A-Release)
2. Download the `.jar` file
3. Double click the file
4. Start adding tasks!

The full user guide for Cygnus can be found [here](https://jyrw.github.io/ip/).

*Cygnus* is based on the *Duke project template* (info below).

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
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
