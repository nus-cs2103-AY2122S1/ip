package duke.ui;

import duke.DukeException;
import duke.tasks.Task;
import duke.TaskList;

import java.util.ArrayList;

/**
 * Text UI of the application.
 */
public class Ui {
    public static final String DIVIDER = "=================================";

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello from Duke!");
        System.out.println("What do you need to do today?");
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints the Goodbye message before exiting Duke.
     */
    public static void showExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("See you! Have a nice day!");
        System.out.println(DIVIDER);
    }

    /**
     * Shows the list of tasks to user, formatted as an indexed list.
     *
     * @param list the tasklist to show
     */
    public static void showTaskList(TaskList list) {
        System.out.println(DIVIDER);
        System.out.println(list.toDisplay());
        System.out.println(DIVIDER);

    }

    /**
     * Shows the last task added to the tasklist upon successful addition of a task.
     *
     * @param list the tasklist that is added unto
     */
    public static void showAddedTask(TaskList list) {
        System.out.println(DIVIDER);
        System.out.printf("added: %s\n", list.getTask(list.count()));
    }

    /**
     * Shows the task deleted to the tasklist upon successful deletion of a task.
     *
     * @param task the deleted task
     */
    public static void showDeletedTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Got it! I've removed this task:");
        System.out.println(task);
    }

    /**
     * Shows the task completed to the tasklist upon successful marking of a task as Done.
     *
     * @param task the completed task
     */
    public static void showDoneTask(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task);
        System.out.println(DIVIDER);
    }

    /**
     * Shows the number of tasks in the tasklist.
     *
     * @param tasks the tasklist
     */
    public static void showTaskCount(TaskList tasks) {
        System.out.printf("Now you have %d tasks in your list.%n%n", tasks.count());
        System.out.println(DIVIDER);

    }

    /**
     * Shows the description of error when an operation fails.
     *
     * @param e the exception that caused the error
     */
    public static void showErrorMessage(DukeException e) {
        System.out.println(DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(DIVIDER);
    }

    /**
     * Shows the search results by displaying the matching tasks in
     * an indexed list.
     *
     * @param results ArrayList of matching tasks
     */
    public static void showFindResults(ArrayList<Task> results) {
        System.out.println(DIVIDER);
        if (results.isEmpty()) {
            System.out.println("Sorry, no matches found :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task item : results) {
                System.out.println(index + ". " + item);
            }
        }
        System.out.println(DIVIDER);
    }
}
