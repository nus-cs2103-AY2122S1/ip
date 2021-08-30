# CS2106 individual Project <br /> (Duke Chatbot)

This project started of as a greenfield java project. It's named after the Java mascot _Duke_. Given below are 
instructions on how to use it.

> "My job is not to be easy on people. My job is to make them better." [Source](https://www.treasurequotes.com/quotes/my-job-is-not-to-be-easy-on-people-my-job-is-2)

Duke helps you remember things and keep tracks of things for you.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    Good day there! I'm DUKE
    
    What can I do for you?
   ```
### To get Duke working,
1. Download the [here](https://github.com/marcuspeh/ip/releases/download/A-Jar/A-Jar.jar)
2. To run the file, type in `java -jar A-Jar.jar` in the command line.
3. Add your tasks.
4. Let it manage the task for u.
  
## Main advantage of Duke includes
- Text based
- Easy to use
- Minimalistic

## Features:
- [X] Managing Task
- [X] Managing datelines
- [X] Managing Todos
- [ ] Reminders

### Main _advantage_ of Duke includes
- Text based
- Easy to use
- Minimalistic

### Miscellaneous
If you Java programmer, you can use it to practice Java too. Here's the main method:
```java
public class Main {
    public static void main(String[] args) {
        Duke.run();
    }
}
```