package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String divider = "____________________________________________________________";

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays a greeting message.
     */
    public void greet() {
        System.out.println("Hello from\n" + logo +"\n");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a goodbye message.
     */
    public void goodBye() {
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints a divider.
     */
    public void showLine() {
        System.out.println(divider);
    }

    /**
     * Prints a message for when a task is created.
     *
     * @param task The task created.
     * @param taskList The current TaskList.
     */
    public void taskCreatedMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
    }

    /**
     * Reads the given input from the user.
     *
     * @return The String input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the results of the find command.
     *
     * @param matchList The resultant list of tasks from calling find.
     */
    public void showFindMessage(ArrayList<Task> matchList) {
        if (matchList == null || matchList.isEmpty()) {
            System.out.println("Oh no, Duke cannot find any matches!");
            return;
        }

        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < matchList.size(); i++) {
            System.out.println((i + 1) + ".  " + matchList.get(i));
        }
    }
}
