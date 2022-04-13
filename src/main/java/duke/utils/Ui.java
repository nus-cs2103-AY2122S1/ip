package duke.utils;

import java.util.Scanner;

/**
 * The Ui class is responsible for methods that interact with the user.
 */
public class Ui {
    private static final String GREETING = "Hi, I'm Duke, your personal assistant!\n";
    private static final String FAREWELL = "Bye from Duke!";

    /**
     * Displays the list of tasks from a TaskList object.
     *
     * @param taskList The TaskList object with tasks to be displayed.
     */
    public void printTasks(TaskList taskList) {
        assert taskList != null : "task list cannot be null";
        System.out.println(taskList);
    }

    /**
     * Displays the greeting message to the user.
     */
    public void greetUser() {
        System.out.println(GREETING);
    }

    /**
     * Displays the farewell message to the user.
     */
    public void farewellToUser() {
        System.out.println(FAREWELL);
    }

    /**
     * Displays the error message from an exception.
     *
     * @param e The exception to be displayed.
     */
    public void displayError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Returns the command provided by the user.
     *
     * @return The input provided by the user.
     */
    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
