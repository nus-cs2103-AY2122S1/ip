# Peter Griffin MBBPH (My Big Boy Peter Helper)

> ┏ (゜ω゜)=☞ 
> 
> Here're some jokes to lighten your mood before you get all serious and read my codes: 😁
> 1. “I have an idea so smart that my head would explode if I even began to know what I was talking about.” — Peter Griffin, Family Guy, Season 2: Love Thy Trophy
> 2. “I can be just as non-competitive as anybody. Matter of fact, I'm the most non-competitive. So I win.” — Peter Griffin, Family Guy, Season 2: Running Mates
> 3. “Now I may be an idiot, but there's one thing I am not sir, and that sir, is an idiot.” — Peter Griffin, Family Guy, Season 5: Hell Comes to Quahog
> 4. *(more? find them yourselves @ [Family Guy](https://youtu.be/ShBlLLsGcXs))!*

## 1. What am I? 😏

```

▒█▀▀█ █▀▀ ▀▀█▀▀ █▀▀ █▀▀█ 　 ▒█▀▀█ █▀▀█ ░▀░ █▀▀ █▀▀ ░▀░ █▀▀▄ 　 ▒█▀▄▀█ ▒█▀▀█ ▒█▀▀█ ▒█▀▀█ ▒█░▒█ 
▒█▄▄█ █▀▀ ░░█░░ █▀▀ █▄▄▀ 　 ▒█░▄▄ █▄▄▀ ▀█▀ █▀▀ █▀▀ ▀█▀ █░░█ 　 ▒█▒█▒█ ▒█▀▀▄ ▒█▀▀▄ ▒█▄▄█ ▒█▀▀█ 
▒█░░░ ▀▀▀ ░░▀░░ ▀▀▀ ▀░▀▀ 　 ▒█▄▄█ ▀░▀▀ ▀▀▀ ▀░░ ▀░░ ▀▀▀ ▀░░▀ 　 ▒█░░▒█ ▒█▄▄█ ▒█▄▄█ ▒█░░░ ▒█░▒█
```

Clearly, I am here to clean up Tim's mess, this is what I do daily for this lazy boy:
- Greet him with some funny words. 👋
- Scold him and make him continue working on his project. 😠
- Keep track of his todos... *(He's from CS, he's quite busy apparently)* 😔
- Remind him of his random events and his crazy deadlines. 😰

## 2. Do you want to get a piece of me? 🎃

### **Here's what you're gonna do!**

1. Download this [`Bot-2.0.jar`](https://github.com/Timothyoung97/ip/releases/download/A-Release2/Bot-2.0.jar) file.
2. Navigate to the directory where you save `Bot-2.0.jar`.
3. Double Click!
4. Enjoy and have fun!

## 3. Features Development 👩‍💻

- [X] Repond to the following commands:
```json
Command     Custom Input                                                         Purpose
todo        something                                                            -> Add a todo type task into the task list
event       something /at yyyy-mm-ddThh:mm:ss (Format: ISO_LOCAL_DATE_TIME)      -> Add an event type task into the task list
deadline    something /by yyyy-mm-ddThh:mm:ss (Format: ISO_LOCAL_DATE_TIME)      -> Add an deadline type task into the task list
list                                                                             -> Display all tasks with their status
done        Integer (A task indexed in the task list)                            -> Mark a task as completed
delete      Integer (A task indexed in the task list)                            -> Delete a task from the task list
find        keywords (eg find homework play)                                     -> Find tasks that contain the respective keywords
massops     delete / done							 -> Mark all task as done / delete all tasks
```

## 4. Make me better? 📈

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). 

```java
Launcher.java

public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
```

5. If the setup is correct, you should see something like this:

![Capture](https://user-images.githubusercontent.com/62177572/133310665-d8b89965-a71c-4836-81f6-7ba668f41dd5.PNG)

## 5. Credits
- [Brian Griffin Picture Resource](https://en.wikipedia.org/wiki/File:Brian_Griffin.png)
- [Peter Griffin Picture Resource](https://en.wikipedia.org/wiki/File:Peter_Griffin.png)
- [Peter Griffin Dancing Picture Resource](https://www.nicepng.com/ourpic/u2q8a9t4u2a9q8u2_peter-griffin-family-guy-29-by-frasier-and/)
- [Peter Griffin Head Icon Picture Resource](https://www.kindpng.com/imgv/hJTimxi_family-guy-peter-face-hd-png-download/)
