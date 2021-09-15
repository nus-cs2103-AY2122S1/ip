import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String line = "____________________________________________________________";

    public static void Greet() {
        System.out.println( line + "\n" +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line);
    }

    public static void bye() {
        System.out.println(line +"\nBye. Hope to see you again soon!\n" + line);
    }

    public static void list(TaskList tasks) {
        System.out.println(line);
        System.out.println("Here are tasks in your list:");
        for(int index = 1; index <= tasks.size(); index++) {
            System.out.println(index + "." + tasks.get(index - 1).toString());
        }
        System.out.println(line);
    }

    public static void done(Task task) {
        System.out.println(line);
        System.out.println("done:\n" + task.toString());
        System.out.println(line);
    }

    public static void delete(TaskList tasks, Task task) {
        System.out.println(line);
        System.out.println("removed:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void add(TaskList tasks, Task task) {
        System.out.println(line);
        System.out.println("added: " + task
                + "\nNow you have " + tasks.size() + ""
                + " tasks in the list.");
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println(line);
        System.out.println("Cannot Load From Data.\n");
        System.out.println(line);
    }

}
