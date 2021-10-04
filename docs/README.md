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

Clearly, I am here to clean up anyone's mess, this is what I do daily for YOU:
- Greet you with some funny words. 👋
- Remind you to continue working on your project. 😠
- Keep track of your todos... 😔
- Remind you of your random events and your crazy deadlines. 😰

## 2. Do you want to get a piece of me? 🎃

### **Here's what you're gonna do!**

1. Download this [`Bot-2.0.jar`](https://github.com/Timothyoung97/ip/releases/download/A-Release2/Bot-2.0.jar) file.
2. Navigate to the directory where you save `Bot-2.0.jar`.
3. Double Click!
4. Enjoy and have fun!

## 3. Features Development 👩‍💻

- [X] Repond to the following commands:
```java
Command     Custom Input                                                            Purpose
todo        taskDescription                                                         -> Add a todo type task into the task list
event       taskDescription /at yyyy-mm-ddThh:mm:ss (Format: ISO_LOCAL_DATE_TIME)   -> Add an event type task into the task list
deadline    taskDescription /by yyyy-mm-ddThh:mm:ss (Format: ISO_LOCAL_DATE_TIME)   -> Add an deadline type task into the task list
list                                                                                -> Display all tasks with their status
done        Integer (A task indexed in the task list)                               -> Mark a task as completed
delete      Integer (A task indexed in the task list)                               -> Delete a task from the task list
find        keywords (eg find homework play)                                        -> Find tasks that contain the respective keywords
massops     delete / done                                                           -> Mark all task as done / delete all tasks
bye                                                                                 -> Exit System
```

---

> ### Add `todo` task 🧾

Add the simplest task, a `todo` task, into your MBBPH. He will track it for you!

Simply enter the following format:

- ```todo taskDesc```

Example:
```java
Input:      todo eat food
Output:     [T][ ] eat food
```
![image](https://user-images.githubusercontent.com/62177572/135904520-8ca39ed3-dbf0-44d9-bfde-fc605d073f02.png)

---

> ### Add `event` task 🏁

Think of future events? Add an `event` task into your MBBPH. He will track both the task and the event time!

Simply enter the following format:

- ```event taskDecs /at yyyy-mm-ddThh:mm:ss```

**remember to input T between data and time to follow the ISO_LOCAL_DATE_TIME format**

Example:
```java
Input:      event meet Tim /at 2021-10-01T18:00:00
Output:     [E][ ] meet Tim (at: 2021-10-01 18:00:00)
```
![image](https://user-images.githubusercontent.com/62177572/135904599-7a1ad537-cf0e-43fd-9778-cc515d7c361c.png)

---

### Add `deadline` ⏳

Deadline coming up? No worries, tell MBBPH to keep track for you!

Simply enter the following format:
- ```deadline taskDecs /by yyyy-mm-ddThh:mm:ss```

**remember to input T between data and time to follow the ISO_LOCAL_DATE_TIME format**

Example:
```java
Input:      deadline work /by 2021-10-01T18:00:00
Output:     [E][ ] work (by: 2021-10-01 18:00:00)
```
![image](https://user-images.githubusercontent.com/62177572/135904749-72638d17-2e2c-45f1-9618-c06a550cb88a.png)

---

### List 📃

Have too many tasks on hand? Ask MBBPH to display everything at once!

Simply enter the following format:
- ```list```

Example:
```java
Input:      list
Output:     Refer to below for the expected output
```
![image](https://user-images.githubusercontent.com/62177572/135904845-16845f04-1b44-4fed-9acc-5f347f01d96f.png)

---

### Done ☑

Completed a task? Tell MBBPH to mark as done!

Simply enter the following format:
- ```done IndexOfTaskInList```

**remember to use `list` to find the index before marking a task as done!

Example:
```java
Input:      done 1
Output:     [T][X] eat food
```
![image](https://user-images.githubusercontent.com/62177572/135904918-c85dc0a5-3ab1-40bc-8d18-b471f429925c.png)

---

### Delete ❌

It's time to get rid of some tasks! Tell MBBPH to remove them from your task list!

Simply enter the following format:
- ```delete IndexOfTaskInList```

**remember to use `list` to find the index before deleting a task

Example:
```java
Input:      delete 1
Output:     Refer to below for the expected output
```
![image](https://user-images.githubusercontent.com/62177572/135905114-9ea5972b-9c6d-4d4d-8361-8186c32d125a.png)

---

### find 🔍

Losing track? Find a specific task using some keywords with the help of MBBPH!

Simply enter the following format:
- ```find keywords```

**You can key in multiple keywords! However, remember to seperate them with `space`**

Example:
```java
Input:      find Tim
Output:     Refer to below for the expected output
```
![image](https://user-images.githubusercontent.com/62177572/135905211-e7b1d826-6411-4c95-801f-aeba38775a00.png)

---

### MassOps 💪

One powerful function of MBBPH is to do many things at once! You can delete all tasks on the task tracker or ask MBBPH to mark all as done!

Simply enter either command:
- ```massops done```
- ```massops delete```

**Think twice before doing that! It cannot be undone!**

Example:
```java
Input:      massops done
Output:     Refer to below for the expected output
```
![image](https://user-images.githubusercontent.com/62177572/135905355-43140c23-b68d-472c-abde-6e0cce0c09e6.png)
```java
Input:      massops delete
Output:     Refer to below for the expected output
```
![image](https://user-images.githubusercontent.com/62177572/135905454-fdb1541d-8702-45ba-b7e0-ed2f2e32141b.png)

---

### Bye 👋

Done with MBBPH! Say bye!

Simply enter either command:
- ```bye```

**Press `enter` again after the commond would fully close the software**

Example:
![image](https://user-images.githubusercontent.com/62177572/135907686-ac9e6771-0987-44d6-ad62-d86155a4c9cc.png)


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
