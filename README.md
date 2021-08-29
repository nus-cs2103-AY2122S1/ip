# Welcome to Duke, your very own task manager!

**Things you can do with ~~Duck~~ Duke:**
1. Add different kinds of tasks
   * _Duke can keep track of tasks with specific deadlines._
   * _Having an event? Duke will keep track of your event periods as well._
   * _Just need a simple to-do list? Duke will do that too._
2. Show that tasks are done
   * _Once a task is done, mark it as so! Duke will keep track of the tasks you have completed._
3. Delete tasks
   * _Done with a task? Get rid of it. Typo? Just delete it and do it again._
4. Find a specific task
   * _Need to know what to get done today? Find tasks by date!_
   * _You can also find tasks by keywords!_

All you need to do is: 

- [ ] Head over to this application's [GitHub page](https://github.com/ntwbruce/ip)!
- [ ] Download the JAR file of "Duke v0.1" under Releases
- [ ] Run the JAR file, and you're good to go! :thumbsup:

If you're curious to play around and customise this app for yourself, 
feel free to download the source code from the same [GitHub page](https://github.com/ntwbruce/ip)!

You will find the main function in `src/main/java/duke/Duke`, as shown below.
````java
public class Duke {
    // ...
   public static void main(String[] args) {
      new Duke("data", "duke.txt").run(System.in);
   }
}
````

> See Mumei-ter!
