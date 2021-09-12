# DukeBot

> “Not only should you have a to-do list, but it must become your best friend.” — Jim Kwik [(source)](https://medium.com/@oaklinejournal/25-quotes-from-successful-people-on-the-power-of-writing-to-do-lists-8a0b3729a34c)

DukeBot is a greenfield Java Project made in CS2103T (Software Engineering).
It's named after the Java mascot _Duke_. 

## About

DukeBot is a CLI (Command Line Interface) program that functions as a to-do list. 
It frees your mind of having to remember things you need to do. 
It's: 
* Easy to install 
* ~~Easy~~ **SUPER EASY** to use
* Text-based 

Start using it **today** to increase your productivity!

## Features 

Features:
* Managing tasks
* Managing deadlines
* Managing events
* Filter your tasks
* Save the date and time of your tasks


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
   Hello! I'm Duke.
   What can I do for you?
   ```

## For programmers
If you are a Java programmer, you can use it to practice Java too. Here's the main method:

```java 
public Duke {
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
```

`data/duke.txt` is where your tasks will be saved and retrieved from when needed.

## Acknowledgements 
Chatbot icon made by [Adib Sulthon](https://www.flaticon.com/authors/adib-sulthon") from www.flaticon.com

User icon made by [Vectors Market](https://www.flaticon.com/authors/vectors-market) from www.flaticon.com

Tutorials from [SE-Education](https://se-education.org/guides/tutorials/javaFxPart1.html)