import java.util.ArrayList;

/**
 * Manages the adding, deleting, and marking completion of tasks.
 *
 * @author Adam Ho
 */
public class Message {
    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Jani, your personal chat bot.\n" +
                "    How may I assist you today?");
        System.out.println("    ____________________________________________________________");
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Goodbye! Please visit me again soon :(");
        System.out.println("    ____________________________________________________________");
    }

    public static void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: \n      " + task);
        System.out.println("    Now you have " + TaskManager.listOfTasks.size() + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println("    ____________________________________________________________");
        for (Task task : tasks) {
            System.out.println("    " + (tasks.indexOf(task) + 1) + "." + task);
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void done(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: \n      " + task);
        System.out.println("    ____________________________________________________________");
    }

    public static void error(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + errorMessage);
        System.out.println("    ____________________________________________________________");
    }

    public static void delete(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task: \n      " + task);
        System.out.println("    Now you have " + TaskManager.listOfTasks.size() + " in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
