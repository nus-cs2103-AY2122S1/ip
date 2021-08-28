package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 *
 * @author Adam Ho
 */
public class Ui {
    private final String DIVIDER = "    ____________________________________________________________\n";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        System.out.println(DIVIDER
                + "    Hello! I'm Adam, your personal chat bot.\n"
                + "    How may I assist you today?"
                + DIVIDER);
    }

    public void showExit() {
        System.out.println(DIVIDER
                + "    Goodbye! Please visit me again soon :("
                + DIVIDER);
    }

    public void showAddTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task: \n      " + task);
        System.out.println("    Now you have " + "testsize" + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showDeleteTask(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Noted. I've removed this task: \n      " + task);
        System.out.println("    Now you have " + "testsize" + " in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println(DIVIDER + "TEST LOADING ERROR" + DIVIDER);
    }

//    public static void list(ArrayList<Task> tasks) {
//        System.out.println("    ____________________________________________________________");
//        for (Task task : tasks) {
//            System.out.println("    " + (tasks.indexOf(task) + 1) + "." + task);
//        }
//        System.out.println("    ____________________________________________________________");
//    }

    public static void done(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Nice! I've marked this task as done: \n      " + task);
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String errorMessage) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + errorMessage);
        System.out.println("    ____________________________________________________________");
    }
}
