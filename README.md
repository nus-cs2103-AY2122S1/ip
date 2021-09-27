# CS2103T Individual Project

## Sora

```
     ____________________________  ______
    /  _______/  _____   / ____  \/ _   |
   /  /______   /    /  / /____/ / /_|  |
  /______   /  /    /  /  _   __/  __   |
 _______/  /  /____/  /  / \  \/  /  |  |
/_________/__________/__/   \__\_/   |__|
```

Sora is a desktop application for managing Tasks. The main mode of input is via Command Line Interface (CLI) with a
Graphical User Interface (GUI) to show the output.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project
   first)
1. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained
   in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk). <br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/sora/Sora.java` file, right-click it, and choose `Run Sora.main()` (if the code
   editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the
   below as the output:
   ```
   ____________________________________________________________
        ____________________________  ______
       /  _______/  _____   / ____  \/ _   |
      /  /______   /    /  / /____/ / /_|  |
     /______   /  /    /  /  _   __/  __   |
    _______/  /  /____/  /  / \  \/  /  |  |
   /_________/__________/__/   \__\_/   |__|
   Hi! I'm Sora. How can I help you?
   ____________________________________________________________
   ```
1. Or, locate the `src/main/java/sora/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code
   editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the
   below as the output:<br>
   ![Ui.png](docs/Ui.png)
