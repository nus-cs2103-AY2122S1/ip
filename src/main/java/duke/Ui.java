package duke;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * Handles all functionality related to displaying of the user interface.
 */
public class Ui {
    private static final String DIVIDER = "───────────────────────────────────────────────────────────────────────\n";
    private static final String DOUBLE_DIVIDER = "═══════════════════════════════════════"
            + "════════════════════════════════\n";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println(DOUBLE_DIVIDER + "Welcome to Duke!\n" + DOUBLE_DIVIDER);
        System.out.println("Please enter the tasks (todo/event/deadline) to be added to the list.\n"
                + "(Enter 'list' to view the list, or 'bye' to exit.)\n" + DIVIDER);
    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        return "Exiting the program, goodbye!";
    }

    /**
     * Displays the loading error message.
     */
    public void showLoadingError() {
        System.out.println(DIVIDER + "No taskfile found, creating a new taskfile...\n" + DIVIDER);
    }

    /**
     * Displays the message when a Task is added to a TaskList.
     * @param task The task added to the TaskList.
     * @param taskList The TaskList with the Task added.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        return "Got it. I have added this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message when a Task is deleted from a TaskList.
     * @param task The task deleted from the TaskList.
     * @param taskList The TaskList the Task is deleted from.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return "Noted. I have removed this task:\n  " + task + "\n Now you have "
                + taskList.size() + " tasks in the list.\n";
    }

    /**
     * Displays the message shown when a Task is marked as done
     *
     * @param task The Task which is marked as done.
     */
    public String showTaskDone(Task task) {
        return "Great! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Displays an error message with the given error.
     *
     * @param error The error message to be displayed.
     */
    public void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    /**
     * Prints a horizontal dividing line.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Reads the command entered by the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the contents of the TaskList
     *
     * @param taskList The TaskList whose contents are to be displayed.
     */
    public String showList(TaskList taskList) {
        return "Here are the tasks in your list: " + taskList;
    }

    /**
     * Displays the matching tasks in the list.
     * @param taskList List of matching tasks.
     */
    public String showMatchingTasks(TaskList taskList) {
        return "Here are the matching tasks in your list: " + taskList;
    }

}
