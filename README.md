# Duke
> “Your mind is for having ideas, not holding them.” – David Allen
Duke is an application that allows you to manage your tasks more efficiently!!! 😄

* Text-based
* Quick and easy to use
* ~Easy~ Very easy to learn!
* USE TODAY!!! :joy: :joy: :joy:

## Features

- [ ] Add tasks such as todos, deadlines or events
- [ ] Mark them as done
- [ ] Find tasks using specific key words
- [ ] Delete tasks
- [ ] List out all your tasks

## Setup

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Click https://github.com/mukundrs/ip/releases/tag/A-Jar [here] and download the jar file.
2. Run java -jar ip.jar in command prompt. Yoou should see this output: 
   ```
   _______________________________________________
   Hello!
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
   I'm Duke! How can I help you?
   _______________________________________________
   ```

If you Java programmer, you can use it to practice Java too. Here's the `main` method:
```java
public static void main(String[] args) {
        Duke duke = new Duke("tasklist.txt");
        duke.run();
    }
```
