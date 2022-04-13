package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Handles interacting with the user.
 */
public class Ui {

    /** Takes in user input. */
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Gets the next line of user inputs.
     *
     * @return next line of user inputs.
     */
    public String getNextLine() {
        return sc.nextLine().trim();
    }

    /**
     * Prints out a greeting for the user when the bot is first ran in command line.
     */
    public void cliGreet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a greeting for the user when the bot is first ran.
     */
    public static String greet() {
        return "Hello! I'm Duke.\nWhat can I do for you?";
    }

    /**
     * Prints out a goodbye message when the bot is exited.
     */
    public String exit() {
        sc.close();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out a message with the appropriate lines.
     *
     * @param message message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("__________________________________");
        System.out.println(message);
        System.out.println("__________________________________");
    }

    /**
     * Prints all elements in an ArrayList of Tasks.
     *
     * @param tasks various tasks in an ArrayList.
     */
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
