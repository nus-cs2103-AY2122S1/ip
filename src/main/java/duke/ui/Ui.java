package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents the UI that interacts with the user.
 */
public class Ui {
    /**
     * The scanner for the UI
     */
    private Scanner sc;
    /**
     * The parser to parse user commands
     */
    private Parser parser;
    /**
     * The status of the UI
     */
    private boolean isOpen = true;

    /**
     * Constructs the UI. Initializes the scanner to receive user input and the
     * parser to parse user commands.
     */
    public Ui() {
        sc = new Scanner(System.in);
        parser = new Parser();
    }

    /**
     * Greets the user with a hello message.
     */
    public void greet() {
        System.out.println("Hello! This is Jarvis.");
        System.out.println("What can I do for you sir?");
    }

    /**
     * Returns the command after parsing the user input.
     *
     * @return the command after parsing the user input.
     * @throws DukeException when the parser cannot parse the user input.
     */
    public Command receiveCommand() throws DukeException {
        System.out.println("---------------------------------");
        String cmd = sc.nextLine();
        return parser.parse(cmd);
    }

    /**
     * Displays the given list of tasks to the user.
     *
     * @param tasks The list of tasks to display to the user.
     */
    public void displayTasks(List<Task> tasks) {
        System.out.println("---------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Displays the matching tasks.
     *
     * @param tasks The matching tasks to display.
     */
    public void displayFoundTasks(List<Task> tasks) {
        System.out.println("---------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }


    /**
     * Displays a task marked as completed.
     *
     * @param task The task to be displayed as completed.
     */
    public void displayDoneTask(Task task) {
        System.out.println("---------------------------------");
        System.out.println("I have marked this task as done");
        System.out.printf("%s%n", task);
    }

    /**
     * Displays the task that was removed and the number of tasks remaining.
     *
     * @param task  The task that was removed.
     * @param count The number of tasks remaining.
     */
    public void displayRemovedTask(Task task, int count) {
        System.out.println("---------------------------------");
        System.out.println("I have removed this task");
        System.out.printf("%s%n", task);
        System.out.printf("Now you have %d tasks.%n", count);
    }

    /**
     * Displays an added task and the number of tasks after adding.
     *
     * @param task  The task that was added.
     * @param count The number of tasks after adding.
     */
    public void displayAddedTask(Task task, int count) {
        System.out.println("---------------------------------");
        System.out.println("I have added this task: ");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks.%n", count);
    }

    /**
     * Displays an error message to the user.
     *
     * @param msg The error message to display to the user.
     */
    public void displayErrorMessage(String msg) {
        System.out.println("---------------------------------");
        System.out.println(msg);
    }

    /**
     * Display a goodbye message to the user.
     */
    public void sayGoodbye() {
        System.out.println("---------------------------------");
        System.out.println("Goodbye Sir. Hope you have a pleasant day sir.");
        System.out.println("---------------------------------");
    }

    /**
     * Closes the UI and the scanner receiving keyboard input.
     */
    public void close() {
        sc.close();
        isOpen = false;
    }

    /**
     * Returns true if the UI is open or else false.
     *
     * @return A boolean value representing whether the UI is open.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
