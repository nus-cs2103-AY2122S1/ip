
# CS2103 Duke Project  

<img height="100" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Duke_%28Java_mascot%29_waving.svg/226px-Duke_%28Java_mascot%29_waving.svg.png"/>

## Duke Template

Based on a project template for a greenfield Java project. It's named after the [Java mascot _Duke_](https://www.oracle.com/java/duke.html).

- Project Duke is an educational software project designed to take you through the steps of building a small software incrementally while applying as many Java and SE techniques as possible along the way.
- The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.

Given below are instructions on how to use it:

## Setting up in Intellij :zap:

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk)<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ____________________________________________________________
   Hello! I'm Duke
   What can I do for you?
   ____________________________________________________________
   ```

## List of Features :bread:
- [X] Add Command
- [X] Delete Command
- [X] Done Command
- [X] List Command
- [X] Find Command
- [X] Done Command
- [ ] ~~Useless Command~~
- [ ] _More features to come!_

## How To Use :bulb:

> Prepare a `tasks.txt` containing a list of commands & task to be **initialized** <br>
> This file is _optional_ but is recommended to test the program's features

For example:
```text
add todo read book
add deadline return book /by 6/6/2021 1600
```

## Some Code :package:

```java
public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
 ```
    
