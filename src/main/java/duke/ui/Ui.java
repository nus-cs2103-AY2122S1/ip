package duke.ui;

import java.util.Scanner;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Responsible for getting user input and displaying messages back
 */
public class Ui {
    private final String WELCOME_MESSAGE = "Hello! I'm Duke. What can I do for you?";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private final int INDENT = 4;
    private final String BORDER = "-".repeat(150);

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns user's command line input
     *
     * @return user input
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message
     */
    public String showWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints exit message
     */
    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Prints list of tasks user currently has
     *
     * @param tasks List of tasks user currently has
     */
    public String showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n" + tasks);
        return "Here are the tasks in your list:\n" + tasks;
    }

    /**
     * Prints a task added message
     *
     * @param task Task added
     * @param numRemainingTasks Total number of tasks user has
     */
    public String showTaskAddedMessage(Task task, int numRemainingTasks) {
        return "Got it. I've added this task:\n  " + task + '\n'
            + "You have " + numRemainingTasks + " tasks in the list\n";
    }

    /**
     * Prints a task deleted message
     *
     * @param task Task deleted
     * @param numRemainingTasks Total number of tasks user has
     */
    public String showTaskDeletedMessage(Task task, int numRemainingTasks) {
        return "Noted. I've removed this task:\n " + task + '\n'
            + "You have " + numRemainingTasks + " tasks in the list\n";
    }

    /**
     * Prints a task done message
     *
     * @param task Task marked done
     */
    public String showTaskDoneMessage(Task task) {
        return "Nice! this task has been marked done:\n  " + task + "\n";
    }

    /**
     * Prints list of filtered tasks
     *
     * @param tasks List of filtered tasks
     */
    public String showFilteredTaskList(TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }

    /**
     * Prints error message.
     *
     * @param e Exception object.
     */
    public String showErrorMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints the list of messages for the user.
     *
     * @param messages List of messages to be printed.
     */
    private void display(String... messages) {
        printWithIndent(BORDER);
        for (String message : messages){
            String[] newLineSeparated = message.split("\n");
            for (String line: newLineSeparated) {
                printWithIndent(line);
            }
        }
        printWithIndent(BORDER);
    }

    /**
     * Prints a message with indentation.
     *
     * @param string message to be printed.
     */
    private void printWithIndent(String string) {
        System.out.println(" ".repeat(INDENT) + string);
    }
}
