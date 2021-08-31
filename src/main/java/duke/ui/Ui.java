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
    public static String showWelcomeMessage() {
        String welcomeMsg = "Hello from Duke! \n"
                + "What do you need to do today?";
        return welcomeMsg;
    }

    /**
     * Generates and prints the Goodbye message before exiting Duke.
     */
    public static String showExitMessage() {
        return "See you! Have a nice day!";
    }

    /**
     * Shows the list of tasks to user, formatted as an indexed list.
     *
     * @param list the tasklist to show
     */
    public static String showTaskList(TaskList list) {
        return list.toDisplay();


    }

    /**
     * Shows the last task added to the tasklist upon successful addition of a task.
     *
     * @param list the tasklist that is added unto
     */
    public static String showAddedTask(TaskList list) {
        String addedTask = String.format("added: %s\n", list.getTask(list.count()));
        return addedTask;
    }

    /**
     * Shows the task deleted to the tasklist upon successful deletion of a task.
     *
     * @param task the deleted task
     */
    public static String showDeletedTask(Task task) {
        String deletedTask = "Got it! I've removed this task: \n" + task;
        return deletedTask;
    }

    /**
     * Shows the task completed to the tasklist upon successful marking of a task as Done.
     *
     * @param task the completed task
     */
    public static String showDoneTask(Task task) {
        String completedTask = "Good job! I've marked this task as done: \n" + task;
        return completedTask;
    }

    /**
     * Shows the number of tasks in the tasklist.
     *
     * @param tasks the tasklist
     */
    public static String showTaskCount(TaskList tasks) {
        String taskCount = String.format("Now you have %d tasks in your list.%n%n", tasks.count());
        return taskCount;

    }

    /**
     * Shows the description of error when an operation fails.
     *
     * @param e the exception that caused the error
     */
    public static String showErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Shows the search results by displaying the matching tasks in
     * an indexed list.
     *
     * @param results ArrayList of matching tasks
     */
    public static String showFindResults(ArrayList<Task> results) {
        if (results.isEmpty()) {
            return "Sorry, no matches found :(";
        } else {
            String searchResults = "Here are the matching tasks in your list: \n";
            int index = 1;
            for (Task item : results) {
                searchResults += index + ". " + item;
            }
            return searchResults;
        }
    }
}
