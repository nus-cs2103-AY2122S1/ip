package duke;

import java.util.Scanner;

import duke.tasks.Task;

/**
 * This class represents a User Interface.
 * It deals with user interactions like reading user input and displaying messages to the user.
 */
public class Ui {
    private static final String DIVIDER_LINE = "_______________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows loading error when tasks cannot be loaded from Storage.
     */
    public void showLoadingError() {
        System.out.println("There was a problem loading saved tasks.");
    }

    /**
     * Shows greeting when Duke chatbot is first started.
     */
    public void showWelcome() {
        System.out.println("Hello...\nWhat do you want?\n");
    }

    /**
     * Shows message when Duke chatbot is exited.
     */
    public void showBye() {
        System.out.println("Whatever...");
    }

    /**
     * Returns parsed user input.
     *
     * @return Parsed user input as String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows divider line.
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Shows error message when DukeException is thrown.
     *
     * @param message Error message to be shown.
     */
    public void showError(String message) {
        System.out.println("Error. " + message);
    }

    /**
     * Shows message to user when task is marked as done.
     *
     * @param task Task that is marked as done.
     */
    public void showMarkDone(Task task) {
        System.out.println("I've marked this task as done:");
        System.out.println("\t" + task);
    }

    /**
     * Shows message to user when task is deleted.
     *
     * @param task Task that was deleted.
     * @param tasks Updated TaskList that does not contain deleted task.
     */
    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n\t" + task);
        printTasksCount(tasks);
    }

    /**
     * Prints message showing number of tasks in TaskList.
     *
     * @param tasks TaskList of tasks whose number of tasks is to be displayed.
     */
    void printTasksCount(TaskList tasks) {
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list.");
    }

    /**
     * Show message to user when a task is added to a TaskList.
     *
     * @param tasks Updated TaskList of tasks containing added task.
     * @param task Task that was added to the TaskList.
     */
    public void showAddTask(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this task:\n\t" + task);
        printTasksCount(tasks);
    }

    /**
     * Closes Scanner stored in Ui.
     */
    public void close() {
        scanner.close();
    }
}
