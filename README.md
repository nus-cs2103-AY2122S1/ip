# Duke

> The key is not to prioritize what’s on your schedule, but to schedule your priorities.  
> ~ Stephen Covey

Duke is an awesome way to keep track of all your daily tasks.

Forgot what is coming up in your schedule? **Duke it**
Hard to remember all your appointments? **Duke it**
Find it difficult to manage your todo list? **Duke it**

Duke is:
1. `Text-based`
2. Easy-to-learn
3. _Blazing_ fast :fire:
4. **Free** :no_entry_sign::dollar:

## Setting up

Prerequisites: JDK 11, download [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

1. Download the Duke jar file [here](https://github.com/chunweii/ip/releases)
2. Open a terminal/command prompt and navigate to the directory of the jar file.
3. Run `java -jar ip.jar`
4. Add your tasks!
## Future features
- ~~Graphical User Interface~~ will be implemented in the next release!
- Reminders

Do support us! If you are a Java programmer, consider improving the program and submitting a pull request! Here is our main method:
```java
public static void main(String[] args) {
    // new Duke(<file path>, <limit>).run();
    new Duke("./dukedata.txt", 100).run();
}
```