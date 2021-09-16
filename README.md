# Duke project template

This is a project for the CS2103 (Software Engineering) Individual Project component. 

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
# Duke Chatbot

According to the [nus-cs2103-AY2122S1/ip README.md](https://github.com/nus-cs2103-AY2122S1/ip/blob/master/README.md),

> This is a project template for a greenfield Java project. It's named after the Java mascot Duke.

## Usage

This **chatbot** can be _used by_:
* the JAR file, found [here](https://github.com/jovyntls/ip/blob/master/build/libs/ip-1.0-SNAPSHOT-all.jar)
* run tests using `bash ./text-ui-test/duke-test.sh`
* clone the repo and run the `main` function:

```java
public static void main(String[] args) {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    Duke duke = new Duke("../../data/duke_storage.txt");

    duke.greet();
    duke.taskMode();
}
```

(This code is found in `ip/src/main/java/duke/Duke.java`.)

## Progress

### Milestones
1. ~~Week 1~~
2. ~~Week 2~~
3. ~~Week 3~~
4. Week 4

### To do

- [x] Basic chatbot implementation
- [x] Extra features
- [ ] GUI

:+1: :sparkles: :camel: :tada:
:rocket: :metal: :octocat: