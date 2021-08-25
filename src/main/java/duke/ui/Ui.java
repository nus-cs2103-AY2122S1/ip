package duke.ui;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Handles interacting with the user.
 */
public class Ui {

    /**
     * Prints out a greeting for the user when the bot is first ran.
     */
    public void greet() {
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a goodbye message when the bot is exited.
     */
    public void exit() {
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    /**
     * Prints out a message with the appropriate lines.
     *
     * @param message message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println("__________________________________");
        System.out.println(message);
        System.out.println("__________________________________");
    }

    public static void printList(ArrayList<Task> messages) {
        System.out.println("__________________________________");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(messages.get(i).toString());
            if (i != messages.size() - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
        System.out.println("__________________________________");
    }

}
